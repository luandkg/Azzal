package libs.zettaquorum;

import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.*;
import libs.luan.*;
import libs.tronarko.Tronarko;

public class ZettaPasta {

    private Arquivador mArquivador;
    private Fazendario mFazendario;
    private ArmazemPrimario mSilos;

    private Armazem mArquivos;
    private ZettaSequenciador mSequenciador;
    private ArmazemIndiceSumario mIndice;

    public ZettaPasta(Arquivador eArquivador, Fazendario eFazendario,ArmazemPrimario eSilos, Armazem eArquivos, ZettaSequenciador eSequenciador, ArmazemIndiceSumario eIndice) {
        mArquivador = eArquivador;
        mFazendario=eFazendario;
        mSilos = eSilos;
        mArquivos = eArquivos;
        mSequenciador = eSequenciador;
        mIndice = eIndice;
    }


    public void dump() {

        Lista<Entidade> silos = mSilos.getItens();

        for (Entidade e_silo : silos) {

            Silo silo = new Silo(mArquivador, e_silo.atLong("Ponteiro"));

            e_silo.at("Quantidade", silo.getQuantidade());
            e_silo.at("Alocados", silo.getAlocados());
            e_silo.at("Disponiveis", silo.getDisponiveis());
            e_silo.at("Ocupados", silo.getOcupados());

        }


        ENTT.EXIBIR_TABELA_COM_NOME(silos, "ARMAZEM -- @Silos::Dados ( " + mSilos.getIndice() + " )");
    }


    public Lista<Entidade> getItensArquivos() {

        Lista<Entidade> lista = new Lista<Entidade>();

        for (ItemAlocado item : mArquivos.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            e_item.at("@PTR", item.getPonteiroDados());

            e_item.tornar_primeiro("@PTR");
            e_item.tornar_primeiro("@ID");

            lista.adicionar(e_item);
        }

        return lista;
    }

    public Lista<Par<ItemAlocado, Entidade>> getItensArquivosAtualizaveis() {

        Lista<Par<ItemAlocado, Entidade>> lista = new Lista<Par<ItemAlocado, Entidade>>();

        for (ItemAlocado item : mArquivos.getItensAlocados()) {
            lista.adicionar(new Par<ItemAlocado, Entidade>(item, ENTT.PARSER_ENTIDADE(item.lerTextoUTF8())));
        }

        return lista;
    }

    public void dump_arquivos() {
        ENTT.EXIBIR_TABELA_COM_NOME(getItensArquivos(), "@DUMP - ARQUIVOS");
    }

    public Lista<Entidade> getArquivos() {
        return getItensArquivos();
    }

    public Lista<ZettaArquivo> getArquivosAtualizaveis() {

        Lista<ZettaArquivo> lista = new Lista<ZettaArquivo>();

        for (Par<ItemAlocado, Entidade> par : getItensArquivosAtualizaveis()) {
            lista.adicionar(new ZettaArquivo(this,mIndice, par.getChave(), par.getValor()));
        }

        return lista;
    }


    public Opcional<ZettaArquivo> procurar_arquivo(String eNome) {

        Opcional<ZettaArquivo> ret = Opcional.CANCEL();

        for (Par<ItemAlocado, Entidade> par : getItensArquivosAtualizaveis()) {
            if (par.getValor().is("Nome", eNome)) {
                ret = Opcional.OK(new ZettaArquivo(this, mIndice,par.getChave(), par.getValor()));
                break;
            }
        }

        return ret;
    }


    public void adicionar_arquivo(String eNome, byte[] eBytes) {

        organizar_espacos();

        Opcional<Long> bloco_arquivo = obter_um();

        if (bloco_arquivo.isOK()) {

            long bloco_a = bloco_arquivo.get();

            mFazendario.marcar_ocupado(bloco_a);

            mArquivador.setPonteiro(bloco_a);

            int bloco_status_aqui = mArquivador.get_u8();
            long ponteiro_eu_mesmo = mArquivador.get_u64();
            long ponteiro_dados_aqui = mArquivador.get_u64();


            mArquivador.setPonteiro(ponteiro_dados_aqui);
            mArquivador.set_u32(0);

            SuperBloco super_inode = new SuperBloco(mArquivador, mFazendario,this, bloco_a);

            super_inode.guardar(eBytes);


            Entidade arquivo = new Entidade();
            arquivo.at("INode", bloco_a);
            arquivo.at("Nome", eNome);
            arquivo.at("Tamanho", eBytes.length);
            arquivo.at("TamanhoFormatado", fmt.formatar_tamanho_precisao_dupla(eBytes.length));

            arquivo.at("DDC", Tronarko.getTronAgora().getTextoZerado());
            arquivo.at("DDA", Tronarko.getTronAgora().getTextoZerado());
            arquivo.at("DDM", Tronarko.getTronAgora().getTextoZerado());

            long proximo = mSequenciador.getProximo();

            arquivo.at("@ID", proximo);

            ItemAlocado item =  mArquivos.item_adicionar(ENTT.TO_DOCUMENTO(arquivo));

            mIndice.setItem(proximo, item.getPonteiroDados());

        }


    }

    public Opcional<Long> obter_um() {

        Opcional<Long> ret = Opcional.CANCEL();

        for (Entidade silo : mSilos.getItens()) {

            Silo silo_corrente = new Silo(mArquivador, silo.atLong("Ponteiro"));

            ret = silo_corrente.obter_um();

            if (ret.isOK()) {
                break;
            }

            organizar_espacos();

        }

        return ret;
    }

    public Lista<Long> obter_ate(int quantidade) {

        Lista<Long> ret = new Lista<Long>();

        for (Entidade silo : mSilos.getItens()) {

            Silo silo_corrente = new Silo(mArquivador, silo.atLong("Ponteiro"));

            ret.adicionar_varios(silo_corrente.obter_ate(quantidade));

            if (ret.getQuantidade() == quantidade) {
                break;
            }

            organizar_espacos();

        }

        return ret;
    }


    public void organizar_espacos() {

        boolean esta_abaixo_do_minimo = true;
        long minimo = 5;

        long somando_livres = 0;

        for (Entidade silo : mSilos.getItens()) {

            Silo silo_corrente = new Silo(mArquivador, silo.atLong("Ponteiro"));

            somando_livres += silo_corrente.getDisponiveis();

            if (somando_livres >= minimo) {
                esta_abaixo_do_minimo = false;
                break;
            }

        }

        if (esta_abaixo_do_minimo) {

            fmt.print("SILOS :: ABAIXO DO MINIMO !");

            long ptr_silo = mFazendario.CRIAR_SILO(mSilos.getIndice());

            Silo silo_corrente = new Silo(mArquivador, ptr_silo);

            Entidade novo_silo = new Entidade();
            novo_silo.at("SiloID", mSilos.getItensAlocadosContagem());
            novo_silo.at("Ponteiro", silo_corrente.getPonteiro());
            novo_silo.at("Inicio", silo_corrente.getMapaInicio());
            novo_silo.at("Fim", silo_corrente.getMapaFim());
            novo_silo.at("Tamanho", silo_corrente.getMapaTamanho());
            novo_silo.at("Quantidade", silo_corrente.getQuantidade());

            mSilos.adicionar(novo_silo);
        }

    }






    public SuperBloco getSuperBloco(Entidade arquivo) {
        SuperBloco superbloco = new SuperBloco(mArquivador, mFazendario,this, arquivo.atLong("INode"));
        return superbloco;
    }


    public void exibir_arquivo(Entidade arquivo) {

        SuperBloco superbloco = new SuperBloco(mArquivador, mFazendario,this, arquivo.atLong("INode"));

        long arquivo_tamanho_completo = superbloco.getTamanhoEscrito();

        fmt.print("\t -- {}", arquivo_tamanho_completo);


        fmt.print("Mapa de Blocos");

        long dados_arquivo_inicio = 0;
        long dados_arquivo_fim = 0;

        for (Long bloco_ptr : superbloco.getBlocos()) {

            Bloco bloco = new Bloco(mArquivador, mFazendario,this, bloco_ptr);

            fmt.print("\t >> {} -->> {}", bloco_ptr, bloco.getInodesContagem());

            for (Long inode : bloco.getInodes()) {

                dados_arquivo_fim += Matematica.KB(16);
                if (dados_arquivo_fim > arquivo_tamanho_completo) {
                    dados_arquivo_fim = arquivo_tamanho_completo;
                }

                long dados_arquivo_bloco_tamanho = dados_arquivo_fim - dados_arquivo_inicio;

                fmt.print("\t\t ++ {}   :: {} : {} - {} :: {}", inode, dados_arquivo_inicio, dados_arquivo_fim, dados_arquivo_bloco_tamanho, fmt.formatar_tamanho_precisao_dupla(dados_arquivo_bloco_tamanho));


                dados_arquivo_inicio += Matematica.KB(16);

            }

        }

    }


    public void adicionar_ou_atualizar(String nome, byte[] bytes) {

        boolean atualizado = false;

        for (ZettaArquivo arquivo : getArquivosAtualizaveis()) {

            if (arquivo.isNome(nome)) {

                arquivo.atualizar_dados(bytes);

                atualizado = true;
                break;
            }

        }

        if (!atualizado) {
            adicionar_arquivo(nome, bytes);
        }

    }


    public void adicionar_arquivo_se_nao_existir(String eNome, byte[] eBytes) {

        boolean existe = false;

        for (ZettaArquivo arquivo : getArquivosAtualizaveis()) {
            if (arquivo.isNome(eNome)) {
                existe = true;
                break;
            }

        }

        if (!existe) {
            adicionar_arquivo(eNome, eBytes);
        }

    }


    public void limpar() {
        for (ZettaArquivo arquivo : getArquivosAtualizaveis()) {
            arquivo.remover();
        }
    }

}

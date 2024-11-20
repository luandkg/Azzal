package libs.zettaquorum;

import libs.arquivos.binario.Arquivador;
import libs.entt.Entidade;
import libs.fazendario.*;
import libs.luan.*;
import libs.tronarko.Tronarko;

public class Silos {

    private Arquivador mArquivador;
    private Fazendario mFazendario;

    private ArmazemInterno mSilos;
    private ArmazemInterno mArquivos;

    public Silos(Arquivador eArquivador, Fazendario eFazendario) {
        mArquivador = eArquivador;
        mFazendario = eFazendario;
        mSilos = new ArmazemInterno(mFazendario, "@Silos::Dados");
        mArquivos = new ArmazemInterno(mFazendario, "@Silos::Arquivos");

    }


    public void dump() {
        mSilos.dump();
    }

    public void dump_arquivos() {
        mArquivos.dump();
    }

    public Lista<Entidade> getArquivos() {
        return mArquivos.getItens();
    }

    public Lista<Par<ItemAlocado, Entidade>> getArquivosAtualizaveis() {
        return mArquivos.getItensAtualizaveis();
    }


    public void adicionar_arquivo(String eNome, byte[] eBytes) {

        organizar_espacos();

        Opcional<Long> bloco_arquivo = obter_um();

        if (bloco_arquivo.isOK()) {

            long bloco_a = bloco_arquivo.get();

            marcar_ocupado(bloco_a);

            mArquivador.setPonteiro(bloco_a);

            int bloco_status_aqui = mArquivador.get_u8();
            long ponteiro_eu_mesmo = mArquivador.get_u64();
            long ponteiro_dados_aqui = mArquivador.get_u64();


            mArquivador.setPonteiro(ponteiro_dados_aqui);
            mArquivador.set_u32(0);

            SuperBloco super_inode = new SuperBloco(mArquivador, this, bloco_a);

            super_inode.guardar(eBytes);


            Entidade arquivo = new Entidade();
            arquivo.at("INode", bloco_a);
            arquivo.at("Nome", eNome);
            arquivo.at("Tamanho", eBytes.length);
            arquivo.at("TamanhoFormatado", fmt.formatar_tamanho_precisao_dupla(eBytes.length));

            arquivo.at("DDC", Tronarko.getTronAgora().getTextoZerado());
            arquivo.at("DDA", Tronarko.getTronAgora().getTextoZerado());
            arquivo.at("DDM", Tronarko.getTronAgora().getTextoZerado());


            mArquivos.adicionar(arquivo);
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


    public void marcar_ocupado(long bloco_ponteiro) {

        mArquivador.setPonteiro(bloco_ponteiro);

        int bloco_status = mArquivador.get_u8();

        if (bloco_status == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO) {

            mArquivador.ir_para_o_fim();
            long ponteiro_dados = mArquivador.getPonteiro();

            mArquivador.set_u8_em_bloco(Fazendario.TAMANHO_AREA_ITEM, (byte) 0);

            mArquivador.setPonteiro(bloco_ponteiro);
            mArquivador.set_u8((byte) Fazendario.ESPACO_VAZIO_E_JA_ALOCADO);
            long pular = mArquivador.get_u64();
            mArquivador.set_u64(ponteiro_dados);

        }


        mArquivador.setPonteiro(bloco_ponteiro);
        mArquivador.set_u8((byte) Fazendario.ESPACO_OCUPADO);

    }

    public Inode getInode(long ptr) {

        mArquivador.setPonteiro(ptr);

        int bloco_status_aqui = mArquivador.get_u8();
        long ponteiro_eu_mesmo = mArquivador.get_u64();
        long ponteiro_dados_aqui = mArquivador.get_u64();

        Inode inode = new Inode(bloco_status_aqui, ponteiro_eu_mesmo, ponteiro_dados_aqui);

        return inode;
    }

    public SuperBloco getSuperBloco(Entidade arquivo) {
        SuperBloco superbloco = new SuperBloco(mArquivador, this, arquivo.atLong("INode"));
        return superbloco;
    }


    public void exibir_arquivo(Entidade arquivo) {

        SuperBloco superbloco = new SuperBloco(mArquivador, this, arquivo.atLong("INode"));

        long arquivo_tamanho_completo = superbloco.getTamanhoEscrito();

        fmt.print("\t -- {}", arquivo_tamanho_completo);


        fmt.print("Mapa de Blocos");

        long dados_arquivo_inicio = 0;
        long dados_arquivo_fim = 0;

        for (Long bloco_ptr : superbloco.getBlocos()) {

            Bloco bloco = new Bloco(mArquivador, this, bloco_ptr);

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
}
package libs.aqz.extincao;

import libs.aqz.utils.ItemDoBancoUTF8;
import libs.aqz.extincao.armazenador_antigo.ArmazenadorEmExtincao;
import libs.armazenador.ParticaoEmExtincao;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.RefLong;

public class ColecaoUTF8Antigamente {

    private String mNome;
    private ArmazenadorEmExtincao mArmazenador;
    private ParticaoEmExtincao mSequencias;
    private ParticaoEmExtincao mColecao;

    public ColecaoUTF8Antigamente(String eNome, ArmazenadorEmExtincao eArmazenador, ParticaoEmExtincao eSequencias, ParticaoEmExtincao eColecao) {
        mNome = eNome;
        mArmazenador = eArmazenador;
        mSequencias = eSequencias;
        mColecao = eColecao;

        Sequenciador.organizar_sequencial(mSequencias, mNome);

    }

    public void zerarSequencial() {
        Sequenciador.zerar_sequencial(mSequencias, mNome);
    }


    public boolean adicionarUTF8(Entidade objeto) {

        int chave = Sequenciador.aumentar_sequencial(mSequencias, mNome);
        objeto.at("ID", (chave));
        long endereco = mColecao.adicionarUTF8(ENTT.TO_DOCUMENTO(objeto));

        mColecao.set(chave, endereco);

        return false;
    }

    public boolean adicionarUTF8SemID(Entidade objeto) {

        // int chave = Sequenciador.aumentar_sequencial(mSequencias, mNome);
        long endereco = mColecao.adicionarUTF8(ENTT.TO_DOCUMENTO(objeto));

        //   mColecao.set(chave, endereco);

        return false;
    }


    public Opcional<Long> adicionarUTF8ComIDInterno(Entidade objeto) {

        int chave = Sequenciador.aumentar_sequencial(mSequencias, mNome);
        objeto.at("@ID", (chave));
        long endereco = mColecao.adicionarUTF8(ENTT.TO_DOCUMENTO(objeto));

        mColecao.set(chave, endereco);

        return Opcional.OK(endereco);
    }


    public void remover_por_chave(int eID) {

        for (ItemDoBancoUTF8Antigamente item : mColecao.getItensUTF8()) {
            Entidade objeto = item.toEntidadeUTF8();
            if (objeto.atIntOuPadrao("ID", 0) == eID) {

                mColecao.set(objeto.atIntOuPadrao("ID", 0), 0);

                mColecao.remover(item);
                break;
            }
        }

    }

    public void remover(ItemDoBancoUTF8 item) {

        Entidade objeto = item.toEntidadeUTF8();
        mColecao.set(objeto.atIntOuPadrao("ID", 0), 0);

        mColecao.remover(item);
    }

    public void atualizar_por_chave(int eID, Entidade objeto_novo) {

        for (ItemDoBancoUTF8Antigamente item : mColecao.getItensUTF8()) {
            Entidade objeto = item.toEntidadeUTF8();
            if (objeto.atIntOuPadrao("ID", 0) == eID) {
                objeto_novo.at("ID", eID);
                item.atualizarUTF8(objeto_novo.toString());
                break;
            }
        }

    }

    public void limpar() {
        mColecao.limpar();
    }

    public void limpar_profundamente() {
        mColecao.limpar_profundamente();
    }

    public void exibir_dump() {
        mColecao.exibir_dump();
    }

    public void limpar_com_log(String eLog) {
        mColecao.limpar_com_long(eLog);
    }

    public Lista<ItemDoBancoUTF8Antigamente> getItens() {
        return mColecao.getItensUTF8();
    }

    public long getItensContagem() {
        return mColecao.getItensContagem();
    }

    public void primeiro_campo(String ePrimeiro) {

        for (ItemDoBancoUTF8Antigamente item : getItens()) {

            Entidade objeto = item.toEntidadeUTF8();

            //  objeto.rearranjar(ePrimeiro, 0);

            item.atualizarUTF8(objeto.toString());

        }

    }


    public void indexar(String eArquivoIndice) {

        Arquivador.remover(eArquivoIndice);

        Arquivador mArquivadorIndice = new Arquivador(eArquivoIndice);
        mArquivadorIndice.setPonteiro(0);

        mArquivadorIndice.set_u8((byte) 100);
        mArquivadorIndice.set_u8((byte) 130);

        int indice_maior = 0;

        for (ItemDoBancoUTF8Antigamente item : getItens()) {

            Entidade objeto = item.toEntidadeUTF8();
            int indice = objeto.atIntOuPadrao("ID", 0);

            if (indice > indice_maior) {
                indice_maior = indice;
            }

        }


        for (int chave = 0; chave < (indice_maior + 1); chave++) {
            mArquivadorIndice.set_u8((byte) 0);
            mArquivadorIndice.set_u64(0);
        }

        for (ItemDoBancoUTF8Antigamente item : getItens()) {

            Entidade objeto = item.toEntidadeUTF8();
            int indice = objeto.atIntOuPadrao("ID", 0);

            long ponteiro = 2L + ((1L + 8L) * indice);

            mArquivadorIndice.setPonteiro(ponteiro);

            mArquivadorIndice.set_u8((byte) 100);
            mArquivadorIndice.set_u64(item.getPonteiro());

        }

        mArquivadorIndice.encerrar();

    }

    public void mostrar_indices(String eArquivoIndice) {


        Arquivador mArquivadorIndice = new Arquivador(eArquivoIndice);
        mArquivadorIndice.setPonteiro(0);

        int b1 = mArquivadorIndice.get_u8();
        int b2 = mArquivadorIndice.get_u8();

        long tamanho = mArquivadorIndice.getLength();
        long indo = 2L;

        long chave = 0;

        while (indo < tamanho) {

            int item_status = mArquivadorIndice.get_u8();
            long item_ponteiro = mArquivadorIndice.get_u64();

            if (item_status == 100) {
                System.out.println(" >> Item " + chave + " : " + item_ponteiro);
            }

            chave += 1;
            indo += 9;
        }


        mArquivadorIndice.encerrar();

    }

    private Opcional<RefLong> get(long procurar_indice) {
        return mColecao.get(procurar_indice);
    }

    public Opcional<ItemDoBancoUTF8> getIndexado(long procurar_indice) {

        Opcional<ItemDoBancoUTF8> ret = new Opcional<ItemDoBancoUTF8>();

        Opcional<RefLong> proc2t = get(procurar_indice);

        if (proc2t.temValor()) {

            ItemDoBancoUTF8 item = mArmazenador.getItemDiretoUTF8(proc2t.get().get());

            if (item.existe()) {
                ret.set(item);
            }


        }

        return ret;
    }

    public void indexado_atualizar(long procurar_indice,Entidade dados) {

        Opcional<RefLong> proc2t = get(procurar_indice);

        if (proc2t.temValor()) {
            ItemDoBancoUTF8 item = mArmazenador.getItemDiretoUTF8(proc2t.get().get());

            if (item.existe()) {
                item.atualizarUTF8(dados);
            }
        }

    }

    public Lista<Entidade> getObjetosUTF8() {

        Lista<ItemDoBancoUTF8Antigamente> itens = getItens();

        Lista<Entidade> objetos = new Lista<Entidade>();

        for (ItemDoBancoUTF8Antigamente item : itens) {
            // fmt.print("ItemDoBanco : {}",item.lerTexto());
            Entidade obj = item.toEntidadeUTF8();
            objetos.adicionar(obj);
        }

        return objetos;
    }


}

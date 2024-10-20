package libs.ez;


import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.RefLong;

public class AQColecao {

    private String mNome;
    private Armazenador mArmazenador;
    private Banco mSequencias;
    private Banco mColecao;

    public AQColecao(String eNome, Armazenador eArmazenador, Banco eSequencias, Banco eColecao) {
        mNome = eNome;
        mArmazenador = eArmazenador;
        mSequencias = eSequencias;
        mColecao = eColecao;

        AQSequenciador.organizar_sequencial(mSequencias, mNome);

    }

    public void zerarSequencial() {
        AQSequenciador.zerar_sequencial(mSequencias, mNome);
    }




    public boolean adicionar(Entidade objeto) {

        int chave = AQSequenciador.aumentar_sequencial(mSequencias, mNome);
        objeto.at("ID",(chave));
        long endereco = mColecao.adicionar(ENTT.TO_DOCUMENTO(objeto));

        mColecao.set(chave, endereco);

        return false;
    }


    public void remover_por_chave(int eID) {

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade objeto = item.toEntidade();
            if (objeto.atIntOuPadrao("ID",0) == eID) {

                mColecao.set(objeto.atIntOuPadrao("ID",0), 0);

                mColecao.remover(item);
                break;
            }
        }

    }

    public void remover(ItemDoBanco item) {

        Entidade objeto = item.toEntidade();
        mColecao.set(objeto.atIntOuPadrao("ID",0), 0);

        mColecao.remover(item);
    }

    public void atualizar_por_chave(int eID, Entidade objeto_novo) {

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade objeto = item.toEntidade();
            if (objeto.atIntOuPadrao("ID",0) == eID) {
                objeto_novo.at("ID",eID);
                item.atualizar(objeto_novo.toString());
                break;
            }
        }

    }

    public void limpar() {
        mColecao.limpar();
    }

    public void limpar_com_log(String eLog) {
        mColecao.limpar_com_long(eLog);
    }

    public Lista<ItemDoBanco> getItens() {
        return mColecao.getItens();
    }

    public long getItensContagem() {
        return mColecao.getItensContagem();
    }

    public void primeiro_campo(String ePrimeiro) {

        for (ItemDoBanco item : getItens()) {

            Entidade objeto = item.toEntidade();

          //  objeto.rearranjar(ePrimeiro, 0);

            item.atualizar(objeto.toString());

        }

    }


    public void indexar(String eArquivoIndice) {

        Arquivador.remover(eArquivoIndice);

        Arquivador mArquivadorIndice = new Arquivador(eArquivoIndice);
        mArquivadorIndice.setPonteiro(0);

        mArquivadorIndice.set_u8((byte) 100);
        mArquivadorIndice.set_u8((byte) 130);

        int indice_maior = 0;

        for (ItemDoBanco item : getItens()) {

            Entidade objeto = item.toEntidade();
            int indice = objeto.atIntOuPadrao("ID",0);

            if (indice > indice_maior) {
                indice_maior = indice;
            }

        }


        for (int chave = 0; chave < (indice_maior + 1); chave++) {
            mArquivadorIndice.set_u8((byte) 0);
            mArquivadorIndice.set_u64(0);
        }

        for (ItemDoBanco item : getItens()) {

            Entidade objeto =item.toEntidade();
            int indice = objeto.atIntOuPadrao("ID",0);

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

    public Opcional<ItemDoBanco> getIndexado(long procurar_indice) {

        Opcional<ItemDoBanco> ret = new Opcional<ItemDoBanco>();

        Opcional<RefLong> proc2t = get(procurar_indice);

        if (proc2t.temValor()) {

            ItemDoBanco item = mArmazenador.getItemDireto(proc2t.get().get());

            if (item.existe()) {
                ret.set(item);
            }


        }

        return ret;
    }

    public Lista<Entidade> getObjetos() {

        Lista<ItemDoBanco> itens = getItens();

        Lista<Entidade> objetos = new Lista<Entidade>();

        for (ItemDoBanco item : itens) {
            Entidade obj = item.toEntidade();
            objetos.adicionar(obj);
        }

        return objetos;
    }

}

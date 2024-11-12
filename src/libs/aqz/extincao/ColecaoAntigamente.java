package libs.aqz.extincao;

import libs.aqz.extincao.armazenador_antigo.ArmazenadorEmExtincao;
import libs.armazenador.ParticaoEmExtincao;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.RefLong;

public class ColecaoAntigamente {

    private String mNome;
    private ArmazenadorEmExtincao mArmazenador;
    private ParticaoEmExtincao mSequencias;
    private ParticaoEmExtincao mColecao;

    public ColecaoAntigamente(String eNome, ArmazenadorEmExtincao eArmazenador, ParticaoEmExtincao eSequencias, ParticaoEmExtincao eColecao) {
        mNome = eNome;
        mArmazenador = eArmazenador;
        mSequencias = eSequencias;
        mColecao = eColecao;

        Sequenciador.organizar_sequencial(mSequencias, mNome);

    }

    public void zerarSequencial() {
        Sequenciador.zerar_sequencial(mSequencias, mNome);
    }

    public boolean adicionar(Entidade objeto) {

        //fmt.print("AQZ STATUS ADD p1");

        int chave = Sequenciador.aumentar_sequencial(mSequencias, mNome);
        objeto.at("ID",chave);
        objeto.tornar_primeiro("ID");
        long endereco = mColecao.adicionar(objeto);

      //  fmt.print("AQZ STATUS ADD p2 - {} :: {}",chave,endereco);

        mColecao.set(chave, endereco);

      //  fmt.print("AQZ STATUS ADD p3");

        return false;
    }


    public void remover_por_chave(int eID) {

        for (ItemDoBancoEmExtincao item : mColecao.getItens()) {
            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (objeto.atInt("ID") == eID) {

                mColecao.set(objeto.atInt("ID"), 0);

                mColecao.remover(item);
                break;
            }
        }

    }

    public void remover(ItemDoBancoEmExtincao item) {

        Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTexto());
        mColecao.set(objeto.atInt("ID"), 0);

        mColecao.remover(item);
    }

    public void atualizar_por_chave(int eID, Entidade objeto_novo) {

        for (ItemDoBancoEmExtincao item : mColecao.getItens()) {
            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (objeto.atInt("ID") == eID) {
                objeto_novo.at("ID",eID);
                item.atualizar(objeto_novo.toString());
                break;
            }
        }

    }

    public void limpar() {
        mColecao.limpar();
    }

    public Lista<ItemDoBancoEmExtincao> getItens() {
        return mColecao.getItens();
    }

    public long getItensContagem() {
        return mColecao.getItensContagem();
    }


    public void primeiro_campo(String ePrimeiro) {

        for (ItemDoBancoEmExtincao item : getItens()) {

            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTexto());

            objeto.tornar_primeiro(ePrimeiro);

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

        for (ItemDoBancoEmExtincao item : getItens()) {

            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTexto());
            int indice = objeto.atInt("ID");

            if (indice > indice_maior) {
                indice_maior = indice;
            }

        }


        for (int chave = 0; chave < (indice_maior + 1); chave++) {
            mArquivadorIndice.set_u8((byte) 0);
            mArquivadorIndice.set_u64(0);
        }

        for (ItemDoBancoEmExtincao item : getItens()) {

            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTexto());
            int indice = objeto.atInt("ID");

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

    public Opcional<ItemDoBancoEmExtincao> getIndexado(long procurar_indice) {

        Opcional<ItemDoBancoEmExtincao> ret = new Opcional<ItemDoBancoEmExtincao>();

        Opcional<RefLong> proc2t = get(procurar_indice);

        if (proc2t.temValor()) {

            ItemDoBancoEmExtincao item = mArmazenador.getItemDireto(proc2t.get().get());

            if (item.existe()) {
                ret.set(item);
            }


        }

        return ret;
    }

    public Lista<Entidade> getObjetos() {

        Lista<ItemDoBancoEmExtincao> itens = getItens();

        Lista<Entidade> objetos = new Lista<Entidade>();

        for (ItemDoBancoEmExtincao item : itens) {
            Entidade obj = ENTT.PARSER_ENTIDADE(item.lerTexto());
            objetos.adicionar(obj);
        }

        return objetos;
    }

}

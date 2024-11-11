package libs.aqz.colecao;

import libs.aqz.utils.AZSequenciador;
import libs.aqz.utils.ItemDoBancoUTF8;
import libs.armazenador.Armazenador;
import libs.armazenador.ParticaoMestre;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.RefLong;

public class ColecaoUTF8 {

    private String mNome;
    private Armazenador mArmazenador;
    private ParticaoMestre mSequencias;
    private ParticaoMestre mColecao;

    public ColecaoUTF8(String eNome, Armazenador eArmazenador, ParticaoMestre eSequencias, ParticaoMestre eColecao) {
        mNome = eNome;
        mArmazenador = eArmazenador;
        mSequencias = eSequencias;
        mColecao = eColecao;

        AZSequenciador.organizar_sequencial(mSequencias, mNome);

    }

    public String getNome(){return mNome;}

    public void zerarSequencial() {
        AZSequenciador.zerar_sequencial(mSequencias, mNome);
    }

    public boolean adicionar(Entidade objeto) {

        //fmt.print("AQZ STATUS ADD p1");

        int chave = AZSequenciador.aumentar_sequencial(mSequencias, mNome);
        objeto.at("ID",chave);
        objeto.tornar_primeiro("ID");
        long endereco = mColecao.adicionarUTF8(objeto);

        //  fmt.print("AQZ STATUS ADD p2 - {} :: {}",chave,endereco);

        mColecao.set(chave, endereco);

        //  fmt.print("AQZ STATUS ADD p3");

        return false;
    }


    public void remover_por_chave(int eID) {

        for (ItemDoBancoUTF8 item : mColecao.getItensUTF8()) {
            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (objeto.atInt("ID") == eID) {

                mColecao.set(objeto.atInt("ID"), 0);

                mColecao.remover(item);
                break;
            }
        }

    }

    public void remover(ItemDoBancoUTF8 item) {

        Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
        mColecao.set(objeto.atInt("ID"), 0);

        mColecao.remover(item);
    }

    public void atualizar_por_chave(int eID, Entidade objeto_novo) {

        for (ItemDoBancoUTF8 item : mColecao.getItensUTF8()) {
            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (objeto.atInt("ID") == eID) {
                objeto_novo.at("ID",eID);
                item.atualizarUTF8(objeto_novo.toString());
                break;
            }
        }

    }

    public void limpar() {
        mColecao.limpar();
    }

    public Lista<ItemDoBancoUTF8> getItens() {
        return mColecao.getItensUTF8();
    }

    public long getItensContagem() {
        return mColecao.getItensContagem();
    }


    public void primeiro_campo(String ePrimeiro) {

        for (ItemDoBancoUTF8 item : getItens()) {

            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            objeto.tornar_primeiro(ePrimeiro);

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

        for (ItemDoBancoUTF8 item : getItens()) {

            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            int indice = objeto.atInt("ID");

            if (indice > indice_maior) {
                indice_maior = indice;
            }

        }


        for (int chave = 0; chave < (indice_maior + 1); chave++) {
            mArquivadorIndice.set_u8((byte) 0);
            mArquivadorIndice.set_u64(0);
        }

        for (ItemDoBancoUTF8 item : getItens()) {

            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
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

    public Lista<Entidade> getObjetos() {

        Lista<ItemDoBancoUTF8> itens = getItens();

        Lista<Entidade> objetos = new Lista<Entidade>();

        for (ItemDoBancoUTF8 item : itens) {
            Entidade obj = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            objetos.adicionar(obj);
        }

        return objetos;
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


    public Opcional<Long> adicionarUTF8ComIDInterno(Entidade objeto) {

        int chave = AZSequenciador.aumentar_sequencial(mSequencias, mNome);
        objeto.at("@ID", (chave));
        long endereco = mColecao.adicionarUTF8(ENTT.TO_DOCUMENTO(objeto));

        mColecao.set(chave, endereco);

        return Opcional.OK(endereco);
    }


    public Opcional<ItemDoBancoUTF8> obter_opcional(String att_nome, String att_valor) {

        for (ItemDoBancoUTF8 item : getItens()) {
            Entidade e_item = item.toEntidadeUTF8();

            if (e_item.is(att_nome,att_valor)) {
                return Opcional.OK(item);
            }

        }

        return Opcional.CANCEL();
    }
}

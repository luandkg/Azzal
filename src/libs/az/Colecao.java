package libs.az;

import libs.arquivos.binario.Arquivador;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.RefLong;
import libs.armazenador.Banco;
import libs.armazenador.ItemDoBanco;
import libs.armazenador.Armazenador;

import java.util.ArrayList;

public class Colecao {

    private String mNome;
    private Armazenador mArmazenador;
    private Banco mSequencias;
    private Banco mColecao;

    public Colecao(String eNome, Armazenador eArmazenador, Banco eSequencias, Banco eColecao) {
        mNome = eNome;
        mArmazenador = eArmazenador;
        mSequencias = eSequencias;
        mColecao = eColecao;

        AZSequenciador.organizar_sequencial(mSequencias, mNome);

    }

    public void zerarSequencial(){
        AZSequenciador.zerar_sequencial(mSequencias, mNome);
    }

    public boolean adicionar(DKGObjeto objeto) {

        int chave = AZSequenciador.aumentar_sequencial(mSequencias, mNome);
        objeto.identifique("ID").setInteiro(chave);
        objeto.rearranjar("ID",0);
        long endereco = mColecao.adicionar(objeto.toDocumento());

        mColecao.set(chave, endereco);

        return false;
    }


    public void remover_por_chave(int eID) {

        for (ItemDoBanco item : mColecao.getItens()) {
            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (objeto.identifique("ID").getInteiro(0) == eID) {

                mColecao.set(objeto.identifique("ID").getInteiro(0), 0);

                mColecao.remover(item);
                break;
            }
        }

    }

    public void remover(ItemDoBanco item) {

        DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
        mColecao.set(objeto.identifique("ID").getInteiro(0), 0);

        mColecao.remover(item);
    }

    public void atualizar_por_chave(int eID, DKGObjeto objeto_novo) {

        for (ItemDoBanco item : mColecao.getItens()) {
            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (objeto.identifique("ID").getInteiro(0) == eID) {
                objeto_novo.identifique("ID").setInteiro(eID);
                item.atualizar(objeto_novo.toString());
                break;
            }
        }

    }

    public void limpar() {
        mColecao.limpar();
    }

    public Lista<ItemDoBanco> getItens() {
        return mColecao.getItens();
    }


    public void primeiro_campo(String ePrimeiro){

        for(ItemDoBanco item: getItens()){

            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());

            objeto.rearranjar(ePrimeiro,0);

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

            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            int indice = objeto.identifique("ID").getInteiro(0);

            if (indice > indice_maior) {
                indice_maior = indice;
            }

        }


        for (int chave = 0; chave < (indice_maior + 1); chave++) {
            mArquivadorIndice.set_u8((byte) 0);
            mArquivadorIndice.set_u64(0);
        }

        for (ItemDoBanco item : getItens()) {

            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            int indice = objeto.identifique("ID").getInteiro(0);

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

    public Lista<DKGObjeto> getObjetos(){

        Lista<ItemDoBanco> itens = getItens();

        Lista<DKGObjeto> objetos = new Lista<DKGObjeto>();

        for (ItemDoBanco item : itens) {
            DKGObjeto obj = DKG.PARSER_TO_OBJETO(item.lerTexto());
            objetos.adicionar(obj);
        }

        return objetos;
    }

}

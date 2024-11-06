package libs.armazenador;


import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;


public class Pagina {

    private Arquivador mArquivador;
    private Banco mBanco;
    private long mPonteiro;

    public Pagina(Arquivador eArquivador, Banco eBanco, long ePonteiro) {
        mArquivador = eArquivador;
        mBanco = eBanco;
        mPonteiro = ePonteiro;
    }

    public long getPonteiro() {
        return mPonteiro;
    }

    public int contagemTodos() {

        int contando = 0;

        //  mArquivador.setPonteiro(mPonteiro);
        //  int pagina_status = mArquivador.readByteAsInt();
        // for (int item = 0; item < Momentum.MAX_ITENS_POR_PAGINA; item++) {
        //     int pag_status =  mArquivador.readByteAsInt();
        //     long pag_local = mArquivador.readLong();
        //     contando += 1;
        //  }

        contando = Armazenador.MAX_ITENS_POR_PAGINA;
        return contando;

    }

    public int contagemZerados() {

        int contando = 0;

        mArquivador.setPonteiro(mPonteiro);

        int pagina_status = mArquivador.get_u8();

        for (int item = 0; item < Armazenador.MAX_ITENS_POR_PAGINA; item++) {
            int pag_status = mArquivador.get_u8();
            long pag_local = mArquivador.get_u64();

            if (pag_status != 0) {
                contando += 1;
            }

        }

        return contando;

    }


    public int contagemUsados() {

        int contando = 0;

        mArquivador.setPonteiro(mPonteiro);

        int pagina_status = mArquivador.get_u8();

        for (int item = 0; item < Armazenador.MAX_ITENS_POR_PAGINA; item++) {
            int pag_status = mArquivador.get_u8();
            long pag_local = mArquivador.get_u64();

            if (pag_status == Armazenador.ITEM_ALOCADO_OCUPADO) {
                contando += 1;
            }

        }

        return contando;

    }

    public int contagemAlocados() {

        int contando = 0;

        mArquivador.setPonteiro(mPonteiro);

        int pagina_status = mArquivador.get_u8();

        for (int item = 0; item < Armazenador.MAX_ITENS_POR_PAGINA; item++) {
            int pag_status = mArquivador.get_u8();
            long pag_local = mArquivador.get_u64();

            if (pag_status == Armazenador.ITEM_ALOCADO_OCUPADO || pag_status == Armazenador.ITEM_ALOCADO_DISPONIVEL) {
                contando += 1;
            }

        }

        return contando;

    }

    public boolean temDisponivel() {
        return contagemUsados() < contagemTodos();
    }

    public long adicionar(String conteudo) {

        long endereco = 0;

        mArquivador.setPonteiro(mPonteiro);

        int pagina_status = mArquivador.get_u8();

        for (int item = 0; item < Armazenador.MAX_ITENS_POR_PAGINA; item++) {

            long ponteiro_item = mArquivador.getPonteiro();

            int item_status = mArquivador.get_u8();

            long ponteiro_guardar = mArquivador.getPonteiro();

            long ponteiro_item_dados = mArquivador.get_u64();

            if (item_status == Armazenador.ITEM_NAO_ALOCADO) {

                ItemGuardar.guardar_em_item_nao_alocado(mArquivador, ponteiro_item, ponteiro_guardar, conteudo);

                endereco = ponteiro_item;

                break;
            } else if (item_status == Armazenador.ITEM_ALOCADO_DISPONIVEL) {

                ItemGuardar.guardar_em_item_ja_alocado(mArquivador, ponteiro_item, ponteiro_item_dados, conteudo);

                endereco = ponteiro_item;

                break;
            } else if (item_status == Armazenador.ITEM_ALOCADO_OCUPADO) {

            } else {
                if (Armazenador.IS_DEBUG) {
                    System.out.println("!INFO -- REAPROVEITAR ITEM ALOCADO COM PROBLEMA :: " + ponteiro_item + " Status = " + item_status);
                }

            }

        }

        return endereco;
    }

    public int libertar_item(int item_livre) {

        int contando = 0;

        mArquivador.setPonteiro(mPonteiro);

        int pagina_status = mArquivador.get_u8();

        for (int item = 0; item < Armazenador.MAX_ITENS_POR_PAGINA; item++) {

            long ponteiro_antes = mArquivador.getPonteiro();

            int pag_status = mArquivador.get_u8();
            long pag_local = mArquivador.get_u64();

            if (pag_local == item_livre) {
                mArquivador.setPonteiro(ponteiro_antes);
                mArquivador.set_u8((byte) Armazenador.ITEM_ALOCADO_DISPONIVEL);
                break;
            }

        }

        return contando;

    }

    public Lista<ItemDoBanco> getItens() {

        Lista<ItemDoBanco> itens = new Lista<ItemDoBanco>();

        mArquivador.setPonteiro(mPonteiro);

        int pagina_status = mArquivador.get_u8();

        for (int item = 0; item < Armazenador.MAX_ITENS_POR_PAGINA; item++) {

            long item_ponteiro = mArquivador.getPonteiro();

            int item_status = mArquivador.get_u8();
            long pag_local = mArquivador.get_u64();

            if (item_status == Armazenador.ITEM_ALOCADO_OCUPADO) {
                itens.adicionar(new ItemDoBanco(mArquivador, mBanco, item_ponteiro, pag_local));
            }

        }

        return itens;

    }

    public Lista<ItemDoBancoUTF8> getItensUTF8() {

        Lista<ItemDoBancoUTF8> itens = new Lista<ItemDoBancoUTF8>();

        mArquivador.setPonteiro(mPonteiro);

        int pagina_status = mArquivador.get_u8();

        for (int item = 0; item < Armazenador.MAX_ITENS_POR_PAGINA; item++) {

            long item_ponteiro = mArquivador.getPonteiro();

            int item_status = mArquivador.get_u8();
            long pag_local = mArquivador.get_u64();

            if (item_status == Armazenador.ITEM_ALOCADO_OCUPADO) {
                itens.adicionar(new ItemDoBancoUTF8(mArquivador, mBanco, item_ponteiro, pag_local));
            }

        }

        return itens;

    }


    // FUNCOES PARA UTF-8

    public long adicionarUTF8(String conteudo) {

        long endereco = 0;

        mArquivador.setPonteiro(mPonteiro);

        int pagina_status = mArquivador.get_u8();

        for (int item = 0; item < Armazenador.MAX_ITENS_POR_PAGINA; item++) {

            long ponteiro_item = mArquivador.getPonteiro();

            int item_status = mArquivador.get_u8();

            long ponteiro_guardar = mArquivador.getPonteiro();

            long ponteiro_item_dados = mArquivador.get_u64();

            if (item_status == Armazenador.ITEM_NAO_ALOCADO) {

                ItemGuardar.guardar_em_item_nao_alocado_utf8(mArquivador, ponteiro_item, ponteiro_guardar, conteudo);

                endereco = ponteiro_item;

                break;
            } else if (item_status == Armazenador.ITEM_ALOCADO_DISPONIVEL) {

                ItemGuardar.guardar_em_item_ja_alocado_utf8(mArquivador, ponteiro_item, ponteiro_item_dados, conteudo);

                endereco = ponteiro_item;

                break;
            } else if (item_status == Armazenador.ITEM_ALOCADO_OCUPADO) {

            } else {
                if (Armazenador.IS_DEBUG) {
                    System.out.println("!INFO -- REAPROVEITAR ITEM ALOCADO COM PROBLEMA :: " + ponteiro_item + " Status = " + item_status);
                }

            }

        }

        return endereco;
    }



    public Lista<Long> getAlocados() {

        int contando = 0;
        Lista<Long> alocados = new Lista<Long>();

        mArquivador.setPonteiro(mPonteiro);

        int pagina_status = mArquivador.get_u8();

        for (int item = 0; item < Armazenador.MAX_ITENS_POR_PAGINA; item++) {
            int pag_status = mArquivador.get_u8();
            long pag_local = mArquivador.get_u64();

            if (pag_status == Armazenador.ITEM_ALOCADO_OCUPADO || pag_status == Armazenador.ITEM_ALOCADO_DISPONIVEL) {
                contando += 1;
                alocados.adicionar(pag_local);
            }

        }

        return alocados;

    }

}

package libs.armazenador;


import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;

import java.io.File;

public class Armazenador {

    private String mArquivo;
    private Arquivador mArquivador;
    private Lista<Banco> mBancos;

    public static final int MAX_BANCOS = 255;
    public static final int MAX_CAPITULOS = 1024;
    public static final int MAX_PAGINAS = 1024;
    public static final int MAX_ITENS_POR_PAGINA = 4 * 1024;
    public static final int MAX_ITENS_DO_INDICE = 1024;


    //  public static final int MAX_BANCOS = 255;
    //  public static final int MAX_CAPITULOS = 6;
    //  public static final int MAX_PAGINAS = 3;
    //   public static final int MAX_ITENS_POR_PAGINA = 5;

    public static final int MARCADOR_SUMARIO = 120;
    public static final int MARCADOR_CACHE = 150;

    public static final int MARCADOR_CAPITULO = 160;

    public static final int MARCADOR_PAGINA = 180;

    public static final int MARCADOR_INDICE = 200;

    public static final int ITEM_NAO_ALOCADO = 0;
    public static final int ITEM_ALOCADO_DISPONIVEL = 2;
    public static final int ITEM_ALOCADO_OCUPADO = 255;

    public static final int TAMANHO_ITEM = 10 * 1024;
    public static final int MAX_ITENS_DO_CACHE = 1024;

    public static final boolean IS_DEBUG = false;

    public Armazenador(String eArquivo) {
        mArquivo = eArquivo;

        mArquivador = new Arquivador(mArquivo);
        mArquivador.setPonteiro(0);

        mBancos = new Lista<Banco>();
    }

    public Lista<Banco> getBancos() {
        if (mBancos.getQuantidade() == 0) {
            organizar_bancos();
        }
        return mBancos;
    }

    public Banco getBanco(String eNome) {
        for (Banco b : getBancos()) {
            if (b.getNome().contentEquals(eNome)) {
                return b;
            }
        }
        return null;
    }


    public boolean banco_existe(String eNome) {
        boolean ret = false;
        for (Banco b : getBancos()) {
            if (b.getNome().contentEquals(eNome)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public void banco_criar(String eNome) {
        if (!banco_existe(eNome)) {
            interno_banco_criar(eNome);
            mBancos.limpar();
        }
    }

    public void banco_remover(String eNome) {
        for (Banco b : getBancos()) {
            if (b.getNome().contentEquals(eNome)) {

                b.limpar();

                mArquivador.setPonteiro(b.getLocalBanco());
                mArquivador.set_u8((byte) 2);

                mBancos.limpar();
                break;
            }
        }
    }


    public ItemDoBanco getItemDireto(long ePointeiro) {

        mArquivador.setPonteiro(ePointeiro);
        long item_ponteiro = mArquivador.getPonteiro();

        int item_status = mArquivador.get_u8();
        long dados_ponteiro = mArquivador.get_u64();

        return new ItemDoBanco(mArquivador, ePointeiro, dados_ponteiro);
    }

    public void fechar() {
        mArquivador.encerrar();
    }


    // ----------------- INTERNAMENTE -----------------


    private void organizar_bancos() {
        mBancos.limpar();

        mArquivador.setPonteiro(0);

        int b1 = mArquivador.get_u8();
        int b2 = mArquivador.get_u8();

        int v1 = mArquivador.get_u8();
        int v2 = mArquivador.get_u8();


        //   System.out.println("Inicio -->> " + b1 + "." + b2);
        ///     System.out.println("Versao -->> " + v1 + "." + v2);


        for (int banco = 0; banco < MAX_BANCOS; banco++) {

            long ponteiro = mArquivador.getPonteiro();

            int st = mArquivador.get_u8();
            mArquivador.setPonteiro(mArquivador.getPonteiro() + 1024);

            long local_itens = mArquivador.get_u64();
            long local_cache = mArquivador.get_u64();
            long local_corrente = mArquivador.get_u64();
            long local_indice = mArquivador.get_u64();


            if (st == 255) {
                //  System.out.println("\t - Banco " + banco + " -->> " + st + " --->> " + ponteiro + " :: { Local Itens = " + local_itens + " , Local Cache = " + local_cache + " , Local Indice = " + local_indice + " }");
                mBancos.adicionar(new Banco(this, mArquivador, banco, ponteiro, local_itens, local_cache, local_corrente, local_indice));
            }

        }


    }

    private void interno_banco_criar(String banco_nome) {

        if (banco_nome.length() > 0 && banco_nome.length() < 255) {

        } else {
            return;
        }

        mArquivador.setPonteiro(0);

        int b1 = mArquivador.get_u8();
        int b2 = mArquivador.get_u8();

        int v1 = mArquivador.get_u8();
        int v2 = mArquivador.get_u8();


        long ponteiro_final_arquivo = mArquivador.getLength();


        for (int banco = 0; banco < Armazenador.MAX_BANCOS; banco++) {

            long ponteiro_banco = mArquivador.getPonteiro();

            int st = mArquivador.get_u8();

            if (st == 0) {
                mArquivador.setPonteiro(ponteiro_banco);
                mArquivador.set_u8((byte) 255);
                mArquivador.set_u8_array(TX.toListBytes(banco_nome));


                mArquivador.setPonteiro(ponteiro_final_arquivo);

                long guardar_capitulos = mArquivador.getPonteiro();

                mArquivador.set_u8((byte) Armazenador.MARCADOR_SUMARIO);
                mArquivador.set_u64((byte) 0);
                mArquivador.set_u64((byte) 0);
                mArquivador.set_u64((byte) 0);

                for (int capitulo = 0; capitulo < Armazenador.MAX_CAPITULOS; capitulo++) {
                    mArquivador.set_u64(0);
                }

                long guardar_cache = mArquivador.getPonteiro();

                mArquivador.set_u8((byte) Armazenador.MARCADOR_CACHE);
                for (int item = 0; item < Armazenador.MAX_ITENS_DO_CACHE; item++) {
                    mArquivador.set_u64(0);
                }

                long guardar_primeiro_capitulo = mArquivador.getPonteiro();

                mArquivador.set_u8((byte) Armazenador.MARCADOR_CAPITULO);
                for (int item = 0; item < Armazenador.MAX_PAGINAS; item++) {
                    mArquivador.set_u64(0);
                }

                long guardar_primeira_pagina_do_primeiro_capitulo = mArquivador.getPonteiro();

                mArquivador.set_u8((byte) Armazenador.MARCADOR_PAGINA);
                for (int item = 0; item < Armazenador.MAX_ITENS_POR_PAGINA; item++) {
                    mArquivador.set_u8((byte) 0);
                    mArquivador.set_u64(0);
                }


                long guardar_local_indice = mArquivador.getPonteiro();

                System.out.println("CRIAR INDICE EM :: " + guardar_local_indice);

                mArquivador.set_u8((byte) Armazenador.MARCADOR_INDICE);
                mArquivador.set_u64(0);
                mArquivador.set_u64(0);

                for (int item = 0; item < Armazenador.MAX_ITENS_DO_INDICE; item++) {
                    mArquivador.set_u64(0);
                }


                mArquivador.setPonteiro(guardar_capitulos + 1);
                mArquivador.set_u64(guardar_primeiro_capitulo);

                mArquivador.setPonteiro(guardar_primeiro_capitulo + 1);
                mArquivador.set_u64(guardar_primeira_pagina_do_primeiro_capitulo);


                mArquivador.setPonteiro(ponteiro_banco + 1 + 1024);
                mArquivador.set_u64(guardar_capitulos);
                mArquivador.set_u64(guardar_cache);
                mArquivador.set_u64(guardar_primeira_pagina_do_primeiro_capitulo);
                mArquivador.set_u64(guardar_local_indice);

                break;
            } else if (st == 2) {

                long antes = mArquivador.getPonteiro();

                mArquivador.setPonteiro(ponteiro_banco);

                mArquivador.set_u8((byte) 255);
                mArquivador.set_u8_array(TX.toListBytes(banco_nome));


                mArquivador.setPonteiro(antes);

                break;
            }


            mArquivador.setPonteiro(mArquivador.getPonteiro() + 1024);

            long local_itens = mArquivador.get_u64();
            long local_cache = mArquivador.get_u64();
            long local_pagina_corrente = mArquivador.get_u64();
            long local_indice = mArquivador.get_u64();

            //    System.out.println("\t - Banco " + banco + " -->> " + st + " :: { Local Itens = " + local_itens + " , Local Cache = " + local_cache + " }");

        }


    }

    public static boolean existe(String arquivo) {
        return new File(arquivo).exists();
    }

    public static void criar(String arquivo) {

        long tamanho_item = 10 * 1024;
        long pagina = (Armazenador.MAX_ITENS_POR_PAGINA) * tamanho_item;
        long capitulo = (Armazenador.MAX_CAPITULOS) * pagina;


        long pagina_itens = Armazenador.MAX_ITENS_POR_PAGINA;
        long capitulo_itens = Armazenador.MAX_PAGINAS * pagina_itens;
        long banco_itens = Armazenador.MAX_CAPITULOS * capitulo_itens;


        Arquivador.remover(arquivo);

        Arquivador arquivar = new Arquivador(arquivo);

        arquivar.set_u8((byte) 100);
        arquivar.set_u8((byte) 150);
        arquivar.set_u8((byte) 1);
        arquivar.set_u8((byte) 0);

        // GUARDAR BANCOS
        for (int banco = 0; banco < Armazenador.MAX_BANCOS; banco++) {

            arquivar.set_u8((byte) 0);
            arquivar.set_u8_em_bloco(1024, (byte) 0);
            arquivar.set_u64(0);
            arquivar.set_u64(0);
            arquivar.set_u64(0);
            arquivar.set_u64(0);

        }


        // CACHE DE ITENS GLOBAIS
        arquivar.set_u8((byte) Armazenador.MARCADOR_CACHE);

        for (int item_cache = 0; item_cache < Armazenador.MAX_ITENS_DO_CACHE; item_cache++) {
            arquivar.set_u64(0L);
        }


        arquivar.encerrar();
    }


    public void remover(ItemDoBanco item) {

        mArquivador.setPonteiro(item.getPonteiro());
        mArquivador.set_u8((byte) 2);


        LocalCache.tentar_guardar_em_cache(mArquivador, getGlobalCache(), item.getPonteiro());
    }

    public long getLocalBancos() {
        long ponteiro_global_cache = 4L;
        return ponteiro_global_cache;
    }


    public long getGlobalCache() {
        long ponteiro_global_cache = 4L + (Armazenador.MAX_BANCOS * (1L + 1024L + 8L + 8L + 8L + 8L));

        return ponteiro_global_cache;
    }

    public long contagem_em_cache() {
        return LocalCache.getItensEmCacheContagem(mArquivador, getGlobalCache());
    }


    public Arquivador getArquivador() {
        return mArquivador;
    }
}

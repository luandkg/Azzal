package libs.materializedview;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.TX;

import java.io.File;
import java.util.ArrayList;

public class HiperMaterializedView10K {

    public final static byte MV_CABECALHO_01 = (byte) 40;
    public final static byte MV_CABECALHO_02 = (byte) 77;
    public final static byte MV_CABECALHO_03 = (byte) 76;

    public final static int CONTEUDO_TAMANHO = 10 * 1024;
    public final static int ITEM_TAMANHO = 1 + 8 + (10 * 1024);

    // UTILITARIOS

    public static void construir(String eArquivo) {

        Arquivador.remover(eArquivo);

        Arquivador arquivador = new Arquivador(eArquivo);
        arquivador.set_u8((byte) MV_CABECALHO_01);
        arquivador.set_u8((byte) MV_CABECALHO_02);
        arquivador.set_u8((byte) MV_CABECALHO_03);


        for (int n = 0; n < 256; n++) {

            arquivador.set_u8((byte) 0);
            arquivador.set_u64(0);
            arquivador.set_u64(0);

        }

        arquivador.encerrar();

    }


    public static boolean isConstruida(String eArquivo) {

        boolean ret = false;

        Arquivador arquivador = new Arquivador(eArquivo);
        arquivador.setPonteiro(0);

        int p1 = arquivador.organizar_to_int(arquivador.get());
        int p2 = arquivador.organizar_to_int(arquivador.get());
        int p3 = arquivador.organizar_to_int(arquivador.get());

        if (p1 == 40 && p2 == 77 && p3 == 76) {
            ret = true;
        }

        arquivador.encerrar();

        return ret;
    }


    public static void organizar_colecao(String eArquivo, int colecao, int quantidade) {

        if (colecao >= 0 && colecao < 256) {

            Arquivador arquivador = new Arquivador(eArquivo);

            arquivador.setPonteiro(0);

            int p1 = arquivador.organizar_to_int(arquivador.get());
            int p2 = arquivador.organizar_to_int(arquivador.get());
            int p3 = arquivador.organizar_to_int(arquivador.get());

            int avancar_em = colecao * (1 + 8 + 8);

            arquivador.setPonteiro(3 + avancar_em);

            int status = arquivador.organizar_to_int(arquivador.get());

            if (status == 0) {

                System.out.println("HiperMaterializedView - Colecao " + colecao + " iniciando construção ...");

                arquivador.setPonteiro(arquivador.getLength());

                long antes = arquivador.getPonteiro();

                for (int n = 0; n < quantidade; n++) {

                    arquivador.set_u8((byte) 0);
                    arquivador.set_u8_em_bloco(8, (byte) 0);
                    arquivador.set_u8_em_bloco(CONTEUDO_TAMANHO, (byte) 0);

                    //    System.out.println("\t\t ### MaterializedView " + n + " ate " + quantidade);
                }

                long depois = arquivador.getPonteiro();

                arquivador.setPonteiro(3 + avancar_em);
                arquivador.set_u8((byte) 1);
                arquivador.set_u64(antes);
                arquivador.set_u64(depois);

                long itens = (depois - antes) / ITEM_TAMANHO;

                System.out.println("HiperMaterializedView - Colecao " + colecao + " construida entre " + antes + " - " + depois + " com " + itens + " itens");

            } else {
                long antes = arquivador.get_u64();
                long depois = arquivador.get_u64();

                long itens = (depois - antes) / ITEM_TAMANHO;

                System.out.println("HiperMaterializedView - Colecao " + colecao + " já construida entre " + antes + " - " + depois + " com " + itens + " itens");
            }


            arquivador.encerrar();

        }


    }

    public static boolean isColecaoConstruida(String eArquivo, int eColecao) {

        boolean construida = false;

        File Arq = new File(eArquivo);
        if (Arq.exists()) {

            Arquivador arquivador = new Arquivador(eArquivo);
            arquivador.setPonteiro(0);

            byte p1 = arquivador.get();
            byte p2 = arquivador.get();
            byte p3 = arquivador.get();

            int avancar_em = eColecao * (1 + 8 + 8);

            arquivador.setPonteiro(3 + avancar_em);

            int status = arquivador.organizar_to_int(arquivador.get());

            if (status == 1) {

                construida = true;
            }


            arquivador.encerrar();


        }


        return construida;

    }


    public static int colecao_contagem(String eArquivo, int eColecao) {

        int quantidade = 0;

        File Arq = new File(eArquivo);
        if (Arq.exists()) {

            Arquivador arquivador = new Arquivador(eArquivo);
            arquivador.setPonteiro(0);

            byte p1 = arquivador.get();
            byte p2 = arquivador.get();
            byte p3 = arquivador.get();

            int avancar_em = eColecao * (1 + 8 + 8);

            arquivador.setPonteiro(3 + avancar_em);

            int status = arquivador.organizar_to_int(arquivador.get());

            if (status == 1) {

                long antes = arquivador.get_u64();
                long depois = arquivador.get_u64();

                quantidade = (int) ((depois - antes) / ITEM_TAMANHO);

            }


            arquivador.encerrar();


        }


        return quantidade;

    }


    // ACESSADORES

    public static void set(String eArquivo, int eColecao, int eItemID, String dados) {

        Arquivador arquivador = new Arquivador(eArquivo);

        int ponteiro_colecao = 3 + (eColecao * (1 + 8 + 8));

        arquivador.setPonteiro(ponteiro_colecao);

        int status = arquivador.organizar_to_int(arquivador.get());

        if (status == 1) {

            long antes = arquivador.get_u64();
            long depois = arquivador.get_u64();

            long ponteiro = antes + (eItemID * ITEM_TAMANHO);

            if (ponteiro < depois) {

                arquivador.setPonteiro(ponteiro);
                arquivador.set_u8((byte) 1);

                ArrayList<Byte> bytes = TX.toListBytes(dados);

                arquivador.set_u64((long) bytes.size());

                arquivador.set_u8_array(bytes);

            }

        }


        arquivador.encerrar();

        //System.out.println("-->> MATERIALIZED VIEW SET (" + id + ") :: " + ponteiro + " dados = " + dados.length() + " -->> " + bytes.size());

    }


    public static String get(String eArquivo, int eColecao, int eItemID) {


        int quantidade = 0;
        String dados = "";


        Arquivador arquivador = new Arquivador(eArquivo);

        int ponteiro_colecao = 3 + (eColecao * (1 + 8 + 8));

        arquivador.setPonteiro(ponteiro_colecao);

        int status = arquivador.organizar_to_int(arquivador.get());

        if (status == 1) {

            long antes = arquivador.get_u64();
            long depois = arquivador.get_u64();

            long ponteiro = antes + (eItemID * ITEM_TAMANHO);

            if (ponteiro < depois) {

                arquivador.setPonteiro(ponteiro);

                int status_item = arquivador.organizar_to_int(arquivador.get());

                if (status_item == 1) {

                    long tam = arquivador.get_u64();

                    ArrayList<Byte> bytes = arquivador.get_u8_bloco((int) tam);
                    quantidade = bytes.size();

                    dados = TX.lerDeBytes(bytes);

                }


            }

        }


        arquivador.encerrar();


        //  System.out.println("-->> MATERIALIZED VIEW GET (" + id + ") :: " + ponteiro + " dados = " + quantidade);

        return dados;
    }

}

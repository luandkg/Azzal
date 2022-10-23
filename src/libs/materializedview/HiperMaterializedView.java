package libs.materializedview;

import libs.Arquivos.Binario.Arquivador;
import libs.Arquivos.TX;

import java.io.File;
import java.util.ArrayList;

public class HiperMaterializedView {

    public final static byte MV_CABECALHO_01 = (byte) 40;
    public final static byte MV_CABECALHO_02 = (byte) 77;
    public final static byte MV_CABECALHO_03 = (byte) 76;

    public final static int CONTEUDO_TAMANHO = 100 * 1024;
    public final static int ITEM_TAMANHO = 1 + 8 + (100 * 1024);

    // UTILITARIOS

    public static void construir(String eArquivo) {

        Arquivador.remover(eArquivo);

        Arquivador arquivador = new Arquivador(eArquivo);
        arquivador.writeByte((byte) MV_CABECALHO_01);
        arquivador.writeByte((byte) MV_CABECALHO_02);
        arquivador.writeByte((byte) MV_CABECALHO_03);


        for (int n = 0; n < 256; n++) {

            arquivador.writeByte((byte) 0);
            arquivador.writeLong(0);
            arquivador.writeLong(0);

        }

        arquivador.encerrar();

    }


    public static boolean isConstruida(String eArquivo) {

        boolean ret = false;

        Arquivador arquivador = new Arquivador(eArquivo);
        arquivador.setPonteiro(0);

        int p1 = arquivador.organizar_to_int(arquivador.readByte());
        int p2 = arquivador.organizar_to_int(arquivador.readByte());
        int p3 = arquivador.organizar_to_int(arquivador.readByte());

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

            int p1 = arquivador.organizar_to_int(arquivador.readByte());
            int p2 = arquivador.organizar_to_int(arquivador.readByte());
            int p3 = arquivador.organizar_to_int(arquivador.readByte());

            int avancar_em = colecao * (1 + 8 + 8);

            arquivador.setPonteiro(3 + avancar_em);

            int status = arquivador.organizar_to_int(arquivador.readByte());

            if (status == 0) {

                System.out.println("HiperMaterializedView - Colecao " + colecao + " iniciando construção ...");

                arquivador.setPonteiro(arquivador.getLength());

                long antes = arquivador.getPonteiro();

                for (int n = 0; n < quantidade; n++) {

                    arquivador.writeByte((byte) 0);
                    arquivador.writeByteRepetidos(8, (byte) 0);
                    arquivador.writeByteRepetidos(CONTEUDO_TAMANHO, (byte) 0);

                    //    System.out.println("\t\t ### MaterializedView " + n + " ate " + quantidade);
                }

                long depois = arquivador.getPonteiro();

                arquivador.setPonteiro(3 + avancar_em);
                arquivador.writeByte((byte) 1);
                arquivador.writeLong(antes);
                arquivador.writeLong(depois);

                long itens = (depois - antes) / ITEM_TAMANHO;

                System.out.println("HiperMaterializedView - Colecao " + colecao + " construida entre " + antes + " - " + depois + " com " + itens + " itens");

            } else {
                long antes = arquivador.readLong();
                long depois = arquivador.readLong();

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

            byte p1 = arquivador.readByte();
            byte p2 = arquivador.readByte();
            byte p3 = arquivador.readByte();

            int avancar_em = eColecao * (1 + 8 + 8);

            arquivador.setPonteiro(3 + avancar_em);

            int status = arquivador.organizar_to_int(arquivador.readByte());

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

            byte p1 = arquivador.readByte();
            byte p2 = arquivador.readByte();
            byte p3 = arquivador.readByte();

            int avancar_em = eColecao * (1 + 8 + 8);

            arquivador.setPonteiro(3 + avancar_em);

            int status = arquivador.organizar_to_int(arquivador.readByte());

            if (status == 1) {

                long antes = arquivador.readLong();
                long depois = arquivador.readLong();

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

        int status = arquivador.organizar_to_int(arquivador.readByte());

        if (status == 1) {

            long antes = arquivador.readLong();
            long depois = arquivador.readLong();

            long ponteiro = antes + (eItemID * ITEM_TAMANHO);

            if (ponteiro < depois) {

                arquivador.setPonteiro(ponteiro);
                arquivador.writeByte((byte) 1);

                ArrayList<Byte> bytes = TX.toListBytes(dados);

                arquivador.writeLong((long) bytes.size());

                arquivador.writeByteArray(bytes);

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

        int status = arquivador.organizar_to_int(arquivador.readByte());

        if (status == 1) {

            long antes = arquivador.readLong();
            long depois = arquivador.readLong();

            long ponteiro = antes + (eItemID * ITEM_TAMANHO);

            if (ponteiro < depois) {

                arquivador.setPonteiro(ponteiro);

                int status_item = arquivador.organizar_to_int(arquivador.readByte());

                if (status_item == 1) {

                    long tam = arquivador.readLong();

                    ArrayList<Byte> bytes = arquivador.readBytes((int) tam);
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

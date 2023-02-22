package libs.materializedview;


import libs.arquivos.binario.Arquivador;
import libs.arquivos.TX;

import java.io.File;
import java.util.ArrayList;

public class MaterializedView {

    // ORGANIZADORES

    public final static byte MV_CABECALHO_01 = (byte) 77;
    public final static byte MV_CABECALHO_02 = (byte) 76;

    public final static int CONTEUDO_TAMANHO = 100 * 1024;
    public final static int ITEM_TAMANHO = 1 + 8 + (100 * 1024);

    // UTILITARIOS

    public static void construir(String eArquivo, int quantidade) {

        Arquivador.remover(eArquivo);

        Arquivador arquivador = new Arquivador(eArquivo);
        arquivador.set_u8((byte) MV_CABECALHO_01);
        arquivador.set_u8((byte) MV_CABECALHO_02);


        for (int n = 0; n < quantidade; n++) {

            arquivador.set_u8((byte) 0);
            arquivador.set_u8_em_bloco(8, (byte) 0);
            arquivador.set_u8_em_bloco(CONTEUDO_TAMANHO, (byte) 0);

     //       System.out.println("\t\t ### MaterializedView " + n + " de " + quantidade);
        }


        arquivador.encerrar();


    }

    public static void expandir(String eArquivo, int quantidade) {

        int quantidade_atual = contagem(eArquivo);

        if (quantidade_atual == 0) {
            construir(eArquivo, quantidade);
        } else {

            Arquivador arquivador = new Arquivador(eArquivo);
            arquivador.setPonteiro(arquivador.getLength());


            for (int n = 0; n < quantidade; n++) {

                arquivador.set_u8((byte) 0);
                arquivador.set_u8_em_bloco(8, (byte) 0);
                arquivador.set_u8_em_bloco(CONTEUDO_TAMANHO, (byte) 0);

            //    System.out.println("\t\t ### MaterializedView " + n + " ate " + quantidade);
            }


            arquivador.encerrar();


        }


    }

    public static int contagem(String eArquivo) {

        int quantidade = 0;

        File Arq = new File(eArquivo);
        if (Arq.exists()) {

            Arquivador arquivador = new Arquivador(eArquivo);
            arquivador.setPonteiro(0);

            byte p1 = arquivador.get();
            byte p2 = arquivador.get();


            quantidade = (int) ((arquivador.getLength() - 2) / ITEM_TAMANHO);

            arquivador.encerrar();


        }


        return quantidade;

    }


    // ACESSADORES

    public static void set(String eArquivo, int id, String dados) {

        Arquivador arquivador = new Arquivador(eArquivo);

        long ponteiro = 2 + (id * ITEM_TAMANHO);

        arquivador.setPonteiro(ponteiro);
        arquivador.set_u8((byte) 1);

        ArrayList<Byte> bytes = TX.toListBytes(dados);

        arquivador.set_u64((long) bytes.size());

        arquivador.set_u8_array(bytes);

        arquivador.encerrar();


        //System.out.println("-->> MATERIALIZED VIEW SET (" + id + ") :: " + ponteiro + " dados = " + dados.length() + " -->> " + bytes.size());

    }

    public static String get(String eArquivo, int id) {

        Arquivador arquivador = new Arquivador(eArquivo);

        long ponteiro = 2 + (id * ITEM_TAMANHO);

        arquivador.setPonteiro(ponteiro);

        int b1 = arquivador.organizar_to_int(arquivador.get());

        int quantidade = 0;
        String dados = "";

        if (b1 == 1) {

            long tam = arquivador.get_u64();

            ArrayList<Byte> bytes = arquivador.get_u8_bloco((int) tam);
            quantidade = bytes.size();

            dados = TX.lerDeBytes(bytes);

        }

        arquivador.encerrar();


      //  System.out.println("-->> MATERIALIZED VIEW GET (" + id + ") :: " + ponteiro + " dados = " + quantidade);

        return dados;
    }


    // ASSESSORIO

    public static String getTamanhoFormatado(String eArquivo) {

        Arquivador arquivador = new Arquivador(eArquivo);

        long tam = arquivador.getLength();

        arquivador.encerrar();

        return String.valueOf(tam);
    }

}
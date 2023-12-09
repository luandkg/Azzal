package libs.arquivos;


import libs.arquivos.binario.Arquivador;

import java.io.File;
import java.nio.charset.StandardCharsets;


public class CacheIndexado {

    // IMPLEMENTACAO 2023 12 09

    //  private static final int TAMANHO_ITEM = (1024 * 1024);

    private static final int TAMANHO_ITEM = (500 * 1024);

    public static void limpar(String eArquivo) {
        Arquivador.remover(eArquivo);
    }

    public static boolean existe(String eArquivo) {
        File arquivo = new File(eArquivo);
        return arquivo.exists();
    }

    public static void init(String eArquivo) {

        Arquivador arquivar = new Arquivador(eArquivo);

        long t = arquivar.getLength();

        if (t == 0) {
            arquivar.setPonteiro(0);
            arquivar.set_u8((byte) 100);
            arquivar.set_u8((byte) 150);
        }

        arquivar.encerrar();
    }

    public static void auto_init(String eArquivo) {

        if (!CacheIndexado.existe(eArquivo)) {
            CacheIndexado.init(eArquivo);
        }

    }


    public static int getQuantidade(String eArquivo) {

        int v = 0;

        Arquivador arquivar = new Arquivador(eArquivo);
        long t = arquivar.getLength();
        arquivar.encerrar();

        if (t > 2) {
            t = t - 2;
            v = (int) (t / TAMANHO_ITEM);
        }

        return v;

    }

    public static void criar_slot(String eArquivo) {

        Arquivador arquivar = new Arquivador(eArquivo);
        arquivar.setPonteiro(arquivar.getLength());

        int v = 0;
        while (v < TAMANHO_ITEM) {
            arquivar.set_u8((byte) 0);
            v += 1;
        }

        arquivar.encerrar();

    }


    public static void set(String eArquivo, int indice, String dados) {


        Arquivador arquivar = new Arquivador(eArquivo);
        arquivar.setPonteiro(2L + ((long)indice * (long)TAMANHO_ITEM));

        byte[] bytes = dados.getBytes(StandardCharsets.UTF_8);

        arquivar.set_u32(bytes.length);

        int i = 0;
        int o = bytes.length;

        while (i < o) {
            arquivar.set_u8(bytes[i]);
            i += 1;
        }

        arquivar.encerrar();

    }

    public static int getTamanho(String eArquivo, int indice) {

        Arquivador arquivar = new Arquivador(eArquivo);
        arquivar.setPonteiro(2L + ((long) indice * (long) TAMANHO_ITEM));


        int tamanho = arquivar.get_u32();
        return tamanho;
    }

    public static void setTamanho(String eArquivo, int indice, int tamanho) {


        Arquivador arquivar = new Arquivador(eArquivo);
        arquivar.setPonteiro(2L + ((long) indice * (long) TAMANHO_ITEM));
        arquivar.set_u32(tamanho);
        arquivar.encerrar();

    }

    public static String get(String eArquivo, int indice) {


        Arquivador arquivar = new Arquivador(eArquivo);
        arquivar.setPonteiro(2L + ((long) indice * (long) TAMANHO_ITEM));


        int tamanho = arquivar.get_u32();

        byte[] bytes = new byte[tamanho];
        int i = 0;

        while (i < tamanho) {
            bytes[i] = arquivar.get();
            i += 1;
        }


        arquivar.encerrar();

        return new String(bytes, StandardCharsets.UTF_8);
    }


}

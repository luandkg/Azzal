package libs.arquivos.indexados;

import libs.arquivos.binario.Arquivador;
import libs.luan.FS;


public class CentumIndexado {

    // IMPLEMENTACAO 2024 10 13

    private static final int TAMANHO_ITEM = CacheIndexado.CENTUM;

    public static void limpar(String eArquivo) {
        Arquivador.remover(eArquivo);
    }

    public static boolean existe(String eArquivo) {
        return FS.arquivo_existe(eArquivo);
    }

    public static void init(String eArquivo) {
        CacheIndexadoEstatico.init(CacheIndexado.CENTUM, eArquivo);
    }

    public static void auto_init(String eArquivo) {

        if (!existe(eArquivo)) {
            CacheIndexadoEstatico.init(CacheIndexado.CENTUM, eArquivo);
        }

    }


    public static int getQuantidade(String eArquivo) {
        return CacheIndexadoEstatico.getQuantidade(CacheIndexado.CENTUM, eArquivo);
    }

    public static void criar_slot(String eArquivo) {
        CacheIndexadoEstatico.criar_slot(CacheIndexado.CENTUM, eArquivo);
    }


    public static void set(String eArquivo, int indice, String dados) {
        CacheIndexadoEstatico.set(CacheIndexado.CENTUM, eArquivo, indice, dados);
    }

    public static int getTamanho(String eArquivo, int indice) {
        return CacheIndexadoEstatico.getTamanho(CacheIndexado.CENTUM, eArquivo, indice);
    }

    public static void setTamanho(String eArquivo, int indice, int tamanho) {
        CacheIndexadoEstatico.setTamanho(CacheIndexado.CENTUM, eArquivo, indice, tamanho);
    }

    public static String get(String eArquivo, int indice) {
        return CacheIndexadoEstatico.get(CacheIndexado.CENTUM, eArquivo,indice);
    }


}

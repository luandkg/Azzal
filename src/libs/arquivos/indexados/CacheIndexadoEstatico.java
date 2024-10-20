package libs.arquivos.indexados;

import libs.arquivos.binario.Arquivador;
import libs.luan.FS;


public class CacheIndexadoEstatico {

    // IMPLEMENTACAO 2024 10 13

    private static final int TAMANHO_ITEM =CacheIndexado.CENTUM;

    public static void limpar(String eArquivo) {
        Arquivador.remover(eArquivo);
    }

    public static boolean existe(String eArquivo) {
        return FS.arquivo_existe(eArquivo);
    }

    public static void init(int eTamanhoCache,String eArquivo) {

        CacheIndexado cache = new CacheIndexado(eTamanhoCache, eArquivo);
        cache.abrir();
        cache.fechar();

    }

    public static void auto_init(int eTamanhoCache,String eArquivo) {

        if (!existe(eArquivo)) {
            init(eTamanhoCache,eArquivo);
        }

    }


    public static int getQuantidade(int eTamanhoCache,String eArquivo) {

        CacheIndexado cache = new CacheIndexado(eTamanhoCache, eArquivo);
        cache.abrir();
        int quantidade = cache.getQuantidade();
        cache.fechar();

        return quantidade;

    }

    public static void criar_slot(int eTamanhoCache,String eArquivo) {

        CacheIndexado cache = new CacheIndexado(eTamanhoCache, eArquivo);
        cache.abrir();
        cache.criar_slot();
        cache.fechar();

    }


    public static void set(int eTamanhoCache,String eArquivo, int indice, String dados) {

        CacheIndexado cache = new CacheIndexado(eTamanhoCache, eArquivo);
        cache.abrir();
        cache.set(indice, dados);
        cache.fechar();

    }

    public static int getTamanho(int eTamanhoCache,String eArquivo, int indice) {

        CacheIndexado cache = new CacheIndexado(eTamanhoCache, eArquivo);
        cache.abrir();
        int indice_tamanho = cache.getTamanho(indice);
        cache.fechar();

        return indice_tamanho;
    }

    public static void setTamanho(int eTamanhoCache,String eArquivo, int indice, int tamanho) {

        CacheIndexado cache = new CacheIndexado(eTamanhoCache, eArquivo);
        cache.abrir();
        cache.setTamanho(indice, tamanho);
        cache.fechar();

    }

    public static String get(int eTamanhoCache,String eArquivo, int indice) {

        CacheIndexado cache = new CacheIndexado(eTamanhoCache, eArquivo);
        cache.abrir();
        String dados = cache.get(indice);
        cache.fechar();

        return dados;
    }


}

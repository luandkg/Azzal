package libs.arquivos;

import java.io.File;

public class IO {

    private static final long KB = 1024;
    private static final long MB = 1024*1024;
    private static final long GB = 1024*1024*1024;

    public static void remova_se_existir(String eArquivo){

        if (new File(eArquivo).exists()) {
            new File(eArquivo).delete();
        }

    }

    public static String formatar_tamanho(long tamanho){
      
        String s = tamanho + " bytes";

        if (tamanho>=KB){
            s = (tamanho/KB)+ " Kb";
        }

        if (tamanho>=MB){
            s = (tamanho/MB)+ " Mb";
        }

        if (tamanho>=GB){
            s = (tamanho/GB)+ " Gb";
        }

        return s;
    }
}

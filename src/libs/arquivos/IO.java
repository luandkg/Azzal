package libs.arquivos;

import java.io.File;

public class IO {

    private static final long KB = 1024;
    private static final long MB = 1024 * 1024;
    private static final long GB = 1024 * 1024 * 1024;

    public static void remova_se_existir(String eArquivo) {

        if (new File(eArquivo).exists()) {
            new File(eArquivo).delete();
        }

    }

    public static boolean existe(String eArquivo) {
        return (new File(eArquivo).exists());
    }

    public static String formatar_tamanho(long tamanho) {

        String s = tamanho + " bytes";

        if (tamanho >= KB) {
            s = (tamanho / KB) + " Kb";
        }

        if (tamanho >= MB) {
            s = (tamanho / MB) + " Mb";
        }

        if (tamanho >= GB) {
            s = (tamanho / GB) + " Gb";
        }

        return s;
    }

    public static String formatar_tamanho_precisao_dupla(long t) {

        String ret = "";

        int TAXA_BINARIA = 1024;

        if (t < TAXA_BINARIA) {
            ret = t + " bytes";
        } else {

            long kb = t / TAXA_BINARIA;

            if (kb < TAXA_BINARIA) {
                ret = kb + " Kb";
            } else {

                long mb = kb / TAXA_BINARIA;
                long sobras = kb - (mb * 1024);


                if (mb < TAXA_BINARIA) {
                    ret = mb + " Mb e " + sobras + " Kb";
                } else {

                    long gb = mb / TAXA_BINARIA;

                    if (gb < TAXA_BINARIA) {
                        ret = gb + " Gb";
                    } else {
                        ret = gb + " Gb";
                    }

                }

            }

        }

        return ret;

    }

}

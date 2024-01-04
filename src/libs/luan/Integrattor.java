package libs.luan;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import libs.arquivos.binario.Inteiro;

public class Integrattor {


    public static String GET(String dados) {

        String s_dados = dados ;

        byte[] bytes = s_dados.getBytes(StandardCharsets.UTF_8);

        int i = 0;
        int o = bytes.length;

        String ALFABETO = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int ALFABETO_TAMANHO = ALFABETO.length();


        int e = 0;
        int CHAVE_TAMANHO = 30;
        ArrayList<Integer> chaves = new ArrayList<>();
        for (int c = 0; c < CHAVE_TAMANHO; c++) {
            chaves.add(0);
        }


        while (i < o) {

            int valor = chaves.get(e) + Inteiro.byteToInt(bytes[i]);

            while (valor >= ALFABETO_TAMANHO) {
                valor -= ALFABETO_TAMANHO;
            }

            chaves.set(e, valor);

            e += 1;
            if (e >= CHAVE_TAMANHO) {
                e = 0;
            }
            i += 1;
        }


        String verificado = "";
        for (int c = 0; c < CHAVE_TAMANHO; c++) {
            verificado += String.valueOf(ALFABETO.charAt(chaves.get(c)));
        }

        return verificado ;
    }

    public static String GET_DIFFER(String la, String lb) {

        String ret = "";

        if (la.length() == lb.length()) {
            ret = String.valueOf(la.length()) + " - ";


            int i = 0;
            int o = la.length();

            while (i < o) {
                String ia = String.valueOf(la.charAt(i));
                String ib = String.valueOf(lb.charAt(i));
                if (!ia.contentEquals(ib)) {
                    ret += "{" + String.valueOf(i) + "} = " + ia + "|" + ib + " ";
                }
                i += 1;
            }

        }

        return ret;

    }

    public static String GET_DIFFER_V2(String la, String lb) {

        String ret = "";

        if (la.length() == lb.length()) {

            int difs = 0;
            int muito_diferente = lb.length() / 2;

            int i = 0;
            int o = la.length();

            while (i < o) {
                String ia = String.valueOf(la.charAt(i));
                String ib = String.valueOf(lb.charAt(i));
                if (!ia.contentEquals(ib)) {
                    difs += 1;
                }
                i += 1;
            }

            if (difs >= muito_diferente) {
                ret = "MUITO DIFERENTE - " + difs;
                return ret;
            }

            if (difs > 0) {

                i = 0;
                o = la.length();

                ret = "";

                while (i < o) {
                    String ia = String.valueOf(la.charAt(i));
                    String ib = String.valueOf(lb.charAt(i));
                    if (!ia.contentEquals(ib)) {
                        ret += "{" + String.valueOf(i) + "} = " + ia + "|" + ib + " ";
                    }
                    i += 1;
                }

                return "POUCO DIFERENTE - " + String.valueOf(difs) + " - [ " + ret + "]";
            }

            if (difs == 0) {
                ret = "IDENTICO";
                return ret;
            }


        }

        return "NAO FOI POSSIVEL ANALISAR";

    }


}

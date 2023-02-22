package libs.luan;

import java.util.ArrayList;

public class Strings {

    public static String retirar_espaco_do_comeco(String texto) {

        int i = 0;
        int o = texto.length();

        String ret = "";
        boolean comecou = false;

        while (i < o) {
            String c = String.valueOf(texto.charAt(i));
            if (comecou) {
                ret += c;
            } else {
                if (c.contentEquals(" ") || c.contentEquals("\t") || c.contentEquals("\n")) {

                } else {
                    comecou = true;
                    ret += c;
                }
            }

            i += 1;
        }

        return ret;
    }


    public static ArrayList<String> dividir_linhas(String texto) {
        ArrayList<String> linhas = new ArrayList<String>();

        int i = 0;
        int o = texto.length();

        String linha = "";

        while (i < o) {
            String c = String.valueOf(texto.charAt(i));
            if (c.contentEquals("\n")) {
                linhas.add(linha);
                linha = "";
            } else {
                linha += c;
            }
            i += 1;
        }
        if (linha.length() > 0) {
            linhas.add(linha);
        }
        return linhas;
    }


    public static ArrayList<String> ordenar(ArrayList<String> entradas) {

        int n = entradas.size();
        String temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entradas.get(j - 1).compareTo(entradas.get(j)) > 0) {
                    temp = entradas.get(j - 1);
                    entradas.set(j - 1, entradas.get(j));
                    entradas.set(j, temp);

                }

            }
        }
        return entradas;
    }


}

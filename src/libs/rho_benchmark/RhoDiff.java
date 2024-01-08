package libs.rho_benchmark;

import libs.luan.Strings;
import libs.luan.Texto;

import java.util.ArrayList;

public class RhoDiff {

    public static void diff(String arquivo_1, String arquivo_2) {

        System.out.println("--------------------- RHO DIFF ------------------------");

        System.out.println("\t - Lendo arquivo ALFA !");
        String texto_a = Texto.arquivo_ler(arquivo_1);

        System.out.println("\t - Lendo arquivo BETA !");
        String texto_b = Texto.arquivo_ler(arquivo_2);

        System.out.println("\t - Processando arquivo ALFA !");
        ArrayList<String> alfa = Strings.dividir_linhas(texto_a);

        System.out.println("\t - Processando arquivo BETA !");
        ArrayList<String> beta = Strings.dividir_linhas(texto_b);

        int na = alfa.size();
        int nb = beta.size();

        int i = na;
        if (nb < i) {
            i = nb;
        }

        System.out.println("\t - Comparando ALFA e BETA !");
        System.out.println("");

        for (int linha = 0; linha < i; linha++) {

            String linha_a = alfa.get(linha);
            String linha_b = beta.get(linha);

            if (!linha_a.contentEquals(linha_b)) {
                System.out.println("LINHA - " + linha);
                System.out.println("\t - ALFA : " + linha_a);
                System.out.println("\t - BETA : " + linha_b);
            }

        }

    }


}

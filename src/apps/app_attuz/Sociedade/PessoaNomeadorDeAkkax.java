package apps.app_attuz.Sociedade;

import libs.luan.Strings;
import libs.luan.fmt;

import java.util.ArrayList;
import java.util.Random;

public class PessoaNomeadorDeAkkax {

    public static String get() {

        String primeiro_nome = "";
        String de = "";
        String sobrenomes = "";

        ArrayList<String> silabas = new ArrayList<String>();
        ArrayList<String> terminais = new ArrayList<String>();

        silabas.add("ga");
        silabas.add("ma");
        silabas.add("lo");
        silabas.add("te");
        silabas.add("va");
        silabas.add("la");
        silabas.add("ne");
        silabas.add("xi");
        silabas.add("pa");
        silabas.add("guo");
        silabas.add("gue");

        silabas.add("dou");
        silabas.add("faz");
        silabas.add("rez");
        silabas.add("doc");

        silabas.add("ra");
        silabas.add("gra");
        silabas.add("mox");
        silabas.add("rem");
        silabas.add("zal");
        silabas.add("col");

        terminais.add("maz");
        terminais.add("tom");
        terminais.add("sol");
        terminais.add("dim");
        terminais.add("mesh");
        terminais.add("paz");

        Random sorte = new Random();

        int quantidade_de_silabas = 2 + sorte.nextInt(3);
        for (int s = 0; s < quantidade_de_silabas; s++) {
            if (s == (quantidade_de_silabas - 1)) {
                primeiro_nome += silabas.get(sorte.nextInt(silabas.size()));
            } else {
                primeiro_nome += terminais.get(sorte.nextInt(terminais.size()));
            }
        }

        if (sorte.nextInt(100) >= 50) {
            de = " de ";
        } else {
            de = " ";
        }

        int quantidade_de_sobrenomes = 1 + sorte.nextInt(2);
        for (int sn = 0; sn < quantidade_de_sobrenomes; sn++) {

            String sobrenome = "";
            quantidade_de_silabas = 2 + sorte.nextInt(3);
            for (int s = 0; s < quantidade_de_silabas; s++) {
                sobrenome += silabas.get(sorte.nextInt(silabas.size()));
            }


            sobrenomes += toCapital(sobrenome) + " ";
        }


        return toCapital(primeiro_nome) + de + sobrenomes.trim();
    }

    public static String toCapital(String a) {
        return a.substring(0, 1).toUpperCase() + a.substring(1, a.length()).toLowerCase();
    }


    public static String getSimples() {

        String primeiro_nome = "";
        ArrayList<String> silabas = new ArrayList<String>();
        ArrayList<String> terminais = new ArrayList<String>();

        silabas.add("ga");
        silabas.add("ma");
        silabas.add("lo");
        silabas.add("te");
        silabas.add("va");
        silabas.add("la");
        silabas.add("ne");
        silabas.add("xi");
        silabas.add("pa");
        silabas.add("guo");
        silabas.add("gue");

        silabas.add("dou");
        silabas.add("faz");
        silabas.add("rez");
        silabas.add("doc");

        silabas.add("ra");
        silabas.add("gra");
        silabas.add("mox");
        silabas.add("rem");
        silabas.add("zal");
        silabas.add("col");

        terminais.add("maz");
        terminais.add("tom");
        terminais.add("sol");
        terminais.add("dim");
        terminais.add("mesh");
        terminais.add("paz");

        Random sorte = new Random();

        int quantidade_de_silabas = 2 + sorte.nextInt(3);
        for (int s = 0; s < quantidade_de_silabas; s++) {
            if (s == (quantidade_de_silabas - 1)) {
                primeiro_nome += silabas.get(sorte.nextInt(silabas.size()));
            } else {
                primeiro_nome += terminais.get(sorte.nextInt(terminais.size()));
            }
        }

        return primeiro_nome;
    }


    public static String getSimplesMenor() {

        String primeiro_nome = "";
        ArrayList<String> silabas = new ArrayList<String>();
        ArrayList<String> terminais = new ArrayList<String>();

        silabas.add("ga");
        silabas.add("ma");
        silabas.add("lo");
        silabas.add("te");
        silabas.add("va");
        silabas.add("la");
        silabas.add("ne");
        silabas.add("xi");
        silabas.add("pa");
        silabas.add("guo");
        silabas.add("gue");

        silabas.add("dou");
        silabas.add("faz");
        silabas.add("rez");
        silabas.add("doc");

        silabas.add("ra");
        silabas.add("gra");
        silabas.add("mox");
        silabas.add("rem");
        silabas.add("zal");
        silabas.add("col");

        terminais.add("maz");
        terminais.add("tom");
        terminais.add("sol");
        terminais.add("dim");
        terminais.add("mesh");
        terminais.add("paz");

        Random sorte = new Random();

        int quantidade_de_silabas = 2 + sorte.nextInt(2);
        for (int s = 0; s < quantidade_de_silabas; s++) {
            if (s == (quantidade_de_silabas - 1)) {
                primeiro_nome += silabas.get(sorte.nextInt(silabas.size()));
            } else {
                primeiro_nome += terminais.get(sorte.nextInt(terminais.size()));
            }
        }

        return primeiro_nome;
    }

    public static void VISUALIZAR_AMOSTRA_PEQUENA(){


        fmt.print("----------------- AMOSTRA PEQUENA ------------------------");
        fmt.print();
        for(int i=0;i<30;i++){

            String palavra_a = Strings.Capitalizar(getSimples());
            String palavra_b = Strings.Capitalizar(getSimples());
            String palavra_c = Strings.Capitalizar(getSimples());

            fmt.print("{dir20} {dir20} {}",palavra_a,palavra_b,palavra_c);

        }
        fmt.print();
        fmt.print("----------------- ---------------- ------------------------");


    }



}

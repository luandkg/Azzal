package libs.luan;

import java.util.ArrayList;
import java.util.Random;

public class Aleatorio {

    public static int aleatorio(int maximo) {

        if (maximo < 1) {
            maximo = 2;
        }
        Random sorte = new Random();
        int ret = sorte.nextInt(maximo);

        return ret;
    }




    public static int aleatorio_entre(int minimo, int maximo) {

        int delta = maximo - minimo;
        Random sorte = new Random();
        int ret = minimo + sorte.nextInt(delta);

        return ret;
    }


    public static int aleatorio_entre_positivo_ou_negativo(int minimo, int maximo) {

        int delta = maximo - minimo;
        Random sorte = new Random();
        int ret = minimo + sorte.nextInt(delta);

        if(aleatorio_entre(0,100)<50){
            ret=ret*(-1);
        }

        return ret;
    }


    public static String aleatorio_numero_real(int minimo,int maximo){
        return Aleatorio.aleatorio_entre(minimo, maximo) + "." + Aleatorio.aleatorio_entre(0, 100);
    }

    public static String aleatorio_escolha(String opcao_1, String opcao_2) {

        Random sorte = new Random();
        int sorteado = sorte.nextInt(100);

        String ret = opcao_1;

        if (sorteado >= 50) {
            ret = opcao_2;
        }

        return ret;
    }

    public static String aleatorio_escolha(String opcao_1, String opcao_2, String opcao_3) {

        Random sorte = new Random();
        int sorteado = sorte.nextInt(100);

        String ret = opcao_1;

        if (sorteado >= 33) {
            ret = opcao_2;
        }

        if (sorteado >= 66) {
            ret = opcao_3;
        }

        return ret;
    }

    public static int aleatorio_a_partir(int comecar, int ate) {

        Random sorte = new Random();
        int sorteado = comecar + sorte.nextInt(ate);

        return sorteado;
    }


    public static String aleatorio_desses(String desses) {

        int delta = desses.length();
        Random sorte = new Random();
        return String.valueOf(desses.charAt(sorte.nextInt(delta)));
    }

    public static String aleatorio_desses(String desses, int quantidade) {

        int delta = desses.length();
        Random sorte = new Random();

        String seq = "";

        for (int v = 0; v < quantidade; v++) {
            seq += String.valueOf(desses.charAt(sorte.nextInt(delta)));
        }

        return seq;
    }


    public static String escolha_um(ArrayList<String> opcoes) {

        String ret = "";

        Random sorte = new Random();

        if (opcoes.size() == 0) {
            ret = "";
        } else if (opcoes.size() == 1) {
            ret = opcoes.get(0);
        } else {
            int sorteado = sorte.nextInt(opcoes.size());
            ret = opcoes.get(sorteado);
        }


        return ret;
    }

    public static <T> T escolha_um(Lista<T> opcoes) {

        T ret = null;

        Random sorte = new Random();

        if (opcoes.getQuantidade() == 0) {
            ret = null;
        } else if (opcoes.getQuantidade() == 1) {
            ret = opcoes.get(0);
        } else {
            int sorteado = sorte.nextInt(opcoes.getQuantidade());
            ret = opcoes.get(sorteado);
        }


        return ret;
    }

    public static <T> T escolha_um(Vetor<T> opcoes) {

        T ret = null;

        Random sorte = new Random();

        if (opcoes.getCapacidade() == 0) {
            ret = null;
        } else if (opcoes.getCapacidade() == 1) {
            ret = opcoes.get(0);
        } else {
            int sorteado = sorte.nextInt(opcoes.getCapacidade());
            ret = opcoes.get(sorteado);
        }


        return ret;
    }


    public static <T> Lista<T> criarCaixaDeEscolhaUnica(Vetor<T> opcoes) {
        Lista<T> candidatos = new Lista<T>();

        for (T item : opcoes) {
            candidatos.adicionar(item);
        }

        return candidatos;

    }


    public static <T> Lista<T> criarCaixaDeEscolhaUnica(Lista<T> opcoes) {
        Lista<T> candidatos = new Lista<T>();

        for (T item : opcoes) {
            candidatos.adicionar(item);
        }

        return candidatos;

    }


    public static <T> T escolha_um_sem_repetir(Lista<T> candidatos) {


        T ret = null;

        Random sorte = new Random();

        if (candidatos.getQuantidade() == 0) {
            ret = null;
        } else if (candidatos.getQuantidade() == 1) {
            ret = candidatos.get(0);
            candidatos.remover_indice(0);
        } else {
            int sorteado = sorte.nextInt(candidatos.getQuantidade());
            ret = candidatos.get(sorteado);
            candidatos.remover_indice(sorteado);
        }


        return ret;
    }


    public static String aleatorio_com_esses(String conjunto, int tamanho) {

        int delta = conjunto.length();

        Random sorte = new Random();

        String ret = "";
        while (ret.length() < tamanho) {
            ret += conjunto.charAt(sorte.nextInt(delta));
        }

        return ret;
    }

    public static String aleatorio_dos_numero(int tamanho) {
        return Aleatorio.aleatorio_com_esses("0123456789", tamanho);
    }


}

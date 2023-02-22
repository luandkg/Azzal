package libs.luan;

import java.util.ArrayList;
import java.util.Random;

public class Aleatorio {

    public static int aleatorio(int maximo) {

        Random sorte = new Random();
        int ret = sorte.nextInt(maximo);

        return ret;
    }

    public static int alatorio_entre(int minimo, int maximo) {

        int delta = maximo - minimo;
        Random sorte = new Random();
        int ret = minimo + sorte.nextInt(delta);

        return ret;
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

    public static String escolha_um(ArrayList<String> opcoes) {

        String ret = "";

        Random sorte = new Random();

        if (opcoes.size() == 0) {
            ret = "";
        } else if (opcoes.size() == 0) {
            ret = opcoes.get(0);
        } else {
            int sorteado = sorte.nextInt(opcoes.size());
            ret = opcoes.get(sorteado);
        }


        return ret;
    }

}

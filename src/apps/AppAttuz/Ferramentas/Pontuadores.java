package apps.AppAttuz.Ferramentas;

import apps.AppAttuz.Camadas.CadaPonto;
import apps.AppAttuz.Camadas.Massas;
import azzal.Formatos.Ponto;

import java.util.ArrayList;
import java.util.Random;

public class Pontuadores {

    public static int getMaior(Massas tectonica, int TECTONICA_VALOR, Massas massa) {

        final int[] maior = {0};

        tectonica.paraCadaPonto(new CadaPonto() {
            @Override
            public void onPonto(int x, int y) {

                if (tectonica.getValor(x, y) == TECTONICA_VALOR) {
                    int corrente = massa.getValor(x, y);
                    if (corrente > maior[0]) {
                        maior[0] = corrente;
                    }
                }
            }
        });

        return maior[0];
    }

    public static int getMenor(Massas tectonica, int TECTONICA_VALOR, Massas massa) {


        final int[] menor = {Integer.MAX_VALUE};

        tectonica.paraCadaPonto(new CadaPonto() {
            @Override
            public void onPonto(int x, int y) {

                if (tectonica.getValor(x, y) == TECTONICA_VALOR) {
                    int corrente = massa.getValor(x, y);
                    if (corrente < menor[0]) {
                        menor[0] = corrente;
                    }
                }
            }
        });

        return menor[0];
    }

    public void expansores(Massas tectonica, Massas massa, int VALOR_PADRAO, ArrayList<Ponto> pt_montanhas, int v) {

        //  System.out.println(" -->> " + v + " ENTRADA " + pt_montanhas.size());

        AlgoritmosDeMapa am = new AlgoritmosDeMapa();

        ArrayList<Ponto> n2 = am.expandir_em4(pt_montanhas, 10);
        ArrayList<Ponto> n3 = am.expandir_em4(n2, 20);
        ArrayList<Ponto> n4 = am.expandir_em4(n3, 20);
        ArrayList<Ponto> n5 = am.expandir_em4(n4, 20);

        Random eSorte = new Random();

        for (Ponto ePonto : pt_montanhas) {
            if (tectonica.getValor(ePonto.getX(), ePonto.getY()) == VALOR_PADRAO) {
                massa.setValor(ePonto.getX(), ePonto.getY(), (v * 100) + eSorte.nextInt(100));
            }
        }

    }

    public static void marcarPontos(Massas tectonica, Massas massa, int VALOR_PADRAO, ArrayList<Ponto> pontos, int v) {

        Random eSorte = new Random();

        for (Ponto ePonto : pontos) {
            if (tectonica.getValor(ePonto.getX(), ePonto.getY()) == VALOR_PADRAO) {
                massa.setValor(ePonto.getX(), ePonto.getY(), (v * 100) + eSorte.nextInt(100));
            }
        }

    }

}

package AppAttuz.Ferramentas;

import AppAttuz.Camadas.Massas;

public class Variattor {

    private int[] valorInicial;
    private double[] variacoes;
    private boolean[] tem_variacoes;
    private int mLargura;

    public void init(Massas tectonica, int quantidade, Massas alfa, Massas beta) {

        valorInicial = new int[tectonica.getAltura() * tectonica.getLargura()];
        variacoes = new double[tectonica.getAltura() * tectonica.getLargura()];
        tem_variacoes = new boolean[tectonica.getAltura() * tectonica.getLargura()];

        mLargura=tectonica.getLargura();


        for (int y = 0; y < tectonica.getAltura(); y++) {
            for (int x = 0; x < tectonica.getLargura(); x++) {

                if (tectonica.isTerra(x, y)) {

                    int valor_verao = alfa.getValor(x, y);
                    int valor_inverno = beta.getValor(x, y);

                    tem_variacoes[(y * tectonica.getLargura()) + x] = false;
                    valorInicial[(y * tectonica.getLargura()) + x] = valor_verao;


                    if (valor_verao != valor_inverno) {

                        double dif = (double) (valor_verao - valor_inverno) / (double) quantidade;

                        tem_variacoes[(y * tectonica.getLargura()) + x] = true;
                        variacoes[(y * tectonica.getLargura()) + x] = dif;

                    }


                }


            }
        }


    }

    public boolean temVariacao(int x, int y) {
        return tem_variacoes[(y * mLargura) + x];
    }

    public double getVariacao(int x,int y){
        return variacoes[(y * mLargura) + x];
    }
}

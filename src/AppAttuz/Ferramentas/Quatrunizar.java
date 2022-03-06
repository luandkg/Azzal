package AppAttuz.Ferramentas;

import AppAttuz.Camadas.Massas;

public class Quatrunizar {

    public static void emQuatrum(Massas tectonica, Massas massa) {


        for (int y = 0; y < tectonica.getAltura(); y++) {
            for (int x = 0; x < tectonica.getLargura(); x++) {

                if (tectonica.isTerra(x, y)) {

                    int valor = massa.getValor(x, y);

                    if (tectonica.isTerra(x, y - 1) && tectonica.isTerra(x, y + 1) && tectonica.isTerra(x - 1, y) && tectonica.isTerra(x + 1, y)) {

                        valor += massa.getValor(x, y - 1);
                        valor += massa.getValor(x, y + 1);
                        valor += massa.getValor(x - 1, y);
                        valor += massa.getValor(x + 1, y);

                        valor = valor / 5;


                    }


                    massa.setValor(x, y, valor);

                }


            }
        }


    }

}

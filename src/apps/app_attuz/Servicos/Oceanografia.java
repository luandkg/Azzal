package apps.app_attuz.Servicos;

import apps.app_attuz.Ferramentas.Local;
import libs.luan.Lista;

public class Oceanografia {

    private Lista<Local> mMares;

    public Oceanografia(Lista<Local> eMares) {
        mMares = eMares;
    }

    public String getOceanoProximo(int px, int py) {
        String ret = "";

        int proximo = 0;
        boolean isPrimeiro = true;

        double dPX1 = (double) px;
        double dPY1 = (double) py;

        //  System.out.println("Local :: " + px + " - " + py);

        for (Local mar : mMares) {

            double dPX2 = (double) mar.getX();
            double dPY2 = (double) mar.getY();

            double d1 = (dPX2 - dPX1);
            double d2 = (dPY2 - dPY1);

            int distancia = (int) Math.sqrt((d1 * d1) + (d2 * d2));

            if (isPrimeiro) {
                isPrimeiro = false;
                proximo = distancia;
                ret = mar.getNome();
            } else {
                if (distancia < proximo) {
                    proximo = distancia;
                    ret = mar.getNome();
                }
            }

            //System.out.println("Mar :: " + mar.getNome() + " : " + mar.getX() + " - " + mar.getY() + " --- " + distancia);

        }


        return ret;
    }


}

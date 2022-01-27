package AppAttuz.Ferramentas;

import AppAttuz.Camadas.Massas;
import AppAttuz.IDW.PontoIDW;

import java.util.ArrayList;

public class ExpansorDeLimite {

    public static ArrayList<PontoIDW> expandir(ArrayList<PontoIDW> eixos, Massas tectonica) {

        ArrayList<PontoIDW> novos = new ArrayList<PontoIDW>();

        for (PontoIDW eixo : eixos) {


            // LATERALMENTE
            if (eixo.getX() > (tectonica.getLargura() / 2)) {
                novos.add(new PontoIDW(eixo.getX() - tectonica.getLargura(), eixo.getY(), eixo.getDistancia(), eixo.getValor()));
            } else if (eixo.getX() < (tectonica.getLargura() / 2)) {
                novos.add(new PontoIDW(tectonica.getLargura() + eixo.getX(), eixo.getY(), eixo.getDistancia(), eixo.getValor()));
            }


            // ACIMA E EMBAIXO
            if (eixo.getY() > ((tectonica.getAltura() / 2) + (tectonica.getAltura() / 4))) {

                if (eixo.getX() > (tectonica.getLargura() / 2)) {
                    novos.add(new PontoIDW(tectonica.getLargura() - eixo.getX(), eixo.getY(), eixo.getDistancia(), eixo.getValor()));
                } else if (eixo.getX() < (tectonica.getLargura() / 2)) {
                    int ate_centro = (tectonica.getLargura() / 2) - eixo.getX();
                    novos.add(new PontoIDW((tectonica.getLargura() / 2) + ate_centro, eixo.getY(), eixo.getDistancia(), eixo.getValor()));
                }

            } else if (eixo.getY() < (tectonica.getAltura() / 4)) {

                if (eixo.getX() > (tectonica.getLargura() / 2)) {
                    novos.add(new PontoIDW(tectonica.getLargura() - eixo.getX(), eixo.getY(), eixo.getDistancia(), eixo.getValor()));
                } else if (eixo.getX() < (tectonica.getLargura() / 2)) {
                    int ate_centro = (tectonica.getLargura() / 2) - eixo.getX();
                    novos.add(new PontoIDW((tectonica.getLargura() / 2) + ate_centro, eixo.getY(), eixo.getDistancia(), eixo.getValor()));
                }

            }

        }

        return novos;
    }
}

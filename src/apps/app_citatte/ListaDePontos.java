package apps.app_citatte;

import libs.azzal.geometria.Ponto;
import libs.luan.Lista;

public class ListaDePontos {


    public static void adicionar_unicamente(Ponto pt, Lista<Ponto> pontos) {

        boolean existe = false;

        for (Ponto proc : pontos) {
            if (proc.isIgual(pt)) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            pontos.adicionar(pt);
        }
    }
}

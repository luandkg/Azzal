package apps.app_atzum.utils;

import libs.azzal.geometria.Ponto;
import libs.luan.Lista;

public class GeometriaEspacial2D {

    public static boolean PONTO_EXISTE(Lista<Ponto> todos, Ponto pt_proc) {

        boolean existe = false;

        for (Ponto pt : todos) {
            if (pt.getX() == pt_proc.getX() && pt.getY() == pt_proc.getY()) {
                existe = true;
                break;
            }
        }

        return existe;
    }




}

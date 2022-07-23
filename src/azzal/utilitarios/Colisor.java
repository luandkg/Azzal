package azzal.utilitarios;

import azzal.geometria.Retangulo;

public class Colisor {

    public boolean colisao_Retangulos(Retangulo r1, Retangulo r2) {

        if (r1.getX() < r2.getX() + r2.getLargura() &&
                r1.getX() + r1.getLargura() > r2.getX() &&
                r1.getY() < r2.getY() + r2.getAltura() &&
                r1.getY() + r1.getAltura() > r2.getY()) {
            return true;
        }


        return false;
    }

}

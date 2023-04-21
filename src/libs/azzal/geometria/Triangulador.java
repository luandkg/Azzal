package libs.azzal.geometria;

public class Triangulador {

    public static Triangulo getSetaDireita(Retangulo eRect) {

        Ponto eP1 = new Ponto(eRect.getX(), eRect.getY());
        Ponto eP2 = new Ponto(eRect.getX(), eRect.getY2());
        Ponto eP3 = new Ponto(eRect.getX2(), eRect.getY() + ((eRect.getY2() - eRect.getY()) / 2));

        return new Triangulo(eP1, eP2, eP3);

    }
}

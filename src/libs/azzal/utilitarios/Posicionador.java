package libs.azzal.utilitarios;

import libs.azzal.geometria.Circulo;
import libs.azzal.geometria.Oval;
import libs.azzal.geometria.Quadrado;
import libs.azzal.geometria.Retangulo;

public class Posicionador {

    public Quadrado getQuadrado_Centralizado(int eX, int eY, int eTamanho) {

        int eMetade = eTamanho / 2;

        return new Quadrado(eX - eMetade, eY - eMetade, eTamanho);

    }

    public Retangulo getRetangulo_Centralizado(int eX, int eY, int eLargura, int eAltura) {

        int eMetadeLargura = eLargura / 2;
        int eMetadeeAltura = eAltura / 2;

        return new Retangulo(eX - eMetadeLargura, eY - eMetadeeAltura, eLargura, eAltura);

    }

    public Circulo getCirculo_Centralizado(int eX, int eY, Circulo eCirculo) {

        return new Circulo(eX - eCirculo.getRaio(), eY - eCirculo.getRaio(), eCirculo.getRaio());

    }

    public Circulo getCirculo_Centralizado(int eX, int eY, int eRaio) {

        return new Circulo(eX - eRaio, eY - eRaio, eRaio);

    }

    public Circulo getCirculo(int eX, int eY, int eRaio) {

        return new Circulo(eX, eY, eRaio);

    }

    public Oval getOval_Centralizado(int eX, int eY, int eRaioLargura, int eRaioAltura) {

        return new Oval(eX - eRaioLargura, eY - eRaioAltura, eRaioLargura, eRaioAltura);

    }

}

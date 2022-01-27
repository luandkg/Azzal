package Griff;

import Azzal.Renderizador;
import Azzal.Utils.Cor;

public class Griphizz {

    public void drawString(Renderizador mRenderizador, Griphattor mGriphattor, String eTexto, Cor eTextoCor, int eX, int eY) {

        int i = 0;
        int o = eTexto.length();

        while (i < o) {

            String letra = String.valueOf(eTexto.charAt(i));

            if (letra.contentEquals("A")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getA(), eTextoCor);
            } else if (letra.contentEquals("B")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getB(), eTextoCor);
            } else if (letra.contentEquals("C")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getC(), eTextoCor);
            } else if (letra.contentEquals("D")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getD(), eTextoCor);
            } else if (letra.contentEquals("E")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getE(), eTextoCor);
            } else if (letra.contentEquals("F")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getF(), eTextoCor);
            } else if (letra.contentEquals("G")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getG(), eTextoCor);
            } else if (letra.contentEquals("H")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getH(), eTextoCor);
            } else if (letra.contentEquals("I")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getI(), eTextoCor);
            } else if (letra.contentEquals("J")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getJ(), eTextoCor);
            } else if (letra.contentEquals("K")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getK(), eTextoCor);
            } else if (letra.contentEquals("L")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getL(), eTextoCor);
            } else if (letra.contentEquals("M")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getM(), eTextoCor);

            } else if (letra.contentEquals("N")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getN(), eTextoCor);
            } else if (letra.contentEquals("O")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getO(), eTextoCor);
            } else if (letra.contentEquals("P")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getP(), eTextoCor);
            } else if (letra.contentEquals("Q")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getQ(), eTextoCor);
            } else if (letra.contentEquals("T")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getT(), eTextoCor);
            } else if (letra.contentEquals("R")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getR(), eTextoCor);
            } else if (letra.contentEquals("S")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getS(), eTextoCor);
            } else if (letra.contentEquals("U")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getU(), eTextoCor);
            } else if (letra.contentEquals("V")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getV(), eTextoCor);
            } else if (letra.contentEquals("W")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getW(), eTextoCor);
            } else if (letra.contentEquals("X")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getX(), eTextoCor);
            } else if (letra.contentEquals("Y")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getY(), eTextoCor);
            } else if (letra.contentEquals("Z")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getZ(), eTextoCor);


            } else if (letra.contentEquals("_")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.get_(), eTextoCor);
            } else if (letra.contentEquals("-")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.get_Hifen(), eTextoCor);

            } else if (letra.contentEquals(".")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.get_Ponto(), eTextoCor);

            } else if (letra.contentEquals(":")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.get_DoisPonto(), eTextoCor);
            } else if (letra.contentEquals("º")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getOrdem(), eTextoCor);

            } else if (letra.contentEquals("=")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getIgual(), eTextoCor);
            } else if (letra.contentEquals(">")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.get_Maior(), eTextoCor);


            } else if (letra.contentEquals("0")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getN0(), eTextoCor);
            } else if (letra.contentEquals("1")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getN1(), eTextoCor);
            } else if (letra.contentEquals("2")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getN2(), eTextoCor);
            } else if (letra.contentEquals("3")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getN3(), eTextoCor);
            } else if (letra.contentEquals("4")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getN4(), eTextoCor);
            } else if (letra.contentEquals("5")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getN5(), eTextoCor);
            } else if (letra.contentEquals("6")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getN6(), eTextoCor);
            } else if (letra.contentEquals("7")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getN7(), eTextoCor);
            } else if (letra.contentEquals("8")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getN8(), eTextoCor);
            } else if (letra.contentEquals("9")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getN9(), eTextoCor);


            } else if (letra.contentEquals(" ")) {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getESPACO(), eTextoCor);
            } else {
                drawGriph(mRenderizador, eX + (i * 16 * 1), eY, mGriphattor.getESPACO(), eTextoCor);
            }

            i += 1;
        }

    }


    public void drawGriph(Renderizador mRenderizador, int eX, int eY, Griph eGriph, Cor eCor) {

        int aX = eX;
        int aY = eY;

        int aXFim = eGriph.getLargura();
        int aYFim = eGriph.getAltura();

        for (int iy = 0; iy < aYFim; iy++) {
            for (int ix = 0; ix < aXFim; ix++) {

                boolean e = eGriph.get(ix, iy);

                if (e) {
                    mRenderizador.drawRect_Pintado(aX + (ix * 1), aY + (iy * 1), 1, 1, eCor);
                }

            }

        }

    }


}

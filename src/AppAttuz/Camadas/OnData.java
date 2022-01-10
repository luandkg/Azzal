package AppAttuz.Camadas;

import AppAttuz.EscalasPadroes;
import AppAttuz.Ferramentas.Escala;
import Imaginador.ImageUtils;

import java.awt.image.BufferedImage;

public class OnData {

    private int mLargura;
    private int mAltura;

    private Escala mTerrestre;
    private Escala mAquatica;
    private Escala mMar;
    private Escala mESCALA_UMIDADE;

    private BufferedImage relevo;
    private BufferedImage proximidade_do_mar;
    private BufferedImage umidade;

    private int DISTANCIA_PADRAO = 10;
    private int ALTITUDE_PADRAO = 500;
    private int UMIDADE_PADRAO = 5;

    public OnData(String LOCAL) {

        relevo = ImageUtils.getImagem(LOCAL + "build/relevo.png");
        proximidade_do_mar = ImageUtils.getImagem(LOCAL + "build/mar.png");
        umidade = ImageUtils.getImagem(LOCAL + "build/umidade.png");

        mLargura = relevo.getWidth();
        mAltura = relevo.getHeight();


        mTerrestre = EscalasPadroes.getEscalaTerrestre();
        mAquatica = EscalasPadroes.getEscalaAquatica();
        mMar = EscalasPadroes.getEscalaDistancia();
        mESCALA_UMIDADE= EscalasPadroes.getEscalaUmidade();
    }

    public int getAltura(int x, int y) {
        if (x >= 0 && x < mLargura && y >= 0 && y < mAltura) {

            int v = relevo.getRGB(x, y);
            int e = 0;

            for (int i = 0; i < 11; i++) {

                if (mTerrestre.get(i) == v) {
                    e = i * ALTITUDE_PADRAO;
                    break;
                } else if (mAquatica.get(i) == v) {
                    e = -(i * ALTITUDE_PADRAO);
                    break;
                }

            }

            return e;
        } else {
            return 0;
        }
    }

    public int getDistanciaDoMar(int x, int y) {
        if (x >= 0 && x < mLargura && y >= 0 && y < mAltura) {

            int v = proximidade_do_mar.getRGB(x, y);
            int e = 0;

            for (int i = 0; i < mMar.getMaximo(); i++) {

                if (mMar.get(i) == v) {
                    e = i * DISTANCIA_PADRAO;
                    break;
                }

            }

            return e;
        } else {
            return 0;
        }
    }

    public int getUmidade(int x, int y) {
        if (x >= 0 && x < mLargura && y >= 0 && y < mAltura) {

            int v = umidade.getRGB(x, y);
            int e = 0;

            for (int i = 0; i < 14; i++) {

                if (mESCALA_UMIDADE.get(i) == v) {
                    e = i * UMIDADE_PADRAO;
                    break;
                }

            }

            return e;
        } else {
            return 0;
        }
    }

}

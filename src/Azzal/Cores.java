package Azzal;

import Azzal.Utils.Cor;

import java.awt.*;

public class Cores {

    private Cor mLaranja;
    private Cor mPreto;
    private Cor mAmarelo;
    private Cor mVermelho;
    private Cor mAzul;
    private Cor mVerde;
    private Cor mBranco;

    public Cores() {

        mLaranja = Cor.getRGB(Color.orange);
        mAmarelo = Cor.getRGB(Color.yellow);
        mVermelho = Cor.getRGB(Color.red);
        mAzul = Cor.getRGB(Color.blue);
        mVerde = Cor.getRGB(Color.green);

        mBranco = Cor.getRGB(Color.white);
        mPreto = Cor.getRGB(Color.black);

    }

    public Cor getLaranja() {
        return mLaranja;
    }

    public Cor getBranco() {
        return mBranco;
    }

    public Cor getPreto() {
        return mPreto;
    }

    public Cor getAmarelo() {
        return mAmarelo;
    }

    public Cor getVermelho() {
        return mVermelho;
    }

    public Cor getVerde() {
        return mVerde;
    }

    public Cor getAzul() {
        return mAzul;
    }

    public static Cor hexToCor(String colorStr) {
        return new Cor(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

    public static Color hexToColor(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }
}

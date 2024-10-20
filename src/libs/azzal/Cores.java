package libs.azzal;

import libs.azzal.utilitarios.Cor;

import java.awt.*;

public class Cores {

    private Cor mLaranja;
    private Cor mPreto;
    private Cor mAmarelo;
    private Cor mVermelho;
    private Cor mAzul;
    private Cor mVerde;
    private Cor mBranco;
    private Cor mTurquesa;
    private Cor mTurjo;

    private Cor mCinza;
    private Cor mMarrom;
    private Cor mRosa;
    private Cor mCianeto;

    public Cores() {

        mLaranja = Cor.getRGB(Color.orange);
        mAmarelo = Cor.getRGB(Color.yellow);
        mVermelho = Cor.getRGB(Color.red);
        mAzul = Cor.getRGB(Color.blue);
        mVerde = Cor.getRGB(Color.green);

        mTurquesa = Cor.getHexCor("#6A1B9A");
        mCinza = Cor.getHexCor("#90A4AE");
        mMarrom = Cor.getHexCor("#8D6E63");
        mRosa = Cor.getHexCor("#E91E63");
        mCianeto = Cor.getHexCor("#1DE9B6");
mTurjo = Cor.getHexCor("#9E9D24");

        mBranco = Cor.getRGB(Color.white);
        mPreto = Cor.getRGB(Color.black);


    }

    public Cor getLaranja() {
        return mLaranja;
    }


    public Cor getTurjo(){
        return mTurjo;
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

    public Cor getTurquesa() {
        return mTurquesa;
    }

    public Cor getCinza() {
        return mCinza;
    }

    public Cor getMarrom() {
        return mMarrom;
    }

    public Cor getCianeto() {
        return mCianeto;
    }

    public Cor getRosa() {
        return mRosa;
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

    public static Cor toCinza(int valor){return new Cor(valor, valor, valor);}

    public static int organizarVariacao(int valor){
        if (valor > 255) {
            valor = 255;
        }
        if (valor < 0) {
            valor = 0;
        }
        return valor;
    }
}

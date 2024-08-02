package libs.azzal.utilitarios;

import libs.luan.fmt;

public class HSV {

    public static final int MINIMO = 9;
    public static final int MAXIMO = 99;
    public static final int MEDIANO = 55;

    public static final int AZUL = 240;



    private double mH;
    private double mS;
    private double mV;

    public HSV(double eH, double eS, double eV) {
        mH = eH;
        mS = eS/100;
        mV = eV/100;
    }

    public double getH() {
        return mH;
    }

    public double getS() {
        return mS;
    }

    public double getV() {
        return mV;
    }

    public void set(double eH, double eS, double eV) {
        mH = eH;
        mS = eS;
        mV = eV;
    }

    public Cor toRGB(){
        return HSV.toRGB(this);
    }

    public static HSV getRGB(Cor eCor) {

        double h = 0;
        double s = 0;
        double v = 0;


        return new HSV(h, s, v);
    }

    public static Cor toRGB(HSV eHSV) {

        double r =0;
        double g = 0;
        double b = 0;


        double hh = eHSV.getH() / 60;
        int i = ((int) hh) % 6;

        double f = hh - i;
        double p = eHSV.getV() * (1 - eHSV.getS());
        double q = eHSV.getV() * (1 - f * eHSV.getS());
        double t = eHSV.getV() * (1 - (1 - f) * eHSV.getS());

        switch (i) {
            case 0:
                r = eHSV.getV(); g = t; b = q; break;
            case 1:
                r = q; g = eHSV.getV(); b = p; break;
            case 2:
                r = p; g = eHSV.getV(); b = t; break;
            case 3:
               r = p; g = q; b = eHSV.getV(); break;
            case 4:
                r = t; g = p; b = eHSV.getV(); break;
            case 5:
                r = eHSV.getV(); g = p; b = q; break;
            default:
        }

        return new Cor((int)(r*256.0), (int)(g*256.0), (int)(b*256.));
    }

    public String toString(){
        return "<<"+fmt.f2(mH) + "|" + fmt.f2(mS) + "|" + fmt.f2(mV) + ">>";
    }


    public static int NORMAL(int valor){
        if(valor<0){
            return 0;
        }
        if(valor>99){
            return 99;
        }
        return valor;
    }

    public static int INVERSO(int valor) {
        return 99 - NORMAL(valor);
    }

}

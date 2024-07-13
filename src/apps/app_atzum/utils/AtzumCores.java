package apps.app_atzum.utils;

import libs.azzal.Cores;
import libs.azzal.utilitarios.Cor;

public class AtzumCores {

    public static final Cor T0 = Cor.getHexCor("#4FC3F7");
    public static final Cor T1 = Cor.getHexCor("#2196F3");
    public static final Cor T2 = Cor.getHexCor("#5E35B1");
    public static final Cor T3 = Cor.getHexCor("#7B1FA2");
    public static final Cor T4 = Cor.getHexCor("#C2185B");
    public static final Cor T5 = Cor.getHexCor("#C62828");

    public static final Cor T6 = Cor.getHexCor("#CDDC39");
    public static final Cor T7 = Cor.getHexCor("#7CB342");
    public static final Cor T8 = Cor.getHexCor("#2E7D32");
    public static final Cor T9 = Cor.getHexCor("#FDD835");
    public static final Cor T10 = Cor.getHexCor("#FB8C00");
    public static final Cor T11 = Cor.getHexCor("#D84315");

    public static final Cor T12 = Cor.getHexCor("#4E342E");

    public static Cor GET_COR(int indice) {

        Cores mCores = new Cores();

        Cor eCor = mCores.getBranco();

        if (indice == 0) {
            eCor = AtzumCores.T0;
        } else if (indice == 100) {
            eCor = AtzumCores.T1;
        } else if (indice == 200) {
            eCor = AtzumCores.T2;
        } else if (indice == 300) {
            eCor = AtzumCores.T3;
        } else if (indice == 400) {
            eCor = AtzumCores.T4;
        } else if (indice == 500) {
            eCor = AtzumCores.T5;
        } else if (indice == 600) {
            eCor = AtzumCores.T6;
        } else if (indice == 700) {
            eCor = AtzumCores.T7;
        } else if (indice == 800) {
            eCor = AtzumCores.T8;
        } else if (indice == 900) {
            eCor = AtzumCores.T9;
        } else if (indice == 1000) {
            eCor = AtzumCores.T10;
        } else if (indice == 1100) {
            eCor = AtzumCores.T11;
        } else if (indice == 1200) {
            eCor = AtzumCores.T12;
        }

        return eCor;
    }

}

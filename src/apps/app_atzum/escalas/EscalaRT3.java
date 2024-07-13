package apps.app_atzum.escalas;

import libs.azzal.Cores;
import libs.azzal.utilitarios.Cor;
import libs.luan.Lista;

public class EscalaRT3 {

    // VERMELHO

    public static final Cor T0 = Cor.getHexCor("#FFEBEE");
    public static final Cor T1 = Cor.getHexCor("#FFCDD2");
    public static final Cor T2 = Cor.getHexCor("#EF9A9A");
    public static final Cor T3 = Cor.getHexCor("#FF8A80");
    public static final Cor T4 = Cor.getHexCor("#E57373");
    public static final Cor T5 = Cor.getHexCor("#FF5252");

    public static final Cor T6 = Cor.getHexCor("#EF5350");
    public static final Cor T7 = Cor.getHexCor("#F44336");

    public static final Cor T8 = Cor.getHexCor("#E53935");
    public static final Cor T9 = Cor.getHexCor("#D32F2F");
    public static final Cor T10 = Cor.getHexCor("#C62828");
    public static final Cor T11 = Cor.getHexCor("#B71C1C");
    public static final Cor T12 = Cor.getHexCor("#D50000");

    public static int MINIMO() {
        return 0;
    }

    public static int MAXIMO() {
        return 1200;
    }

    public static Cor GET_COR(int indice) {

        Cores mCores = new Cores();

        Cor eCor = mCores.getBranco();

        if (indice == 0) {
            eCor = EscalaRT3.T0;
        } else if (indice == 100) {
            eCor = EscalaRT3.T1;
        } else if (indice == 200) {
            eCor = EscalaRT3.T2;
        } else if (indice == 300) {
            eCor = EscalaRT3.T3;
        } else if (indice == 400) {
            eCor = EscalaRT3.T4;
        } else if (indice == 500) {
            eCor = EscalaRT3.T5;
        } else if (indice == 600) {
            eCor = EscalaRT3.T6;
        } else if (indice == 700) {
            eCor = EscalaRT3.T7;
        } else if (indice == 800) {
            eCor = EscalaRT3.T8;
        } else if (indice == 900) {
            eCor = EscalaRT3.T9;
        } else if (indice == 1000) {
            eCor = EscalaRT3.T10;
        } else if (indice == 1100) {
            eCor = EscalaRT3.T11;
        } else if (indice == 1200) {
            eCor = EscalaRT3.T12;
        }

        return eCor;
    }

    public static Lista<Integer> GET_VALORES() {
        Lista<Integer> valores = new Lista<Integer>();

        valores.adicionar(0);
        valores.adicionar(100);
        valores.adicionar(200);
        valores.adicionar(300);
        valores.adicionar(400);
        valores.adicionar(500);
        valores.adicionar(600);
        valores.adicionar(700);
        valores.adicionar(800);
        valores.adicionar(900);
        valores.adicionar(1000);
        valores.adicionar(1100);
        valores.adicionar(1200);

        return valores;
    }


    public static int GET_INDICE(int valor) {

        int indice = 0;


        if (valor == 0) {
            indice = 0;
        } else if (valor == 100) {
            indice = 1;
        } else if (valor == 200) {
            indice = 2;
        } else if (valor == 300) {
            indice = 3;
        } else if (valor == 400) {
            indice = 4;
        } else if (valor == 500) {
            indice = 5;
        } else if (valor == 600) {
            indice = 6;
        } else if (valor == 700) {
            indice = 7;
        } else if (valor == 800) {
            indice = 8;
        } else if (valor == 900) {
            indice = 9;
        } else if (valor == 1000) {
            indice = 10;
        } else if (valor == 1100) {
            indice = 11;
        } else if (valor == 1200) {
            indice = 12;
        }

        return indice;
    }

}

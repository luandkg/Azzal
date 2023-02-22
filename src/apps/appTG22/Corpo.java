package apps.appTG22;

public class Corpo {

    public static double getPeso(double ePeso) {
        return ePeso * 2.21;
    }

    public static double getAltura(double eAltura) {
        return eAltura * 2.04;
    }

    public static double getNivel(double ePeso, double eAltura) {
        double p = ePeso;
        double a = eAltura;

        return ((p) / (a * a))*3.14;
    }
}

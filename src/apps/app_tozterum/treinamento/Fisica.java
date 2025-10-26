package apps.app_tozterum.treinamento;

public class Fisica {

    public static String KG(int eQuantidade) {
        return String.valueOf(eQuantidade) + " Kg";
    }

    public static String MIN(int eQuantidade) {
        return String.valueOf(eQuantidade) + " min";
    }

    public static Serie SERIE(int eSeries, int eRepeticoes) {
        return new Serie(eSeries, eRepeticoes);
    }
}

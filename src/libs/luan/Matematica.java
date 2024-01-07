package libs.luan;

public class Matematica {

    public static int maior(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public static int menor(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    public static double normaliza(double a) {
        if (a < 0) {
            return a * (-1);
        } else {
            return a;
        }
    }

    public static int getProporcionadoInt(double taxa, double valor) {
        return (int) (taxa * valor);
    }


    public static double criarTaxa(int total, int por) {
        return (double) total / (double) por;
    }

    public static int aumentar_ate(int corrente, int taxa, int limite) {

        corrente += taxa;
        if (corrente >= limite) {
            corrente = limite;
        }

        return corrente;
    }

    public static int diminuir_ate(int corrente, int taxa, int limite) {

        corrente -= taxa;
        if (corrente <= limite) {
            corrente = limite;
        }

        return corrente;
    }

}

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

    public static boolean isPar(int numero){
        return (numero % 2)==0;
    }

    public static boolean isImpar(int numero){
        return (numero % 2)==1;
    }

    public static Ordenavel<Double> DOUBLE_COMPARADOR(){
        return new Ordenavel<Double>() {
            @Override
            public int emOrdem(Double a, Double b) {
                return Double.compare(a,b);
            }
        };
    }


    public static double NORMALIZAR(double valor,double min,double max){

        if(valor<min){
            valor=min;
        }

        if(valor>max){
            valor=max;
        }

        return valor;
    }

    public static int NORMALIZAR(int valor,int min,int max){

        if(valor<min){
            valor=min;
        }

        if(valor>max){
            valor=max;
        }

        return valor;
    }


    public static int ESCALA_NORMAL(double valor,double maximo){
      return  (int)(((double) valor / (double) maximo) * 100.0);
    }
}

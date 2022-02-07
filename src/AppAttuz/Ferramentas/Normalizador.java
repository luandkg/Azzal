package AppAttuz.Ferramentas;

public class Normalizador {

    private int maior = 0;
    private int menor = 0;
    private boolean zerado = true;
    private double delta = 0;
    private double dec = 0;
    private double tx = 0 ;

    private boolean atualizar;

    private int mEscalas;

    public Normalizador(int escalas) {

        maior = 0;
        menor = 0;
        mEscalas = escalas;

        zerado = true;
        atualizar = true;
    }

    public void zerar() {
        zerado = true;
        maior = 0;
        menor = 0;
        atualizar = true;
    }

    public void adicionar(int valor1, int valor2) {
        adicionar(valor1);
        adicionar(valor2);
    }

    public void adicionar(int valor) {

        if (zerado) {
            maior = valor;
            menor = valor;
            zerado = false;
        } else {
            if (valor > maior) {
                maior = valor;
            }
            if (valor < menor) {
                menor = valor;
            }
        }

        atualizar = true;

    }

    public void stock(){

        System.out.println("Maior      :: " + maior);
          System.out.println("Menor      :: " + menor);
         System.out.println("Delta      :: " + delta);
         System.out.println("Taxa       :: " + tx);
          System.out.println("Escalas    :: " + mEscalas);
          System.out.println("T. Esc     :: " + dec);
    }

    public void equilibrar() {

        delta = (double) (maior - menor);
        dec = (double) mEscalas / 100.0;
        tx = (double) delta / 100.0;

        atualizar = false;

    }

    public int get(int eValor) {

        if (atualizar) {
            equilibrar();
        }


       // double valor = ((double) eValor - (double) menor) / delta;

        //double fluxo = valor*dec;

        double o = emOutra(menor, maior, 0, mEscalas, eValor);

        return (int) o;

    }

    public double emOutra(int min_a, int max_a, int min_b, int max_b, int val_a) {

        int delta_a = max_a - min_a;
        double taxa_a = (double) delta_a / 100.0;
        double prop_a = ((double) (val_a - min_a) / (double) delta_a) * 100.0;

        int delta_b = max_b - min_b;
        double taxa_b = (double) delta_b / 100.0;

        double o = prop_a * taxa_b;

        return o;
    }

    public int intervalo(int min, int max, int valor) {

        if (valor < min) {
            valor = min;
        }

        if (valor > max) {
            valor = max;
        }

        return valor;
    }
}

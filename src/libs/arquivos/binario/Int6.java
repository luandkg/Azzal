package libs.arquivos.binario;

public class Int6 {

    private int valores[];

    public Int6() {
        valores = new int[6];

        for (int i = 0; i < 6; i++) {
            valores[i] = 0;
        }

    }


    public Int6(int valor) {
        valores = new int[6];
        set(valor);
    }

    public void set(int valor){

        for (int i = 0; i < 6; i++) {
            valores[i] = 0;
        }

        if (valor >= 0) {

            if (valor < 2) {
                valores[5] = valor;
            } else {
                int eixo = 5;

                while (valor > 1) {
                    int divisor = valor / 2;
                    int resto = valor % 2;
                    valores[eixo] = resto;
                    eixo -= 1;
                    valor = divisor;
                }

                valores[eixo] = valor;

            }


        }
    }

    public String get() {
        String ret = "";

        for (int i = 0; i < 6; i++) {
            ret += String.valueOf(valores[i]);
        }

        return ret;
    }

    public int getInt() {

        int ret = 0;

        int elevado = 0;

        for (int i = 5; i >= 0; i--) {
            ret += (valores[i] * potencia(2, elevado));
            elevado += 1;
        }

        return ret;
    }

    private int potencia(int base, int elevado) {
        int ret = 1;

        if (elevado > 0) {
            for (int e = 0; e < elevado; e++) {
                ret = ret * base;
            }
        }

        return ret;
    }

    public void setZero(int p) {
        valores[p] = 0;
    }

    public void setUm(int p) {
        valores[p] = 1;
    }

    public void setValor(int p,int v){
        valores[p] = v;
    }

    public void zerar() {
        for (int i = 0; i < 6; i++) {
            valores[i] = 0;
        }
    }


    public int[] getValores(){return valores;}
}

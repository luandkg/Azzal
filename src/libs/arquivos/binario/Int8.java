package libs.arquivos.binario;

public class Int8 {

    private int valores[];

    public Int8() {
        valores = new int[8];

        for (int i = 0; i < 8; i++) {
            valores[i] = 0;
        }

    }


    public Int8(int valor) {
        valores = new int[8];

        set(valor);

    }

    public void set(int valor) {

        for (int i = 0; i < 8; i++) {
            valores[i] = 0;
        }


        if (valor >= 0) {

            if (valor < 2) {
                valores[7] = valor;
            } else {
                int eixo = 7;

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

        for (int i = 0; i < 8; i++) {
            ret += String.valueOf(valores[i]);
        }

        return ret;
    }

    public String getBits(int c, int tamanho) {
        String ret = "";

        for (int i = c; i < (c + tamanho); i++) {
            ret += String.valueOf(valores[i]);
        }

        return ret;
    }

    public int getBitsInt(int c, int tamanho) {
        String ret = "";

        for (int i = c; i < (c + tamanho); i++) {
            ret += String.valueOf(valores[i]);
        }

        return Integer.parseInt(ret);
    }


    public int getInt() {

        int ret = 0;

        int elevado = 0;

        for (int i = 7; i >= 0; i--) {
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

    public boolean isZero(int p) {
        return valores[p] == 0;
    }

    public boolean isUm(int p) {
        return valores[p] == 1;
    }

    public void zerar() {
        for (int i = 0; i < 8; i++) {
            valores[i] = 0;
        }
    }


    public void copiarComecando(int comecar, int[] copiar, int tamanho) {

        int e = 0;

        for (int i = comecar; i < (comecar + tamanho); i++) {
            valores[i] = copiar[e];
            e += 1;
        }

    }


    public void set2Bits(int c, int p0, int p1) {
        valores[c] = p0;
        valores[c + 1] = p1;
    }

    public int getValor(int p) {
        return valores[p];
    }


    public int[] getArray(int comecar, int tamanho) {

        int c = comecar;
        int t = comecar + tamanho;

        int a[] = new int[tamanho];

        int p = 0;

        for (int i = c; i < t; i++) {
            a[p] = valores[i];
            p += 1;
        }

        return a;
    }

    public int getParteToInt(int comecar, int tamanho) {

        int c = comecar;
        int t = comecar + tamanho;

        int ret = 0;

        int elevado = 0;

        for (int i = (t - 1); i >= c; i--) {
            ret += (valores[i] * potencia(2, elevado));
            elevado += 1;
        }


        return ret;


    }
}

package libs.llcripto;

public class Quadrante {

    private int LADO = 4;
    private int[] mValores;

    public Quadrante() {
        mValores = new int[LADO * LADO];
    }

    public void set(int linha, int coluna, int valor) {
        mValores[(coluna * LADO) + linha] = valor;
    }

    public int get(int linha, int coluna) {
        return mValores[(coluna * LADO) + linha];
    }


    public void definirLinha(int eLinha, int v1, int v2, int v3, int v4) {

        mValores[(0 * LADO) + eLinha] = v1;
        mValores[(1 * LADO) + eLinha] = v2;
        mValores[(2 * LADO) + eLinha] = v3;
        mValores[(3 * LADO) + eLinha] = v4;

    }

    public void setDireto(int e, int valor) {
        mValores[e] = valor;
    }

    public int getDireto(int e) {
        return mValores[e];
    }

    public int getTamanho() {
        return LADO * LADO;
    }

    public String getView() {
        String ret = "";
        for (int linha = 0; linha < LADO; linha++) {
            for (int coluna = 0; coluna < LADO; coluna++) {

                String valorcorrente = String.valueOf(mValores[(coluna * LADO) + linha]);
                if (valorcorrente.length() == 1) {
                    valorcorrente = "00" + valorcorrente;
                } else if (valorcorrente.length() == 2) {
                    valorcorrente = "0" + valorcorrente;
                }
                ret += valorcorrente + " ";
            }
            ret += "\n";
        }

        return ret;
    }


    public void copiarDe(Quadrante copiar) {

        for (int i = 0; i < mValores.length; i++) {
            mValores[i] = copiar.mValores[i];
        }

    }

    public int calcularPontoCentral(){
        int v = 0;

        v+=get(1,1) + get(1,2);
        v+=get(2,1) + get(2,2);

        return v;
    }

    public int calcularTorque(){
        int v = 0;

        v+=get(0,0) + get(0,3);
        v+=get(3,0) + get(3,3);

        return v;
    }


    public static Quadrante somar(Quadrante a, Quadrante b) {
        Quadrante ret = new Quadrante();


        for (int i = 0; i < (4 * 4); i++) {
            ret.setDireto(i, a.getDireto(i) + b.getDireto(i));
        }

        return ret;
    }

    public static Quadrante subtrair(Quadrante a, Quadrante b) {
        Quadrante ret = new Quadrante();


        for (int i = 0; i < (4 * 4); i++) {
            ret.setDireto(i, a.getDireto(i) - b.getDireto(i));
        }

        return ret;
    }
}

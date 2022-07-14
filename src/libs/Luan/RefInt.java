package libs.Luan;

public class RefInt {

    private int mValor;

    public RefInt() {
        mValor = 0;
    }

    public RefInt(int eValor) {
        mValor = eValor;
    }

    public int get() {
        return mValor;
    }

    public void set(int eValor) {
        mValor = eValor;
    }

    public void set(RefInt eValor) {
        mValor = eValor.get();
    }

    // OPERACOES BASICAS

    public void somar(int eValor) {
        mValor += eValor;
    }

    public void somar(RefInt eValor) {
        mValor += eValor.get();
    }

    public void subtrair(int eValor) {
        mValor -= eValor;
    }

    public void subtrair(RefInt eValor) {
        mValor -= eValor.get();
    }

    public void multiplicar(int eValor) {
        mValor *= eValor;
    }

    public void multiplicar(RefInt eValor) {
        mValor *= eValor.get();
    }

    public void dividir(int eValor) {
        mValor /= eValor;
    }

    public void dividir(RefInt eValor) {
        mValor /= eValor.get();
    }


    // COMPARADORES

    public boolean isIgual(int eValor) {
        return mValor == eValor;
    }

    public boolean isDiferente(int eValor) {
        return mValor != eValor;
    }

    public boolean isMaior(int eValor) {
        return mValor > eValor;
    }

    public boolean isMenor(int eValor) {
        return mValor < eValor;
    }

    public boolean isMaiorIgual(int eValor) {
        return mValor >= eValor;
    }

    public boolean isMenorIgual(int eValor) {
        return mValor <= eValor;
    }

    public boolean isIgual(RefInt eValor) {
        return mValor == eValor.get();
    }

    public boolean isDiferente(RefInt eValor) {
        return mValor != eValor.get();
    }

    public boolean isMaior(RefInt eValor) {
        return mValor > eValor.get();
    }

    public boolean isMenor(RefInt eValor) {
        return mValor < eValor.get();
    }

    public boolean isMaiorIgual(RefInt eValor) {
        return mValor >= eValor.get();
    }

    public boolean isMenorIgual(RefInt eValor) {
        return mValor <= eValor.get();
    }


    // COMPLEMENTAR

    public boolean isPositivo() {
        return mValor >= 0;
    }

    public boolean isNegativo() {
        return mValor < 0;
    }

    public boolean isZero() {
        return mValor == 0;
    }

    public int getModulo() {

        int modulo = mValor;

        if (modulo < 0) {
            modulo = modulo * (-1);
        }

        return modulo;
    }


    // ESTATICAS

    public static RefInt somar(RefInt a,RefInt b){
        return new RefInt(a.get()+b.get());
    }

    public static RefInt subtrair(RefInt a,RefInt b){
        return new RefInt(a.get()-b.get());
    }

    public static RefInt multiplicar(RefInt a,RefInt b){
        return new RefInt(a.get()*b.get());
    }

    public static RefInt dividir(RefInt a,RefInt b){
        return new RefInt(a.get()/b.get());
    }


    public  void reduzir(  RefInt menor, int valor) {
        while (get() >= valor) {
            subtrair(valor);
            menor.somar(1);
        }
    }

    public  void aumentar(  RefInt menor, int valor) {
        while (get() <= (valor * (-1))) {
            somar(valor);
            menor.subtrair(1);
        }
    }

    // ESTRUTURAIS

    public static void reduz(RefInt maior, RefInt menor, int valor) {
        while (maior.get() >= valor) {
            maior.subtrair(valor);
            menor.somar(1);
        }
    }

    public static void aumente(RefInt maior, RefInt menor, int valor) {
        while (maior.get() <= (valor * (-1))) {
            maior.somar(valor);
            menor.subtrair(1);
        }
    }
}

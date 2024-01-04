package libs.luan;

public abstract class Ordenavel<T> {


    public static final int MENOR = -1;
    public static final int IGUAL = 0;
    public static final int MAIOR = +1;

    public abstract int emOrdem(T a, T b);

}

package libs.luan;

public class Extremos<T> {

    private T mMenor;
    private T mMaior;

    private int mItens;
    private Ordenavel<T> mOrdenavel;

    public Extremos(Ordenavel<T> ordenavel) {
        mMenor = null;
        mMaior = null;

        mItens = 0;
        mOrdenavel = ordenavel;
    }

    public T getMenor(){return mMenor;}
    public T getMaior(){return mMaior;}


    public void set(T valor) {

        if (mItens == 0) {
            mMenor = valor;
            mMaior = valor;
        } else {
            if (mOrdenavel.emOrdem(valor, mMenor)==Ordenavel.MENOR) {
                mMenor=valor;
            }
            if (mOrdenavel.emOrdem(valor, mMaior)==Ordenavel.MAIOR) {
                mMaior=valor;
            }
        }

        mItens += 1;
    }

}

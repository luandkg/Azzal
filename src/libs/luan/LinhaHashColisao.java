package libs.luan;

public class LinhaHashColisao<T1, T2> {

    private T1 mChave;
    private T2 mValor;


    public LinhaHashColisao(T1 eChave, T2 eValor) {
        mChave = eChave;
        mValor = eValor;
    }

    public T1 getChave() {
        return mChave;
    }

    public T2 getValor() {
        return mValor;
    }

    public void setValor(T2 eValor) {
        mValor = eValor;
    }

}
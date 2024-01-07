package libs.luan;

public class Trio<T1, T2, T3> {

    private T1 mChave;
    private T2 mValor1;
    private T3 mValor2;

    public Trio(T1 eChave, T2 eValor1, T3 eValor2) {

        mChave = eChave;
        mValor1 = eValor1;
        mValor2 = eValor2;

    }


    public T1 getChave() {
        return mChave;
    }

    public T2 getValor1() {
        return mValor1;
    }

    public T3 getValor2() {
        return mValor2;
    }

}

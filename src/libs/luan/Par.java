package libs.luan;

public class Par<T1,T2> {

    private T1 mChave;
    private T2 mValor;

    public Par(T1 eChave,T2 eValor){
        mChave = eChave;
        mValor=eValor;
    }

    public void set(T1 eChave, T2 eValor) {
        mChave = eChave;
        mValor = eValor;
    }

    public void setChave(T1 eChave) {
        mChave = eChave;
    }

    public void setValor(T2 eValor) {
        mValor = eValor;
    }
    public T1 getChave(){return mChave;}
    public T2 getValor(){return mValor;}

}

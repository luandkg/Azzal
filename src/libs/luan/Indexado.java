package libs.luan;

public class Indexado <T>{

    private int mIndex;
    private T mValor;

    public Indexado(int index,T valor){
        mIndex=index;
        mValor=valor;
    }

    public T get(){return mValor;}

    public int index(){return mIndex;}

    public void setIndex(int eIndex){
        mIndex=eIndex;
    }
}

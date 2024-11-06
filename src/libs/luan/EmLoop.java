package libs.luan;

public class EmLoop<T> {

    private int mIndice;
    private Lista<T> mLista;

    public EmLoop(Lista<T> eLista){
        mLista=eLista;
        mIndice=0;
    }

    public T get(){
        T corrente = mLista.get(mIndice);
        mIndice+=1;
        if(mIndice==mLista.getQuantidade()){
            mIndice=0;
        }
        return corrente;
    }

}

package libs.Luan;

public class Par<A,B> {

    private A mChave;
    private B mValor;

    public Par(A eChave,B eValor){
        mChave = eChave;
        mValor=eValor;
    }

    public A getChave(){return mChave;}
    public B getValor(){return mValor;}

}

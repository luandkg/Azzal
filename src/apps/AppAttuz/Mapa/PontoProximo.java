package apps.AppAttuz.Mapa;

public class PontoProximo {

    private int mValor;
    private int mDistancia;
    private boolean mExiste;

    public PontoProximo(){
        mExiste=false;
        mDistancia=0;
        mValor=0;
    }

    public PontoProximo(int eValor,int eDistancia){
        mExiste=true;
        mDistancia=eDistancia;
        mValor=eValor;
    }

    public void set(int eValor,int eDistancia){
        mExiste=true;
        mDistancia=eDistancia;
        mValor=eValor;
    }

    public boolean existe(){return mExiste;}
    public int getDistancia(){return mDistancia;}
    public int getValor(){return mValor;}

}

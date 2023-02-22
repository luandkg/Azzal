package libs.rho_benchmark;

public class RhoSlot {

    private String mNome;

    private long mInicio;
    private long mFim;

    public RhoSlot(String eNome,long eInicio,long eFim){
        mNome=eNome;
        mInicio=eInicio;
        mFim=eFim;
    }
    public String getNome(){return mNome;}

    public long getInicio(){return mInicio;}
    public long getFim(){return mFim;}

    public long getTempo(){return mFim-mInicio;}

    public boolean isDoTipo(String tipo){
        return mNome.contentEquals(tipo);
    }
}

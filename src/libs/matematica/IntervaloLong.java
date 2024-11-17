package libs.matematica;

public class IntervaloLong {

    private long mInicio;
    private long mFim;

    public IntervaloLong(long eInicio,long eFim){
        mInicio=eInicio;
        mFim=eFim;
    }

    public long getInicio(){return mInicio;}
    public long getFim(){return mFim;}
}

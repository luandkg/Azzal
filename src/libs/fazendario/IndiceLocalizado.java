package libs.fazendario;

public class IndiceLocalizado {

    private long mIndice;
    private long mPonteiro;

    public IndiceLocalizado(long indice,long ponteiro){
        mIndice=indice;
        mPonteiro=ponteiro;
    }


    public long getIndice(){return mIndice;}
    public long getPonteiro(){return mPonteiro;}
}

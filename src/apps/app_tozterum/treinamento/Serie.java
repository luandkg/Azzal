package apps.app_tozterum.treinamento;

public class Serie {

    private int mSeries;
    private int mRepeticoes;

    public Serie(int eSeries, int eRepeticoes) {
        mSeries=eSeries;
        mRepeticoes=eRepeticoes;
    }

    public int getSeries(){
        return mSeries;
    }

    public int getRepeticoes(){
        return mRepeticoes;
    }
}

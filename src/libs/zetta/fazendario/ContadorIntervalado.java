package libs.zetta.fazendario;

public class ContadorIntervalado {

    private long mMinimo;
    private long mMaximo;
    private long mCorrente;

    public ContadorIntervalado(long eMinimo, long eMaximo, long eCorrente) {
        mMinimo = eMinimo;
        mMaximo = eMaximo;
        mCorrente = eCorrente;
    }

    public long getMinimo() {
        return mMinimo;
    }

    public long getMaximo() {
        return mMaximo;
    }

    public long getCorrente() {
        return mCorrente;
    }


    public void mais() {
        mCorrente += 1;
    }

    public boolean isDentro() {
        if (mCorrente >= mMinimo && mCorrente < mMaximo) {
            return true;
        }
        return false;
    }

    public boolean ultrapassou() {
        return mCorrente >= mMaximo;
    }
}

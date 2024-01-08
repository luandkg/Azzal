package libs.rho_benchmark;

public class RhoTempo {

    private String mNome;
    private long mIniciado;
    private long mFinalizado;

    public RhoTempo(String eNome, long eIniciado, long eFinalizado) {
        mNome = eNome;
        mIniciado = eIniciado;
        mFinalizado = eFinalizado;
    }

    public String getNome() {
        return mNome;
    }

    public long getIniciado() {
        return mIniciado;
    }

    public long getFinalizado() {
        return mFinalizado;
    }

    public void setIniciado(long eIniciado) {
        mIniciado = eIniciado;
    }

    public void setFinalizado(long eFinalizado) {
        mFinalizado = eFinalizado;
    }

}

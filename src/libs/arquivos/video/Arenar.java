package libs.arquivos.video;

public class Arenar {

    private int mFrameIndex;
    private long mLocal;
    private boolean mAlocouNovoQuadro;
    private long mTamanho;

    private QuadroTipo mQuadroTipo;
    private int mTipoCorrente;
    private String mStatus;

    public Arenar() {
        mFrameIndex = -1;
        mLocal = -1;
        mAlocouNovoQuadro = false;
        mQuadroTipo = QuadroTipo.Completo;
        mTipoCorrente = 0;
        mTamanho = 0;
        mStatus = "";
    }

    public void setTipoCorrente(int eTipo) {
        mTipoCorrente = eTipo;
    }

    public int getTipoCorrente() {
        return mTipoCorrente;
    }

    public void setFrameIndex(int eFrameIndex) {
        mFrameIndex = eFrameIndex;
    }

    public void setLocal(long eLocal) {
        mLocal = eLocal;
    }

    public void setAlocouNovoQuadro(boolean eAlocou) {
        mAlocouNovoQuadro = eAlocou;
    }

    public void setTipoFrame(QuadroTipo eQuadroTipo) {
        mQuadroTipo = eQuadroTipo;
    }

    public int getFrameIndex() {
        return mFrameIndex;
    }

    public long getLocal() {
        return mLocal;
    }

    public long getTamanho() {
        return mTamanho;
    }

    public void setTamanho(long eTamanho) {
        mTamanho = eTamanho;
    }

    public boolean getAlocouNovoQuadro() {
        return mAlocouNovoQuadro;
    }

    public QuadroTipo getTipoFrame() {
        return mQuadroTipo;
    }


    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String eStatus) {
         mStatus = eStatus;
    }

}

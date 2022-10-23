package libs.matematica;

public class ArcoFloat {

    private float mInicio;
    private float mFim;

    public ArcoFloat(float eInicio, float eFim) {
        mInicio = eInicio;
        mFim = eFim;
    }

    public void set(float eInicio, float eFim) {
        mInicio = eInicio;
        mFim = eFim;
    }

    public float getInicio() {
        return mInicio;
    }

    public float getFim() {
        return mFim;
    }

    public void rodar(float eRotacao) {
        mInicio = mInicio + eRotacao;
        mFim = mFim + eRotacao;
    }
}

package libs.luan;

public class Resultado<T1, T2> {

    private T1 mOK = null;
    private T2 mErro = null;
    private boolean mStatus = false;

    public Resultado(T1 eOK) {
        mOK = eOK;
        mStatus = true;
    }

    public Resultado() {
        mStatus = false;
    }

    public boolean isOK() {
        return mStatus == true;
    }

    public boolean isErro() {
        return mStatus == false;
    }

    public T1 getOK() {
        return mOK;
    }

    public T2 getErro() {
        return mErro;
    }

    public void errar(T2 eErro) {
        mStatus = false;
        mErro = eErro;
    }

    public static <T1x, T2x> Resultado<T1x, T2x> OK(T1x eT1) {
        return new Resultado<>(eT1);
    }

    public static <T1x, T2x> Resultado<T1x, T2x> FALHAR(T2x eT2) {
        Resultado<T1x, T2x> resultado = new Resultado<>();
        resultado.errar(eT2);
        return resultado;
    }
}

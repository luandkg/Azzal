package Luan;

public class RefString {

    private String mValor;

    public RefString() {
        mValor = "";
    }

    public RefString(String eValor) {
        mValor = eValor;
    }

    public String get() {
        return mValor;
    }

    public void set(String eValor) {
        mValor = eValor;
    }

    // COMPARADORES

    public boolean isIgual(String outra){
        return mValor.contentEquals(outra);
    }

    public boolean isDiferente(String outra){
        return !mValor.contentEquals(outra);
    }

    public boolean isIgual(RefString outra){
        return mValor.contentEquals(outra.get());
    }

    public boolean isDiferente(RefString outra){
        return !mValor.contentEquals(outra.get());
    }
}

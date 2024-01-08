package apps.app_tg22;

public class Ficha {

    private String mTozte = "";

    private double mAltura = 0.0;
    private double mPeso = 0.0;

    private boolean mIsFichario;

    public Ficha(String eTozte, double eAltura, double ePeso) {
        mTozte = eTozte;
        mAltura = eAltura;
        mPeso = ePeso;
        mIsFichario = true;
    }

    public Ficha(String eTozte) {
        mTozte = eTozte;
        mAltura = 0;
        mPeso = 0;
        mIsFichario = false;
    }

    public boolean isFichario() {
        return mIsFichario;
    }

    public String getTozte() {
        return mTozte;
    }

    public double getAltura() {
        return mAltura;
    }

    public double getPeso() {
        return mPeso;
    }

}

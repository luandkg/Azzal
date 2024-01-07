package libs.onterraria;

public class Localizacao {

    private Ponto mLatitude;
    private Ponto mLongitude;

    public Localizacao(Ponto eLatitude, Ponto eLongitude) {
        mLatitude = eLatitude;
        mLongitude = eLongitude;
    }

    public Ponto getLatitude() {
        return mLatitude;
    }

    public Ponto getLongitude() {
        return mLongitude;
    }

    public void set(Ponto eLatitude, Ponto eLongitude) {
        mLatitude = eLatitude;
        mLongitude = eLongitude;
    }


}

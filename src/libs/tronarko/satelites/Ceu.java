package libs.tronarko.satelites;

import libs.tronarko.Tozte;

public class Ceu {

    private MapaCelestial.Allux mAllux;
    private MapaCelestial.Ettos mEttos;
    private MapaCelestial.Unnos mUnnos;

    public Ceu() {

        mAllux = new MapaCelestial.Allux();
        mEttos = new MapaCelestial.Ettos();
        mUnnos = new MapaCelestial.Unnos();

    }

    public Fases allux_getFase(Tozte TozteC) {
        return mAllux.getFase(TozteC);
    }

    public Fases ettos_getFase(Tozte TozteC) {
        return mEttos.getFase(TozteC);
    }

    public Fases unnos_getFase(Tozte TozteC) {
        return mUnnos.getFase(TozteC);
    }


    public Satelite getAllux() {
        return mAllux;
    }

    public Satelite getEttos() {
        return mEttos;
    }

    public Satelite getUnnos() {
        return mUnnos;
    }

}

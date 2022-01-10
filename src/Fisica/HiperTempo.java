package Fisica;

import Azzal.Utils.Cronometro;

public class HiperTempo {

    private Cronometro mCronTempo;
    private long mPassou;

    public HiperTempo(int eIntervalo) {

        mCronTempo = new Cronometro(eIntervalo);
        mPassou = 0;

    }


    public boolean passou() {

        boolean ret = false;

        mCronTempo.esperar();
        if (mCronTempo.foiEsperado()) {
            mCronTempo.zerar();
            ret = true;
            mPassou += 1;
        }

        return ret;

    }

    public long getTempo() {
        return mPassou;
    }
}

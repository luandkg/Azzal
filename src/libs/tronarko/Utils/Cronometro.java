package libs.tronarko.Utils;

import libs.tronarko.Tronarko;

public class Cronometro {

    private long mEsperar;
    private long mSuperarko_Ettons;

    private long mIniciado_Superarkos;
    private long mIniciado_Ettons;

    private long mAtualizado_Superarkos;
    private long mAtualizado_Ettons;

    private Tronarko eTronarko;

    public Cronometro(long eEsperar) {
        mEsperar = eEsperar;

        mSuperarko_Ettons = 10 * 100 * 100;

        eTronarko = new Tronarko();

        mIniciado_Superarkos = eTronarko.getTozte().getSuperarkosTotal();
        mIniciado_Ettons = eTronarko.getHazde().getTotalEttons();

        mAtualizado_Superarkos = mIniciado_Superarkos;
        mAtualizado_Ettons = mIniciado_Ettons;

    }

    public void iniciar() {

        mIniciado_Superarkos = eTronarko.getTozte().getSuperarkosTotal();
        mIniciado_Ettons = eTronarko.getHazde().getTotalEttons();

        mAtualizado_Superarkos = mIniciado_Superarkos;
        mAtualizado_Ettons = mIniciado_Ettons;

    }

    public void atualizar() {
        mAtualizado_Superarkos =  eTronarko.getTozte().getSuperarkosTotal();
        mAtualizado_Ettons = eTronarko.getHazde().getTotalEttons();
    }

    public boolean foiEsperado() {

        long esp_Superarkos = mAtualizado_Superarkos - mIniciado_Superarkos;
        long esp_Ettons = mAtualizado_Ettons - mIniciado_Ettons;

        long esperou = (esp_Superarkos * mSuperarko_Ettons) + esp_Ettons;

        return esperou >= mEsperar;

    }

    public long getEsperado() {

        long esp_Superarkos = mAtualizado_Superarkos - mIniciado_Superarkos;
        long esp_Ettons = mAtualizado_Ettons - mIniciado_Ettons;

        long esperou = (esp_Superarkos * mSuperarko_Ettons) + esp_Ettons;

        if(esperou >= mEsperar){
            return mEsperar;
        }else{
            return esperou;
        }

    }
}

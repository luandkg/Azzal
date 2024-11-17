package libs.tronarko.utils;

import libs.tronarko.Hazde;
import libs.tronarko.Tozte;
import libs.tronarko.Tron;

public class TronarkoFalsum {

    private Tron mInicio;
    private Tron mFim;
    private Tron mCorrente;

    private int mPassado;

    public TronarkoFalsum(Tron eInicio, Tron eFim) {
        mInicio = eInicio;
        mFim = eFim;

        mCorrente = mInicio.getCopia();
        mPassado = -1;
    }

    public TronarkoFalsum(Tozte eInicio, Tozte eFim) {
        mInicio = new Tron(new Hazde(0, 0, 0), eInicio);
        mFim = new Tron(new Hazde(9, 99, 99), eFim);

        mCorrente = mInicio.getCopia();
        mPassado = -1;
    }

    public void avancar(int mais) {
        mCorrente = mCorrente.modificar_Uzzon(mais);

        if (mCorrente.getTozte().isIgual(mFim.getTozte())) {
            if (mCorrente.getHazde().isMaiorIgual(mFim.getHazde())) {
                mCorrente = mInicio.getCopia();
            }
        } else if (mCorrente.getTozte().isMaiorQue(mFim.getTozte())) {
            mCorrente = mInicio.getCopia();
        }

    }


    public Tozte getTozte() {
        return mCorrente.getTozte();
    }

    public Hazde getHazde() {
        return mCorrente.getHazde();
    }

    public void sincronizar(int agora, int mais) {

        if (agora != mPassado) {
            mPassado = agora;
            // System.out.println("Mudar :: " + agora + " -- " + mCorrente.getTextoZerado());
            avancar(mais);
        }

    }


}

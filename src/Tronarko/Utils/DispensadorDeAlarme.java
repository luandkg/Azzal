package Tronarko.Utils;

import Tronarko.Hazde;

public class DispensadorDeAlarme {

    private boolean mDispensar;
    private Hazde mDispensadoCom;

    public DispensadorDeAlarme() {
        mDispensar = false;
        mDispensadoCom = null;
    }

    public void dispensar(Hazde eAgora) {
        mDispensar = true;
        mDispensadoCom = eAgora;
    }

    public boolean foiDispensado() {
        return mDispensar;
    }

    public Hazde getDispensado() {
        return mDispensadoCom;
    }

    public boolean estaTocando(Lembrete eLembrete, int mTocar, Hazde mAgora) {

        boolean isTocando = false;

        Hazde mIniciar = eLembrete.getHazde();
        Hazde mFinalizar = eLembrete.getHazde().adicionar_Itta(mTocar);


        boolean deve_alterar = false;

        if (mAgora.isMaiorIgual(mIniciar) && mAgora.isMenor(mFinalizar)) {
            deve_alterar = true;
        }


        if (foiDispensado()) {

            if (mIniciar.isMaiorIgual(mDispensadoCom)) {
                if (mAgora.isMaiorIgual(mIniciar) && mAgora.isMenor(mFinalizar)) {
                    mDispensar = false;
                }
            }

        } else if (deve_alterar) {
            isTocando = true;
        }

        return isTocando;
    }

}

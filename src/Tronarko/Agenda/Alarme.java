package Tronarko.Agenda;

import Tronarko.Hazde;
import Tronarko.Superarkos;
import Tronarko.Tozte;

import java.util.ArrayList;

public class Alarme {

    private ArrayList<Lembrete> mLembretes;
    private ArrayList<LembreteSuperarko> mSLembretes;


    private boolean mDispensar;

    private Tozte mDispensadoTozteCom;
    private Hazde mDispensadoHazdeCom;

    public Alarme() {

        mLembretes = new ArrayList<Lembrete>();
        mSLembretes = new ArrayList<LembreteSuperarko>();


        mDispensar = false;

        mDispensadoTozteCom = null;
        mDispensadoHazdeCom = null;
    }


    public void marcarSimples(Tozte eTozte, Hazde eHazde) {

        mLembretes.add(new Lembrete(eTozte, eHazde));

    }

    public void marcarSuperarko(Superarkos eSuperarkos, Hazde eHazde) {

        mSLembretes.add(new LembreteSuperarko(eSuperarkos, eHazde));

    }

    public void limparTudo() {
        mLembretes.clear();
        mSLembretes.clear();
    }

    public ArrayList<Lembrete> getLembretes(Tozte eHoje) {

        ArrayList<Lembrete> mLembrar = new ArrayList<Lembrete>();

        for (Lembrete eLembrete : mLembretes) {
            if (eLembrete.getTozte().isIgual(eHoje)) {
                mLembrar.add(eLembrete);
            }
        }

        for (LembreteSuperarko eLembrete : mSLembretes) {

            if (eLembrete.getSuperarkos() == eHoje.getSuperarko_Status()) {

                mLembrar.add(new Lembrete(eHoje.getCopia(), eLembrete.getHazde()));

            }

        }


        return mLembrar;
    }

    public boolean temLembretes(Tozte eHoje) {

        boolean tem = false;

        for (Lembrete eLembrete : mLembretes) {
            if (eLembrete.getTozte().isIgual(eHoje)) {
                tem = true;
                break;
            }
        }

        for (LembreteSuperarko eLembrete : mSLembretes) {
            if (eLembrete.getSuperarkos() == eHoje.getSuperarko_Status()) {
                tem = true;
                break;
            }

        }


        return tem;
    }


    public void dispensar(Tozte mHoje, Hazde eAgora) {
        mDispensar = true;
        mDispensadoTozteCom = mHoje;
        mDispensadoHazdeCom = eAgora;
    }

    public boolean foiDispensado() {
        return mDispensar;
    }

    public Hazde getHazdeDispensado() {
        return mDispensadoHazdeCom;
    }

    public Tozte getTozteDispensado() {
        return mDispensadoTozteCom;
    }

    public String quandoFoiDispensado() {
        return mDispensadoTozteCom.getTexto() + " " + mDispensadoHazdeCom.getTexto();
    }

    public boolean estaTocando(Lembrete eLembrete, int mTocar, Tozte mHoje, Hazde mAgora) {

        boolean isTocando = false;

        Tozte mTozteIniciar = eLembrete.getTozte();

        if (mHoje.isMaiorIgualQue(mTozteIniciar)) {

            Hazde mIniciar = eLembrete.getHazde();
            Hazde mFinalizar = eLembrete.getHazde().adicionar_Itta(mTocar);


            boolean deve_alterar = false;

            if (mAgora.isMaiorIgual(mIniciar) && mAgora.isMenor(mFinalizar)) {
                deve_alterar = true;
            }


            if (foiDispensado()) {

                if (mIniciar.isMaiorIgual(mDispensadoHazdeCom)) {
                    if (mAgora.isMaiorIgual(mIniciar) && mAgora.isMenor(mFinalizar)) {
                        mDispensar = false;
                    }
                }

            } else if (deve_alterar) {
                isTocando = true;
            }

        }


        return isTocando;
    }

}

package libs.tronarko.agenda;

import libs.luan.Lista;
import libs.tronarko.Hazde;
import libs.tronarko.Superarkos;
import libs.tronarko.Tozte;


public class Alarme {

    private Lista<Lembrete> mLembretes;
    private Lista<LembreteSuperarko> mSLembretes;


    private boolean mDispensar;

    private Tozte mDispensadoTozteCom;
    private Hazde mDispensadoHazdeCom;

    public static final int EVENTO_PASSADO = -1;
    public static final int EVENTO_TOCANDO = 0;
    public static final int EVENTO_FUTURO = 1;


    public Alarme() {

        mLembretes = new Lista<Lembrete>();
        mSLembretes = new Lista<LembreteSuperarko>();


        mDispensar = false;

        mDispensadoTozteCom = null;
        mDispensadoHazdeCom = null;
    }


    public void marcarSimples(Tozte eTozte, Hazde eHazde) {

        mLembretes.adicionar(new Lembrete(eTozte, eHazde));

    }

    public void marcarSuperarko(Superarkos eSuperarkos, Hazde eHazde) {

        mSLembretes.adicionar(new LembreteSuperarko(eSuperarkos, eHazde));

    }

    public void limparTudo() {
        mLembretes.limpar();
        mSLembretes.limpar();
    }

    public Lista<Lembrete> getLembretes(Tozte eHoje) {

        Lista<Lembrete> mLembrar = new Lista<Lembrete>();

        for (Lembrete eLembrete : mLembretes) {
            if (eLembrete.getTozte().isIgual(eHoje)) {
                mLembrar.adicionar(eLembrete);
            }
        }

        for (LembreteSuperarko eLembrete : mSLembretes) {

            if (eLembrete.getSuperarkos() == eHoje.getSuperarko_Status()) {

                mLembrar.adicionar(new Lembrete(eHoje.getCopia(), eLembrete.getHazde()));

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


    public int getModo(Lembrete eLembrete, int eTocar, Tozte eTozteSelecionado, Tozte eHoje, Hazde eAgora) {

        int ret = EVENTO_PASSADO;

        boolean estaTocando = estaTocando(eLembrete, eTocar, eHoje, eAgora);

        if (estaTocando) {
            ret = EVENTO_TOCANDO;
        } else {
            if (eTozteSelecionado.isMenorQue(eHoje)) {
                ret = EVENTO_FUTURO;
            } else if (eTozteSelecionado.isIgual(eHoje) && eAgora.isMenor(eLembrete.getHazde())) {
                ret = EVENTO_FUTURO;
            }
        }

        return ret;
    }
}

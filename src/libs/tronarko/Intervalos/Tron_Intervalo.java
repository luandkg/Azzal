package libs.tronarko.Intervalos;

import libs.tronarko.Hazde;
import libs.tronarko.Tron;

public class Tron_Intervalo {

    private String mNome;
    private Tron mInicio;
    private Tron mFim;

    public Tron_Intervalo(String eNome, Tron eInicio, Tron eFim) {

        this.mNome = eNome;

        Organizar(eInicio, eFim);

    }

    private void Organizar(Tron eInicio, Tron eFim) {

        if (eInicio.isMenorIgualQue(eFim)) {
            this.mInicio = eInicio;
            this.mFim = eFim;
        } else {
            this.mFim = eInicio;
            this.mInicio = eFim;
        }

    }

    public String getNome() {
        return mNome;
    }

    public Tron getInicio() {
        Organizar(mInicio, mFim);

        return mInicio;
    }

    public Tron getFim() {
        Organizar(mInicio, mFim);

        return mFim;
    }

    public long getTotalEttons() {
        Organizar(mInicio, mFim);

        return mFim.getTotal() - mInicio.getTotal();
    }

    public int getArkos() {

        int mArkos = 0;
        long mTotal = getTotalEttons();
        long UmArko = 1 * 100 * 100;

        while (mTotal >= UmArko) {
            mTotal -= UmArko;
            mArkos += 1;
        }

        return mArkos;
    }

    public int getArkosComFim() {

        return getArkos() + 1;
    }

    public int getSuperarkos() {

        int mArkos = getArkos();
        int mSuperarkos = 0;

        while (mArkos >= 10) {
            mArkos -= 10;
            mSuperarkos += 1;
        }

        return mSuperarkos;
    }

    public String getIntervalo() {

        int mArkos = 0;
        int mIttas = 0;
        int mEttons = 0;

        int mSuperarkos = 0;

        long mTotal = getTotalEttons();
        long UmArko = 1 * 100 * 100;

        while (mTotal >= UmArko) {
            mTotal -= UmArko;
            mArkos += 1;
        }

        while (mTotal >= 100) {
            mTotal -= 100;
            mEttons += 1;
        }

        while (mEttons >= 100) {
            mEttons -= 100;
            mIttas += 1;
        }

        while (mArkos >= 10) {
            mArkos -= 10;
            mSuperarkos += 1;
        }

        return mSuperarkos + " - " + mArkos + ":" + mIttas + ":" + mEttons;

    }

    public int getIntervaloIttas() {

        int mArkos = 0;
        int mIttas = 0;

        int mSuperarkos = 0;

        long mTotal = getTotalEttons();
        long UmArko = 1 * 100 * 100;

        while (mTotal >= UmArko) {
            mTotal -= UmArko;
            mArkos += 1;
        }


        while (mTotal >= 100) {
            mTotal -= 100;
            mIttas += 1;
        }

        while (mArkos >= 10) {
            mArkos -= 10;
            mSuperarkos += 1;
        }

        return (mSuperarkos * 1000) + (mArkos * 100) + mIttas;

    }

    public Hazde getHazde() {

        int mArkos = 0;
        int mIttas = 0;
        int mEttons = 0;

        long mTotal = getTotalEttons();
        long UmArko = 1 * 100 * 100;

        while (mTotal >= UmArko) {
            mTotal -= UmArko;
            mArkos += 1;
        }

        while (mTotal >= 100) {
            mTotal -= 100;
            mEttons += 1;
        }

        while (mEttons >= 100) {
            mEttons -= 100;
            mIttas += 1;
        }

        return new Hazde(mArkos, mIttas, mEttons);

    }

    public String toString() {
        Organizar(mInicio, mFim);

        return mInicio.toString() + " - " + mFim.toString();
    }

    // COMPARADORES

    public boolean MaiorQue(Tron_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() > Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean MenorQue(Tron_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() < Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean Igual(Tron_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() == Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean Diferente(Tron_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() != Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean MaiorIgualQue(Tron_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() >= Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean MenorIgualQue(Tron_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() <= Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

}

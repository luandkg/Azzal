package libs.tronarko.intervalos;

import libs.luan.fmt;
import libs.tronarko.Hazde;

public class Hazde_Intervalo {

    private String mNome;
    private Hazde mInicio;
    private Hazde mFim;

    public Hazde_Intervalo(String eNome, Hazde eInicio, Hazde eFim) {

        this.mNome = eNome;

        organizar(eInicio, eFim);

    }

    private void organizar(Hazde eInicio, Hazde eFim) {

        if (eInicio.isMenorIgual(eFim)) {
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

    public Hazde getInicio() {
        organizar(mInicio, mFim);

        return mInicio;
    }

    public Hazde getFim() {
        organizar(mInicio, mFim);

        return mFim;
    }

    public int getTotalEttons() {
        organizar(mInicio, mFim);

        return (mFim.getTotalEttons() - mInicio.getTotalEttons());
    }

    public boolean Arco_Igual() {
        organizar(mInicio, mFim);

        return mInicio.getArco() == mFim.getArco();
    }

    public int getArkos() {

        int mArkos = 0;
        int mEttons = getTotalEttons();

        int mIttas = 0;

        while (mEttons >= 100) {
            mEttons -= 100;
            mIttas += 1;
        }

        while (mIttas >= 100) {
            mIttas -= 100;
            mArkos += 1;
        }

        return mArkos;

    }

    public String getDiferenca() {

        int mArkos = 0;
        int mEttons = getTotalEttons();

        int mIttas = 0;

        while (mEttons >= 100) {
            mEttons -= 100;
            mIttas += 1;
        }

        while (mIttas >= 100) {
            mIttas -= 100;
            mArkos += 1;
        }

        return mArkos + ":" + mIttas + ":" + mEttons;

    }

    public String getDiferencaZerado() {

        int mArkos = 0;
        int mEttons = getTotalEttons();

        int mIttas = 0;

        while (mEttons >= 100) {
            mEttons -= 100;
            mIttas += 1;
        }

        while (mIttas >= 100) {
            mIttas -= 100;
            mArkos += 1;
        }

        return fmt.zerado(mArkos, 2) + ":" + fmt.zerado(mIttas, 2) + ":" + fmt.zerado(mEttons, 2);

    }

    public String toString() {
        organizar(mInicio, mFim);

        return mInicio.toString() + " - " + mFim.toString();
    }

    // COMPARADORES

    public boolean maiorQue(Hazde_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() > Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean menorQue(Hazde_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() < Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean igual(Hazde_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() == Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean diferente(Hazde_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() != Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean maiorIgualQue(Hazde_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() >= Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean menorIgualQue(Hazde_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() <= Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

}


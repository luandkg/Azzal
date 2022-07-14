package libs.Tronarko.Intervalos;

import libs.Tronarko.Hazde;

public  class Hazde_Intervalo {

    private String mNome;
    private Hazde mInicio;
    private Hazde mFim;

    public Hazde_Intervalo(String eNome, Hazde eInicio, Hazde eFim) {

        this.mNome = eNome;

        Organizar(eInicio, eFim);

    }

    private void Organizar(Hazde eInicio, Hazde eFim) {

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
        Organizar(mInicio, mFim);

        return mInicio;
    }

    public Hazde getFim() {
        Organizar(mInicio, mFim);

        return mFim;
    }

    public int getTotalEttons() {
        Organizar(mInicio, mFim);

        return (mFim.getTotalEttons() - mInicio.getTotalEttons());
    }

    public boolean Arco_Igual() {
        Organizar(mInicio, mFim);

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

    public String toString() {
        Organizar(mInicio, mFim);

        return mInicio.toString() + " - " + mFim.toString();
    }

    // COMPARADORES

    public boolean MaiorQue(Hazde_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() > Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean MenorQue(Hazde_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() < Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean Igual(Hazde_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() == Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean Diferente(Hazde_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() != Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean MaiorIgualQue(Hazde_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() >= Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean MenorIgualQue(Hazde_Intervalo Outro) {
        boolean resposta = false;
        if (this.getTotalEttons() <= Outro.getTotalEttons()) {
            resposta = true;
        }

        return resposta;
    }

}


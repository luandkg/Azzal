package libs.tronarko.intervalos;

import libs.tronarko.Tozte;

public class Tozte_Intervalo {

    private String mNome;
    private Tozte mInicio;
    private Tozte mFim;

    public Tozte_Intervalo(String eNome, Tozte eInicio, Tozte eFim) {

        this.mNome = eNome;

        organizar(eInicio, eFim);

    }

    private void organizar(Tozte eInicio, Tozte eFim) {

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

    public Tozte getInicio() {
        organizar(mInicio, mFim);

        return mInicio;
    }

    public Tozte getFim() {
        organizar(mInicio, mFim);

        return mFim;
    }

    public long getSuperarkos() {
        organizar(mInicio, mFim);

        return (mFim.getSuperarkosTotal() - mInicio.getSuperarkosTotal());
    }

    public long getSuperarkosComFim() {
        organizar(mInicio, mFim);

        return ((mFim.getSuperarkosTotal() - mInicio.getSuperarkosTotal()) + 1);
    }

    public boolean Tronako_Igual() {
        organizar(mInicio, mFim);

        return mInicio.getTronarko() == mFim.getTronarko();
    }

    public boolean Hiperarko_Igual() {
        organizar(mInicio, mFim);

        return mInicio.getHiperarko() == mFim.getHiperarko();
    }

    public boolean TronarkoEHiperarko_Igual() {
        return (Hiperarko_Igual() == true) && (Tronako_Igual() == true);
    }

    public String toString() {
        organizar(mInicio, mFim);

        return mInicio.toString() + " - " + mFim.toString();
    }

    // COMPARADORES

    public boolean maiorQue(Tozte_Intervalo Outro) {
        boolean resposta = false;
        if (this.getSuperarkos() > Outro.getSuperarkos()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean menorQue(Tozte_Intervalo Outro) {
        boolean resposta = false;
        if (this.getSuperarkos() < Outro.getSuperarkos()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean igual(Tozte_Intervalo Outro) {
        boolean resposta = false;
        if (this.getSuperarkos() == Outro.getSuperarkos()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean diferente(Tozte_Intervalo Outro) {
        boolean resposta = false;
        if (this.getSuperarkos() != Outro.getSuperarkos()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean maiorIgualQue(Tozte_Intervalo Outro) {
        boolean resposta = false;
        if (this.getSuperarkos() >= Outro.getSuperarkos()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean menorIgualQue(Tozte_Intervalo Outro) {
        boolean resposta = false;
        if (this.getSuperarkos() <= Outro.getSuperarkos()) {
            resposta = true;
        }

        return resposta;
    }

}

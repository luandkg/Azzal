package libs.tronarko.Intervalos;

import libs.tronarko.Tozte;

public  class Tozte_Intervalo {

    private String mNome;
    private Tozte mInicio;
    private Tozte mFim;

    public Tozte_Intervalo(String eNome, Tozte eInicio, Tozte eFim) {

        this.mNome = eNome;

        Organizar(eInicio, eFim);

    }

    private void Organizar(Tozte eInicio, Tozte eFim) {

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
        Organizar(mInicio, mFim);

        return mInicio;
    }

    public Tozte getFim() {
        Organizar(mInicio, mFim);

        return mFim;
    }

    public long getSuperarkos() {
        Organizar(mInicio, mFim);

        return (mFim.getSuperarkosTotal() - mInicio.getSuperarkosTotal());
    }

    public long getSuperarkosComFim() {
        Organizar(mInicio, mFim);

        return ((mFim.getSuperarkosTotal() - mInicio.getSuperarkosTotal()) + 1);
    }

    public boolean Tronako_Igual() {
        Organizar(mInicio, mFim);

        return mInicio.getTronarko() == mFim.getTronarko();
    }

    public boolean Hiperarko_Igual() {
        Organizar(mInicio, mFim);

        return mInicio.getHiperarko() == mFim.getHiperarko();
    }

    public boolean TronarkoEHiperarko_Igual() {
        return (Hiperarko_Igual() == true) && (Tronako_Igual() == true);
    }

    public String toString() {
        Organizar(mInicio, mFim);

        return mInicio.toString() + " - " + mFim.toString();
    }

    // COMPARADORES

    public boolean MaiorQue(Tozte_Intervalo Outro) {
        boolean resposta = false;
        if (this.getSuperarkos() > Outro.getSuperarkos()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean MenorQue(Tozte_Intervalo Outro) {
        boolean resposta = false;
        if (this.getSuperarkos() < Outro.getSuperarkos()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean Igual(Tozte_Intervalo Outro) {
        boolean resposta = false;
        if (this.getSuperarkos() == Outro.getSuperarkos()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean Diferente(Tozte_Intervalo Outro) {
        boolean resposta = false;
        if (this.getSuperarkos() != Outro.getSuperarkos()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean MaiorIgualQue(Tozte_Intervalo Outro) {
        boolean resposta = false;
        if (this.getSuperarkos() >= Outro.getSuperarkos()) {
            resposta = true;
        }

        return resposta;
    }

    public boolean MenorIgualQue(Tozte_Intervalo Outro) {
        boolean resposta = false;
        if (this.getSuperarkos() <= Outro.getSuperarkos()) {
            resposta = true;
        }

        return resposta;
    }

}

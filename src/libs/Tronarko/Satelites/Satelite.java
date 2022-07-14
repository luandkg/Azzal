package libs.Tronarko.Satelites;

import libs.Tronarko.Tozte;

public class Satelite {

    private String mNome;

    private Tozte mTozteInicial;
    private int mTaxa;
    private String mCor = "";

    public Satelite(String eNome, Tozte eTozteInicial, int eTaxa, String eCor) {

        this.mNome = eNome;

        this.mTaxa = eTaxa;
        this.mCor = eCor;

        mTozteInicial = eTozteInicial;

    }

    public String getNome() {
        return mNome;
    }

    public String getNomeCapitalizado() {
        return String.valueOf(mNome.charAt(0)).toUpperCase() + mNome.substring(1).toLowerCase();
    }

    public int getCrescente() {
        return mTaxa;
    }

    public int getCheia() {
        return mTaxa;
    }

    public int getMinguante() {
        return mTaxa;
    }

    public int getNova() {
        return mTaxa;
    }

    public int getTaxa() {
        return mTaxa;
    }

    public String getCor() {
        return mCor;
    }

    public String getDef() {

        return mNome + " - { CRESCENTE = " + getCrescente() + " CHEIA = " + getCheia() + " MINGUANTE = " + getMinguante()
                + " NOVA = " + getNova() + " } : " + getIntervalo() + " : " + mCor + " - Inicio : "
                + mTozteInicial.toString();

    }

    public int getIntervalo() {
        return 8 * mTaxa;
    }

    public String getCiclo() {

        int F1 = mTaxa;
        int F2 = 2 * mTaxa;
        int F3 = 3 * mTaxa;
        int F4 = 4 * mTaxa;

        return mNome + " - { CRESCENTE = [ 0 - " + (F1 - 1) + " ] CHEIA = [ " + F1 + " - " + (F2 - 1) + " ]"
                + " MINGUANTE = [ " + (F2) + " - " + (F3 - 1) + " ] NOVA = " + (F3) + " - " + (F4 - 1) + " ] } ";

    }

    public long getQuantidade(Tozte TozteC) {

        Tozte Inicio = new Tozte(mTozteInicial.getSuperarko(), mTozteInicial.getHiperarko(), mTozteInicial.getTronarko());

        long ret = 0;

        ret = TozteC.getSuperarkosTotal() - Inicio.getSuperarkosTotal();

        while (ret >= getIntervalo()) {
            ret -= getIntervalo();
        }

        return ret;
    }

    public String getInfo(Tozte TozteC) {

        int F1 = mTaxa;
        int F2 = 2 * mTaxa;
        int F3 = 3 * mTaxa;
        int F4 = 4 * mTaxa;
        int F5 = 5 * mTaxa;
        int F6 = 6 * mTaxa;
        int F7 = 7 * mTaxa;
        int F8 = 8 * mTaxa;

        String ret = "";

        ret += "SATELITE : " + mNome + " \n\n";

        ret += "\t - Tozte : " + TozteC.getTexto() + "\n";
        ret += "\t - Fase Atual : " + getFase((int) getQuantidade(TozteC)) + "\n";
        ret += "\t - Quantidade : " + getQuantidade(TozteC) + "\n";
        ret += "\t - Proxima : " + getProximo(TozteC) + "\n";
        ret += "\t - Proxima Fase : " + getProximaFase(TozteC) + "\n\n";


        ret += "\t - Fase NOVA : [1 a " + F1 + "]\n";
        ret += "\t - Fase MINGUANTE : [" + (F1) + " a " + F2 + "]\n";
        ret += "\t - Fase QUADRO_MINGUANTE : [" + (F2) + " a " + F3 + "]\n";
        ret += "\t - Fase MINGUANTE_GIBOSA : [" + F3 + " a " + F4 + "]\n";
        ret += "\t - Fase CHEIA : [" + F4 + " a " + F5 + "]\n";
        ret += "\t - Fase CRESCENTE_GIBOSA : [" + F5 + " a " + F6 + "]\n";
        ret += "\t - Fase QUADRO_CRESCENTE : [" + F6 + " a " + F7 + "]\n";
        ret += "\t - Fase CRESCENTE : [" + F7 + " a " + F8 + "]\n";

        return ret;

    }

    public int getProximoValor(Tozte TozteC) {
        int t = (int) getQuantidade(TozteC) + getProximo(TozteC) + 1;
        if (t >= getIntervalo()) {
            t -= getIntervalo();
        }
        return t;
    }

    public Fases getProximaFase(Tozte TozteC) {
        return getFase(getProximoValor(TozteC));
    }

    public int getProximo(Tozte TozteC) {
        int ret = 0;

        int aTozteQuantidade = (int) getQuantidade(TozteC);

        int F1 = mTaxa;
        int F2 = 2 * mTaxa;
        int F3 = 3 * mTaxa;
        int F4 = 4 * mTaxa;
        int F5 = 5 * mTaxa;
        int F6 = 6 * mTaxa;
        int F7 = 7 * mTaxa;
        int F8 = 8 * mTaxa;


        if (aTozteQuantidade >= 0 && aTozteQuantidade <= F1) {
            ret = F1 - aTozteQuantidade;
        }

        if (aTozteQuantidade > F1 && aTozteQuantidade <= F2) {
            ret = F2 - aTozteQuantidade;
        }

        if (aTozteQuantidade > F2 && aTozteQuantidade <= F3) {
            ret = F3 - aTozteQuantidade;
        }

        if (aTozteQuantidade > F3 && aTozteQuantidade <= F4) {
            ret = F4 - aTozteQuantidade;
        }

        if (aTozteQuantidade > F4 && aTozteQuantidade <= F5) {
            ret = F5 - aTozteQuantidade;
        }

        if (aTozteQuantidade > F5 && aTozteQuantidade <= F6) {
            ret = F6 - aTozteQuantidade;
        }

        if (aTozteQuantidade > F6 && aTozteQuantidade <= F7) {
            ret = F7 - aTozteQuantidade;
        }

        if (aTozteQuantidade > F7 && aTozteQuantidade <= F8) {
            ret = F8 - aTozteQuantidade;
        }

        return ret;
    }

    public Fases getFase(Tozte TozteC) {
        return getFase((int) getQuantidade(TozteC));
    }

    public Fases getFase(int aTozteQuantidade) {
        Fases ret = Fases.NOVA;

        int F1 = mTaxa;
        int F2 = 2 * mTaxa;
        int F3 = 3 * mTaxa;
        int F4 = 4 * mTaxa;
        int F5 = 5 * mTaxa;
        int F6 = 6 * mTaxa;
        int F7 = 7 * mTaxa;
        int F8 = 8 * mTaxa;

        if (aTozteQuantidade >= 0 && aTozteQuantidade <= F1) {
            ret = Fases.NOVA;
        } else if (aTozteQuantidade > F1 && aTozteQuantidade <= F2) {
            ret = Fases.MINGUANTE;
        } else if (aTozteQuantidade > F2 && aTozteQuantidade <= F3) {
            ret = Fases.QUADRO_MINGUANTE;
        } else if (aTozteQuantidade > F3 && aTozteQuantidade <= F4) {
            ret = Fases.MINGUANTE_GIBOSA;
        } else if (aTozteQuantidade > F4 && aTozteQuantidade <= F5) {
            ret = Fases.CHEIA;
        } else if (aTozteQuantidade > F5 && aTozteQuantidade <= F6) {
            ret = Fases.CRESCENTE_GIBOSA;
        } else if (aTozteQuantidade > F6 && aTozteQuantidade <= F7) {
            ret = Fases.QUADRO_CRESCENTE;
        } else if (aTozteQuantidade > F7 && aTozteQuantidade <= F8) {
            ret = Fases.CRESCENTE;
        }

        return ret;
    }

    public int getFaseIntTozte(Tozte TozteC) {
        return getFaseIntTozte((int) getQuantidade(TozteC));
    }

    public int getFaseIntTozte(int aTozteQuantidade) {
        return getFase(aTozteQuantidade).getValor();
    }

    public void Mostar_Tudo(int eTronarko) {

        Tozte TozteC = new Tozte(1, 1, eTronarko);

        for (int i = 0; i < 500; i++) {

            System.out.println(TozteC.toString() + " :: " + getFase(TozteC).toString());

            TozteC = TozteC.adicionar_Superarko(1);
        }

    }

}

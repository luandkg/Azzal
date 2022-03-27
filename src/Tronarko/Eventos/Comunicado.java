package Tronarko.Eventos;

import Tronarko.Tozte;
import Tronarko.Utils.TozteCor;

import java.awt.*;

public class Comunicado {

    private TozteCor mTozte;
    private String mValor;

    public Comunicado(TozteCor eTozteCor, String eValor) {
        mTozte = eTozteCor;
        mValor = eValor;
    }

    public TozteCor getTozte() {
        return mTozte;
    }

    public String getValor() {
        return mValor;
    }

    public boolean isOK() {
        return getValor().length() > 0;
    }

    public Color getCor() {
        return getTozte().getCor();
    }

    public boolean temVariosSuperarkos() {

        boolean mais = false;

        if (!getValor().contains("Tron") && getTozte().getTozteMax().isMaiorQue(getTozte().getTozteMin())) {
            mais = true;
        }

        return mais;
    }

    public boolean estaDentro(Tozte dentro) {
        boolean ret = false;

        if (dentro.isMaiorIgualQue(getTozte().getTozteMin()) && dentro.isMenorIgualQue(getTozte().getTozteMax())) {
            ret = true;
        }

        return ret;
    }

    public int getDuracao() {
        int ret = 0;

        if (getTozte().getTozteMin().isIgual(getTozte().getTozteMax())) {
            ret = 1;
        } else if (getTozte().getTozteMin().isDiferente(getTozte().getTozteMax())) {
            ret = Momentum.getDistancia(getTozte().getTozteMin(), getTozte().getTozteMax());
        }

        return ret;
    }

    public int getDistanciaDe(Tozte dentro) {
        int ret = Momentum.getDistancia(getTozte().getTozteMin(), dentro);
        return ret;

    }


}

package Tronarko.Eventos;

import Tronarko.TozteCor;

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

    public Color getCor(){
        return getTozte().getCor();
    }
}

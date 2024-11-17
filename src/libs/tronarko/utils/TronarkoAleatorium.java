package libs.tronarko.utils;

import libs.luan.Aleatorio;
import libs.tronarko.Hazde;
import libs.tronarko.Tozte;
import libs.tronarko.Tron;

public class TronarkoAleatorium {

    private Tron mInicio;
    private Tron mFim;


    public TronarkoAleatorium(Tron eInicio, Tron eFim) {
        mInicio = eInicio;
        mFim = eFim;
    }

    public TronarkoAleatorium(Tozte eInicio, Tozte eFim) {
        mInicio = new Tron(new Hazde(0, 0, 0), eInicio);
        mFim = new Tron(new Hazde(9, 99, 99), eFim);
    }

    public Tozte getTozte(){

        long intervalo = mFim.getSuperarkosTotal()-mInicio.getSuperarkosTotal();
        Tron novo  =mInicio.getCopia();
        novo.internalizar_Superarko(Aleatorio.aleatorio((int)intervalo));

        return novo.getTozte();
    }


}

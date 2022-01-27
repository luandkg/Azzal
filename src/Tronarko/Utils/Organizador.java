package Tronarko.Utils;


import Tronarko.Hazde;
import Tronarko.Superarkos;
import Tronarko.Tozte;
import Tronarko.Utils.Lembrete;
import Tronarko.Utils.LembreteSuperarko;

import java.util.ArrayList;

public class Organizador {

    private ArrayList<Lembrete> mLembretes;
    private ArrayList<LembreteSuperarko> mSLembretes;

    public Organizador() {

        mLembretes = new ArrayList<Lembrete>();
        mSLembretes = new ArrayList<LembreteSuperarko>();

    }

    public void marcarSimples(Tozte eTozte, Hazde eHazde) {

        mLembretes.add(new Lembrete(eTozte, eHazde));

    }

    public void marcarSuperarko(Superarkos eSuperarkos, Hazde eHazde) {

        mSLembretes.add(new LembreteSuperarko(eSuperarkos, eHazde));

    }

    public ArrayList<Lembrete> getLembretes(Tozte eHoje) {

        ArrayList<Lembrete> mLembrar = new ArrayList<Lembrete>();

        for (Lembrete eLembrete : mLembretes) {
            if (eLembrete.getTozte().Igual(eHoje)) {
                mLembrar.add(eLembrete);
            }
        }

        for (LembreteSuperarko eLembrete : mSLembretes) {

            if (eLembrete.getSuperarkos()== eHoje.getSuperarko_Status()) {

                mLembrar.add(new Lembrete(eHoje.getCopia(), eLembrete.getHazde()));

            }

        }


        return mLembrar;
    }

}

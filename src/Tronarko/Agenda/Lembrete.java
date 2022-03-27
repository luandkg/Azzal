package Tronarko.Agenda;


import Tronarko.Hazde;
import Tronarko.Tozte;

import java.util.ArrayList;

public class Lembrete {

    private Tozte mTozte;
    private Hazde mHazde;

    public Lembrete(Tozte eTozte, Hazde eHazde) {

        mTozte = eTozte;
        mHazde = eHazde;

    }

    public Tozte getTozte() {
        return mTozte;
    }

    public Hazde getHazde() {
        return mHazde;
    }



}

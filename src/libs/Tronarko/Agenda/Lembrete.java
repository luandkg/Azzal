package libs.Tronarko.Agenda;


import libs.Tronarko.Hazde;
import libs.Tronarko.Tozte;

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

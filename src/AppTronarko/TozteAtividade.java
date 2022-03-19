package AppTronarko;

import Tronarko.Tozte;

public class TozteAtividade {

    private Tozte mTozte;
    private String mAtividade;

    public TozteAtividade(Tozte eTozte, String eAtividade) {
        mTozte = eTozte;
        mAtividade = eAtividade;
    }

    public Tozte getTozte() {
        return mTozte;
    }

    public String getAtividade() {
        return mAtividade;
    }

    public void setAtividade(String eAtividade) {
        mAtividade = eAtividade;
    }
}

package AppTronarko;

import Tronarko.Intervalos.Tozte_Intervalo;
import Tronarko.Tozte;

import java.util.ArrayList;

public class TimeLine {

    private Tozte mInicio;
    private Tozte mFim;
    private ArrayList<TozteAtividade> mAtividades;

    public TimeLine(Tozte eInicio, Tozte eFim) {
        mInicio = eInicio;
        mFim = eFim;
        mAtividades = new ArrayList<TozteAtividade>();
    }

    public int getSuperarkos() {
        return (int) (new Tozte_Intervalo("", mInicio, mFim).getSuperarkos());
    }

    public Tozte getInicio() {
        return mInicio;
    }

    public Tozte getFim() {
        return mFim;
    }

    public void adicionar(Tozte eTozte, String eAtividade) {

        if (eTozte.isMaiorIgualQue(mInicio) && eTozte.isMenorIgualQue(mFim)) {
            mAtividades.add(new TozteAtividade(eTozte, eAtividade));
        }

    }

    public boolean isDentro(Tozte eTozte) {

        boolean ret = false;
        if (eTozte.isMaiorIgualQue(mInicio) && eTozte.isMenorIgualQue(mFim)) {
            ret = true;
        }

        return ret;
    }

    public ArrayList<TozteAtividade> getAtividades() {
        return mAtividades;
    }

    public int getPosicao(Tozte eTozte) {
        int v = 0;

        if (eTozte.isMaiorIgualQue(mInicio) && eTozte.isMenorIgualQue(mFim)) {

            Tozte indo = mInicio.getCopia();

            while (indo.isDiferente(eTozte)) {
                v += 1;
                indo = indo.adicionar_Superarko(1);
            }

        }

        return v;
    }


}

package apps.app_campeonatum;

import libs.tronarko.Superarkos;
import libs.tronarko.Tozte;

public class TempoCorrente {

    private Tozte mTempo;

    public TempoCorrente(int _superarko, int _hiperarko, int _tronarko) {
        mTempo = new Tozte(_superarko, _hiperarko, _tronarko);
    }

    public void adicionarSuperarkos(int superarkos) {
        mTempo = mTempo.adicionar_Superarko(superarkos);
    }

    public Tozte getTozte() {
        return mTempo;
    }

    public String getTextoZerado() {
        return mTempo.getTextoZerado();
    }


    public void proximaOmega() {

        mTempo = mTempo.adicionar_Superarko(1);
        while (mTempo.getSuperarko_Status() != Superarkos.OMEGA) {
            mTempo = mTempo.adicionar_Superarko(1);
        }

    }

    public void proximoSuperarko(Superarkos opcao_a, Superarkos opcao_b, Superarkos opcao_c) {

        while (true) {
            mTempo = mTempo.adicionar_Superarko(1);
            if (mTempo.getSuperarko_Status() == opcao_a) {
                break;
            } else if (mTempo.getSuperarko_Status() == opcao_b) {
                break;
            } else if (mTempo.getSuperarko_Status() == opcao_c) {
                break;
            }
        }
    }


}

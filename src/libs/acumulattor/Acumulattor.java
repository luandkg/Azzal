package libs.acumulattor;

import java.util.ArrayList;

public class Acumulattor {

    private ArrayList<Acumulado> mAcumulados;

    public Acumulattor() {
        mAcumulados = new ArrayList<Acumulado>();
    }

    public void mais(String nome, int mais_v) {
        boolean enc = false;
        for (Acumulado acc : mAcumulados) {
            if (acc.getNome().contentEquals(nome)) {
                acc.mais(mais_v);
                enc = true;
                break;
            }
        }
        if (!enc) {
            Acumulado acn = new Acumulado(nome);
            acn.mais(mais_v);
            mAcumulados.add(acn);
        }
    }

    public void menos(String nome, int menos_v) {
        boolean enc = false;
        for (Acumulado acc : mAcumulados) {
            if (acc.getNome().contentEquals(nome)) {
                acc.menos(menos_v);
                enc = true;
                break;
            }
        }
        if (!enc) {
            Acumulado acn = new Acumulado(nome);
            acn.menos(menos_v);
            mAcumulados.add(acn);
        }
    }


    public int get(String nome) {
        int ret = 0;
        for (Acumulado acc : mAcumulados) {
            if (acc.getNome().contentEquals(nome)) {
                ret = acc.getQuantidade();
                break;
            }
        }
        return ret;
    }
}

package libs.acumulattor;

import libs.luan.Lista;

public class Acumulattor {

    private Lista<Acumulado> mAcumulados;

    public Acumulattor() {
        mAcumulados = new Lista<Acumulado>();
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
            mAcumulados.adicionar(acn);
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
            mAcumulados.adicionar(acn);
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

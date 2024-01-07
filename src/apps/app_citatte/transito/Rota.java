package apps.app_citatte.transito;

import apps.app_citatte.engenharia.AvenidaViaria;
import libs.azzal.geometria.Ponto;
import libs.luan.Lista;

public class Rota {

    private Lista<AvenidaViaria> mAvenidas;
    private Lista<Ponto> mAndando;
    private Lista<Ponto> mCruzamentos;

    private boolean mSucesso;

    public Rota() {

        mAvenidas = new Lista<AvenidaViaria>();
        mAndando = new Lista<Ponto>();
        mCruzamentos = new Lista<Ponto>();
        mSucesso = false;

    }

    public Lista<AvenidaViaria> getAvenidas() {
        return mAvenidas;
    }

    public Lista<Ponto> getAndando() {
        return mAndando;
    }

    public Lista<Ponto> getCruzamentos() {
        return mCruzamentos;
    }

    public void setAvenidas(Lista<AvenidaViaria> eAvenidas) {
        mAvenidas = eAvenidas;
    }

    public void marcarComSucesso() {
        mSucesso = true;
    }

    public boolean teveSucesso() {
        return mSucesso;
    }
}

package AppAttuz.Ferramentas;

import AppAttuz.Camadas.Massas;

public class MassaComNormal {

    private Massas mDados;
    private Normalizador mNormal;

    public MassaComNormal(Massas eDados,Normalizador eNormalizador){
        mDados=eDados;
        mNormal=eNormalizador;
    }

    public Massas getDados(){return mDados;}
    public Normalizador getNormalizado(){return mNormal;}
}

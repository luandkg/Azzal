package apps.app_attuz.Ferramentas;

import apps.app_attuz.Assessorios.Massas;

public class MassaComNormal {

    private Massas mDados;
    private Normalizador mNormal;

    public MassaComNormal(Massas eDados, Normalizador eNormalizador) {
        mDados = eDados;
        mNormal = eNormalizador;
    }

    public Massas getDados() {
        return mDados;
    }

    public Normalizador getNormalizado() {
        return mNormal;
    }

    public void setValor(int x, int y, int valor) {
        mNormal.adicionar(valor);
        mDados.setValor(x, y, valor);
    }
}

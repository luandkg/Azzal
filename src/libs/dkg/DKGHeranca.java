package libs.dkg;

public class DKGHeranca {


    // IMPLEMENTADO 2022.09.30


    private DKGObjeto mObjeto;

    public DKGHeranca() {
        mObjeto = new DKGObjeto("");
    }

    public DKGHeranca(DKGObjeto eObjeto) {
        mObjeto = eObjeto;
    }


    public void def(DKGObjeto eObjeto) {
        mObjeto = eObjeto;
    }


    // METODOS

    public String get(String eNome) {
        return mObjeto.identifique(eNome).getValor();
    }

    public void set(String eNome, String eValor) {
        mObjeto.identifique(eNome).setValor(eValor);
    }


    public int getI32(String eNome) {
        return mObjeto.identifique(eNome).getInteiro(0);
    }

    public void setI32(String eNome, int eValor) {
        mObjeto.identifique(eNome).setInteiro(eValor);
    }

    public long getI64(String eNome) {
        return mObjeto.identifique(eNome).getLong(0);
    }

    public void setI64(String eNome, long eValor) {
        mObjeto.identifique(eNome).setLong(eValor);
    }


    public float getF32(String eNome) {
        return mObjeto.identifique(eNome).getFloat(0);
    }

    public void setF32(String eNome, float eValor) {
        mObjeto.identifique(eNome).setFloat(eValor);
    }

    public double getF64(String eNome) {
        return mObjeto.identifique(eNome).getDouble(0.0);
    }

    public void setF64(String eNome, double eValor) {
        mObjeto.identifique(eNome).setDouble(eValor);
    }
}

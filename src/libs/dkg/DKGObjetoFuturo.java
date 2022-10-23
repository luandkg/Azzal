package libs.dkg;

public class DKGObjetoFuturo {

    public static final int NOVO = 1;
    public static final int EXISTENTE = 2;

    private int mModo;
    private DKGObjeto mObjeto;

    public DKGObjetoFuturo(DKGObjeto eObjeto, int eModo) {
        mObjeto = eObjeto;
        mModo = eModo;
    }

    public int getModo() {
        return mModo;
    }

    public boolean foiCriado() {
        return mModo == NOVO;
    }

    public boolean jaExistia() {
        return mModo == EXISTENTE;
    }

    public DKGObjeto getObjeto() {
        return mObjeto;
    }

}


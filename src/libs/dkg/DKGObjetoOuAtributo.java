package libs.dkg;

public class DKGObjetoOuAtributo {

    public static final int OBJETO = 0;
    public static final int ATRIBUTO = 1;

    private int mTipo;
    private DKGObjeto mObjeto;
    private DKGAtributo mAtributo;

    public DKGObjetoOuAtributo(DKGObjeto eObjeto) {
        mTipo = OBJETO;
        mObjeto = eObjeto;
    }

    public DKGObjetoOuAtributo(DKGAtributo eAtributo) {
        mTipo = ATRIBUTO;
        mAtributo = eAtributo;
    }

    public boolean isObjeto() {
        return mTipo == OBJETO;
    }

    public boolean isAtributo() {
        return mTipo == ATRIBUTO;
    }

    public DKGAtributo getAtributo() {
        return mAtributo;
    }

    public DKGObjeto getObjeto() {
        return mObjeto;
    }

}

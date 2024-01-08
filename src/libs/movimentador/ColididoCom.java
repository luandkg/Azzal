package libs.movimentador;


public class ColididoCom {

    private boolean mColidiu;
    private Object mCom;

    public ColididoCom() {
        mColidiu = false;
        mCom = null;
    }

    public void colidir(Object eObject) {
        mCom = eObject;
        mColidiu = true;
    }

    public void descolidir() {
        mColidiu = false;
        mCom = null;
    }

    public boolean isColidiu() {
        return mColidiu;
    }

    public Object getCom() {
        return mCom;
    }


}

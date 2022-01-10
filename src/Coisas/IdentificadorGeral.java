package Coisas;

public class IdentificadorGeral {

    private int mID;

    public IdentificadorGeral() {
        mID = 0;
    }

    public int registrar() {
        int eID = mID;
        mID += 1;
        return eID;
    }
}

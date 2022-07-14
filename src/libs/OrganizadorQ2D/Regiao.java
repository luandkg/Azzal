package libs.OrganizadorQ2D;

import java.util.ArrayList;

public class Regiao {

    private int mX;
    private int mY;
    private ArrayList<Object> mObjetos;

    public Regiao(int eX, int eY) {
        mX = eX;
        mY = eY;
        mObjetos = new ArrayList<Object>();
    }


    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public ArrayList<Object> getObjetos() {
        return mObjetos;
    }

    public void adicionar(Object eObjeto) {
        mObjetos.add(eObjeto);
    }

    public void remover(Object eObjeto) {
        mObjetos.remove(eObjeto);
    }
}

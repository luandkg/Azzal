package apps.app_atzum.utils;

import libs.azzal.utilitarios.Cor;

public class RegiaoDefinida {

    private int mX;
    private int mY;
    private Cor mCor;
    private int mValor;
    private int mSubValor;

    public RegiaoDefinida(int eX, int eY, Cor eCor, int eValor,int eSubValor){

        mX=eX;
        mY=eY;
        mCor=eCor;
        mValor=eValor;
        mSubValor=eSubValor;

    }

    public int getX(){return mX;}
    public int getY(){return mY;}
    public Cor getCor(){return mCor;}
    public int getValor(){return mValor;}
    public int getSubValor(){return mSubValor;}

}

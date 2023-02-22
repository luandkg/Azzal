package libs.tronarko;

import azzal.utilitarios.Cor;

public class Astro {

    private int mx1;
    private int my1;
    private int mx2;
    private int my2;
    private Cor mCor;

    private int mPasso = 0;
    private int mTaxa = +1;
    private int mMaximo = 0;
    private String mNome;

    private int mPosX;
    private int mPosY;

    public Astro(String eNome, Cor eCor, int ex1, int ey1, int ex2, int ey2,int ePasso,int eTaxa,int eMaximo) {
        mNome=eNome;
        mCor = eCor;
        mx1 = ex1;
        my1 = ey1;
        mx2 = ex2;
        my2 = ey2;
        mPasso = ePasso;
        mTaxa=eTaxa;
        mMaximo=eMaximo;

        mPosX=0;
        mPosY=0;
    }

    public Cor getCor() {
        return mCor;
    }

    public int getTaxa(){return mTaxa;}

    public int getMaximo(){return mMaximo;}

    public int getX1() {
        return mx1;
    }

    public int getY1() {
        return my1;
    }

    public int getX2() {
        return mx2;
    }

    public int getY2() {
        return my2;
    }

    public int getPasso() {
        return mPasso;
    }

    public void setPasso(int ePasso) {
        mPasso = ePasso;
    }

    public void setTaxa(int eTaxa) {
        mTaxa = eTaxa;
    }

    public String getNome(){return mNome;}


    public void setPos(int ex,int ey){
        mPosX=ex;
        mPosY=ey;
    }

    public int getPosX(){return mPosX;}
    public int getPosY(){return mPosY;}

}

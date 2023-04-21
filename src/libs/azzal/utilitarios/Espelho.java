package libs.azzal.utilitarios;

public class Espelho {

    private int mX;
    private int mY;

    private int mLargura;
    private int mAltura;

    public Espelho(int eX,int eY,int eLargura,int eAltura){
        mX=eX;
        mY=eY;

        mLargura=eLargura;
        mAltura=eAltura;
    }

    public int getX(){return mX;}
    public int getY(){return mY;}

    public int getX2(){return mX + mLargura;}
    public int getY2(){return mY + mAltura;}

    public void setX(int eX){mX=eX;}
    public void setY(int eY){mY=eY;}

    public int getLargura(){return mLargura;}
    public int getAltura(){return mAltura;}

    public void setLargura(int eLargura){mLargura=eLargura;}
    public void setAltura(int eAltura){mAltura=eAltura;}

}

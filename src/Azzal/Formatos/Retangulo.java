package Azzal.Formatos;

public class Retangulo {

    private int mX;
    private int mY;

    private int mLargura;
    private int mAltura;

    public Retangulo(){
        mX=0;
        mY=0;

        mLargura=200;
        mAltura=100;

    }

    public Retangulo(int eX,int eY){
        mX=eX;
        mY=eY;

        mLargura=200;
        mAltura=100;
    }


    public Retangulo(int eX,int eY,int eLargura,int eAltura){
        mX=eX;
        mY=eY;

        mLargura=eLargura;
        mAltura=eAltura;
    }


    public int getX(){return mX;}
    public int getY(){return mY;}

    public void setX(int eX){mX=eX;}
    public void setY(int eY){mY=eY;}

}

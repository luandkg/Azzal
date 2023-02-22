package libs.tronarko.utils;

public class AstroLocal {

    private String mNome;

    private int mX;
    private int mY;

    public AstroLocal(String eNome,int eX,int eY){
        mNome=eNome;
        mX=eX;
        mY=eY;
    }

    public String getNome(){return mNome;}
    public int getX(){return mX;}
    public int getY(){return mY;}

    public void setNome(String e){
        mNome=e;
    }

    public void setX(int eX){
        mX=eX;
    }

    public void setY(int eY){
        mY=eY;
    }
}


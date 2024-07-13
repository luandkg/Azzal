package algoritmos.carzane;

import libs.luan.Lista;

public class Ponto {

    private String mNome;
    private int mX;
    private int mY;
    private int mValor;
    private int mTronarko;
    private int mSuperarko;

    private Lista<Movimento> mMovimentos;

    public Ponto(String eNome,int eX,int eY,int eValor,int eTronarko,int eSuperarko){

        mNome=eNome;
        mX=eX;
        mY=eY;
        mValor=eValor;
        mTronarko=eTronarko;
        mSuperarko=eSuperarko;

        mMovimentos=new Lista<Movimento>();
    }


    public String getNome(){return mNome;}
    public int getX(){return mX;}
    public int getY(){return mY;}
    public int getValor(){return mValor;}

    public int getTronarko(){return mTronarko;}
    public int getSuperarko(){return mSuperarko;}


    public void setX(int eX){
        mX=eX;
    }

    public void setY(int eY){
        mY=eY;
    }

    public void setValor(int eValor){
        mValor=eValor;
    }


    public void setSuperarko(int eSuperarko){
        mSuperarko=eSuperarko;
    }

    public void setTronarko(int eTronarko){
        mTronarko=eTronarko;
    }


    public void adicionar_movimento(Movimento eMovimento){
        mMovimentos.adicionar(eMovimento);
    }

    public Lista<Movimento> getMovimentos(){return mMovimentos;}
}

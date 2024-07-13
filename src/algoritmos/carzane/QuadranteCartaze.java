package algoritmos.carzane;

import libs.luan.Lista;

public class QuadranteCartaze {

    private int mX;
    private int mY;

    private Lista<String> mItens;

    public QuadranteCartaze(int x,int y){

        mX=x;
        mY=y;

        mItens = new Lista<String>();

    }

    public int getX(){return mX;}
    public int getY(){return mY;}

    public void limpar(){
        mItens.limpar();
    }

    public void adicionar(String item){
        mItens.adicionar(item);
    }

    public Lista<String> itens(){return mItens;}
}

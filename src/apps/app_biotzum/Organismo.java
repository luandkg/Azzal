package apps.app_biotzum;

import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.luan.Aleatorio;

public class Organismo {

    private int mX;
    private int mY;

    private Cores mCores = new Cores();

    public Organismo(int x,int y){
        mX=x;
        mY=y;
    }

    public void andar(){

        mX+= Aleatorio.aleatorio_entre(-3,3);
        mY+=Aleatorio.aleatorio_entre(-3,3);

        restringirArea();
    }

    private void restringirArea(){
        if(mX>=100){
            mX=99;
        }
        if(mY>=100){
            mY=99;
        }
        if(mX<=0){
            mX=0;
        }
        if(mY<=1){
            mY=2;
        }
    }

    public void atualizar(){
        andar();
    }


    public void render(Renderizador g){
        g.drawRect_Pintado(mX*10,mY*10,10,10,mCores.getVermelho());
    }
}

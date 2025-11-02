package apps.app_biotzum;

import libs.azzal.Cores;
import libs.azzal.Renderizador;

public class Organismo {

    private int mX;
    private int mY;

    private Cores mCores = new Cores();

    public Organismo(int x,int y){
        mX=x;
        mY=y;
    }

    public void andar(){
        mX+=1;
        mY+=1;
    }

    public void atualizar(){
        andar();
    }


    public void render(Renderizador g){
        g.drawRect_Pintado(mX*10,mY*10,10,10,mCores.getVermelho());
    }
}

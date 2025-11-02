package apps.app_biotzum;

import libs.azzal.Cores;
import libs.azzal.Renderizador;

public class Comida {

    private int mPx = 0;
    private int mPy = 0;

    private Cores mCores;

    public Comida(int x, int y) {
        mPx = x;
        mPy = y;
        mCores = new Cores();
    }

    public int getX(){
        return mPx;
    }

    public int getY(){
        return mPy;
    }

    public void render(Renderizador g) {
        g.drawCirculo_Pintado((mPx * 10)+2, (mPy * 10)+2, 3, mCores.getAmarelo());
    }

}

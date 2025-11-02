package apps.app_biotzum;

import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.luan.Aleatorio;

public class Organismo {

    private int mX;
    private int mY;

    private Cores mCores = new Cores();

    public Organismo(int x, int y) {
        mX = x;
        mY = y;
    }

    public void andar() {

        int px = mX+Aleatorio.aleatorio_entre(-3, 3);
        int py = mY+ Aleatorio.aleatorio_entre(-3, 3);

        if(isLocalValido(px,py)){
            mX=px;
            mY=py;
        }
    }

    private boolean isLocalValido(int px,int py) {
        boolean ret = true;
        if (px >= 100) {
            ret = false;
        }
        if (py >= 100) {
            ret = false;
        }
        if (px <= 0) {
            ret = false;
        }
        if (py <= 1) {
            ret = false;
        }
        return ret;
    }

    public void atualizar() {
        andar();
    }


    public void render(Renderizador g) {
        g.drawRect_Pintado(mX * 10, mY * 10, 10, 10, mCores.getVermelho());
    }
}

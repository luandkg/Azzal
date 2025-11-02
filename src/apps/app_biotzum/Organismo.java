package apps.app_biotzum;

import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cronometro;
import libs.luan.Aleatorio;
import libs.luan.Lista;
import libs.luan.Matematica;

public class Organismo {

    final int ESTAGIO_NORMAL = 0;
    final int ESTAGIO_DESCANSANDO = 1;

    private int mX;
    private int mY;

    private Cores mCores = new Cores();
    private int mEnergia = 500;
    private int mEstagio = 0;
    private int mDescansando=0;
    private int mDeveDescansar=0;

    private Cronometro mCron;

    public Organismo(int x, int y) {
        mX = x;
        mY = y;
        mEstagio = ESTAGIO_NORMAL;
        mCron = new Cronometro(500);

    }

    public void andar(Lista<Organismo> outros) {

        int mover_x = Aleatorio.aleatorio_entre(-3, 3);
        int mover_y = Aleatorio.aleatorio_entre(-3, 3);

        int px = mX + mover_x;
        int py = mY + mover_y;

        int gasto_de_movimentacao = (Matematica.modulo(mover_x) * 3) + (Matematica.modulo(mover_y) * 5);

        if (mEnergia >= gasto_de_movimentacao) {
            if (isLocalValido(px, py,outros)) {
                mX = px;
                mY = py;
                mEnergia -= gasto_de_movimentacao;
            }
        }

    }

    private boolean isLocalValido(int px, int py,Lista<Organismo> outros) {
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
        if(ret){
            for(Organismo outro : outros){
                if(outro.mX==px && outro.mY==py){
                    ret=false;
                    break;
                }
            }
        }
        return ret;
    }

    public void atualizar(Lista<Organismo> outros,Lista<Comida> comidas) {

        boolean aguardou = false;

        mCron.esperar();
        if (mCron.foiEsperado()) {
            mCron.zerar();
            aguardou = true;
        }

        if (mEstagio == ESTAGIO_NORMAL) {
            andar(outros);

            for(Comida comida : comidas){
                if(comida.getX()==mX && comida.getY()==mY){
                    comidas.remover(comida);
                    mEnergia+=10_000;
                    break;
                }
            }

            if (mEnergia < 50) {
                mEstagio = ESTAGIO_DESCANSANDO;
                mCron.zerar();
                mDescansando=0;
                mDeveDescansar=Aleatorio.aleatorio_entre(5,10);
            }
        } else if (mEstagio == ESTAGIO_DESCANSANDO) {
            if (aguardou) {
                mDescansando+=1;
                if(mDescansando>=mDeveDescansar){
                    mEnergia=500;
                    mEstagio = ESTAGIO_NORMAL;
                }
            }
        }
    }


    public void render(Renderizador g) {

        if (mEstagio == ESTAGIO_NORMAL) {
            g.drawRect_Pintado(mX * 10, mY * 10, 10, 10, mCores.getVerde());
        }else{
            g.drawRect_Pintado(mX * 10, mY * 10, 10, 10, mCores.getVermelho());
        }
    }
}

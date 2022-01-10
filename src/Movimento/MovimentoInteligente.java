package Movimento;

import AppAzzal.Corpo;

import java.util.Random;

public class MovimentoInteligente implements Movimento {

    private Corpo mCorpo;
    private Random eSorte;

    private int mDirecao;
    private int mRepetir;
    private int mRepetindo;

    public MovimentoInteligente() {

        eSorte = new Random();
        mDirecao = -1;
        mRepetir = 0;
        mRepetindo = 0;
    }

    public void setCorpo(Corpo eCorpo) {
        mCorpo = eCorpo;
    }

    public void mover() {


        if (mDirecao == -1) {

            int v = eSorte.nextInt(100);

            if (v >= 0 && v < 20) {
                mDirecao = 0;
            } else if (v >= 20 && v < 40) {
                mDirecao = 1;
            } else if (v >= 40 && v < 60) {
                mDirecao = 2;
            } else if (v >= 60 && v < 80) {
                mDirecao = 3;
            } else if (v >= 80 && v <= 100) {
                mDirecao = 4;
            }

            mRepetir = eSorte.nextInt(15);
            mRepetindo = 0;

        } else {

            mRepetindo += 1;

        }


        int antes_x = mCorpo.getX();
        int antes_y = mCorpo.getY();

        if (mDirecao==0) {

        } else if (mDirecao==1) {
            mCorpo.setPos(mCorpo.getX() + 1, mCorpo.getY());
        } else if (mDirecao==2) {
            mCorpo.setPos(mCorpo.getX() - 1, mCorpo.getY());
        } else if (mDirecao==3) {
            mCorpo.setPos(mCorpo.getX(), mCorpo.getY() + 1);
        } else if (mDirecao==4) {
            mCorpo.setPos(mCorpo.getX(), mCorpo.getY() - 1);
        }

        if (mCorpo.getX() < mCorpo.getMin().getX()) {
            mCorpo.setX(antes_x);
        }
        if (mCorpo.getY() < mCorpo.getMin().getY()) {
            mCorpo.setX(antes_x);
        }
        if (mCorpo.getX() > mCorpo.getMax().getX()) {
            mCorpo.setY(antes_y);
        }
        if (mCorpo.getY() > mCorpo.getMax().getY()) {
            mCorpo.setY(antes_y);
        }

        if (mRepetindo > mRepetir) {
            mDirecao = -1;
        }

    }

}

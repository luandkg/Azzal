package Movimento;

import java.util.Random;

public class MovimentoVertical implements Movimento {

    private Corpo mCorpo;
    private Random eSorte;

    private int mDirecao;
    private int mRepetir;
    private int mRepetindo;

    public MovimentoVertical() {

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

            if (v >= 0 && v < 33) {
                mDirecao = 0;
            } else if (v >= 33 && v < 66) {
                mDirecao = 1;
            } else if (v >= 66 && v <= 100) {
                mDirecao = 2;
            }

            if (mCorpo.getMax().getY()>0){
                mRepetir = eSorte.nextInt(mCorpo.getMax().getY());
            }

            mRepetindo = 0;

        }

        mRepetindo += 1;
        if (mRepetindo > mRepetir) {
            mDirecao -= 1;
        }

        int antes_x = mCorpo.getX();
        int antes_y = mCorpo.getY();

        if (mDirecao == 0) {

        } else if (mDirecao == 1) {
            mCorpo.setPos(mCorpo.getX(), mCorpo.getY() + 1);
        } else if (mDirecao == 2) {
            mCorpo.setPos(mCorpo.getX(), mCorpo.getY() - 1);
        }

        if (mCorpo.getX() < mCorpo.getMin().getX()) {
            mCorpo.setX(mCorpo.getMin().getX());
            mDirecao = -1;
        }
        if (mCorpo.getY() < mCorpo.getMin().getY()) {
            mCorpo.setX(mCorpo.getMin().getY());
            mDirecao = -1;
        }
        if (mCorpo.getX() > mCorpo.getMax().getX()) {
            mCorpo.setY(mCorpo.getMax().getY());
            mDirecao = -1;
        }
        if (mCorpo.getY() > mCorpo.getMax().getY()) {
            mCorpo.setY(mCorpo.getMax().getY());
            mDirecao = -1;
        }


    }

}

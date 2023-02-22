package libs.movimentador;

import java.util.Random;

public class MovimentoBurro implements Movimento {

    private Corpo mCorpo;
    private Random eSorte;

    public MovimentoBurro() {
        eSorte = new Random();
    }

    public void setCorpo(Corpo eCorpo) {
        mCorpo = eCorpo;
    }

    public void mover() {

        int v = eSorte.nextInt(100);

        int antes_x = mCorpo.getX();
        int antes_y = mCorpo.getY();

        if (v >= 0 && v < 20) {

        } else if (v >= 20 && v < 40) {
            mCorpo.setPos(mCorpo.getX() + 1, mCorpo.getY());
        } else if (v >= 40 && v < 60) {
            mCorpo.setPos(mCorpo.getX() - 1, mCorpo.getY());
        } else if (v >= 60 && v < 80) {
            mCorpo.setPos(mCorpo.getX(), mCorpo.getY() + 1);
        } else if (v >= 80 && v <= 100) {
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

    }

}

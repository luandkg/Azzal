package libs.movimentador;

import java.util.Random;

public class Movettor {

    private int mX;
    private int mY;

    private int vX;
    private int vY;

    private Random mSorte;

    private int mMinimumX;
    private int mMinimumY;

    private int mMaximumX;
    private int mMaximumY;

    private boolean emLinhaX;

    public Movettor() {
        mX = 0;
        mY = 0;
        vX = 0;
        vY = 0;

        mMinimumX = 0;
        mMinimumY = 0;
        mMaximumX = 0;
        mMaximumY = 0;

        mSorte = new Random();
        emLinhaX = false;

    }

    public Movettor(int eX, int eY) {
        mX = eX;
        mY = eY;
        vX = 0;
        vY = 0;

        mMinimumX = 0;
        mMinimumY = 0;
        mMaximumX = 0;
        mMaximumY = 0;
        mSorte = new Random();

    }

    public Movettor(int eX, int eY, int evX, int evY) {
        mX = eX;
        mY = eY;
        vX = evX;
        vY = evY;

        mMinimumX = 0;
        mMinimumY = 0;
        mMaximumX = 0;
        mMaximumY = 0;
        mSorte = new Random();

    }

    public void setMinimo(int eX, int eY) {
        mMinimumX = eX;
        mMinimumY = eY;
    }

    public void setMaximo(int eX, int eY) {
        mMaximumX = eX;
        mMaximumY = eY;
    }

    public void move() {


        mX += vX;



        if (mX < mMinimumX) {
            mX = mMinimumX+100;
            if(vX<0){
                vX = (-1) * vX;
            }
        }


        if (mX > mMaximumX) {
            mX = mMaximumX-100;
            if(vX>0){
                vX = (-1) * vX;
            }
        }


        if (mY < mMinimumY) {
            mY = mMinimumY;
        }

        if (mY > mMaximumY) {
            mY = mMaximumY;
        }
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }
}

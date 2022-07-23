package apps.app_attuz.Mapa;


import azzal.geometria.Ponto;

import java.util.ArrayList;
import java.util.Random;

public class Viajante {

    private int mX;
    private int mY;
    private int mAtitude;
    private int mTempo = 0;
    private ArrayList<Ponto> mRota;
    private int mIndoNaRota;
    private String mIndoPara;


    private int mPensando ;
    private int mIndo ;
    private int mDormindo ;


    public Viajante() {
        mX = 0;
        mY = 0;

        mPensando=1;
        mIndo=1;
        mDormindo=1;

        mAtitude = mDormindo;
        mTempo = 2;
        mRota = new ArrayList<Ponto>();
        mIndoNaRota = 0;
        mIndoPara = "";


    }

    public Viajante(int eX, int eY) {
        mX = eX;
        mY = eY;


        mPensando=1;
        mIndo=1;
        mDormindo=1;

        mAtitude = mDormindo;
        mTempo = 2;
        mRota = new ArrayList<Ponto>();
        mIndoNaRota = 0;
        mIndoPara = "";


    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public void setPos(int eX, int eY) {
        mX = eX;
        mY = eY;
    }

    public int getTempo() {
        return mTempo;
    }


    public int getAtividade() {
        return mAtitude;
    }

    public void mudar() {

        if (mAtitude == mDormindo) {
            mTempo -= 1;
            if (mTempo == 0) {
                mAtitude = mPensando;

                Random eSorte = new Random();
                mTempo = eSorte.nextInt(30) + 1;

            }
        } else if (mAtitude == mPensando) {

            mTempo -= 1;
            if (mTempo == 0) {
                mAtitude = mIndo;
            }

        } else if (mAtitude == mIndo) {
            if (mIndoNaRota >= mRota.size()) {
                dormir();
            } else {
                setPos(mRota.get(mIndoNaRota).getX(), mRota.get(mIndoNaRota).getY());
                avancar();
            }
        }

    }

    public void dormir() {
        Random eSorte = new Random();
        mTempo = eSorte.nextInt(30) + 1;
        mAtitude = mDormindo;
    }

    public boolean estouPensando() {
        return mAtitude == mPensando;
    }

    public boolean estouIndo() {
        return mAtitude == mIndo;
    }

    public void limparTrilha() {
        mRota.clear();
        mIndoNaRota = 0;
    }

    public void adicionarRota(int ex, int ey) {
        mRota.add(new Ponto(ex, ey));
    }

    public void obterRota(ArrayList<Ponto> rota){

        limparTrilha();

        for(Ponto ePonto : rota){
            adicionarRota(ePonto.getX(), ePonto.getY());
        }

    }

    public void avancar() {
        mIndoNaRota += 1;
    }

    public Ponto getEstou() {
        return mRota.get(mIndoNaRota);
    }

    public boolean temMaisRota() {
        return mIndoNaRota < mRota.size();
    }

    public int getRotaTamanho() {
        return mRota.size();
    }

    public int getRealizado() {
        return mIndoNaRota;
    }

    public void setIndoPara(String e) {
        mIndoPara = e;
    }

    public String getIndoPara() {
        return mIndoPara;
    }

    public int getPensando(){return mPensando;}
    public int getIndo(){return mIndo;}
    public int getDormindo(){return mDormindo;}

}

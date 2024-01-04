package apps.app_citatte.cidade_beta;

import apps.app_attuz.Ferramentas.GPS;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.luan.Lista;

public class Avenida {

    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;


    private int mAvenidaID;
    private Lista<Ponto> mPontosA;
    private Lista<Ponto> mPontosB;

    private Lista<Ponto> mPontos;

    private boolean mConectada;

    private int mTipo;

    public Avenida(int eAvenidaID, int eTipo) {
        mTipo = eTipo;
        mAvenidaID = eAvenidaID;
        mPontosA = new Lista<Ponto>();
        mPontosB = new Lista<Ponto>();

        mPontos = new Lista<Ponto>();
        mConectada = false;
    }

    public int getAvenidaID() {
        return mAvenidaID;
    }

    public void adicionar_ponto(Ponto pa, Ponto pb) {
        mPontosA.adicionar(pa);
        mPontosB.adicionar(pb);
    }

    public Lista<Ponto> getPontos() {
        return mPontos;
    }

    public void organizar() {

        mPontos.limpar();

        if (mTipo == HORIZONTAL) {

            int i = 0;
            int o = mPontosA.getQuantidade();
            for (Ponto p : mPontosA) {
                if (i + 1 < o) {
                    for (Ponto px : GPS.criarRotaReta(p.getX(), p.getY() + 2, mPontosA.get(i + 1).getX(), mPontosA.get(i + 1).getY() + 2)) {
                        mPontos.adicionar(px);
                    }
                }
                //  mCidade.drawRect_Pintado(p.getX(), p.getY(), 5, 5, mCores.getAmarelo());
                i += 1;
            }

            i = 0;
            o = mPontosB.getQuantidade();
            for (Ponto p : mPontosB) {
                if (i + 1 < o) {
                    for (Ponto px : GPS.criarRotaReta(p.getX(), p.getY() + 2, mPontosB.get(i + 1).getX(), mPontosB.get(i + 1).getY() + 2)) {
                        mPontos.adicionar(px);
                    }
                }
                //    mCidade.drawRect_Pintado(p.getX(), p.getY(), 5, 5, mCores.getAmarelo());
                i += 1;
            }

        } else if (mTipo == VERTICAL) {


            int i = 0;
            int o = mPontosA.getQuantidade();
            for (Ponto p : mPontosA) {
                if (i + 1 < o) {

                    for (Ponto px : GPS.criarRotaReta(p.getX() + 2, p.getY(), mPontosA.get(i + 1).getX() + 2, mPontosA.get(i + 1).getY())) {
                        mPontos.adicionar(px);
                    }

                }
                //   mCidade.drawRect_Pintado(p.getX(), p.getY(), 5, 5, mCores.getAmarelo());
                i += 1;
            }

            i = 0;
            o = mPontosB.getQuantidade();
            for (Ponto p : mPontosB) {
                if (i + 1 < o) {

                    for (Ponto px : GPS.criarRotaReta(p.getX() + 2, p.getY(), mPontosB.get(i + 1).getX() + 2, mPontosB.get(i + 1).getY())) {
                        mPontos.adicionar(px);
                    }

                }
                //   mCidade.drawRect_Pintado(p.getX(), p.getY(), 5, 5, mCores.getAmarelo());
                i += 1;
            }


        }


    }

    public void draw(Renderizador mCidade, Cor avenida_cor) {

        if (mTipo == HORIZONTAL) {

            for(Ponto px : getPontos()){
                mCidade.drawPixel(px.getX(),px.getY(),avenida_cor);
            }


            int i = 0;
            int o = mPontosA.getQuantidade();
            for (Ponto p : mPontosA) {
                if (i + 1 < o) {
                 //   mCidade.drawLinha(p.getX(), p.getY() + 2, mPontosA.get(i + 1).getX(), mPontosA.get(i + 1).getY() + 2, avenida_cor);
                }
                //  mCidade.drawRect_Pintado(p.getX(), p.getY(), 5, 5, mCores.getAmarelo());
                i += 1;
            }

            i = 0;
            o = mPontosB.getQuantidade();
            for (Ponto p : mPontosB) {
                if (i + 1 < o) {
                 //   mCidade.drawLinha(p.getX(), p.getY() + 2, mPontosB.get(i + 1).getX(), mPontosB.get(i + 1).getY() + 2, avenida_cor);
                }
                //    mCidade.drawRect_Pintado(p.getX(), p.getY(), 5, 5, mCores.getAmarelo());
                i += 1;
            }
        } else if (mTipo == VERTICAL) {
            for(Ponto px : getPontos()){
                mCidade.drawPixel(px.getX(),px.getY(),avenida_cor);
            }

            int i = 0;
            int o = mPontosA.getQuantidade();
            for (Ponto p : mPontosA) {
                if (i + 1 < o) {
                  //  mCidade.drawLinha(p.getX() + 2, p.getY(), mPontosA.get(i + 1).getX() + 2, mPontosA.get(i + 1).getY(), avenida_cor);
                }
                //   mCidade.drawRect_Pintado(p.getX(), p.getY(), 5, 5, mCores.getAmarelo());
                i += 1;
            }

            i = 0;
            o = mPontosB.getQuantidade();
            for (Ponto p : mPontosB) {
                if (i + 1 < o) {
               //     mCidade.drawLinha(p.getX() + 2, p.getY(), mPontosB.get(i + 1).getX() + 2, mPontosB.get(i + 1).getY(), avenida_cor);
                }
                //   mCidade.drawRect_Pintado(p.getX(), p.getY(), 5, 5, mCores.getAmarelo());
                i += 1;
            }


        }


    }

    public boolean temConexaoCom(Avenida av2) {

        boolean tem = false;
        for (Ponto p1 : mPontos) {

            for (Ponto p2 : av2.getPontos()) {
                if (p1.isIgual(p2)) {
                    tem = true;
                    break;
                }
            }

            if (tem) {
                break;
            }

        }

        return tem;
    }


    public boolean isConectada() {
        return mConectada;
    }

    public void setConectada(boolean c) {
        mConectada = c;
    }
}

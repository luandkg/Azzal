package AppAzzal;

import Azzal.Cenarios.Cena;
import Azzal.Formatos.*;
import Azzal.Utils.*;
import Azzal.Renderizador;
import Azzal.Windows;
import Luan.Iterador;
import Luan.Lista;
import Movimento.Movettor;

import java.awt.*;
import java.util.Random;


public class Alpha extends Cena {

    private int movendo = 0;
    private int rodando = 0;
    private int opacidade = 0;

    private TransformadorDeCor TDA;
    private TransformadorDeCor TDB;
    private Paleta mPaleta;

    private Lista<Retangulo> mCaiu;
    private boolean tem;
    private Retangulo mCaindo;
    private int velocidade;
    private Cor mCor;

    private Movettor mMovettor;

    public Alpha() {

        mCaiu = new Lista<Retangulo>();
        tem = false;

        TDA = new TransformadorDeCor(new Cor(76, 175, 80));
        TDA.mudarAzul(20, 10);
        TDA.mudarVermelho(16, 6);
        TDA.mudarVerde(15, 14);


        TDB = new TransformadorDeCor(new Cor(76, 175, 80));
        TDB.mudarAzul(10, -15);
        TDB.mudarVermelho(22, 12);
        TDB.mudarVerde(25, 16);


        mPaleta = new Paleta();
        mPaleta.criar("Alfa", new Cor(120, 50, 60));
        mPaleta.criar("Beta", new Cor(156, 39, 176));
        mPaleta.criar("Gama", new Cor(255, 193, 7));
        mPaleta.criar("Delta", new Cor(200, 160, 40));
        mPaleta.criar("Epsilon", new Cor(244, 67, 54));
        mPaleta.criar("Kapa", new Cor(76, 175, 80));
        mPaleta.criar("Zeta", new Cor(96, 125, 139));
        mPaleta.criar("Iota", new Cor(63, 81, 181));
        mPaleta.criar("Omega", new Cor(255, 235, 59));

        mMovettor = new Movettor(0, 0, 12, 12);
        mMovettor.setMinimo(0, 0);
        mMovettor.setMaximo(10000, 10000);

    }


    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Alpha");
    }

    @Override
    public void update(double dt) {

        TDA.atualizar();
        TDB.atualizar();

        mMovettor.move();

        rodando += 1;

        if (rodando >= 360) {
            rodando = 0;
        }

        opacidade += 1;
        if (opacidade >= 255) {
            opacidade = 0;
        }

        movendo += 3;

        //  if (movendo >= 255) {
        //    movendo = 0;
        //  }

        if (!tem) {
            Random rd = new Random();
            mCaindo = new Retangulo(100 + rd.nextInt(500), 0, 10, 10);
            tem = true;
            velocidade = 1 + rd.nextInt(12);

            if (velocidade >= 0 && velocidade < 3) {
                mCor = mPaleta.getCor("Alfa");
            } else if (velocidade >= 3 && velocidade < 6) {
                mCor = mPaleta.getCor("Gama");
            } else if (velocidade >= 6 && velocidade < 9) {
                mCor = mPaleta.getCor("Kapa");
            } else {
                mCor = mPaleta.getCor("Iota");
            }

            mCor = TDA.getCor();

        } else {


            int vel = velocidade;

            for (int v = 0; v < vel; v++) {

                if (mCaindo.getY() + mCaindo.getAltura() >= 600) {
                    tem = false;
                    mCaiu.adicionar(mCaindo);
                    break;
                } else {


                    Iterador<Retangulo> mi = new Iterador<Retangulo>(mCaiu);
                    boolean continuar = true;

                    for (mi.iniciar(); mi.continuar(); mi.proximo()) {

                        boolean ret = colisao(mCaindo, mi.getValor());


                        if (ret) {
                            continuar = false;
                            break;
                        }

                    }

                    if (continuar) {
                        mCaindo.setY(mCaindo.getY() + 1);
                    } else {
                        tem = false;
                        mCaiu.adicionar(mCaindo);
                        break;
                    }

                }

            }


        }

    }

    public boolean colisao(Retangulo r1, Retangulo r2) {

        if (r1.getX() < r2.getX() + r2.getLargura() &&
                r1.getX() + r1.getLargura() > r2.getX() &&
                r1.getY() < r2.getY() + r2.getAltura() &&
                r1.getY() + r1.getAltura() > r2.getY()) {
            // collision detected!
            return true;
        }


        return false;
    }

    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);

        // mRenderizador.drawQuad(300 + movendo, 200, 100, 100, TDA.getCor());

        // mRenderizador.drawQuad(300 + movendo, 500, 100, 100, TDB.getCor());

        Posicionador ePosicionador = new Posicionador();

        Retangulo Qa = new Retangulo(600, 200, 100, 200);


        mRenderizador.drawRect_Pintado(Qa, mPaleta.getCor("Gama"));

        mRenderizador.drawEsquema_AC(Qa.getRetangulo(), 10, mPaleta.getCor("Gama"));
        mRenderizador.drawEsquema_AC(Qa.getRetangulo(), 20, mPaleta.getCor("Gama"));
        mRenderizador.drawEsquema_BD(Qa.getRetangulo(), 30, mPaleta.getCor("Gama"));
        mRenderizador.drawEsquema_BD(Qa.getRetangulo(), 40, mPaleta.getCor("Gama"));


        Retangulo Qb = ePosicionador.getRetangulo_Centralizado(150, 500, 200, 100);


        // mRenderizador.drawLinha(new Linha(Qb.getCentro(),new Ponto(100,100)), new Cor(100,50,40));


        mRenderizador.drawLinha(new Linha(Qb.getPonto_AB(), new Ponto(100, 100)), new Cor(100, 50, 40));
        mRenderizador.drawLinha(new Linha(Qb.getPonto_BC(), new Ponto(100, 100)), new Cor(100, 50, 40));
        mRenderizador.drawLinha(new Linha(Qb.getPonto_CD(), new Ponto(100, 100)), new Cor(100, 50, 40));
        mRenderizador.drawLinha(new Linha(Qb.getPonto_DA(), new Ponto(100, 100)), new Cor(100, 50, 40));


        mRenderizador.drawLinha(new Linha(Qb.getPonto_BA(), new Ponto(300, 100)), new Cor(100, 80, 100));
        mRenderizador.drawLinha(new Linha(Qb.getPonto_CB(), new Ponto(300, 100)), new Cor(100, 80, 100));
        mRenderizador.drawLinha(new Linha(Qb.getPonto_DC(), new Ponto(300, 100)), new Cor(100, 80, 100));
        mRenderizador.drawLinha(new Linha(Qb.getPonto_AD(), new Ponto(300, 100)), new Cor(100, 80, 100));


        mRenderizador.drawLinha(new Linha(Qb.getPonto_A(), new Ponto(500, 100)), new Cor(100, 100, 40));
        mRenderizador.drawLinha(new Linha(Qb.getPonto_B(), new Ponto(500, 100)), new Cor(100, 100, 40));
        mRenderizador.drawLinha(new Linha(Qb.getPonto_C(), new Ponto(500, 100)), new Cor(100, 100, 40));
        mRenderizador.drawLinha(new Linha(Qb.getPonto_D(), new Ponto(500, 100)), new Cor(100, 100, 40));

        mRenderizador.drawLinha(new Linha(Qb.getCentro(), new Ponto(600, 100)), new Cor(100, 50, 40));


        mRenderizador.drawRect(Qb, mPaleta.getCor("Gama"));

        Quadrado Qc = new Quadrado(Qb.getX2(), Qb.getY2(), 100);

        Qc.setY(Qc.getY2() + 50);


        mRenderizador.drawRect(Qc, mPaleta.getCor("Gama"));

        mRenderizador.drawRect(Qc.getBloquinho_A(10), mPaleta.getCor("Delta"));
        mRenderizador.drawRect(Qc.getBloquinho_B(10), mPaleta.getCor("Delta"));
        mRenderizador.drawRect(Qc.getBloquinho_C(10), mPaleta.getCor("Delta"));
        mRenderizador.drawRect(Qc.getBloquinho_D(10), mPaleta.getCor("Delta"));

        mRenderizador.drawRect(Qc.getBloquinho_Centro(10), mPaleta.getCor("Delta"));

        Triangulo mTA = Qc.getTriangulo_ABC();
        mTA.aumentarX(150);

        mRenderizador.drawTriangulo_Pintado(mTA, mPaleta.getCor("Gama"));

        //  Triangulo rT = new Triangulo(mTA.getM1(),mTA.getM2(),mTA.getM3());


        mRenderizador.drawLinha(mTA.getM1(), mPaleta.getCor("Alfa"));
        mRenderizador.drawLinha(mTA.getM2(), mPaleta.getCor("Alfa"));
        mRenderizador.drawLinha(mTA.getM3(), mPaleta.getCor("Alfa"));


        Triangulo mTB = Qc.getTriangulo_ACD();
        mTB.aumentarX(-150);

        mRenderizador.drawTriangulo_Pintado(mTB, mPaleta.getCor("Kapa"));

        Triangulo mTC = Qc.getTriangulo_ABD();
        mTC.aumentarY(-150);

        mRenderizador.drawTriangulo_Pintado(mTC, mPaleta.getCor("Zeta"));

        Triangulo mTD = Qc.getTriangulo_BCD();
        mTD.aumentarY(150);


        mRenderizador.drawTriangulo_Pintado(mTD, mPaleta.getCor("Epsilon"));
        mRenderizador.drawTriangulo(mTD, mPaleta.getCor("Epsilon"));


        Triangulo TrianguloSeparado = mTD;

        TrianguloSeparado.moverPara(1000, 50);

        mRenderizador.drawTriangulo_Pintado(TrianguloSeparado, mPaleta.getCor("Epsilon"));


        Triangulo mTria = new Triangulo();
        mTria.setA(1200, 200);
        mTria.setB(900, 300);
        mTria.setC(900, 500);

        //System.out.println("Triangulo : " + mTria.getPosicao());

        //mTria.aumentarX(200);

        // System.out.println("Triangulo : " + mTria.getPosicao());

        mRenderizador.drawTriangulo_Pintado(mTria, mPaleta.getCor("Gama"));

        Ponto encontro = new Ponto(1400, 400);

        mRenderizador.drawLinha(new Linha(mTria.getPonto_A(), encontro), mPaleta.getCor("Iota"));
        mRenderizador.drawLinha(new Linha(mTria.getPonto_B(), encontro), mPaleta.getCor("Iota"));
        mRenderizador.drawLinha(new Linha(mTria.getPonto_C(), encontro), mPaleta.getCor("Iota"));


        mRenderizador.drawRect(mTria.getRetangulo(), mPaleta.getCor("Iota"));

        Circulo eCirculo = ePosicionador.getCirculo_Centralizado(1200, 800, 50);
        Retangulo eRetangulo = eCirculo.getRetangulo();


        mRenderizador.drawCirculo_Pintado(eCirculo, mPaleta.getCor("Iota"));
        mRenderizador.drawRect(eRetangulo, mPaleta.getCor("Kapa"));


        mRenderizador.drawLinha(new Linha(eCirculo.getCentro(), eCirculo.getTopo()), mPaleta.getCor("Omega"));
        mRenderizador.drawLinha(new Linha(eCirculo.getCentro(), eCirculo.getBaixo()), mPaleta.getCor("Omega"));
        mRenderizador.drawLinha(new Linha(eCirculo.getCentro(), eCirculo.getEsquerda()), mPaleta.getCor("Omega"));
        mRenderizador.drawLinha(new Linha(eCirculo.getCentro(), eCirculo.getDireita()), mPaleta.getCor("Omega"));

        mRenderizador.drawLinha(new Linha(eCirculo.getTopo(), encontro), mPaleta.getCor("Omega"));
        mRenderizador.drawLinha(new Linha(eCirculo.getBaixo(), encontro), mPaleta.getCor("Omega"));
        mRenderizador.drawLinha(new Linha(eCirculo.getDireita(), encontro), mPaleta.getCor("Omega"));
        mRenderizador.drawLinha(new Linha(eCirculo.getEsquerda(), encontro), mPaleta.getCor("Omega"));


        Circulo o1 = ePosicionador.getCirculo_Centralizado(700, 800, 100);
        mRenderizador.drawCirculo_Pintado(o1, mPaleta.getCor("Delta"));

        Circulo o2 = ePosicionador.getCirculo_Centralizado(700, 800, 50);
        mRenderizador.drawCirculo_Pintado(o2, mPaleta.getCor("Omega"));

        Circulo o3 = ePosicionador.getCirculo_Centralizado(700, 800, 20);
        mRenderizador.drawCirculo_Pintado(o3, mPaleta.getCor("Kapa"));

        Oval o4 = ePosicionador.getOval_Centralizado(100, 100, 10, 20);
        mRenderizador.drawOval_Pintado(o4, mPaleta.getCor("Kapa"));


        // Linha aLinha = new Linha(new Ponto(500, movendo), new Ponto(200, 500));
        // mRenderizador.drawLinha(aLinha, new Cor(100, 100, 40));


        // mRenderizador.drawLinha(Qc.getLinha_BA(), new Cor(100,50,40));

        // mRenderizador.drawLinha(Qc.getLinha_CB(), new Cor(100,50,40));

        //  mRenderizador.drawLinha(Qc.getLinha_DC(), new Cor(100,50,40));

        // mRenderizador.drawLinha(Qc.getLinha_AD(), new Cor(100,50,40));


        // mRenderizador.drawLinha(Qc.getDiagonal_CA(), new Cor(100,50,40));

        //mRenderizador.drawLinha(Qc.getDiagonal_DB(), new Cor(100,50,40));

        //mRenderizador.bloquear_Rect(Qb);


        //  mRenderizador.drawPonto(Qc.getPonto_A(), new Cor(100,50,40));


        // mRenderizador.drawLinha(new Linha(Qc.getPonto_A(),new Ponto(100,100)), new Cor(100,50,40));
        //  mRenderizador.drawLinha(new Linha(Qc.getPonto_B(),new Ponto(100,100)), new Cor(100,50,40));//
        //  mRenderizador.drawLinha(new Linha(Qc.getPonto_C(),new Ponto(100,100)), new Cor(100,50,40));
        // mRenderizador.drawLinha(new Linha(Qc.getPonto_D(),new Ponto(100,100)), new Cor(100,50,40));


        Cor T = new Cor(255, 0, 0);
        T.setAlpha(50);

        // mRenderizador.adicionarLuz(new Ponto(400 + movendo, 450),T , 200);

        Cor R = new Cor(0, 255, 0);
        R.setAlpha(130);

     //   mRenderizador.adicionarLuz(new Ponto(400 + movendo, 600),R , 50);


        // mRenderizador.drawQuad(500 , 500, 100, 100, TDB.getCor());

      //  System.out.println("Op : " + opacidade);

        mRenderizador.setAmbiente(255, 0, 0);
        mRenderizador.setOpacidade(80);
        mRenderizador.iluminar();

        Cor eT1 = new Cor(255, 0, 0);
        eT1.setAlpha(80);

        mRenderizador.blend(mMovettor.getX(), mMovettor.getY(), 100, 100,eT1);

        Cor eT2 = new Cor(0, 255, 0);
        eT2.setAlpha(80);

        mRenderizador.blend(mMovettor.getX()+60, mMovettor.getY()+60, 100, 100,eT2);



    }


}

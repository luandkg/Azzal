package AppAzzal;

import Azzal.Cenarios.Cena;
import Azzal.Formatos.*;
import Azzal.Utils.Cor;
import Azzal.Renderizador;
import Azzal.Utils.Paleta;
import Azzal.Utils.Posicionador;
import Azzal.Utils.TransformadorDeCor;
import Azzal.Windows;

import java.awt.*;


public class Alpha extends Cena {

    private int movendo = 0;
    private int rodando = 0;

    private TransformadorDeCor TDA;
    private TransformadorDeCor TDB;
    private Paleta mPaleta;

    public Alpha() {


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


    }


    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Alpha");
    }

    @Override
    public void update(double dt) {

        TDA.atualizar();
        TDB.atualizar();

        rodando += 1;

        if (rodando >= 360) {
            rodando = 0;
        }

        movendo += 3;

        //  if (movendo >= 255) {
        //    movendo = 0;
        //  }
    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);

        // mRenderizador.drawQuad(300 + movendo, 200, 100, 100, TDA.getCor());

        // mRenderizador.drawQuad(300 + movendo, 500, 100, 100, TDB.getCor());


        Retangulo Qa = new Retangulo(600, 200, 100, 200);


        mRenderizador.drawRect_Pintado(Qa, mPaleta.getCor("Gama"));

        mRenderizador.drawEsquema_AC(Qa.getRetangulo(), 10, mPaleta.getCor("Gama"));
        mRenderizador.drawEsquema_AC(Qa.getRetangulo(), 20, mPaleta.getCor("Gama"));
        mRenderizador.drawEsquema_BD(Qa.getRetangulo(), 30, mPaleta.getCor("Gama"));
        mRenderizador.drawEsquema_BD(Qa.getRetangulo(), 40, mPaleta.getCor("Gama"));

        Posicionador ePosicionador = new Posicionador();

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

        TrianguloSeparado.moverPara(1000,100);

        mRenderizador.drawTriangulo_Pintado(TrianguloSeparado, mPaleta.getCor("Epsilon"));


        Triangulo mTria = new Triangulo();
        mTria.setA(1000,200);
        mTria.setB(800,300);
        mTria.setC(800,400);

        //System.out.println("Triangulo : " + mTria.getPosicao());

        //mTria.aumentarX(200);

       // System.out.println("Triangulo : " + mTria.getPosicao());

        mRenderizador.drawTriangulo_Pintado(mTria, mPaleta.getCor("Gama"));


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
        R.setAlpha(50);

        //mRenderizador.adicionarLuz(new Ponto(400 + movendo, 600),R , 200);


        // mRenderizador.drawQuad(500 , 500, 100, 100, TDB.getCor());


        mRenderizador.iluminar();


    }


}

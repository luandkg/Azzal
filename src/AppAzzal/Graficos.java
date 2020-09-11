package AppAzzal;

import Azzal.Cenarios.Cena;
import Azzal.Formatos.*;
import Azzal.Utils.*;
import Azzal.Renderizador;
import Azzal.Windows;
import Luan.Iterador;
import Luan.Lista;

import java.awt.*;
import java.util.Random;


public class Graficos extends Cena {

  private TransformadorDeCor TDA;
    private TransformadorDeCor TDB;
    private Paleta mPaleta;
    private ZettaBarras mZettaBarras;

    Cronometro mCron;


    public Graficos() {


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

        mZettaBarras = new ZettaBarras();

        mZettaBarras.setCorBarra(new Cor(30,50,80));
        mZettaBarras.setCorFundo(Cor.getRGB(Color.WHITE));

        mZettaBarras.setMaiorDoGrupo(true);
        mZettaBarras.setCorMaiorBarra(new Cor(10,150,160));

        mZettaBarras.setMenorDoGrupo(true);
        mZettaBarras.setCorMenorBarra(new Cor(200,60,150));


        mZettaBarras.nivelar(-100,100);

        mCron = new Cronometro(600);

    }


    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Zetta Graficos - Luan Freitas");
    }

    @Override
    public void update(double dt) {

        TDA.atualizar();
        TDB.atualizar();

        mCron.Esperar();

        if (mCron.Esperado()){

            Random gerador = new Random();

            mZettaBarras.adicionar(100-gerador.nextInt(200));


        }

    }



    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);

        mZettaBarras.onDraw(mRenderizador, 100, 200, 600, 300);

        mZettaBarras.onDrawRect(mRenderizador, 800, 200, 600, 300);

        mZettaBarras.onDrawTower(mRenderizador, 100, 600, 600, 300);

        mZettaBarras.onDrawQuad(mRenderizador, 800, 600, 600, 300);

    }


}

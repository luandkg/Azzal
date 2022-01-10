package OnTerraria;

import Azzal.Cenarios.Cena;
import Azzal.Formatos.Quadrado;
import Azzal.Formatos.Retangulo;
import Azzal.Utils.*;
import Azzal.Renderizador;
import Azzal.Windows;


import java.util.ArrayList;


public class TerrariaCena extends Cena {

    private Retangulo mTerrariaArea;
    private Terraria mTerraria;

    private ArrayList<Localizacao> mPontos;
    private ArrayList<Azzal.Formatos.Ponto> mReferente;
    private ArrayList<Azzal.Formatos.Ponto> mMarcar;

    public TerrariaCena() {

        mTerraria = new Terraria();

        mTerrariaArea = new Retangulo(50, 50, mTerraria.LARGURA, mTerraria.ALTURA);

        mPontos = new ArrayList<Localizacao>();
        mMarcar = new ArrayList<Azzal.Formatos.Ponto>();
        mReferente = new ArrayList<Azzal.Formatos.Ponto>();

        mPontos.add(new Localizacao(new Ponto("-15.8025340"), new Ponto("-47.9041140")));
        mPontos.add(new Localizacao(new Ponto("-15.7490850"), new Ponto("-47.7649330")));

    }


    @Override
    public void iniciar(Windows eWindows) {
        eWindows.setTitle("OnTerraria");
    }


    @Override
    public void update(double dt) {

        mMarcar.clear();
        mReferente.clear();

        for (Localizacao ePonto : mPontos) {

            int la = mTerraria.getLatidude(ePonto.getLatitude());
            int lo = mTerraria.getLongitude(ePonto.getLongitude());

            //System.out.println("Latitude :: " + ePonto.getLatitude().toString() + " :: " + la);
            //System.out.println("Longitude :: " + ePonto.getLongitude().toString() + " :: " + lo);

            mMarcar.add(new Azzal.Formatos.Ponto(la / 10, lo / 10));
            mReferente.add(new Azzal.Formatos.Ponto(la + 50, lo + 50));
        }

    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(new Cor(255, 255, 255));

        mRenderizador.drawRect(mTerrariaArea, new Cor(0, 0, 0));


        for (Azzal.Formatos.Ponto ePonto : mMarcar) {
            mRenderizador.drawRect_Pintado(new Quadrado(50 + (ePonto.getX() * 10), 50 + (ePonto.getY() * 10), 10), new Cor(255, 0, 0));
        }




        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 60; y++) {
                mRenderizador.drawRect(new Quadrado(50 + (x * 10), 50 + (y * 10), 10), new Cor(0, 0, 0));
            }
        }

        for (Azzal.Formatos.Ponto ePonto : mReferente) {
            mRenderizador.drawRect_Pintado(new Quadrado(ePonto.getX(), ePonto.getY(), 5), new Cor(0, 255, 0));
        }
        
    }


}

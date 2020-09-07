package AppAzzal;

import Azzal.Cenarios.Cena;
import Azzal.Formatos.Ponto;
import Azzal.Utils.Cor;
import Azzal.Renderizador;
import Azzal.Utils.Paleta;
import Azzal.Utils.TransformadorDeCor;
import Azzal.Windows;

import java.awt.*;


public class Alpha extends Cena {

    private int movendo = 0;
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
        mPaleta.criar("Alfa",new Cor(120,50,60));

    }


    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Alpha");
    }

    @Override
    public void update(double dt) {

        TDA.atualizar();
        TDB.atualizar();


    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);


        // mRenderizador.drawQuad(300 + movendo, 200, 100, 100, TDA.getCor());

        // mRenderizador.drawQuad(300 + movendo, 500, 100, 100, TDB.getCor());


        mRenderizador.drawEsquema(200, 500, 100, 100, 5, TDA.getCor());

        for (int l = 0; l < 100; l++) {
            mRenderizador.drawPonto(new Ponto(500 + movendo, 500 + l), TDB.getCor());
            mRenderizador.drawPonto(new Ponto(501 + movendo, 500 + l), TDB.getCor());
            mRenderizador.drawPonto(new Ponto(502 + movendo, 500 + l), TDB.getCor());
        }

        mRenderizador.drawRect(800, 500, 100, 100, mPaleta.getCor("Alfa"));

        // mRenderizador.drawLinha(600-20 ,700,0,100, TDA.getCor());

        // mRenderizador.drawLinha(600-20 ,700,100,0, TDB.getCor());

        //  mRenderizador.drawLinha(700 ,800+20,0,-100, TDA.getCor());

        //  mRenderizador.drawLinha(700 ,800+20,-100,0, TDB.getCor());


        // mRenderizador.drawLinha(700 ,800+20,-100,10, TDB.getCor());


        movendo += 1;


    }


}

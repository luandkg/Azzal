package apps.app_azzal;

import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.utilitarios.Cor;

import java.awt.*;
import java.util.Random;


public class Chiado extends Cena {


    private Windows mWindows;

    public Chiado() {

    }


    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Chicado - libs.Luan Freitas");
        mWindows = eWindows;

    }

    @Override
    public void update(double dt) {


    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);
        Random eSorte = new Random();

        for (int x = 0; x < mWindows.getLargura(); x++) {
            for (int y = 0; y < mWindows.getAltura(); y++) {
                mRenderizador.drawPixel(x, y, new Cor(eSorte.nextInt(255), eSorte.nextInt(255), eSorte.nextInt(255)));
            }
        }

    }


}

package apps.app_misturador_de_cores;

import libs.azzal.cenarios.Cena;
import libs.azzal.geometria.Quadrado;
import libs.azzal.utilitarios.*;
import libs.azzal.Renderizador;
import libs.azzal.Windows;


import java.awt.*;
import java.util.ArrayList;


public class MisturadorDeCores extends Cena {


    public MisturadorDeCores() {


    }


    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Misturador de Cores");


    }


    @Override
    public void update(double dt) {


    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);

        Cor eVermelho = new Cor(255, 0, 0);
        Cor eVerde = new Cor(0, 255, 0);
        Cor eAzul = new Cor(0, 0, 255);
        Cor eAmarelo = new Cor(255, 255, 0);
        Cor eRoxo = new Cor(75, 0, 130);
        Cor eMagenta = new Cor(255, 0, 255);
        Cor eLaranja = new Cor(255, 165, 0);
        Cor eCinza = new Cor(211, 211, 211);
        Cor eMarrom = new Cor(150, 75, 0);

        ArrayList<Cor> eCores = new ArrayList<Cor>();
        eCores.add(eVermelho);
        eCores.add(eVerde);
        eCores.add(eAzul);
        eCores.add(eAmarelo);
        eCores.add(eRoxo);
        eCores.add(eMagenta);
        eCores.add(eLaranja);
        eCores.add(eCinza);
        eCores.add(eMarrom);

        int eX1 = 150;
        int eY1 = 150;

        int descendo = 0;

        for (Cor eCor : eCores) {
            mRenderizador.drawRect_Pintado(new Quadrado(eX1, eY1 + descendo, 25), eCor);
            descendo += 50;
        }

        int indo = 50;
        int eX2 = 150;
        int eY2 = 100;


        for (Cor eCor : eCores) {
            mRenderizador.drawRect_Pintado(new Quadrado(eX2 + indo, eY2, 25), eCor);
            indo += 50;
        }


        indo = 50;
        descendo = 50;

        for (Cor Cor1 : eCores) {

            indo = 50;

            for (Cor Cor2 : eCores) {

                Cor eCor3 = new Cor(getMist(Cor1.getRed(), Cor2.getRed()), getMist(Cor1.getGreen(), Cor2.getGreen()), getMist(Cor1.getBlue(), Cor2.getBlue()));

                mRenderizador.drawRect_Pintado(new Quadrado(eX2 + indo, eY2 + descendo, 25), eCor3);

                indo += 50;

            }
            descendo += 50;
        }


    }

    public int getMist(int a, int b) {

        int s = (a + b)/2;


        return s;
    }



}

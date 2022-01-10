package Azzal;

import Azzal.Cenarios.Cena;
import Azzal.Mouse;
import Azzal.Utils.*;
import Azzal.Renderizador;
import Azzal.Windows;
import Griff.Griph;
import Griff.Griphattor;
import Griff.Griphizz;


import java.awt.*;
import java.util.Random;


public class CenaModelo extends Cena {


    public CenaModelo() {


    }


    @Override
    public void iniciar(Windows eWindows) {
        eWindows.setTitle("Cena Modelo");
    }


    @Override
    public void update(double dt) {


    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(new Cor(255, 255, 255));


    }


}

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
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class AppGlobal extends Cena {


    private TransicionadorDeCena mTransicionador;


    @Override
    public void iniciar(Windows eWindows) {

        mTransicionador = new TransicionadorDeCena(eWindows);

        mTransicionador.adicionarCena(new Alpha());
        mTransicionador.adicionarCena(new AppTronarko.AppTronarko());
        mTransicionador.adicionarCena(new Arch.Arch());
        mTransicionador.adicionarCena(new AppArquivos.AppVideo());
        mTransicionador.adicionarCena(new AppArquivos.AppImagem());
        mTransicionador.adicionarCena(new AppArquivos.AppAnimacao());

        mTransicionador.iniciarCenas();


    }

    @Override
    public void update(double dt) {

        mTransicionador.update(dt);

    }


    @Override
    public void draw(Renderizador mRenderizador) {
        mTransicionador.draw(mRenderizador);
    }


}

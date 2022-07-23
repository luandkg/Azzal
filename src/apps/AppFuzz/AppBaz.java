package apps.AppFuzz;


import azzal.cenarios.Cena;
import azzal.Renderizador;
import azzal.Windows;
import libs.FuzzerUI.Fuzzer;
import libs.FuzzerUI.Menu;

import java.awt.*;
import java.util.ArrayList;


public class AppBaz extends Cena {

    private Fuzzer mFuzzer;
    private ArrayList<libs.FuzzerUI.Menu> menu_principal;


    @Override
    public void iniciar(Windows eWindows) {


        mFuzzer = new Fuzzer();

        menu_principal = new ArrayList<libs.FuzzerUI.Menu>();

        libs.FuzzerUI.Menu SLT_A = mFuzzer.onMenu(menu_principal,400, 50, 140, 60, "TIPO A");
        libs.FuzzerUI.Menu SLT_B = mFuzzer.onMenu(menu_principal,400 + (1 * 160), 50, 140, 60, "TIPO B");
        libs.FuzzerUI.Menu SLT_C = mFuzzer.onMenu(menu_principal,400 + (2 * 160), 50, 140, 60, "TIPO C");
        Menu SLT_D = mFuzzer.onMenu(menu_principal,400 + (3 * 160), 50, 140, 60, "TIPO D");


        mFuzzer.onBotao(80, 300, 150, 30, "FUNC A");
        mFuzzer.onBotao(80, 350, 150, 30, "FUNC B");
        mFuzzer.onBotao(80, 400, 150, 30, "FUNC C");
        mFuzzer.onBotao(80, 450, 150, 30, "FUNC D");
        mFuzzer.onBotao(80, 500, 150, 30, "FUNC E");
        mFuzzer.onBotao(80, 550, 150, 30, "FUNC F");

        SLT_A.setValor(true);

    }


    @Override
    public void update(double dt) {

        mFuzzer.update(dt, getWindows().getMouse().getX(), getWindows().getMouse().getY(), getWindows().getMouse().isPressed());

        getWindows().getMouse().liberar();

    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);
        mRenderizador.drawRect_Pintado(0, 0, mRenderizador.getLargura(), mRenderizador.getAltura(), mFuzzer.getFundo());


        mFuzzer.setRenderizador(mRenderizador);


        mFuzzer.onDraw(mRenderizador);


    }


}


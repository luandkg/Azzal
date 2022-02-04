package AppFuzz;


import Azzal.Cenarios.Cena;
import Azzal.Renderizador;
import Azzal.Windows;
import java.awt.*;


public class AppBaz extends Cena {


    private Fuzzer mFuzzer;

    @Override
    public void iniciar(Windows eWindows) {


        mFuzzer = new Fuzzer();

        Seletor SLT_A = mFuzzer.onSeletor(400, 50, 140, 60, "TIPO A");
        Seletor SLT_B = mFuzzer.onSeletor(400 + (1 * 160), 50, 140, 60, "TIPO B");
        Seletor SLT_C = mFuzzer.onSeletor(400 + (2 * 160), 50, 140, 60, "TIPO C");
        Seletor SLT_D = mFuzzer.onSeletor(400 + (3 * 160), 50, 140, 60, "TIPO D");


        mFuzzer.onBotao(80, 300, 150, 30, "FUNC A");
        mFuzzer.onBotao(80, 350, 150, 30, "FUNC B");
        mFuzzer.onBotao(80, 400, 150, 30, "FUNC C");
        mFuzzer.onBotao(80, 450, 150, 30, "FUNC D");
        mFuzzer.onBotao(80, 500, 150, 30, "FUNC E");
        mFuzzer.onBotao(80, 550, 150, 30, "FUNC F");


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


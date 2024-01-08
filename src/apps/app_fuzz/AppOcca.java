package apps.app_fuzz;

import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.fuzzer.Fuzzer;
import libs.fuzzer.ItemAcao;
import libs.fuzzer.Listagem;

import java.awt.*;


public class AppOcca extends Cena {


    private Listagem mListagem;
    private Fuzzer mFuzzer;

    @Override
    public void iniciar(Windows eWindows) {


        mFuzzer = new Fuzzer();

        mListagem = new Listagem(100, 200);

        mListagem.setNome("MODALIDADES");
        mListagem.setMaximo(10);

        mListagem.getItens().add(new ItemAcao("Corrida", true));
        mListagem.getItens().add(new ItemAcao("Danca", false));
        mListagem.getItens().add(new ItemAcao("Hit", true));
        mListagem.getItens().add(new ItemAcao("Musculação", true));
        mListagem.getItens().add(new ItemAcao("Aerobico", true));
        mListagem.getItens().add(new ItemAcao("Natação", false));
        mListagem.getItens().add(new ItemAcao("Canto", false));
        mListagem.getItens().add(new ItemAcao("Balé", false));
        mListagem.getItens().add(new ItemAcao("Reforço", true));


        mFuzzer.onListagem(mListagem);


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


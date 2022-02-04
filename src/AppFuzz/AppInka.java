package AppFuzz;


import Azzal.Cenarios.Cena;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Azzal.Windows;
import java.awt.*;


public class AppInka extends Cena {



    private Listagem mListagem;

    private Fuzzer mFuzzer;

    @Override
    public void iniciar(Windows eWindows) {

        mFuzzer = new Fuzzer();


        mListagem = new Listagem(100, 600);

        mListagem.setNome("MODALIDADES");
        mListagem.setMaximo(2);

        mListagem.getItens().add(new ItemAcao("Corrida", true));
        mListagem.getItens().add(new ItemAcao("Danca", false));
        mListagem.getItens().add(new ItemAcao("Hit", true));
        mListagem.getItens().add(new ItemAcao("Musculação", true));
        mListagem.getItens().add(new ItemAcao("Aerobico", true));
        mListagem.getItens().add(new ItemAcao("Natação", false));
        mListagem.getItens().add(new ItemAcao("Canto", false));
        mListagem.getItens().add(new ItemAcao("Balé", false));
        mListagem.getItens().add(new ItemAcao("Reforço", true));


        mFuzzer.onBotao(80, 300, 150, 30, "SOMAR");
        mFuzzer.onBotao(80, 350, 150, 30, "SUBTRAIR");
        mFuzzer.onBotao(80, 400, 150, 30, "MULTIPLICAR");

        Seletor SLT_A = mFuzzer.onSeletor(600, 600, 140, 60, "TIPO A");
        Seletor SLT_B = mFuzzer.onSeletor(750, 600, 140, 60, "TIPO B");
        Seletor SLT_C = mFuzzer.onSeletor(600, 685, 140, 60, "TIPO C");
        Seletor SLT_D = mFuzzer.onSeletor(750, 685, 140, 60, "TIPO D");


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



        mFuzzer.pequeno_escreva(600, 400, "[QUADRO DE DADOS]");
        mFuzzer.normal_escreva(600, 450, "[QUADRO DE DADOS]");


        mFuzzer.onDraw(mRenderizador);

        onCaixa(mRenderizador, 1000, 200, 500, 500);


    }

    public void onCaixa(Renderizador mRenderizador, int px, int py, int largura, int altura) {

        Cor eCor = new Cor(255, 255, 255);
        Cor sCor = new Cor(200, 100, 0);

        mRenderizador.drawLinha(px, py, px + largura, py, eCor); // Linha Superior
        mRenderizador.drawLinha(px, py + altura, px + largura, py + altura, eCor); // Linha Inferior
        mRenderizador.drawLinha(px + largura, py, px + largura, py + altura, eCor); // Coluna Direita


        // ALTURA MIN 100
        int recuo_altura = 200;
        int recuo_largura = 50;
        int recuo_afastamento = 50;

        mRenderizador.drawLinha(px, py, px, py + recuo_afastamento, eCor); // Coluna Esquerda
        mRenderizador.drawLinha(px, py + recuo_afastamento+recuo_altura, px, py +recuo_altura +(altura - recuo_altura), eCor); // Coluna Esquerda

        mRenderizador.drawLinha(px, py+50, px + recuo_largura, py+50, eCor); // Linha Superior Recuo
        mRenderizador.drawLinha(px, py+50+recuo_altura, px + recuo_largura, py+50+recuo_altura, eCor); // Linha Inferior Recuo
        mRenderizador.drawLinha(px + recuo_largura, py+50, px + recuo_largura, py+50+recuo_altura, eCor); // Coluna Direita Recuo

        mRenderizador.drawRect_Pintado(px+5, py+recuo_afastamento + 5, recuo_largura-10,recuo_altura-10, sCor);

    }

}


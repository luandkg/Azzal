package libs.futuristico_ui;

import libs.azzal.Renderizador;
import libs.luan.Lista;

public class CaixaFuturistica {

    private TemaFuturistico mTemaFuturistico;
    private Lista<BotaoFuturistico> mBotoes;

    private int mPX;
    private int mPY;

    private int botao_pos_x;
    private int botao_pos_y;

    private int lado = 0;

    public CaixaFuturistica(TemaFuturistico eTemaFuturistico, int px, int py) {
        mTemaFuturistico = eTemaFuturistico;
        mBotoes = new Lista<>();
        mPX = px;
        mPY = py;

        botao_pos_x = mPX;
        botao_pos_y = mPY;
    }

    public void criarBotao(String titulo, String descricao, String info) {


        mBotoes.adicionar(new BotaoFuturistico(mTemaFuturistico, botao_pos_x, botao_pos_y, titulo, descricao, info));

        lado+=1;
        botao_pos_x+=450;

        if(lado==3){
            lado=0;
            botao_pos_x=mPX;
            botao_pos_y +=80;
        }

    }

    public void update(int px, int py) {

        for (BotaoFuturistico botao : mBotoes) {
            botao.update(px, py);
        }

    }

    public void draw(Renderizador g) {

        for (BotaoFuturistico botao : mBotoes) {
            botao.draw(g);
        }


    }
}

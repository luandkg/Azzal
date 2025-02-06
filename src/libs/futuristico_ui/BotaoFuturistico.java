package libs.futuristico_ui;

import libs.azzal.Renderizador;
import libs.azzal.geometria.Retangulo;
import libs.azzal.utilitarios.Cor;

public class BotaoFuturistico {

    private TemaFuturistico mTemaFuturistico;

    private int mPX;
    private int mPY;

    private String mTitulo;
    private String mDescricao;
    private String mInfo;

    private int mEstagio;

    private final int ESTAGIO_NORMAL = 0;
    private final int ESTAGIO_ACIMA = 1;

    private Retangulo mArea;


    public BotaoFuturistico(TemaFuturistico eTemaFuturistico, int px, int py, String titulo, String descricao, String info) {

        mTemaFuturistico = eTemaFuturistico;

        mPX = px;
        mPY = py;

        mTitulo = titulo;
        mDescricao = descricao;
        mInfo = info;

        mEstagio = ESTAGIO_NORMAL;

        mArea = new Retangulo(mPX, mPY, 400, 60);

    }


    public void update(int px, int py) {

        mEstagio = ESTAGIO_NORMAL;

        if (mArea.isDentro(px, py)) {
            mEstagio = ESTAGIO_ACIMA;
        }

    }

    public void draw(Renderizador g) {

        g.drawRect_Border(mArea, 3, Cor.getHexCor("#9CCC65"));

        if (mEstagio == ESTAGIO_ACIMA) {
            g.drawRect_Pintado(mArea, Cor.getHexCor("#E8F5E9"));
            g.drawRect_Border(mArea, 3, Cor.getHexCor("#9CCC65"));
        }

        g.drawRect_Border(new Retangulo(mPX + 20, mPY + 10, 30, 40), 2, Cor.getHexCor("#9CCC65"));
        g.drawRect_Border(new Retangulo(mPX + 20, mPY + 10, 30, 10), 2, Cor.getHexCor("#9CCC65"));
        g.drawRect_Pintado(new Retangulo(mPX + 30, mPY + 40, 10, 5), Cor.getHexCor("#9CCC65"));

        mTemaFuturistico.ESCRITOR_TITULO_MEDIO.escreva(mPX + 70, mPY + 5, mTitulo);
        mTemaFuturistico.ESCRITOR_COMUM_PEQUENO.escreva(mPX + 70, mPY + 35, mDescricao);

        mTemaFuturistico.ESCRITOR_TITULO_PEQUENO.escreva(mPX + 400 - (mTemaFuturistico.ESCRITOR_TITULO_PEQUENO.getLarguraDe(mInfo) + 10), mPY + 40, mInfo);


    }


}

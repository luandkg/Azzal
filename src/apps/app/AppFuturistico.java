package apps.app;

import libs.azzal.AzzalUnico;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.utilitarios.Cor;
import libs.futuristico_ui.BotaoFuturistico;
import libs.futuristico_ui.CaixaFuturistica;
import libs.futuristico_ui.TemaFuturistico;
import libs.luan.Aleatorio;

public class AppFuturistico extends Cena {

    public TemaFuturistico mTemaFuturistico;

    private CaixaFuturistica mCaixaFuturistica;


    @Override
    public void iniciar(Windows eWindows) {

        mTemaFuturistico = new TemaFuturistico();
        mCaixaFuturistica = new CaixaFuturistica(mTemaFuturistico, 200, 100);


        int pos_x = 200;

        for (int l = 1; l <= 20; l++) {
                mCaixaFuturistica.criarBotao("CAIXA - " + l, "NOME : BOTÃO SIMPLES - " + l, Aleatorio.aleatorio_entre(100, 500) + "KB");
        }


    }

    @Override
    public void update(double dt) {

        int px = getWindows().getMouse().getX();
        int py = getWindows().getMouse().getY();


        mCaixaFuturistica.update(px, py);


    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(Cor.getHexCor("#004D40"));

        mTemaFuturistico.draw(g);

        mCaixaFuturistica.draw(g);

    }


    public static void INIT() {

        AzzalUnico.unico("App Futuristico", 1800, 900, new AppFuturistico());

    }
}

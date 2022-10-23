package apps.app_arquivos;

import azzal.utilitarios.Cronometro;
import libs.Arquivos.AN;
import azzal.cenarios.Cena;
import azzal.Renderizador;
import azzal.utilitarios.Cor;
import azzal.Windows;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;
import mockui.Interface.BotaoCor;
import mockui.Interface.Clicavel;

import java.awt.*;


public class AppAnimacao extends Cena {

    private Windows mWindows;

    private Fonte mEscritor;
    private Cronometro mCron;

    BotaoCor BTN_P1;
    BotaoCor BTN_P2;

    Clicavel mClicavel;

    String eArquivoAN = "/home/luan/Imagens/quadum.an";


    private boolean temAberto = false;
    private int i;
    private AN mAN;

    public void abrir() {

        mAN = new AN();
        mAN.abrir(eArquivoAN);

        i = 0;
        mCron = new Cronometro(mAN.getChrono());

        temAberto = true;

    }


    @Override
    public void iniciar(Windows eWindows) {
        eWindows.setTitle("Visualizador AN");

        mWindows = eWindows;

        mEscritor = new FonteRunTime(Cor.getRGB(Color.BLACK), 11);

        mClicavel = new Clicavel();

        BTN_P1 = mClicavel.criarBotaoCor(new BotaoCor(400, 50, 50, 50, new Cor(26, 188, 156)));
        BTN_P2 = mClicavel.criarBotaoCor(new BotaoCor(500, 50, 50, 50, new Cor(26, 188, 156)));


        temAberto = false;

        i = 0;
        mCron = new Cronometro(200);

        abrir();


    }


    @Override
    public void update(double dt) {

        mClicavel.update(dt, mWindows.getMouse().getX(), mWindows.getMouse().getY(), mWindows.getMouse().isPressed());

        if (mAN.getQuantidade() > 0) {

            mCron.esperar();

            if (mCron.foiEsperado()) {

                if (temAberto){
                    i += 1;
                    if (i >= mAN.getQuantidade()) {
                        i = 0;
                    }
                }

            }

            if (mClicavel.getClicado()) {

                int px = (int) mWindows.getMouse().getX();
                int py = (int) mWindows.getMouse().getY();


                if (BTN_P1.getClicado(px, py)) {

                    // abrir();
                } else if (BTN_P2.getClicado(px, py)) {

                    //abrir();
                }

            }

        }


    }


    @Override
    public void draw(Renderizador r) {

        r.limpar(new Cor(255, 255, 255));

        mEscritor.setRenderizador(r);


        mClicavel.onDraw(r);

        mEscritor.escreva(20, 80,  "LARGURA : " + mAN.getLargura());
        mEscritor.escreva(20, 100, "ALTURA  : " + mAN.getAltura());
        mEscritor.escreva(20, 120, "TEMPO   : " + mAN.getChrono());
        mEscritor.escreva(20, 140, "QUADRO  : " + i + " de " + (mAN.getQuantidade() - 1));

        if (temAberto) {

            r.drawImagem(200, 200, mAN.getImagens().getValor(i));

        }


    }

}
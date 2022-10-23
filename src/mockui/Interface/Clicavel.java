package mockui.Interface;

import azzal.Renderizador;
import azzal.utilitarios.Cronometro;

import java.util.ArrayList;

public class Clicavel {

    private Cronometro mTempo;

    private boolean clicavel = false;
    private boolean mClicado = false;

    private ArrayList<BotaoCor> mAcionadores;
    private ArrayList<BotaoCor> mDesenhadores;

    public Clicavel() {

        mTempo = new Cronometro(200);
        mAcionadores = new ArrayList<BotaoCor>();
        mDesenhadores = new ArrayList<BotaoCor>();

    }

    public BotaoCor criarBotaoCor(BotaoCor eBotaoCor) {
        mAcionadores.add(eBotaoCor);
        mDesenhadores.add(0,eBotaoCor);
        return eBotaoCor;
    }

    public BotaoCor criarBotaoCorDesenharAcima(BotaoCor eBotaoCor) {
        mAcionadores.add(eBotaoCor);
        mDesenhadores.add(eBotaoCor);
        return eBotaoCor;
    }
    public void update(double dt, int px, int py, boolean ePrecionado) {

        mClicado = false;

        mTempo.esperar();

        //   System.out.println("Pode..." + mTempo.get() + " :: " + mTempo.getFim()  + " ->> " + mTempo.foiEsperado());

        if (mTempo.foiEsperado()) {
            mTempo.zerar();
            clicavel = true;
        }

        if (clicavel) {
            if (ePrecionado) {
                mTempo.zerar();
                clicavel = false;
                mClicado = true;
                //   System.out.println("Cliquei...");
            }
        }

        if (mClicado) {

            for (BotaoCor eBotao : mAcionadores) {
                if (eBotao.temVariacao()) {
                    eBotao.setCor(eBotao.getCorNormal());
                }
            }

            for (BotaoCor eBotao : mAcionadores) {
                if (eBotao.getClicado(px, py)) {

                    if (eBotao.temVariacao()) {
                        eBotao.setCor(eBotao.getCorPressionado());
                    }

                    if (eBotao.temAcao()) {
                        eBotao.clicar();
                        break;
                    }

                }
            }

        }

        if (!ePrecionado) {
            for (BotaoCor eBotao : mAcionadores) {
                if (eBotao.temVariacao()) {
                    eBotao.setCor(eBotao.getCorNormal());
                }
            }
        }
    }

    public void onDraw(Renderizador g) {
        for (BotaoCor eBotao : mDesenhadores) {
            eBotao.draw(g);
        }
    }

    public boolean getClicado() {
        return mClicado;
    }
}

package UI.Interface;

import Azzal.Cronometro;
import Azzal.Renderizador;

import java.util.ArrayList;

public class Clicavel {

    private Cronometro mTempo;

    private boolean clicavel = false;
    private boolean mClicado = false;

    private ArrayList<BotaoCor> mAcionadores;

    public Clicavel() {

        mTempo = new Cronometro(200);
        mAcionadores = new ArrayList<BotaoCor>();

    }

    public BotaoCor criarBotaoCor(BotaoCor eBotaoCor) {
        mAcionadores.add(eBotaoCor);
        return eBotaoCor;
    }


    public void update(double dt, int px, int py, boolean ePrecionado) {

        mClicado = false;

        mTempo.atualizar();

     //   System.out.println("Pode..." + mTempo.get() + " :: " + mTempo.getFim()  + " ->> " + mTempo.foiEsperado());

        if (mTempo.esperado()) {
            clicavel = true;
        }

        if (clicavel) {
            if (ePrecionado == true) {
                mTempo.zerar();
                clicavel = false;
                mClicado = true;
                System.out.println("Cliquei...");
            }
        }

        if (mClicado) {

            for (BotaoCor eBotao : mAcionadores) {
                if (eBotao.getClicado(px, py)) {
                    if (eBotao.temAcao()) {
                        eBotao.clicar();
                    }
                }
            }


        }

    }

    public void onDraw(Renderizador g) {
        for (BotaoCor eBotao : mAcionadores) {
            eBotao.draw(g);
        }
    }

    public boolean getClicado() {
        return mClicado;
    }
}

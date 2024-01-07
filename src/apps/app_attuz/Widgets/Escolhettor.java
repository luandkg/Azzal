package apps.app_attuz.Widgets;

import apps.app_attuz.Assessorios.Escala;
import apps.app_attuz.Assessorios.Nivelador;
import apps.app_letrum.Fonte;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;

import java.awt.*;

public class Escolhettor {

    private Nivelador mNivelador;
    private Escala mCores;

    private int px;
    private int py;

    public Escolhettor(int ex, int ey, Nivelador eNivelador, Clicavel mClicavel, Escala eCores) {

        px = ex;
        py = ey;

        mNivelador = eNivelador;
        mCores = eCores;

        BotaoCor BTN_APAGAR = mClicavel.criarBotaoCor(new BotaoCor(px, py, 50, 50, Cor.getRGB(new Color(100, 100, 100))));
        BTN_APAGAR.setAcao(mNivelador.get(0));

        int e = 1;
        int u = 0;

        for (int i = 1; i <= 10; i++) {

            BotaoCor BTN_N2 = mClicavel.criarBotaoCor(new BotaoCor(px + (e * 40) + 50, py + (u * 30), 20, 20, Cor.getRGB(new Color(mCores.get(i)))));
            BTN_N2.setAcao(mNivelador.get(i));

            e += 1;

            if (e > 5) {
                e = 1;
                u += 1;
            }
        }

    }


    public void drawSelecionado(Renderizador r, Fonte escritor) {

        Cor sel_cor = new Cor(100, 100, 100);
        Cor cor_branco = new Cor(255, 255, 255);

        if (mNivelador.getNivel() > 0) {
            sel_cor = Cor.getRGB(new Color(mCores.get(mNivelador.getNivel())));
        }

        r.drawRect_Pintado(px + 400, py + 10, 50, 50, sel_cor);
        r.drawRect_Pintado(px + 410, py + 20, 30, 30, cor_branco);

        escritor.escreva(px + 420, py + 30, "" + mNivelador.getNivel());


    }
}

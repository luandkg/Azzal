package apps.app_attuz;

import apps.app_attuz.Assessorios.Escala;
import apps.app_attuz.Assessorios.Nivelador;
import azzal.Renderizador;
import azzal.Utils.Cor;
import apps.appLetrum.Fonte;
import azzal_ui.Interface.BotaoCor;
import azzal_ui.Interface.Clicavel;

import java.awt.*;

public class Escolhettor {

    private Nivelador mNivelador;
    private Escala mCores;

    public Escolhettor(Nivelador eNivelador, Clicavel mClicavel, Escala eCores) {

        mNivelador=eNivelador;
        mCores=eCores;

        BotaoCor BTN_APAGAR = mClicavel.criarBotaoCor(new BotaoCor(720, 50, 50, 50, Cor.getRGB(new Color(100, 100, 100))));
        BTN_APAGAR.setAcao(mNivelador.get(0));

        int e = 1;
        int u = 0;

        for (int i = 1; i <= 10; i++) {

            BotaoCor BTN_N2 = mClicavel.criarBotaoCor(new BotaoCor(750 + (e * 40), 50 + (u * 30), 20, 20, Cor.getRGB(new Color(mCores.get(i)))));
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

        if (mNivelador.getNivel() > 0) {
            sel_cor = Cor.getRGB(new Color(mCores.get(mNivelador.getNivel())));
        }

        r.drawRect_Pintado(1000, 50, 50, 50, sel_cor);
        r.drawRect_Pintado(1010, 60, 30, 30, new Cor(255, 255, 255));

        escritor.escreva(1015, 65, "" + mNivelador.getNivel());


    }
}

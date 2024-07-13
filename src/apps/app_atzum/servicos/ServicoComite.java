package apps.app_atzum.servicos;

import apps.app_atzum.AtzumCreator;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.imagem.Imagem;

import java.awt.image.BufferedImage;

public class ServicoComite {

    public static void INIT() {

        BufferedImage mapa = AtzumCreator.GET_MAPA_RELEVO_TERRA();
        Renderizador render = new Renderizador(mapa);

        Cores mCores = new Cores();

        int CAIXA_LARGURA = render.getLargura() / 10;
        int CAIXA_ALTURA = render.getAltura() / 10;

        for (int x = 0; x < 10; x++) {
            render.drawLinha(x * CAIXA_LARGURA, 0, x * CAIXA_LARGURA, render.getAltura(), mCores.getBranco());
        }

        for (int y = 0; y < 10; y++) {
            render.drawLinha(0, y * CAIXA_ALTURA, render.getLargura(), y * CAIXA_ALTURA, mCores.getBranco());
        }


        Fonte ESCRITOR_NORMAL = new FonteRunTime(mCores.getVerde(), 30);
        ESCRITOR_NORMAL.setRenderizador(render);

        Fonte ESCRITOR_NORMAL_AZUL = new FonteRunTime(mCores.getAzul(), 30);
        ESCRITOR_NORMAL_AZUL.setRenderizador(render);

        Fonte ESCRITOR_NORMAL_VERMELHO = new FonteRunTime(mCores.getVermelho(), 30);
        ESCRITOR_NORMAL_VERMELHO.setRenderizador(render);

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {

                int indice = (y * 10) + x;

                ESCRITOR_NORMAL.escreva(x * CAIXA_LARGURA, y * CAIXA_ALTURA, String.valueOf(indice));

                if (y == 0) {
                    ESCRITOR_NORMAL_AZUL.escreva(x * CAIXA_LARGURA, (y * CAIXA_ALTURA) + 40, "ESQ");
                } else if (y == 1) {
                    ESCRITOR_NORMAL_VERMELHO.escreva(x * CAIXA_LARGURA, (y * CAIXA_ALTURA) + 40, "DIR");
                }


            }
        }


        Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("temperatura_verao.png"));

    }

}

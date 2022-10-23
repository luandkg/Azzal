package apps.app_attuz.Servicos;

import apps.app_attuz.Arkazz.Arkazz;
import apps.app_attuz.Assessorios.Massas;
import apps.app_attuz.Assessorios.MassasDados;
import apps.app_attuz.Regiao.Regiao;
import libs.Imaginador.ImageUtils;
import libs.Servittor.Servico;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Territorios extends Servico {

    private String LOCAL;

    public Territorios(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {


        Arkazz eArkazz = new Arkazz();

        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        for (Regiao regiao : eArkazz.getRegioes()) {

            BufferedImage mapa = ImageUtils.getImagem(LOCAL + "build/politicamente.png");
            BufferedImage mapa_da_regiao = ImageUtils.getImagem(LOCAL + "build/politicamente.png");


            int largura = mapa.getWidth();
            int altura = mapa.getHeight();

            int cor_selecionada = regiao.getCor().getValor();

            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {

                    if (tectonica.isTerra(x, y)) {

                        int c = mapa.getRGB(x, y);

                        if (c == cor_selecionada) {
                            mapa_da_regiao.setRGB(x, y, cor_selecionada);
                        } else {
                            mapa_da_regiao.setRGB(x, y, Color.BLACK.getRGB());
                        }

                    }


                }
            }

            ImageUtils.exportar(mapa_da_regiao, LOCAL + "regioes/" + regiao.getNome() + ".png");

            println("Regiao " + regiao.getNome() + " -- " + regiao.getCor().toString());

        }


    }
}

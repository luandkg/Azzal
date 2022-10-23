package apps.app_attuz.Localizador;

import apps.app_attuz.Ferramentas.Local;
import apps.app_attuz.Widgets.NomesEspecificos;
import azzal.ComplexoRender;
import azzal.Cores;
import azzal.Renderizador;
import azzal.utilitarios.Cor;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DroneCamera {

    private int quadro_x = 200;
    private int quadro_y = 240;

    private BufferedImage img;
    private Renderizador g;
    private NomesEspecificos mNomear;
    private Fonte micro;

    public DroneCamera(Cores mCores) {

        img = new BufferedImage(quadro_x, quadro_y, BufferedImage.TYPE_INT_RGB);
        g = new Renderizador(img);

        micro = new FonteRunTime(Cor.getRGB(Color.BLACK), 7);
        mNomear = new NomesEspecificos(mCores);


    }

    public BufferedImage onGravar(BufferedImage mapa, int x, int y, int eu_x, int eu_y, ArrayList<Local> mLocais) {

        int height = mapa.getHeight();
        int width = mapa.getWidth();

        int ix = x;
        int ox = x + quadro_x;

        int mesmo_x = eu_x - x;
        int mesmo_y = eu_y - y;

        int raio = 100;
        int xCenter = quadro_x / 2;
        int yCenter = quadro_y / 2;

        for (int i = 0; i < quadro_x; i++) {

            int iy = y;
            int oy = y + quadro_y;

            for (int j = 0; j < quadro_y; j++) {

                img.setRGB(i, j, Color.WHITE.getRGB());

                if (ix >= 0 && ix < width && ix < ox && iy >= 0 && iy < height && iy < oy) {

                    int quadrado_x = (xCenter - i) * (xCenter - i);
                    int quadrado_y = (yCenter - j) * (yCenter - j);

                   // if (Math.sqrt(quadrado_x + quadrado_y) <= raio) {
                        img.setRGB(i, j, mapa.getRGB(ix, iy));
                  //  }

                }

                iy += 1;

            }

            ix += 1;
        }

        droneOrganizar(x, y, img, mLocais);

        ComplexoRender.sinalizar(g, mesmo_x - 3, mesmo_y - 3, 6, new Cor(255, 255, 255), new Cor(255, 0, 0));
        ComplexoRender.sinalizar(g, mesmo_x - 4, mesmo_y - 4, 8, new Cor(255, 255, 255), new Cor(255, 0, 0));

        return img;

    }

    public void droneOrganizar(int ox, int oy, BufferedImage eImagemDrone, ArrayList<Local> mLocais) {

        Renderizador gg = new Renderizador(eImagemDrone);


        int raio = 100;
        int xCenter = quadro_x / 2;
        int yCenter = quadro_y / 2;


        int l = 0;
        for (Local ePonto : mLocais) {

            int rx = (ePonto.getX() * 2) - ox;
            int ry = (ePonto.getY() * 2) - oy;


            int quadrado_x = (xCenter - rx) * (xCenter - rx);
            int quadrado_y = (yCenter - ry) * (yCenter - ry);

            if (Math.sqrt(quadrado_x + quadrado_y) <= raio) {

                gg.drawRect_Pintado(rx, ry, 5, 5, Cor.getRGB(Color.green));

                mNomear.nomearDireto(gg, micro, l, ePonto.getNome(), rx, ry, 0, 0);

            }

            l += 1;
        }

        //  ImageUtils.exportar(copia, LOCAL + "territorio.png");

    }


}

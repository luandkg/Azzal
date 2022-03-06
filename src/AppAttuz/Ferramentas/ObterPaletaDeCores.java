package AppAttuz.Ferramentas;

import AppAttuz.Assessorios.Unicidade;
import Imaginador.ImageUtils;

import java.awt.image.BufferedImage;

public class ObterPaletaDeCores {

    public static void get(String arquivo, String arqpaleta) {

        BufferedImage mapa = ImageUtils.getImagem(arquivo);

        int mLargura = mapa.getWidth();
        int mAltura = mapa.getHeight();


        Unicidade unico = new Unicidade();

        for (int y = 0; y < mAltura; y++) {
            for (int x = 0; x < mLargura; x++) {

                int real = mapa.getRGB(x, y);
                unico.em(real);

            }
        }

        unico.listar();

        int xmax = unico.getTodos().size() * 100;

        BufferedImage copia = new BufferedImage(xmax, 100, BufferedImage.TYPE_INT_RGB);


        for (int y = 0; y < 100; y++) {

            int i = 0;
            int t = 0;

            for (int x = 0; x < xmax; x++) {

                if (t == 100) {
                    t = 0;
                    i += 1;
                }

                if (i < unico.getTodos().size()) {
                    copia.setRGB(x, y, Integer.parseInt(unico.getTodos().get(i)));
                }

                t += 1;

            }
        }

        ImageUtils.exportar(copia, arqpaleta);

    }
}

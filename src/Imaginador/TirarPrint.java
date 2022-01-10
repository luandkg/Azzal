package Imaginador;

import Azzal.Windows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TirarPrint {

    public static void print(Windows eWindows, String eArquivo){

        BufferedImage sc = new BufferedImage(eWindows.getLargura(), eWindows.getAltura(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = sc.createGraphics();
        bGr.drawImage(eWindows.getImagem(), 0, 0, eWindows.getLargura(), eWindows.getAltura(), null);
        bGr.dispose();

        try {
            ImageIO.write(sc, "png", new File(eArquivo));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

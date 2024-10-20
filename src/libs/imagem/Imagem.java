package libs.imagem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Imagem {

    public static byte[] IMAGEM_TO_BYTES(BufferedImage imagem){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(imagem, "png", baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return baos.toByteArray();
    }

    public static BufferedImage getImagem(String eArquivo) {
        BufferedImage IMG_MOSTRANDO = null;

        try {
            IMG_MOSTRANDO = ImageIO.read(new File(eArquivo));
        } catch (IOException e) {
        }

        return IMG_MOSTRANDO;
    }

    public static BufferedImage GET_IMAGEM(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void exportar(BufferedImage eImage, String eArquivo) {
        try {
            ImageIO.write(eImage, "PNG", new File(eArquivo));
        } catch (IOException e) {
        }
    }

    public static BufferedImage getCopia(BufferedImage eImagem) {

        int largura = eImagem.getWidth();
        int altura = eImagem.getHeight();

        BufferedImage copia = new BufferedImage(eImagem.getWidth(), eImagem.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                copia.setRGB(x, y, eImagem.getRGB(x, y));
            }
        }

        return copia;
    }

    public static BufferedImage criarEmBranco(int largura, int altura) {


        BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                imagem.setRGB(x, y, Color.WHITE.getRGB());
            }
        }

        return imagem;
    }



    public static BufferedImage girar(BufferedImage eImagem) {

        int largura = eImagem.getWidth();
        int altura = eImagem.getHeight();

        BufferedImage copia = new BufferedImage(eImagem.getHeight(), eImagem.getWidth(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                copia.setRGB(y, x, eImagem.getRGB(x, y));
            }
        }

        return copia;
    }

    public static BufferedImage espelhar_verticalmente(BufferedImage eImagem) {

        int largura = eImagem.getWidth();
        int altura = eImagem.getHeight();

        BufferedImage copia = new BufferedImage(eImagem.getWidth(), eImagem.getHeight(), BufferedImage.TYPE_INT_RGB);

        int y2 = altura-1;

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                copia.setRGB(x, y2, eImagem.getRGB(x, y));
            }

            y2-=1;
        }

        return copia;
    }

    public static BufferedImage espelhar_horizontalmente(BufferedImage eImagem) {

        int largura = eImagem.getWidth();
        int altura = eImagem.getHeight();

        BufferedImage copia = new BufferedImage(eImagem.getWidth(), eImagem.getHeight(), BufferedImage.TYPE_INT_RGB);


        for (int y = 0; y < altura; y++) {
            int x2 = largura-1;

            for (int x = 0; x < largura; x++) {
                copia.setRGB(x2, y, eImagem.getRGB(x, y));
                x2-=1;
            }


        }

        return copia;
    }


    public static BufferedImage GET_IMAGEM_POR_PIXEL_RGB(String eArquivo) {
        BufferedImage IMG_MOSTRANDO = null;

        try {
            IMG_MOSTRANDO = ImageIO.read(new File(eArquivo));
        } catch (IOException e) {
        }


        BufferedImage copia = new BufferedImage(IMG_MOSTRANDO.getWidth(), IMG_MOSTRANDO.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < IMG_MOSTRANDO.getHeight(); y++) {
            for (int x = 0; x < IMG_MOSTRANDO.getWidth(); x++) {
                copia.setRGB(x, y, IMG_MOSTRANDO.getRGB(x, y));
            }
        }


        return copia;
    }

    public static BufferedImage GET_IMAGEM_POR_PIXEL_ARGB(String eArquivo) {
        BufferedImage IMG_MOSTRANDO = null;

        try {
            IMG_MOSTRANDO = ImageIO.read(new File(eArquivo));
        } catch (IOException e) {
        }


        BufferedImage copia = new BufferedImage(IMG_MOSTRANDO.getWidth(), IMG_MOSTRANDO.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < IMG_MOSTRANDO.getHeight(); y++) {
            int x2 = IMG_MOSTRANDO.getWidth()-1;

            for (int x = 0; x < IMG_MOSTRANDO.getWidth(); x++) {
                copia.setRGB(x2, y, IMG_MOSTRANDO.getRGB(x, y));
                x2-=1;
            }


        }


        return copia;
    }

    public static boolean VERIFICAR_IGUALDADE(BufferedImage mIMG_01, BufferedImage mIMG_02) {

        int m1l = mIMG_01.getWidth();
        int m1a = mIMG_01.getHeight();

        int m2l = mIMG_02.getWidth();
        int m2a = mIMG_02.getHeight();


        boolean ret = true;

        if (m1l == m2l && m1a == m2a) {
            //System.out.println("Comparavel : Sim");

            // Pegar Linhas
            for (int aqy = 0; aqy < m1a; aqy++) {

                for (int aqx = 0; aqx < m1l; aqx++) {

                    int pixel1 = mIMG_01.getRGB(aqx, aqy);
                    int pixel2 = mIMG_02.getRGB(aqx, aqy);

                    if (pixel1 != pixel2) {
                        ret = false;
                        break;
                    }

                }

                if (!ret) {
                    break;
                }

            }

        } else {
            ret = false;
        }


        return ret;
    }

    public static BufferedImage redimensionador(BufferedImage bufferedImage, int escala) {
        int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

        BufferedImage resizedImage = new BufferedImage(bufferedImage.getWidth() / escala, bufferedImage.getHeight() / escala, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth() / escala, bufferedImage.getHeight() / escala, null);
        g.dispose();

        return resizedImage;
    }

    public static BufferedImage redimensionador(BufferedImage bufferedImage, int largura,int altura) {
        int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

        BufferedImage resizedImage = new BufferedImage(largura, altura, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(bufferedImage, 0, 0, largura, altura, null);
        g.dispose();

        return resizedImage;
    }

}

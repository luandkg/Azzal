package apps.app_tronarko;

import libs.imagem.Efeitos;
import libs.imagem.Imagem;

import java.awt.image.BufferedImage;

public class Satelatizador {

    private BufferedImage IMG_0;
    private BufferedImage IMG_1;
    private BufferedImage IMG_2;
    private BufferedImage IMG_3;
    private BufferedImage IMG_4;
    private BufferedImage IMG_5;
    private BufferedImage IMG_6;
    private BufferedImage IMG_7;

    public Satelatizador(String eNome) {

        IMG_0 = Efeitos.reduzirComAlfa(Imagem.getImagem("res/lua/" + eNome + "/0.png"), 32, 32);
        IMG_1 = Efeitos.reduzirComAlfa(Imagem.getImagem("res/lua/" + eNome + "/1.png"), 32, 32);
        IMG_2 = Efeitos.reduzirComAlfa(Imagem.getImagem("res/lua/" + eNome + "/2.png"), 32, 32);
        IMG_3 = Efeitos.reduzirComAlfa(Imagem.getImagem("res/lua/" + eNome + "/3.png"), 32, 32);
        IMG_4 = Efeitos.reduzirComAlfa(Imagem.getImagem("res/lua/" + eNome + "/4.png"), 32, 32);
        IMG_5 = Efeitos.reduzirComAlfa(Imagem.getImagem("res/lua/" + eNome + "/5.png"), 32, 32);
        IMG_6 = Efeitos.reduzirComAlfa(Imagem.getImagem("res/lua/" + eNome + "/6.png"), 32, 32);
        IMG_7 = Efeitos.reduzirComAlfa(Imagem.getImagem("res/lua/" + eNome + "/7.png"), 32, 32);

    }


    public BufferedImage get(int v) {

        BufferedImage imagem = null;

        switch (v) {
            case 0: {
                imagem = IMG_0;
                break;
            }
            case 1: {
                imagem = IMG_1;
                break;
            }
            case 2: {
                imagem = IMG_2;
                break;
            }
            case 3: {
                imagem = IMG_3;
                break;
            }
            case 4: {
                imagem = IMG_4;
                break;
            }
            case 5: {
                imagem = IMG_5;
                break;
            }
            case 6: {
                imagem = IMG_6;
                break;
            }
            case 7: {
                imagem = IMG_7;
                break;
            }
        }

        return imagem;

    }

}

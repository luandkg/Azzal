package apps.AppTronarko;

import libs.Imaginador.Efeitos;
import libs.Imaginador.ImageUtils;
import libs.tronarko.Signos;

import java.awt.image.BufferedImage;

public class TronarkoImagemSignos {

    public BufferedImage SIGNO_CARPA;
    public BufferedImage SIGNO_GATO;
    public BufferedImage SIGNO_GAVIAO;
    public BufferedImage SIGNO_LEAO;
    public BufferedImage SIGNO_LEOPARDO;

    public BufferedImage SIGNO_LOBO;
    public BufferedImage SIGNO_RAPOSA;
    public BufferedImage SIGNO_SERPENTE;
    public BufferedImage SIGNO_TIGRE;
    public BufferedImage SIGNO_TOURO;

    public TronarkoImagemSignos() {

        SIGNO_CARPA = Efeitos.reduzirComAlfa(ImageUtils.getImagem("res/signos/carpa.png"), 64, 64);
        SIGNO_GATO = Efeitos.reduzirComAlfa(ImageUtils.getImagem("res/signos/gato.png"), 64, 64);
        SIGNO_GAVIAO = Efeitos.reduzirComAlfa(ImageUtils.getImagem("res/signos/gaviao.png"), 64, 64);
        SIGNO_LEAO = Efeitos.reduzirComAlfa(ImageUtils.getImagem("res/signos/leao.png"), 64, 64);
        SIGNO_LEOPARDO = Efeitos.reduzirComAlfa(ImageUtils.getImagem("res/signos/leopardo.png"), 64, 64);

        SIGNO_LOBO = Efeitos.reduzirComAlfa(ImageUtils.getImagem("res/signos/lobo.png"), 64, 64);
        SIGNO_RAPOSA = Efeitos.reduzirComAlfa(ImageUtils.getImagem("res/signos/raposa.png"), 64, 64);
        SIGNO_SERPENTE = Efeitos.reduzirComAlfa(ImageUtils.getImagem("res/signos/serpente.png"), 64, 64);
        SIGNO_TIGRE = Efeitos.reduzirComAlfa(ImageUtils.getImagem("res/signos/tigre.png"), 64, 64);
        SIGNO_TOURO = Efeitos.reduzirComAlfa(ImageUtils.getImagem("res/signos/touro.png"), 64, 64);


    }

    public BufferedImage getSigno(Signos eSigno) {


        BufferedImage imagem = null;


        switch (eSigno) {
            case CARPA -> imagem = SIGNO_CARPA;
            case GATO -> imagem = SIGNO_GATO;
            case GAVIAO -> imagem = SIGNO_GAVIAO;
            case LEAO -> imagem = SIGNO_LEAO;
            case LEOPARDO -> imagem = SIGNO_LEOPARDO;
            case LOBO -> imagem = SIGNO_LOBO;
            case RAPOSA -> imagem = SIGNO_RAPOSA;
            case SERPENTE -> imagem = SIGNO_SERPENTE;
            case TIGRE -> imagem = SIGNO_TIGRE;
            case TOURO -> imagem = SIGNO_TOURO;
        }

        return imagem;

    }


}

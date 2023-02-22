package apps.app_tronarko;

import libs.imagem.Efeitos;
import libs.imagem.Imagem;
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

        SIGNO_CARPA = Efeitos.reduzirComAlfa(Imagem.getImagem("res/signos/carpa.png"), 64, 64);
        SIGNO_GATO = Efeitos.reduzirComAlfa(Imagem.getImagem("res/signos/gato.png"), 64, 64);
        SIGNO_GAVIAO = Efeitos.reduzirComAlfa(Imagem.getImagem("res/signos/gaviao.png"), 64, 64);
        SIGNO_LEAO = Efeitos.reduzirComAlfa(Imagem.getImagem("res/signos/leao.png"), 64, 64);
        SIGNO_LEOPARDO = Efeitos.reduzirComAlfa(Imagem.getImagem("res/signos/leopardo.png"), 64, 64);

        SIGNO_LOBO = Efeitos.reduzirComAlfa(Imagem.getImagem("res/signos/lobo.png"), 64, 64);
        SIGNO_RAPOSA = Efeitos.reduzirComAlfa(Imagem.getImagem("res/signos/raposa.png"), 64, 64);
        SIGNO_SERPENTE = Efeitos.reduzirComAlfa(Imagem.getImagem("res/signos/serpente.png"), 64, 64);
        SIGNO_TIGRE = Efeitos.reduzirComAlfa(Imagem.getImagem("res/signos/tigre.png"), 64, 64);
        SIGNO_TOURO = Efeitos.reduzirComAlfa(Imagem.getImagem("res/signos/touro.png"), 64, 64);


    }

    public BufferedImage getSigno(Signos eSigno) {


        BufferedImage imagem = null;


        switch (eSigno) {
            case CARPA : { imagem = SIGNO_CARPA;break;}
            case GATO  : {imagem = SIGNO_GATO;break;}
            case GAVIAO   :{ imagem = SIGNO_GAVIAO;break;}
            case LEAO   :{ imagem = SIGNO_LEAO;break;}
            case LEOPARDO  : { imagem = SIGNO_LEOPARDO;break;}
            case LOBO   :{ imagem = SIGNO_LOBO;break;}
            case RAPOSA   :{ imagem = SIGNO_RAPOSA;break;}
            case SERPENTE  : { imagem = SIGNO_SERPENTE;break;}
            case TIGRE  : { imagem = SIGNO_TIGRE;break;}
            case TOURO  : { imagem = SIGNO_TOURO;break;}
        }

        return imagem;

    }


}

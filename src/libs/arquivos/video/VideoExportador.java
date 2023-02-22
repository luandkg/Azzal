package libs.arquivos.video;

import libs.imagem.Imagem;

import java.awt.image.BufferedImage;

public class VideoExportador {

    public static void exportarQuadros(String eArquivoAbrir, String eLocal, String ePrefixo) {

        Video mVideo = new Video();
        mVideo.abrir(eArquivoAbrir);

        //mCron = new Cronometro(mMovie.getTaxa());
        // mCron = new Cronometro(mMovie.getTaxa());


        int qi = 0;
        int qo = mVideo.getQuadrosTotal();
        while (qi < qo) {
            mVideo.proximo();

            BufferedImage quadro = mVideo.getImagemCorrente();

            Imagem.exportar(quadro, eLocal + "/" + ePrefixo  + qi + ".png");

            qi += 1;
        }


    }
}

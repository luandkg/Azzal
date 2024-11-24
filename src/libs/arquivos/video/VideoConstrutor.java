package libs.arquivos.video;

import java.awt.image.BufferedImage;

public class VideoConstrutor {

    private Empilhador mEpilhadorDeVideo;

    public VideoConstrutor(String eArquivo, int eLargura, int eAltura) {
        mEpilhadorDeVideo = new VideoCodecador().criar(eArquivo, eLargura, eAltura);
    }

    public void adicionarQuadro(BufferedImage quadro){
        mEpilhadorDeVideo.empurrarQuadro(quadro);
    }
    public void fechar(){
        mEpilhadorDeVideo.fechar();
    }
}

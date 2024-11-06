package apps.app_atzum;

import libs.arquivos.video.Empilhador;
import libs.arquivos.video.VideoCodecador;
import libs.azzal.Renderizador;
import libs.imagem.Efeitos;
import libs.imagem.Imagem;
import libs.meta_functional.Acao;

import java.awt.image.BufferedImage;

public class VideoRasterizar {

    private String mArquivo;
    private Renderizador mRender;
    private Empilhador mVideo;
    private boolean mDebug;
    private String mDebugLocal;

    public VideoRasterizar(String eArquivo, Renderizador eRender) {
        mArquivo = eArquivo;
        mRender = eRender;
        mVideo = new VideoCodecador().criar(mArquivo, mRender.getLargura() / 2, mRender.getAltura() / 2);
        mDebug = false;
        mDebugLocal = "";
    }

    public Acao onQuadro() {
        Acao eAcao = new Acao() {
            @Override
            public void fazer() {

                BufferedImage quadro_corrente = mRender.toImagemSemAlfa();

                if (mDebug) {
                    Imagem.exportar(quadro_corrente, mDebugLocal);
                }

                mVideo.empurrarQuadro(Efeitos.reduzirMetade(quadro_corrente));

            }
        };

        return eAcao;
    }


    public void novoQuadro(){
        onQuadro().fazer();
    }


    public void debug(String eDebugLocal) {
        mDebug = true;
        mDebugLocal = eDebugLocal;
    }

    public void fechar() {
        mVideo.fechar();
    }

}

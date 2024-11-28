package apps.app_atzum.app;

import libs.arquivos.dsvideo.DSVideo;
import libs.azzal.utilitarios.Cronometro;

import java.awt.image.BufferedImage;

public class VideoEmExecucao {


    private boolean mVideoExecutando = false;
    private DSVideo mVideo = null;
    private Cronometro mVideoCronometro;
    private int mVideoQuadrosTotal = 0;
    private String mVideoDuracao = "";

    private int VIDEO_TAXA_DE_ATUALIZACAO = 25;
    private boolean mCarregado = false;

    public VideoEmExecucao(){
        mCarregado=false;
    }


    public void reproduzirVideo(DSVideo video) {
        mVideoExecutando = true;
        mVideo = video;
        mVideo.abrir();

        mVideoQuadrosTotal = mVideo.getQuadrosTotal();
        mVideoDuracao = mVideo.getDuracao() + " :: " + mVideo.getTempoTotalFormatado();

        mVideoCronometro = new Cronometro(VIDEO_TAXA_DE_ATUALIZACAO);
    }



    public boolean isExecutando(){
        return mVideoExecutando;
    }

    public boolean isExibindo(){
        return mVideoExecutando && mCarregado;
    }

    public BufferedImage getImagemCorrente(){
        return mVideo.getImagemCorrente();
    }

    public int getLargura(){
        return mVideo.getLargura();
    }

    public int getAltura(){
        return mVideo.getAltura();
    }

    public int getFrameCorrente(){
        return mVideo.getFrameCorrente();
    }

    public void update() {
        mVideoCronometro.esperar();

        if (mVideoCronometro.foiEsperado()) {

            // fmt.print("Video ++");

            if (!mVideo.getAcabou()) {

                //    fmt.print("++ Video Ler Proximo ");

                mVideo.proximo();
                mCarregado = true;

                //  System.out.println("\t - Falta = " + mLinhaDoTempo.getTempoFaltante(mVideo.getFrameCorrente()));
                //  mPorcentagem = mLinhaDoTempo.getPorcentagem(mVideo.getFrameCorrente());

                //  System.out.println("\t - Video -->> Frame Index = " + mVideo.getFrameCorrente());

            }

        }
    }

    public void verificarFim(){
        if (mVideo.getAcabou()) {
            mVideo.fechar();
        }
    }

    public int getVideoQuadrosTotal(){
        return mVideoQuadrosTotal;
    }

    public String getVideoDuracao(){
        return mVideoDuracao;
    }
}

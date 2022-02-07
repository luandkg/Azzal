package AppArquivos;

import Arquivos.Video.Video;
import Arquivos.Video.LinhaDoTempo;
import Azzal.Cenarios.Cena;
import Azzal.Cronometro;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Azzal.Windows;
import Imaginador.ImageUtils;
import Letrum.Fonte;
import Letrum.Maker.FonteRunTime;
import UI.Interface.BotaoCor;
import UI.Interface.Clicavel;

import java.awt.image.BufferedImage;

public class AppVideo extends Cena {

    private Fonte TextoGrande;
    private Cronometro mCron;

    BotaoCor BTN_P1;
    BotaoCor BTN_P2;
    BotaoCor BTN_P3;

    Clicavel mClicavel;

    // String eArquivoAbrir = "/home/luan/Vídeos/vi/ecossistema_02.vi";
    // String eArquivoAbrir = "/home/luan/Vídeos/vi/alunos_v2.vi";
    String eArquivoAbrir = "/home/luan/Imagens/Arkazz/build/temperatura.vi";


    private boolean mAberto = false;
    private boolean mCarregado = false;

    private Video mVideo;
    private boolean mPausado;

    private int mTotal;
    private LinhaDoTempo mLinhaDoTempo;
    private int mPorcentagem;

    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Visualizador MV");


        TextoGrande = new FonteRunTime(new Cor(0, 0, 0), 11);

        mClicavel = new Clicavel();

        BTN_P1 = mClicavel.criarBotaoCor(new BotaoCor(25, 100, 50, 50, new Cor(26, 188, 156)));
        BTN_P2 = mClicavel.criarBotaoCor(new BotaoCor(25, 170, 50, 50, new Cor(255, 50, 80)));
        BTN_P3 = mClicavel.criarBotaoCor(new BotaoCor(25, 240, 50, 50, new Cor(150, 80, 200)));

        mPausado = false;
        mTotal = 0;


        mCron = new Cronometro(200);

        System.out.println("Carregar video...");

        abrir();

        System.out.println("Iniciar video...");

        mTotal = mVideo.getQuadrosTotal();

        System.out.println("\t - Arenas  = " + mVideo.getArenas().size());
        System.out.println("\t - Quadros = " + mTotal);
        System.out.println("\t - Duracao = " + mVideo.getSegundosTotal() + " s");
        System.out.println("\t - Duracao = " + mVideo.getTempoTotalFormatado());


        // mMovie.irParaPorcentagem(50);
        mLinhaDoTempo = new LinhaDoTempo(mTotal, mVideo.getTaxa());
        mPorcentagem = 0;

    }


    public void abrir() {

        mVideo = new Video();
        mVideo.abrir(eArquivoAbrir);

        mCron = new Cronometro(mVideo.getTaxa());
          mCron = new Cronometro(5);

        mAberto = true;


    }


    @Override
    public void update(double dt) {

        mClicavel.update(dt, getWindows().getMouse().getX(), getWindows().getMouse().getY(), getWindows().getMouse().isPressed());

        if (!mPausado) {
            mCron.atualizar();

            if (mCron.esperado()) {

                if (!mVideo.getAcabou()) {
                    mVideo.proximo();
                    mCarregado = true;

                    System.out.println("\t - Falta = " + mLinhaDoTempo.getTempoFaltante(mVideo.getFrameCorrente()));
                    mPorcentagem = mLinhaDoTempo.getPorcentagem(mVideo.getFrameCorrente());

                    System.out.println("\t - Movie -->> Frame Index = " + mVideo.getFrameCorrente());

                }

            }

        }

        if (mClicavel.getClicado()) {

            int px = (int) getWindows().getMouse().getX();
            int py = (int) getWindows().getMouse().getY();


            if (BTN_P1.getClicado(px, py)) {

                if (!mVideo.getAcabou()) {
                    mVideo.proximo();
                    mCarregado = true;
                }


            } else if (BTN_P2.getClicado(px, py)) {

                mPausado = !mPausado;

            } else if (BTN_P3.getClicado(px, py)) {

                mVideo.reIniciar();

            }

        }

        getWindows().getMouse().liberar();

    }

    @Override
    public void draw(Renderizador r) {

        r.limpar(new Cor(255, 255, 255));

        mClicavel.onDraw(r);

        TextoGrande.setRenderizador(r);

        TextoGrande.escreva(20, 50, "Visualizador de Video ( .vi ) :: " + mVideo.getFrameCorrente());


        if (mAberto && mCarregado) {
           // r.drawImagem(100, 100, mVideo.getImagemCorrente());

            BufferedImage reduzido = Imaginador.Efeitos.reduzir(mVideo.getImagemCorrente(),mVideo.getImagemCorrente().getWidth()/3,mVideo.getImagemCorrente().getHeight()/3);
            r.drawImagem(100, 100, reduzido);

        }

        int mAlargador = 5;

        r.drawRect_Pintado(300, 950, 100 * mAlargador, 10, new Cor(0, 0, 0));

        if (mAberto && mCarregado) {

            if (mTotal > 0) {


                int eFrame = mPorcentagem * 5;

                //   System.out.println("Porcentagem - " +mPorcentagem );

                r.drawRect_Pintado(300 + eFrame, 950, 5, 10, new Cor(255, 0, 0));

            }

        }

    }

}
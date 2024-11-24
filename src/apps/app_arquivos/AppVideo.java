package apps.app_arquivos;

import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.arquivos.video.LinhaDoTempo;
import libs.arquivos.video.Video;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.Cronometro;
import libs.mockui.Interface.Acao;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;

import java.awt.image.BufferedImage;

public class AppVideo extends Cena {

    private Fonte mEscritorGrande;
    private Cronometro mCron;

    private BotaoCor BTN_P1;
    private BotaoCor BTN_P2;
    private BotaoCor BTN_P3;

    private  Clicavel mClicavel;

  //  private  String eArquivoAbrir = "/home/luan/Imagens/atzum/regionalizando.vi";
    // String eArquivoAbrir = "/home/luan/Vídeos/vi/alunos_v2.vi";
    // String eArquivoAbrir = "/home/luan/Imagens/Arkazz/build/temperatura.vi";

   // private  String eArquivoAbrir=   "/home/luan/Imagens/atzum/build/complexo/temperatura.vi";
   // private  String eArquivoAbrir=   "/home/luan/Imagens/atzum/build/complexo/chuva.vi";
//private  String eArquivoAbrir=   "/home/luan/Imagens/atzum/build/complexo/preciptacao.vi";
  //  private  String eArquivoAbrir=   "/home/luan/Imagens/atzum/build/complexo/tp.vi";
    // private  String eArquivoAbrir=   "/home/luan/Imagens/atzum/build/complexo/chuva.vi";
     //private  String eArquivoAbrir=   "/home/luan/Imagens/atzum/videos/fatores_climaticos.vi";
    private  String eArquivoAbrir=   "/home/luan/Imagens/atzum/videos_sensores/fatores_climaticos.vi";

    private boolean mAberto = false;
    private boolean mCarregado = false;

    private Video mVideo;
    private boolean mPausado;

    private int mTotal;
    private LinhaDoTempo mLinhaDoTempo;
    private int mPorcentagem;
    private int TAXA_DE_ATUALIZACAO = 25;

    private Cores mCores;

    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Visualizador VI");


        mCores = new Cores();
        mEscritorGrande = new FonteRunTime(mCores.getPreto(), 11);

        mClicavel = new Clicavel();

        BTN_P1 = mClicavel.criarBotaoCor(new BotaoCor(25, 100, 50, 50, new Cor(26, 188, 156)));
        BTN_P2 = mClicavel.criarBotaoCor(new BotaoCor(25, 170, 50, 50, new Cor(255, 50, 80)));
        BTN_P3 = mClicavel.criarBotaoCor(new BotaoCor(25, 240, 50, 50, new Cor(150, 80, 200)));

        mPausado = false;
        mTotal = 0;


        mCron = new Cronometro(TAXA_DE_ATUALIZACAO);

        System.out.println("Carregar video...");

        abrir();

        System.out.println("Iniciar video...");

        mTotal = mVideo.getQuadrosTotal();

        System.out.println("\t - Arenas  = " + mVideo.getArenas().getQuantidade());
        System.out.println("\t - Quadros = " + mTotal);
        System.out.println("\t - Duracao = " + mVideo.getSegundosTotal() + " s");
        System.out.println("\t - Duracao = " + mVideo.getTempoTotalFormatado());


        mLinhaDoTempo = new LinhaDoTempo(mTotal, mVideo.getTaxa());
        mPorcentagem = 0;

        BTN_P1.setAcao(new Acao() {
            @Override
            public void onClique() {

                if (!mVideo.getAcabou()) {
                    mVideo.proximo();
                    mCarregado = true;
                }

            }
        });

        BTN_P2.setAcao(new Acao() {
            @Override
            public void onClique() {
                mPausado = !mPausado;
            }
        });

        BTN_P3.setAcao(new Acao() {
            @Override
            public void onClique() {
                mVideo.reIniciar();

                abrir();
            }
        });
    }


    public void abrir() {

        mVideo = new Video();
        mVideo.abrir(eArquivoAbrir);

       // mCron = new Cronometro(mVideo.getTaxa());
        mCron = new Cronometro(TAXA_DE_ATUALIZACAO);
       // mCron = new Cronometro(5);

        mAberto = true;


    }


    @Override
    public void update(double dt) {

        mClicavel.update(dt, getWindows().getMouse().getX(), getWindows().getMouse().getY(), getWindows().getMouse().isPressed());

        if (!mPausado) {
            mCron.esperar();

            if (mCron.foiEsperado()) {

                if (!mVideo.getAcabou()) {
                    mVideo.proximo();
                    mCarregado = true;

                    System.out.println("\t - Falta = " + mLinhaDoTempo.getTempoFaltante(mVideo.getFrameCorrente()));
                    mPorcentagem = mLinhaDoTempo.getPorcentagem(mVideo.getFrameCorrente());

                    System.out.println("\t - Video -->> Frame Index = " + mVideo.getFrameCorrente());

                }

            }

        }



        getWindows().getMouse().liberar();

    }

    @Override
    public void draw(Renderizador r) {

        r.limpar(mCores.getBranco());

        mClicavel.onDraw(r);

        mEscritorGrande.setRenderizador(r);

        mEscritorGrande.escreva(20, 50, "Visualizador de Video ( .vi ) :: " + mVideo.getFrameCorrente());


        if (mAberto && mCarregado) {
            // r.drawImagem(100, 100, mVideo.getImagemCorrente());

          //  BufferedImage reduzido = libs.imagem.Efeitos.reduzir(mVideo.getImagemCorrente(), mVideo.getImagemCorrente().getWidth() / 3, mVideo.getImagemCorrente().getHeight() / 3);
            BufferedImage reduzido =mVideo.getImagemCorrente();

            r.drawImagem(100, 100, reduzido);

        }

        int alargador = 5;

        r.drawRect_Pintado(300, 950, 100 * alargador, 10, new Cor(0, 0, 0));

        if (mAberto && mCarregado) {

            if (mTotal > 0) {

                //   System.out.println("Porcentagem - " +mPorcentagem );
                int passador = mPorcentagem * 5;
                r.drawRect_Pintado(300 + passador, 950, 5, 10, new Cor(255, 0, 0));

            }

        }

    }

}
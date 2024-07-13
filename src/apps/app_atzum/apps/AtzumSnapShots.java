package apps.app_atzum.apps;

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
import libs.imagem.Efeitos;
import libs.luan.Lista;
import libs.mockui.Interface.Acao;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;

import java.awt.image.BufferedImage;

public class AtzumSnapShots extends Cena {

    private Fonte mEscritorGrande;



    //  private  String eArquivoAbrir = "/home/luan/Imagens/atzum/regionalizando.vi";
    // String eArquivoAbrir = "/home/luan/Vídeos/vi/alunos_v2.vi";
    // String eArquivoAbrir = "/home/luan/Imagens/Arkazz/build/temperatura.vi";

     private  String eArquivoAbrir=   "/home/luan/Imagens/atzum/build/complexo/temperatura.vi";
    // private  String eArquivoAbrir=   "/home/luan/Imagens/atzum/build/complexo/chuva.vi";
   // private  String eArquivoAbrir=   "/home/luan/Imagens/atzum/build/complexo/preciptacao.vi";

    private boolean mAberto = false;
    private boolean mCarregado = false;

    private Video mVideo;

    private int mTotal;
    private LinhaDoTempo mLinhaDoTempo;

    private Cores mCores;

    private Lista<BufferedImage> quadros ;


    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Atzum SnapShots");


        mCores = new Cores();
        mEscritorGrande = new FonteRunTime(mCores.getBranco(), 11);


        mTotal = 0;


        System.out.println("Carregar video...");

        abrir();

        System.out.println("Iniciar video...");

        mTotal = mVideo.getQuadrosTotal();

        System.out.println("\t - Arenas  = " + mVideo.getArenas().getQuantidade());
        System.out.println("\t - Quadros = " + mTotal);
        System.out.println("\t - Duracao = " + mVideo.getSegundosTotal() + " s");
        System.out.println("\t - Duracao = " + mVideo.getTempoTotalFormatado());


        mLinhaDoTempo = new LinhaDoTempo(mTotal, mVideo.getTaxa());


    }


    public void abrir() {

        mVideo = new Video();
        mVideo.abrir(eArquivoAbrir);
        mAberto = true;

        if (!mVideo.getAcabou()) {
            mCarregado = true;

            quadros = new Lista<BufferedImage>();

            int total = mVideo.getQuadrosTotal();
            int taxa = total/10;
            int indice = 0;

            while(indice<total ){
                int indice_proximo= indice+taxa;
                while(mVideo.getFrameCorrente()<indice_proximo){
                    mVideo.proximo();
                }

                System.out.println("\t - Video -->> Frame Index = " + mVideo.getFrameCorrente());

                BufferedImage reduzido = Efeitos.reduzir(mVideo.getImagemCorrente(), mVideo.getImagemCorrente().getWidth() / 4, mVideo.getImagemCorrente().getHeight() / 4);

                quadros.adicionar(reduzido);
                indice+=taxa;
            }

            System.out.println("\t - Video -->> Frame Index = " + mVideo.getFrameCorrente());

        }
    }


    @Override
    public void update(double dt) {

    }

    @Override
    public void draw(Renderizador r) {

        r.limpar(mCores.getPreto());

        mEscritorGrande.setRenderizador(r);

        mEscritorGrande.escreva(20, 50, "Visualizador de Video ( .vi ) :: " + mVideo.getFrameCorrente());

        int py = 0;
int px = 0;

        for(BufferedImage imagem : quadros){
            r.drawImagem(100+px, 100+py, imagem);
            py+=300;

            if(py>800){
                py=0;
                px+=400;
            }
        }

    }

}
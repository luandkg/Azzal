package apps.app_arquivos;

import libs.imagem.Imagem;
import libs.arquivos.IM;
import libs.azzal.cenarios.Cena;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.azzal.Windows;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.mockui.Interface.Acao;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;

import java.awt.image.BufferedImage;

public class AppImagem extends Cena {

    private Fonte pequeno;
    private Fonte micro;
    private Cores mCores;

    private Clicavel mClicavel;

    private BufferedImage mImagem;


    @Override
    public void iniciar(Windows eWindows) {

        mCores = new Cores();

        pequeno = new FonteRunTime(mCores.getPreto(), FonteRunTime.getTamanhoPequeno());
        micro = new FonteRunTime(mCores.getPreto(), FonteRunTime.getTamanhoMicro());

        IM.salvar(Imagem.getImagem("/home/luan/Imagens/cover.jpeg"), "/home/luan/Imagens/eita.im");


        // mImagem = IM.abrir("/home/luan/Dropbox/CED_01/imagem.im");
        //mImagem = IM.abrir("/home/luan/Imagens/eu.im");
        mImagem = IM.abrir("/home/luan/Imagens/eita.im");


      //  RhoDiff.diff("/home/luan/Containers/im_abrir.txt", "/home/luan/Containers/im_salvar.txt");


        resolucao_adequar();

        mClicavel = new Clicavel();

        BotaoCor BTN_PROXIMA = mClicavel.criarBotaoCor(new BotaoCor(500, 50, 50, 50, new Cor(26, 188, 156)));
        BotaoCor BTN_VOLTAR = mClicavel.criarBotaoCor(new BotaoCor(600, 50, 50, 50, new Cor(26, 188, 156)));

        BTN_PROXIMA.setAcao(new Acao() {
            @Override
            public void onClique() {
                imagem_proxima();
            }
        });

        BTN_VOLTAR.setAcao(new Acao() {
            @Override
            public void onClique() {
                imagem_voltar();
            }
        });
    }

    public void imagem_proxima() {
        resolucao_adequar();
    }

    public void imagem_voltar() {


        resolucao_adequar();

    }


    public void resolucao_adequar() {

        if (mImagem.getWidth() > 600 && mImagem.getHeight() < 600) {
            //   mImagem = Efeitos.reduzir(mImagem, mImagem.getWidth() / 2, mImagem.getHeight());
        } else if (mImagem.getWidth() < 600 && mImagem.getHeight() > 600) {
            //      mImagem = Efeitos.reduzir(mImagem, mImagem.getWidth(), mImagem.getHeight() / 2);
        } else if (mImagem.getWidth() > 600 || mImagem.getHeight() > 600) {
            //     mImagem = Efeitos.reduzir(mImagem, mImagem.getWidth() / 2, mImagem.getHeight() / 2);
        }

    }


    @Override
    public void update(double dt) {


        mClicavel.update(dt, getMx(), getMy(), isPressionsado());

        getWindows().getMouse().liberar();


    }


    @Override
    public void draw(Renderizador eRender) {

        eRender.limpar(mCores.getBranco());

        pequeno.setRenderizador(eRender);
        micro.setRenderizador(eRender);

        mClicavel.onDraw(eRender);


        eRender.drawImagem(100, 300, mImagem);

    }


}

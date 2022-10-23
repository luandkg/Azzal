package apps.app_arquivos;

import libs.arquivos.IM;
import azzal.cenarios.Cena;
import azzal.Cores;
import azzal.Renderizador;
import azzal.utilitarios.Cor;
import azzal.Windows;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;
import mockui.Interface.Acao;
import mockui.Interface.BotaoCor;
import mockui.Interface.Clicavel;

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


        mImagem = IM.abrir("/home/luan/Dropbox/CED_01/imagem.im");
        //mImagem = IM.abrir("/home/luan/Imagens/eu.im");

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


        eRender.drawImagem(150, 300, mImagem);

    }


}

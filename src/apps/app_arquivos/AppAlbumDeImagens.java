package apps.app_arquivos;

import libs.arquivos.AI;
import libs.arquivos.ImagemDoAlbum;
import libs.azzal.cenarios.Cena;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.azzal.Windows;
import libs.imagem.Efeitos;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.mockui.Interface.Acao;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;

import java.awt.image.BufferedImage;

public class AppAlbumDeImagens extends Cena {

    private Fonte pequeno;
    private Fonte micro;
    private Cores mCores;


    private Clicavel mClicavel;

    private AI mAlbumCorrente;
    private int mSelecionado;
    private BufferedImage mImagem;


    @Override
    public void iniciar(Windows eWindows) {

        mCores = new Cores();

        pequeno = new FonteRunTime(mCores.getPreto(), FonteRunTime.getTamanhoPequeno());
        micro = new FonteRunTime(mCores.getPreto(), FonteRunTime.getTamanhoMicro());

        mAlbumCorrente = new AI();
        mAlbumCorrente.abrir("/home/luan/Imagens/luan.ai");
        mSelecionado = 0;

        mImagem = mAlbumCorrente.getImagem(0);
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

        mSelecionado += 1;

        if (mSelecionado >= mAlbumCorrente.getImagens().size()) {
            mSelecionado = 0;
        }

        System.out.println(" -->> " + mAlbumCorrente.getImagens().get(mSelecionado).getNome());

        mImagem = mAlbumCorrente.getImagem(mSelecionado);

        resolucao_adequar();

    }

    public void imagem_voltar() {

        mSelecionado -= 1;

        if (mSelecionado < 0) {
            mSelecionado = mAlbumCorrente.getImagens().size() - 1;
        }

        System.out.println(" -->> " + mAlbumCorrente.getImagens().get(mSelecionado).getNome());

        mImagem = mAlbumCorrente.getImagem(mSelecionado);

        resolucao_adequar();

    }


    public void resolucao_adequar() {

        if (mImagem.getWidth() > 600 && mImagem.getHeight() < 600) {
            mImagem = Efeitos.reduzir(mImagem, mImagem.getWidth() / 2, mImagem.getHeight());
        } else if (mImagem.getWidth() < 600 && mImagem.getHeight() > 600) {
            mImagem = Efeitos.reduzir(mImagem, mImagem.getWidth(), mImagem.getHeight() / 2);
        } else if (mImagem.getWidth() > 600 || mImagem.getHeight() > 600) {
            mImagem = Efeitos.reduzir(mImagem, mImagem.getWidth() / 2, mImagem.getHeight() / 2);
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

        int a = 0;
        for (ImagemDoAlbum imagem : mAlbumCorrente.getImagens()) {

            if (mSelecionado == a) {
                eRender.drawRect_Pintado(30, 102 + (a * 25), 10, 10, mCores.getVermelho());
            } else {
                eRender.drawRect(30, 102 + (a * 25), 10, 10, mCores.getPreto());
            }

            pequeno.escreva(50, 100 + (a * 25), "Imagem :: " + imagem.getNome());
            a += 1;

        }

        eRender.drawImagem(150, 300, mImagem);

    }


}

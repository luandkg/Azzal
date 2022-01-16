package Arquivos;

import Azzal.Cenarios.Cena;
import Azzal.Cores;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Azzal.Windows;
import Imaginador.Efeitos;
import Letrum.Fonte;
import Letrum.Maker.FonteRunTime;
import Luan.fmt;
import UI.Interface.Acao;
import UI.Interface.BotaoCor;
import UI.Interface.Clicavel;

import java.awt.image.BufferedImage;

public class AppImagem extends Cena {

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

        BotaoCor BTN_IR = mClicavel.criarBotaoCor(new BotaoCor(500, 50, 50, 50, new Cor(26, 188, 156)));

        BTN_IR.setAcao(new Acao() {
            @Override
            protected void onClique() {

                clicar();

            }
        });


    }

    public void clicar() {

        mSelecionado += 1;

        if (mSelecionado >= mAlbumCorrente.getImagens().size()) {
            mSelecionado = 0;
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

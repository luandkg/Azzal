package apps.app_azzal;

import libs.azzal.cenarios.Cena;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.Cronometro;
import libs.azzal.Windows;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TransicionadorDeCena {

    private Cronometro mCron;

    private ArrayList<Cena> mCenas;

    private int mSelecionada;
    private int mProxima;
    private boolean isMudando;
    private int delta_x;

    private BufferedImage imagem_antes;
    private BufferedImage imagem_depois;

    private Renderizador render_antes;
    private Renderizador render_depois;

    private int antes;
    private int depois;

    private int x_antes;
    private int x_depois;
    private boolean indo;

    private Windows mWindows;

    public TransicionadorDeCena(Windows eWindows) {

        mWindows = eWindows;

        mCron = new Cronometro(50);

        mCenas = new ArrayList<Cena>();

        mSelecionada = 0;
        mProxima = 0;
        isMudando = false;
        delta_x = 0;

        imagem_antes = new BufferedImage(mWindows.getLargura(), mWindows.getAltura(), BufferedImage.TYPE_INT_ARGB);
        imagem_depois = new BufferedImage(mWindows.getLargura(), mWindows.getAltura(), BufferedImage.TYPE_INT_ARGB);

        render_antes = new Renderizador(imagem_antes);
        render_depois = new Renderizador(imagem_depois);

        antes = 0;
        depois = 0;

        x_antes = 0;
        x_depois = 0;
        indo = true;
    }

    public void adicionarCena(Cena eCena) {
        mCenas.add(eCena);
    }

    public void iniciarCenas() {

        for (Cena eCena : mCenas) {
            eCena.iniciar(mWindows);
            eCena.setWindows(mWindows);
        }

    }

    public void update(double dt) {

        if (isMudando) {

            mCron.esperar();

            if (mCron.foiEsperado()) {

                if (indo) {

                    x_antes -= delta_x;
                    x_depois -= delta_x;

                    if (x_depois < 100) {
                        x_depois = 0;
                        isMudando = false;
                        mSelecionada = mProxima;
                    }

                } else {

                    x_antes += delta_x;
                    x_depois += delta_x;

                    if (x_depois > 0) {
                        x_depois = 0;
                        isMudando = false;
                        mSelecionada = mProxima;
                    }

                }


            }

            // System.out.println("mud...");
        } else {

            if (mWindows.getTeclado().foiPressionado(KeyEvent.VK_P)) {

                isMudando = true;
                mProxima = mSelecionada + 1;
                indo = true;

                if (mProxima > mCenas.size() - 1) {
                    mProxima = 0;
                }

                antes = mSelecionada;
                depois = mProxima;

                x_antes = 0;
                x_depois = mWindows.getLargura() + 100;

                delta_x = 100;

                mCron.zerar();

            } else if (mWindows.getTeclado().foiPressionado(KeyEvent.VK_V)) {

                isMudando = true;
                mProxima = mSelecionada - 1;
                indo = false;

                if (mProxima < 0) {
                    mProxima = mCenas.size() - 1;
                }

                antes = mSelecionada;
                depois = mProxima;


                x_antes = 0;
                x_depois = -(mWindows.getLargura() + 100);

                delta_x = 100;

                mCron.zerar();

            } else {
                mCenas.get(mSelecionada).update(dt);
            }

        }

    }

    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);

        if (isMudando) {

            render_antes.limpar(Color.WHITE);
            render_depois.limpar(Color.WHITE);

            mCenas.get(antes).draw(render_antes);
            mCenas.get(depois).draw(render_depois);

            mRenderizador.drawImagem(x_antes, 0, imagem_antes);
            mRenderizador.drawImagem(x_depois, 0, imagem_depois);

        } else {
            mCenas.get(mSelecionada).draw(mRenderizador);
        }

    }

    public void drawComCor(Renderizador mRenderizador, Cor eCor) {

        mRenderizador.limpar(eCor);

        if (isMudando) {

            render_antes.limpar(Color.WHITE);
            render_depois.limpar(Color.WHITE);

            mCenas.get(antes).draw(render_antes);
            mCenas.get(depois).draw(render_depois);

            mRenderizador.drawImagem(x_antes, 0, imagem_antes);
            mRenderizador.drawImagem(x_depois, 0, imagem_depois);

        } else {
            mCenas.get(mSelecionada).draw(mRenderizador);
        }

    }

}

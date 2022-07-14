package apps.app_azzal;

import azzal.Cenarios.Cena;
import azzal.Formatos.Circulo;
import azzal.Formatos.Quadrado;
import azzal.Formatos.Retangulo;
import azzal.RenderizadorClip;
import azzal.Teclado;
import azzal.Utils.*;
import azzal.Renderizador;
import azzal.Windows;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class C1 extends Cena {


    private Cronometro mCron;
    private Fonte mLetramentoPreto;
    private Fonte mLetramentoVermelho;
    //  private LetramentoColorido m3;
    // private LetramentoColorido m4;

    private Cor mCorVermelha;
    private Cor mCorVerde;
    private Cor mCorAzul;
    private Cor mCorAmarela;

    private Retangulo mRect;
    private Retangulo mRect2;
    private Retangulo mRect3;
    private Retangulo mRect4;

    private Circulo mCirculo1;
    private Circulo mCirculo2;

    int d;
    boolean t;
    private Posicionador mPosicionador;

    private Retangulo mQuadrante;
    private Circulo mCirculante;
    private Circulo mCirculante2;

    int graus;
    int regraus;

    private ArrayList<String> mTabelaDeCores;
    private Teclado mTeclado;

    public C1() {


        mCron = new Cronometro(50);

        mLetramentoPreto = new FonteRunTime(new Cor(255,0,0),10);
        mLetramentoVermelho = new FonteRunTime(new Cor(255,0,0),10);
        //   m3 = new LetramentoColorido(Cor.getHexCor("#99d066"));
        //   m4 = new LetramentoColorido(Cor.getHexCor("#ffd54f"));


        mCorVermelha = Cor.getHexCor("#e64a19");
        mCorVerde = Cor.getHexCor("#8bc34a");
        mCorAzul = Cor.getHexCor("#039be5");
        mCorAmarela = Cor.getHexCor("#ffeb3b");

        mRect = new Retangulo(100, 300, 100, 100);
        mRect2 = new Retangulo(100, 300, 100, 100);
        mRect3 = new Retangulo(100, 300, 100, 100);
        mRect4 = new Retangulo(100, 300, 100, 100);

        d = 0;
        t = true;

        mCirculo1 = new Circulo(500, 300, 50);
        mCirculo2 = new Circulo(500, 300, 20);

        mPosicionador = new Posicionador();


        mQuadrante = new Retangulo(490, 490, 220, 220);

        mCirculante = new Circulo(mQuadrante.getX() + (mQuadrante.getLargura() / 2) - 100, mQuadrante.getY() + (mQuadrante.getAltura() / 2) - 100, 100);
        mCirculante2 = new Circulo(mQuadrante.getX() + (mQuadrante.getLargura() / 2) - 80, mQuadrante.getY() + (mQuadrante.getAltura() / 2) - 80, 80);

        graus = 0;
        regraus = 0;

        mTabelaDeCores = new ArrayList<String>();

        adicionarLinha("#ffebee", "#ffcdd2", "#ef9a9a", "#e57373", "#ef5350", "#f44336", "#e53935", "#d32f2f", "#c62828", "#b71c1c", "#b71c1c");
        adicionarLinha("#fce4ec", "#ffebee", "#f8bbd0", "#f48fb1", "#f06292", "#ec407a", "#e91e63", "#c2185b", "#ad1457", "#880e4f", "#880e4f");
        adicionarLinha("#e1bee7", "#ce93d8", "#ba68c8", "#ab47bc", "#9c27b0", "#8e24aa", "#7b1fa2", "#6a1b9a", "#4a148c", "#4a148c", "#4a148c");

    }

    public void adicionarLinha(String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11) {

        mTabelaDeCores.add(a1);
        mTabelaDeCores.add(a2);
        mTabelaDeCores.add(a3);
        mTabelaDeCores.add(a4);
        mTabelaDeCores.add(a5);
        mTabelaDeCores.add(a6);
        mTabelaDeCores.add(a7);
        mTabelaDeCores.add(a8);
        mTabelaDeCores.add(a9);
        mTabelaDeCores.add(a10);
        mTabelaDeCores.add(a11);

    }

    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Cena 1 - libs.Luan Freitas");
        mTeclado = eWindows.getTeclado();

    }

    @Override
    public void update(double dt) {


        mCron.esperar();

        if (mCron.foiEsperado()) {
            if (t) {
                d += 1;

                if (d >= 20) {
                    t = !t;
                }
            } else {
                d -= 1;

                if (d <= 0) {
                    t = !t;
                }
            }

            //  graus += 10;
            if (graus > 360) {
                graus = 0;
            }

            regraus -= 10;
            if (regraus < 0) {
                regraus = 360;
            }

        }

        if (mTeclado.foiPressionado(KeyEvent.VK_A)) {
            graus += 10;
            if (graus > 360) {
                graus = 0;
            }
        }
        if (mTeclado.foiPressionado(KeyEvent.VK_S)) {
            graus -= 10;
            if (graus < 0) {
                graus = 360;
            }
        }

        mCirculo2.setRaio(20 + d);

        if (mTeclado.estaPressionado(KeyEvent.VK_S)) {
            d += 1;
            if (d > 120) {
                d = 120;
            }
        }


        organizarPosicao_direita(mRect, mRect2, d);
        organizarPosicao_abaixo(mRect, mRect3, d);
        organizarPosicao_abaixo(mRect2, mRect4, d);

    }

    public void organizarPosicao_direita(Retangulo r1, Retangulo r2, int mDistancia) {

        r2.setX(r1.getX() + r1.getLargura() + mDistancia);
        r2.setY(r1.getY());

    }


    public void organizarPosicao_abaixo(Retangulo r1, Retangulo r2, int mDistancia) {

        r2.setY(r1.getY() + r1.getAltura() + mDistancia);
        r2.setX(r1.getX());

    }

    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);

        mLetramentoPreto.setRenderizador(mRenderizador);
        mLetramentoVermelho.setRenderizador(mRenderizador);
        //  m3.atualizar(mRenderizador);
        // m4.atualizar(mRenderizador);


        mLetramentoPreto.escreva(150, 150, "libs.Luan Alves Freitas");
        mLetramentoVermelho.escreva(150, 170, "libs.Luan Alves Freitas");
        // m3.escreve(150, 190, "libs.Luan Alves Freitas");
        //  m4.escreve(150, 210, "libs.Luan Alves Freitas");

        RenderizadorClip eRenderizadorClipCentro = new RenderizadorClip(mRenderizador, new Retangulo(150, 350, 100, 100));

        // mRenderizador.drawRect_Pintado(mRect, mCorVermelha);
        //  mRenderizador.drawRect_Pintado(mRect2, mCorVerde);
        //   mRenderizador.drawRect_Pintado(mRect3, mCorAmarela);
        //  mRenderizador.drawRect_Pintado(mRect4, mCorAzul);

        eRenderizadorClipCentro.drawRect_Pintado(mRect, mCorVermelha);
        eRenderizadorClipCentro.drawRect_Pintado(mRect2, mCorVerde);
        eRenderizadorClipCentro.drawRect_Pintado(mRect3, mCorAmarela);
        eRenderizadorClipCentro.drawRect_Pintado(mRect4, mCorAzul);

        //  mRenderizador.drawRect_Pintado(new Retangulo(150, 350, 100, 100), mCorVermelha);


        mRenderizador.drawCirculoCentralizado(500, 300, 50, mCorAzul);


        if (d > 110) {
            mRenderizador.drawCirculo(mPosicionador.getCirculo_Centralizado(500, 300, mCirculo2), mCorVermelha);
        } else {
            mRenderizador.drawCirculo(mPosicionador.getCirculo_Centralizado(500, 300, mCirculo2), mCorVerde);
        }


        mRenderizador.drawRect(mQuadrante, mCorAzul);
        //  mRenderizador.drawCirculo(mCirculante, mCorVerde);


        int p1 = graus;
        int p2 = graus + 90;

        int r1 = regraus;
        int r2 = regraus + 90;

        int a = mRenderizador.valide(r1 + 180);
        int b = mRenderizador.valide(r2 + 40);

        //mRenderizador.drawArco(mCirculante, p1, p2, mCorVerde,false);
        mRenderizador.drawArco(mCirculante2, p1 + 180, p2 - 40, mCorVerde, true);


        // mRenderizador.drawArco(mCirculante2, a, b, mCorVerde,true);

        String eFrase = "A = " + a + " B = " + b;

        // mRenderizador.drawMidpointCircle(mCirculante2, 0, 360, mCorVerde);

        mLetramentoPreto.escreva(mCirculante.getX(), mCirculante.getY() + 2 * (mCirculante.getRaio()) + 20, eFrase);

        drawTabelaDeCores(mRenderizador);


        mRenderizador.drawRect(new Retangulo(100, 600, 300, 300), mCorVermelha);

        RenderizadorClip eRenderizadorClip = new RenderizadorClip(mRenderizador, new Retangulo(100, 600, 300, 300));

        eRenderizadorClip.drawRect_Pintado(new Retangulo(200, 550, 100, 100), mCorAmarela);

    }


    public void drawTabelaDeCores(Renderizador mRenderizador) {

        int ePx = 800;
        int ePy = 400;

        int eCorID = 0;

        for (String eCor : mTabelaDeCores) {

            mRenderizador.drawRect_Pintado(new Quadrado(ePx, ePy, 30), Cor.getHexCor(eCor));

            if (eCorID >= 10) {
                eCorID = 0;
                ePy += 40;
                ePx = 800;
            } else {
                ePx += 40;
                eCorID += 1;
            }

        }

    }


}

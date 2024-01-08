package apps.app_fisica;

import libs.azzal.cenarios.Cena;
import libs.azzal.geometria.Retangulo;
import libs.azzal.Teclado;
import libs.azzal.utilitarios.*;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;

import java.awt.*;
import java.awt.event.KeyEvent;


public class AppFisica extends Cena {


    private Fonte mLetramentoPreto;


    private HiperTempo mTempo;

    private LinhaVertical mInicio;
    private LinhaVertical mFinal;

    private Retangulo mC1;
    private LinhaHorizontal mPista;
    private int mVelC1;
    private int mT1;

    private Retangulo mC2;
    private LinhaHorizontal mPista2;
    private int mVelC2;
    private int mT2;

    private Retangulo mC3;
    private LinhaHorizontal mPista3;
    private int mVelC3;
    private int mT3;

    private Retangulo mC4;
    private LinhaHorizontal mPista4;
    private int mVelC4;
    private int mT4;

    private VerificarColisao mC4C1;
    private VerificarColisao mC4C2;
    private VerificarColisao mC4C3;

    private boolean mAguardar = false;

    private Cronometro mCronAguardando;
    private Trena mTrena;

    private Teclado mTeclado;

    public AppFisica() {

        mLetramentoPreto = new FonteRunTime(new Cor(255, 0, 0), 10);

        zerar();

    }

    public void zerar() {

        mTempo = new HiperTempo(100);
        mCronAguardando = new Cronometro(1000);

        mInicio = new LinhaVertical(200, 90, 100);
        mFinal = new LinhaVertical(800, 90, 100);

        mPista = new LinhaHorizontal(100, 200, 800);
        mC1 = new Retangulo(200, mPista.getY() - 52, 50, 50);
        mVelC1 = 10;

        mPista2 = new LinhaHorizontal(100, 350 + 20, 800);
        mC2 = new Retangulo(200, mPista2.getY() - 52, 50, 50);
        mVelC2 = 20;

        mPista3 = new LinhaHorizontal(100, 500 + 40, 800);
        mC3 = new Retangulo(200, mPista3.getY() - 52, 50, 50);
        mVelC3 = 30;

        mPista4 = new LinhaHorizontal(100, 650 + 60, 800);
        mC4 = new Retangulo(800, mPista4.getY() - 52, 50, 50);
        mVelC4 = -22;

        mT1 = 0;
        mT2 = 0;
        mT3 = 0;
        mT4 = 0;

        mC4C1 = new VerificarColisao(mC1, mC4);
        mC4C2 = new VerificarColisao(mC2, mC4);
        mC4C3 = new VerificarColisao(mC3, mC4);

        mTrena = new Trena(-100, 50, mLetramentoPreto);

    }

    @Override
    public void iniciar(Windows eWindows) {
        eWindows.setTitle("Cena 1 - libs.Luan Freitas");
        mTeclado = eWindows.getTeclado();
    }

    @Override
    public void update(double dt) {

        if (mTeclado.foiPressionado(KeyEvent.VK_ESCAPE)) {
            zerar();
        }

        if (!precisaAguardar()) {

            if (mTempo.passou()) {
                tempo();
            }

            terminarColisoes();

        }


    }

    public boolean precisaAguardar() {

        boolean ret = false;

        if (!mAguardar) {

            if (mTempo.passou()) {
                tempo();
            }


        } else {
            mCronAguardando.esperar();
            if (mCronAguardando.foiEsperado()) {
                mCronAguardando.zerar();
                mAguardar = false;
            }
            ret = true;
        }

        return ret;
    }

    public void terminarColisoes() {
        if (mC4C1.jaColidiu()) {
            mAguardar = true;
            mC4C1.retirarColisao();
        }
        if (mC4C2.jaColidiu()) {
            mAguardar = true;
            mC4C2.retirarColisao();
        }
        if (mC4C3.jaColidiu()) {
            mAguardar = true;
            mC4C3.retirarColisao();
        }
    }

    public void tempo() {

        if (mC1.getX() < mFinal.getX()) {
            mC1.setX(mC1.getX() + mVelC1);
            mT1 += 1;
        }
        if (mC2.getX() < mFinal.getX()) {
            mC2.setX(mC2.getX() + mVelC2);
            mT2 += 1;
        }

        if (mC3.getX() < mFinal.getX()) {
            mC3.setX(mC3.getX() + mVelC3);
            mT3 += 1;
        }

        if (mC4.getX2() > mInicio.getX()) {
            mC4.setX(mC4.getX() + mVelC4);
            mT4 += 1;
        }


        //   colisoes();


    }

    public void colisoes() {
        if (mC4C1.colidiu()) {
            mCronAguardando.zerar();
        }
        if (mC4C2.colidiu()) {
            mCronAguardando.zerar();
        }
        if (mC4C3.colidiu()) {
            mCronAguardando.zerar();
        }
    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);

        mLetramentoPreto.setRenderizador(mRenderizador);


        espacoHorizontalMarcado(mRenderizador, mC1, mVelC1, mT1, new Cor(255, 0, 0), mPista);
        espacoHorizontalMarcado(mRenderizador, mC2, mVelC2, mT2, new Cor(0, 255, 0), mPista2);
        espacoHorizontalMarcado(mRenderizador, mC3, mVelC3, mT3, new Cor(0, 0, 255), mPista3);
        espacoHorizontalMarcado(mRenderizador, mC4, mVelC4, mT4, new Cor(200, 0, 50), mPista4);

        //  mLetramentoPreto.desenharGrade(10,100);
    }

    public void espacoHorizontalMarcado(Renderizador mRenderizador, Retangulo mCorpo, int eVel, int eTempo, Cor eCorCorpo, LinhaHorizontal mPistaDoCorpo) {


        mLetramentoPreto.escreva(mPistaDoCorpo.getX() + 120, mPistaDoCorpo.getY() - 100, "X : " + (mCorpo.getX() - 200));
        mLetramentoPreto.escreva(mPistaDoCorpo.getX() + 220, mPistaDoCorpo.getY() - 100, "Y : " + mCorpo.getY());
        mLetramentoPreto.escreva(mPistaDoCorpo.getX() + 350, mPistaDoCorpo.getY() - 100, "T : " + eTempo);


        drawLinhaHorizontal(mRenderizador, mPistaDoCorpo, new Cor(0, 0, 0));
        drawFillRect(mRenderizador, mCorpo, eCorCorpo);
        mTrena.render(mRenderizador, mPistaDoCorpo.getX(), mPistaDoCorpo.getX2(), mPistaDoCorpo.getY() + 20);

        drawLinhaVerticalComStep(mRenderizador, new LinhaVertical(mInicio.getX(), mPistaDoCorpo.getY() - 100, mInicio.getTamanho()), 10, new Cor(0, 0, 0));
        drawLinhaVerticalComStep(mRenderizador, new LinhaVertical(mFinal.getX(), mPistaDoCorpo.getY() - 100, mFinal.getTamanho()), 10, new Cor(0, 0, 0));

    }


    public void drawFillRect(Renderizador mRenderizador, Retangulo mRetangulo, Cor eCor) {
        mRenderizador.drawRect_Pintado(mRetangulo, eCor);
    }


    public void drawLinhaHorizontal(Renderizador mRenderizador, LinhaHorizontal eLinha, Cor eCor) {
        mRenderizador.drawLinhaHorizontal(eLinha.getX(), eLinha.getY(), eLinha.getTamanho(), eCor);
    }


    public void drawLinhaVerticalComStep(Renderizador mRenderizador, LinhaVertical eLinha, int ePasso, Cor eCor) {

        int cy = eLinha.getY();

        while (cy < eLinha.getY2()) {
            mRenderizador.drawLinhaVertical(eLinha.getX(), cy, ePasso, eCor);

            cy += ePasso + 10;
        }


    }

}

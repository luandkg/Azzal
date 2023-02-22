package apps.appParticulas;

import azzal.cenarios.Cena;
import azzal.utilitarios.*;
import azzal.Renderizador;
import azzal.Windows;
import libs.luan.Iterador;
import libs.luan.Lista;

import java.awt.*;
import java.util.Random;


public class AppFenomenos extends Cena {

    private TransformadorDeCor TDA;
    private TransformadorDeCor TDB;
    private Paleta mPaleta;

    Cronometro mCron;

    private Lista<Particula> mAreia;
    private Lista<Particula> mLiquido;


    private int mTamanho;
    private int mTamanhoCopo;

    private int mIndice;
    private int mQuantos;
    private Random rd;

    private int mJogarAreia;
    private int mJogarLiquido;

    public static Color getHexColor(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

    public AppFenomenos() {


        TDA = new TransformadorDeCor(new Cor(76, 175, 80));
        TDA.mudarAzul(20, 10);
        TDA.mudarVermelho(16, 6);
        TDA.mudarVerde(15, 14);


        TDB = new TransformadorDeCor(new Cor(76, 175, 80));
        TDB.mudarAzul(10, -15);
        TDB.mudarVermelho(22, 12);
        TDB.mudarVerde(25, 16);


        mPaleta = new Paleta();

        mPaleta.criar("Areia", new Cor(120, 50, 60));
        mPaleta.criar("Beta", new Cor(156, 39, 176));
        mPaleta.criar("Amarelo", new Cor(255, 193, 7));
        mPaleta.criar("AmareloEscuro", new Cor(200, 160, 40));
        mPaleta.criar("Vermelho", new Cor(244, 67, 54));
        mPaleta.criar("Verde", new Cor(76, 175, 80));
        mPaleta.criar("Agua", Cor.getRGB(getHexColor("#64b5f6")));
        mPaleta.criar("Iota", new Cor(63, 81, 181));
        mPaleta.criar("Omega", new Cor(255, 235, 59));


        mCron = new Cronometro(200);

        mAreia = new Lista<Particula>();
        mLiquido = new Lista<Particula>();


        mTamanho = 3;
        mTamanhoCopo = 10;

        mIndice = 0;
        mQuantos = 40;
        rd = new Random();

        mJogarAreia = 800;
        mJogarLiquido = 200;

    }


    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Fenomenos - libs.Luan Freitas");
    }

    @Override
    public void update(double dt) {

        TDA.atualizar();
        TDB.atualizar();

        mCron.esperar();


        if (mCron.foiEsperado()) {

            mAreia.adicionar(new Particula(mJogarAreia, -100, mTamanho));
            mLiquido.adicionar(new Particula(mJogarLiquido, -100, mTamanho));

            // System.out.println(" --->> INSERINDO");

            mIndice += 1;
            if (mIndice > mQuantos) {
                mIndice = 0;
                //  mEixoX += 100;
                //  mQuantos+=30;

                int sorteadoAreia = (rd.nextInt(20) * mTamanho) + 800;
                if (sorteadoAreia >= 800 && sorteadoAreia <= 1000) {
                    mJogarAreia = sorteadoAreia;
                }

                int sorteado = (rd.nextInt(50) * mTamanho) + 200;
                if (sorteado >= 100 && sorteado <= 500) {
                    mJogarLiquido = sorteado;
                }
            }

        }


        iterarAreia(mAreia);
        iterarLiquido(mLiquido);

    }

    public void iterarAreia(Lista<Particula> ls) {

        Iterador<Particula> mIterador = new Iterador<Particula>(ls);

        for (mIterador.iniciar(); mIterador.continuar(); mIterador.proximo()) {

            Particula mCorrente = mIterador.getValor();

            areia(mCorrente);

        }

    }

    public void iterarLiquido(Lista<Particula> ls) {

        Iterador<Particula> mIterador = new Iterador<Particula>(ls);

        for (mIterador.iniciar(); mIterador.continuar(); mIterador.proximo()) {

            Particula mCorrente = mIterador.getValor();

            liquido(mCorrente);

        }

    }


    public void areia(Particula mCorrente) {

        //System.out.println(" --->> AREIA  " + mCorrente.getID() + "  X = " + mCorrente.getX() + " Y = " + mCorrente.getY());

        if (mCorrente.getY() < 800) {

            if (colideCom(mAreia, mCorrente.getX(), mCorrente.getY() + mTamanho)) {

                //System.out.println("\t - Colidiu no CHAO");


                // System.out.println("ESQUERDA TENTAR :: X = " + (mCorrente.getX() - mTamanho) + " Y = " + mCorrente.getY());

                if (estaVazio(mAreia, mCorrente.getX() - mTamanho, mCorrente.getY())) {


                    //System.out.println("ESQUERDA VAZIA");

                    if (estaVazio(mAreia, mCorrente.getX() - mTamanho, mCorrente.getY() + mTamanho)) {
                        mCorrente.setX(mCorrente.getX() - mTamanho);
                        mCorrente.setY(mCorrente.getY() + mTamanho);
                    } else {

                        //  System.out.println("DIREITA TENTAR");
                        if (estaVazio(mAreia, mCorrente.getX() + mTamanho, mCorrente.getY())) {


                            //  System.out.println("DIREITA VAZIA");

                            if (estaVazio(mAreia, mCorrente.getX() + mTamanho, mCorrente.getY() + mTamanho)) {
                                mCorrente.setX(mCorrente.getX() + mTamanho);
                                mCorrente.setY(mCorrente.getY() + mTamanho);
                            }

                        }

                    }


                } else {

                    // System.out.println("DIREITA TENTAR");
                    if (estaVazio(mAreia, mCorrente.getX() + mTamanho, mCorrente.getY())) {


                        //   System.out.println("DIREITA VAZIA");

                        if (estaVazio(mAreia, mCorrente.getX() + mTamanho, mCorrente.getY() + mTamanho)) {
                            mCorrente.setX(mCorrente.getX() + mTamanho);
                            mCorrente.setY(mCorrente.getY() + mTamanho);
                        }

                    }

                }


            } else {
                mCorrente.setY(mCorrente.getY() + mTamanho);
                // System.out.println("Descendo ");
            }


        }

    }

    public void liquido(Particula mCorrente) {

        //System.out.println(" --->> AREIA  " + mCorrente.getID() + "  X = " + mCorrente.getX() + " Y = " + mCorrente.getY());

        int min_x = 100;
        int max_x = 500;

        if (mCorrente.getY() < 800) {

            if (colideCom(mLiquido, mCorrente.getX(), mCorrente.getY() + mTamanho)) {
                //System.out.println("\t - Colidiu no CHAO");

                int dir = rd.nextInt(100);

                if (dir < 50) {

                    if ((mCorrente.getX() - mTamanho) > min_x) {
                        if (estaVazio(mLiquido, mCorrente.getX() - mTamanho, mCorrente.getY())) {
                            mCorrente.setX(mCorrente.getX() - mTamanho);
                        } else {

                            if ((mCorrente.getX() + mTamanho) < max_x) {
                                if (estaVazio(mLiquido, mCorrente.getX() + mTamanho, mCorrente.getY())) {
                                    mCorrente.setX(mCorrente.getX() + mTamanho);
                                }
                            }
                        }
                    }

                } else {


                    if ((mCorrente.getX() + mTamanho) < max_x) {
                        if (estaVazio(mLiquido, mCorrente.getX() + mTamanho, mCorrente.getY())) {
                            mCorrente.setX(mCorrente.getX() + mTamanho);
                        } else {

                            if ((mCorrente.getX() - mTamanho) > min_x) {
                                if (estaVazio(mLiquido, mCorrente.getX() - mTamanho, mCorrente.getY())) {
                                    mCorrente.setX(mCorrente.getX() - mTamanho);
                                }
                            }

                        }
                    }

                }


            } else {
                mCorrente.setY(mCorrente.getY() + mTamanho);
                // System.out.println("Descendo ");
            }


        }

    }


    public boolean estaVazio(Lista<Particula> mParticulas, int ex, int ey) {
        boolean vazio = true;

        Iterador<Particula> mProcurando = new Iterador<Particula>(mParticulas);
        for (mProcurando.iniciar(); mProcurando.continuar(); mProcurando.proximo()) {
            Particula mCorrente = mProcurando.getValor();

            if (mCorrente.getX() >= ex && mCorrente.getX2() <= (ex + mTamanho)) {
                if (mCorrente.getY() >= ey && mCorrente.getY2() <= (ey + mTamanho)) {
                    vazio = false;
                    break;
                }
            }

        }

        return vazio;
    }

    public boolean colideCom(Lista<Particula> mParticulas, int ex, int ey) {

        boolean tem = false;

        Iterador<Particula> mProcurando = new Iterador<Particula>(mParticulas);
        for (mProcurando.iniciar(); mProcurando.continuar(); mProcurando.proximo()) {
            Particula mCorrente = mProcurando.getValor();

            if (mCorrente.getX() >= ex && mCorrente.getX2() <= (ex + mTamanho)) {
                if (mCorrente.getY() >= ey && mCorrente.getY2() <= (ey + mTamanho)) {
                    tem = true;
                    break;
                }
            }

        }

        return tem;
    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);

        desenharLiquido(mRenderizador);
        desenharAreia(mRenderizador);

    }

    public void desenharAreia(Renderizador mRenderizador) {

        Iterador<Particula> mIterador = new Iterador<Particula>(mAreia);

        for (mIterador.iniciar(); mIterador.continuar(); mIterador.proximo()) {

            Particula mCorrente = mIterador.getValor();
            //mRenderizador.drawQuad(mCorrente.getX(), mCorrente.getY(), mCorrente.getLargura() - 1, mCorrente.getAltura() - 1, mPaleta.getCor("Areia"));
            mRenderizador.drawRect_Pintado(mCorrente.getX(), mCorrente.getY(), mCorrente.getLargura(), mCorrente.getAltura(), mPaleta.getCor("Areia"));


        }

    }

    public void desenharLiquido(Renderizador mRenderizador) {

        Iterador<Particula> mIterador = new Iterador<Particula>(mLiquido);

        for (mIterador.iniciar(); mIterador.continuar(); mIterador.proximo()) {

            Particula mCorrente = mIterador.getValor();

            //  mRenderizador.drawQuad(mCorrente.getX(), mCorrente.getY(), mCorrente.getLargura()-1, mCorrente.getAltura()-1, mPaleta.getCor("Alfa"));
            mRenderizador.drawRect_Pintado(mCorrente.getX(), mCorrente.getY(), mCorrente.getLargura(), mCorrente.getAltura(), mPaleta.getCor("Agua"));

            desenharCopo(mRenderizador);

        }

    }

    public void desenharCopo(Renderizador mRenderizador) {

        int ex = 100;
        int ex2 = 500;

        int ey = 800-(mTamanhoCopo-mTamanho);


        for (int a = 0; a < 40; a++) {
            mRenderizador.drawRect_Pintado(ex + (a * mTamanhoCopo), ey + (mTamanhoCopo), mTamanhoCopo, mTamanhoCopo, mPaleta.getCor("Areia"));
        }

        ey = 510;
        for (int a = 0; a < 31; a++) {
            mRenderizador.drawRect_Pintado(ex, ey + (a * mTamanhoCopo), mTamanhoCopo, mTamanhoCopo, mPaleta.getCor("Areia"));
            mRenderizador.drawRect_Pintado(ex2, ey + (a * mTamanhoCopo), mTamanhoCopo, mTamanhoCopo, mPaleta.getCor("Areia"));
        }

    }

}

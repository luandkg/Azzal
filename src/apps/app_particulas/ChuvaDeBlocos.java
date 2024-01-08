package apps.app_particulas;

import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.geometria.Ponto;
import libs.azzal.geometria.Retangulo;
import libs.azzal.utilitarios.Colisor;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.Paleta;
import libs.luan.Iterador;
import libs.luan.Lista;

import java.awt.*;
import java.util.Random;


public class ChuvaDeBlocos extends Cena {


    private Paleta mPaleta;

    private Lista<Retangulo> mCaiu;
    private boolean tem;
    private Retangulo mCaindo;
    private int velocidade;
    private Cor mCor;
    Colisor eColisor;
    private Retangulo mChao;

    public ChuvaDeBlocos() {

        mCaiu = new Lista<Retangulo>();
        tem = false;


        mPaleta = new Paleta();
        mPaleta.criar("Alfa", new Cor(120, 50, 60));
        mPaleta.criar("Beta", new Cor(156, 39, 176));
        mPaleta.criar("Gama", new Cor(255, 193, 7));
        mPaleta.criar("Delta", new Cor(200, 160, 40));
        mPaleta.criar("Epsilon", new Cor(244, 67, 54));
        mPaleta.criar("Kapa", new Cor(76, 175, 80));
        mPaleta.criar("Zeta", new Cor(96, 125, 139));
        mPaleta.criar("Iota", new Cor(63, 81, 181));
        mPaleta.criar("Omega", new Cor(255, 235, 59));

        eColisor = new Colisor();
        mChao = new Retangulo(100, 600, 600, 50);

    }


    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Chuva De Blocos");
    }

    @Override
    public void update(double dt) {


        if (!tem) {
            Random rd = new Random();
            mCaindo = new Retangulo(100 + rd.nextInt(500), 0, 10, 10);
            tem = true;
            velocidade = 1 + rd.nextInt(12);

            if (velocidade >= 0 && velocidade < 3) {
                mCor = mPaleta.getCor("Alfa");
            } else if (velocidade >= 3 && velocidade < 6) {
                mCor = mPaleta.getCor("Gama");
            } else if (velocidade >= 6 && velocidade < 9) {
                mCor = mPaleta.getCor("Kapa");
            } else {
                mCor = mPaleta.getCor("Iota");
            }


        } else {


            int vel = velocidade;

            for (int v = 0; v < vel; v++) {

                if (mCaindo.getY() + mCaindo.getAltura() >= mChao.getY()) {
                    tem = false;
                    mCaiu.adicionar(mCaindo);
                    break;
                } else {


                    Iterador<Retangulo> mi = new Iterador<Retangulo>(mCaiu);
                    boolean continuar = true;

                    for (mi.iniciar(); mi.continuar(); mi.proximo()) {

                        boolean ret = eColisor.colisao_Retangulos(mCaindo, mi.getValor());


                        if (ret) {
                            continuar = false;
                            break;
                        }

                    }

                    if (continuar) {
                        mCaindo.setY(mCaindo.getY() + 1);
                    } else {
                        tem = false;
                        mCaiu.adicionar(mCaindo);
                        break;
                    }

                }

            }


        }

    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);


        if (tem) {
            mRenderizador.drawRect_Pintado(mCaindo, mCor);
        }

        Iterador<Retangulo> mi = new Iterador<Retangulo>(mCaiu);

        for (mi.iniciar(); mi.continuar(); mi.proximo()) {

            mRenderizador.drawRect_Pintado(mi.getValor(), mPaleta.getCor("Kapa"));

        }


        mRenderizador.drawRect_Pintado(mChao, mPaleta.getCor("Gama"));


        Retangulo mE = new Retangulo(200, 0, 500, 700);

        mRenderizador.espelhar(new Ponto(800, 300), mE);


    }


}

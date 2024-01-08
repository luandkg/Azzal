package libs.movimentador;

import libs.azzal.cenarios.Cena;
import libs.azzal.geometria.Ponto;
import libs.azzal.geometria.Retangulo;
import libs.azzal.Teclado;
import libs.azzal.utilitarios.*;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.organizadorq2d.OrganizadorQ2D;
import libs.organizadorq2d.Regiao;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;


public class QuadranteEspacial extends Cena {

    private int mTamanho;
    private int mQuadrante;

    private int mLargura;
    private int mAltura;

    private ArrayList<Corpo> mCorpos;

    private Corpo mJogador;

    private Teclado mTeclado;

    private OrganizadorQ2D mOrganizadorQ2D;

    private Cronometro eCron;

    public QuadranteEspacial() {

        mTamanho = 10;
        mQuadrante = 100;

        mLargura = 0;
        mAltura = 0;

        mCorpos = new ArrayList<Corpo>();

        mOrganizadorQ2D = new OrganizadorQ2D(mQuadrante);

        adicionar(1, 5, 5);
        adicionar(2, 5, 8);
        adicionar(3, 12, 5);
        adicionar(4, 20, 12);
        adicionar(5, 35, 14);
        adicionar(6, 10, 12);
        adicionar(7, 10, 8);
        adicionar(8, 40, 12);
        adicionar(9, 35, 12);

        adicionar(10, 49, 20);
        adicionar(11, 50, 8);
        adicionar(12, 52, 30);
        adicionar(13, 54, 40);


        mJogador = new Corpo(0, 50, 50);
        mCorpos.add(mJogador);

        // mObservacao = 100;

        eCron = new Cronometro(50);

    }

    public void adicionar(int eID, int ex, int ey) {

        Corpo ec = new Corpo(eID, ex, ey);

        Random eSorte = new Random();

        int v = eSorte.nextInt(100);

        ec.setMovimento(0, new MovimentoBurro());

        if (v >= 50) {
            ec.setMovimento(1, new MovimentoVertical());
        }

        if (v >= 60) {
            ec.setMovimento(2, new MovimentoLinear());
        }

        if (v >= 70) {
            ec.setMovimento(3, new MovimentoInteligente());
        }


        int eqx = (mLargura / mTamanho) - 2;
        int eqy = (mAltura / mTamanho) - 2;

        ec.setMin(1, 4);
        ec.setMax(eqx, eqy);

        mCorpos.add(ec);

    }


    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Quadrante Espacial");

        mTeclado = eWindows.getTeclado();

        mLargura = eWindows.getLargura();
        mAltura = eWindows.getAltura();

        Random eSorte = new Random();

        int eqx = (mLargura / mTamanho) - 2;
        int eqy = (mAltura / mTamanho) - 2;

        for (int g = 0; g < 40; g++) {

            //adicionar(14 + g, eSorte.nextInt(eqx), eSorte.nextInt(eqy));

        }

    }

    public void mover() {


        for (Corpo eCorpo : mCorpos) {
            if (eCorpo.getID() != mJogador.getID()) {

                int antesx = mJogador.getX();
                int antesy = mJogador.getY();

                eCorpo.mover();

                ArrayList<Corpo> mCorposAoRedor = new ArrayList<Corpo>();

                for (Object eObjeto : mOrganizadorQ2D.getObjetosAoRedor(eCorpo.getX() * mTamanho, eCorpo.getY() * mTamanho)) {
                    mCorposAoRedor.add((Corpo) eObjeto);
                }

                ColididoCom eColidiu = isColidiu(eCorpo, mCorposAoRedor);
                if (eColidiu.isColidiu()) {
                    eCorpo.setPos(antesx, antesy);
                }


            }
        }


    }

    @Override
    public void update(double dt) {

        mOrganizadorQ2D.limpar();

        for (Corpo eCorpo : mCorpos) {
            mOrganizadorQ2D.adicionar(eCorpo.getX() * mTamanho, eCorpo.getY() * mTamanho, eCorpo);
        }


        mover();


        int antesx = mJogador.getX();
        int antesy = mJogador.getY();

        eCron.esperar();
        if (eCron.foiEsperado()) {

            eCron.zerar();

            if (mTeclado.estaPressionado(KeyEvent.VK_LEFT)) {
                mJogador.setX(mJogador.getX() - 1);
            } else if (mTeclado.estaPressionado(KeyEvent.VK_RIGHT)) {
                mJogador.setX(mJogador.getX() + 1);
            } else if (mTeclado.estaPressionado(KeyEvent.VK_UP)) {
                mJogador.setY(mJogador.getY() - 1);
            } else if (mTeclado.estaPressionado(KeyEvent.VK_DOWN)) {
                mJogador.setY(mJogador.getY() + 1);
            }

        }

        ArrayList<Corpo> mCorposAoRedor = new ArrayList<Corpo>();

        for (Object eObjeto : mOrganizadorQ2D.getObjetosAoRedor(mJogador.getX() * mTamanho, mJogador.getY() * mTamanho)) {
            mCorposAoRedor.add((Corpo) eObjeto);
        }

        ColididoCom eColidiu = isColidiu(mJogador, mCorposAoRedor);

        if (eColidiu.isColidiu()) {

            Corpo eCorpo = (Corpo) eColidiu.getCom();

            System.out.println("Colidiu com " + eCorpo.getX() + " :: " + eCorpo.getY());

            mJogador.setX(antesx);
            mJogador.setY(antesy);

        }


    }

    public void mostrarRegioes() {

        for (Regiao eRegiao : mOrganizadorQ2D.getRegioes()) {

            //  System.out.println("Regiao Q : <" + eRegiao.getX() + ":" + eRegiao.getY() + ">");

            for (Object eObject : eRegiao.getObjetos()) {

                Corpo eCorpo = (Corpo) eObject;

                //  System.out.println("\t - Corpo  : <" + eCorpo.getX() + ":" + eCorpo.getY() + ">");

            }

        }

    }

    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(new Cor(255, 255, 255));

        int eqx = mLargura / mTamanho;
        int eqy = mAltura / mTamanho;


        for (Corpo eCorpo : mCorpos) {

            if (eCorpo.getTipo() == 0) {
                mRenderizador.drawRect_Pintado(new Retangulo(eCorpo.getX() * mTamanho, eCorpo.getY() * mTamanho, mTamanho, mTamanho), Cor.getHexCor("#B0C4DE"));

            } else if (eCorpo.getTipo() == 1) {

                mRenderizador.drawRect_Pintado(new Retangulo(eCorpo.getX() * mTamanho, eCorpo.getY() * mTamanho, mTamanho, mTamanho), Cor.getHexCor("#F08080"));

            } else {

                mRenderizador.drawRect_Pintado(new Retangulo(eCorpo.getX() * mTamanho, eCorpo.getY() * mTamanho, mTamanho, mTamanho), Cor.getHexCor("#aa14aa"));

            }

        }


        for (int y = 0; y < eqy; y++) {
            for (int x = 0; x < eqx; x++) {
                //    mRenderizador.drawRect(new Retangulo(x * mTamanho, y * mTamanho, mTamanho, mTamanho), Cor.getHexCor("#aa14aa"));
            }
        }


        eqx = mLargura / mQuadrante;
        eqy = mAltura / mQuadrante;

        for (int y = 0; y < eqy; y++) {
            for (int x = 0; x < eqx; x++) {


                mRenderizador.drawRect(new Retangulo(x * mQuadrante, y * mQuadrante, mQuadrante, mQuadrante), Cor.getHexCor("#DC143C"));


            }
        }


        Ponto eQuadranteJogador = getQuadranteCorpo(mJogador, mTamanho, mQuadrante);


        mRenderizador.drawRect(new Retangulo((eQuadranteJogador.getX() - 1) * mQuadrante, (eQuadranteJogador.getY() - 1) * mQuadrante, mQuadrante, mQuadrante), Cor.getHexCor("#4169E1"));
        mRenderizador.drawRect(new Retangulo((eQuadranteJogador.getX()) * mQuadrante, (eQuadranteJogador.getY() - 1) * mQuadrante, mQuadrante, mQuadrante), Cor.getHexCor("#4169E1"));
        mRenderizador.drawRect(new Retangulo((eQuadranteJogador.getX() + 1) * mQuadrante, (eQuadranteJogador.getY() - 1) * mQuadrante, mQuadrante, mQuadrante), Cor.getHexCor("#4169E1"));

        mRenderizador.drawRect(new Retangulo((eQuadranteJogador.getX() - 1) * mQuadrante, eQuadranteJogador.getY() * mQuadrante, mQuadrante, mQuadrante), Cor.getHexCor("#4169E1"));
        mRenderizador.drawRect(new Retangulo((eQuadranteJogador.getX() + 1) * mQuadrante, eQuadranteJogador.getY() * mQuadrante, mQuadrante, mQuadrante), Cor.getHexCor("#4169E1"));

        mRenderizador.drawRect(new Retangulo((eQuadranteJogador.getX() - 1) * mQuadrante, (eQuadranteJogador.getY() + 1) * mQuadrante, mQuadrante, mQuadrante), Cor.getHexCor("#4169E1"));
        mRenderizador.drawRect(new Retangulo((eQuadranteJogador.getX()) * mQuadrante, (eQuadranteJogador.getY() + 1) * mQuadrante, mQuadrante, mQuadrante), Cor.getHexCor("#4169E1"));
        mRenderizador.drawRect(new Retangulo((eQuadranteJogador.getX() + 1) * mQuadrante, (eQuadranteJogador.getY() + 1) * mQuadrante, mQuadrante, mQuadrante), Cor.getHexCor("#4169E1"));

        mRenderizador.drawRect(new Retangulo(eQuadranteJogador.getX() * mQuadrante, eQuadranteJogador.getY() * mQuadrante, mQuadrante, mQuadrante), Cor.getHexCor("#228B22"));


        // mRenderizador.drawRect(new Retangulo((mJogador.getX() * mTamanho) - mObservacao, (mJogador.getY() * mTamanho) - mObservacao, 2 * mObservacao, 2 * mObservacao), Cor.getHexCor("#FFA500"));


        mRenderizador.drawRect_Pintado(new Retangulo(mJogador.getX() * mTamanho, mJogador.getY() * mTamanho, mTamanho, mTamanho), Cor.getHexCor("#FF8C00"));

    }

    public void onDentro(Renderizador mRenderizador) {

        Ponto eQuadranteJogador = getQuadranteCorpo(mJogador, mTamanho, mQuadrante);

        onDrawDentro(mRenderizador, eQuadranteJogador.getX() - 1, eQuadranteJogador.getY() - 1);
        onDrawDentro(mRenderizador, eQuadranteJogador.getX(), eQuadranteJogador.getY() - 1);
        onDrawDentro(mRenderizador, eQuadranteJogador.getX() + 1, eQuadranteJogador.getY() - 1);


        onDrawDentro(mRenderizador, eQuadranteJogador.getX(), eQuadranteJogador.getY());
        onDrawDentro(mRenderizador, eQuadranteJogador.getX() - 1, eQuadranteJogador.getY());
        onDrawDentro(mRenderizador, eQuadranteJogador.getX() + 1, eQuadranteJogador.getY());

        onDrawDentro(mRenderizador, eQuadranteJogador.getX() - 1, eQuadranteJogador.getY() + 1);
        onDrawDentro(mRenderizador, eQuadranteJogador.getX(), eQuadranteJogador.getY() + 1);
        onDrawDentro(mRenderizador, eQuadranteJogador.getX() + 1, eQuadranteJogador.getY() + 1);


    }

    public void onDrawDentro(Renderizador mRenderizador, int eX, int eY) {

        // System.out.println("Quadrante <" + eX + " :: " + eY + ">" + " -->> " + mOrganizadorQ2D.getRegiao(eX, eY).getObjetos().size());

        for (Object eObjeto : mOrganizadorQ2D.getRegiao(eX, eY).getObjetos()) {

            Corpo eCorpo = (Corpo) eObjeto;

            mRenderizador.drawRect_Pintado(new Retangulo(eCorpo.getX() * mTamanho, eCorpo.getY() * mTamanho, mTamanho, mTamanho), Cor.getHexCor("#FFD700"));

            //   System.out.println("Passando por " + eCorpo.getX() + " -- " + eCorpo.getY());
        }

    }


    public Ponto getQuadranteCorpo(Corpo eJogador, int eTamanho, int eQuadrante) {
        int mQuadrantex = (eJogador.getX() * eTamanho) / eQuadrante;
        int mQuadrantey = (eJogador.getY() * eTamanho) / eQuadrante;

        return new Ponto(mQuadrantex, mQuadrantey);
    }


    public ColididoCom isColidiu(Corpo mOggo, ArrayList<Corpo> mOggos) {

        ColididoCom eColididoCom = new ColididoCom();

        int p = 0;

        for (Corpo eOggo : mOggos) {

            if ((eOggo.getID() != mOggo.getID())) {


                if ((eOggo.getX() * mTamanho) >= (mOggo.getX() * mTamanho) && ((eOggo.getX() + 1) * mTamanho) <= ((mOggo.getX() + 1) * mTamanho)) {
                    if ((eOggo.getY() * mTamanho) >= (mOggo.getY() * mTamanho) && ((eOggo.getY() + 1) * mTamanho) <= ((mOggo.getY() + 1) * mTamanho)) {
                        eColididoCom.colidir(eOggo);

                        //   System.out.println("Testando " + mOggo.getX() + "::" + mOggo.getY() + " com " + eOggo.getX() + "::" + eOggo.getY() + " - Bateu ");

                        System.out.println("Passou por : " + p);
                        break;
                    }
                }

                // System.out.println("Testando " + mOggo.getX() + "::" + mOggo.getY() + " com " + eOggo.getX() + "::" + eOggo.getY() + " - Nao ");


            }
            p += 1;
        }

        return eColididoCom;

    }

}

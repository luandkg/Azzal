package apps.appMetropole;

import azzal.cenarios.Cena;
import azzal.Teclado;
import azzal.Renderizador;
import azzal.Windows;

import java.awt.*;

import static java.awt.event.KeyEvent.*;


public class CidadeCena extends Cena {

    private Windows mWindows;
    private Teclado mTeclado;

    private Tanque mT1;
    private Tanque mT2;
    private Tanque mT3;

    private Cano mC1;
    private Cano mC2;
    private Controlador mControlador;
    private DQT2 mDQT2;
    private DQT2 mDQT2Reverso;

    private HiperCron mCron;

    private Cano mC3;
    private Cano mC4;
    private Cano mC5;
    private Cano mC6;


    private boolean isAutomatico;

    @Override
    public void iniciar(Windows eWindows) {

        mWindows = eWindows;
        mTeclado = eWindows.getTeclado();

        eWindows.setTitle("CidadeCena - libs.Luan Freitas");

        mT1 = new Tanque(50, 350, 500, 300);
        mT2 = new Tanque(1045, 750, 500, 0);
        mT3 = new Tanque(1185, 750, 500, 0);

        mC1 = new Cano(100, 340, 500, Sentido.Horizontal, Direcao.Frente, 10);
        mC1.adicionarEixosEquilibrados(50);

        mC2 = new Cano(630, 340, 500, Sentido.Horizontal, Direcao.Frente, 10);
        mC2.adicionarEixosEquilibrados(50);


        mControlador = new Controlador(600, 330, mC1, mC2);

        mDQT2 = new DQT2(1130, 340, Modo.Abaixo);
        mDQT2Reverso = new DQT2(1130, 830, Modo.Acima);

        mCron = new HiperCron(50);

        mT1.abrir();
        mControlador.abrir();
        mT2.abrir();

        mDQT2.abrir();
        mDQT2.abrirDireita();
        mDQT2.abrirEsquerda();

        mDQT2Reverso.abrir();
        mDQT2Reverso.abrirDireita();
        mDQT2Reverso.abrirEsquerda();


        mC3 = new Cano(20, 900, 1100, Sentido.Horizontal, Direcao.Traz, 10);
        mC3.adicionarEixosEquilibrados(50);

        mC4 = new Cano(20, 50, 850, Sentido.Vertical, Direcao.Traz, 10);
        mC4.adicionarEixosEquilibrados(50);

        mC5 = new Cano(20, 50, 50, Sentido.Horizontal, Direcao.Frente, 10);
        mC5.adicionarEixosEquilibrados(50);

        mC6 = new Cano(70, 50, 50, Sentido.Vertical, Direcao.Frente, 10);
        mC6.adicionarEixosEquilibrados(20);

        isAutomatico = true;

    }

    @Override
    public void update(double dt) {


        if (mTeclado.foiPressionado(VK_A)) {
            if (mControlador.isAberto()) {
                mControlador.fechar();
            } else {
                mControlador.abrir();
            }
        }

        if (mTeclado.foiPressionado(VK_B)) {
            isAutomatico = !isAutomatico;
        }


        if (mTeclado.foiPressionado(VK_C)) {
            if (mDQT2Reverso.isAberto()) {
                mDQT2Reverso.fechar();
            } else {
                mDQT2Reverso.abrir();
            }
        }


        mCron.esperar(dt);
        if (mCron.foiEsperado()) {

            mControlador.passar();

            mC1.passar();
            mC2.passar();
            mC3.passar();
            mC4.passar();
            mC5.passar();
            mC6.passar();

            mC3.conectarCano(mC4);
            mC4.conectarCano(mC5);
            mC5.conectarCano(mC6);

            mT1.conectar(Pressao.Saindo, mC1);
            // mT2.conectar(Pressao.Entrando, mC2);

            if (mT2.getVolume() > 20) {
                mDQT2Reverso.abrirEsquerda();
            } else {
                mDQT2Reverso.fecharEsquerda();
            }

            if (mT3.getVolume() > 20) {
                mDQT2Reverso.abrirDireita();
            } else {
                mDQT2Reverso.fecharDireita();
            }

            if (isAutomatico){
                if (mT2.getVolume() > 10 || mT3.getVolume() > 10) {
                    mDQT2Reverso.abrir();
                } else {
                    mDQT2Reverso.fechar();
                }
            }



            mDQT2.conectar(Pressao.Entrando, mC2);

            if (mDQT2.getEmissaoEsquerda() > 0) {
                mT2.colocar(mDQT2.getEmissaoEsquerda());
            }

            if (mDQT2.getEmissaoDireita() > 0) {
                mT3.colocar(mDQT2.getEmissaoDireita());
            }

            mDQT2Reverso.conectarSaindo(mT2, mT3, mC4);

            if (mDQT2Reverso.temPronto()) {
                int eQuantidade = mDQT2Reverso.retirarTudo();
                int voltar = mC3.entrar(eQuantidade);
                if (voltar > 0) {
                    mDQT2Reverso.deixarParado(voltar);
                }
            }
            //  mC1.passar();
        }

        // System.out.println(" ------------------------");
        for (EixoCano eEixo : mC6.getEixos()) {
            // System.out.println(eEixo.getPos() + " :: " + eEixo.isCheio());
        }

        EixoCano ultimoC6 = mC6.getEixos().get(mC6.getEixos().size() - 1);
        if (ultimoC6.isCheio()) {
            if (mT1.isAberto()) {
                mT1.colocar(ultimoC6.getQuantidade());
                ultimoC6.esvaziar();
            }
        }


    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);

        mT1.render(mRenderizador);
        mT2.render(mRenderizador);
        mT3.render(mRenderizador);

        mC1.render(mRenderizador);
        mC2.render(mRenderizador);
        mC3.render(mRenderizador);
        mC4.render(mRenderizador);
        mC5.render(mRenderizador);
        mC6.render(mRenderizador);

        mControlador.render(mRenderizador);

        mDQT2.render(mRenderizador);
        mDQT2Reverso.render(mRenderizador);

    }


}

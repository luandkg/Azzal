package apps.app_letrum;

import libs.azzal.geometria.Retangulo;
import libs.azzal.Renderizador;
import libs.azzal.Teclado;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.Cronometro;

import java.awt.event.KeyEvent;

public class WidgetTextBox {

    private int mX;
    private int mY;
    private String mTexto;
    private Cronometro mCron;
    private boolean mMostrarMarcador;
    private boolean mEstaEditando;
    private Fonte mLetramento;
    private Retangulo mArea;

    private int mPosicaoMarcador;
    private int mTamanhoComprimento;
    private boolean mTabulado;

    public WidgetTextBox(int eX, int eY, Fonte eLetramento) {

        mX = eX;
        mY = eY;
        mTexto = "";
        mCron = new Cronometro(500);
        mMostrarMarcador = true;
        mEstaEditando = false;
        mTamanhoComprimento = 0;
        mPosicaoMarcador = 0;
        mLetramento = eLetramento;
        mArea = new Retangulo(eX, eY, 1, 1);

        mTabulado = false;

    }

    public void update() {

        mCron.esperar();

        if (mCron.foiEsperado()) {
            mMostrarMarcador = !mMostrarMarcador;
        }

        mPosicaoMarcador = mLetramento.getLarguraDe(mTexto);
        mTamanhoComprimento = mPosicaoMarcador;

        if (mTamanhoComprimento < 30) {
            mTamanhoComprimento = 30;
        }

        mArea = new Retangulo(mX, mY, mTamanhoComprimento, mLetramento.getAltura());

    }

    public boolean estaDentro(int x, int y) {
        return mArea.isDentro(x, y);
    }


    public void render(Renderizador mRenderizador) {

        mLetramento.escreva(mX, mY, mTexto);


        mRenderizador.drawRect_Pintado(new Retangulo(mX, mY + 20, mTamanhoComprimento, 2), Cor.getHexCor("#8bc34a"));

        if (mEstaEditando && mMostrarMarcador) {

            int eMarcadorX = mX + 3 + mPosicaoMarcador;
            int eComecarY = mY;


            for (int y = 0; y < mLetramento.getAltura() + 10; y++) {
                mRenderizador.drawPixel(eMarcadorX, (eComecarY - 5) + y, new Cor(255, 0, 0));
            }

            for (int x = 0; x < 5; x++) {
                mRenderizador.drawPixel(eMarcadorX - x, (eComecarY) + mLetramento.getAltura() + 5, new Cor(255, 0, 0));
                mRenderizador.drawPixel(eMarcadorX - x, (eComecarY) + mLetramento.getAltura() + 6, new Cor(255, 0, 0));

                mRenderizador.drawPixel(eMarcadorX - x, (eComecarY) + mLetramento.getAltura() + 8, new Cor(255, 0, 0));
                mRenderizador.drawPixel(eMarcadorX - x, (eComecarY) + mLetramento.getAltura() + 9, new Cor(255, 0, 0));

            }

            for (int x = 0; x < 5; x++) {
                mRenderizador.drawPixel(eMarcadorX + x, (eComecarY) + mLetramento.getAltura() + 5, new Cor(255, 0, 0));
                mRenderizador.drawPixel(eMarcadorX + x, (eComecarY) + mLetramento.getAltura() + 6, new Cor(255, 0, 0));

                mRenderizador.drawPixel(eMarcadorX + x, (eComecarY) + mLetramento.getAltura() + 8, new Cor(255, 0, 0));
                mRenderizador.drawPixel(eMarcadorX + x, (eComecarY) + mLetramento.getAltura() + 9, new Cor(255, 0, 0));

            }


        }


    }

    public void setTexto(String eTexto) {
        mTexto = eTexto;
    }


    public String getTexto() {
        return mTexto;
    }


    public void editar() {
        mEstaEditando = true;
    }

    public void bloquear() {
        mEstaEditando = false;
    }

    public boolean estaEditando() {
        return mEstaEditando;
    }

    public void removaOUltimoEVolte() {


        if (mTexto.length() > 0) {
            mTexto = mTexto.substring(0, mTexto.length() - 1);
        }


    }


    public void receberTeclado(Teclado mTeclado) {

        if (mTeclado.foiPressionado(KeyEvent.VK_A)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "A");
            } else {
                setTexto(getTexto() + "a");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_B)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "B");
            } else {
                setTexto(getTexto() + "b");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_C)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "C");
            } else {
                setTexto(getTexto() + "c");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_D)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "D");
            } else {
                setTexto(getTexto() + "d");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_E)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "E");
            } else {
                setTexto(getTexto() + "e");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_F)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "F");
            } else {
                setTexto(getTexto() + "f");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_G)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "G");
            } else {
                setTexto(getTexto() + "g");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_H)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "H");
            } else {
                setTexto(getTexto() + "h");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_I)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "I");
            } else {
                setTexto(getTexto() + "i");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_J)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "J");
            } else {
                setTexto(getTexto() + "j");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_K)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "K");
            } else {
                setTexto(getTexto() + "k");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_L)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "L");
            } else {
                setTexto(getTexto() + "l");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_M)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "M");
            } else {
                setTexto(getTexto() + "m");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_N)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "N");
            } else {
                setTexto(getTexto() + "n");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_O)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "O");
            } else {
                setTexto(getTexto() + "o");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_P)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "P");
            } else {
                setTexto(getTexto() + "p");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_Q)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "Q");
            } else {
                setTexto(getTexto() + "q");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_R)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "R");
            } else {
                setTexto(getTexto() + "r");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_S)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "S");
            } else {
                setTexto(getTexto() + "s");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_T)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "T");
            } else {
                setTexto(getTexto() + "t");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_U)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "U");
            } else {
                setTexto(getTexto() + "u");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_V)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "V");
            } else {
                setTexto(getTexto() + "v");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_W)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "W");
            } else {
                setTexto(getTexto() + "w");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_X)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "X");
            } else {
                setTexto(getTexto() + "x");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_Y)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "Y");
            } else {
                setTexto(getTexto() + "y");
            }
        } else if (mTeclado.foiPressionado(KeyEvent.VK_Z)) {

            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                setTexto(getTexto() + "Z");
            } else {
                setTexto(getTexto() + "z");
            }

        } else if (mTeclado.foiPressionado(KeyEvent.VK_0)) {
            setTexto(getTexto() + "0");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_1)) {
            setTexto(getTexto() + "1");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_2)) {
            setTexto(getTexto() + "2");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_3)) {
            setTexto(getTexto() + "3");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_4)) {
            setTexto(getTexto() + "4");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_5)) {
            setTexto(getTexto() + "5");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_6)) {
            setTexto(getTexto() + "6");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_7)) {
            setTexto(getTexto() + "7");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_8)) {
            setTexto(getTexto() + "8");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_9)) {
            setTexto(getTexto() + "9");


        } else if (mTeclado.foiPressionado(KeyEvent.VK_NUMPAD0)) {
            setTexto(getTexto() + "0");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_NUMPAD1)) {
            setTexto(getTexto() + "1");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_NUMPAD2)) {
            setTexto(getTexto() + "2");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_NUMPAD3)) {
            setTexto(getTexto() + "3");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_NUMPAD4)) {
            setTexto(getTexto() + "4");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_NUMPAD5)) {
            setTexto(getTexto() + "5");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_NUMPAD6)) {
            setTexto(getTexto() + "6");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_NUMPAD7)) {
            setTexto(getTexto() + "7");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_NUMPAD8)) {
            setTexto(getTexto() + "8");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_NUMPAD9)) {
            setTexto(getTexto() + "9");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_SPACE)) {
            setTexto(getTexto() + " ");

        } else if (mTeclado.foiPressionado(KeyEvent.VK_PLUS)) {
            setTexto(getTexto() + "+");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_MINUS)) {
            setTexto(getTexto() + "-");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_ADD)) {
            setTexto(getTexto() + "+");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_SUBTRACT)) {
            setTexto(getTexto() + "-");

        } else if (mTeclado.foiPressionado(KeyEvent.VK_MULTIPLY)) {
            setTexto(getTexto() + "*");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_DIVIDE)) {
            setTexto(getTexto() + "/");
        } else if (mTeclado.foiPressionado(KeyEvent.VK_EQUALS)) {
            setTexto(getTexto() + "=");


        } else if (mTeclado.foiPressionado(KeyEvent.VK_BACK_SPACE)) {

            removaOUltimoEVolte();

        } else if (mTeclado.foiPressionado(KeyEvent.VK_ESCAPE)) {
            bloquear();
        } else if (mTeclado.foiPressionado(KeyEvent.VK_ALT)) {
            mTabulado = true;
        }


    }


    public boolean foiTabulado() {
        return mTabulado;
    }

    public void desTabular() {
        mTabulado = false;
    }
}

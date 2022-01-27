package AppKhronos;


import Azzal.Formatos.Retangulo;
import Azzal.Renderizador;
import Azzal.Teclado;
import Azzal.Utils.Cor;
import Azzal.Utils.Cronometro;
import Letrum.Fonte;

import java.awt.event.KeyEvent;

public class EditorDeTexto {

    private int mX;
    private int mY;
    private Cronometro mCron;
    private boolean mMostrarMarcador;
    private boolean mEstaEditando;
    private Fonte mLetramento;
    private Retangulo mArea;

    private int mPosicaoMarcador;
    private int mTamanhoComprimento;
    private boolean mTabulado;
    private OnRecebedorDeTeclado mRecebedor;

    private boolean mMostrarCursor;
private boolean mMostrarBarraInferior;

    public EditorDeTexto(int eX, int eY, Fonte eLetramento) {

        mX = eX;
        mY = eY;
        mCron = new Cronometro(500);
        mMostrarMarcador = true;
        mEstaEditando = false;
        mTamanhoComprimento = 0;
        mPosicaoMarcador = 0;
        mLetramento = eLetramento;
        mArea = new Retangulo(eX, eY, 1, 1);

        mTabulado = false;
        mRecebedor = new OnRecebedorDeTeclado();
        mRecebedor.simplificar();

        mMostrarCursor = true;
        mMostrarBarraInferior=true;
    }


    public void setMostrarCursor(boolean e) {
        mMostrarCursor = e;
    }
    public void setMostrarBarraInferior(boolean e) {
        mMostrarBarraInferior = e;
    }

    public boolean recebeuEnter() {
        return mRecebedor.recebeuEnter();
    }

    public void limpar() {
        mRecebedor.getDocumento().setTexto("");
        mRecebedor.getDocumento().organizarLinhas();
        mRecebedor.getDocumento().setCursorFim();
    }

    public void setEnterizar(boolean e) {
        mRecebedor.setEnterizar(e);
    }


    public void clicar(double dt, int px, int py, boolean ePrecionado) {
        if (ePrecionado) {

        }
    }

    public void update() {

        mCron.esperar();
        mRecebedor.update();

        if (mCron.foiEsperado()) {
            mMostrarMarcador = !mMostrarMarcador;
        }

        mPosicaoMarcador = mLetramento.getLarguraDe(mRecebedor.getDocumento().getTexto());
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

        mLetramento.setRenderizador(mRenderizador);

        mLetramento.escreva(mX, mY, mRecebedor.getDocumento().getTexto());

        if (mMostrarBarraInferior) {
            mRenderizador.drawRect_Pintado(new Retangulo(mX, mY + 20, mTamanhoComprimento, 2), Cor.getHexCor("#8bc34a"));
        }

        if (mMostrarCursor && mEstaEditando && mMostrarMarcador) {

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
        mRecebedor.getDocumento().setTexto(eTexto);
    }

    public void setCursorFim() {
        mRecebedor.getDocumento().organizarLinhas();
        mRecebedor.getDocumento().setCursorFim();
    }

    public String getTexto() {
        return mRecebedor.getDocumento().getTexto();
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


    public void receberTeclado(Teclado mTeclado) {

        mRecebedor.receberTeclado(mTeclado);

    }


    public boolean foiTabulado() {
        return mTabulado;
    }

    public void desTabular() {
        mTabulado = false;
    }
}


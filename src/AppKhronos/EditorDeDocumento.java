package AppKhronos;


import Azzal.Cores;
import Azzal.Formatos.Retangulo;
import Azzal.Renderizador;
import Azzal.Teclado;
import Azzal.Utils.Cor;
import Azzal.Utils.Cronometro;
import Letrum.Fonte;
import Letrum.Maker.FonteRunTime;
import LuanDKG.Texto;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EditorDeDocumento {

    private int mX;
    private int mY;
    private Cronometro mCron;

    private boolean mMostrarCursor;
    private boolean mMostrarMarcador;

    private boolean mEstaEditando;

    private Fonte mTextoNormal;
    private Fonte mNumeroSelecionado;



    private int mTamanhoComprimento;
    private boolean mTabulado;
    private boolean mTemSintaxe;
    private Sintaxer mSintaxer;

    private int initLinha;
    private int maxLinhas;

    private OnRecebedorDeTeclado mRecebedor;

    private String mFonte_Nome;
    private int mFonte_Tamanho;

    public EditorDeDocumento(int eX, int eY ) {

        mX = eX;
        mY = eY;
        mCron = new Cronometro(500);

        mMostrarMarcador = true;
        mEstaEditando = false;
        mTamanhoComprimento = 0;

        mFonte_Nome="Ubuntu";
        mFonte_Tamanho= FonteRunTime.getTamanhoMedio();

        mTextoNormal = new FonteRunTime(Cores.hexToCor("#ebdbb2"), mFonte_Nome, mFonte_Tamanho);
        mNumeroSelecionado = new FonteRunTime(Cores.hexToCor("#cc241d"), mFonte_Nome,mFonte_Tamanho);

        mTabulado = false;

        mTemSintaxe = false;
        mSintaxer = null;


        mRecebedor = new OnRecebedorDeTeclado();

        initLinha = 0;
        maxLinhas = 48;

        mMostrarCursor = true;
    }

    public String getFonteNome(){return mFonte_Nome;}
    public int getFonteTamanho(){return mFonte_Tamanho;}


    public void clicar(double dt, int px, int py, boolean isPressionado) {
        mRecebedor.getDocumento().clicar(initLinha, px, py, isPressionado, mX, mY, mTextoNormal);
    }

    public void organizarTexto(){
        mRecebedor.getDocumento().organizarLinhas();
    }

    public void update() {

        mCron.esperar();
        mRecebedor.update();


        if (mCron.foiEsperado()) {
            mMostrarMarcador = !mMostrarMarcador;
        }


        mTamanhoComprimento = 30;

        mRecebedor.getDocumento().organizarLinhas();



        int lo = initLinha + maxLinhas;

        if (mRecebedor.getDocumento().getCurY() > lo) {

            System.out.println("Cur          -->> " + mRecebedor.getDocumento().getCurY() + " : " + mRecebedor.getDocumento().getCurX());
            System.out.println("Linha Inicio -->> " + initLinha);
            System.out.println("Linha Final  -->> " + mRecebedor.getDocumento().getLinhas().size());

            initLinha = 0;
            System.out.println("Vamos nivelar ...");

            initLinha = ((mRecebedor.getDocumento().getCurY() + 1) - maxLinhas);

            System.out.println("Linha Inicio -->> " + initLinha);

        }

    }


    public void setCursorFim() {
        mRecebedor.getDocumento().setCursorFim();
    }




    public void mostrarCursor(boolean e) {
        mMostrarCursor = e;
    }

    public void render(Renderizador mRenderizador) {

        mTextoNormal.setRenderizador(mRenderizador);
        mNumeroSelecionado.setRenderizador(mRenderizador);


        // mRenderizador.drawLinha(mX, mY, mX, mY + 200, new Cor(255, 0, 0));
        //mRenderizador.drawLinha(mX, mY, mX + 200, mY, new Cor(255, 0, 0));

        //int y_nivel = 4;
        //for (int l = 0; l < 30; l++) {
        // mRenderizador.drawLinha(mX, mY + y_nivel + (l * mLetramento.getAltura()), mX + 200, mY + y_nivel + (l * mLetramento.getAltura()), new Cor(255, 0, 0));
        //mLetramento.escreva(mX - 20, mY + y_nivel + (l * mLetramento.getAltura()), String.valueOf(l));
        //}
        //  mRenderizador.drawPixel(sel_x, sel_y, new Cor(255, 255, 0));
        int li = 0;

        int ultima_linha = initLinha + maxLinhas;

        if (mTemSintaxe) {


            int corrente_y = mY;
            for (String linha : mRecebedor.getDocumento().getLinhas()) {
                if (li >= initLinha && li < ultima_linha) {
                    mSintaxer.onRender(mRenderizador, linha, mX, corrente_y);

                    if (li == mRecebedor.getDocumento().getCurY()){
                        mNumeroSelecionado.escreva(mX - 40, corrente_y, String.valueOf(li));
                    }else{
                        mTextoNormal.escreva(mX - 40, corrente_y, String.valueOf(li));
                    }

                    corrente_y += mTextoNormal.getAltura();
                }
                li += 1;
            }

        } else {
            int corrente_y = mY;
            for (String linha : mRecebedor.getDocumento().getLinhas()) {
                if (li >= initLinha && li < ultima_linha) {
                    mTextoNormal.escreva(mX, corrente_y, linha);

                    mTextoNormal.escreva(mX - 40, corrente_y, String.valueOf(li));

                    corrente_y += mTextoNormal.getAltura();
                }
                li += 1;
            }
        }


        // mRenderizador.drawRect_Pintado(new Retangulo(mX, mY + 20, mTamanhoComprimento, 2), Cor.getHexCor("#8bc34a"));

        if (mMostrarCursor && mEstaEditando && mMostrarMarcador) {

            int eMarcadorX = mX;
            int eComecarY = mY;

            // System.out.println("des :: " + cur_y + ":" + cur_x);

            if (mRecebedor.getDocumento().getCurY() < mRecebedor.getDocumento().getLinhas().size()) {

                int recuar_y = mRecebedor.getDocumento().getCurY() - initLinha;

                eComecarY += (recuar_y * mTextoNormal.getAltura());

                String l = mRecebedor.getDocumento().getLinhas().get(mRecebedor.getDocumento().getCurY());

                if (mRecebedor.getDocumento().getCurX() > 0) {
                    if (mRecebedor.getDocumento().getCurX() < l.length()) {
                        eMarcadorX += mTextoNormal.getLarguraDe(l.substring(0, mRecebedor.getDocumento().getCurX()));
                    } else if (mRecebedor.getDocumento().getCurX() == l.length()) {
                        eMarcadorX += mTextoNormal.getLarguraDe(l);
                    }
                }

            }


            for (int y = 0; y < mTextoNormal.getAltura() + 10; y++) {
                mRenderizador.drawPixel(eMarcadorX, (eComecarY - 5) + y, new Cor(255, 0, 0));
                mRenderizador.drawPixel(eMarcadorX + 1, (eComecarY - 5) + y, new Cor(255, 0, 0));
            }

            for (int x = 0; x < 5; x++) {
                mRenderizador.drawPixel(eMarcadorX - x, (eComecarY) + mTextoNormal.getAltura() + 5, new Cor(255, 0, 0));
                mRenderizador.drawPixel(eMarcadorX - x, (eComecarY) + mTextoNormal.getAltura() + 6, new Cor(255, 0, 0));

                mRenderizador.drawPixel(eMarcadorX - x, (eComecarY) + mTextoNormal.getAltura() + 8, new Cor(255, 0, 0));
                mRenderizador.drawPixel(eMarcadorX - x, (eComecarY) + mTextoNormal.getAltura() + 9, new Cor(255, 0, 0));

            }

            for (int x = 0; x < 5; x++) {
                mRenderizador.drawPixel(eMarcadorX + x, (eComecarY) + mTextoNormal.getAltura() + 5, new Cor(255, 0, 0));
                mRenderizador.drawPixel(eMarcadorX + x, (eComecarY) + mTextoNormal.getAltura() + 6, new Cor(255, 0, 0));

                mRenderizador.drawPixel(eMarcadorX + x, (eComecarY) + mTextoNormal.getAltura() + 8, new Cor(255, 0, 0));
                mRenderizador.drawPixel(eMarcadorX + x, (eComecarY) + mTextoNormal.getAltura() + 9, new Cor(255, 0, 0));

            }


        }


    }

    public void setTexto(String eTexto) {
        mRecebedor.getDocumento().setTexto(eTexto);
    }


    public String getTexto() {
        return mRecebedor.getDocumento().getTexto();
    }

    public void setSintaxer(Sintaxer eSintaxer) {
        mTemSintaxe = true;
        mSintaxer = eSintaxer;
    }

    public void semSintaxer(){
        mTemSintaxe = false;
        mSintaxer = null;
    }

    public void editar() {
        mEstaEditando = true;
    }

    public void receberTeclado(Teclado mTeclado) {
        mRecebedor.receberTeclado(mTeclado);
    }

    public void bloquear() {
        mEstaEditando = false;
    }

    public boolean estaEditando() {
        return mEstaEditando;
    }


    public boolean foiTabulado() {
        return mTabulado;
    }

    public void desTabular() {
        mTabulado = false;
    }

    public void visualizar_subir() {
        if (initLinha > 0) {
            initLinha -= 1;
        }
        if (mRecebedor.getDocumento().getCurY() > 0) {
            mRecebedor.getDocumento().setCurY(mRecebedor.getDocumento().getCurY() - 1);
        }
    }

    public void visualizar_descer() {
        if (initLinha + maxLinhas < mRecebedor.getDocumento().getLinhas().size()) {
            initLinha += 1;
        }
        if (mRecebedor.getDocumento().getCurY() < mRecebedor.getDocumento().getLinhas().size()) {
            mRecebedor.getDocumento().setCurY(mRecebedor.getDocumento().getCurY() + 1);
        }
    }

    public void visualizar_primeira_linha(){
        initLinha=0;
        mRecebedor.getDocumento().setCurY(0);
    }

    public void visualizar_ultima_linha(){

        organizarTexto();
        initLinha = mRecebedor.getDocumento().getLinhas().size()-maxLinhas;
        if (initLinha<0){
            initLinha=0;
        }
        mRecebedor.getDocumento().setCurX(0);
        mRecebedor.getDocumento().setCurY(initLinha);

    }

    public boolean deveSair() {
        return mRecebedor.deveSair();
    }

    public void voltar() {
        mRecebedor.voltar();
    }
}


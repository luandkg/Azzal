package apps.AppKhronos;

import azzal.Teclado;
import azzal.Utils.Cronometro;

import java.awt.event.KeyEvent;

public class OnRecebedorDeTeclado {

    private String mais;
    private Cronometro mCronTeclado;
    private OnDocumento mOnDocumento;
    private boolean mSimples;
    private boolean mEnter;
    private boolean mDeveSair;

    public OnRecebedorDeTeclado() {
        mCronTeclado = new Cronometro(100);
        mais = "";
        mOnDocumento = new OnDocumento();
        mSimples = false;
        mEnter = false;
        mDeveSair = false;

    }

    public void simplificar() {
        mSimples = true;
    }

    public void setEnterizar(boolean e) {
        mEnter = e;
    }

    public boolean recebeuEnter() {
        return mEnter;
    }

    public void update() {
        mCronTeclado.esperar();

        if (mCronTeclado.foiEsperado()) {

        }
    }

    public OnDocumento getDocumento() {
        return mOnDocumento;
    }

    public boolean getTecla(Teclado mTeclado, int eTecla, String eValorMin, String eValorMai) {
        boolean ret = false;
        if (mTeclado.foiPressionado(eTecla)) {
            ret = true;
            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                mais = eValorMai;
                mCronTeclado.zerar();
            } else {
                mais = eValorMin;
                mCronTeclado.zerar();
            }
        } else {

        }


        return ret;
    }

    public boolean getTeclaControl(Teclado mTeclado, int eTecla, String eValorMin, String eValorMai) {
        boolean ret = false;
        if (mTeclado.foiPressionado(eTecla)) {
            ret = true;
            if (mTeclado.estaPressionado(KeyEvent.VK_CONTROL)) {
                mais = eValorMai;
                mCronTeclado.zerar();
            } else {
                mais = eValorMin;
                mCronTeclado.zerar();
            }
        }
        return ret;
    }

    public boolean getTeclaControlUnica(Teclado mTeclado, int eTecla, String eValorMin) {
        boolean ret = false;
        if (mTeclado.foiPressionado(eTecla)) {
            if (mTeclado.estaPressionado(KeyEvent.VK_CONTROL)) {
                mais = eValorMin;
                mCronTeclado.zerar();
                ret = true;
            } else {
                mTeclado.voltarParaPressionar(eTecla);
            }
        }
        return ret;
    }


    public boolean getTeclaShift(Teclado mTeclado, int eTecla, String eValorMin, String eValorMai) {
        boolean ret = false;
        if (mTeclado.foiPressionado(eTecla)) {
            ret = true;
            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                mais = eValorMai;
                mCronTeclado.zerar();
            } else {
                mais = eValorMin;
                mCronTeclado.zerar();
            }
        }
        return ret;
    }

    public boolean getTeclaUnica(Teclado mTeclado, int eTecla, String eValor) {
        boolean ret = false;
        if (mTeclado.foiPressionado(eTecla)) {
            ret = true;
            mais = eValor;
            mCronTeclado.zerar();
        }
        return ret;
    }


    public boolean getTeclaShiftada(Teclado mTeclado, int eTecla, String eValor) {
        boolean ret = false;
        if (mTeclado.foiPressionado(eTecla)) {
            if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT)) {
                mais = eValor;
                mCronTeclado.zerar();
                ret = true;
            } else {
                mTeclado.voltarParaPressionar(eTecla);
            }
        }
        return ret;
    }


    public boolean getTeclaPressionada(Teclado mTeclado, int eTecla, String eValorMin, String eValorMai) {
        boolean ret = false;

        if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT) && mTeclado.estaPressionado(eTecla)) {
            mais = eValorMai;
            mCronTeclado.zerar();
        } else if (!mTeclado.estaPressionado(KeyEvent.VK_SHIFT) && mTeclado.estaPressionado(eTecla)) {
            mais = eValorMin;
            mCronTeclado.zerar();
        }

        return ret;
    }

    public boolean getTeclaControlPressionada(Teclado mTeclado, int eTecla, String eValorMin, String eValorMai) {
        boolean ret = false;

        if (mTeclado.estaPressionado(KeyEvent.VK_CONTROL) && mTeclado.estaPressionado(eTecla)) {
            mais = eValorMai;
            mCronTeclado.zerar();
        } else if (!mTeclado.estaPressionado(KeyEvent.VK_CONTROL) && mTeclado.estaPressionado(eTecla)) {
            mais = eValorMin;
            mCronTeclado.zerar();
        }

        return ret;
    }

    public boolean getTeclaShiftPressionada(Teclado mTeclado, int eTecla, String eValorMin, String eValorMai) {
        boolean ret = false;

        if (mTeclado.estaPressionado(KeyEvent.VK_SHIFT) && mTeclado.estaPressionado(eTecla)) {
            mais = eValorMai;
            mCronTeclado.zerar();
        } else if (!mTeclado.estaPressionado(KeyEvent.VK_SHIFT) && mTeclado.estaPressionado(eTecla)) {
            mais = eValorMin;
            mCronTeclado.zerar();
        } else {

        }

        return ret;
    }

    public boolean getTeclaUnicaPressionada(Teclado mTeclado, int eTecla, String eValor) {
        boolean ret = false;

        if (mTeclado.estaPressionado(eTecla)) {
            mais = eValor;
            mCronTeclado.zerar();
            ret = true;
        }

        return ret;
    }

    public boolean deveSair() {
        return mDeveSair;
    }

    public void voltar() {
        mDeveSair = false;
    }

    public void receberTeclado(Teclado mTeclado) {

        mais = "";
        boolean zerar = false;


        if (getTeclaControlUnica(mTeclado, KeyEvent.VK_1, "")) {
            zerar = true;
            mDeveSair = true;
            return;
        } else if (getTecla(mTeclado, KeyEvent.VK_A, "a", "A")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_B, "b", "B")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_C, "c", "C")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_D, "d", "D")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_E, "e", "E")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_F, "f", "F")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_G, "g", "G")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_H, "h", "H")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_I, "i", "I")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_J, "j", "J")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_K, "k", "K")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_L, "l", "L")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_M, "m", "M")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_N, "n", "N")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_O, "o", "O")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_P, "p", "P")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_Q, "q", "Q")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_R, "r", "R")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_S, "s", "S")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_T, "t", "T")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_U, "u", "U")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_V, "v", "V")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_W, "w", "W")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_X, "x", "X")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_Y, "y", "Y")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_Z, "z", "Z")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_SPACE, " ")) {

        } else if (getTeclaShift(mTeclado, KeyEvent.VK_0, "0", ")")) {
        } else if (getTeclaShift(mTeclado, KeyEvent.VK_1, "1", "!")) {
        } else if (getTeclaShift(mTeclado, KeyEvent.VK_2, "2", "@")) {
        } else if (getTeclaShift(mTeclado, KeyEvent.VK_3, "3", "#")) {
        } else if (getTeclaShift(mTeclado, KeyEvent.VK_4, "4", "$")) {
        } else if (getTeclaShift(mTeclado, KeyEvent.VK_5, "5", "%")) {
        } else if (getTeclaShift(mTeclado, KeyEvent.VK_5, "6", "¨")) {
        } else if (getTeclaShift(mTeclado, KeyEvent.VK_7, "7", "&")) {
        } else if (getTeclaShift(mTeclado, KeyEvent.VK_8, "8", "*")) {
        } else if (getTeclaShift(mTeclado, KeyEvent.VK_9, "9", "(")) {

        } else if (getTeclaShift(mTeclado, KeyEvent.VK_QUOTE, "'", "\"")) {

        } else if (getTeclaShift(mTeclado, KeyEvent.VK_OPEN_BRACKET, "[", "{")) {
        } else if (getTeclaShift(mTeclado, KeyEvent.VK_CLOSE_BRACKET, "]", "}")) {
        } else if (getTeclaShift(mTeclado, KeyEvent.VK_SLASH, "/", "?")) {

        } else if (getTeclaShift(mTeclado, KeyEvent.VK_EQUALS, "=", "+")) {


        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_0, "0")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_1, "1")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_2, "2")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_3, "3")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_4, "4")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_5, "5")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_6, "6")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_7, "7")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_8, "8")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_9, "9")) {

        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_NUMPAD0, "0")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_NUMPAD1, "1")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_NUMPAD2, "2")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_NUMPAD3, "3")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_NUMPAD4, "4")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_NUMPAD5, "5")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_NUMPAD6, "6")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_NUMPAD7, "7")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_NUMPAD8, "8")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_NUMPAD9, "9")) {

        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_PLUS, "+")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_MINUS, "-", "_")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_ADD, "+")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_SUBTRACT, "-")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_MULTIPLY, "*")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_DIVIDE, "/")) {
            //} else if (getTeclaUnica(mTeclado, KeyEvent.VK_EQUALS, "=")) {


        } else if (getTecla(mTeclado, KeyEvent.VK_COMMA, ",", "<")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_PERIOD, ".", ">")) {
        } else if (getTecla(mTeclado, KeyEvent.VK_SEMICOLON, ";", ":")) {


        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_TAB, "\t")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_ALT, "\t")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_ENTER, "\n")) {
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_BACK_SPACE, " ")) {
            mOnDocumento.removaOUltimoEVolte();
            zerar = true;


        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_RIGHT, " ")) {
            mOnDocumento.irDireita();
            zerar = true;
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_LEFT, " ")) {
            mOnDocumento.irEsquerda();
            zerar = true;
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_UP, " ")) {
            mOnDocumento.irSubir();
            zerar = true;
        } else if (getTeclaUnica(mTeclado, KeyEvent.VK_DOWN, " ")) {
            mOnDocumento.irDescer();
            zerar = true;

        }


        if (mais.length() == 0 && mCronTeclado.foiEsperado()) {

            if (getTeclaPressionada(mTeclado, KeyEvent.VK_A, "a", "A")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_B, "b", "B")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_C, "c", "C")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_D, "d", "D")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_E, "e", "E")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_F, "f", "F")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_G, "g", "G")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_H, "h", "H")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_I, "i", "I")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_J, "j", "J")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_K, "k", "K")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_L, "l", "L")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_M, "m", "M")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_N, "n", "N")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_O, "o", "O")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_P, "p", "P")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_Q, "q", "Q")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_R, "r", "R")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_S, "s", "S")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_T, "t", "T")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_U, "u", "U")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_V, "v", "V")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_W, "w", "W")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_X, "x", "X")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_Y, "y", "Y")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_Z, "z", "Z")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_SPACE, " ")) {


            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_0, "0", ")")) {
            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_1, "1", "!")) {
            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_2, "2", "@")) {
            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_3, "3", "#")) {
            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_4, "4", "$")) {
            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_5, "5", "%")) {
            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_5, "6", "¨")) {
            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_7, "7", "&")) {
            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_8, "8", "*")) {
            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_9, "9", "(")) {

            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_QUOTE, "'", "\"")) {

            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_OPEN_BRACKET, "[", "{")) {
            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_CLOSE_BRACKET, "]", "}")) {
            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_SLASH, "/", "?")) {

            } else if (getTeclaShiftPressionada(mTeclado, KeyEvent.VK_EQUALS, "=", "+")) {


            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_0, "0")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_1, "1")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_2, "2")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_3, "3")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_4, "4")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_5, "5")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_6, "6")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_7, "7")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_8, "8")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_9, "9")) {

            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_NUMPAD0, "0")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_NUMPAD1, "1")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_NUMPAD2, "2")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_NUMPAD3, "3")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_NUMPAD4, "4")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_NUMPAD5, "5")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_NUMPAD6, "6")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_NUMPAD7, "7")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_NUMPAD8, "8")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_NUMPAD9, "9")) {

            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_PLUS, "+")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_MINUS, "-", "_")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_ADD, "+")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_SUBTRACT, "-")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_MULTIPLY, "*")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_DIVIDE, "/")) {
                // } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_EQUALS, "=")) {

            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_COMMA, ",", "<")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_PERIOD, ".", ">")) {
            } else if (getTeclaPressionada(mTeclado, KeyEvent.VK_SEMICOLON, ";", ":")) {


            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_QUOTE, "\"")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_TAB, "\t")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_ALT, "\t")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_ENTER, "\n")) {
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_BACK_SPACE, " ")) {
                mOnDocumento.removaOUltimoEVolte();
                zerar = true;

            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_RIGHT, " ")) {
                mOnDocumento.irDireita();
                zerar = true;
            } else if (getTeclaUnicaPressionada(mTeclado, KeyEvent.VK_LEFT, " ")) {
                mOnDocumento.irEsquerda();
                zerar = true;
            }

        }

        if (zerar) {
            mais = "";
        }


        if (mais.length() > 0) {

            if (mais.contentEquals("\n")) {
                mEnter = true;
            } else {
                mEnter = false;
            }

            if (mSimples && mais.contentEquals("\n")) {
                return;
            }

            mOnDocumento.organizarLinhas();

            System.out.println("-------------------------------");
            System.out.println("VALOR  :: " + mais);
            System.out.println("cursor :: " + mOnDocumento.getCurY() + " : " + mOnDocumento.getCurX());

            int pos = mOnDocumento.irAteCursor();

            System.out.println("mod 1  :: " + pos);


            String mTexto = mOnDocumento.inserir(mOnDocumento.getTexto(), pos, mais);
            mOnDocumento.setTexto(mTexto);

            if (mais.length()>0){
                mOnDocumento.organizarLinhas();
            }

            mOnDocumento.setCurX(mOnDocumento.getCurX() + 1);

            if (mais.contentEquals("\n")) {
                mOnDocumento.setCurX(0);
                mOnDocumento.setCurY(mOnDocumento.getCurY() + 1);
            }

            pos = mOnDocumento.irAteCursor();
            System.out.println("mod 2  :: " + pos);
            System.out.println("cursor :: " + mOnDocumento.getCurY() + " : " + mOnDocumento.getCurX());


        }

    }
}

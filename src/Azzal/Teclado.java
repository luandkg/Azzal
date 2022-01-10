package Azzal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {


    private boolean[] mPassado;
    private boolean[] mAgora;
    private boolean[] mSegurando;

    public Teclado() {

        mPassado = new boolean[255];
        mAgora = new boolean[255];
        mSegurando = new boolean[255];

        for (int tecla = 0; tecla < 255; tecla++) {

            mPassado[tecla] = false;
            mAgora[tecla] = false;
            mSegurando[tecla] = false;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int tecla = e.getKeyCode();

        if (tecla >= 0 && tecla < 255) {

            mSegurando[tecla] = false;
            mPassado[tecla] = false;
            mAgora[tecla] = false;

        }


    }

    @Override
    public void keyPressed(KeyEvent e) {

        int tecla = e.getKeyCode();

        if (tecla >= 0 && tecla < 255) {

            if (mAgora[tecla]) {
                mPassado[tecla] = true;
            }

            if (!mAgora[tecla]) {
                mPassado[tecla] = false;
                mAgora[tecla] = true;
            }

            mSegurando[tecla] = true;

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public boolean foiPressionado(int eTecla) {

        if (eTecla >= 0 && eTecla < 255) {
            if (mAgora[eTecla] == true && mPassado[eTecla] == false) {
                mPassado[eTecla] = true;
                return true;
            }
        }

        return false;
    }


    public boolean estaPressionado(int eTecla) {

        if (eTecla >= 0 && eTecla < 255) {
                return mSegurando[eTecla] == true;
        }

        return false;
    }


}

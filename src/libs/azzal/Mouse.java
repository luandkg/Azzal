package libs.azzal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

    private boolean isPressionado;
    private boolean isClicado;
    private boolean isMovendo;

    private int mX;
    private int mY;

    private int dX;
    private int dY;

    public Mouse() {
        isPressionado = false;
        isClicado = false;
        isMovendo = false;
        dX = 0;
        dY = 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isPressionado = true;
        isClicado = true;
        isMovendo = false;

        mX = e.getX();
        mY = e.getY();
        dX = 0;
        dY = 0;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mX = e.getX();
        mY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        isClicado = true;
        isMovendo = false;

        mX = e.getX();
        mY = e.getY();

        dX = 0;
        dY = 0;

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dX = e.getX();
        dY = e.getY();
        isMovendo = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isPressionado = false;
        isMovendo = false;
        isClicado = false;
    }

    public boolean isPressed() {
        return isPressionado;
    }

    public boolean isClicked() {
        return isClicado;
    }

    public boolean isMovendo() {
        return isMovendo;
    }

    public void liberar() {
        isClicado = false;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public int getDeltaX() {
        return dX - mX;
    }

    public int getDeltaY() {
        return dY - mY;
    }

    public int getMovendoX(){
        return getX() + getDeltaX();
    }

    public int getMovendoY(){
        return getY() + getDeltaY();
    }
}

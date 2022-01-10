package Azzal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

    private boolean isPressed;
    private boolean isClicked;
    private boolean isMovendo;

    private int mX;
    private int mY;

    private int dX;
    private int dY;

    public Mouse() {
        isPressed = false;
        isClicked = false;
        isMovendo = false;
        dX = 0;
        dY = 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isPressed = true;
        isClicked = true;
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
        isClicked = true;
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
        isPressed = false;
        isMovendo = false;
        isClicked = false;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public boolean isMovendo() {
        return isMovendo;
    }

    public void liberar() {
        isClicked = false;
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

}

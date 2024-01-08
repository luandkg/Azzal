package libs.azzal.geometria;

public class Oval {

    private int mX;
    private int mY;

    private int mRaioLargura;
    private int mRaioAltura;

    public Oval() {
        mX = 0;
        mY = 0;

        mRaioLargura = 100;
        mRaioAltura = 100;

    }

    public Oval(int eX, int eY) {
        mX = eX;
        mY = eY;

        mRaioLargura = 100;
        mRaioAltura = 100;

    }

    public Oval(int eX, int eY, int eRaioLargura, int eRaioAltura) {
        mX = eX;
        mY = eY;

        mRaioLargura = eRaioLargura;
        mRaioAltura = eRaioAltura;

    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public int getRaioLargura() {
        return mRaioLargura;
    }

    public int getRaioAltura() {
        return mRaioAltura;
    }

    public int getDiametroLargura() {
        return mRaioLargura * 2;
    }

    public int getDiametroAltura() {
        return mRaioAltura * 2;
    }

    public void setX(int eX) {
        mX = eX;
    }

    public void setY(int eY) {
        mY = eY;
    }

    public void setRaioLargura(int eRaioLargura) {
        mRaioLargura = eRaioLargura;
    }

    public void setRaioAltura(int eRaioAltura) {
        mRaioAltura = eRaioAltura;
    }

    public Ponto getCentro() {
        return new Ponto(this.getX() + this.getRaioLargura(), this.getY() + this.getRaioAltura());
    }

    public Ponto getTopo() {
        return new Ponto(this.getX() + this.getRaioLargura(), this.getY());
    }

    public Ponto getBaixo() {
        return new Ponto(this.getX() + this.getRaioLargura(), this.getY() + this.getDiametroAltura());
    }

    public Ponto getEsquerda() {
        return new Ponto(this.getX(), this.getY() + this.getRaioAltura());
    }

    public Ponto getDireita() {
        return new Ponto(this.getX() + this.getDiametroLargura(), this.getY() + this.getRaioAltura());
    }

    public Retangulo getRetangulo() {
        return new Retangulo(this.getX(), this.getY(), this.getDiametroLargura(), this.getDiametroAltura());
    }

}

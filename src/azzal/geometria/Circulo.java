package azzal.geometria;

public class Circulo {

    private int mX;
    private int mY;

    private int mRaio;

    public Circulo() {
        mX = 0;
        mY = 0;

        mRaio = 100;

    }

    public Circulo(int eX, int eY) {
        mX = eX;
        mY = eY;

        mRaio = 100;

    }

    public Circulo(int eX, int eY, int eRaio) {
        mX = eX;
        mY = eY;

        mRaio = eRaio;

    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public int getRaio() {
        return mRaio;
    }

    public int getDiametro() {
        return mRaio * 2;
    }

    public void setX(int eX) {
        mX = eX;
    }

    public void setY(int eY) {
        mY = eY;
    }

    public void setRaio(int eRaio) {
        mRaio = eRaio;
    }

    public Ponto getCentro() {
        return new Ponto(this.getX()+this.getRaio(), this.getY()+this.getRaio());
    }

    public Ponto getTopo() {
        return new Ponto(this.getX()+this.getRaio(), this.getY());
    }

    public Ponto getBaixo() {
        return new Ponto(this.getX()+this.getRaio(), this.getY() + this.getDiametro());
    }

    public Ponto getEsquerda() {
        return new Ponto(this.getX(), this.getY() + this.getRaio());
    }

    public Ponto getDireita() {
        return new Ponto(this.getX()+this.getDiametro(), this.getY() + this.getRaio());
    }

    public Retangulo getRetangulo() {
        return new Retangulo(this.getX() , this.getY() , this.getDiametro(), this.getDiametro());
    }

}

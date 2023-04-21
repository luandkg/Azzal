package libs.azzal.geometria;

public class Retangulo {

    private int mX;
    private int mY;

    private int mLargura;
    private int mAltura;

    public Retangulo() {
        mX = 0;
        mY = 0;

        mLargura = 200;
        mAltura = 100;

    }

    public Retangulo(int eX, int eY) {
        mX = eX;
        mY = eY;

        mLargura = 200;
        mAltura = 100;
    }


    public Retangulo(int eX, int eY, int eLargura, int eAltura) {
        mX = eX;
        mY = eY;

        mLargura = eLargura;
        mAltura = eAltura;
    }


    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public int getX2() {
        return mX + mLargura;
    }

    public int getY2() {
        return mY + mAltura;
    }

    public void setX(int eX) {
        mX = eX;
    }

    public void setY(int eY) {
        mY = eY;
    }

    public int getLargura() {
        return mLargura;
    }

    public int getAltura() {
        return mAltura;
    }

    public void setLargura(int eLargura) {
        mLargura = eLargura;
    }

    public void setAltura(int eAltura) {
        mAltura = eAltura;
    }


    public Retangulo getRetangulo() {
        return new Retangulo(mX, mY, mLargura, mAltura);
    }

    public Ponto getCentro() {
        int eMetadeX = mLargura / 2;
        int eMetadeY = mAltura / 2;

        return new Ponto(mX + eMetadeX, mY + eMetadeY);
    }

    public Ponto getPonto_Centro() {
        int eMetadeX = mLargura / 2;
        int eMetadeY = mAltura / 2;
        return new Ponto(mX + eMetadeX, mY + eMetadeY);
    }

    public Ponto getPonto_A() {
        return new Ponto(this.getX(), this.getY());
    }

    public Ponto getPonto_B() {
        return new Ponto(this.getX2(), this.getY());
    }

    public Ponto getPonto_C() {
        return new Ponto(this.getX2(), this.getY2());
    }

    public Ponto getPonto_D() {
        return new Ponto(this.getX(), this.getY2());
    }


    public Linha getLinha_AB() {
        return new Linha(this.getX(), this.getY(), this.getX2(), this.getY());
    }

    public Linha getLinha_BC() {
        return new Linha(this.getX2(), this.getY(), this.getX2(), this.getY2());
    }

    public Linha getLinha_CD() {
        return new Linha(this.getX2(), this.getY2(), this.getX(), this.getY2());
    }

    public Linha getLinha_DA() {
        return new Linha(this.getX(), this.getY2(), this.getX(), this.getY());
    }

    public Linha getLinha_BA() {
        return new Linha(this.getX2(), this.getY(), this.getX(), this.getY());
    }

    public Linha getLinha_CB() {
        return new Linha(this.getX2(), this.getY2(), this.getX2(), this.getY());
    }

    public Linha getLinha_DC() {
        return new Linha(this.getX(), this.getY2(), this.getX2(), this.getY2());
    }

    public Linha getLinha_AD() {
        return new Linha(this.getX(), this.getY(), this.getX(), this.getY2());
    }


    public Linha getDiagonal_AC() {
        return new Linha(this.getX(), this.getY(), this.getX2(), this.getY2());
    }

    public Linha getDiagonal_BD() {
        return new Linha(this.getX2(), this.getY(), this.getX(), this.getY2());
    }


    public Linha getDiagonal_CA() {
        return new Linha(this.getX2(), this.getY2(), this.getX(), this.getY());
    }

    public Linha getDiagonal_DB() {
        return new Linha(this.getX(), this.getY2(), this.getX2(), this.getY());
    }


    public Ponto getPonto_AB() {
        int eTam = (this.getX2() - this.getX()) / 2;
        return new Ponto(this.getX() + eTam, this.getY());
    }

    public Ponto getPonto_BC() {
        int eTam = (this.getY2() - this.getY()) / 2;
        return new Ponto(this.getX2(), this.getY() + eTam);
    }


    public Ponto getPonto_CD() {
        int eTam = (this.getX2() - this.getX()) / 2;
        return new Ponto(this.getX() + eTam, this.getY2());
    }

    public Ponto getPonto_DA() {
        int eTam = (this.getY2() - this.getY()) / 2;
        return new Ponto(this.getX(), this.getY() + eTam);
    }

    public Ponto getPonto_BA() {
        int eTam = (this.getX2() - this.getX()) / 2;
        return new Ponto(this.getX() + eTam, this.getY());
    }

    public Ponto getPonto_CB() {
        int eTam = (this.getY2() - this.getY()) / 2;
        return new Ponto(this.getX2(), this.getY() + eTam);
    }


    public Ponto getPonto_DC() {
        int eTam = (this.getX2() - this.getX()) / 2;
        return new Ponto(this.getX() + eTam, this.getY2());
    }

    public Ponto getPonto_AD() {
        int eTam = (this.getY2() - this.getY()) / 2;
        return new Ponto(this.getX(), this.getY() + eTam);
    }


    public String getPosicaoFormatada() {
        return " X1 = " + getX() + " Y1 = " + getY() + " X2 = " + getX2() + " Y2 = " + getY2();
    }

    public boolean isDentro(int eX, int eY) {

        boolean eisDentro = false;

        if (eX >= getX() && eX <= getX2()) {
            if (eY >= getY() && eY <= getY2()) {
                eisDentro = true;
            }
        }

        return eisDentro;

    }

    public boolean isIntersectado(Retangulo eRect) {

        boolean mRet = false;

        if (eRect.getX() >= this.getX() && eRect.getX2() < this.getX() + this.getLargura()) {
            if (eRect.getY() >= this.getY() && eRect.getY2() < this.getY() + this.getAltura()) {
                mRet = true;
            }
        }

        return mRet;
    }
}

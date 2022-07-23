package azzal.geometria;

public class Quadrado {

    private int mX;
    private int mY;

    private int mTamanho;

    public Quadrado() {
        mX = 0;
        mY = 0;

        mTamanho = 100;

    }

    public Quadrado(int eX, int eY) {
        mX = eX;
        mY = eY;

        mTamanho = 100;

    }


    public Quadrado(int eX, int eY, int eTamanho) {
        mX = eX;
        mY = eY;

        mTamanho = eTamanho;

    }


    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public void setX(int eX) {
        mX = eX;
    }

    public void setY(int eY) {
        mY = eY;
    }

    public int getX2() {
        return (mX + mTamanho);
    }

    public int getY2() {
        return (mY + mTamanho);
    }

    public int getTamanho() {
        return mTamanho;
    }

    public int getLargura() {
        return mTamanho;
    }

    public int getAltura() {
        return mTamanho;
    }

    public void setTamanho(int eTamanho) {
        mTamanho = eTamanho;
    }


    public Retangulo getRetangulo() {
        return new Retangulo(mX, mY, mTamanho, mTamanho);
    }

    public Ponto getCentro() {
        int eMetade = mTamanho / 2;
        return new Ponto(mX + eMetade, mY + eMetade);
    }

    public Ponto getPonto_Centro() {
        int eMetade = mTamanho / 2;
        return new Ponto(mX + eMetade, mY + eMetade);
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

    public Quadrado getBloquinho_A(int eTam) {
        return new Quadrado(mX, mY, eTam);
    }

    public Quadrado getBloquinho_B(int eTam) {
        return new Quadrado(mX + mTamanho - eTam, mY, eTam);
    }

    public Quadrado getBloquinho_C(int eTam) {
        return new Quadrado(mX + mTamanho - eTam, mY + mTamanho - eTam, eTam);
    }

    public Quadrado getBloquinho_D(int eTam) {
        return new Quadrado(mX, mY + mTamanho - eTam, eTam);
    }

    public Quadrado getBloquinho_Centro(int eTam) {

        int eMetade = mTamanho / 2;
        int eTamMetade = eTam / 2;

        return new Quadrado(mX+eMetade-eTamMetade, mY + eMetade - eTamMetade, eTam);
    }

    public Triangulo getTriangulo_ABC(){
        Triangulo mT = new Triangulo();

        mT.setA(this.getPonto_A());
        mT.setB(this.getPonto_B());
        mT.setC(this.getPonto_C());

        return mT;
    }

    public Triangulo getTriangulo_ACD(){
        Triangulo mT = new Triangulo();

        mT.setA(this.getPonto_A());
        mT.setB(this.getPonto_C());
        mT.setC(this.getPonto_D());

        return mT;
    }

    public Triangulo getTriangulo_ABD(){
        Triangulo mT = new Triangulo();

        mT.setA(this.getPonto_A());
        mT.setB(this.getPonto_B());
        mT.setC(this.getPonto_D());

        return mT;
    }

    public Triangulo getTriangulo_BCD(){
        Triangulo mT = new Triangulo();

        mT.setA(this.getPonto_B());
        mT.setB(this.getPonto_C());
        mT.setC(this.getPonto_D());

        return mT;
    }

}

package Azzal.Formatos;

public class Triangulo {

    private int mAX;
    private int mAY;

    private int mBX;
    private int mBY;

    private int mCX;
    private int mCY;

    public Triangulo() {

        mAX = 0;
        mAY = 0;

        mBX = 0;
        mBY = 0;

        mCX = 0;
        mCY = 0;


    }

    public Triangulo(Ponto ePontoA, Ponto ePontoB, Ponto ePontoC) {

        mAX = ePontoA.getX();
        mAY = ePontoA.getY();

        mBX = ePontoB.getX();
        mBY = ePontoB.getY();

        mCX = ePontoC.getX();
        mCY = ePontoC.getY();


    }

    public Ponto getPonto_A() {
        return new Ponto(mAX, mAY);
    }

    public Ponto getPonto_B() {
        return new Ponto(mBX, mBY);
    }

    public Ponto getPonto_C() {
        return new Ponto(mCX, mCY);
    }


    public int getAX() {
        return mAX;
    }

    public int getAY() {
        return mAY;
    }

    public Linha getA() {
        return new Linha(mAX, mAY, mBX, mBY);
    }

    public int getBX() {
        return mBX;
    }

    public int getBY() {
        return mBY;
    }

    public Linha getB() {
        return new Linha(mBX, mBY, mCX, mCY);
    }

    public int getCX() {
        return mCX;
    }

    public int getCY() {
        return mCY;
    }

    public Linha getC() {
        return new Linha(mCX, mCY, mAX, mAY);
    }


    public Ponto getCentro_A() {
        return new Linha(mAX, mAY, mBX, mBY).getCentro();
    }

    public Ponto getCentro_B() {
        return new Linha(mBX, mBY, mCX, mCY).getCentro();
    }

    public Ponto getCentro_C() {
        return new Linha(mCX, mCY, mAX, mAY).getCentro();
    }


    public void setA(int eAX, int eAY) {
        mAX = eAX;
        mAY = eAY;
    }

    public void setA(Ponto ePontoA) {
        mAX = ePontoA.getX();
        mAY = ePontoA.getY();
    }

    public void setB(int eBX, int eBY) {
        mBX = eBX;
        mBY = eBY;
    }

    public void setB(Ponto ePontoB) {
        mBX = ePontoB.getX();
        mBY = ePontoB.getY();
    }

    public void setC(int eCX, int eCY) {
        mCX = eCX;
        mCY = eCY;
    }

    public void setC(Ponto ePontoC) {
        mCX = ePontoC.getX();
        mCY = ePontoC.getY();
    }

    public void aumentarX(int eX) {

        mAX += eX;
        mBX += eX;
        mCX += eX;

    }

    public void aumentarY(int eY) {

        mAY += eY;
        mBY += eY;
        mCY += eY;

    }

    public void moverPara(int eX, int eY) {

        mBX = mBX - mAX;
        mCX = mCX - mAX;
        mAX = 0;

        mBY = mBY - mAY;
        mCY = mCY - mAY;
        mAY = 0;

        mAX = eX;
        mBX = eX + mBX;
        mCX = eX + mCX;

        mAY = eY;
        mBY = eY + mBY;
        mCY = eY + mCY;


    }

    public Linha getM1() {
        return new Linha(this.getCentro_A(), this.getCentro_B());
    }

    public Linha getM2() {
        return new Linha(this.getCentro_B(), this.getCentro_C());
    }

    public Linha getM3() {
        return new Linha(this.getCentro_C(), this.getCentro_A());
    }


    public Retangulo getRetangulo() {

        int maxX = Math.max(mAX, Math.max(mBX, mCX));
        int minX = Math.min(mAX, Math.min(mBX, mCX));

        int maxY = Math.max(mAY, Math.max(mBY, mCY));
        int minY = Math.min(mAY, Math.min(mBY, mCY));

        int tamX = maxX - minX;
        int tamY = maxY - minY;

        return new Retangulo(minX, minY, tamX, tamY);
    }


    public String toString() {
        return "A { " + mAX + "," + mAY + "} " + "B { " + mBX + "," + mBY + "} " + "C { " + mCX + "," + mCY + "}";
    }

}

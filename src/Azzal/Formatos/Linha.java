package Azzal.Formatos;

import Azzal.Utils.Cor;
import Luan.Lista;

public class Linha {

    private int mX1;
    private int mY1;

    private int mX2;
    private int mY2;

    public Linha() {

        mX1 = 0;
        mY1 = 0;

        mX2 = 200;
        mY2 = 100;

    }

    public Linha(int eX1, int eY1, int eX2, int eY2) {

        mX1 = eX1;
        mY1 = eY1;

        mX2 = eX2;
        mY2 = eY2;

    }

    public Linha(Ponto ePontoOrigem, Ponto ePontoFinal) {

        mX1 = ePontoOrigem.getX();
        mY1 = ePontoOrigem.getY();

        mX2 = ePontoFinal.getX();
        mY2 = ePontoFinal.getY();

    }

    public int getX1() {
        return mX1;
    }

    public int getY1() {
        return mY1;
    }

    public void setX1(int eX) {
        mX1 = eX;
    }

    public void setY1(int eY) {
        mY1 = eY;
    }


    public int getX2() {
        return mX2;
    }

    public int getY2() {
        return mY2;
    }

    public void setX2(int eX) {
        mX2 = eX;
    }

    public void setY2(int eY) {
        mY2 = eY;
    }


    public Lista<Ponto> getPontos_A() {

        Lista<Ponto> mLista = new Lista<Ponto>();

        int dx = this.getX2() - this.getX1();
        int dy = this.getY2() - this.getY1();

        // System.out.println("\t Dx : " + dx);
        // System.out.println("\t Dy : " + dy);


        if (dx == 0 && dy == 0) {


        } else if (dx == 0 && dy != 0) {

            //  System.out.println("LINHA VERTICAL");

            if (dy > 0) {

                for (int y = 0; y < dy; y++) {

                    mLista.adicionar(new Ponto(this.getX1(), this.getY1() + y));

                }
            } else {

                for (int y = 0; y >= dy; y--) {

                    mLista.adicionar(new Ponto(this.getX1(), this.getY1() + y));

                }

            }

        } else if (dx != 0 && dy == 0) {

            // System.out.println("LINHA HORIZONTAL");

            if (dx > 0) {

                for (int x = 0; x < dx; x++) {

                    mLista.adicionar(new Ponto(this.getX1() + x, this.getY1()));

                }

            } else {

                for (int x = 0; x >= dx; x--) {


                    mLista.adicionar(new Ponto(this.getX1() + x, this.getY1()));


                }

            }


        } else {

            //  System.out.println("LINHA DIAGONAL");


            // System.out.println("\t X : " + eLinha.getX1());
            // System.out.println("\t Y : " + eLinha.getY1());


            //  System.out.println("\t X2 : " + eLinha.getX2());
            //  System.out.println("\t Y2 : " + eLinha.getY2());


            int d = 0;

            dx = Math.abs(this.getX2() - this.getX1());
            dy = Math.abs(this.getY2() - this.getY1());

            int dx2 = 2 * dx; // slope scaling factors to
            int dy2 = 2 * dy; // avoid floating point

            int ix = this.getX1() < this.getX2() ? 1 : -1; // increment direction
            int iy = this.getY1() < this.getY2() ? 1 : -1;

            int x = this.getX1();
            int y = this.getY1();

            if (dx >= dy) {
                while (true) {
                    mLista.adicionar(new Ponto(x, y));

                    if (x == this.getX2())
                        break;
                    x += ix;
                    d += dy2;
                    if (d > dx) {
                        y += iy;
                        d -= dx2;
                    }
                }
            } else {
                while (true) {

                    mLista.adicionar(new Ponto(x, y));


                    if (y == this.getY2())
                        break;
                    y += iy;
                    d += dx2;
                    if (d > dy) {
                        x += ix;
                        d -= dy2;
                    }
                }
            }
            // System.out.println("-------------------------------------");

        }

        return mLista;
    }


}

package apps.AppAttuz.Ferramentas;

import azzal.Formatos.Linha;
import azzal.Formatos.Ponto;

import java.util.ArrayList;

public class Espaco2D {

    public static int distancia_entre_pontos(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    public static boolean isDentro(int x, int y, int largura, int altura, int px, int py) {
        boolean ret = false;

        if (px >= x && px < (x + largura)) {
            if (py >= y && py < (y + altura)) {
                ret = true;
            }

        }

        return ret;
    }

    public static ArrayList<Ponto> getPontosDeLinha(int eX, int eY, int eX2, int eY2) {
        return getPontosDeLinha(new Linha(eX, eY, eX2, eY2));
    }

    public static ArrayList<Ponto> getPontosDeLinha(Linha eLinha) {

        ArrayList<Ponto> pontos = new ArrayList<Ponto>();

        int dx = eLinha.getX2() - eLinha.getX1();
        int dy = eLinha.getY2() - eLinha.getY1();

        // System.out.println("\t Dx : " + dx);
        // System.out.println("\t Dy : " + dy);


        if (dx == 0 && dy == 0) {


        } else if (dx == 0 && dy != 0) {

            //  System.out.println("LINHA VERTICAL");

            if (dy > 0) {

                for (int y = 0; y <= dy; y++) {

                    pontos.add(new Ponto(eLinha.getX1(), eLinha.getY1() + y));

                }
            } else {

                for (int y = 0; y >= dy; y--) {

                    pontos.add(new Ponto(eLinha.getX1(), eLinha.getY1() + y));

                }

            }

        } else if (dx != 0 && dy == 0) {

            // System.out.println("LINHA HORIZONTAL");

            if (dx > 0) {

                for (int x = 0; x < dx; x++) {

                    pontos.add(new Ponto(eLinha.getX1() + x, eLinha.getY1()));

                }

            } else {

                for (int x = 0; x >= dx; x--) {


                    pontos.add(new Ponto(eLinha.getX1() + x, eLinha.getY1()));


                }

            }


        } else {

            //  System.out.println("LINHA DIAGONAL");


            // System.out.println("\t X : " + eLinha.getX1());
            // System.out.println("\t Y : " + eLinha.getY1());


            //  System.out.println("\t X2 : " + eLinha.getX2());
            //  System.out.println("\t Y2 : " + eLinha.getY2());


            int d = 0;

            dx = Math.abs(eLinha.getX2() - eLinha.getX1());
            dy = Math.abs(eLinha.getY2() - eLinha.getY1());

            int dx2 = 2 * dx; // slope scaling factors to
            int dy2 = 2 * dy; // avoid floating point

            int ix = eLinha.getX1() < eLinha.getX2() ? 1 : -1; // increment direction
            int iy = eLinha.getY1() < eLinha.getY2() ? 1 : -1;

            int x = eLinha.getX1();
            int y = eLinha.getY1();

            if (dx >= dy) {
                while (true) {
                    pontos.add(new Ponto(x, y));

                    if (x == eLinha.getX2())
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
                    pontos.add(new Ponto(x, y));
                    if (y == eLinha.getY2())
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

        return pontos;

    }

}

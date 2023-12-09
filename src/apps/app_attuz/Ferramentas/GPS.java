package apps.app_attuz.Ferramentas;


import libs.azzal.geometria.Linha;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;

import java.util.ArrayList;
import java.util.Random;

public class GPS {

    public static ArrayList<Ponto> criarRota(Ponto p1,Ponto p2) {
        return criarRota(p1.getX(),p1.getY(),p2.getX(),p2.getY());
    }

        public static ArrayList<Ponto> criarRota(int estou_x, int estou_y, int ir_x, int ir_y) {

        ArrayList<Ponto> rota = new ArrayList<Ponto>();

        int ENTROPIA = 3;

        Random eSorte = new Random();

        int ux = 0;
        if (ir_x > estou_x) {
            ux += 1;
        } else if (ir_x < estou_x) {
            ux = -1;
        }
        int uy = 0;
        if (ir_y > estou_y) {
            uy += 1;
        } else if (ir_y < estou_y) {
            uy = -1;
        }

        // System.out.println("IR PARA :: " + ir_x + "," + ir_y);

        int ax = 0;

        int ax1 = 0;
        int ax2 = 0;
        int ax3 = 0;


        int ay = 0;

        int ay1 = 0;
        int ay2 = 0;
        int ay3 = 0;

        Random r = new Random();


        while ((estou_x != ir_x) || (estou_y != ir_y)) {

            if (eSorte.nextInt(100) > 50) {
                if (estou_x != ir_x) {
                    estou_x += ux;
                } else {
                    if (estou_y != ir_y) {
                        estou_y += uy;
                    }
                }
            } else {
                if (estou_y != ir_y) {
                    estou_y += uy;
                } else {
                    if (estou_x != ir_x) {
                        estou_x += ux;
                    }
                }
            }

            ax3 = ax2;
            ax2 = ax1;
            ax1 = estou_x;


            if (ax == ENTROPIA) {
                if (ax1 == ax2 && ax2 == ax3) {
                    //System.out.println("-->> TRIX :: " + ax3 + " " + ax2 + " " + ax1);

                    if (ux > 0) {
                        estou_x -= r.nextInt(ENTROPIA);
                    } else if (ux < 0) {
                        estou_x += r.nextInt(ENTROPIA);
                    }

                    ax1 = estou_x;
                }
            }

            ay3 = ay2;
            ay2 = ay1;
            ay1 = estou_y;


            if (ay == ENTROPIA) {
                if (ay1 == ay2 && ay2 == ay3) {
                    //System.out.println("-->> PLOZ :: " + ay3 + " " + ay2 + " " + ay1);
                    if (uy > 0) {
                        estou_y -= r.nextInt(ENTROPIA);
                    } else if (uy < 0) {
                        estou_y += r.nextInt(ENTROPIA);
                    }

                    ay1 = estou_y;
                }
            }


            //System.out.println("mover(" + estou_x + "," + estou_y + ")");
            rota.add(new Ponto(estou_x, estou_y));

            if (ax < ENTROPIA) {
                ax += 1;
            }

            if (ay < ENTROPIA) {
                ay += 1;
            }
        }


        return rota;

    }


    public  static ArrayList<Ponto> criarRotaReta(int eX, int eY, int eX2, int eY2) {
        return criarRotaReta(new Linha(eX, eY, eX2, eY2));
    }
    public  static ArrayList<Ponto> criarRotaReta(Ponto p1,Ponto p2) {
        return criarRotaReta(new Linha(p1,p2));
    }


        public static ArrayList<Ponto> criarRotaReta(Linha eLinha) {

        ArrayList<Ponto> rota = new ArrayList<Ponto>();


        int dx = eLinha.getX2() - eLinha.getX1();
        int dy = eLinha.getY2() - eLinha.getY1();

        // System.out.println("\t Dx : " + dx);
        // System.out.println("\t Dy : " + dy);


        if (dx == 0 && dy == 0) {


        } else if (dx == 0 && dy != 0) {

            //  System.out.println("LINHA VERTICAL");

            if (dy > 0) {

                for (int y = 0; y <= dy; y++) {

                    rota.add(new Ponto(eLinha.getX1(), eLinha.getY1() + y));

                }
            } else {

                for (int y = 0; y >= dy; y--) {

                    rota.add(new Ponto(eLinha.getX1(), eLinha.getY1() + y));

                }

            }

        } else if (dx != 0 && dy == 0) {

            // System.out.println("LINHA HORIZONTAL");

            if (dx > 0) {

                for (int x = 0; x < dx; x++) {

                    rota.add(new Ponto(eLinha.getX1() + x, eLinha.getY1()));

                }

            } else {

                for (int x = 0; x >= dx; x--) {


                    rota.add(new Ponto(eLinha.getX1() + x, eLinha.getY1()));


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
                    rota.add(new Ponto(x, y));
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
                    rota.add(new Ponto(x, y));
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

        return rota;
    }

}

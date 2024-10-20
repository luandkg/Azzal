package apps.app_atzum.utils;

import libs.azzal.geometria.Ponto;
import libs.luan.Lista;

import java.util.Random;

public class FerramentaGPS {

    public static Lista<Ponto> criarRota(int estou_x, int estou_y, int ir_x, int ir_y) {

        Lista<Ponto> rota = new Lista<Ponto>();

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
            rota.adicionar(new Ponto(estou_x, estou_y));

            if (ax < ENTROPIA) {
                ax += 1;
            }

            if (ay < ENTROPIA) {
                ay += 1;
            }
        }


        return rota;

    }


    public static Lista<Ponto> criarRotaMelhorDe3(int estou_x, int estou_y, int ir_x, int ir_y) {

        Lista<Ponto> melhor_rota = new Lista<Ponto>();

        int tentativa = 1;
        final int MAX_TENTATIVAS = 3;

        while (tentativa <= MAX_TENTATIVAS) {

            Lista<Ponto> rota_corrente = FerramentaGPS.criarRota(estou_x, estou_y, ir_x, ir_y);

            if(tentativa==1){
                melhor_rota=rota_corrente;
            }else{
                if(rota_corrente.getQuantidade()< melhor_rota.getQuantidade()){
                    melhor_rota=rota_corrente;
                }
            }

            tentativa += 1;
        }

        return melhor_rota;
    }


}

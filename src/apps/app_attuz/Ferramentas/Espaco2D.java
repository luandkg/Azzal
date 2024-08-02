package apps.app_attuz.Ferramentas;

import libs.azzal.Renderizador;
import libs.azzal.geometria.Linha;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.luan.Embaralhar;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;

import java.util.ArrayList;

public class Espaco2D {

    public static int distancia_entre_pontos(Ponto p1, Ponto p2) {
        return distancia_entre_pontos(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public static int distancia_entre_pontos(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    public static double distancia_entre_pontos_double(int x1, int y1, int x2, int y2) {
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
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

    public static ArrayList<Ponto> getPontosDeLinha(Ponto origem, Ponto destino) {
        return getPontosDeLinha(new Linha(origem.getX(), origem.getY(), destino.getX(), destino.getY()));
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


    public static Opcional<Ponto> GET_MAIS_PROXIMO(Ponto ir_comecar, Lista<Ponto> possiveis) {

        boolean existe_proximo = false;

        int distancia = 0;
        Ponto proximo = null;

        for (Ponto prox : possiveis) {
            if (prox.isDiferente(ir_comecar)) {

                if (!existe_proximo) {
                    distancia = Espaco2D.distancia_entre_pontos(ir_comecar.getX(), ir_comecar.getY(), prox.getX(), prox.getY());
                    existe_proximo = true;
                    proximo = prox;
                } else {
                    if (Espaco2D.distancia_entre_pontos(ir_comecar.getX(), ir_comecar.getY(), prox.getX(), prox.getY()) < distancia) {
                        distancia = Espaco2D.distancia_entre_pontos(ir_comecar.getX(), ir_comecar.getY(), prox.getX(), prox.getY());
                        proximo = prox;
                    }
                }

            }
        }

        if (existe_proximo) {
            return Opcional.OK(proximo);
        }

        return Opcional.CANCEL();
    }

    public static Opcional<Ponto> GET_UM_DOS_MAIS_PROXIMO(Ponto ir_comecar, int max_distancia, Lista<Ponto> possiveis) {


        Lista<Ponto> candidatos = new Lista<Ponto>();

        for (Ponto prox : possiveis) {
            if (prox.isDiferente(ir_comecar)) {

                int distancia = Espaco2D.distancia_entre_pontos(ir_comecar.getX(), ir_comecar.getY(), prox.getX(), prox.getY());
                if (distancia <= max_distancia) {
                    candidatos.adicionar(prox);
                }

            }
        }

        if (candidatos.getQuantidade() > 0) {
            Embaralhar.emabaralhe(candidatos);
            return Opcional.OK(candidatos.get(0));
        }

        return Opcional.CANCEL();
    }

    public static Opcional<Integer> GET_MAIS_PROXIMO_ORDEM(Ponto ir_comecar, Lista<Ponto> possiveis) {

        boolean existe_proximo = false;

        int distancia = 0;
        Ponto proximo = null;

        int ordem = 0;
        int ordem_proximo = 0;

        for (Ponto prox : possiveis) {
            if (prox.isDiferente(ir_comecar)) {

                if (!existe_proximo) {
                    distancia = Espaco2D.distancia_entre_pontos(ir_comecar.getX(), ir_comecar.getY(), prox.getX(), prox.getY());
                    existe_proximo = true;
                    proximo = prox;
                    ordem_proximo=ordem;
                } else {
                    if (Espaco2D.distancia_entre_pontos(ir_comecar.getX(), ir_comecar.getY(), prox.getX(), prox.getY()) < distancia) {
                        distancia = Espaco2D.distancia_entre_pontos(ir_comecar.getX(), ir_comecar.getY(), prox.getX(), prox.getY());
                        proximo = prox;
                        ordem_proximo=ordem;
                    }
                }

            }
            ordem+=1;
        }

        if (existe_proximo) {
            return Opcional.OK(ordem_proximo);
        }

        return Opcional.CANCEL();
    }



    public static int GET_DISTANCIA_MAIS_PROXIMA(Lista<Ponto> pontos,int eixo_x,int eixo_y){

        int distancia_mais_proxima = Integer.MAX_VALUE;

        for(Ponto pt : pontos) {
            int distancia = Espaco2D.distancia_entre_pontos(eixo_x,eixo_y,pt.getX(),pt.getY());
            if(distancia<distancia_mais_proxima){
                distancia_mais_proxima=distancia;
            }
        }

        return distancia_mais_proxima;
    }

    public static int GET_DISTANCIA_MAIS_PROXIMA_DOS_PONTOS_COM_VALOR(Lista<Par<Ponto,Integer>> pontos, int eixo_x, int eixo_y){

        int distancia_mais_proxima = Integer.MAX_VALUE;

        for(Par<Ponto,Integer> pt : pontos) {
            int distancia = Espaco2D.distancia_entre_pontos(eixo_x,eixo_y,pt.getChave().getX(),pt.getChave().getY());
            if(distancia<distancia_mais_proxima){
                distancia_mais_proxima=distancia;
            }
        }

        return distancia_mais_proxima;
    }


    public static int GET_VALOR_DA_DISTANCIA_MAIS_PROXIMA(Lista<Par<Ponto,Integer>> pontos, int eixo_x, int eixo_y){

        int distancia_mais_proxima = Integer.MAX_VALUE;
int valor =-1;

        for(Par<Ponto,Integer> pt : pontos) {
            int distancia = Espaco2D.distancia_entre_pontos(eixo_x,eixo_y,pt.getChave().getX(),pt.getChave().getY());
            if(distancia<distancia_mais_proxima){
                distancia_mais_proxima=distancia;
                valor=pt.getValor();
            }
        }

        return valor;
    }

    public static String GET_TEXTO_DA_DISTANCIA_MAIS_PROXIMA(Lista<Par<Ponto,String>> pontos, int eixo_x, int eixo_y){

        int distancia_mais_proxima = Integer.MAX_VALUE;
        String valor ="";

        for(Par<Ponto,String> pt : pontos) {
            int distancia = Espaco2D.distancia_entre_pontos(eixo_x,eixo_y,pt.getChave().getX(),pt.getChave().getY());
            if(distancia<distancia_mais_proxima){
                distancia_mais_proxima=distancia;
                valor=pt.getValor();
            }
        }

        return valor;
    }


    public static Lista<Ponto> SO_PONTOS(Lista<Par<Ponto, Integer>> pontos_com_valor){

        Lista<Ponto> pontos = new Lista<Ponto>();

        for(Par<Ponto, Integer> pt : pontos_com_valor) {
            pontos.adicionar(pt.getChave());
        }

        return pontos;
        }

        public static boolean AO_REDOR_TEM(Renderizador render, int x, int y, Cor eCor){

            Ponto pt_esquerda = new Ponto(x - 1, y);
            Ponto pt_direita = new Ponto(x + 1, y);
            Ponto pt_subir = new Ponto(x, y - 1);
            Ponto pt_descer = new Ponto(x, y + 1);


            if(AO_REDOR_IGUAL(render,eCor,pt_esquerda) || AO_REDOR_IGUAL(render,eCor,pt_direita) || AO_REDOR_IGUAL(render,eCor,pt_subir) ||AO_REDOR_IGUAL(render,eCor,pt_descer)){
            return true;
            }

         return false;
        }

    public static boolean AO_REDOR_IGUAL(Renderizador render, Cor cor_entrada,  Ponto pt_processar) {

        boolean ret = false;


        if (pt_processar.getX() >= 0 && pt_processar.getY() >= 0 && pt_processar.getX() < render.getLargura() && pt_processar.getY() < render.getAltura()) {
            if (render.getPixel(pt_processar.getX(), pt_processar.getY()).igual(cor_entrada)) {
                ret=true;
            }
        }

        return ret;
    }
}

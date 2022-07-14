package apps.AppAttuz.Assessorios;

import apps.AppAttuz.Ferramentas.GPS;
import apps.AppAttuz.Mapa.Local;
import azzal.Formatos.Ponto;
import libs.DKG.DKG;
import libs.DKG.DKGObjeto;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MapaUtilitario {

    public static void normalizar(ArrayList<Local> pontos) {

        for (Local ePonto : pontos) {

            ePonto.setX(ePonto.getX() * 2);
            ePonto.setY(ePonto.getY() * 2);

        }

    }

    public static void ligarPontos(BufferedImage mapa, Graphics g, int valores[], ArrayList<Local> pontos, int altura) {

        //  g.setColor(eCor);

        for (Local p1 : pontos) {
            for (Local p2 : pontos) {

                int dx = (p1.getX() - p2.getX());
                int dy = (p1.getY() - p2.getY());

                int d = (int) Math.sqrt((dx * dx) + (dy * dy));

                if (d <= 50) {
                    System.out.println("Distancia :: " + d);

                    //g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());

                    ArrayList<Ponto> rota = GPS.criarRota(p1.getX(), p1.getY(), p2.getX(), p2.getY());

                    for (Ponto a : rota) {
                        // g.drawRect(a.getX(), a.getY(), 1,1);

                        valores[a.getX() + (a.getY() * mapa.getWidth())] = altura;


                    }


                }

            }
        }

    }

    public static int emPonto(int x, int y, BufferedImage mapa) {
        return x + (y * mapa.getWidth());
    }


    public static void preencher(BufferedImage mapa, int valores[]) {

        for (int i = 0; i < valores.length; i++) {

            int valor = valores[i];

            int ey = (i / mapa.getWidth());
            int ex = i - (ey * mapa.getWidth());

            int real = mapa.getRGB(ex, ey);

            if (real < -1) {

                if (valor == 0) {

                    int acima_y = (i / mapa.getWidth()) - 1;
                    int acima_x = i - (ey * mapa.getWidth());

                    int abaixo_y = (i / mapa.getWidth()) + 1;
                    int abaixo_x = i - (ey * mapa.getWidth());

                    int direita_y = (i / mapa.getWidth());
                    int direita_x = i - (ey * mapa.getWidth()) + 1;

                    int esquerda_y = (i / mapa.getWidth());
                    int esquerda_x = i - (ey * mapa.getWidth()) - 1;

                    int s1 = valores[emPonto(acima_x, acima_y, mapa)];
                    int s2 = valores[emPonto(abaixo_x, abaixo_y, mapa)];
                    int s3 = valores[emPonto(direita_x, direita_y, mapa)];
                    int s4 = valores[emPonto(esquerda_x, esquerda_y, mapa)];

                    if (s1 != 0 && s2 != 0 && s3 != 0 && s4 != 0) {
                        //System.out.println("acu :: " + (s1 + s2 + s3 + s4));
                        int acu = (s1 + s2 + s3 + s4) / 4;

                        valores[i] = acu;

                    }


                }

            }
        }
    }

    public static ArrayList<Ponto> contorno_de_area(BufferedImage mapa, ArrayList<Local> mLocais) {

        ArrayList<Ponto> tudo = new ArrayList<Ponto>();

        Graphics g = mapa.getGraphics();

        normalizar(mLocais);

        g.setColor(Color.RED);

        Local anterior = mLocais.get(mLocais.size() - 1);

        for (Local eCorrente : mLocais) {

            ArrayList<Ponto> rota = GPS.criarRota(anterior.getX(), anterior.getY(), eCorrente.getX(), eCorrente.getY());

            for (Ponto a : rota) {
                g.drawRect(a.getX(), a.getY(), 1, 1);
                tudo.add(a);
            }

            anterior = eCorrente;
        }

        return tudo;
    }

    public static void salvarPontos(String arquivo, ArrayList<Ponto> mLocais) {

        DKG eDKG = new DKG();

        DKGObjeto pontos = eDKG.unicoObjeto("Pontos");

        for (Ponto eCorrente : mLocais) {

            pontos.criarObjeto("Pontos", "x", eCorrente.getX(), "y", eCorrente.getY());

        }

        eDKG.salvar(arquivo);
    }

    public static void salvarLocais(String arquivo, ArrayList<Local> mLocais) {

        DKG eDKG = new DKG();

        DKGObjeto pontos = eDKG.unicoObjeto("Pontos");

        for (Local eCorrente : mLocais) {

           DKGObjeto obj= pontos.criarObjeto("Pontos", "x", eCorrente.getX(), "y", eCorrente.getY());
            obj.identifique("nome",eCorrente.getNome());

        }

        eDKG.salvar(arquivo);
    }

    public static ArrayList<Local> obterLocais(String arquivo) {

        ArrayList<Local> mLocais = new ArrayList<Local>();

        DKG eDKG = new DKG();
        eDKG.abrir(arquivo);

        DKGObjeto pontos = eDKG.unicoObjeto("Pontos");

        for (DKGObjeto eCorrente : pontos.getObjetos()) {


            mLocais.add(new Local( eCorrente.identifique("nome").getValor(), eCorrente.identifique("x").getInteiro(), eCorrente.identifique("y").getInteiro()));

        }

        return mLocais;
    }

    public static ArrayList<Ponto> obterPontos(String arquivo) {

        ArrayList<Ponto> mLocais = new ArrayList<Ponto>();

        DKG eDKG = new DKG();
        eDKG.abrir(arquivo);

        DKGObjeto pontos = eDKG.unicoObjeto("Pontos");

        for (DKGObjeto eCorrente : pontos.getObjetos()) {


            mLocais.add(new Ponto( eCorrente.identifique("x").getInteiro(), eCorrente.identifique("y").getInteiro()));

        }

        return mLocais;
    }

    public static ArrayList<Ponto> localToPonto(ArrayList<Local> locais) {

        ArrayList<Ponto> pontos = new ArrayList<Ponto>();

        for (Local ePonto : locais) {
            pontos.add(new Ponto(ePonto.getX(), ePonto.getY()));
        }

        return pontos;
    }

    public static ArrayList<Ponto> localToPontoNormalizado(ArrayList<Local> locais) {

        ArrayList<Ponto> pontos = new ArrayList<Ponto>();

        for (Local ePonto : locais) {
            pontos.add(new Ponto(ePonto.getX(), ePonto.getY()));
        }

        for (Ponto ePonto : pontos) {

            ePonto.setX(ePonto.getX() * 2);
            ePonto.setY(ePonto.getY() * 2);

        }

        return pontos;
    }

    public static ArrayList<Local> toLocalNormalizado(ArrayList<Local> locais) {

        ArrayList<Local> pontos = new ArrayList<Local>();

        for (Local ePonto : locais) {
            pontos.add(new Local(ePonto.getNome(),ePonto.getX(), ePonto.getY()));
        }

        for (Local ePonto : pontos) {

            ePonto.setX(ePonto.getX() * 2);
            ePonto.setY(ePonto.getY() * 2);

        }

        return pontos;
    }

    public static ArrayList<Ponto> getPontosComNome(ArrayList<Local> locais, String v) {
        ArrayList<Ponto> lista = new ArrayList<Ponto>();

        for (Local l : locais) {
            if (l.getNome().contentEquals(v)) {
                lista.add(new Ponto(l.getX(), l.getY()));
            }
        }


        return lista;
    }
}

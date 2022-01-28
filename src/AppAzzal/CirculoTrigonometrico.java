package AppAzzal;

import Azzal.Formatos.Circulo;
import Azzal.Formatos.Ponto;

import java.util.ArrayList;

public class CirculoTrigonometrico {

    private ArrayList<Ponto> pontos;
    private int cx;
    private int cy;

    public CirculoTrigonometrico(Circulo eCirculo) {

        pontos = new ArrayList<Ponto>();

        obterPontos(eCirculo.getX(), eCirculo.getY(), eCirculo.getRaio());


        cx = eCirculo.getCentro().getX();
        cy = eCirculo.getCentro().getY();

        System.out.println("\t CX :: " + cx);
        System.out.println("\t CY :: " + cy);

        ArrayList<Ponto> acima_direita = new ArrayList<Ponto>();
        ArrayList<Ponto> acima_esquerda = new ArrayList<Ponto>();

        ArrayList<Ponto> abaixo_direita = new ArrayList<Ponto>();
        ArrayList<Ponto> abaixo_esquerda = new ArrayList<Ponto>();

        for (Ponto p : pontos) {
            if (p.getX() > cx && p.getY() < cy) {
                acima_direita.add(p);
            } else if (p.getX() < cx && p.getY() < cy) {
                acima_esquerda.add(p);
            } else if (p.getX() > cx && p.getY() > cy) {
                abaixo_direita.add(p);
            } else if (p.getX() < cx && p.getY() > cy) {
                abaixo_esquerda.add(p);
            }
        }

        System.out.println("Pontos :: " + pontos.size());

        System.out.println("Acima Direita   :: " + acima_direita.size());
        System.out.println("Acima Esquerda  :: " + acima_esquerda.size());
        System.out.println("Abaixo Direita  :: " + abaixo_direita.size());
        System.out.println("Abaixo Esquerda :: " + abaixo_esquerda.size());

        ordenar_x(acima_direita);
        inverter_y(acima_esquerda);

        ordenar_y(abaixo_direita);
        inverter_x(abaixo_esquerda);


        pontos.clear();

        pontos.addAll(acima_direita);
        pontos.addAll(abaixo_direita);
        pontos.addAll(abaixo_esquerda);
        pontos.addAll(acima_esquerda);

        // for (Ponto p : acima_esquerda) {
        //    System.out.println("\t P :: " + p.getX() + " :: " + p.getY());
        //}
    }

    public Ponto getGrau(int grau) {

        double pt = ((double) pontos.size() / 360.0) * (double) grau;
        int ptx = (int) pt;

        if (ptx >= pontos.size()) {
            ptx = pontos.size() - 1;
        }

        return pontos.get(ptx);

    }

    private void ordenar_x(ArrayList<Ponto> entradas) {

        int n = entradas.size();
        Ponto temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entradas.get(j - 1).getX() >= (entradas.get(j).getX())) {
                    temp = entradas.get(j - 1);
                    entradas.set(j - 1, entradas.get(j));
                    entradas.set(j, temp);

                }

            }
        }
    }

    private void inverter_x(ArrayList<Ponto> entradas) {

        int n = entradas.size();
        Ponto temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entradas.get(j - 1).getX() <= (entradas.get(j).getX())) {
                    temp = entradas.get(j - 1);
                    entradas.set(j - 1, entradas.get(j));
                    entradas.set(j, temp);

                }

            }
        }
    }

    private void ordenar_y(ArrayList<Ponto> entradas) {

        int n = entradas.size();
        Ponto temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entradas.get(j - 1).getY() >= (entradas.get(j).getY())) {
                    temp = entradas.get(j - 1);
                    entradas.set(j - 1, entradas.get(j));
                    entradas.set(j, temp);

                }

            }
        }
    }

    private void inverter_y(ArrayList<Ponto> entradas) {

        int n = entradas.size();
        Ponto temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entradas.get(j - 1).getY() <= (entradas.get(j).getY())) {
                    temp = entradas.get(j - 1);
                    entradas.set(j - 1, entradas.get(j));
                    entradas.set(j, temp);

                }

            }
        }
    }

    public ArrayList<Ponto> getPontos() {
        return pontos;
    }

    private void obterPontos(int x1, int y1, int raio) {

        int x = 0;
        int y = raio;
        double d = 1.25 - (double) raio;

        int xi = x1 + raio;
        int yi = y1 + raio;


        while (x < y) {
            if (d < 0) {
                d = d + 2 * x + 3;
                x += 1;
            } else {
                d = d + 2 * (x - y) + 5;
                x += 1;
                y -= 1;
            }
            unico(xi + x, yi + y);
            unico(xi + x, yi - y);
            unico(xi - x, yi + y);
            unico(xi - x, yi - y);

            unico(xi + y, yi + x);
            unico(xi + y, yi - x);
            unico(xi - y, yi + x);
            unico(xi - y, yi - x);

        }

    }

    private void unico(int ex, int ey) {

        boolean enc = false;

        for (Ponto p : pontos) {
            if (p.getX() == ex && p.getY() == ey) {
                enc = true;
                break;
            }
        }

        if (!enc) {
            pontos.add(new Ponto(ex, ey));
        }

    }


    public Ponto getPontoCentralDaLinha(int x1, int y1, int x2, int y2) {

        int dx = x2 - x1;
        int dy = y2 - y1;

        int px = x1 + (dx / 2);
        int py = y1 + (dy / 2);


        return new Ponto(px, py);
    }
}

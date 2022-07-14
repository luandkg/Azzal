package apps.AppAttuz.Ferramentas;

import apps.AppAttuz.Assessorios.Unicidade;
import azzal.Formatos.Ponto;

import java.util.ArrayList;
import java.util.Random;

public class AlgoritmosDeMapa {

    private Random mSorte;

    public AlgoritmosDeMapa() {
        mSorte = new Random();
    }

    public ArrayList<Ponto> expandir_em4(ArrayList<Ponto> locais, int max) {
        ArrayList<Ponto> ret = new ArrayList<Ponto>();

        for (Ponto ePonto : locais) {

            ret.add(new Ponto(ePonto.getX(), ePonto.getY() - sorte(max)));
            ret.add(new Ponto(ePonto.getX(), ePonto.getY() + sorte(max)));

            ret.add(new Ponto(ePonto.getX() - sorte(max), ePonto.getY()));
            ret.add(new Ponto(ePonto.getX() + sorte(max), ePonto.getY()));

        }


        return ret;
    }

    public int sorte(int valor) {
        return mSorte.nextInt(valor);
    }


    public ArrayList<Ponto> ligarPontos(ArrayList<Ponto> pontos) {

        ArrayList<Ponto> contorno = new ArrayList<Ponto>();

        Unicidade unicos = new Unicidade();

        for (Ponto p1 : pontos) {
            for (Ponto p2 : pontos) {

                int dx = (p1.getX() - p2.getX());
                int dy = (p1.getY() - p2.getY());

                int d = (int) Math.sqrt((dx * dx) + (dy * dy));

                if (d <= 50) {
                    //  System.out.println("Distancia :: " + d);

                    //g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
                    ArrayList<Ponto> rota = GPS.criarRota(p1.getX(), p1.getY(), p2.getX(), p2.getY());

                    for (Ponto a : rota) {
                        // g.drawRect(a.getX(), a.getY(), 1,1);
                        if (!unicos.em(a.getX() + "::" + a.getY())) {
                            contorno.add(a);
                        }
                    }


                }

            }
        }

        return contorno;
    }

    public ArrayList<Ponto> getArea(ArrayList<Ponto> contorno) {

        ArrayList<Ponto> a = new ArrayList<Ponto>();

        ArrayList<Ponto> a1 = getArea1(contorno);
        ArrayList<Ponto> a2 = getArea2(contorno);

        ArrayList<String> unico = new ArrayList<String>();

        System.out.println("A1 :: " + a1.size());
        System.out.println("A2 :: " + a2.size());

        for (Ponto p1 : a1) {

            String s = p1.getX() + "::" + p1.getY();

            if (!unico.contains(s)) {
                unico.add(s);

                for (Ponto p2 : a2) {
                    if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
                        a.add(p1);
                        break;
                    }
                }
            }

        }

        System.out.println("Comb(A1,A2) :: " + a.size());

        return a;
    }

    public ArrayList<Ponto> getArea1(ArrayList<Ponto> contorno) {

        ArrayList<Ponto> a = new ArrayList<Ponto>();

        if (contorno.size() == 0) {
            return a;
        }

        int altura_min = contorno.get(0).getY();
        int altura_max = contorno.get(0).getY();

        for (Ponto contornando : contorno) {

            if (contornando.getY() < altura_min) {
                altura_min = contornando.getY();
            }

            if (contornando.getY() > altura_max) {
                altura_max = contornando.getY();
            }

        }

        // System.out.println("Min :: " + altura_min);
        //   System.out.println("Max :: " + altura_max);

        for (int y = altura_min; y < altura_max; y++) {

            boolean tem = false;

            int largura_min = 0;
            int largura_max = 0;

            for (Ponto contornando : contorno) {

                if (contornando.getY() == y) {
                    if (!tem) {
                        largura_min = contornando.getX();
                        largura_max = contornando.getX();
                        tem = true;
                    }
                    if (contornando.getX() < largura_min) {
                        largura_min = contornando.getX();
                    }

                    if (contornando.getX() > largura_max) {
                        largura_max = contornando.getX();
                    }
                }
            }

            if (tem) {
                for (int x = largura_min; x < largura_max; x++) {
                    a.add(new Ponto(x, y));
                }
            }


        }

        return a;
    }

    public ArrayList<Ponto> getArea2(ArrayList<Ponto> contorno) {

        ArrayList<Ponto> a = new ArrayList<Ponto>();

        int largura_min = contorno.get(0).getX();
        int largura_max = contorno.get(0).getX();

        for (Ponto contornando : contorno) {

            if (contornando.getX() < largura_min) {
                largura_min = contornando.getX();
            }

            if (contornando.getX() > largura_max) {
                largura_max = contornando.getX();
            }

        }

        //  System.out.println("Min :: " + largura_min);
        //  System.out.println("Max :: " + largura_max);

        for (int x = largura_min; x < largura_max; x++) {

            boolean tem = false;

            int altura_min = 0;
            int altura_max = 0;

            for (Ponto contornando : contorno) {

                if (contornando.getX() == x) {
                    if (!tem) {
                        altura_min = contornando.getY();
                        altura_max = contornando.getY();
                        tem = true;
                    }
                    if (contornando.getY() < altura_min) {
                        altura_min = contornando.getY();
                    }

                    if (contornando.getY() > altura_max) {
                        altura_max = contornando.getY();
                    }
                }
            }

            if (tem) {
                for (int y = altura_min; y < altura_max; y++) {
                    a.add(new Ponto(x, y));
                }
            }


        }

        return a;
    }

    public ArrayList<Ponto> contorno_de_area(ArrayList<Ponto> mLocais) {

        ArrayList<Ponto> tudo = new ArrayList<Ponto>();

        if (mLocais.size() > 0) {
            Ponto anterior = mLocais.get(mLocais.size() - 1);

            for (Ponto eCorrente : mLocais) {


                ArrayList<Ponto> rota = GPS.criarRota(anterior.getX(), anterior.getY(), eCorrente.getX(), eCorrente.getY());
                for (Ponto a : rota) {
                    tudo.add(a);
                }

                anterior = eCorrente;
            }
        }


        return tudo;
    }

    public ArrayList<Ponto> contorno_de_area_de(ArrayList<Ponto> mLocais,int r) {

        ArrayList<Ponto> tudo = new ArrayList<Ponto>();

        if (mLocais.size() > 0) {
            Ponto anterior = mLocais.get(mLocais.size() - 1);

            for (Ponto eCorrente : mLocais) {

                int dx = (eCorrente.getX() - anterior.getX());
                int dy = (eCorrente.getY() - anterior.getY());

                int d = (int) Math.sqrt((dx * dx) + (dy * dy));

                if (d <= r) {
                    ArrayList<Ponto> rota = GPS.criarRota(anterior.getX(), anterior.getY(), eCorrente.getX(), eCorrente.getY());
                    for (Ponto a : rota) {
                        tudo.add(a);
                    }
                }


                anterior = eCorrente;
            }
        }


        return tudo;
    }

}

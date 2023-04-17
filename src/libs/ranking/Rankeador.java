package libs.ranking;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Rankeador {


    public static ArrayList<RankeadoInteiro> ordenar(ArrayList<RankeadoInteiro> itens) {

        Collections.sort(itens, new Comparator() {
            @Override
            public int compare(Object objeto_um, Object objeto_dois) {
                return Integer.compare((((RankeadoInteiro) objeto_um).getRanking()), (((RankeadoInteiro) objeto_dois).getRanking()));
            }
        });

        return itens;
    }

    public static ArrayList<RankeadoInteiro> ordenar_inverso(ArrayList<RankeadoInteiro> itens) {

        Collections.sort(itens, new Comparator() {
            @Override
            public int compare(Object objeto_um, Object objeto_dois) {
                return Integer.compare((((RankeadoInteiro) objeto_um).getRanking()), (((RankeadoInteiro) objeto_dois).getRanking())) * (-1);
            }
        });

        return itens;
    }

    public static ArrayList<RankeadoInteiro> getCopia(ArrayList<RankeadoInteiro> itens) {

        ArrayList<RankeadoInteiro> copia = new ArrayList<RankeadoInteiro>();
        for (RankeadoInteiro a : itens) {
            copia.add(a);
        }

        return copia;
    }


    public static int getMaiorChave(ArrayList<RankeadoInteiro> itens) {

        int maior = Integer.MIN_VALUE;

        for (RankeadoInteiro a : itens) {
            if (a.getRanking() > maior) {
                maior = a.getRanking();
            }
        }

        return maior;
    }

    public static int getMenorChave(ArrayList<RankeadoInteiro> itens) {

        int menor = Integer.MAX_VALUE;

        for (RankeadoInteiro a : itens) {
            if (a.getRanking() < menor) {
                menor = a.getRanking();
            }
        }

        return menor;
    }

    public static int somarChaves(ArrayList<RankeadoInteiro> itens) {

        int somando = 0;

        for (RankeadoInteiro a : itens) {
            somando += a.getRanking();
        }

        return somando;
    }

    public static ArrayList<Integer> selecionar_maiores(ArrayList<RankeadoInteiro> itens, int quantidade) {

        ArrayList<RankeadoInteiro> mais = new ArrayList<RankeadoInteiro>();
        for (RankeadoInteiro a : itens) {
            mais.add(a);
        }

        Rankeador.ordenar_inverso(mais);

        ArrayList<Integer> selecionados = new ArrayList<Integer>();

        for (RankeadoInteiro a : mais) {

            boolean existe = false;
            int valor = a.getRanking();

            for (Integer i : selecionados) {
                if (i == valor) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                selecionados.add(valor);
                if (selecionados.size() >= quantidade) {
                    break;
                }
            }

        }

        return selecionados;
    }

    public static ArrayList<Integer> selecionar_menores(ArrayList<RankeadoInteiro> itens, int quantidade) {

        ArrayList<RankeadoInteiro> mais = new ArrayList<RankeadoInteiro>();
        for (RankeadoInteiro a : itens) {
            mais.add(a);
        }

        Rankeador.ordenar(mais);

        ArrayList<Integer> selecionados = new ArrayList<Integer>();

        for (RankeadoInteiro a : mais) {

            boolean existe = false;
            int valor = a.getRanking();

            for (Integer i : selecionados) {
                if (i == valor) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                selecionados.add(valor);
                if (selecionados.size() >= quantidade) {
                    break;
                }
            }

        }

        return selecionados;
    }

    public static ArrayList<RankeadoInteiro> limitar_do_grupo(ArrayList<RankeadoInteiro> itens, ArrayList<Integer> limites) {

        ArrayList<RankeadoInteiro> selecionados = new ArrayList<RankeadoInteiro>();

        for (RankeadoInteiro a : itens) {

            boolean existe = false;
            int valor = a.getRanking();

            for (Integer i : limites) {
                if (i == valor) {
                    existe = true;
                    break;
                }
            }

            if (existe) {
                selecionados.add(a);
            }

        }

        return selecionados;
    }


}

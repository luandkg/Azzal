package libs.ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RankeadorDouble {

    // DOUBLE

    public static ArrayList<RankeadoDouble> ordenar(ArrayList<RankeadoDouble> itens) {

        Collections.sort(itens, new Comparator() {
            @Override
            public int compare(Object objeto_um, Object objeto_dois) {
                return Double.compare((((RankeadoDouble) objeto_um).getRanking()), (((RankeadoDouble) objeto_dois).getRanking()));
            }
        });

        return itens;
    }

    public static ArrayList<RankeadoDouble> ordenar_inverso(ArrayList<RankeadoDouble> itens) {

        Collections.sort(itens, new Comparator() {
            @Override
            public int compare(Object objeto_um, Object objeto_dois) {
                return Double.compare((((RankeadoDouble) objeto_um).getRanking()), (((RankeadoDouble) objeto_dois).getRanking())) * (-1);
            }
        });

        return itens;
    }


    public static ArrayList<RankeadoDouble> getCopia(ArrayList<RankeadoDouble> itens) {
        ArrayList<RankeadoDouble> mais = new ArrayList<RankeadoDouble>();
        for (RankeadoDouble a : itens) {
            mais.add(a);
        }
        return mais;
    }

    public static ArrayList<RankeadoDouble> filtrar_maior_ou_igual(ArrayList<RankeadoDouble> itens, double referencia) {
        ArrayList<RankeadoDouble> mais = new ArrayList<RankeadoDouble>();
        for (RankeadoDouble a : itens) {
            if (a.getRanking() >= referencia) {
                mais.add(a);
            }
        }
        return mais;
    }


    public static ArrayList<Double> selecionar_maiores(ArrayList<RankeadoDouble> itens, int quantidade) {

        ArrayList<RankeadoDouble> mais = new ArrayList<RankeadoDouble>();
        for (RankeadoDouble a : itens) {
            mais.add(a);
        }

        RankeadorDouble.ordenar_inverso(mais);

        ArrayList<Double> selecionados = new ArrayList<Double>();

        for (RankeadoDouble a : mais) {

            boolean existe = false;
            double valor = a.getRanking();

            for (Double i : selecionados) {
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

    public static ArrayList<Double> selecionar_menores(ArrayList<RankeadoDouble> itens, int quantidade) {

        ArrayList<RankeadoDouble> mais = new ArrayList<RankeadoDouble>();
        for (RankeadoDouble a : itens) {
            mais.add(a);
        }

        RankeadorDouble.ordenar(mais);

        ArrayList<Double> selecionados = new ArrayList<Double>();

        for (RankeadoDouble a : mais) {

            boolean existe = false;
            double valor = a.getRanking();

            for (Double i : selecionados) {
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

    public static ArrayList<RankeadoDouble> limitar(ArrayList<RankeadoDouble> itens, ArrayList<Double> limites) {

        ArrayList<RankeadoDouble> selecionados = new ArrayList<RankeadoDouble>();

        for (RankeadoDouble a : itens) {

            boolean existe = false;
            double valor = a.getRanking();

            for (Double i : limites) {
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


    public static double getMedia(ArrayList<RankeadoDouble> itens) {

        double somando = 0;
        int contando = 0;

        for (RankeadoDouble item : itens) {
            somando += item.getRanking();
            contando += 1;
        }

        if (contando > 0) {
            return somando / contando;
        }

        return 0;
    }


}

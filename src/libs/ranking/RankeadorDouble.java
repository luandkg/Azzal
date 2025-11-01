package libs.ranking;

import libs.luan.Lista;
import libs.luan.Ordenador;
import libs.luan.Ordenavel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RankeadorDouble {

    // DOUBLE

    public static Ordenavel<RankeadoDouble> ORDENAVEL_RANKEADO_DOUBLE() {
        return new Ordenavel<RankeadoDouble>() {
            @Override
            public int emOrdem(RankeadoDouble a, RankeadoDouble b) {


                int resp = Ordenavel.IGUAL;

                if (a.getRanking() > b.getRanking()) {
                    resp = Ordenavel.MAIOR;
                } else if (a.getRanking() < b.getRanking()) {
                    resp = Ordenavel.MENOR;
                }


                return resp;
            }
        };

    }


    public static Lista<RankeadoDouble> ordenar(Lista<RankeadoDouble> itens) {

        Ordenador.ORDENAR_CRESCENTE_LISTA(itens, ORDENAVEL_RANKEADO_DOUBLE());

        return itens;
    }

    public static Lista<RankeadoDouble> ordenar_inverso(Lista<RankeadoDouble> itens) {

        Ordenador.ORDENAR_CRESCENTE_LISTA(itens, ORDENAVEL_RANKEADO_DOUBLE());
        return itens;
    }


    public static Lista<RankeadoDouble> getCopia(Lista<RankeadoDouble> itens) {
        Lista<RankeadoDouble> mais = new Lista<RankeadoDouble>();
        for (RankeadoDouble a : itens) {
            mais.adicionar(a);
        }
        return mais;
    }

    public static Lista<RankeadoDouble> filtrar_maior_ou_igual(Lista<RankeadoDouble> itens, double referencia) {
        Lista<RankeadoDouble> mais = new Lista<RankeadoDouble>();
        for (RankeadoDouble a : itens) {
            if (a.getRanking() >= referencia) {
                mais.adicionar(a);
            }
        }
        return mais;
    }


    public static Lista<Double> selecionar_maiores(Lista<RankeadoDouble> itens, int quantidade) {

        Lista<RankeadoDouble> mais = new Lista<RankeadoDouble>();
        for (RankeadoDouble a : itens) {
            mais.adicionar(a);
        }

        RankeadorDouble.ordenar_inverso(mais);

        Lista<Double> selecionados = new Lista<Double>();

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
                selecionados.adicionar(valor);
                if (selecionados.getQuantidade() >= quantidade) {
                    break;
                }
            }

        }

        return selecionados;
    }

    public static Lista<Double> selecionar_menores(Lista<RankeadoDouble> itens, int quantidade) {

        Lista<RankeadoDouble> mais = new Lista<RankeadoDouble>();
        for (RankeadoDouble a : itens) {
            mais.adicionar(a);
        }

        RankeadorDouble.ordenar(mais);

        Lista<Double> selecionados = new Lista<Double>();

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
                selecionados.adicionar(valor);
                if (selecionados.getQuantidade() >= quantidade) {
                    break;
                }
            }

        }

        return selecionados;
    }

    public static Lista<RankeadoDouble> limitar(Lista<RankeadoDouble> itens, Lista<Double> limites) {

        Lista<RankeadoDouble> selecionados = new Lista<RankeadoDouble>();

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
                selecionados.adicionar(a);
            }

        }

        return selecionados;
    }


    public static double getMedia(Lista<RankeadoDouble> itens) {

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

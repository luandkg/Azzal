package libs.ranking;


import libs.luan.Lista;
import libs.luan.Ordenador;
import libs.luan.Ordenavel;


public class Rankeador {

    public static Ordenavel<RankeadoInteiro> ORDENAVEL_RANKEADO_INTEIRO() {
        return new Ordenavel<RankeadoInteiro>() {
            @Override
            public int emOrdem(RankeadoInteiro a, RankeadoInteiro b) {


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


    public static <TRef> Lista<RankeadoInteiro<TRef>> ordenar(Lista<RankeadoInteiro<TRef>> itens) {


        int n = itens.getQuantidade();
        RankeadoInteiro<TRef> temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                int ordem = ORDENAVEL_RANKEADO_INTEIRO().emOrdem(itens.get(j - 1), itens.get(j));

                if (ordem == Ordenavel.MAIOR) {
                    temp = itens.get(j - 1);
                    itens.set(j - 1, itens.get(j));
                    itens.set(j, temp);
                }

            }
        }

        return itens;
    }

    public static <TRef> Lista<RankeadoInteiro<TRef>> ordenar_inverso(Lista<RankeadoInteiro<TRef>> itens) {

        int n = itens.getQuantidade();
        RankeadoInteiro<TRef> temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                int ordem = ORDENAVEL_RANKEADO_INTEIRO().emOrdem(itens.get(j - 1), itens.get(j));

                if (ordem == Ordenavel.MENOR) {
                    temp = itens.get(j - 1);
                    itens.set(j - 1, itens.get(j));
                    itens.set(j, temp);
                }

            }
        }
        return itens;
    }

    public static Lista<RankeadoInteiro> getCopia(Lista<RankeadoInteiro> itens) {

        Lista<RankeadoInteiro> copia = new Lista<RankeadoInteiro>();
        for (RankeadoInteiro a : itens) {
            copia.adicionar(a);
        }

        return copia;
    }


    public static int getMaiorChave(Lista<RankeadoInteiro> itens) {

        int maior = Integer.MIN_VALUE;

        for (RankeadoInteiro a : itens) {
            if (a.getRanking() > maior) {
                maior = a.getRanking();
            }
        }

        return maior;
    }

    public static int getMenorChave(Lista<RankeadoInteiro> itens) {

        int menor = Integer.MAX_VALUE;

        for (RankeadoInteiro a : itens) {
            if (a.getRanking() < menor) {
                menor = a.getRanking();
            }
        }

        return menor;
    }

    public static int somarChaves(Lista<RankeadoInteiro> itens) {

        int somando = 0;

        for (RankeadoInteiro a : itens) {
            somando += a.getRanking();
        }

        return somando;
    }

    public static <T1> Lista<Integer> selecionar_maiores(Lista<RankeadoInteiro<T1>> itens, int quantidade) {

        Lista<RankeadoInteiro<T1>> mais = new Lista<RankeadoInteiro<T1>>();
        for (RankeadoInteiro<T1> a : itens) {
            mais.adicionar(a);
        }

        Rankeador.ordenar_inverso(mais);

        Lista<Integer> selecionados = new Lista<Integer>();

        for (RankeadoInteiro<T1> a : mais) {

            boolean existe = false;
            int valor = a.getRanking();

            for (Integer i : selecionados) {
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

    public static <TRef> Lista<Integer> selecionar_menores(Lista<RankeadoInteiro<TRef>> itens, int quantidade) {

        Lista<RankeadoInteiro<TRef>> mais = new Lista<RankeadoInteiro<TRef>>();
        for (RankeadoInteiro<TRef> a : itens) {
            mais.adicionar(a);
        }

        Rankeador.ordenar(mais);

        Lista<Integer> selecionados = new Lista<Integer>();

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
                selecionados.adicionar(valor);
                if (selecionados.getQuantidade() >= quantidade) {
                    break;
                }
            }

        }

        return selecionados;
    }

    public static Lista<RankeadoInteiro> limitar_do_grupo(Lista<RankeadoInteiro> itens, Lista<Integer> limites) {

        Lista<RankeadoInteiro> selecionados = new Lista<RankeadoInteiro>();

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
                selecionados.adicionar(a);
            }

        }

        return selecionados;
    }


}

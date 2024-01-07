package apps.app_citatte;

import libs.luan.Lista;
import libs.luan.Par;


public class Stringum {


    public static Lista<String> LISTA_UNICA(Lista<String> valores) {

        Lista<String> unicos = new Lista<String>();
        for (String item : valores) {
            if (!unicos.existe(item)) {
                unicos.adicionar(item);
            }
        }

        return unicos;
    }


    public static String EXIBIR_EM_LISTA(Lista<String> lista) {
        String ret = "";

        for (String item : lista) {
            ret += item + " ";
        }

        return ret;
    }

    public static String GET_MAIOR_FREQUENCIA(Lista<String> lista) {
        String ret = "";

        Lista<Par<String, Integer>> tabela_de_frequencias = new Lista<Par<String, Integer>>();

        for (String item : lista) {

            boolean existe_na_tabela = false;
            for (Par<String, Integer> freq : tabela_de_frequencias) {
                if (freq.getChave().contentEquals(item)) {
                    freq.setValor(freq.getValor() + 1);
                    existe_na_tabela = true;
                    break;
                }
            }

            if (!existe_na_tabela) {
                tabela_de_frequencias.adicionar(new Par<String, Integer>(item, 1));
            }

        }

        int maior = 0;
        for (Par<String, Integer> freq : tabela_de_frequencias) {
            if (freq.getValor() > maior) {
                maior = freq.getValor();
                ret = freq.getChave();
            }
        }


        return ret;
    }

    public static String GET_FREQUENCIA_ANALISE(Lista<String> lista) {
        String ret = "";

        Lista<Par<String, Integer>> tabela_de_frequencias = new Lista<Par<String, Integer>>();

        for (String item : lista) {

            boolean existe_na_tabela = false;
            for (Par<String, Integer> freq : tabela_de_frequencias) {
                if (freq.getChave().contentEquals(item)) {
                    freq.setValor(freq.getValor() + 1);
                    existe_na_tabela = true;
                    break;
                }
            }

            if (!existe_na_tabela) {
                tabela_de_frequencias.adicionar(new Par<String, Integer>(item, 1));
            }

        }

        int ultimo = tabela_de_frequencias.getQuantidade() - 1;
        int index = 0;

        for (Par<String, Integer> freq : tabela_de_frequencias) {
            ret += freq.getChave() + " = " + freq.getValor();
            if (index < ultimo) {
                ret += " | ";
            }
            index += 1;
        }


        return ret;
    }

    public static <T> String EXIBIR_EM_LISTA_TIPADA(Lista<T> lista) {
        String ret = "";

        for (T item : lista) {
            ret += String.valueOf(item) + " ";
        }

        return ret;
    }

}

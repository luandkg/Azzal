package libs.luan;

import java.util.ArrayList;
import java.util.Random;

public class Indexamento {

    public static final int POSICIONALMENTE_PRIMEIRO = 1;
    public static final int POSICIONALMENTE_PRIMEIRO_E_ULTIMO = 2;
    public static final int POSICIONALMENTE_ULTIMO = 3;


    public static int OBTER_POSICAO(int tamanho, int index) {
        if (index == 0 && tamanho == 1) {
            return POSICIONALMENTE_PRIMEIRO_E_ULTIMO;
        } else if (index == 0 && tamanho > 1) {
            return POSICIONALMENTE_PRIMEIRO;
        } else if (index == tamanho - 1) {
            return POSICIONALMENTE_ULTIMO;
        }
        return 0;
    }

    public static <T> ArrayList<Indexado<T>> indexe(ArrayList<T> lista) {

        ArrayList<Indexado<T>> ret = new ArrayList<Indexado<T>>();

        int tamanho = lista.size();
        int index = 0;
        for (T item : lista) {
            ret.add(new Indexado<T>(index,OBTER_POSICAO(tamanho,index), item));
            index += 1;
        }


        return ret;
    }

    public static <T> ArrayList<Indexado<T>> reverso(ArrayList<T> lista) {

        ArrayList<Indexado<T>> ret = new ArrayList<Indexado<T>>();
        int tamanho = lista.size();

        int index = lista.size() - 1;
        for (T item : lista) {
            ret.add(new Indexado<T>(index,OBTER_POSICAO(tamanho,index), item));
            index -= 1;
        }


        return ret;
    }

    public static <T> Lista<Indexado<T>> embaralhar(Lista<T> lista) {

        Lista<Indexado<T>> ret = new Lista<Indexado<T>>();
        int tamanho = lista.getQuantidade();

        int index = 0;
        for (T item : lista) {
            ret.adicionar(new Indexado<T>(index,OBTER_POSICAO(tamanho,index), item));
            index += 1;
        }

        Random r = new Random();

        for (int e = 0; e < ret.getQuantidade(); e++) {

            int p1 = r.nextInt(lista.getQuantidade());
            int p2 = r.nextInt(lista.getQuantidade());


            Indexado<T> v1 = ret.get(p1);
            Indexado<T> v2 = ret.get(p2);

            v1.setIndex(p2);
            v2.setIndex(p1);

            ret.set(p1, v2);
            ret.set(p2, v1);


        }


        return ret;
    }


    public static <T> Lista<Indexado<T>> indexe(Lista<T> lista) {

        Lista<Indexado<T>> ret = new Lista<Indexado<T>>();
        int tamanho = lista.getQuantidade();

        int index = 0;
        for (T item : lista) {
            ret.adicionar(new Indexado<T>(index,OBTER_POSICAO(tamanho,index), item));
            index += 1;
        }


        return ret;
    }

    public static <T> Lista<Indexado<T>> indexe(Vetor<T> lista) {

        Lista<Indexado<T>> ret = new Lista<Indexado<T>>();
        int tamanho = lista.getCapacidade();

        int index = 0;
        for (T item : lista) {
            ret.adicionar(new Indexado<T>(index,OBTER_POSICAO(tamanho,index), item));
            index += 1;
        }


        return ret;
    }

    public static <T> Lista<IndexadoComAnterior<T>> indexeAnteriormente(Lista<T> lista) {

        Lista<IndexadoComAnterior<T>> ret = new Lista<IndexadoComAnterior<T>>();
        int tamanho = lista.getQuantidade();

        int index_anteriormente = -1;
        T anteriormente = null;

        int index_corrente = 0;

        for (T item : lista) {
            ret.adicionar(new IndexadoComAnterior<T>(index_anteriormente, anteriormente, index_corrente, item));
            index_corrente += 1;

            anteriormente = item;
            index_anteriormente += 1;
        }


        return ret;
    }

}

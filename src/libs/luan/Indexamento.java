package libs.luan;

import java.util.ArrayList;
import java.util.Random;

public class Indexamento {

    public static <T> ArrayList<Indexado<T>> indexe(ArrayList<T> lista) {

        ArrayList<Indexado<T>> ret = new ArrayList<Indexado<T>>();

        int index = 0;
        for (T item : lista) {
            ret.add(new Indexado<T>(index, item));
            index += 1;
        }


        return ret;
    }

    public static <T> ArrayList<Indexado<T>> reverso(ArrayList<T> lista) {

        ArrayList<Indexado<T>> ret = new ArrayList<Indexado<T>>();

        int index = lista.size() - 1;
        for (T item : lista) {
            ret.add(new Indexado<T>(index, item));
            index -= 1;
        }


        return ret;
    }

    public static <T> ArrayList<Indexado<T>> embaralhar(ArrayList<T> lista) {

        ArrayList<Indexado<T>> ret = new ArrayList<Indexado<T>>();

        int index = 0;
        for (T item : lista) {
            ret.add(new Indexado<T>(index, item));
            index += 1;
        }

        Random r = new Random();

        for (int e = 0; e < ret.size(); e++) {

            int p1 = r.nextInt(lista.size());
            int p2 = r.nextInt(lista.size());


            Indexado<T> v1 = ret.get(p1);
            Indexado<T> v2 = ret.get(p2);

            v1.setIndex(p2);
            v2.setIndex(p1);

            ret.set(p1, v2);
            ret.set(p2, v1);


        }


        return ret;
    }


    public static <T> Lista<Indexado<T>> indexe(Vetor<T> lista) {

        Lista<Indexado<T>> ret = new Lista<Indexado<T>>();

        int index = 0;
        for (T item : lista) {
            ret.adicionar(new Indexado<T>(index, item));
            index += 1;
        }


        return ret;
    }


    public static <T> Lista<Indexado<T>> indexe(Lista<T> lista) {

        Lista<Indexado<T>> ret = new Lista<Indexado<T>>();

        int index = 0;
        for (T item : lista) {
            ret.adicionar(new Indexado<T>(index, item));
            index += 1;
        }


        return ret;
    }

}

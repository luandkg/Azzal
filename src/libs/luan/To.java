package libs.luan;

import java.util.ArrayList;

public class To<T> {

    public static <T> ArrayList<T> TO_ARRAY(Lista<T> eLista) {

        ArrayList<T> array = new ArrayList<T>();

        for (T e : eLista) {
            array.add(e);
        }

        return array;

    }

    public static <T> Lista<T> TO_LISTA(ArrayList<T> eArray) {

        Lista<T> lista = new Lista<T>();

        for (T e : eArray) {
            lista.adicionar(e);
        }

        return lista;

    }

}
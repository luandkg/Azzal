package AppAttuz;

import java.util.ArrayList;

public class Integers {

    public static ArrayList<Integer> ordenar(ArrayList<Integer> entradas) {

        int n = entradas.size();
        Integer temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entradas.get(j - 1) >= (entradas.get(j))) {
                    temp = entradas.get(j - 1);
                    entradas.set(j - 1, entradas.get(j));
                    entradas.set(j, temp);

                }

            }
        }
        return entradas;
    }

}

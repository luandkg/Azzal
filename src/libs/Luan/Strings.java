package libs.Luan;

import java.util.ArrayList;

public class Strings {

    public static ArrayList<String> ordenar(ArrayList<String> entradas) {

        int n = entradas.size();
        String temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entradas.get(j - 1).compareTo(entradas.get(j)) >0) {
                    temp = entradas.get(j - 1);
                    entradas.set(j - 1, entradas.get(j));
                    entradas.set(j, temp);

                }

            }
        }
        return entradas;
    }


}

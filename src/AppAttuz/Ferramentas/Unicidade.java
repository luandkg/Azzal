package AppAttuz.Ferramentas;

import java.util.ArrayList;

public class Unicidade {

    private ArrayList<String> unicos;

    public Unicidade() {
        unicos = new ArrayList<String>();
    }

    public boolean em(int item) {
        boolean ret = false;

        String sItem = String.valueOf(item);

        if (!unicos.contains(sItem)) {
            unicos.add(sItem);
            ret = true;
        }

        return ret;
    }

    public boolean em(String item) {
        boolean ret = false;

        if (!unicos.contains(item)) {
            unicos.add(item);
            ret = true;
        }
        return ret;

    }

    public void listar() {
        for (String item : unicos) {
            System.out.println("\t - " + item);
        }
    }

    public ArrayList<String> getTodos() {
        return unicos;
    }
}

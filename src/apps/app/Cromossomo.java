package apps.app;

import java.util.ArrayList;

public class Cromossomo {

    private String mNome;
    private ArrayList<Gene> mGenes;

    public Cromossomo(String eNome) {
        mNome = eNome;
        mGenes = new ArrayList<Gene>();
    }


    public String getNome() {
        return mNome;
    }


    public void adicionar_gene(Gene g) {
        mGenes.add(g);
    }

    public ArrayList<Gene> getGenes() {
        return mGenes;
    }

    public int getQuantidade() {
        return mGenes.size();
    }
}

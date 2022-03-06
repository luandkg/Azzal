package AppAttuz;

import Azzal.Utils.Cor;

import java.util.ArrayList;

public class Arkazz {

    private ArrayList<Regiao> mRegioes;

    public Arkazz() {

        mRegioes = new ArrayList<Regiao>();

        mRegioes.add(new Regiao("Alkammus", new Cor(240, 246, 164)));
        mRegioes.add(new Regiao("Ongaz", new Cor(84, 156, 231)));
        mRegioes.add(new Regiao("Umbus", new Cor(151, 163, 164)));
        mRegioes.add(new Regiao("Gonnaz", new Cor(44, 3, 235)));
        mRegioes.add(new Regiao("Bacco", new Cor(246, 182, 64)));
        mRegioes.add(new Regiao("A", new Cor(221, 34, 34)));
        mRegioes.add(new Regiao("Flum", new Cor(210, 80, 21)));
        mRegioes.add(new Regiao("Ozz", new Cor(148, 64, 31)));
        mRegioes.add(new Regiao("A", new Cor(49, 181, 103)));
        mRegioes.add(new Regiao("Skor", new Cor(180, 99, 189)));
        mRegioes.add(new Regiao("Immal", new Cor(49, 34, 103)));

    }

    public ArrayList<Regiao> getRegioes() {
        return mRegioes;
    }

    public Regiao getRegiaoDaCor(Cor eCor) {
        Regiao eRegiao = null;

        for (Regiao r : mRegioes) {
            if (r.getCor().igual(eCor)) {
                eRegiao = r;
                break;
            }
        }

        return eRegiao;
    }
}

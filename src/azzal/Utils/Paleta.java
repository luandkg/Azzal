package azzal.Utils;

import libs.Luan.Dicionario;

public class Paleta {

    private Dicionario<Cor> mCores;

    public Paleta() {

        mCores = new Dicionario<Cor>();

    }

    public boolean existeChave(String eChave) {
        return mCores.existeChave(eChave);
    }

    public void criar(String eNome, Cor eCor) {
        if (!existeChave(eNome)) {

            mCores.adicionar(eNome, eCor);

        } else {

            throw new IllegalArgumentException("Ja existe uma cor com esse nome : " + eNome);
        }

    }


    public Cor getCor(String eNome) {

        return mCores.obter(eNome);

    }

    public void remover(String eChave) {

        if (existeChave(eChave)) {
            mCores.remover(eChave);
        } else {

            throw new IllegalArgumentException("Cor nao encontrada : " + eChave);
        }
    }

    public int getQuantidade() {
        return mCores.getQuantidade();
    }


}

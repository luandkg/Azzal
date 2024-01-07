package apps.app_attuz.Regiao;

import libs.azzal.utilitarios.Cor;

public class Regiao {

    private String mNome;
    private Cor mCor;

    public Regiao(String eNome, Cor eCor) {
        mNome = eNome;
        mCor = eCor;
    }

    public String getNome() {
        return mNome;
    }

    public Cor getCor() {
        return mCor;
    }

}

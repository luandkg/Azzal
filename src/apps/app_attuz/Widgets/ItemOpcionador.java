package apps.app_attuz.Widgets;

import libs.azzal.utilitarios.Cor;

public class ItemOpcionador {

    private String mNome;
    private Cor mCorNormal;
    private Cor mCorSelecionado;

    public ItemOpcionador(String eNome, Cor eCorNormal, Cor eCorSelecionado) {
        mNome = eNome;
        mCorNormal = eCorNormal;
        mCorSelecionado = eCorSelecionado;
    }

    public String getNome() {
        return mNome;
    }

    public Cor getCorNormal() {
        return mCorNormal;
    }

    public Cor getCorSelecionado() {
        return mCorSelecionado;
    }

}

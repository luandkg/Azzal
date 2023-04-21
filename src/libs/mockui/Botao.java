package libs.mockui;

import libs.azzal.geometria.Retangulo;

public class Botao {

    private String mNome;
    private Retangulo mRect;

    public Botao(String eNome, Retangulo eRect) {
        mNome = eNome;
        mRect = eRect;
    }

    public String getNome() {
        return mNome;
    }

    public Retangulo getRetangulo() {
        return mRect;
    }

    public boolean isDentro(int eX, int eY) {
        return mRect.isDentro(eX, eY);
    }


}

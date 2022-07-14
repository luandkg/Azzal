package apps.appLetrum;

import azzal.Renderizador;
import azzal.Utils.Cor;
import apps.appLetrum.Maker.FonteRunTime;

public class FonteDuplaRunTime implements FonteDupla {

    private Fonte mFonte1;
    private Fonte mFonte2;


    public FonteDuplaRunTime(Cor eCor1, Cor eCor2, int eTamanho) {

        mFonte1 = new FonteRunTime(eCor1, eTamanho);
        mFonte2 = new FonteRunTime(eCor2, eTamanho);

    }

    @Override
    public void setRenderizador(Renderizador eRenderizador) {
        mFonte1.setRenderizador(eRenderizador);
        mFonte2.setRenderizador(eRenderizador);
    }

    @Override
    public int getLarguraDe(String frase) {
        return mFonte1.getLarguraDe(frase);
    }

    @Override
    public int getAltura() {
        return mFonte1.getAltura();
    }

    @Override
    public void escreva(int x, int y, String frase) {
        mFonte1.escreva(x, y, frase);


        int largura = getLarguraDe(frase);

       // mFonte1.getRenderizador().drawRect(x, y, largura, mFonte1.getAltura(), mFonte1.getCor());

    }

    @Override
    public void escreveLinha(int y, int x1, int x2, String eTexto1, String eTexto2) {
        mFonte1.escreveLinha(y, x1, x2, eTexto1, eTexto2);
    }

    @Override
    public void escrevaCentralizado(int x, int y, String frase) {

        int largura = getLarguraDe(frase);

        escreva(x - (largura / 2), y, frase);


    }

    public int getLarguraSelecionadaDe(String frase) {
        return mFonte2.getLarguraDe(frase);
    }


    @Override
    public void escrevaSelecionada(int x, int y, String frase) {
        mFonte2.escreva(x, y, frase);
    }

    @Override
    public void escreveLinhaSelecionada(int y, int x1, int x2, String eTexto1, String eTexto2) {
        mFonte2.escreveLinha(y, x1, x2, eTexto1, eTexto2);
    }

    @Override
    public void escrevaSelecionadaCentralizado(int x, int y, String frase) {

        int largura = getLarguraSelecionadaDe(frase);

        escrevaSelecionada(x - (largura / 2), y, frase);

    }


}

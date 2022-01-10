package Letrum;

import Azzal.Renderizador;

public interface Fonte {

    void setRenderizador(Renderizador eRenderizador);

    void escreva(int x, int y, String frase);

    int getLarguraDe(String frase);

    int getAltura();

    void escreveLinha(int y, int x1, int x2, String eTexto1, String eTexto2);


}

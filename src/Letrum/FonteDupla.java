package Letrum;

import Azzal.Renderizador;

public interface FonteDupla {

    void setRenderizador(Renderizador eRenderizador);


    int getLarguraDe(String frase);

    int getAltura();

    void escreva(int x, int y, String frase);

    void escreveLinha(int y, int x1, int x2, String eTexto1, String eTexto2);

    void escrevaSelecionada(int x, int y, String frase);

    void escreveLinhaSelecionada(int y, int x1, int x2, String eTexto1, String eTexto2);

}
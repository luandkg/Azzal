package libs.azzal;

import libs.azzal.utilitarios.Cor;

public class ComplexoRender {

    public static void sinalizar(Renderizador r, int cx, int cy, int tamanho, Cor eFundo, Cor eDestaque) {

        r.drawCirculo_Pintado(cx , cy , tamanho, eDestaque);
        r.drawCirculo_Pintado(cx  + 1, cy  + 1, tamanho-1, eFundo);
        r.drawCirculo_Pintado(cx  + 4, cy  + 4, tamanho-4, eDestaque);

    }
}

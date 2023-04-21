package libs.mockui;

import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;

public class Marcador {

    public static void marcar(Renderizador r, int px, int py, int tamanho, int tamanho_dentro, Cor eCor, Cor eCorDentro) {

        r.drawRect_Pintado(px, py, tamanho, tamanho, eCor);
        r.drawRect_Pintado(px + tamanho_dentro, py + tamanho_dentro, (tamanho_dentro * 2), (tamanho_dentro * 2), eCorDentro);


    }

    public static void marcar_barra_dupla(Renderizador r, int px, int py, int largura, int altura, Cor eCor ) {

        r.drawRect_Pintado(px, py, largura, altura,eCor);
        r.drawRect_Pintado(px + 10, py, largura, altura, eCor);

    }


}

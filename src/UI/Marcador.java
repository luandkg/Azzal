package UI;

import Azzal.Renderizador;
import Azzal.Utils.Cor;

public class Marcador {

    public static void marcar(Renderizador r, int px, int py, int tamanho, int tamanho_dentro, Cor eCor, Cor eCorDentro) {

        r.drawRect_Pintado(px, py, tamanho, tamanho, eCor);
        r.drawRect_Pintado(px + tamanho_dentro, py + tamanho_dentro, (tamanho_dentro * 2), (tamanho_dentro * 2), eCorDentro);


    }
}

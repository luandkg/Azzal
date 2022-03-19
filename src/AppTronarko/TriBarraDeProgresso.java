package AppTronarko;

import Azzal.Renderizador;
import Azzal.Utils.Cor;

public class TriBarraDeProgresso {

    public static void draw(Renderizador r, int px, int py, int tamanho, int maximo, int completo, int v1, int v2) {


        double eTaxa = (double) tamanho / (double) maximo;
        int eCompleto = (int) (eTaxa * (double) completo);

        Cor VERDE = Cor.getHexCor("#4caf50");
        Cor VERMELHO = Cor.getHexCor("#c62828");
        Cor AMARELO = Cor.getHexCor("#fdd835");
        Cor PRETO = new Cor(0, 0, 0);

        r.drawRect_Pintado(px, py, 5, 20, PRETO);
        r.drawRect_Pintado(px + 7, py, 5, 20, PRETO);
        r.drawRect_Pintado(px + 12, py + 7, tamanho, 5, PRETO);

        if (completo >= 0 && completo < v1) {
            r.drawRect_Pintado(px + 12, py + 5, eCompleto, 10, VERDE);
        } else if (completo >= v1 && completo < v2) {
            r.drawRect_Pintado(px + 12, py + 5, eCompleto, 10, AMARELO);
        } else {
            r.drawRect_Pintado(px + 12, py + 5, eCompleto, 10, VERMELHO);
        }


    }


}

package apps.app_biotzum.movimentacao;

import apps.app_biotzum.Organismo;
import libs.luan.Aleatorio;
import libs.luan.Lista;

public class MovimentadorAleatorio implements Movimentador {

    private Organismo mOrganismo;

    public MovimentadorAleatorio(Organismo eOrganismo) {
        mOrganismo = eOrganismo;
    }

    public void andar(Lista<Organismo> outros) {

        int mover_x = Aleatorio.aleatorio_entre(-3, 3);
        int mover_y = Aleatorio.aleatorio_entre(-3, 3);

        int px = mOrganismo.getX() + mover_x;
        int py = mOrganismo.getY() + mover_y;

        int gasto_de_movimentacao = mOrganismo.calcularGastoDeMovimento(mover_x, mover_y);

        if (mOrganismo.getEnergia() >= gasto_de_movimentacao) {
            if (mOrganismo.isLocalValido(px, py, outros)) {
                mOrganismo.andarDireto(mover_x, mover_y, px, py);
            }
        }

    }
}

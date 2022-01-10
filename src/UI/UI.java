package UI;

import Azzal.Formatos.Retangulo;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import LetrumArkaica.Letramento;

public class UI {

    private Letramento mLetramento;

    public UI(Letramento eLetramento) {
        mLetramento = eLetramento;
    }

    public void renderToggle(Renderizador mRenderizador,Toggle eToggle) {


        if (eToggle.getStatus()) {

            mRenderizador.drawRect_Pintado(eToggle.getRetangulo(), Cor.getHexCor("#fafafa"));
            mLetramento.escreve(eToggle.getRetangulo().getX() + eToggle.getTextoAlinhamentoX(), eToggle.getRetangulo().getY() + eToggle.getTextoAlinhamentoY(), eToggle.getNome());

            mRenderizador.drawRect_Pintado(new Retangulo(eToggle.getRetangulo().getX() + 5, eToggle.getRetangulo().getY() + 35, eToggle.getRetangulo().getLargura() - 10, 10), Cor.getHexCor("#81c784"));

        } else {
            mRenderizador.drawRect_Pintado(eToggle.getRetangulo(), Cor.getHexCor("#fafafa"));
            mLetramento.escreve(eToggle.getRetangulo().getX() + eToggle.getTextoAlinhamentoX(), eToggle.getRetangulo().getY() + eToggle.getTextoAlinhamentoY(), eToggle.getNome());

        }


    }


}

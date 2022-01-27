package UI;

import Azzal.Formatos.Retangulo;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Letrum.Fonte;

public class UI {

    private Fonte mLetramento;

    public UI(Fonte eLetramento) {
        mLetramento = eLetramento;
    }

    public void renderToggle(Renderizador mRenderizador,Toggle eToggle) {


        if (eToggle.getStatus()) {

            mRenderizador.drawRect_Pintado(eToggle.getRetangulo(), Cor.getHexCor("#fafafa"));
            mLetramento.escreva(eToggle.getRetangulo().getX() + eToggle.getTextoAlinhamentoX(), eToggle.getRetangulo().getY() + eToggle.getTextoAlinhamentoY(), eToggle.getNome());

            mRenderizador.drawRect_Pintado(new Retangulo(eToggle.getRetangulo().getX() + 5, eToggle.getRetangulo().getY() + 35, eToggle.getRetangulo().getLargura() - 10, 10), Cor.getHexCor("#81c784"));

        } else {
            mRenderizador.drawRect_Pintado(eToggle.getRetangulo(), Cor.getHexCor("#fafafa"));
            mLetramento.escreva(eToggle.getRetangulo().getX() + eToggle.getTextoAlinhamentoX(), eToggle.getRetangulo().getY() + eToggle.getTextoAlinhamentoY(), eToggle.getNome());

        }


    }


}

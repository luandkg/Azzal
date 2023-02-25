package apps.app_momentum;

import azzal.cenarios.Cena;
import azzal.utilitarios.*;
import azzal.Renderizador;
import azzal.Windows;



public class CenaBanco extends Cena {


    public CenaBanco() {

    }


    @Override
    public void iniciar(Windows eWindows) {
        eWindows.setTitle("Cena Banco");
    }


    @Override
    public void update(double dt) {


    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(new Cor(255, 255, 255));


    }


}

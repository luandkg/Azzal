package apps.app_momentum;

import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.utilitarios.Cor;


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

package libs.azzal;

import libs.azzal.cenarios.Cena;
import libs.azzal.utilitarios.Cor;


public class CenaModelo extends Cena {


    public CenaModelo() {


    }


    @Override
    public void iniciar(Windows eWindows) {
        eWindows.setTitle("Cena Modelo");
    }


    @Override
    public void update(double dt) {


    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(new Cor(255, 255, 255));


    }


}

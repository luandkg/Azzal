package azzal;

import azzal.cenarios.Cena;
import azzal.utilitarios.*;


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

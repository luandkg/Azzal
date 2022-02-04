package AppFuzz;

import AppAzzal.TransicionadorDeCena;
import Azzal.Cenarios.Cena;
import Azzal.Utils.*;
import Azzal.Renderizador;
import Azzal.Windows;


public class AppFuzz extends Cena {

    private TransicionadorDeCena mTransicionador;
    private Cor mCor;

    @Override
    public void iniciar(Windows eWindows) {

        mTransicionador = new TransicionadorDeCena(eWindows);

        mTransicionador.adicionarCena(new AppInka());
        mTransicionador.adicionarCena(new AppOcca());
        mTransicionador.adicionarCena(new AppBaz());

        mTransicionador.iniciarCenas();

        mCor=   Cor.getHexCor("#0d191e");

    }

    @Override
    public void update(double dt) {
        mTransicionador.update(dt);
    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mTransicionador.drawComCor(mRenderizador,mCor);
    }


}


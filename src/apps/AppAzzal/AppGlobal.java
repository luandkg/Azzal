package apps.AppAzzal;

import apps.appArch.AppArch;
import azzal.Cenarios.Cena;
import azzal.Renderizador;
import azzal.Windows;


public class AppGlobal extends Cena {

    private TransicionadorDeCena mTransicionador;

    @Override
    public void iniciar(Windows eWindows) {

        mTransicionador = new TransicionadorDeCena(eWindows);

        mTransicionador.adicionarCena(new Alpha());
        mTransicionador.adicionarCena(new apps.AppTronarko.AppTronarko());
        mTransicionador.adicionarCena(new AppArch());
        mTransicionador.adicionarCena(new apps.AppArquivos.AppVideo());
        mTransicionador.adicionarCena(new apps.AppArquivos.AppImagem());
        mTransicionador.adicionarCena(new apps.AppArquivos.AppAnimacao());

        mTransicionador.iniciarCenas();

    }

    @Override
    public void update(double dt) {
        mTransicionador.update(dt);
    }


    @Override
    public void draw(Renderizador mRenderizador) {
        mTransicionador.draw(mRenderizador);
    }


}

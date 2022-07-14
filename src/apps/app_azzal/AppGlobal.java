package apps.app_azzal;

import apps.app_arch.AppArch;
import azzal.Cenarios.Cena;
import azzal.Renderizador;
import azzal.Windows;


public class AppGlobal extends Cena {

    private TransicionadorDeCena mTransicionador;

    @Override
    public void iniciar(Windows eWindows) {

        mTransicionador = new TransicionadorDeCena(eWindows);

        mTransicionador.adicionarCena(new Alpha());
        mTransicionador.adicionarCena(new apps.app_tronarko.AppTronarko());
        mTransicionador.adicionarCena(new AppArch());
        mTransicionador.adicionarCena(new apps.app_arquivos.AppVideo());
        mTransicionador.adicionarCena(new apps.app_arquivos.AppImagem());
        mTransicionador.adicionarCena(new apps.app_arquivos.AppAnimacao());

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

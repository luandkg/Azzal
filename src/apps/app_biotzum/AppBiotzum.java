package apps.app_biotzum;

import libs.azzal.AzzalUnico;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;

import java.awt.*;

public class AppBiotzum extends Cena {


    public static void INICIAR(){
        AzzalUnico.unico("AppBiotzum", 1000, 1000, new AppBiotzum());
    }

    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("AppBiotzum");


    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(Color.WHITE);

    }
}

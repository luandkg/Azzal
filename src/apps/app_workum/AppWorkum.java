package apps.app_workum;

import java.awt.Point;

import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.utilitarios.Cor;

public class AppWorkum extends Cena {

    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setLocation(new Point(1000, 50));
        eWindows.setTitle("App Workum");

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(Cor.getHexCor("#37474F"));

        g.drawRect(10, 30, 500, 850, Cor.getHexCor("#FFFDE7"));
        g.drawRect(500 + 20, 30, 500, 850, Cor.getHexCor("#FFFDE7"));
        g.drawRect(1000 + 30, 30, 500, 850, Cor.getHexCor("#FFFDE7"));

    }

}

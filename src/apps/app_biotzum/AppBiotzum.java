package apps.app_biotzum;

import libs.azzal.AzzalUnico;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.Cronometro;
import libs.luan.Lista;

import java.awt.*;

public class AppBiotzum extends Cena {

    private Cores mCores;

    private Lista<Organismo> mOrganismos;
    private Lista<Comida> mComidas;

    private Cronometro mCron;

    public static void INICIAR() {
        AzzalUnico.unico("AppBiotzum", 1000, 1008, new AppBiotzum());
    }

    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("AppBiotzum");

        mCores = new Cores();
        mCron = new Cronometro(100);

        mOrganismos = new Lista<Organismo>();
        mComidas = new Lista<Comida>();

        mOrganismos.adicionar(new Organismo(10, 30));
        mOrganismos.adicionar(new Organismo(50, 30));
        mOrganismos.adicionar(new Organismo(80, 80));
        mOrganismos.adicionar(new Organismo(50, 30));
        mOrganismos.adicionar(new Organismo(40, 60));
        mOrganismos.adicionar(new Organismo(60, 90));
        mOrganismos.adicionar(new Organismo(70, 20));
        mOrganismos.adicionar(new Organismo(20, 20));

        mComidas.adicionar(new Comida(80, 5));
        mComidas.adicionar(new Comida(20, 80));
        mComidas.adicionar(new Comida(60, 40));
        mComidas.adicionar(new Comida(70, 5));
        mComidas.adicionar(new Comida(44, 80));
        mComidas.adicionar(new Comida(32, 40));

    }

    @Override
    public void update(double dt) {
        mCron.esperar();
        if (mCron.foiEsperado()) {
            mCron.zerar();
            for (Organismo org : mOrganismos) {
                org.atualizar(mOrganismos,mComidas);
            }
        }
    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(mCores.getPreto());


        for (int x = 0; x < 100; x++) {
            g.drawLinha(x * 10, 0, x * 10, g.getAltura(), mCores.getBranco());
        }

        for (int y = 0; y < 100; y++) {
            g.drawLinha(0, y * 10, g.getLargura(), y * 10, mCores.getBranco());
        }

        for (Organismo org : mOrganismos) {
            org.render(g);
        }

        for (Comida comida : mComidas) {
            comida.render(g);
        }
    }
}

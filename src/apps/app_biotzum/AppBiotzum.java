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

    Cores mCores = new Cores();

    private Lista<Organismo> mOrganismos;
    private Cronometro mCron;

    public static void INICIAR() {
        AzzalUnico.unico("AppBiotzum", 1000, 1008, new AppBiotzum());
    }

    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("AppBiotzum");
        mCron = new Cronometro(100);

        mOrganismos = new Lista<Organismo>();
        mOrganismos.adicionar(new Organismo(10, 30));
        mOrganismos.adicionar(new Organismo(50, 30));


    }

    @Override
    public void update(double dt) {
        mCron.esperar();
        if(mCron.foiEsperado()){
            mCron.zerar();
            for(Organismo org : mOrganismos){
                org.atualizar();
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

        for(Organismo org : mOrganismos){
            org.render(g);
        }
    }
}

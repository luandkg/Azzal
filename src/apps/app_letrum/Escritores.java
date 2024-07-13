package apps.app_letrum;

import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;

import java.util.ArrayList;

public class Escritores {

    private ArrayList<Fonte> fontes;

    public Escritores() {
        fontes = new ArrayList<Fonte>();
    }

    public Fonte criarFonte(Cor eCor, int eTamanho) {
        Fonte f = new FonteRunTime(eCor, eTamanho);
        fontes.add(f);
        return f;
    }


    public void render(Renderizador r) {
        for (Fonte f : fontes) {
            f.setRenderizador(r);
        }
    }

}

package Letrum;

import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Letrum.Fonte;
import Letrum.Maker.FonteRunTime;

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

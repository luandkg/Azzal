package libs.futuristico_ui;

import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;

public class TemaFuturistico {

    public Fonte ESCRITOR_TITULO_PEQUENO;
    public Fonte ESCRITOR_TITULO_MEDIO;

    public Fonte ESCRITOR_COMUM_PEQUENO;

    public TemaFuturistico() {

        ESCRITOR_TITULO_MEDIO = new FonteRunTime(Cor.getHexCor("#26C6DA"), 18);
        ESCRITOR_TITULO_PEQUENO = new FonteRunTime(Cor.getHexCor("#26C6DA"), 10);

        ESCRITOR_COMUM_PEQUENO = new FonteRunTime(Cor.getHexCor("#9CCC65"), 8);

    }

    public void draw(Renderizador g) {

        ESCRITOR_TITULO_PEQUENO.setRenderizador(g);
        ESCRITOR_TITULO_MEDIO.setRenderizador(g);

        ESCRITOR_COMUM_PEQUENO.setRenderizador(g);

    }

}

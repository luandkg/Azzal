package apps.AppAttuz.Legendas;

import apps.AppAttuz.Assessorios.Escala;
import azzal.Renderizador;
import azzal.Utils.Cor;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Legendar {

    public static BufferedImage legendar(BufferedImage entrada, Legenda eLegenda, Escala eEscala,int px,int py){

        Renderizador render = new Renderizador(entrada);

        Fonte escrever = new FonteRunTime(Cor.getRGB(Color.BLACK), 30);
        escrever.setRenderizador(render);

        for (int t = 1; t < eEscala.getMaximo(); t++) {
            render.drawRect_Pintado(px, (t * 50) + py, 30, 30, Cor.getInt((eEscala.get(t))));
            escrever.escreva(px+40, (t * 50) + py, eLegenda.get(t));
        }

        return render.toImagemSemAlfa();
    }
}

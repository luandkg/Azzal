package AppAttuz.Legendas;

import AppAttuz.Assessorios.Escala;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Letrum.Fonte;
import Letrum.Maker.FonteRunTime;

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

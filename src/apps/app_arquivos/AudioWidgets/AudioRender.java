package apps.app_arquivos.AudioWidgets;

import libs.arquivos.audio.HZ;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;

public class AudioRender {


    public static void onPlayer(Renderizador mRenderizador, int px, int py, HZ eAu) {

        mRenderizador.drawRect_Pintado(px, py, 500, 30, new Cor(255, 0, 0));

        int tamanho_retangulo = 480;

        double taxa = (double) tamanho_retangulo / 100.0;
        double au_progresso = eAu.getProgresso();

        int v = (int) (taxa * au_progresso);

        if (v > 0) {
            System.out.println("PROG :: " + au_progresso);
            mRenderizador.drawRect_Pintado(px + 10, py + 10, v, 5, new Cor(0, 255, 0));
        }

    }

    public static void onFluxoAmostragem(Renderizador mRenderizador, int[] valorado, int px, int py, Cor eCor) {

        int deslocamento_x = 0;

        for (int a = 0; a < valorado.length; a++) {

            int aa = valorado[a] / 300;

            if (a % 2 == 0) {

                mRenderizador.drawRect_Pintado(px + deslocamento_x, py - aa, 3, aa, eCor);
                deslocamento_x += 3;

            }


        }

    }

    public static void onFluxoAmostragem2(Renderizador mRenderizador, int[] valorado, int px, int py, Cor eCor) {

        int deslocamento_x = 0;

        int div = 1;

        for (int a = 0; a < valorado.length; a++) {
            if (valorado[a] >= 1000) {
                div = 300;
                break;
            }
        }

        for (int a = 0; a < valorado.length; a++) {

            int aa = valorado[a] / div;

            if (a % 2 == 0) {

                mRenderizador.drawRect_Pintado(px + deslocamento_x, py - aa, 3, aa, eCor);
                deslocamento_x += 3;

            }


        }

        deslocamento_x = 200;

        for (int a = 0; a < valorado.length; a++) {

            int aa = valorado[a] / div;

            if (a % 2 != 0) {

                mRenderizador.drawRect_Pintado(px + deslocamento_x, py - aa, 3, aa, eCor);
                deslocamento_x += 3;

            }


        }

    }
}

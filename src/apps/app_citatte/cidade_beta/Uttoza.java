package apps.app_citatte.cidade_beta;

import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.luan.Lista;

import java.util.Random;

public class Uttoza {

    private Random sorte;
    private Cores mCores;

    public Uttoza() {
        sorte = new Random();
        mCores = new Cores();
    }

    public void init(Renderizador mCidade) {

        Lista<Avenida> avenidas = new Lista<Avenida>();

        int pos_y = 50;
        for (int v = 0; v < 5 + sorte.nextInt(15); v++) {

            int px_i = 50 + sorte.nextInt(100);
            pos_y += sorte.nextInt(50);

            int quantidade = draw_horizontal(px_i, pos_y, mCidade, avenidas);

            if (quantidade < 15) {
                px_i += quantidade * 60;
                draw_horizontal(px_i, pos_y, mCidade, avenidas);
            }

            pos_y += 40 + sorte.nextInt(50);
        }

        pos_y = 50;
        int pos_x = 50;

        for (int v = 0; v < 5 + sorte.nextInt(15); v++) {

            int quantidade = draw_vertical(pos_x, pos_y, mCidade, avenidas);

            if (quantidade < 10) {
                pos_x += quantidade * 60;
                draw_vertical(pos_x, pos_y, mCidade, avenidas);
            }


            pos_x += 40 + sorte.nextInt(50);

        }


        for (Avenida av1 : avenidas) {
            for (Avenida av2 : avenidas) {
                if (av1.getAvenidaID() != av2.getAvenidaID()) {

                    if (av1.temConexaoCom(av2)) {
                        av1.setConectada(true);
                        av2.setConectada(true);
                    }

                }
            }
        }

        for (Avenida av1 : avenidas) {
            if (av1.isConectada()) {
                av1.draw(mCidade, mCores.getLaranja());
            } else {
                //    av1.draw(mCidade,mCores.getBranco());
            }
        }

    }


    public int draw_horizontal(int px_i, int py_i, Renderizador mCidade, Lista<Avenida> avenidas) {

        // HORIZONTAL

        Lista<Ponto> eixos_acima = new Lista<Ponto>();
        Lista<Ponto> eixos_abaixo = new Lista<Ponto>();

        int peca_quantidade = 5 + sorte.nextInt(6);

        //  int px_i = 50 + sorte.nextInt(100);
        //int py_i = 200 + sorte.nextInt(100);

        Avenida avenida = new Avenida(avenidas.getQuantidade(), Avenida.HORIZONTAL);
        avenidas.adicionar(avenida);

        for (int vi = 0; vi < peca_quantidade; vi++) {
            px_i += (6 + sorte.nextInt(100));
            int px = px_i;
            eixos_acima.adicionar(new Ponto(px, py_i));
            eixos_abaixo.adicionar(new Ponto(px, py_i + 5));

            avenida.adicionar_ponto(new Ponto(px, py_i), new Ponto(px, py_i + 5));
        }

        avenida.organizar();

        return peca_quantidade;
    }

    public int draw_vertical(int px_i, int py_i, Renderizador mCidade, Lista<Avenida> avenidas) {

        // HORIZONTAL

        Lista<Ponto> eixos_acima = new Lista<Ponto>();
        Lista<Ponto> eixos_abaixo = new Lista<Ponto>();

        int peca_quantidade = 5 + sorte.nextInt(8);

        //  int px_i = 50 + sorte.nextInt(100);
        //int py_i = 200 + sorte.nextInt(100);

        Avenida avenida = new Avenida(avenidas.getQuantidade(), Avenida.VERTICAL);
        avenidas.adicionar(avenida);

        for (int vi = 0; vi < peca_quantidade; vi++) {
            py_i += (6 + sorte.nextInt(100));
            int py = py_i;
            eixos_acima.adicionar(new Ponto(px_i, py));
            eixos_abaixo.adicionar(new Ponto(px_i + 5, py));

            avenida.adicionar_ponto(new Ponto(px_i, py), new Ponto(px_i + 5, py));

        }


        avenida.organizar();

        return peca_quantidade;
    }

}

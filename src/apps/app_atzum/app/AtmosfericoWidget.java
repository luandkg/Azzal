package apps.app_atzum.app;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Retangulo;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Portugues;

public class AtmosfericoWidget {

    private boolean mMovendo = false;
    private int mMovendoPx = 0;
    private int mMovendoPy = 0;

    private boolean mClicado = false;
    private int mClicadoPx = 0;

    private int superarko_selecionado = 0;

    private Cores mCores;
    private Fonte ESCRITOR_NORMAL;

    private Lista<Entidade> mDados;

    private Opcional<Entidade> mCidade;


    public AtmosfericoWidget() {

        mCores = new Cores();

        ESCRITOR_NORMAL = new FonteRunTime(mCores.getVerde(), 10);

        mDados = new Lista<Entidade>();
        mCidade = Opcional.CANCEL();

    }

    public void setDados(Lista<Entidade> dados) {
        mDados = dados;
    }

    public void marcarCidade(Entidade cidade) {
        mCidade.set(cidade);
    }

    public void retirarCidade() {
        mCidade = Opcional.CANCEL();
    }

    public void update(int px, int py, boolean is_clicado) {

        int temperatura_px_inicio = 50;
        int temperatura_py_inicio = 500;

        Retangulo ret = new Retangulo(temperatura_px_inicio, temperatura_py_inicio, 500, 200);
        if (ret.isDentro(px, py)) {
            mMovendo = true;
            mMovendoPx = px - temperatura_px_inicio;
            mMovendoPy = temperatura_py_inicio + 50;

            if (is_clicado) {
                mClicado = true;
                mClicadoPx = px - temperatura_px_inicio;
            }

        }

        superarko_selecionado = 0;

        if (mClicado) {
            superarko_selecionado = (mClicadoPx + 1);
            if (superarko_selecionado < 1) {
                superarko_selecionado = 1;
            }
            if (superarko_selecionado > 500) {
                superarko_selecionado = 500;
            }
        }

    }


    public void render(Renderizador g, int descritor_py) {

        ESCRITOR_NORMAL.setRenderizador(g);

        int temperatura_px_original = 50;
        int temperatura_px = temperatura_px_original;

        int temperatura_py = 500;
        int temperatura_py_centro = temperatura_py + 100;

        int temperatura_py_centro_acima = temperatura_py_centro - 20;
        int temperatura_py_centro_abaixo = temperatura_py_centro + 20;

        int temperatura_py_depois = temperatura_py + 220;

        ESCRITOR_NORMAL.escreva(temperatura_px_original, temperatura_py - 65, "MAPA ATMOSFÉRICO");
        g.drawRect_Pintado(temperatura_px_original, temperatura_py, 500, 200, mCores.getPreto());
        g.drawRect(temperatura_px_original - 10, temperatura_py - 30, 500 + 20, 200 + 60, mCores.getBranco());


        for (int s = 1; s <= 500; s++) {

            Lista<Entidade> acontecimentos = ENTT.COLETAR(mDados, "Superarko", s);

            if (acontecimentos.possuiObjetos()) {
                Cor acontecimento_cor = mCores.getVerde();

                Lista<Entidade> acontecimentos_furacao = ENTT.COLETAR(acontecimentos, "Evento", "FURACAO");
                Lista<Entidade> acontecimentos_tornado = ENTT.COLETAR(acontecimentos, "Evento", "TORNADO");

                if (acontecimentos_furacao.getQuantidade() > 0 && acontecimentos_tornado.getQuantidade() == 0) {
                    acontecimento_cor = mCores.getAzul();
                } else if (acontecimentos_furacao.getQuantidade() == 0 && acontecimentos_tornado.getQuantidade() > 0) {
                    acontecimento_cor = mCores.getLaranja();
                }

                for (Entidade furacao : acontecimentos_furacao) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro + 20, 1, furacao.atInt("Escala") * 10, mCores.getAzul());
                }

                for (Entidade tornado : acontecimentos_tornado) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro - 20 - (tornado.atInt("Escala") * 10), 1, tornado.atInt("Escala") * 10, mCores.getLaranja());
                }

                g.drawRect_Pintado(temperatura_px, temperatura_py_centro - 4, 1, 9, acontecimento_cor);

                if (mCidade.isOK()) {

                    for (Entidade furacao : acontecimentos_furacao) {

                        int distancia = Espaco2D.distancia_entre_pontos(mCidade.get().atInt("X"), mCidade.get().atInt("Y"), furacao.atInt("X"),furacao.atInt("Y"));

                        if(distancia<150){
                            g.drawRect_Pintado(temperatura_px, temperatura_py_centro + 100, 1, 5, mCores.getVerde());
                        }

                    }

                    for (Entidade tornado : acontecimentos_tornado) {

                        int distancia = Espaco2D.distancia_entre_pontos(mCidade.get().atInt("X"), mCidade.get().atInt("Y"), tornado.atInt("X"),tornado.atInt("Y"));

                        if(distancia<150){
                            g.drawRect_Pintado(temperatura_px, temperatura_py_centro - 100, 1, 5, mCores.getVerde());
                        }

                    }

                }

            }

            temperatura_px += 1;

        }

        g.drawRect_Pintado(temperatura_px_original, temperatura_py_centro - 1, 500, 2, mCores.getBranco());

        int temperatura_px_inicio = 50;

        if (mMovendo) {

            int superarko = getSuperarkoPosicao(mMovendoPx);

            int info_py = temperatura_py_depois + 90;

            g.drawRect_Pintado(temperatura_px_inicio + (mMovendoPx - 3), mMovendoPy - 50, 5, 200, mCores.getVermelho());

            g.drawRect_Pintado(20, info_py + 3, 10, 10, mCores.getVermelho());

            render_superarko_info(superarko, g, info_py);

        }

        if (mClicado) {

            int superarko = getSuperarkoPosicao(mClicadoPx);

            int info_py = temperatura_py_depois + 110;

            g.drawRect(temperatura_px_inicio + (mClicadoPx - 3), mMovendoPy - 50, 6, 200, mCores.getBranco());

            g.drawRect_Pintado(20, info_py + 3, 10, 10, mCores.getBranco());

            render_superarko_info(superarko, g, info_py);

        }

    }


    public int getSuperarkoPosicao(int ePosicaoX) {
        int superarko = (ePosicaoX + 1);
        if (superarko < 1) {
            superarko = 1;
        }
        if (superarko > 500) {
            superarko = 500;
        }
        return superarko;
    }


    public void render_superarko_info(int superarko, Renderizador g, int pos_y) {

        Lista<Entidade> acontecimentos = ENTT.COLETAR(mDados, "Superarko", superarko);
        Lista<Entidade> acontecimentos_furacao = ENTT.COLETAR(acontecimentos, "Evento", "FURACAO");
        Lista<Entidade> acontecimentos_tornado = ENTT.COLETAR(acontecimentos, "Evento", "TORNADO");


        ESCRITOR_NORMAL.escreveLinha(pos_y, 40, 120, "Superarko", " = " + superarko);


        ESCRITOR_NORMAL.escreva(220, pos_y, "F = " + Portugues.VALIDAR(acontecimentos_furacao.possuiObjetos(), "SIM", "NÃO"));
        ESCRITOR_NORMAL.escreva(320, pos_y, "T = " + Portugues.VALIDAR(acontecimentos_tornado.possuiObjetos(), "SIM", "NÃO"));
        //  ESCRITOR_NORMAL.escreva(420, pos_y, "C = " +"A");

    }
}

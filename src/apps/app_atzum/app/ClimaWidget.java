package apps.app_atzum.app;

import apps.app_atzum.Atzum;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.geometria.Retangulo;
import libs.azzal.utilitarios.Cor;
import libs.entt.Entidade;
import libs.luan.*;

public class ClimaWidget {

    private boolean mClimaMovendo = false;
    private int mClimaMovendoPx = 0;
    private int mClimaMovendoPy = 0;

    private boolean mClimaClicado = false;
    private int mClimaClicadoPx = 0;

    private int superarko_selecionado = 0;

    private Cores mCores;
    private Fonte ESCRITOR_NORMAL;

    private Entidade mCidade;
    private boolean mCidadeSelecionada;

    private Lista<String> mCidadeFatoresClimaticos;

    public ClimaWidget() {

        mCores = new Cores();

        ESCRITOR_NORMAL = new FonteRunTime(mCores.getVerde(), 10);

        mCidadeSelecionada = false;
        mCidadeFatoresClimaticos = new Lista<String>();

    }

    public void marcarCidade(Entidade eCidade) {
        mCidade = eCidade;
        mCidadeSelecionada = true;

        Unico<String> fatores_climaticos = new Unico<String>(Strings.IGUALAVEL());

        fatores_climaticos.limpar();

        for (int s = 1; s <= 500; s++) {
            if (Strings.isValida(mCidade.at("FC" + s))) {
                fatores_climaticos.item(mCidade.at("FC" + s));
            }
        }

        mCidadeFatoresClimaticos=fatores_climaticos.toLista();
    }

    public void retirarCidade() {
        mCidadeSelecionada = false;
    }

    public int getSuperarkoSelecionado() {
        return superarko_selecionado;
    }

    public void update(int px, int py, boolean is_clicado) {

        int temperatura_px_inicio = 50;
        int temperatura_py_inicio = 500;

        Retangulo ret = new Retangulo(temperatura_px_inicio, temperatura_py_inicio, 500, 200);
        if (ret.isDentro(px, py)) {
            mClimaMovendo = true;
            mClimaMovendoPx = px - temperatura_px_inicio;
            mClimaMovendoPy = temperatura_py_inicio + 50;

            if (is_clicado) {
                mClimaClicado = true;
                mClimaClicadoPx = px - temperatura_px_inicio;
            }

        }

        superarko_selecionado = 0;

        if (mClimaClicado) {
            superarko_selecionado = (mClimaClicadoPx + 1);
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

        ESCRITOR_NORMAL.escreva(temperatura_px_original, temperatura_py - 65, "MAPA CLIMÁTICO");
        g.drawRect_Pintado(temperatura_px_original, temperatura_py, 500, 200, mCores.getPreto());
        g.drawRect(temperatura_px_original - 10, temperatura_py - 30, 500 + 20, 200 + 60, mCores.getBranco());


        if (mCidadeSelecionada) {


            descritor_py = 300;

            ESCRITOR_NORMAL.escreva(400, descritor_py, "Fatores Climáticos");
            descritor_py += 30;
            for (Indexado<String> fator_climatico : Indexamento.indexe(mCidadeFatoresClimaticos)) {
                g.drawRect_Pintado(400, descritor_py, 10, 10, Atzum.GET_FATOR_CLIMATICO_COR(fator_climatico.get()));
                ESCRITOR_NORMAL.escreveLinha(descritor_py - 5, 400, 420, "", "" + fator_climatico.get());
                descritor_py += 20;
            }

            //  ESCRITOR_NORMAL.escreva(50, 400, "Cidade = " + mCidadeSelecionadaNome);
            //   ESCRITOR_NORMAL.escreva(50, 420, "Local  = " + mCidadeSelecionadaX + " : " + mCidadeSelecionadaY);


            int a_cada = 0;

            boolean umidade_existe_antes = false;
            Ponto umidade_antes = null;
            int umidade_antes_valor = 0;


            for (int s = 1; s <= 500; s++) {

                double temperatura = mCidade.atDouble("T" + s);
                double umidade = mCidade.atDouble("U" + s);

                boolean estaChovendo = mCidade.is("FC" + s, "CHUVA");
                boolean estaTempestadeChovendo = mCidade.is("FC" + s, "TEMPESTADE_CHUVA");
                boolean estaNevando = mCidade.is("FC" + s, "NEVE");
                boolean estaTempestadeNeve = mCidade.is("FC" + s, "TEMPESTADE_NEVE");

                boolean estaOndaDeCalor = mCidade.is("FC" + s, "ONDA_DE_CALOR");
                boolean estaSeca = mCidade.is("FC" + s, "SECA");
                boolean estaSecaExtrema = mCidade.is("FC" + s, "SECA_EXTREMA");
                boolean estaVentania = mCidade.is("FC" + s, "VENTANIA");
                boolean estaTempestadeDeVento = mCidade.is("FC" + s, "TEMPESTADE_VENTO");

                boolean estaTempestade = false;

                if (mCidade.is("FC" + s, "TEMPESTADE_CHUVA")) {
                    estaTempestade = true;
                } else if (mCidade.is("FC" + s, "TEMPESTADE_NEVE")) {
                    estaTempestade = true;
                }

                Cor temp_cor = mCores.getVerde();

                if (temperatura <= 10) {
                    temp_cor = mCores.getAzul();
                }
                if (temperatura >= 30) {
                    temp_cor = mCores.getVermelho();
                }

                // if(a_cada==3) {
                if (estaChovendo) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 60, 3, 3, Atzum.COR_CHUVA);
                }
                if (estaTempestadeChovendo) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 70, 3, 3, Atzum.COR_TEMPESTADE_CHUVA);
                }
                if (estaNevando) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 80, 3, 3, Atzum.COR_NEVE);
                }
                if (estaTempestadeNeve) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 90, 3, 3, Atzum.COR_TEMPESTADE_NEVE);
                }

                if (estaTempestade) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo + 50, 3, 3, Atzum.COR_TEMPESTADE_VENTO);
                }


                if (estaOndaDeCalor) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 60, 3, 3, Atzum.COR_ONDA_DE_CALOR);
                }
                if (estaSeca) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 70, 3, 3, Atzum.COR_SECA);
                }
                if (estaSecaExtrema) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 80, 3, 3, Atzum.COR_SECA_EXTREMA);
                }

                if (estaVentania) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 50, 3, 3, Atzum.COR_VENTANIA);
                }
                if (estaTempestadeDeVento) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - 40, 3, 3, Atzum.COR_TEMPESTADE_VENTO);
                }
                // }


                if (temperatura > 0) {
                    int temperatura_modulo = (int) temperatura;

                    if (a_cada == 3) {
                        g.drawRect_Pintado(temperatura_px, temperatura_py_centro_acima - temperatura_modulo, 1, temperatura_modulo, temp_cor);
                    }

                } else {
                    int temperatura_modulo = ((int) temperatura) * (-1);

                    if (a_cada == 3) {
                        g.drawRect_Pintado(temperatura_px, temperatura_py_centro_abaixo, 1, temperatura_modulo, temp_cor);
                    }
                }

                int i_umidade = (int) umidade;
                //   g.drawRect_Pintado(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, 3, 3, mCores.getAzul());

                if (i_umidade <= 30) {
                    g.drawPixel(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, mCores.getVermelho());
                } else {
                    g.drawPixel(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, mCores.getAzul());
                }

                if (umidade_existe_antes) {

                    if (umidade_antes_valor <= 30 && i_umidade <= 30) {
                        g.drawLinha(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, umidade_antes.getX(), umidade_antes.getY(), mCores.getVermelho());
                    } else if (umidade_antes_valor > 30 && i_umidade <= 30) {
                        g.drawLinha(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, umidade_antes.getX(), umidade_antes.getY(), mCores.getLaranja());
                    } else if (umidade_antes_valor <= 30 && i_umidade > 30) {
                        g.drawLinha(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, umidade_antes.getX(), umidade_antes.getY(), mCores.getLaranja());
                    } else {
                        g.drawLinha(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2, umidade_antes.getX(), umidade_antes.getY(), mCores.getAzul());
                    }

                }

                umidade_existe_antes = true;
                umidade_antes_valor = i_umidade;
                umidade_antes = new Ponto(temperatura_px, (temperatura_py_centro_acima - i_umidade) - 2);


                if (temperatura >= 30) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro - 4, 1, 9, mCores.getVermelho());
                }
                if (temperatura <= -5) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro - 4, 1, 9, mCores.getAzul());
                }

                if (temperatura > -5 && temperatura < 30) {
                    g.drawRect_Pintado(temperatura_px, temperatura_py_centro - 4, 1, 9, mCores.getVerde());
                }

                temperatura_px += 1;
                a_cada += 1;
                if (a_cada == 5) {
                    a_cada = 0;
                }
            }

            g.drawRect_Pintado(temperatura_px_original, temperatura_py_centro - 1, 500, 2, mCores.getBranco());

            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 15, 40, 70, "TMin", " = " + mCidade.at("tMin"));
            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 15, 150, 180, "TMax", " = " + mCidade.at("tMax"));

            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 30, 40, 70, "UMin", " = " + mCidade.at("uMin"));
            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 30, 150, 180, "UMax", " = " + mCidade.at("uMax"));

            //ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois+30, 50, 120, "TMax", " = " + mCidade.at("TMax"));

            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 15, 260, 360, "Hiperestação", " = " + mCidade.at("Hiperestacao"));
            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 30, 260, 360, "Vegetação", " = " + mCidade.at("Vegetacao"));

            ESCRITOR_NORMAL.escreveLinha(temperatura_py_depois + 60, 40, 70, "UV" + getSuperarkoSelecionado(), " = " + mCidade.at("UV" + getSuperarkoSelecionado()));

        }

        int temperatura_px_inicio = 50;

        if (mCidadeSelecionada && mClimaMovendo) {

            int superarko = getSuperarkoPosicao(mClimaMovendoPx);

            int info_py = temperatura_py_depois + 90;

            g.drawRect_Pintado(temperatura_px_inicio + (mClimaMovendoPx - 3), mClimaMovendoPy - 50, 5, 200, mCores.getVermelho());

            g.drawRect_Pintado(20, info_py + 3, 10, 10, mCores.getVermelho());

            render_superarko_info(superarko, g, info_py);

        }

        if (mCidadeSelecionada && mClimaClicado) {

            int superarko = getSuperarkoPosicao(mClimaClicadoPx);

            int info_py = temperatura_py_depois + 110;

            g.drawRect(temperatura_px_inicio + (mClimaClicadoPx - 3), mClimaMovendoPy - 50, 6, 200, mCores.getBranco());

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


        ESCRITOR_NORMAL.escreveLinha(pos_y, 40, 120, "Superarko", " = " + superarko);

        double temperatura = mCidade.atDouble("T" + superarko);
        double umidade = mCidade.atDouble("U" + superarko);
        String fator_climatico = mCidade.at("FC" + superarko);

        ESCRITOR_NORMAL.escreva(220, pos_y, "T = " + fmt.f2(temperatura));
        ESCRITOR_NORMAL.escreva(320, pos_y, "U = " + fmt.f2(umidade));
        ESCRITOR_NORMAL.escreva(420, pos_y, "C = " + fator_climatico);

    }

}

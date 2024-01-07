package apps.app_citatte.cidade_beta;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_attuz.Ferramentas.GPS;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;
import libs.luan.fmt;

public class Akkone {

    private AnaliseCitatte mAnaliseCitatte;
    public int habitavel = 0;

    public void init(Renderizador mCidade) {

        Cores mCores = new Cores();
        Lista<Par<Ponto, Integer>> regioes = new Lista<Par<Ponto, Integer>>();


        String local_assets = "/home/luan/assets";

        String arquivo_alfa = local_assets + "/citatte_alfa.dkg";
        String arquivo_beta = local_assets + "/citatte_beta.dkg";
        String arquivo_gamma = local_assets + "/citatte_gamma.dkg";
        String arquivo_delta = local_assets + "/citatte_delta.dkg";
        String arquivo_epsilon = local_assets + "/citatte_epsilon.dkg";

        String arquivo_unica = local_assets + "/citatte_unica.dkg";

        CitatteBetaConstrutor cc = new CitatteBetaConstrutor();


        //  cc.carregar_citatte(arquivo_alfa, mCidade);
        //   cc.carregar_citatte(arquivo_beta, mCidade);
        //    cc.carregar_citatte(arquivo_gamma, mCidade);
        //    cc.carregar_citatte(arquivo_delta, mCidade);
        //       cc.carregar_citatte(arquivo_epsilon, mCidade);


        cc.carregar_citatte(arquivo_unica, mCidade);

        mAnaliseCitatte = new AnaliseCitatte();
        //  habitavel = mAnaliseCitatte.GET_HABITAVEL_RODEADO_PRETO(mCidade);

        // mAnaliseCitatte.retirar_brancos_solos(mCidade);

        Lista<Ponto> habitaveis = mAnaliseCitatte.GET_HABITAVEL_RODEADO_PRETO_LISTA(mCidade);
        habitaveis = mAnaliseCitatte.getBrancos(mCidade);

        for (Ponto px : habitaveis) {
            mAnaliseCitatte.retirar_branco_se_azul_ou_amarelo(mCidade, px);
        }

        habitaveis = mAnaliseCitatte.getBrancos(mCidade);

        //   mAnaliseCitatte.marcar_regiao_laranja(mCidade);


        habitavel = habitaveis.getQuantidade();

        // retirar_brancos();


        boolean criar_rotas_pequenas = false;
        if (criar_rotas_pequenas) {
            for (Ponto px : habitaveis) {

                Opcional<Ponto> proximo = Espaco2D.GET_UM_DOS_MAIS_PROXIMO(px, 10, habitaveis);
                if (proximo.isOK()) {
                    for (Ponto pp : Espaco2D.getPontosDeLinha(px, proximo.get())) {
                        mCidade.drawPixel(pp.getX(), pp.getY(), mCores.getTurquesa());
                    }
                }

                fmt.print("Rota :: {}", px.toString());
            }
        }


        regioes = new Lista<Par<Ponto, Integer>>();

        int i = 0;
        int e = 0;

        habitaveis.limpar();

        for (Ponto px : habitaveis) {

            if (mAnaliseCitatte.isRegiao(mCidade, px)) {

                int tamanho = mAnaliseCitatte.pintar_regiao(mCidade, px);

                if (mCidade.getPixel(px.getX(), px.getY()).igual(mCores.getBranco())) {
                    regioes.adicionar(new Par<Ponto, Integer>(px, tamanho));
                    mCidade.exportarSemAlfa(local_assets + "/regioes/regiao" + regioes.getQuantidade() + ".png");
                    mAnaliseCitatte.guardar_regioes(local_assets + "/regioes.dkg", regioes);
                }

            }


            if (i > 10) {
                e += 1;
                i = 0;
                break;
            }


            i += 1;
        }


        int afastar_x = 100;
        int afastar_y = 50;

        Lista<Ponto> citatte_alfa = new Lista<Ponto>();
        citatte_alfa.adicionar(new Ponto(afastar_x + 100, afastar_y + 100));
        citatte_alfa.adicionar(new Ponto(afastar_x + 400, afastar_y + 430));
        citatte_alfa.adicionar(new Ponto(afastar_x + 200, afastar_y + 200));
        citatte_alfa.adicionar(new Ponto(afastar_x + 500, afastar_y + 400));

        citatte_alfa.adicionar(new Ponto(afastar_x + 250, afastar_y + 280));
        citatte_alfa.adicionar(new Ponto(afastar_x + 570, afastar_y + 160));
        citatte_alfa.adicionar(new Ponto(afastar_x + 60, afastar_y + 340));


        //   cc.construir(citatte_alfa, mCidade);
        //  cc.construir_arquivo(citatte_alfa, arquivo_alfa);

        afastar_x = 800;
        afastar_y = 100;

        Lista<Ponto> citatte_beta = new Lista<Ponto>();
        citatte_beta.adicionar(new Ponto(afastar_x + 100, afastar_y + 100));
        citatte_beta.adicionar(new Ponto(afastar_x + 400, afastar_y + 430));
        citatte_beta.adicionar(new Ponto(afastar_x + 200, afastar_y + 200));

        // cc.construir(citatte_beta, mCidade);
        //  cc.construir_arquivo(citatte_beta, arquivo_beta);


        afastar_x = 700;
        afastar_y = 400;

        Lista<Ponto> citatte_gamma = new Lista<Ponto>();
        citatte_gamma.adicionar(new Ponto(afastar_x + 100, afastar_y + 100));
        citatte_gamma.adicionar(new Ponto(afastar_x + 250, afastar_y + 300));
        citatte_gamma.adicionar(new Ponto(afastar_x + 50, afastar_y + 150));

        // cc.construir(citatte_gamma, mCidade);
        //  cc.construir_arquivo(citatte_gamma, arquivo_gamma);


        Lista<Ponto> citatte_alfa_gama = new Lista<Ponto>();
        Lista<Ponto> citatte_beta_gama = new Lista<Ponto>();


        int alfa_afastar_x = 100;
        int alfa_afastar_y = 50;

        int beta_afastar_x = 800;
        int beta_afastar_y = 100;

        int gamma_afastar_x = 700;
        int gamma_afastar_y = 400;

        citatte_alfa_gama.adicionar(new Ponto(alfa_afastar_x + 200, alfa_afastar_y + 200));
        citatte_alfa_gama.adicionar(new Ponto(gamma_afastar_x + 100, gamma_afastar_y + 100));


        citatte_beta_gama.adicionar(new Ponto(beta_afastar_x + 200, beta_afastar_y + 200));
        citatte_beta_gama.adicionar(new Ponto(gamma_afastar_x + 100, gamma_afastar_y + 100));


        // cc.construir(citatte_alfa_gama, mCidade);
        //  cc.construir(citatte_beta_gama, mCidade);

        //  cc.construir_arquivo(citatte_alfa_gama, arquivo_delta);
        //  cc.construir_arquivo(citatte_beta_gama, arquivo_epsilon);
    }


    public void retirar_brancos(Renderizador mCidade, Lista<Ponto> habitaveis) {

        Cores mCores = new Cores();

        int retirar = 0;
        int retiravel = 3;

        int retirados = 0;

        for (Ponto px : habitaveis) {

            if (retirar == retiravel) {
                retirar = 0;

                mCidade.drawPixel(px.getX(), px.getY(), mCores.getPreto());
                retirados += 1;
            }

            retirar += 1;
        }

        fmt.print("Retirados = {}", retirados);

        habitaveis = mAnaliseCitatte.getBrancos(mCidade);
        habitavel = habitaveis.getQuantidade();

        for (Ponto px : habitaveis) {

            if (mCidade.getPixel(px.getX(), px.getY()).igual(mCores.getBranco())) {

                boolean enc = false;
                Ponto prox = null;
                int distancia = 0;

                for (Ponto px2 : habitaveis) {
                    if (px.isDiferente(px2)) {

                        if (mCidade.getPixel(px2.getX(), px2.getY()).igual(mCores.getBranco()) || mCidade.getPixel(px2.getX(), px2.getY()).igual(mCores.getTurquesa())) {

                            if (!enc) {
                                distancia = Espaco2D.distancia_entre_pontos(px, px2);
                                prox = px2;
                                enc = true;
                            } else {
                                if (Espaco2D.distancia_entre_pontos(px, px2) < distancia) {
                                    prox = px2;
                                }
                            }

                        }

                    }

                }

                if (enc) {
                    mCidade.drawPixel(px.getX(), px.getY(), mCores.getTurquesa());
                    mCidade.drawPixel(prox.getX(), prox.getY(), mCores.getTurquesa());
                    for (Ponto pp : GPS.criarRotaReta(px, prox)) {
                        //      mCidade.drawPixel(pp.getX(), pp.getY(), mCores.getTurquesa());
                    }
                }

            }

        }

    }
}

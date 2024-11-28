package apps.app_atzum.app;

import libs.arquivos.dsvideo.DSVideo;
import libs.azzal.Cores;
import libs.imagem.Efeitos;
import libs.luan.RefInt;
import libs.luan.fmt;
import libs.mockui.Interface.Acao;

public class AtzumBotoesPrincipais {

    public static void criar_camadas(GrupoDeBotoesGrandes grupo, GrupoDeBotoesGrandes mSubCamadas, AppAtzum app) {

        Cores mCores = new Cores();


        grupo.criarCamada("Cidades", mCores.getLaranja()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mWidgetMapaVisualizador.setMapaPequeno(Efeitos.reduzirMetade(app.mArquivoAtzumGeral.GET_MAPA_DE_CONTORNO()));
                grupo.setSelecionado("Cidades");
                mSubCamadas.zerar();
                app.mVideoEmExecucao.parar();

            }
        });

        grupo.criarCamada("Regiões", mCores.getLaranja()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mWidgetMapaVisualizador.setMapaPequeno(Efeitos.reduzirMetade(app.mArquivoAtzumGeral.GET_MAPA_DE_REGIOES()));


                grupo.setSelecionado("Regiões");
                mSubCamadas.zerar();
                app.mVideoEmExecucao.parar();

            }
        });

        grupo.criarCamada("Relevo", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mWidgetMapaVisualizador.setMapaPequeno(Efeitos.reduzirMetade(app.mArquivoAtzumGeral.GET_MAPA_DE_RELEVO()));


                grupo.setSelecionado("Relevo");
                mSubCamadas.zerar();
                app.mVideoEmExecucao.parar();

            }
        });


        grupo.criarCamada("Oceanos", mCores.getAzul()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mWidgetMapaVisualizador.setMapaPequeno(Efeitos.reduzirMetade(app.mArquivoAtzumGeral.GET_MAPA_DE_OCEANOS()));

                grupo.setSelecionado("Oceanos");
                mSubCamadas.zerar();
                app.mVideoEmExecucao.parar();


            }
        });

        grupo.criarCamada("Modelo Climático", mCores.getLaranja()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mWidgetMapaVisualizador.setMapaPequeno(Efeitos.reduzirMetade(app.mArquivoAtzumTronarko.GET_MODELO_CLIMATICO()));

                grupo.setSelecionado("Modelo Climático");
                mSubCamadas.zerar();
                app.mVideoEmExecucao.parar();


            }
        });

        grupo.criarCamada("Modelo Vegetação", mCores.getRosa()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mWidgetMapaVisualizador.setMapaPequeno(Efeitos.reduzirMetade(app.mArquivoAtzumTronarko.GET_MODELO_VEGETACAO()));


                grupo.setSelecionado("Modelo Vegetação");
                mSubCamadas.zerar();
                app.mVideoEmExecucao.parar();

            }
        });


        grupo.criarCamada("Umidade", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                criar_subcamadas(mSubCamadas, app, "umidade");
                grupo.setSelecionado("Umidade");
                app.mVideoEmExecucao.parar();

            }
        });

        grupo.criarCamada("Temperatura", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                criar_subcamadas(mSubCamadas, app, "temperatura");
                grupo.setSelecionado("Temperatura");
                app.mVideoEmExecucao.parar();

            }
        });

        grupo.criarCamada("MassaDeAr", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                criar_subcamadas(mSubCamadas, app, "massa_de_ar");
                grupo.setSelecionado("MassaDeAr");
                app.mVideoEmExecucao.parar();

            }
        });

        grupo.criarCamada("FatorClimatico", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                criar_subcamadas(mSubCamadas, app, "fator_climatico");

                mSubCamadas.criarCamadaComNome("FC", "FC", mCores.getVermelho()).setAcao(new Acao() {
                    @Override
                    public void onClique() {


                        DSVideo video = app.mArquivoAtzumTronarko.GET_VIDEO("@animacao/fatores_climaticos.vi");

                        app.mVideoEmExecucao.reproduzirVideo(video);

                        mSubCamadas.setSelecionado("FC");
                       // app.mVideoEmExecucao.parar();

                    }
                });

                grupo.setSelecionado("FatorClimatico");

            }
        });

    }


    public static void criar_subcamadas(GrupoDeBotoesGrandes mSubCamadas, AppAtzum app, String tipo) {

        Cores mCores = new Cores();

        mSubCamadas.zerar();

        int o_superarko_valor = 25;

        for (int modelo = 1; modelo <= 10; modelo++) {

            RefInt ref_modelo = new RefInt(modelo);

            mSubCamadas.criarCamadaComNome(String.valueOf(ref_modelo.get()), String.valueOf(o_superarko_valor), mCores.getLaranja()).setAcao(new Acao() {
                @Override
                public void onClique() {

                    app.mWidgetMapaVisualizador.setMapaPequeno(Efeitos.reduzirMetade(app.mArquivoAtzumTronarko.GET_MAPA_MODELO(tipo, String.valueOf(ref_modelo.get()))));

                    mSubCamadas.setSelecionado(String.valueOf(ref_modelo.get()));
                    fmt.print("SubCamada :: {} !", tipo);
                    app.mVideoEmExecucao.parar();

                }
            });

            o_superarko_valor += 50;

        }

        if (mSubCamadas.getQuantidade() > 0) {
            mSubCamadas.aplicarCamada("1");
        }

    }

    public static void criar_camadas_zoom(GrupoDeBotoesGrandes mCamadasZoom, AppAtzum app) {

        mCamadasZoom.zerar();

        Cores mCores = new Cores();

        mCamadasZoom.criarCamada("Regiões", mCores.getLaranja()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mWidgetMapaVisualizador.setMapaGrande(app.mArquivoAtzumGeral.GET_MAPA_DE_REGIOES());

                mCamadasZoom.setSelecionado("Regiões");
                app.mMapaZoom.update(true);


            }
        });

        mCamadasZoom.criarCamada("Relevo", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mWidgetMapaVisualizador.setMapaGrande(app.mArquivoAtzumGeral.GET_MAPA_DE_RELEVO());

                mCamadasZoom.setSelecionado("Relevo");
                app.mMapaZoom.update(true);

            }
        });


        mCamadasZoom.criarCamada("Oceanos", mCores.getAzul()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mWidgetMapaVisualizador.setMapaGrande(app.mArquivoAtzumGeral.GET_MAPA_DE_OCEANOS());

                mCamadasZoom.setSelecionado("Oceanos");
                app.mMapaZoom.update(true);

            }
        });

        mCamadasZoom.criarCamada("Modelo Climático", mCores.getLaranja()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mWidgetMapaVisualizador.setMapaGrande(app.mArquivoAtzumTronarko.GET_MODELO_CLIMATICO());

                mCamadasZoom.setSelecionado("Modelo Climático");
                app.mMapaZoom.update(true);

            }
        });

        mCamadasZoom.criarCamada("Modelo Vegetação", mCores.getRosa()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mWidgetMapaVisualizador.setMapaGrande(app.mArquivoAtzumTronarko.GET_MODELO_VEGETACAO());

                mCamadasZoom.setSelecionado("Modelo Vegetação");
                app.mMapaZoom.update(true);

            }
        });

    }

}

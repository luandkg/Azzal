package apps.app_atzum.app;

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

                app.mapa_pequeno = app.mArquivoAtzumGeral.GET_MAPA_DE_CONTORNO();
                app.mapa_pequeno = Efeitos.reduzirMetade(app.mapa_pequeno);
                grupo.setSelecionado("Cidades");
                mSubCamadas.zerar();

            }
        });

        grupo.criarCamada("Regiões", mCores.getLaranja()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mapa_pequeno = app.mArquivoAtzumGeral.GET_MAPA_DE_REGIOES();
                app.mapa_pequeno = Efeitos.reduzirMetade(app.mapa_pequeno);
                grupo.setSelecionado("Regiões");
                mSubCamadas.zerar();

            }
        });

        grupo.criarCamada("Relevo", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mapa_pequeno = app.mArquivoAtzumGeral.GET_MAPA_DE_RELEVO();
                app.mapa_pequeno = Efeitos.reduzirMetade(app.mapa_pequeno);
                grupo.setSelecionado("Relevo");
                mSubCamadas.zerar();

            }
        });


        grupo.criarCamada("Oceanos", mCores.getAzul()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mapa_pequeno = app.mArquivoAtzumGeral.GET_MAPA_DE_OCEANOS();
                app.mapa_pequeno = Efeitos.reduzirMetade(app.mapa_pequeno);
                grupo.setSelecionado("Oceanos");
                mSubCamadas.zerar();


            }
        });

        grupo.criarCamada("Modelo Climático", mCores.getLaranja()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mapa_pequeno = app.mArquivoAtzumTronarko.GET_MODELO_CLIMATICO();
                app.mapa_pequeno = Efeitos.reduzirMetade(app.mapa_pequeno);
                grupo.setSelecionado("Modelo Climático");
                mSubCamadas.zerar();


            }
        });

        grupo.criarCamada("Modelo Vegetação", mCores.getRosa()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mapa_pequeno = app.mArquivoAtzumTronarko.GET_MODELO_VEGETACAO();
                app.mapa_pequeno = Efeitos.reduzirMetade(app.mapa_pequeno);
                grupo.setSelecionado("Modelo Vegetação");
                mSubCamadas.zerar();

            }
        });


        grupo.criarCamada("Umidade", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                criar_subcamadas(mSubCamadas, app, "umidade");
                grupo.setSelecionado("Umidade");

            }
        });

        grupo.criarCamada("Temperatura", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                criar_subcamadas(mSubCamadas, app, "temperatura");
                grupo.setSelecionado("Temperatura");

            }
        });

        grupo.criarCamada("MassaDeAr", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                criar_subcamadas(mSubCamadas, app, "massa_de_ar");
                grupo.setSelecionado("MassaDeAr");

            }
        });

        grupo.criarCamada("FatorClimatico", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {

                criar_subcamadas(mSubCamadas, app, "fator_climatico");
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

                    app.mapa_pequeno = app.mArquivoAtzumTronarko.GET_MAPA_MODELO(tipo, String.valueOf(ref_modelo.get()));
                    app.mapa_pequeno = Efeitos.reduzirMetade(app.mapa_pequeno);
                    mSubCamadas.setSelecionado(String.valueOf(ref_modelo.get()));
                    fmt.print("SubCamada :: {} !", tipo);
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

                app.mapa_grande = app.mArquivoAtzumGeral.GET_MAPA_DE_REGIOES();
                mCamadasZoom.setSelecionado( "Regiões");
                app. drone_update(true);

            }
        });

        mCamadasZoom.criarCamada("Relevo", mCores.getVermelho()).setAcao(new Acao() {
            @Override
            public void onClique() {


                app.mapa_grande = app.mArquivoAtzumGeral.GET_MAPA_DE_RELEVO();
                mCamadasZoom.setSelecionado("Relevo");
                app.  drone_update(true);

            }
        });


        mCamadasZoom.criarCamada("Oceanos", mCores.getAzul()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mapa_grande = app.mArquivoAtzumGeral.GET_MAPA_DE_OCEANOS();
                mCamadasZoom.setSelecionado("Oceanos");
                app.   drone_update(true);

            }
        });

        mCamadasZoom.criarCamada("Modelo Climático", mCores.getLaranja()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mapa_grande = app.mArquivoAtzumTronarko.GET_MODELO_CLIMATICO();
                mCamadasZoom.setSelecionado("Modelo Climático");
                app.  drone_update(true);

            }
        });

        mCamadasZoom.criarCamada("Modelo Vegetação", mCores.getRosa()).setAcao(new Acao() {
            @Override
            public void onClique() {

                app.mapa_grande = app.mArquivoAtzumTronarko.GET_MODELO_VEGETACAO();
                mCamadasZoom.setSelecionado( "Modelo Vegetação");
                app.  drone_update(true);

            }
        });

    }

}

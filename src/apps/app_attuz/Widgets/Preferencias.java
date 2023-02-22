package apps.app_attuz.Widgets;

import libs.arquivos.PreferenciasOrganizadas;
import azzal.Renderizador;
import azzal.utilitarios.Cor;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;
import mockui.Interface.Acao;
import mockui.ToggleHorizontal;

public class Preferencias {

    private PreferenciasOrganizadas pref;

    public ToggleHorizontal mMostrarMarcadores;
    public ToggleHorizontal mMostrarCidades;
    public ToggleHorizontal mMostrarPontosCidades;
    public ToggleHorizontal mMostrarMares;
    public ToggleHorizontal mMostrarEU;

    private Fonte pequeno;

    public Preferencias(String LOCAL) {

        pequeno = new FonteRunTime(new Cor(0, 0, 0), 11);

        preferencias_construir();

        pref = new PreferenciasOrganizadas(LOCAL + "conf.po");
        pref.abrirSeExistir();

        mMostrarMarcadores.setValor(pref.getLogico("Marcadores", "Mostrar"));
        mMostrarCidades.setValor(pref.getLogico("CidadesNomes", "Mostrar"));
        mMostrarPontosCidades.setValor(pref.getLogico("CidadesPontos", "Mostrar"));
        mMostrarMares.setValor(pref.getLogico("Mares", "Mostrar"));
        mMostrarEU.setValor(pref.getLogico("EU", "Mostrar"));


    }


    public void onClique(int px,int py){

        mMostrarMarcadores.foiClicado(px - 3, py - 5);
        mMostrarCidades.foiClicado(px - 3, py - 5);
        mMostrarMares.foiClicado(px - 3, py - 5);
        mMostrarPontosCidades.foiClicado(px - 3, py - 5);
        mMostrarEU.foiClicado(px - 3, py - 5);


    }

    public void salvarConf() {


        pref.setLogico("Marcadores", "Mostrar", mMostrarMarcadores.getValor());
        pref.setLogico("CidadesNomes", "Mostrar", mMostrarCidades.getValor());
        pref.setLogico("CidadesPontos", "Mostrar", mMostrarPontosCidades.getValor());
        pref.setLogico("Mares", "Mostrar", mMostrarMares.getValor());
        pref.setLogico("EU", "Mostrar", mMostrarEU.getValor());

        pref.salvar();

    }

    public void panielConfiguracoes(Renderizador mRender) {

        pequeno.setRenderizador(mRender);

        mMostrarMarcadores.onRender(mRender, pequeno);
        mMostrarCidades.onRender(mRender, pequeno);
        mMostrarPontosCidades.onRender(mRender, pequeno);
        mMostrarMares.onRender(mRender, pequeno);
        mMostrarEU.onRender(mRender, pequeno);

    }


    public void preferencias_construir() {


        mMostrarMarcadores = new ToggleHorizontal(1000, 40, "MOSTRAR MARCADORES", true);

        mMostrarMarcadores.setAcao(new Acao() {
            @Override
            public void onClique() {
                salvarConf();
            }
        });


        mMostrarCidades = new ToggleHorizontal(1000, 60, "MOSTRAR CIDADES", true);

        mMostrarCidades.setAcao(new Acao() {
            @Override
            public void onClique() {
                salvarConf();
            }
        });


        mMostrarPontosCidades = new ToggleHorizontal(1000, 80, "MOSTRAR PONTOS DE CIDADES", true);

        mMostrarPontosCidades.setAcao(new Acao() {
            @Override
            public void onClique() {
                salvarConf();
            }
        });


        mMostrarMares = new ToggleHorizontal(1000, 100, "MOSTRAR MARES", true);

        mMostrarMares.setAcao(new Acao() {
            @Override
            public void onClique() {
                salvarConf();
            }
        });




        mMostrarEU = new ToggleHorizontal(1400, 40, "MOSTRAR PERCURSO", true);

        mMostrarEU.setAcao(new Acao() {
            @Override
            public void onClique() {
                salvarConf();
            }
        });
    }

}

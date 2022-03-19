package AppAttuz;

import Arquivos.PreferenciasOrganizadas;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Letrum.Fonte;
import Letrum.Maker.FonteRunTime;
import UI.Interface.Acao;
import UI.ToggleHorizontal;

import java.awt.*;

public class Preferencias {

    private PreferenciasOrganizadas pref;

    public ToggleHorizontal mMostrarMarcadores;
    public ToggleHorizontal mMostrarCidades;
    public ToggleHorizontal mMostrarPontosCidades;
    public ToggleHorizontal mMostrarMares;

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


    }


    public void salvarConf() {


        pref.setLogico("Marcadores", "Mostrar", mMostrarMarcadores.getValor());
        pref.setLogico("CidadesNomes", "Mostrar", mMostrarCidades.getValor());
        pref.setLogico("CidadesPontos", "Mostrar", mMostrarPontosCidades.getValor());
        pref.setLogico("Mares", "Mostrar", mMostrarMares.getValor());

        pref.salvar();

    }

    public void panielConfiguracoes(Renderizador mRender) {

        pequeno.setRenderizador(mRender);

        mMostrarMarcadores.onRender(mRender, pequeno);
        mMostrarCidades.onRender(mRender, pequeno);
        mMostrarPontosCidades.onRender(mRender, pequeno);
        mMostrarMares.onRender(mRender, pequeno);

    }


    public void preferencias_construir() {


        mMostrarMarcadores = new ToggleHorizontal(2200, 150, "MOSTRAR MARCADORES", true);

        mMostrarMarcadores.setAcao(new Acao() {
            @Override
            public void onClique() {
                salvarConf();
            }
        });


        mMostrarCidades = new ToggleHorizontal(2200, 170, "MOSTRAR CIDADES", true);

        mMostrarCidades.setAcao(new Acao() {
            @Override
            public void onClique() {
                salvarConf();
            }
        });


        mMostrarPontosCidades = new ToggleHorizontal(2200, 190, "MOSTRAR PONTOS DE CIDADES", true);

        mMostrarPontosCidades.setAcao(new Acao() {
            @Override
            public void onClique() {
                salvarConf();
            }
        });


        mMostrarMares = new ToggleHorizontal(2200, 210, "MOSTRAR MARES", true);

        mMostrarMares.setAcao(new Acao() {
            @Override
            public void onClique() {
                salvarConf();
            }
        });
    }

}

package apps.app_bancarium;

import azzal.Cenarios.Cena;
import azzal.Utils.*;
import azzal.Renderizador;
import azzal.Windows;
import libs.Extenum.Chronos;
import libs.Extenum.Colecionador.Colecao;
import libs.Extenum.Organizador;


public class CenaBanco extends Cena {


    public CenaBanco() {

        Organizador mArquivador = new Organizador("res/e1.extenum");

        // mArquivador.dump_cabecalho();
        //mArquivador.mostrarBlocos();

        // mArquivador.dump_paginas();
        //  mArquivador.mostrarPaginas();


        Colecao eFrases = mArquivador.getColecao("Frases");
        Colecao eDatas = mArquivador.getColecao("Datas");
        Colecao eCifras = mArquivador.getColecao("Cifras");

        // eCifras.guarde("Oi");

        Chronos eChronos = new Chronos();

        eDatas.guarde(eChronos.getData() + " " + eChronos.getTempo());
        // eDatas.reIndex();

        // eFrases.guarde("O que e isso ?");
        // eFrases.guarde("Quem e voce ?");
        // eFrases.guarde("DDN 27/07/1992");


        // eNomes.guarde("libs.Luan");
        // eNomes.guarde("Olsa");
        // eNomes.guarde("Carmim");

        //eIndexador.guarde("Shoquiste-ne vos...");



        for (int a = 0; a < 1000; a++) {
            eDatas.guarde(eChronos.getData() + " " + eChronos.getTempo());
        }

        eFrases.atualizar(2, "Geral funcionando !");

        // eIndexador.lerCabecalho();
        //   eIndexador.lerIndices();

        //eIndexador.mostrarPaginas();

        //mArquivador.dump_paginas();

        // eIndexador.dump();

        mArquivador.mostrarBlocosColecao();

        for (Colecao eColecao : mArquivador.getColecoes()) {

            System.out.println("");
            System.out.println("Colecao :: " + eColecao.getColecaoID() + " -->> " + eColecao.getNome());

            eColecao.mostrarSumario();
            eColecao.mostrarConteudo();
            //  eColecao.removerTudo();
        }

        mArquivador.dump_paginas();

        mArquivador.dump_refs();

        //mArquivador.dump_blocos();
        mArquivador.dump_colecoes();

        mArquivador.fechar();

    }


    @Override
    public void iniciar(Windows eWindows) {
        eWindows.setTitle("Cena Banco");
    }


    @Override
    public void update(double dt) {


    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(new Cor(255, 255, 255));


    }


}

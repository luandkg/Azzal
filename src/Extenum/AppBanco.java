package Extenum;

import Extenum.Colecionador.Colecao;

public class AppBanco {

    public void init() {

        System.out.println("");
        System.out.println("");

        System.out.println(" -->> Externum  =  res/e1.extenum");
        System.out.println("");

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


        // eNomes.guarde("Luan");
        // eNomes.guarde("Olsa");
        // eNomes.guarde("Carmim");

        //eIndexador.guarde("Shoquiste-ne vos...");

        for (int a = 0; a < 500; a++) {
            //   eCifras.guarde("Luan Alves Freitas :: " + a);
        }

        for (int a = 0; a < 600; a++) {
            eDatas.guarde(eChronos.getData() + " " + eChronos.getTempo());
        }

        eFrases.atualizar(2, "Geral funcionando !");

        // eIndexador.lerCabecalho();
        //   eIndexador.lerIndices();

        //eIndexador.mostrarPaginas();

        //mArquivador.dump_paginas();

        // eIndexador.dump();


        for (Colecao eColecao : mArquivador.getColecoes()) {

            System.out.println("");
            System.out.println("Colecao :: " + eColecao.getColecaoID() + " -->> " + eColecao.getNome());
            eColecao.mostrarConteudo();
            //  eColecao.removerTudo();
        }

        mArquivador.dump_paginas();

        mArquivador.dump_refs();

        //mArquivador.dump_blocos();
        mArquivador.dump_colecoes();

        mArquivador.fechar();

    }
}

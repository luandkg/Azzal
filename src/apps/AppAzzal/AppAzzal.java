package apps.AppAzzal;

import apps.AppAttuz.WorldBuilding;
import libs.GamaFS.GamaFS;


public class AppAzzal {


    public static void main(String[] args) {

        //    AzzalUnico.unico("apps.AppAzzal", 1600, 1020, new AppGlobal());

        //  AzzalUnico.unico("apps.AppFuzz", 1600, 1020, new apps.AppFuzz.apps.AppFuzz());


        // AzzalUnico.unico("libs.Tronarko", 1550, 1100, new apps.AppTronarko.apps.AppTronarko());
        //AzzalUnico .unico("libs.Tronarko", 900, 800, new apps.AppTronarko.AppAlarme());
        // AzzalUnico.unico("Linha Do Tempo", 900, 1000, new apps.AppTronarko.AppLinhaDoTempo());

        //AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new Alpha());

        // AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new C1());
        // AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new CenaBanco());
        // AzzalUnico.unico("Quadrante Espacial", 1500, 1010, new QuadranteEspacial());

        //   AzzalUnico.unico("apps.AppAzzal", 1100, 800, new Fisica.Fisica());

        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new Letras());

        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new C1());
        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new Chiado());
        //AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new CidadeCena());


        //AzzalUnico.unico("Arch", 1300, 1000, new Arch.Arch());


        //Assembler mAssembler = new Assembler();
        //mAssembler.compilar("res/montagem.l1", "res/montagem.l0", "res/montagem.o");
        //AzzalUnico.unico("", 1100, 900, new TerrariaCena());
        //AzzalUnico.unico("", 1100, 900, new FonteGeradorCena());

        // AzzalUnico.unico("", 1100, 900, new CenaLetrador());


     //   AzzalUnico.unico("Attuz", 2500, 1000, new apps.AppAttuz.apps.AppAttuz());
//
    //    TG22.TG22.init();


        //  AzzalUnico.unico("Editor - luan.dkg", 1000, 1000, new apps.AppKhronos.AppKrhonos());

        //  String eArquivoBzz = "/home/luan/Documentos/viagem.bzz";
        //   BZZ.alocar(eArquivoBzz, 2000);

        // ViagemIndexar.indexar(eArquivoBzz, "/home/luan/Documentos/t7002.txt");
        // ViagemIndexar.indexar(eArquivo, "/home/luan/Documentos/t7003.txt");
        // ViagemIndexar.indexar(eArquivo, "/home/luan/Documentos/t7004.txt");

        // BZZ.procurar(eArquivo, 0);

        boolean isGama = false;
        if (isGama) {
            String eArquivo = "/home/luan/Documentos/fs/gama.fs";

            // libs.GamaFS.criar(eArquivo, 10 * 1024 * 1024);
            // libs.GamaFS.zerar(eArquivo);
            GamaFS.formatar(eArquivo);

            GamaFS eGama = new GamaFS(eArquivo);

            eGama.encerrar();
        }

        //   libs.Servittor.onServico("Arquivador", new apps.AppArquivos.Servicos());

        // AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new apps.AppArquivos.AppImagem());

        //  AzzalUnico.unico("AppAnimacao", 1100, 900, new AppAnimacao());


        //VideoSequenciador.criar("/home/luan/Vídeos/vi/ecossistema_01.vi",800,801,"/home/luan/Imagens/ecossistema_01/S", 0, 97, ".png");
        // VideoSequenciador.criar("/home/luan/Vídeos/vi/ecossistema_02.vi", 800, 801, "/home/luan/Imagens/ecossistema_02/S", 0, 365, ".png");
        //  VideoSequenciador.criar("/home/luan/Vídeos/vi/alunos_v2.vi",3000,2700,"/home/luan/Imagens/alunos_vi/S", 0, 99, ".png");

        // VideoCodecador.abrir("/home/luan/Vídeos/vi/ecossistema_01.vi");

        //   AzzalUnico.unico("AppVideo", 2000, 1100, new AppVideo());

        //  GuiaDeViagem.unir("/home/luan/Documentos/viagem_desorganizada.txt");
        //    GuiaDeViagem.organizar("/home/luan/Documentos/viagem_desorganizada.txt","/home/luan/Documentos/viagem_organizada.txt");
        //GuiaDeViagem.passei("/home/luan/Documentos/viagem_organizada.txt",libs.Tronarko.libs.Tronarko.getTozteDireto(), libs.Tronarko.libs.Tronarko.getHazdeDireto());

        //  BZZ.alocar("/home/luan/Documentos/viagem.bzz", 2000);
        //ViagemIndexar.indexar("/home/luan/Documentos/viagem_organizada.txt", "/home/luan/Documentos/viagem.bzz");

        //  String conteudo = BZZ.procurar("/home/luan/Documentos/viagem.bzz", 140);
        //  System.out.println(conteudo);
        // System.out.println("Tamanho :: " + conteudo.length());


        //  ViagemIndexar.passeiBZZ("/home/luan/Documentos/viagem.bzz", libs.Tronarko.libs.Tronarko.getTozte(), libs.Tronarko.libs.Tronarko.getHazde(), libs.Tronarko.libs.Tronarko.getTozte());

        //System.out.println("HOJE :: " + libs.Tronarko.libs.Tronarko.getTozteDireto().getTexto());

        //ViagemIndexar.passeiBZZ("/home/luan/Documentos/viagem.bzz", new libs.Tronarko.Tozte(1,4,7002), new libs.Tronarko.Hazde(7,0,0),libs.Tronarko.libs.Tronarko.getTozteDireto());

        // System.out.println("BZZ -->> Max " + BZZ.getQuantidadeMaxima("/home/luan/Documentos/viagem.bzz"));
        // System.out.println("BZZ -->> " + BZZ.comValores("/home/luan/Documentos/viagem.bzz"));

        // ViagemIndexar.obterIndexado("/home/luan/Documentos/viagem.bzz","36/05/7002");
        //  String vt =  ViagemIndexar.procurando("/home/luan/Documentos/viagem.bzz", new Viajante(),new libs.Tronarko.Tozte(36,6,7002),new libs.Tronarko.Hazde(4,0,0));

        //  System.out.println(vt);

        // System.out.println("Com Valores :: " + BZZ.comValores("/home/luan/Documentos/viagem.bzz"));
        //   System.out.println("Sem Valores :: " + BZZ.semValores("/home/luan/Documentos/viagem.bzz"));

        String LOCAL = "/home/luan/Imagens/Simples/";
        LOCAL = "/home/luan/Imagens/Arkazz/";

        boolean criar = false;
        boolean renderizar = false;


        if (criar) {
            WorldBuilding.criar(LOCAL);
        }

        if (renderizar) {
            WorldBuilding.renderQTT(LOCAL);
        }

        //WorldBuilding.biomas(LOCAL);


        //RenderQTT.render(eLocal + "dados/relevo.qtt",eLocal + "dados/relevo.png");

        //  libs.Documentar planejamento = new libs.Documentar();
        // planejamento.organizar("/home/luan/Dropbox/CED_01/Planejamento/planejamento.txt", "/home/luan/Dropbox/CED_01/Planejamento/PLANAJEMENTO - PROF. LUAN FREITAS e PROF. WALISON FRANCISCO.pdf");
        // planejamento.organizar("/home/luan/Dropbox/CED_01/Planejamento/pd3_8.txt", "/home/luan/Dropbox/CED_01/Planejamento/PLANEJAMENTO - PROFº ELVES_PROFª. IARA_PROFº LUAN -PLANEJAMENTO DE CURSO 2022.pdf");

        //ViagemCompleta.motrarCidades();
        //ViagemCompleta.remontar_GuiaDeViagem();

        //libs.RhoBenchmark.libs.RhoBenchmark.organizar("res/libs.RhoBenchmark.dkg","/home/luan/Imagens/libs.RhoBenchmark.png");

        VamosCalcular vc = new VamosCalcular();
        // vc.init();


        // libs.Tronarko.Testes.init();

    }


}

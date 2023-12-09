package apps.app;

import apps.app_arquivos.*;
import apps.app_attuz.AppAttuz;
import apps.app_azzal.AppGlobal;
import apps.app_azzal.VamosCalcular;
import apps.app_citatte.AppCitatte;
import apps.app_citatte.AppCitatteModelum;
import apps.app_citatte.CidadeGeradorAleatorio;
import apps.app_coisas.AppClassificador;
import apps.app_llcripto.App_LLCripto;
import apps.app_momentum.HarrempluzCreator;
import apps.app_testes.app_testes;
import libs.arquivos.BZ3;
import libs.arquivos.IM;
import libs.arquivos.Sumario;
import libs.arquivos.stacker.StackItem;
import libs.arquivos.stacker.Stacker;
import libs.azzal.AzzalUnico;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.dkg.DKGUtils;
import libs.documentar.Documentar;
import libs.imagem.Imagem;
import libs.luan.RefInt;
import libs.luan.Strings;
import libs.movimentador.QuadranteEspacial;
import libs.ranking.RankeadoInteiro;
import libs.ranking.Rankeador;
import libs.servittor.Servittor;
import libs.verkuz.VerkuzImplementador;
import libs.xlsx.XLSX;
import libs.xml.XML;
import apps.app_arch.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AppAzzal {

    public static void main(String[] args) {

        //AzzalUnico.unico("apps.AppAzzal", 1600, 1020, new AppGlobal());

        //AzzalUnico.unico("apps.AppFuzz", 1600, 1020, new apps.app_fuzz.AppFuzz());

      //   AzzalUnico.unico("Tronarko", 1550, 1100, new apps.app_tronarko.AppTronarko());

        // AzzalUnico .unico("Tronarko.Alarme", 900, 800, new apps.app_tronarko.AppAlarme());
          AzzalUnico.unico("Astros", 1550, 1100, new apps.app_tronarko.AppAstros());

       //   AzzalUnico.unico("Attuz", 3000, 1000, new AppAttuz());
        //  AzzalUnico.unico("Citatte", 2000, 1000, new AppCitatte());

        //  AzzalUnico.unico("Citatte Modelum", 2000, 1000, new AppCitatteModelum());


        //CidadeGeradorAleatorio.init_cidades();
        // CidadeGeradorAleatorio.init_cidade();
        //  CidadeGeradorAleatorio.render_cidade_entre_vias("1");
        //  CidadeGeradorAleatorio.render_cidade_entre_vias("2");
        //  CidadeGeradorAleatorio.render_cidade_entre_vias("3");

        //  CidadeGeradorAleatorio.render_cidade_entre_vias("melhor_de_3");
        // CidadeGeradorAleatorio.render_cidade_entre_vias("melhor_de_100");

     //   app_testes.init();


        //     CidadeGeradorAleatorio.render_cidade();
        // CidadeGeradorAleatorio.render_gama();


        //AppAttuzServittos.init();

        // AzzalUnico.unico("Linha Do Tempo", 900, 1000, new AppLinhaDoTempo());

        // AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new Alpha());
        // AzzalUnico.unico("apps.AppAudio", 700, 1020, new AppAudio());

        // AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new C1());
        // AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new CenaBanco());
        //   AzzalUnico.unico("Quadrante Espacial", 1500, 1010, new QuadranteEspacial());

        // AzzalUnico.unico("apps.AppAzzal", 1100, 800, new Fisica.Fisica());

        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new Letras());

        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new C1());
        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new Chiado());
        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new CidadeCena());

        // AzzalUnico.unico("Arch", 1300, 1000,new AppArch());

        // Assembler mAssembler = new Assembler();
        // mAssembler.compilar("res/montagem.l1", "res/montagem.l0", "res/montagem.o");

        // AzzalUnico.unico("", 1100, 900, new TerrariaCena());

        // AzzalUnico.unico("", 1100, 900, new FonteGeradorCena());

        // AzzalUnico.unico("", 1100, 900, new CenaLetrador());

        //TG22.init();

        // AzzalUnico.unico("Editor - luan.dkg", 1000, 1000, new AppKrhonos());

        // AppGamaFS.init();

       // Servittor.onServico("Arquivador", new ArquivosServicos());

        // AzzalUnico.unico("Visualizador IM", 1500, 1020, new AppImagem());
       //  AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new AppAlbumDeImagens());

        //AzzalUnico.unico("AppAnimacao", 1100, 900, new AppAnimacao());

        // VideoSequenciador.criar("/home/luan/Vídeos/vi/ecossistema_01.vi", 800, 801, "/home/luan/Imagens/ecossistema_01/S", 0, 97, ".png");
        // VideoSequenciador.criar("/home/luan/Vídeos/vi/ecossistema_02.vi", 800, 801,
        // "/home/luan/Imagens/ecossistema_02/S", 0, 365, ".png");
        // VideoSequenciador.criar("/home/luan/Vídeos/vi/alunos_v2.vi",3000,2700,"/home/luan/Imagens/alunos_vi/S",
        // 0, 99, ".png");

        // VideoCodecador.abrir("/home/luan/Vídeos/vi/ecossistema_01.vi");

        //  AzzalUnico.unico("AppVideo", 2000, 1100, new AppVideo());


        String ESCOLA_LOCAL = "/home/luan/Dropbox/CED 1";


        Documentar.organizar(ESCOLA_LOCAL + "/Planejamento/planejamento_01.txt", ESCOLA_LOCAL + "/Planejamento/PLANEJAMENTO - 1 SEMESTRE - PROFESSOR LUAN FREITAS.pdf");
        Documentar.organizar(ESCOLA_LOCAL + "/Planejamento/planejamento_02.txt", ESCOLA_LOCAL + "/Planejamento/PLANEJAMENTO - 2 SEMESTRE - PROFESSOR LUAN FREITAS.pdf");


        // planejamento.organizar("/home/luan/Dropbox/CED_01/Planejamento/pd3_8.txt",
        // "/home/luan/Dropbox/CED_01/Planejamento/PLANEJAMENTO - PROFº ELVES_PROFª.
        // IARA_PROFº LUAN -PLANEJAMENTO DE CURSO 2022.pdf");

        // libs.RhoBenchmark.libs.RhoBenchmark.organizar("res/libs.RhoBenchmark.dkg","/home/luan/Imagens/libs.RhoBenchmark.png");

        VamosCalcular vc = new VamosCalcular();
        // vc.init();

        // libs.Tronarko.Testes.init();

        //    AzzalUnico.unico("App_LLCripto", 1100, 900, new App_LLCripto());


        VerkuzImplementador vi = new VerkuzImplementador();

        vi.init("/home/luan/IdeaProjects/Azzal/src/libs");
        vi.init("/home/luan/IdeaProjects/Azzal/src/libs/azzal");
        vi.init("/home/luan/IdeaProjects/Azzal/src/libs/mockui");

        // vi.init_bibliotecas("/home/luan/IdeaProjects/Azzal/src/libs");
        // vi.exibir();

        //   vi.toClasse("/home/luan/IdeaProjects/Azzal/src/azzal_version/AzzalVersion.java","azzal_version","AzzalVersion");

        //  AzzalUnico.unico("Corretor", 1100, 900, new AppCorretor());

        //AppMomentum.init();
        //HarrempluzCreator.criar();
        //  HarrempluzCreator.visualizar();

        //GGDNA.init();


        // AppClassificador.init();


        XLSX vendas = new XLSX("/home/luan/assets/vendas.xlsx");
        // vendas.exibir();


        // AppFit.init();

        // GGADF2023.init();


    }

}

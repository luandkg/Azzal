package apps.app;

import apps.app_arquivos.AppImagem;
import apps.app_attuz.AppAttuz;
import apps.app_attuz.AppAttuzServittos;
import apps.app_attuz.WorldBuilding;
import apps.app_audio.AppAudio;
import apps.app_azzal.Alpha;
import apps.app_azzal.AppGlobal;
import apps.app_azzal.VamosCalcular;
import apps.app_gamafs.AppGamaFS;
import apps.app_khronos.AppKrhonos;
import apps.app_tronarko.AppLinhaDoTempo;
import azzal.AzzalUnico;
import libs.GamaFS.GamaFS;
import libs.Movimento.QuadranteEspacial;
import libs.OnTerraria.TerrariaCena;


public class AppAzzal {


    public static void main(String[] args) {

        //AzzalUnico.unico("apps.AppAzzal", 1600, 1020, new AppGlobal());

          AzzalUnico.unico("apps.AppFuzz", 1600, 1020, new apps.AppFuzz.AppFuzz());


         // AzzalUnico.unico("Tronarko", 1550, 1100, new apps.app_tronarko.AppTronarko());

        //   AzzalUnico .unico("Tronarko.Alarme", 900, 800, new apps.app_tronarko.AppAlarme());

        // AzzalUnico.unico("Linha Do Tempo", 900, 1000, new AppLinhaDoTempo());

     //    AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new Alpha());
       // AzzalUnico.unico("apps.AppAudio", 700, 1020, new AppAudio());

        // AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new C1());
        // AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new CenaBanco());
        //  AzzalUnico.unico("Quadrante Espacial", 1500, 1010, new QuadranteEspacial());

        //   AzzalUnico.unico("apps.AppAzzal", 1100, 800, new Fisica.Fisica());

        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new Letras());

        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new C1());
        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new Chiado());
        //AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new CidadeCena());


        //AzzalUnico.unico("Arch", 1300, 1000, new Arch.Arch());


        //Assembler mAssembler = new Assembler();
        //mAssembler.compilar("res/montagem.l1", "res/montagem.l0", "res/montagem.o");

        //  AzzalUnico.unico("", 1100, 900, new TerrariaCena());

        //AzzalUnico.unico("", 1100, 900, new FonteGeradorCena());

        // AzzalUnico.unico("", 1100, 900, new CenaLetrador());


        //    TG22.TG22.init();


        //  AzzalUnico.unico("Editor - luan.dkg", 1000, 1000, new AppKrhonos());


        //  AppGamaFS.init();


        //   libs.Servittor.onServico("Arquivador", new apps.AppArquivos.Servicos());

        // AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new AppImagem());

        //  AzzalUnico.unico("AppAnimacao", 1100, 900, new AppAnimacao());


        //VideoSequenciador.criar("/home/luan/Vídeos/vi/ecossistema_01.vi",800,801,"/home/luan/Imagens/ecossistema_01/S", 0, 97, ".png");
        // VideoSequenciador.criar("/home/luan/Vídeos/vi/ecossistema_02.vi", 800, 801, "/home/luan/Imagens/ecossistema_02/S", 0, 365, ".png");
        //  VideoSequenciador.criar("/home/luan/Vídeos/vi/alunos_v2.vi",3000,2700,"/home/luan/Imagens/alunos_vi/S", 0, 99, ".png");

        // VideoCodecador.abrir("/home/luan/Vídeos/vi/ecossistema_01.vi");

        //   AzzalUnico.unico("AppVideo", 2000, 1100, new AppVideo());


        // AppAttuzServittos.init();

        //   AzzalUnico.unico("Attuz", 2500, 1000, new AppAttuz());


        //  libs.Documentar planejamento = new libs.Documentar();
        // planejamento.organizar("/home/luan/Dropbox/CED_01/Planejamento/planejamento.txt", "/home/luan/Dropbox/CED_01/Planejamento/PLANAJEMENTO - PROF. LUAN FREITAS e PROF. WALISON FRANCISCO.pdf");
        // planejamento.organizar("/home/luan/Dropbox/CED_01/Planejamento/pd3_8.txt", "/home/luan/Dropbox/CED_01/Planejamento/PLANEJAMENTO - PROFº ELVES_PROFª. IARA_PROFº LUAN -PLANEJAMENTO DE CURSO 2022.pdf");


        //libs.RhoBenchmark.libs.RhoBenchmark.organizar("res/libs.RhoBenchmark.dkg","/home/luan/Imagens/libs.RhoBenchmark.png");

        VamosCalcular vc = new VamosCalcular();
        //vc.init();


        // libs.Tronarko.Testes.init();

        //AzzalUnico.unico("App_LLCripto", 1100, 900, new App_LLCripto());


    }


}

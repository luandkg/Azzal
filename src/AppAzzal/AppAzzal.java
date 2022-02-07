package AppAzzal;

import AppAttuz.Servicos.*;

import Servittor.Servittor;
import GamaFS.GamaFS;


public class AppAzzal {


    public static void main(String[] args) {

        //    AzzalUnico.unico("AppAzzal", 1600, 1020, new AppGlobal());

        //  AzzalUnico.unico("AppFuzz", 1600, 1020, new AppFuzz.AppFuzz());


        //  AzzalUnico.unico("Tronarko", 1550, 1100, new AppTronarko());

        //AzzalUnico.unico("AppAzzal", 1500, 1020, new Alpha());

        // AzzalUnico.unico("AppAzzal", 1500, 1020, new C1());
        // AzzalUnico.unico("AppAzzal", 1500, 1020, new CenaBanco());
        // AzzalUnico.unico("Quadrante Espacial", 1500, 1010, new QuadranteEspacial());

        //   AzzalUnico.unico("AppAzzal", 1100, 800, new Fisica.Fisica());

        // AzzalUnico.unico("AppAzzal", 1300, 1000, new Letras());

        // AzzalUnico.unico("AppAzzal", 1300, 1000, new C1());
        // AzzalUnico.unico("AppAzzal", 1300, 1000, new Chiado());
        //AzzalUnico.unico("AppAzzal", 1300, 1000, new CidadeCena());


        //AzzalUnico.unico("Arch", 1300, 1000, new Arch.Arch());


        //Assembler mAssembler = new Assembler();
        //mAssembler.compilar("res/montagem.l1", "res/montagem.l0", "res/montagem.o");
        //AzzalUnico.unico("", 1100, 900, new TerrariaCena());
        //AzzalUnico.unico("", 1100, 900, new FonteGeradorCena());

        // AzzalUnico.unico("", 1100, 900, new CenaLetrador());

        String eLocal = "/home/luan/Imagens/Simples/";

        eLocal = "/home/luan/Imagens/Arkazz/";

        boolean fazer = true;

        //  Servittor.onServico("Conveccionador", new Conveccionador(eLocal));

        if (fazer) {

            //Servittor.onServico("Expansor", new Expansor(eLocal));

            //  Servittor.onServico("Relevo", new Relevo(eLocal));
            // Servittor.onServico("Conveccionador", new Conveccionador(eLocal));
            // Servittor.onServico("LinhaDeRelevo", new LinhaDeRelevo(eLocal));
            // Servittor.onServico("Cartografia", new Cartografia(eLocal));
            // Servittor.onServico("Proximidade Do Mar", new ProximidadeDoMar(eLocal));
            //   Servittor.onServico("Umidade", new Umidade(eLocal));
             Servittor.onServico("Temperatura", new Temperatura(eLocal));


        }

        //    AzzalUnico.unico("Attuz", 2300, 1000, new AppAttuz.AppAttuz());
        // AzzalUnico.unico("Attuz", 2500, 1000, new AppAttuz.AppAttuz());

        // TG22.TG22.init();


        //  AzzalUnico.unico("Editor - luan.dkg", 1000, 1000, new AppKhronos.AppKrhonos());

        //String eArquivo = "/home/luan/Documentos/viagem.bzz";

        // BZZ.alocar(eArquivo, 2000);

        // ViagemIndexar.indexar(eArquivo, "/home/luan/Documentos/t7002.txt");
        // ViagemIndexar.indexar(eArquivo, "/home/luan/Documentos/t7003.txt");
        // ViagemIndexar.indexar(eArquivo, "/home/luan/Documentos/t7004.txt");

        // BZZ.procurar(eArquivo, 0);

        boolean isGama = false;
        if (isGama) {
            String eArquivo = "/home/luan/Documentos/fs/gama.fs";

            // GamaFS.criar(eArquivo, 10 * 1024 * 1024);
            // GamaFS.zerar(eArquivo);
            GamaFS.formatar(eArquivo);

            GamaFS eGama = new GamaFS(eArquivo);

            eGama.encerrar();
        }

        //   Servittor.onServico("Arquivador", new AppArquivos.Servicos());

        // AzzalUnico.unico("AppAzzal", 1500, 1020, new AppArquivos.AppImagem());

        //  AzzalUnico.unico("AppAnimacao", 1100, 900, new AppAnimacao());


        //VideoSequenciador.criar("/home/luan/Vídeos/vi/ecossistema_01.vi",800,801,"/home/luan/Imagens/ecossistema_01/S", 0, 97, ".png");
        // VideoSequenciador.criar("/home/luan/Vídeos/vi/ecossistema_02.vi", 800, 801, "/home/luan/Imagens/ecossistema_02/S", 0, 365, ".png");
        //  VideoSequenciador.criar("/home/luan/Vídeos/vi/alunos_v2.vi",3000,2700,"/home/luan/Imagens/alunos_vi/S", 0, 99, ".png");

        // VideoCodecador.abrir("/home/luan/Vídeos/vi/ecossistema_01.vi");

       //   AzzalUnico.unico("AppVideo", 2000, 1100, new AppVideo());

        //GuiaDeViagem.organizar();
        //GuiaDeViagem.passei(Tronarko.Tronarko.getTozteDireto(),Tronarko.Tronarko.getHazdeDireto());

    }


}

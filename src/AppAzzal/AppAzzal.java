package AppAzzal;

import AppAttuz.Servicos.*;
import Azzal.AzzalUnico;
import Servittor.Servittor;
import GamaFS.GamaFS;
import AppTronarko.AppTronarko;

public class AppAzzal {


    public static void main(String[] args) {

        AzzalUnico.unico("Tronarko", 1550, 1100, new AppTronarko());

        //  AzzalUnico.unico("AppAzzal", 1500, 1020, new C1());
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

        boolean fazer = false;


        if (fazer) {

            Servittor.onServico("Expansor", new Expansor(eLocal));

            Servittor.onServico("Relevo", new Relevo(eLocal));
            // Servittor.onServico("LinhaDeRelevo", new LinhaDeRelevo(eLocal));
            // Servittor.onServico("Cartografia", new Cartografia(eLocal));
            // Servittor.onServico("Proximidade Do Mar", new ProximidadeDoMar(eLocal));
            // Servittor.onServico("Umidade", new Umidade(eLocal));
            // Servittor.onServico("Temperatura", new Temperatura(eLocal));

        }

        // AzzalUnico.unico("Attuz", 2300, 1000, new AppAttuz.AppAttuz());

        //TG22.TG22.init();

        //Servittor.onServico("Arquivador", new Arquivos.Servicos());
        // AzzalUnico.unico("AppImagem", 1100, 900, new AppImagem());

        //  AzzalUnico.unico("Editor - luan.dkg", 1000, 1000, new AppKhronos.AppKrhonos());

        //String eArquivo = "/home/luan/Documentos/viagem.bzz";

        // BZZ.alocar(eArquivo, 2000);

        // ViagemIndexar.indexar(eArquivo, "/home/luan/Documentos/t7002.txt");
        // ViagemIndexar.indexar(eArquivo, "/home/luan/Documentos/t7003.txt");
        // ViagemIndexar.indexar(eArquivo, "/home/luan/Documentos/t7004.txt");

        // BZZ.procurar(eArquivo, 0);

        String eArquivo = "/home/luan/Documentos/fs/gama.fs";

        // GamaFS.criar(eArquivo, 10 * 1024 * 1024);
        // GamaFS.zerar(eArquivo);
        GamaFS.formatar(eArquivo);

        GamaFS eGama = new GamaFS(eArquivo);

        eGama.encerrar();


    }


}

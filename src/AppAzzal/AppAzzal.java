package AppAzzal;

import AppAttuz.Servicos.Cartografia;
import AppAttuz.Servicos.LinhaDeRelevo;
import AppAttuz.Servicos.Relevo;
import Azzal.AzzalUnico;
import Arquivos.AppImagem;
import Servittor.Servittor;

public class AppAzzal {


    public static void main(String[] args) {

        //  AzzalUnico.unico("AppAzzal", 1500, 1020, new C1());
        // AzzalUnico.unico("AppAzzal", 1500, 1020, new CenaBanco());
        //AzzalUnico.unico("Quadrante Espacial", 1500, 1010, new QuadranteEspacial());

        //  AzzalUnico.unico("AppAzzal", 1100, 800, new Fisica.Fisica());

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

       // AzzalUnico.unico("Attuz", 2300, 1000, new AppAttuz.AppAttuz());

        //Servittor.onServico("Relevo", new Relevo("/home/luan/Imagens/Arkazz/"));
        Servittor.onServico("LinhaDeRelevo", new LinhaDeRelevo("/home/luan/Imagens/Arkazz/"));

        //Servittor.onServico("Cartografia", new Cartografia("/home/luan/Imagens/Arkazz/"));

        // Servittor.onServico("Temperatura", new Temperatura("/home/luan/Imagens/Arkazz/"));

        // Servittor.onServico("Umidade", new Umidade("/home/luan/Imagens/Arkazz/"));
        //  Servittor.onServico("Temperatura", new Temperatura("/home/luan/Imagens/Arkazz/"));

        //TG22.TG22.init();

        //Servittor.onServico("Arquivador", new Arquivos.Servicos());
       // AzzalUnico.unico("AppImagem", 1100, 900, new AppImagem());


    }


}

package AppAttuz;

import AppAttuz.Assessorios.ReveladorQTT;
import AppAttuz.Camadas.EscalasPadroes;
import AppAttuz.Servicos.*;
import Servittor.Servittor;

public class WorldBuilding {

    public static void criar(String LOCAL) {


        //Servittor.onServico("Expansor", new Expansor(eLocal));

        Servittor.onServico("Cartografia", new Cartografia(LOCAL));


        Servittor.onServico("Relevo", new Relevo(LOCAL));
        Servittor.onServico("LinhaDeRelevo", new LinhaDeRelevo(LOCAL));


        Servittor.onServico("Proximidade Da Agua", new ProximidadeDaAgua(LOCAL));
        Servittor.onServico("Proximidade Do Mar", new ProximidadeDoMar(LOCAL));


        Servittor.onServico("Umidade", new Umidade(LOCAL));
        Servittor.onServico("Temperatura", new Temperatura(LOCAL));
        Servittor.onServico("Preciptacao", new Preciptacao(LOCAL));
        Servittor.onServico("Divisao Politica", new DivisaoPolitica(LOCAL));


    }

    public static void renderQTT(String LOCAL) {

        System.out.println("QTT -> Relevo");
        ReveladorQTT.relevo(LOCAL, LOCAL + "dados/relevo.qtt", LOCAL + "dados/relevo.png");

        System.out.println("QTT -> Umidade");
        ReveladorQTT.umidade(LOCAL, LOCAL + "dados/umidade.qtt", LOCAL + "dados/umidade.png");

        System.out.println("QTT -> Mar");
        ReveladorQTT.mar_distancia(LOCAL, LOCAL + "dados/mar_distancia.qtt", LOCAL + "dados/mar_distancia.png");

        System.out.println("QTT -> Agua");
        ReveladorQTT.mar_distancia(LOCAL, LOCAL + "dados/agua_distancia.qtt", LOCAL + "dados/agua_distancia.png");


        System.out.println("QTT -> Temperatura");
        ReveladorQTT.temperatura(LOCAL, LOCAL + "dados/temperatura_vi.qtt", LOCAL + "dados/temperatura_vi.png");
        ReveladorQTT.temperatura(LOCAL, LOCAL + "dados/temperatura_iv.qtt", LOCAL + "dados/temperatura_iv.png");

        System.out.println("QTT -> Preciptacao");
        ReveladorQTT.renderSoTerra(LOCAL, LOCAL + "dados/preciptacao.qtt", EscalasPadroes.getEscalaPreciptacao(), LOCAL + "dados/preciptacao.png");

    }

    public static void biomas(String LOCAL) {


        Servittor.onServico("Biomas", new Biomas(LOCAL));


    }

}

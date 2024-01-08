package apps.app_attuz;

import apps.app_attuz.Assessorios.QTTOnImagem;
import apps.app_attuz.Assessorios.EscalasPadroes;
import apps.app_attuz.Servicos.*;
import libs.servittor.Servittor;

public class WorldBuilding {

    public static void criar(String LOCAL) {


        Servittor.onServico("TerraFormar", new TerraFormar(LOCAL));

        Servittor.onServico("Cartografia", new Cartografia(LOCAL));


        Servittor.onServico("Relevo", new Relevo(LOCAL));
        Servittor.onServico("LinhaDeRelevo", new LinhaDeRelevo(LOCAL));


        Servittor.onServico("Proximidade Da Agua", new ProximidadeDaAgua(LOCAL));
        Servittor.onServico("Proximidade Do Mar", new ProximidadeDoMar(LOCAL));


        Servittor.onServico("Umidade", new Umidade(LOCAL));
        Servittor.onServico("Temperatura", new Temperatura(LOCAL));
        Servittor.onServico("Preciptacao", new Preciptacao(LOCAL));


        Servittor.onServico("Divisao Politica", new DivisaoPolitica(LOCAL));
        Servittor.onServico("Territorios", new Territorios(LOCAL));


    }

    public static void renderQTT(String LOCAL) {

        System.out.println("QTT -> Relevo");
        QTTOnImagem.relevo(LOCAL, LOCAL + "dados/relevo.qtt", LOCAL + "renderQTT/relevo.png");

        System.out.println("QTT -> Umidade");
        QTTOnImagem.umidade(LOCAL, LOCAL + "dados/umidade.qtt", LOCAL + "renderQTT/umidade.png");

        System.out.println("QTT -> Mar");
        QTTOnImagem.mar_distancia(LOCAL, LOCAL + "dados/mar_distancia.qtt", LOCAL + "renderQTT/mar_distancia.png");

        System.out.println("QTT -> Agua");
        QTTOnImagem.mar_distancia(LOCAL, LOCAL + "dados/agua_distancia.qtt", LOCAL + "renderQTT/agua_distancia.png");

        System.out.println("QTT -> Temperatura");
        QTTOnImagem.temperatura(LOCAL, LOCAL + "dados/temperatura_vi.qtt", LOCAL + "renderQTT/temperatura_vi.png");
        QTTOnImagem.temperatura(LOCAL, LOCAL + "dados/temperatura_iv.qtt", LOCAL + "renderQTT/temperatura_iv.png");

        System.out.println("QTT -> Preciptacao");
        QTTOnImagem.renderSoTerra(LOCAL, LOCAL + "dados/preciptacao.qtt", EscalasPadroes.getEscalaPreciptacao(), LOCAL + "renderQTT/preciptacao.png");

    }

    public static void biomas(String LOCAL) {


        Servittor.onServico("Biomas", new Biomas(LOCAL));


    }

}

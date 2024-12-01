package apps.app_atzum.utils;

import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.luan.Lista;
import libs.luan.Par;

public class AtzumPlacasTectonicas {

    public static final Cor PLACA_ALFA = Cor.getHexCor("#FDD835");
    public static final Cor PLACA_BETA =Cor.getHexCor("#43A047");
    public static final Cor PLACA_GAMA =Cor.getHexCor("#D81B60");
    public static final Cor PLACA_DELTA =Cor.getHexCor("#1976D2");
    public static final Cor PLACA_EPSILON= Cor.getHexCor("#512DA8");
    public static final Cor PLACA_OMEGA = Cor.getHexCor("#9E9D24");
    public static final Cor PLACA_PI =Cor.getHexCor("#FF8F00");
    public static final Cor PLACA_ZETA = Cor.getHexCor("#D32F2F");


    public static Lista< Cor> GET_PLACAS_TECTONICAS_CORES() {
        Lista<Cor> cores = new Lista<Cor>();

        cores.adicionar(AtzumPlacasTectonicas.PLACA_ALFA);
        cores.adicionar(AtzumPlacasTectonicas.PLACA_BETA);
        cores.adicionar(AtzumPlacasTectonicas.PLACA_GAMA);
        cores.adicionar(AtzumPlacasTectonicas.PLACA_DELTA);
        cores.adicionar(AtzumPlacasTectonicas.PLACA_EPSILON);
        cores.adicionar(AtzumPlacasTectonicas.PLACA_OMEGA);
        cores.adicionar(AtzumPlacasTectonicas.PLACA_PI);
        cores.adicionar(AtzumPlacasTectonicas.PLACA_ZETA);

        return cores;
    }


    public static Lista<Par<Ponto, Cor>> GET_PLACAS_TECTONICAS() {


        Lista<Par<Ponto, Cor>> placas_tectonicas = new Lista<Par<Ponto, Cor>>();

        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(500, 500),PLACA_ALFA ));
        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(800, 500),PLACA_BETA ));
        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(500, 1200), PLACA_GAMA));

        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(100, 700), PLACA_DELTA));
        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(2200, 700), PLACA_DELTA));

        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(500, 1500),PLACA_EPSILON));
        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(2000, 900),PLACA_OMEGA));
        //   placas_tectonicas.adicionar(new Par<Ponto,Cor>(new Ponto(500,50),mCores.getMarrom()));

        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(2080, 600), PLACA_PI));
        placas_tectonicas.adicionar(new Par<Ponto, Cor>(new Ponto(1900, 250),PLACA_ZETA));

        return placas_tectonicas;
    }
}

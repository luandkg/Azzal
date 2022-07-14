package apps.app_attuz.Politicamente;

import apps.app_attuz.Ferramentas.Espaco2D;
import libs.Luan.fmt;

public class EquipamentosDeEngenharia {


    public static int distancia(int p1x, int p1y, int p2x, int p2y) {

        int valor = Espaco2D.distancia_entre_pontos(p1x, p1y, p2x, p2y);
        int distancia = valor * 5;

        return distancia;
    }

    public static String distanciaComUnidade(int p1x, int p1y, int p2x, int p2y) {

        int valor = Espaco2D.distancia_entre_pontos(p1x, p1y, p2x, p2y);
        int distancia = valor * 5;

        return distancia + " Stgz";
    }

    public static float tempo_de_viagem(int distancia, int velocidade) {
        float tempo = (float) distancia / (float) velocidade;
        return tempo;
    }


    public static String getDescricaoViagem(int velocidade, float tempo) {
        return "Tempo = " + fmt.doubleNumC2(tempo) + " arkos a " + velocidade + " Stgz/arko";
    }
}

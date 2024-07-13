package apps.app_atzum;

import apps.app_atzum.apps.*;
import libs.azzal.AzzalUnico;

public class Apps {

    public static final int APP_ATZUM = 0;
    public static final int CIDADE_POSICIONADOR = 1;
    public static final int RELEVADOR_TERRA = 2;
    public static final int RELEVADOR_AGUA = 3;
    public static final int TEMPERATURA = 4;

    public static final int TEMPERATURA_V1 = 5;
    public static final int TEMPERATURA_V2 = 6;

    public static final int MASSAS_DE_AR = 7;

    public static final int ZONEADOR = 8;
    public static final int TEMPERATURA_GERAL = 9;


    public static void INIT(int qual_app) {

        if (qual_app == APP_ATZUM) {
            AzzalUnico.unico("Mapa Atzum", 2000, 950, new apps.app_atzum.AppAtzum());
        } else if (qual_app == CIDADE_POSICIONADOR) {
            AzzalUnico.unico("Mapa Atzum - Posicionador de Cidades", 1700, 950, new apps.app_atzum.apps.AtzumAppCidadePosicionador());
        } else if (qual_app == RELEVADOR_TERRA) {
            AzzalUnico.unico("Mapa Atzum - Construtor de Relevo :: Terra", 1700, 950, new AtzumAppRelevadorTerra());
        } else if (qual_app == RELEVADOR_AGUA) {
            AzzalUnico.unico("Mapa Atzum - Construtor de Relevo :: Água", 1700, 950, new AtzumAppRelevadorAgua());
        } else if (qual_app == TEMPERATURA_GERAL) {
            AzzalUnico.unico("Mapa Atzum - Construtor de Relevo :: Terra", 1700, 950, new AtzumAppTemperatura());
        } else if (qual_app == TEMPERATURA) {
            AzzalUnico.unico("Mapa Atzum - Mapeador de Temperatura", 1700, 950, new AtzumAppRelevadorAgua());
        } else if (qual_app == TEMPERATURA_V1) {
            AzzalUnico.unico("Mapa Atzum - Mapeador de Temperatura - V1", 1700, 950, new AtzumLinhaTermicaV1());
        } else if (qual_app == TEMPERATURA_V2) {
            AzzalUnico.unico("Mapa Atzum - Mapeador de Temperatura - V2", 1700, 950, new AtzumLinhaTermicaV2());
        } else if (qual_app == ZONEADOR) {
            AzzalUnico.unico("Mapa Atzum - Mapeador de Zona", 1700, 950, new AtzumAppZoneador());
        } else if (qual_app == MASSAS_DE_AR) {
            AzzalUnico.unico("Mapa Atzum - Massas de Ar", 1700, 950, new AtzumAppMassasDeAr());


        }

    }


}

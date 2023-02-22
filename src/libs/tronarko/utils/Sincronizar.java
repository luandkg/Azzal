package libs.tronarko.utils;

import libs.tronarko.Hazde;
import libs.tronarko.Tozte;

public class Sincronizar {

    public static int getSincronizanteHazde(Hazde Local, Hazde Origem) {
        return (Origem.getTotalEttons() - Local.getTotalEttons());
    }

    public static long getSincronizanteTozte(Tozte Local, Tozte Origem) {
        return (Origem.getSuperarkosTotal() - Local.getSuperarkosTotal());
    }

}

package Tronarko.Utils;

import Tronarko.Hazde;
import Tronarko.Tozte;

public class Sincronizar {

    public static int getSincronizanteHazde(Hazde Local, Hazde Origem) {
        return (Origem.getTotalEttons() - Local.getTotalEttons());
    }

    public static long getSincronizanteTozte(Tozte Local, Tozte Origem) {
        return (Origem.getSuperarkosTotal() - Local.getSuperarkosTotal());
    }

}

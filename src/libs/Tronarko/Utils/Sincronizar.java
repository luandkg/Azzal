package libs.Tronarko.Utils;

import libs.Tronarko.Hazde;
import libs.Tronarko.Tozte;

public class Sincronizar {

    public static int getSincronizanteHazde(Hazde Local, Hazde Origem) {
        return (Origem.getTotalEttons() - Local.getTotalEttons());
    }

    public static long getSincronizanteTozte(Tozte Local, Tozte Origem) {
        return (Origem.getSuperarkosTotal() - Local.getSuperarkosTotal());
    }

}

package libs.tronarko.Utils;

import libs.tronarko.Hazde;
import libs.tronarko.Tozte;

public class TronarkoNumero {

    public static long getLong(Tozte T, Hazde H) {

        long tta = 10 * 100 * 100;

        long total = (T.getSuperarkosTotal() * tta) + H.getTotalEttons();

        return total;
    }

    public static String setLong(long total) {

        long tta = 10 * 100 * 100;

        long vcc = total;
        long supers = 0;

        while (vcc >= tta) {
            vcc -= tta;
            supers += 1;
        }

        int vt = 0;
        int vh = 0;
        int vs = 0;

        while (supers >= 500) {
            vt += 1;
            supers -= 500;
        }
        while (supers >= 50) {
            vh += 1;
            supers -= 50;
        }

        vh += 1;


        int va1 = 0;
        int va2 = 0;

        while (vcc >= 10000) {
            va1 += 1;
            vcc -= 10000;
        }

        while (vcc >= 100) {
            va2 += 1;
            vcc -= 100;
        }

        va1 += 1;


        String v1 = supers + "/" + vh + "/" + vt;

        String v2 = va1 + ":" + va2 + ":" + vcc;

        return v1 + " " + v2;
    }


}

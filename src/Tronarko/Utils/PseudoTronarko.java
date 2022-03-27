package Tronarko.Utils;

import Tronarko.Hazde;
import Tronarko.Tozte;
import Tronarko.Tron;

import java.util.Random;

public class PseudoTronarko {

    public static Tron getTron_Aleatorio() {
        Random rd = new Random();
        return new Tron(rd.nextInt(10), rd.nextInt(100), rd.nextInt(100), rd.nextInt(50) + 1, rd.nextInt(10) + 1, rd.nextInt(8000));
    }

    public static Tozte getTozte_Aleatorio() {
        Random rd = new Random();
        return new Tozte(rd.nextInt(50) + 1, rd.nextInt(10) + 1, rd.nextInt(8000));
    }

    public static Tozte getTozte_AleatorioDoTronarko(int eTronarko) {
        Random rd = new Random();
        return new Tozte(rd.nextInt(50) + 1, rd.nextInt(10) + 1, eTronarko);
    }

    public static Tozte getTozte_AleatorioDoHiperarko(int eHiperarko, int eTronarko) {
        Random rd = new Random();
        return new Tozte(rd.nextInt(50) + 1, eHiperarko, eTronarko);
    }

    public static Hazde getHazde_Aleatorio() {
        Random rd = new Random();
        return new Hazde(rd.nextInt(10), rd.nextInt(100), rd.nextInt(100));
    }

    public static Hazde getHazdeSemUzzon_Aleatorio() {
        Random rd = new Random();
        return new Hazde(rd.nextInt(10), rd.nextInt(100), 0);
    }

    public static Hazde getHazdeArko_Aleatorio() {
        Random rd = new Random();
        return new Hazde(rd.nextInt(10), 0, 0);
    }
}


package libs.Tronarko.Utils;

import libs.Tronarko.Tozte;

public class FluxoTemporal {


    public static Tozte amanha(Tozte eTozte) {

        Tozte eAmanha = eTozte.getCopia().adicionar_Superarko(1);
        return eAmanha;

    }

    public static Tozte ontem(Tozte eTozte) {

        Tozte eOntem = eTozte.getCopia().adicionar_Superarko(-1);
        return eOntem;

    }

    public static Tozte depois_de_amanha(Tozte eTozte) {

        Tozte eAmanha = eTozte.getCopia().adicionar_Superarko(2);
        return eAmanha;

    }

    public static Tozte ante_ontem(Tozte eTozte) {

        Tozte eOntem = eTozte.getCopia().adicionar_Superarko(-2);
        return eOntem;

    }

    public static Tozte futuro(Tozte eTozte, int v) {

        if (v > 0) {
            Tozte no_futuro = eTozte.getCopia().adicionar_Superarko(v);
            return no_futuro;
        } else {
            Tozte eHoje = eTozte.getCopia();
            return eHoje;
        }

    }

    public static Tozte passado(Tozte eTozte, int v) {

        if (v > 0) {
            Tozte no_passado = eTozte.getCopia().adicionar_Superarko(v * (-1));
            return no_passado;
        } else {
            Tozte eHoje = eTozte.getCopia();
            return eHoje;
        }

    }

    public static Tozte ultimo_tozte_hiperarko(Tozte eTozte) {

        Tozte ultimo = new Tozte(50, eTozte.getHiperarko(), eTozte.getTronarko());
        return ultimo;

    }

    public static Tozte primeiro_tozte_hiperarko(Tozte eTozte) {

        Tozte primeiro = new Tozte(1, eTozte.getHiperarko(), eTozte.getTronarko());
        return primeiro;

    }
}

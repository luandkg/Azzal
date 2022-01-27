package AppAttuz.Localizador;

import Tronarko.StringTronarko;
import Tronarko.Tronarko;
import Tronarko.Tozte;
import Tronarko.Hazde;

public class Visita {


    public static void onVisita(Tozte eTozte, Hazde eHazde, String visita_comecar){

        System.out.println("\t - Cheguei :: " + visita_comecar);
        System.out.println("\t - Agora   :: " + eTozte.toString() + " :: " + c2(eHazde.getArco()) + ":" + c2(eHazde.getItta()));

        StringTronarko st = new StringTronarko();

        int t = Integer.parseInt(st.getTronarko(visita_comecar));
        int h = Integer.parseInt(st.getHiperarko(visita_comecar));
        int s = Integer.parseInt(st.getSuperarko(visita_comecar));

        int tt1 = st.getTotalSuperarkos(t, h, s);
        int tt2 = st.getTotalSuperarkos(eTozte.getTronarko(), eTozte.getHiperarko(), eTozte.getSuperarko());

        int hazde_a = Integer.parseInt(st.getArkoFormatoComplexo(visita_comecar));
        int hazde_i = Integer.parseInt(st.getIttasFormatoComplexo(visita_comecar));

        int ts = tt2 - tt1;
        int ti = st.getTotalIttas(eHazde.getArco(), eHazde.getItta()) - st.getTotalIttas(hazde_a, hazde_i);

        hazde_a = ti / 100;
        hazde_i = ti - (hazde_a * 100);

        System.out.println("\t - Duracao   :: " + ts + " -->> " + hazde_a + ":" + hazde_i);


    }

    public static String c2(int i) {
        String v = String.valueOf(i);
        if (v.length() == 1) {
            v = "0" + v;
        }
        return v;
    }


}

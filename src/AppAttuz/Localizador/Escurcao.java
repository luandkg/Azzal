package AppAttuz.Localizador;

import Tronarko.StringTronarko;
import Tronarko.Tronarko;
import Tronarko.Tozte;
import Tronarko.Hazde;

public class Escurcao {


    public static void onEscurcao(Tozte eTozte, Hazde eHazde, String visita_comecar){

        System.out.println("\t - Comecei :: " + visita_comecar);
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

        int pi1 = st.getTotalIttas(eHazde.getArco(), eHazde.getItta());
        int pi2 = st.getTotalIttas(hazde_a, hazde_i);

        int ti = 0;

        if (pi2>pi1){
            int f1 = st.getTotalIttas(9, 99)-st.getTotalIttas(eHazde.getArco(), eHazde.getItta());
            ti=f1 + st.getTotalIttas(hazde_a, hazde_i);
        }else{
             ti =  pi1- pi2;
        }

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

package AppAttuz.Localizador;

import Tronarko.Intervalos.Tron_Intervalo;
import Tronarko.StringTronarko;
import Tronarko.Tron;

public class ViajemAcao {

    public static void onVisita(String cidade_chegou, String cidade_agora){

        System.out.println("\t - Cheguei :: " + cidade_chegou);
        System.out.println("\t - Agora   :: " + cidade_agora);

        StringTronarko st = new StringTronarko();

        int vt = Integer.parseInt(st.getTronarko(cidade_chegou));
        int vh = Integer.parseInt(st.getHiperarko(cidade_chegou));
        int vs = Integer.parseInt(st.getSuperarko(cidade_chegou));

        int v_a = Integer.parseInt(st.getArkoFormatoComplexo(cidade_chegou));
        int v_i = Integer.parseInt(st.getIttasFormatoComplexo(cidade_chegou));


        int at = Integer.parseInt(st.getTronarko(cidade_agora));
        int ah = Integer.parseInt(st.getHiperarko(cidade_agora));
        int as = Integer.parseInt(st.getSuperarko(cidade_agora));

        int a_a = Integer.parseInt(st.getArkoFormatoComplexo(cidade_agora));
        int a_i = Integer.parseInt(st.getIttasFormatoComplexo(cidade_agora));

        Tron_Intervalo ti = new Tron_Intervalo("",new Tron(v_a,v_i,0,vs,vh,vt),new Tron(a_a,a_i,0,as,ah,at));


        System.out.println("\t - Duracao :: " + ti.getIntervalo());


    }

    public static void onEscurcao(String viagem_comecou, String viagem_agora){

        System.out.println("\t - Comecei :: " + viagem_comecou);
        System.out.println("\t - Agora   :: " + viagem_agora);

        StringTronarko st = new StringTronarko();

        int vt = Integer.parseInt(st.getTronarko(viagem_comecou));
        int vh = Integer.parseInt(st.getHiperarko(viagem_comecou));
        int vs = Integer.parseInt(st.getSuperarko(viagem_comecou));

        int v_a = Integer.parseInt(st.getArkoFormatoComplexo(viagem_comecou));
        int v_i = Integer.parseInt(st.getIttasFormatoComplexo(viagem_comecou));


        int at = Integer.parseInt(st.getTronarko(viagem_agora));
        int ah = Integer.parseInt(st.getHiperarko(viagem_agora));
        int as = Integer.parseInt(st.getSuperarko(viagem_agora));

        int a_a = Integer.parseInt(st.getArkoFormatoComplexo(viagem_agora));
        int a_i = Integer.parseInt(st.getIttasFormatoComplexo(viagem_agora));

        Tron_Intervalo ti = new Tron_Intervalo("",new Tron(v_a,v_i,0,vs,vh,vt),new Tron(a_a,a_i,0,as,ah,at));


        System.out.println("\t - Duracao :: " + ti.getIntervalo());

    }

}

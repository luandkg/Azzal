package libs.tronarko.utils;

import libs.tronarko.Intervalos.Tozte_Intervalo;
import libs.tronarko.Intervalos.Tron_Intervalo;
import libs.tronarko.Tozte;
import libs.tronarko.Tron;

public class IntTronarko {

    public static int getSuperarkos(int s, int h, int t) {
        return (t * 500) + ((h -1)* 50) + s;
    }

    public static int getIttas(int a,int i){
        return (a * 100) + i;
    }

    public static String intervalo(String inicio,String fim){

        StringTronarko st = new StringTronarko();

        int vt = Integer.parseInt(st.getTronarko(inicio));
        int vh = Integer.parseInt(st.getHiperarko(inicio));
        int vs = Integer.parseInt(st.getSuperarko(inicio));

        int v_a = Integer.parseInt(st.getArkoFormatoComplexo(inicio));
        int v_i = Integer.parseInt(st.getIttasFormatoComplexo(inicio));


        int at = Integer.parseInt(st.getTronarko(fim));
        int ah = Integer.parseInt(st.getHiperarko(fim));
        int as = Integer.parseInt(st.getSuperarko(fim));

        int a_a = Integer.parseInt(st.getArkoFormatoComplexo(fim));
        int a_i = Integer.parseInt(st.getIttasFormatoComplexo(fim));

        Tron_Intervalo ti = new Tron_Intervalo("",new Tron(v_a,v_i,0,vs,vh,vt),new Tron(a_a,a_i,0,as,ah,at));


       return ti.getIntervalo();
    }

    public static int intervaloIttas(String inicio,String fim){

        StringTronarko st = new StringTronarko();

        int vt = Integer.parseInt(st.getTronarko(inicio));
        int vh = Integer.parseInt(st.getHiperarko(inicio));
        int vs = Integer.parseInt(st.getSuperarko(inicio));

        int v_a = Integer.parseInt(st.getArkoFormatoComplexo(inicio));
        int v_i = Integer.parseInt(st.getIttasFormatoComplexo(inicio));


        int at = Integer.parseInt(st.getTronarko(fim));
        int ah = Integer.parseInt(st.getHiperarko(fim));
        int as = Integer.parseInt(st.getSuperarko(fim));

        int a_a = Integer.parseInt(st.getArkoFormatoComplexo(fim));
        int a_i = Integer.parseInt(st.getIttasFormatoComplexo(fim));

        Tron_Intervalo ti = new Tron_Intervalo("",new Tron(v_a,v_i,0,vs,vh,vt),new Tron(a_a,a_i,0,as,ah,at));


        return ti.getIntervaloIttas();
    }

    public static long getSuperarkosDiferenca(String t1, String t2) {

        StringTronarko st = new StringTronarko();

        Tozte r1 = new Tozte(Integer.parseInt(st.getSuperarko(t1)), Integer.parseInt(st.getHiperarko(t1)), Integer.parseInt(st.getTronarko(t1)));
        Tozte r2 = new Tozte(Integer.parseInt(st.getSuperarko(t2)), Integer.parseInt(st.getHiperarko(t2)), Integer.parseInt(st.getTronarko(t2)));

        Tozte_Intervalo intervalo = new Tozte_Intervalo("", r1, r2);

        return intervalo.getSuperarkos();
    }
}

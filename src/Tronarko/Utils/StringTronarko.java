package Tronarko.Utils;

import Tronarko.Hazde;
import Tronarko.Tozte;

public class StringTronarko {

    // 2021 12 29 - CAIXA DE UTILIDADES


    public String getSuperarko(String tozte) {
        String ret = "";

        int o = tozte.length();
        int i = 0;

        while (i < 2 && i < o) {
            ret += String.valueOf(tozte.charAt(i));
            i += 1;
        }

        return ret;
    }

    public String getHiperarko(String tozte) {
        String ret = "";

        int o = tozte.length();
        int i = 3;

        while (i < 5 && i < o) {
            ret += String.valueOf(tozte.charAt(i));
            i += 1;
        }

        return ret;
    }

    public String getTronarko(String tozte) {
        String ret = "";

        int o = tozte.length();
        int i = 6;

        while (i < 10 && i < o) {
            ret += String.valueOf(tozte.charAt(i));
            i += 1;
        }

        return ret;
    }

    public String getArkoFormatoComplexo(String tozte) {
        String ret = "";

        int o = tozte.length();
        int i = 14;

        while (i < 16 && i < o) {
            ret += String.valueOf(tozte.charAt(i));
            i += 1;
        }

        return ret;
    }

    public String getIttasFormatoComplexo(String tozte) {
        String ret = "";

        int o = tozte.length();
        int i = 17;

        while (i < 19 && i < o) {
            ret += String.valueOf(tozte.charAt(i));
            i += 1;
        }

        return ret;
    }


    public Tozte getTozte(String entrada){

        int t = Integer.parseInt(getTronarko(entrada));
        int h = Integer.parseInt(getHiperarko(entrada));
        int s = Integer.parseInt(getSuperarko(entrada));

        return new Tozte(s,h,t);
    }

    public Hazde getHazdeDeComplexo(String entrada){

        int a = Integer.parseInt(getArkoFormatoComplexo(entrada));
        int i = Integer.parseInt(getIttasFormatoComplexo(entrada));

        return new Hazde(a,i,0);
    }
}

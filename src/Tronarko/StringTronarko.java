package Tronarko;

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

    public int getTotalSuperarkos(int t, int h, int s) {
        return (t * 500) + (h * 50) + s;
    }

    public int getTotalIttas(int a, int i) {
        return ((a * 100) + i);
    }
}

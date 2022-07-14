package libs.tronarko.Utils;

public class ContadorTronarko {

    private int i = 0;
    private int a = 0;

    private int s = 0;
    private int h = 0;
    private int t = 0;

    private int mSuperarkos;

    public ContadorTronarko(int eSuperarko, int eHiperarko, int eTronarko) {

        s = eSuperarko;
        h = eSuperarko;
        t = eSuperarko;

        mSuperarkos = 0;
    }

    public int getArco() {
        return a;
    }

    public void proximo(int mais) {

        i += mais;

        if (i >= 100) {
            i = 0;
            a += 1;
        }

        if (a == 10) {
            a = 0;
            s += 1;

            mSuperarkos += 1;

        }
        if (s > 50) {
            s = 1;
            h += 1;
        }
        if (h > 10) {
            h = 1;
            t += 1;
        }
    }

    public int getTronarko() {
        return t;
    }


    public int getSuperarkos() {
        return mSuperarkos;
    }

    public String getTozteComArkoIttas() {
        return c2(s) + "/" + c2(h) + "/" + t + " :: " + c2(a) + ":" + c2(i);
    }

    public String getTozte() {
        return c2(s) + "/" + c2(h) + "/" + t;
    }

    public String getArkoIttas() {
        return c2(a) + ":" + c2(i);
    }

    public String getHazde() {
        return c2(a) + ":" + c2(i) + ":00";
    }

    public String getTron() {
        return c2(s) + "/" + c2(h) + "/" + t + " :: " + c2(a) + ":" + c2(i) + ":00";
    }

    private String c2(int i) {
        String v = String.valueOf(i);
        if (v.length() == 1) {
            v = "0" + v;
        }
        return v;
    }
}

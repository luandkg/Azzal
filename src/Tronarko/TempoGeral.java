package Tronarko;

public class TempoGeral {

    private int i = 0;
    private int a = 0;
    private int s = 46;
    private int h = 4;
    private int t = 7002;
    private int mSuperarkos;

    public TempoGeral() {
        mSuperarkos=0;
    }

    public int getArco() {
        return a;
    }

    public void proximo() {

        i += 40;

        if (i >= 100) {
            i = 0;
            a += 1;
        }

        if (a == 10) {
            a = 0;
            s += 1;

            mSuperarkos+=1;

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

    public int getTronarko(){return t;}


    public int getSuperarkos(){return mSuperarkos;}

    public String get() {
        return c2(s) + "/" + c2(h) + "/" + t + " :: " + c2(a) + ":" + c2(i);
    }

    public String c2(int i) {
        String v = String.valueOf(i);
        if (v.length() == 1) {
            v = "0" + v;
        }
        return v;
    }
}

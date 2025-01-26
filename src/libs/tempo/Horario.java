package libs.tempo;

public class Horario {

    private int mHora;
    private int mMinutos;
    private int mSegundos;
    private int mMilissegundos;

    public Horario(int h, int m, int s) {
        mHora = h;
        mMinutos = m;
        mSegundos = s;
        mMilissegundos=0;
    }

    public Horario(int h, int m, int s,int milissegundos) {
        mHora = h;
        mMinutos = m;
        mSegundos = s;
        mMilissegundos=milissegundos;
    }


    public int getHora() {
        return mHora;
    }

    public int getMinutos() {
        return mMinutos;
    }

    public int getSegundos() {
        return mSegundos;
    }

    public int getMilissegundos() {
        return mMilissegundos;
    }


    public String getTempo() {


        String sh = String.valueOf(mHora);
        if (sh.length() == 1) {
            sh = "0" + sh;
        }

        String sm = String.valueOf(mMinutos);
        if (sm.length() == 1) {
            sm = "0" + sm;
        }

        String ss = String.valueOf(mSegundos);
        if (ss.length() == 1) {
            ss = "0" + ss;
        }

        return sh + ":" + sm + ":" + ss;
    }


    public long getValor() {
        return (mHora * 60 * 60) + (mMinutos * 60) + mSegundos;
    }


    public static Horario toHorario(String s) {

        String sh = String.valueOf(s.charAt(0)) + String.valueOf(s.charAt(1));
        String sm = String.valueOf(s.charAt(3)) + String.valueOf(s.charAt(4));
        String ss = String.valueOf(s.charAt(6)) + String.valueOf(s.charAt(7));


        return new Horario(Integer.parseInt(sh), Integer.parseInt(sm), Integer.parseInt(ss));

    }

    public static Horario toHorarioSemSegundos(String s) {

        String sh = String.valueOf(s.charAt(0)) + String.valueOf(s.charAt(1));
        String sm = String.valueOf(s.charAt(3)) + String.valueOf(s.charAt(4));


        return new Horario(Integer.parseInt(sh), Integer.parseInt(sm), 0);

    }


    public static long diferenca(Horario h1, Horario h2) {
        long v1 = h1.getValor();
        long v2 = h2.getValor();

        return v1 - v2;
    }

    public String getTempoSemSegundos() {


        String sh = String.valueOf(mHora);
        if (sh.length() == 1) {
            sh = "0" + sh;
        }

        String sm = String.valueOf(mMinutos);
        if (sm.length() == 1) {
            sm = "0" + sm;
        }


        return sh + ":" + sm;
    }

}

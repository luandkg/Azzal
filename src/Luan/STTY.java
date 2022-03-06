package Luan;

import Luan.Tarefa;

public class STTY {

    public String inFixo(String s, int t) {

        while (s.length() < t) {
            s = " " + s;
        }

        return s;
    }

    public String inAposFixo(String s, int t) {

        while (s.length() < t) {
            s = s + " ";
        }

        return s;
    }

    public String intNum(int i, int c) {
        String s = String.valueOf(i);
        while (s.length() < c) {
            s = "0" + s;
        }
        return s;
    }

    public String LongNum(long i, int c) {
        String s = String.valueOf(i);
        while (s.length() < c) {
            s = "0" + s;
        }
        return s;
    }

    public static String doubleNumC2(double numero) {
        String s = String.valueOf(numero);
        String f = "";

        int e = 0;
        int c = 2;

        boolean ja = false;

        int i = 0;
        int o = s.length();
        while (i < o) {
            String letra = String.valueOf(s.charAt(i));
            if (letra.contentEquals(".")) {
                ja = true;
                f += letra;
            } else {
                if (!ja) {
                    f += letra;
                } else {
                    if (e < c) {
                        f += letra;
                    }
                    e += 1;
                }
            }
            i += 1;
        }

        return f;
    }

    public static String doubleNumC3(double numero) {
        String s = String.valueOf(numero);
        String f = "";

        int e = 0;
        int c = 3;

        boolean ja = false;

        int i = 0;
        int o = s.length();
        while (i < o) {
            String letra = String.valueOf(s.charAt(i));
            if (letra.contentEquals(".")) {
                ja = true;
                f += letra;
            } else {
                if (!ja) {
                    f += letra;
                } else {
                    if (e < c) {
                        f += letra;
                    }
                    e += 1;
                }
            }
            i += 1;
        }

        return f;
    }

    public static String f2zerado(double numero) {
        String s = String.valueOf(numero);
        String f = "";

        int e = 0;
        int c = 2;

        boolean ja = false;

        int i = 0;
        int o = s.length();
        while (i < o) {
            String letra = String.valueOf(s.charAt(i));
            if (letra.contentEquals(".")) {
                ja = true;
                f += letra;
            } else {
                if (!ja) {
                    f += letra;
                } else {
                    if (e < c) {
                        f += letra;
                    }
                    e += 1;
                }
            }
            i += 1;
        }

        if (e < c) {
            f += "0";
        }
        return f;
    }

    public String getTempoFormatado(long t) {

        if (t < 1000) {
            return t + " ms";
        } else {

            int s = 0;
            while (t >= 1000) {
                t -= 1000;
                s += 1;
            }

            if (s >= 60) {
                int min = 0;
                while (s >= 60) {
                    s -= 60;
                    min += 1;
                }

                return min + " min " + s + " s";

            } else {
                return s + " s";
            }

        }

    }

    public long getTempoTarefa(Tarefa eTarefa) {
        long eAntes = System.currentTimeMillis();

        eTarefa.executar();

        long eDepois = System.currentTimeMillis();
        return eDepois - eAntes;
    }

    public String getTempoFormatadoTarefa(Tarefa eTarefa) {
        long eAntes = System.currentTimeMillis();

        eTarefa.executar();

        long eDepois = System.currentTimeMillis();
        return getTempoFormatado(eDepois - eAntes);
    }

    public String getTempoEmSegundosFormatado(long s) {


        if (s >= 60) {
            int min = 0;
            while (s >= 60) {
                s -= 60;
                min += 1;
            }

            return min + " min " + s + " s";

        } else {
            return s + " s";
        }


    }

    public static String espacar_antes(String s, int t) {

        while (s.length() < t) {
            s = " " + s;
        }

        return s;
    }

}

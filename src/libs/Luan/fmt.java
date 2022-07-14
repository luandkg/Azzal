package libs.Luan;

import java.util.ArrayList;

public class fmt {

    // AUTOR : LUAN FREITAS
    // DATA : 2022 01 16

    private static String format_text(String texto, ArrayList<Object> objetos) {

        int i = 0;
        int o = texto.length();

        ArrayList<FatiaString> fatias = new ArrayList<FatiaString>();

        String juntando = "";
        boolean isDentro = false;

        while (i < o) {
            String letra = String.valueOf(texto.charAt(i));

            if (isDentro) {

                if (letra.contentEquals("}")) {

                    FatiaString fatia = new FatiaString();
                    fatia.setTipo(juntando);
                    fatias.add(fatia);

                    isDentro = false;
                    juntando = "";

                } else {
                    juntando += letra;
                }

            } else {
                if (letra.contentEquals("{")) {
                    fatias.add(new FatiaString(juntando));

                    isDentro = true;
                    juntando = "";
                } else {
                    juntando += letra;
                }

            }
            i += 1;
        }

        if (juntando.length() > 0) {
            fatias.add(new FatiaString(juntando));
        }

        String retornar = "";
        int indice = 0;

        for (FatiaString fatia : fatias) {


            if (fatia.mesmoTipo("STRING")) {
                retornar += fatia.get();
            } else {

                if (fatia.getTipo().startsWith("dir")) {

                    String tam = fatia.getTipo().replace("dir", "");
                    int t = Integer.parseInt(tam);
                    String v = String.valueOf(objetos.get(indice));

                    while (v.length() < t) {
                        v = v + " ";
                    }

                    retornar += v;
                } else if (fatia.getTipo().startsWith("esq")) {


                    String tam = fatia.getTipo().replace("esq", "");
                    int t = Integer.parseInt(tam);
                    String v = String.valueOf(objetos.get(indice));

                    while (v.length() < t) {
                        v = " " + v;
                    }

                    retornar += v;

                } else if (fatia.getTipo().startsWith("f2")) {
                    String v = String.valueOf(objetos.get(indice));

                    if (v.endsWith(".0")) {
                        v = v + "0";
                    } else if (!v.contains(".")) {
                        v = v + ".00";
                    }

                    retornar += v;

                } else {
                    retornar += objetos.get(indice);
                }

                indice += 1;
            }

        }


        return retornar;
    }

    public static String format(String texto, Object o1) {

        ArrayList<Object> objetos = new ArrayList<Object>();
        objetos.add(o1);

        return format_text(texto, objetos);
    }

    public static String format(String texto, Object o1, Object o2) {

        ArrayList<Object> objetos = new ArrayList<Object>();
        objetos.add(o1);
        objetos.add(o2);

        return format_text(texto, objetos);
    }

    public static String format(String texto, Object o1, Object o2, Object o3) {

        ArrayList<Object> objetos = new ArrayList<Object>();
        objetos.add(o1);
        objetos.add(o2);
        objetos.add(o3);

        return format_text(texto, objetos);
    }

    public static String format(String texto, Object o1, Object o2, Object o3, Object o4) {

        ArrayList<Object> objetos = new ArrayList<Object>();
        objetos.add(o1);
        objetos.add(o2);
        objetos.add(o3);
        objetos.add(o4);

        return format_text(texto, objetos);
    }

    public static String format(String texto, Object o1, Object o2, Object o3, Object o4, Object o5) {

        ArrayList<Object> objetos = new ArrayList<Object>();
        objetos.add(o1);
        objetos.add(o2);
        objetos.add(o3);
        objetos.add(o4);
        objetos.add(o5);

        return format_text(texto, objetos);
    }

    public static String format(String texto, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6) {

        ArrayList<Object> objetos = new ArrayList<Object>();
        objetos.add(o1);
        objetos.add(o2);
        objetos.add(o3);
        objetos.add(o4);
        objetos.add(o5);
        objetos.add(o6);

        return format_text(texto, objetos);
    }

    public static String format(String texto, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7) {

        ArrayList<Object> objetos = new ArrayList<Object>();
        objetos.add(o1);
        objetos.add(o2);
        objetos.add(o3);
        objetos.add(o4);
        objetos.add(o5);
        objetos.add(o6);
        objetos.add(o7);

        return format_text(texto, objetos);
    }

    public static String format(String texto, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8) {

        ArrayList<Object> objetos = new ArrayList<Object>();
        objetos.add(o1);
        objetos.add(o2);
        objetos.add(o3);
        objetos.add(o4);
        objetos.add(o5);
        objetos.add(o6);
        objetos.add(o7);
        objetos.add(o8);

        return format_text(texto, objetos);
    }

    public static String format(String texto, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9) {

        ArrayList<Object> objetos = new ArrayList<Object>();
        objetos.add(o1);
        objetos.add(o2);
        objetos.add(o3);
        objetos.add(o4);
        objetos.add(o5);
        objetos.add(o6);
        objetos.add(o7);
        objetos.add(o8);
        objetos.add(o9);

        return format_text(texto, objetos);
    }

    public static String format(String texto, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9, Object o10) {

        ArrayList<Object> objetos = new ArrayList<Object>();
        objetos.add(o1);
        objetos.add(o2);
        objetos.add(o3);
        objetos.add(o4);
        objetos.add(o5);
        objetos.add(o6);
        objetos.add(o7);
        objetos.add(o8);
        objetos.add(o9);
        objetos.add(o10);

        return format_text(texto, objetos);
    }

    public static String getN8(long e) {
        String v = String.valueOf(e);
        while (v.length() < 8) {
            v = "0" + v;
        }
        return v;
    }

    public static String getN3(long e) {
        String v = String.valueOf(e);
        while (v.length() < 3) {
            v = "0" + v;
        }
        return v;
    }

    public static String getN2(long e) {
        String v = String.valueOf(e);
        while (v.length() < 2) {
            v = "0" + v;
        }
        return v;
    }

    public static String getCasas(double e, int c) {

        String valor = String.valueOf(e);
        String mRet = "";

        int i = 0;
        int o = valor.length();

        boolean mPontuou = false;
        int dp = 0;

        while (i < o) {
            String l = String.valueOf(valor.charAt(i));
            if (mPontuou) {
                if (dp < c) {
                    mRet += l;
                } else {
                    break;
                }
                dp += 1;
            } else {
                if (l.contentEquals(".")) {
                    if (c > 0) {
                        mRet += l;
                    }
                    mPontuou = true;
                } else {
                    mRet += l;
                }
            }

            i += 1;
        }

        return mRet;

    }


    // PRINT

    public static void print(String texto ) {
        System.out.println(texto);
    }

    public static void print(String texto, Object o1) {
        System.out.println(format(texto, o1));
    }

    public static void print(String texto, Object o1, Object o2) {
        System.out.println(format(texto, o1, o2));
    }

    public static void print(String texto, Object o1, Object o2, Object o3) {
        System.out.println(format(texto, o1, o2, o3));
    }

    public static void print(String texto, Object o1, Object o2, Object o3, Object o4) {
        System.out.println(format(texto, o1, o2, o3, o4));
    }

    public static void print(String texto, Object o1, Object o2, Object o3, Object o4, Object o5) {
        System.out.println(format(texto, o1, o2, o3, o4, o5));
    }

    public static void print(String texto, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6) {
        System.out.println(format(texto, o1, o2, o3, o4, o5, o6));
    }

    public static void print(String texto, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7) {
        System.out.println(format(texto, o1, o2, o3, o4, o5, o6, o7));
    }


    public static void print(String texto, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8) {
        System.out.println(format(texto, o1, o2, o3, o4, o5, o6, o7, o8));
    }


    public static void print(String texto, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9) {
        System.out.println(format(texto, o1, o2, o3, o4, o5, o6, o7, o8, o9));
    }

    public static void print(String texto, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8, Object o9, Object o10) {
        System.out.println(format(texto, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10));
    }

}

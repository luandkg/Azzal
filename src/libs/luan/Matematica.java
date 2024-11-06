package libs.luan;

public class Matematica {

    public static int maior(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public static int menor(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    public static double normaliza(double a) {
        if (a < 0) {
            return a * (-1);
        } else {
            return a;
        }
    }

    public static int getProporcionadoInt(double taxa, double valor) {
        return (int) (taxa * valor);
    }


    public static double criarTaxa(int total, int por) {
        return (double) total / (double) por;
    }

    public static int aumentar_ate(int corrente, int taxa, int limite) {

        corrente += taxa;
        if (corrente >= limite) {
            corrente = limite;
        }

        return corrente;
    }

    public static int diminuir_ate(int corrente, int taxa, int limite) {

        corrente -= taxa;
        if (corrente <= limite) {
            corrente = limite;
        }

        return corrente;
    }

    public static boolean isPar(int numero) {
        return (numero % 2) == 0;
    }

    public static boolean isImpar(int numero) {
        return (numero % 2) == 1;
    }

    public static Ordenavel<Double> DOUBLE_COMPARADOR() {
        return new Ordenavel<Double>() {
            @Override
            public int emOrdem(Double a, Double b) {
                return Double.compare(a, b);
            }
        };
    }


    public static double NORMALIZAR(double valor, double min, double max) {

        if (valor < min) {
            valor = min;
        }

        if (valor > max) {
            valor = max;
        }

        return valor;
    }

    public static int NORMALIZAR(int valor, int min, int max) {

        if (valor < min) {
            valor = min;
        }

        if (valor > max) {
            valor = max;
        }

        return valor;
    }


    public static int ESCALA_NORMAL(double valor, double maximo) {
        return (int) (((double) valor / (double) maximo) * 100.0);
    }


    public static int KB(int eKB) {
        return eKB * 1024;
    }

    public static int MB(int eMB) {
        return eMB * (1024 * 1024);
    }

    public static int POSITIVO(int valor) {
        if (valor < 0) {
            return valor * (-1);
        } else {
            return valor;
        }
    }

    public static String CONDICIONAL(boolean condicao, String valido, String nao_valido) {
        if (condicao) {
            return valido;
        } else {
            return nao_valido;
        }
    }

    public static int CONDICIONAL(boolean condicao, int valido, int nao_valido) {
        if (condicao) {
            return valido;
        } else {
            return nao_valido;
        }
    }


    public static boolean isNumero(String s) {
        boolean ret = false;

        String digitos = "0123456789";

        int i = 0;
        int o = s.length();

        if (o > 0) {
            ret = true;

            boolean tem_decimal = false;

            String p = String.valueOf(0);

            if(p.contentEquals("+")||p.contentEquals("-")){
                i+=1;
            }

            while (i < o) {

                String d = String.valueOf(s.charAt(i));

                if (d.contentEquals(".") || d.contentEquals(",")) {
                    tem_decimal = true;
                    i += 1;
                    break;
                } else {
                    if (!digitos.contains(d)) {
                        ret = false;
                        break;
                    }
                }


                i += 1;
            }

            if (tem_decimal) {
                while (i < o) {

                    String d = String.valueOf(s.charAt(i));

                    if (!digitos.contains(d)) {
                        ret = false;
                        break;
                    }

                    i += 1;
                }

            }
        }


        return ret;
    }

    public static boolean isNumeroInteiro(String s) {
        boolean ret = false;

        String digitos = "0123456789";

        int i = 0;
        int o = s.length();

        if (o > 0) {
            ret = true;

            String p = String.valueOf(0);

            if(p.contentEquals("+")||p.contentEquals("-")){
                i+=1;
            }

            while (i < o) {

                String d = String.valueOf(s.charAt(i));

                if (!digitos.contains(d)) {
                   // fmt.print("OI : {}",d);
                    ret = false;
                    break;
                }


                i += 1;
            }

        }


        return ret;
    }

}

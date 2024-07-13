package apps.app_campeonatum;

public class VERIFICADOR {


    public static void IGUALDADE(int obtido, int esperado) {
        if (obtido == esperado) {

        } else {
            throw new RuntimeException("#PROBLEMA " + obtido + " != " + esperado);
        }
    }
    public static void MINIMO(int obtido, int esperado,String frase_debug) {
        if (obtido >= esperado) {

        } else {
            throw new RuntimeException("#PROBLEMA " + obtido + " != " + esperado + " ->> "+frase_debug);
        }
    }

}

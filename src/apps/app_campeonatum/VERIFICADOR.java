package apps.app_campeonatum;

public class VERIFICADOR {


    public static void ERRAR(String erro) {
        throw new RuntimeException("#VERIFICADOR :: " + erro);
    }

    public static void IGUALDADE(int obtido, int esperado) {
        if (obtido == esperado) {

        } else {
            throw new RuntimeException("#VERIFICADOR " + obtido + " != " + esperado);
        }
    }

    public static void MINIMO(int obtido, int esperado, String frase_debug) {
        if (obtido >= esperado) {

        } else {
            throw new RuntimeException("#VERIFICADOR " + obtido + " != " + esperado + " ->> " + frase_debug);
        }
    }

}

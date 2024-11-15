package apps.app_campeonatum;

import libs.luan.Strings;

public class VERIFICADOR {


    public static void ERRAR(String erro) {
        throw new RuntimeException("#VERIFICADOR :: " + erro);
    }

    public static void IGUALDADE(int obtido, int esperado) {
        if (obtido != esperado) {
            throw new RuntimeException("#VERIFICADOR " + obtido + " != " + esperado);
        }
    }

    public static void IGUALDADE(boolean obtido, boolean esperado) {
        if (obtido != esperado) {
            throw new RuntimeException("#VERIFICADOR " + obtido + " != " + esperado);
        }
    }

    public static void IGUALDADE(String obtido, String esperado) {
        if (Strings.isDiferente(obtido, esperado)) {
            throw new RuntimeException("#VERIFICADOR " + obtido + " != " + esperado);
        }
    }


    public static void MENOR_OU_IGUAL(int obtido, int esperado ) {
        if (obtido <= esperado) {

        } else {
            throw new RuntimeException("#VERIFICADOR " + obtido + " != " + esperado );
        }
    }

    public static void MAIOR_OU_IGUAL(int obtido, int esperado, String frase_debug) {
        if (obtido >= esperado) {

        } else {
            throw new RuntimeException("#VERIFICADOR " + obtido + " != " + esperado );
        }
    }


    public static void MINIMO(int obtido, int esperado, String frase_debug) {
        if (obtido >= esperado) {

        } else {
            throw new RuntimeException("#VERIFICADOR " + obtido + " != " + esperado + " ->> " + frase_debug);
        }
    }


    public static void DEVE_SER_VERDADEIRO(boolean obtido, String eErro) {
        if (obtido) {

        } else {
            throw new RuntimeException("#VERIFICADOR :: " + eErro);
        }
    }
}

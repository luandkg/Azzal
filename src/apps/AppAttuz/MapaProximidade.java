package apps.AppAttuz;

public class MapaProximidade {

    public static boolean isValido(int x, int y, int eLargura, int eAltura) {
        boolean ret = false;

        if (x >= 0 && x < eLargura && y >= 0 && y < eAltura) {
            ret = true;
        }

        return ret;
    }

    public static boolean isValido4Lados(int x, int y, int eLargura, int eAltura) {

        boolean esquerda = isValido(x - 1, y, eLargura, eAltura);
        boolean direita = isValido(x + 1, y, eLargura, eAltura);
        boolean acima = isValido(x, y, eLargura, eAltura - 1);
        boolean abaixo = isValido(x, y, eLargura, eAltura + 1);

        boolean ret = false;
        if (esquerda && direita && acima && abaixo) {
            ret = true;
        }

        return ret;
    }

}

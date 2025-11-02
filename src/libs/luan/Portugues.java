package libs.luan;

public class Portugues {

    public static String sim(boolean logico) {
        return isOK(logico, "SIM", "NÃO");
    }

    public static String isOK(boolean condicao, String verdade, String falso) {
        String ret = "";
        if (condicao) {
            ret = verdade;
        } else {
            ret = falso;
        }

        return ret;
    }

    public static String VALIDAR(boolean condicao,String valido,String nao_valido){
        if(condicao){
            return valido;
        }else{
            return nao_valido;
        }
    }

    public static String singular_ou_plural(int valor, String singular, String plural) {
        if (valor > 1) {
            return plural;
        }

        return singular;
    }

    public static String singular_ou_plural(long valor, String singular, String plural) {
        if (valor > 1) {
            return plural;
        }

        return singular;
    }
}

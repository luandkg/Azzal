package libs.luan;

public class Portugues {

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

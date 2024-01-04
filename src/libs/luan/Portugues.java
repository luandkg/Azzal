package libs.luan;

public class Portugues {



    public static String singular_ou_plural(int valor,String singular,String plural){
        if(valor>1){
            return plural;
        }

        return singular;
    }

    public static String singular_ou_plural(long valor,String singular,String plural){
        if(valor>1){
            return plural;
        }

        return singular;
    }
}

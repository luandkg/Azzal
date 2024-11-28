package libs.zetta;

import libs.entt.Entidade;
import libs.luan.Lista;

public class AZVerificador {


    public static final String TEXTO_NAO_CONTER_NUMEROS = "NUMEROS";
    public static final String TEXTO_FORMATO_FRASE = "FRASE";


    public static Entidade VALOR_UNICO( ){
        return ZettaTabelas.CRIAR_VERIFICADOR("Valor::Tipo", "UNICO");
    }

    public static Entidade TEXTO_TAMANHO_MAIOR(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Texto::TamanhoMaior", quantidade);
    }

    public static Entidade TEXTO_TAMANHO_MENOR(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Texto::TamanhoMenor", quantidade);
    }

    public static Entidade TEXTO_TAMANHO_IGUAL(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Texto::TamanhoIgual", quantidade);
    }

    public static Entidade TEXTO_TAMANHO_MAIOR_OU_IGUAL(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Texto::MaiorIgual", quantidade);
    }

    public static Entidade TEXTO_TAMANHO_MENOR_OU_IGUAL(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Texto::MenorIgual", quantidade);
    }

    public static Entidade TEXTO_ENUMERADOR(Lista<String> opcoes){
        return ZettaTabelas.CRIAR_VERIFICADOR_CONTEM("Texto::Existe", opcoes);
    }

    public static Entidade TEXTO_FORMATO(String eFormato){
        return ZettaTabelas.CRIAR_VERIFICADOR("Texto::Formato", eFormato);
    }

    public static Entidade TEXTO_NAO_CONTER(String eNao){
        return ZettaTabelas.CRIAR_VERIFICADOR("Texto::NaoConter", eNao);
    }


    // INTEIRO

    public static Entidade INTEIRO_MAIOR(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Inteiro::TamanhoMaior", quantidade);
    }

    public static Entidade INTEIRO_MENOR(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Inteiro::TamanhoMenor", quantidade);
    }

    public static Entidade INTEIRO_IGUAL(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Inteiro::TamanhoIgual", quantidade);
    }

    public static Entidade INTEIRO_MAIOR_OU_IGUAL(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Inteiro::MaiorIgual", quantidade);
    }

    public static Entidade INTEIRO_MENOR_OU_IGUAL(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Inteiro::MenorIgual", quantidade);
    }


    // REAL

    public static Entidade REAL_MAIOR(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Real::TamanhoMaior", quantidade);
    }

    public static Entidade REAL_MENOR(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Real::TamanhoMenor", quantidade);
    }

    public static Entidade REAL_IGUAL(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Real::TamanhoIgual", quantidade);
    }

    public static Entidade REAL_MAIOR_OU_IGUAL(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Real::MaiorIgual", quantidade);
    }

    public static Entidade REAL_MENOR_OU_IGUAL(int quantidade){
        return ZettaTabelas.CRIAR_VERIFICADOR("Real::MenorIgual", quantidade);
    }
}

package libs.fts;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Strings;

public class FTS {


    public static Lista<Entidade> PARSER(String texto){

        Lista<Entidade> fts_dados = ENTT.CRIAR_LISTA();

        String caracteres_proibidos = " .,:()[]-{}\\/+&*@#$";

        Lista<String> palavras_texto = new Lista<String>();
        for (String palavra : Strings.DIVIDIR_POR_QUALQUER_UM_DESSES(texto, caracteres_proibidos)) {

            palavra=Strings.CAIXA_ALTA(palavra);
            palavra=Strings.TRIM(palavra);
            palavra=Strings.PURIFICAR(palavra,caracteres_proibidos);

            if (!palavra.isEmpty() ) {
                palavras_texto.adicionar(palavra);
            }
        }

        for (String palavra : palavras_texto) {
            Entidade e_palavra = ENTT.GET_SEMPRE(fts_dados, "Palavra", palavra);
            e_palavra.at("Ranking", Strings.CONTAGEM_EM_LISTA(palavras_texto, palavra));
        }

        return fts_dados;
    }

    public static String PARSER_TO_DOCUMENTO(String texto) {
        return TO_DOCUMENTO(FTS.PARSER((texto)));
    }


        public static String TO_DOCUMENTO(Lista<Entidade> fts_dados){
        return Strings.LINEARIZAR(ENTT.TO_DOCUMENTO(fts_dados));
    }

    public static Lista<Entidade> GET_DOCUMENTO(String fts_dados_brutos){
       return ENTT.PARSER(fts_dados_brutos);
    }

    public static void RETIRAR_PALAVRAS(Lista<Entidade> fts_dados, Lista<String> palavras){

        Lista<Entidade> retirar = new Lista<Entidade>();

        for(Entidade fts_item : fts_dados){

            if(palavras.existe(Strings.IGUALAVEL(),fts_item.at("Palavra"))){
                retirar.adicionar(fts_item);
            }

        }

        for(Entidade retire : retirar){
            fts_dados.remover(retire);
        }

    }

    public static void ORDENAR_MAIOR_RANKING(Lista<Entidade> fts_dados){
        ENTT.ORDENAR_INTEIRO_DECRESCENTE(fts_dados, "Ranking");
    }

    public static void ORDENAR_MENOR_RANKING(Lista<Entidade> fts_dados){
        ENTT.ORDENAR_INTEIRO(fts_dados, "Ranking");
    }
}

package apps.app_humanidade.idiomas;

import apps.app_humanidade.Idioma;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Strings;

public class IdiomaPlaque extends Idioma {

    public IdiomaPlaque() {
        super("Plaque");


        String inicial_consoantes = "bkdghlpw";
        String inicial_vogal = "aeo";

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(inicial_consoantes),Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_inicial(lexema);
        }


        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA("bkp"),Strings.CARACTER_TO_LISTA("lr"),Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_inicial(lexema);
        }



        String interno_consoantes = "bkdghlpw";
        String interno_vogal = "aeo";

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(interno_consoantes),Strings.CARACTER_TO_LISTA(interno_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_interno(lexema);
        }

        String terminal_consoantes = "bkdghlpw";
        String terminal_vogal = "aeo";

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes),Strings.CARACTER_TO_LISTA(terminal_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_terminal(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes),Strings.CARACTER_TO_LISTA(terminal_vogal),Strings.CARACTER_TO_LISTA("btz"), Strings.CONCATENADOR())){
            adicionar_lexema_terminal(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Lista.CRIAR("qu"),Strings.CARACTER_TO_LISTA(terminal_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_terminal(lexema);
        }


        setProbabilidadeInterna(70);
        setProbabilidadeTerminacao(40);
        setQuantidadeInterna(3);
        setTamanhoMinimo(4);

    }

}

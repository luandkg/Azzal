package apps.app_humanidade.idiomas;

import apps.app_humanidade.Idioma;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Strings;

public class IdiomaInmeb extends Idioma {

    public IdiomaInmeb() {
        super("Inmeb");


        String inicial_consoantes = "aeiou";
        String inicial_vogal = "nzcxl";

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(inicial_consoantes),Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_inicial(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Lista.CRIAR("ch","sh","bh"),Strings.CARACTER_TO_LISTA("aeiou"), Strings.CONCATENADOR())){
            adicionar_lexema_inicial(lexema);
        }


         inicial_consoantes = "mncxdfpz";
         inicial_vogal = "aeiou";

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(inicial_consoantes),Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_inicial(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Lista.CRIAR("ts","ca","po"),Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_inicial(lexema);
        }

        String interno_consoantes = "mnckxdfpz";
        String interno_vogal = "aeiou";

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(interno_consoantes),Strings.CARACTER_TO_LISTA(interno_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_interno(lexema);
        }

        String terminal_consoantes = "mncxdfpz";
        String terminal_vogal = "aeiou";

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes),Strings.CARACTER_TO_LISTA(terminal_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_terminal(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes),Strings.CARACTER_TO_LISTA(terminal_vogal),Strings.CARACTER_TO_LISTA("btz"), Strings.CONCATENADOR())){
            adicionar_lexema_terminal(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_vogal),Lista.CRIAR("an","ez","ij"), Strings.CONCATENADOR())){
            adicionar_lexema_terminal(lexema);
        }


        setProbabilidadeInterna(60);
        setProbabilidadeTerminacao(50);
        setQuantidadeInterna(3);
        setTamanhoMinimo(4);

    }

}

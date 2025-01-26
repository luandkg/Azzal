package apps.app_humanidade.idiomas;

import apps.app_humanidade.Idioma;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Strings;

public class IdiomaRequiz extends Idioma {

    public IdiomaRequiz() {
        super("Requiz");


        String inicial_consoantes = "rtmvzwpg";
        String inicial_vogal = "eiou";

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(inicial_consoantes),Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_inicial(lexema);
        }


        for(String lexema : Matematica.COMBINACOES(Lista.CRIAR("qu","tr","pl"),Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_inicial(lexema);
        }



        String interno_consoantes = "rtmvzwpg";
        String interno_vogal = "eiou";

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(interno_consoantes),Strings.CARACTER_TO_LISTA(interno_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_interno(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Lista.CRIAR("qu","tr","pl"),Strings.CARACTER_TO_LISTA(interno_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_interno(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(interno_consoantes),Strings.CARACTER_TO_LISTA(interno_vogal),Lista.CRIAR("z","m","tch"), Strings.CONCATENADOR())){
            adicionar_lexema_interno(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Lista.CRIAR("qu","tr","pl"),Strings.CARACTER_TO_LISTA(interno_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_interno(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Lista.CRIAR("qu","tr","pl"),Strings.CARACTER_TO_LISTA(interno_vogal),Lista.CRIAR("z","m","tch"), Strings.CONCATENADOR())){
            adicionar_lexema_interno(lexema);
        }

        for (String lexema : Matematica.COMBINACOES(Lista.CRIAR("gg","tt"), Strings.CARACTER_TO_LISTA(interno_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_so_interno(lexema);
        }

        String terminal_consoantes = "rtmvzwpg";
        String terminal_vogal = "eiou";

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes),Strings.CARACTER_TO_LISTA(terminal_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_terminal(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes),Strings.CARACTER_TO_LISTA(terminal_vogal),Lista.CRIAR("z","m","tch"), Strings.CONCATENADOR())){
            adicionar_lexema_terminal(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Lista.CRIAR("qu","tr","pl"),Strings.CARACTER_TO_LISTA(terminal_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_terminal(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Lista.CRIAR("qu","tr","pl"),Strings.CARACTER_TO_LISTA(terminal_vogal),Lista.CRIAR("z","m","tch"), Strings.CONCATENADOR())){
            adicionar_lexema_terminal(lexema);
        }


        setProbabilidadeInicial(70);
        setProbabilidadeInterna(80);
        setProbabilidadeTerminacao(50);
        setQuantidadeInterna(3);
        setTamanhoMinimo(4);

    }

}

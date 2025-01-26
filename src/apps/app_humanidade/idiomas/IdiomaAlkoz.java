package apps.app_humanidade.idiomas;

import apps.app_humanidade.Idioma;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Strings;

public class IdiomaAlkoz extends Idioma {

    public IdiomaAlkoz() {
        super("Alkoz");


        String inicial_consoantes = "lmgb";
        String inicial_vogal = "aeuo";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CARACTER_TO_LISTA(inicial_consoantes), Strings.CONCATENADOR())) {
            adicionar_lexema_inicial(lexema);
        }

        inicial_consoantes = "bgdzrtmlk";
        inicial_vogal = "aeuo";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(inicial_consoantes), Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_inicial(lexema);
        }


        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(inicial_consoantes), Strings.CARACTER_TO_LISTA(inicial_vogal), Lista.CRIAR("a", "o"), Strings.CONCATENADOR())) {
            adicionar_lexema_inicial(lexema);
        }


        String interno_consoantes = "bgdzrtmlk";
        String interno_vogal = "aeuo";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(interno_consoantes), Strings.CARACTER_TO_LISTA(interno_vogal), Lista.CRIAR("z", "l", "m", "r"), Strings.CONCATENADOR())) {
            adicionar_lexema_interno(lexema);
        }

        for (String lexema : Matematica.COMBINACOES(Lista.CRIAR("ll","nn"), Strings.CARACTER_TO_LISTA(interno_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_so_interno(lexema);
        }


        String terminal_consoantes = "bgdzrtmlk";
        String terminal_vogal = "aeuo";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes), Strings.CARACTER_TO_LISTA(terminal_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_terminal(lexema);
        }

        terminal_consoantes = "bgdzrtmlk";
        terminal_vogal = "aeuo";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes), Strings.CARACTER_TO_LISTA(terminal_vogal), Lista.CRIAR("a", "o"), Strings.CONCATENADOR())) {
            adicionar_lexema_terminal(lexema);
        }


        setProbabilidadeInterna(75);
        setProbabilidadeTerminacao(30);
        setQuantidadeInterna(3);
        setTamanhoMinimo(4);

    }

}

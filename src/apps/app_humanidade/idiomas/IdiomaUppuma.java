package apps.app_humanidade.idiomas;

import apps.app_humanidade.Idioma;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Strings;

public class IdiomaUppuma extends Idioma {

    public IdiomaUppuma() {
        super("Uppuma");


        String inicial_consoantes = "zgjtrwpk";
        String inicial_vogal = "aou";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CARACTER_TO_LISTA(inicial_consoantes), Strings.CONCATENADOR())) {
            adicionar_lexema_inicial(lexema);
        }

        inicial_consoantes = "zgjtrwpk";
        inicial_vogal = "aou";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(inicial_consoantes), Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_inicial(lexema);
        }


        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(inicial_consoantes), Strings.CARACTER_TO_LISTA(inicial_vogal), Lista.CRIAR("a", "o"), Strings.CONCATENADOR())) {
            adicionar_lexema_inicial(lexema);
        }

        for (String lexema : Matematica.COMBINACOES(Lista.CRIAR("ma", "te"), Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_inicial(lexema);
        }


        String interno_consoantes = "zgjtrwpk";
        String interno_vogal = "aou";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(interno_consoantes), Strings.CARACTER_TO_LISTA(interno_vogal), Lista.CRIAR("g", "t", "j", "k"), Strings.CONCATENADOR())) {
            adicionar_lexema_interno(lexema);
        }


        String terminal_consoantes = "zgjtrwpkm";
        String terminal_vogal = "aou";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes), Strings.CARACTER_TO_LISTA(terminal_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_terminal(lexema);
        }

        terminal_consoantes = "zgjtrwpkm";
        terminal_vogal = "aou";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes), Strings.CARACTER_TO_LISTA(terminal_vogal), Lista.CRIAR("u", "o"), Strings.CONCATENADOR())) {
            adicionar_lexema_terminal(lexema);
        }


        setProbabilidadeInterna(75);
        setProbabilidadeTerminacao(30);
        setQuantidadeInterna(3);
        setTamanhoMinimo(4);

    }

}

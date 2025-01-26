package apps.app_humanidade.idiomas;

import apps.app_humanidade.Idioma;
import libs.luan.Matematica;
import libs.luan.Strings;

public class IdiomaTraddes extends Idioma {

    public IdiomaTraddes() {
        super("Traddes");


        String inicial_consoantes = "tmvzsl";
        String inicial_vogal = "aei";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(inicial_consoantes), Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_inicial(lexema);
        }

        inicial_consoantes = "r";
        inicial_vogal = "aio";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA("td"), Strings.CARACTER_TO_LISTA(inicial_consoantes), Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_inicial(lexema);
            for (String mais : Strings.CARACTER_TO_LISTA("dg")) {
                adicionar_lexema_inicial(lexema + mais);
            }
        }


        String interno_consoantes = "dmtzslbgpz";
        String interno_vogal = "aeio";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(interno_consoantes), Strings.CARACTER_TO_LISTA(interno_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_interno(lexema);
        }

        String terminal_consoantes = "dmtzslbgpz";
        String terminal_vogal = "aeio";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes), Strings.CARACTER_TO_LISTA(terminal_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_terminal(lexema);
        }


        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes), Strings.CARACTER_TO_LISTA(terminal_vogal), Strings.CARACTER_TO_LISTA("sm"), Strings.CONCATENADOR())) {
            adicionar_lexema_terminal(lexema);
        }


        setProbabilidadeInterna(90);
        setProbabilidadeTerminacao(40);
        setQuantidadeInterna(3);
        setTamanhoMinimo(4);

    }

}

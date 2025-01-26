package apps.app_humanidade.idiomas;

import apps.app_humanidade.Idioma;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Strings;


public class IdiomaMokkom extends Idioma {

    public IdiomaMokkom() {
        super("Mokkom");


        String inicial_consoantes = "zmkpg";
        String inicial_vogal = "aeo";


        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA("aeiou"), Strings.CARACTER_TO_LISTA(inicial_consoantes), Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_inicial(lexema);
        }

        for (String lexema : Matematica.COMBINACOES( Strings.CARACTER_TO_LISTA(inicial_consoantes), Strings.CARACTER_TO_LISTA(inicial_vogal), Lista.CRIAR("k","z"), Strings.CONCATENADOR())) {
            adicionar_lexema_inicial(lexema);
        }

        String interno_consoantes = "cxzpfk";
        String interno_vogal = "aio";


        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(interno_consoantes), Strings.CARACTER_TO_LISTA(interno_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_interno(lexema);
        }

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(interno_consoantes), Strings.CARACTER_TO_LISTA(interno_vogal), Strings.CARACTER_TO_LISTA("nszl"), Strings.CONCATENADOR())) {
            adicionar_lexema_interno(lexema);
        }


        String terminal_consoantes = "cxzpfk";
        String terminal_vogal = "aio";

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes), Strings.CARACTER_TO_LISTA(terminal_vogal), Strings.CONCATENADOR())) {
            adicionar_lexema_terminal(lexema);
        }

        for (String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes), Strings.CARACTER_TO_LISTA(terminal_vogal), Strings.CARACTER_TO_LISTA("nzlcm"), Strings.CONCATENADOR())) {
            adicionar_lexema_terminal(lexema);
        }


        setProbabilidadeInterna(70);
        setProbabilidadeTerminacao(30);
        setQuantidadeInterna(2);
        setTamanhoMinimo(4);

    }

}
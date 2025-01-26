package apps.app_humanidade.idiomas;

import apps.app_humanidade.Idioma;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Strings;

public class IdiomaDommus extends Idioma {

    public IdiomaDommus() {
        super("Dommus");


        String inicial_consoantes = "bkjlmnpsvd";
        String inicial_vogal = "eiou";

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(inicial_consoantes),Strings.CARACTER_TO_LISTA(inicial_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_inicial(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(inicial_consoantes),Strings.CARACTER_TO_LISTA(inicial_vogal), Lista.CRIAR("m","s"), Strings.CONCATENADOR())){
            adicionar_lexema_inicial(lexema);
        }

        String interno_consoantes = "bkjlmnpsvd";
        String interno_vogal = "eiou";

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(interno_consoantes),Strings.CARACTER_TO_LISTA(interno_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_interno(lexema);
        }

        String terminal_consoantes = "bkjlmnpsvd";
        String terminal_vogal = "aeu";

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes),Strings.CARACTER_TO_LISTA(terminal_vogal), Strings.CONCATENADOR())){
            adicionar_lexema_terminal(lexema);
        }

        for(String lexema : Matematica.COMBINACOES(Strings.CARACTER_TO_LISTA(terminal_consoantes),Strings.CARACTER_TO_LISTA(terminal_vogal), Lista.CRIAR("m","s"), Strings.CONCATENADOR())){
            adicionar_lexema_terminal(lexema);
        }

        setProbabilidadeInterna(80);
        setProbabilidadeTerminacao(50);
        setQuantidadeInterna(3);
        setTamanhoMinimo(3);

    }

}

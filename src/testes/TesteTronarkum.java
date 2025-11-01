package testes;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Par;
import libs.luan.Strings;
import libs.luan.fmt;
import libs.mitestum.Mitestum;
import libs.tempo.Calendario;
import libs.tronarko.Harremplattor;
import libs.tronarko.Signos;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;

public class TesteTronarkum {


    public static Lista<Entidade> MAPEAR_SIGNOS(Tozte tozte_corrente){

        Lista<Entidade> dados = ENTT.CRIAR_LISTA();

        for(Signos signo : Signos.listar()){

            Entidade e_signo = ENTT.CRIAR_EM(dados);
            e_signo.at("Signo",signo.toString());
            e_signo.at("Tozte",tozte_corrente.getTextoZerado());

            Lista<Par<String, String>> infos = Harremplattor.get(signo, tozte_corrente);

            for(Par<String,String> info : infos){
                //fmt.print("{} - {}",info.getChave(),info.getValor());
                e_signo.at(info.getChave(),info.getValor());
            }

        }

        return dados;
    }

    public static void teste_signos() {

        Signos signo_corrente = Signos.TIGRE;

        Lista<Entidade> dados_tronarkos = ENTT.CRIAR_LISTA();

        for (int tronarko = 7000; tronarko <= 7050; tronarko++) {

            Tozte tozte_corrente = new Tozte(1, 1, tronarko);


            Lista<Entidade> dados = ENTT.CRIAR_LISTA();

            for (int s = 1; s <= 500; s++) {

                Entidade e = ENTT.CRIAR_EM(dados);
                e.at("Tozte", tozte_corrente.getTextoZerado());

                for (Par<String, String> item : Harremplattor.get(signo_corrente, tozte_corrente)) {
                    //   fmt.print("{} - > {}", item.getChave(), item.getValor());
                    e.at(item.getChave(), item.getValor());
                }

                tozte_corrente = tozte_corrente.adicionar_Superarko(1);
            }


            String analisando_campo = "Sentimento";
         //   analisando_campo = "Direção";

            ENTT.EXIBIR_TABELA(dados);
            ENTT.EXIBIR_TABELA(ENTT.DISPERSAO(dados, analisando_campo));
            fmt.print("{} :: {}", analisando_campo,ENTT.CONTAGEM_UNICOS(dados, analisando_campo));

            Lista<Entidade> sentimento_comum = ENTT.GET_ITEM_MAIOR_COM_REPETICAO(ENTT.DISPERSAO(dados, analisando_campo), "Quantidade");
            Lista<Entidade> sentimento_raro = ENTT.GET_ITEM_MENOR_COM_REPETICAO(ENTT.DISPERSAO(dados, analisando_campo), "Quantidade");


            fmt.print("{} Comum :: {} -> {}", analisando_campo,ENTT.GET_PRIMEIRO(sentimento_comum).at("Quantidade"), Strings.LISTA_TO_TEXTO_LINHA(ENTT.VALORES_DE(sentimento_comum, analisando_campo)));
            fmt.print("{} Raro  :: {} -> {}", analisando_campo,ENTT.GET_PRIMEIRO(sentimento_raro).at("Quantidade"), Strings.LISTA_TO_TEXTO_LINHA(ENTT.VALORES_DE(sentimento_raro, analisando_campo)));

            Entidade e_geral = ENTT.CRIAR_EM(dados_tronarkos);
            e_geral.at("Tronarko",tronarko);
            e_geral.at("Comum",ENTT.GET_PRIMEIRO(sentimento_comum).at("Quantidade") + " -> "+Strings.LISTA_TO_TEXTO_LINHA(ENTT.VALORES_DE(sentimento_comum, analisando_campo)));
            e_geral.at("Raro",ENTT.GET_PRIMEIRO(sentimento_raro).at("Quantidade") + " -> "+Strings.LISTA_TO_TEXTO_LINHA(ENTT.VALORES_DE(sentimento_raro, analisando_campo)));


        }

        ENTT.EXIBIR_TABELA(dados_tronarkos);

    }

    public static void TEST(){

        Mitestum teste_tozte = new Mitestum("Tozte");

        teste_tozte.adicionar_igualdade(Tronarko.getData("02/03/2025").getTextoZerado(),"05/08/7004");
        teste_tozte.adicionar_maior_igual(Tronarko.getData("02/03/2025").getTronarko(),7000);
        teste_tozte.adicionar_igualdade(Tronarko.getData("02/03/2025").getHiperarko(),8);

        teste_tozte.validar_descritivo();

    }
}

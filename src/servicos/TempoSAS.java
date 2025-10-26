package servicos;

import libs.bibliotecas.JSONDemanda;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.tempo.Calendario;
import libs.tempo.Data;
import libs.tronarko.Tronarko;

public class TempoSAS {

    public static Entidade GET() {

        Entidade e_geral = new Entidade();

        String API_SOL = "https://www.sunrise-and-sunset.com/pt/sun/brasil/brasilia";

        Opcional<String> dados = Internet.GET_PAGINA_HTML_TIMEOUT(API_SOL);

        if (dados.isOK()) {

            boolean valido = false;

            String nome_corrente = "";

            Lista<Entidade> itens = new Lista<Entidade>();

            for (String linha : Strings.DIVIDIR_LINHAS(dados.get().trim())) {
                linha = linha.trim();
                if (linha.startsWith("<th class=\"col-xs-6\">Data atual</th>")) {
                    valido = true;
                } else if (linha.startsWith("<td><span id=\"currentAltitude\">")) {
                    valido = false;
                }
                if (valido) {
                    //  if(linha.contains("id=\"")){
                    //  String item_nome = Strings.GET_ENTRE_ASPAS(Strings.GET_DEPOIS(linha," "),1 ) ;
                    //String item_valor = Strings.GET_ATE( Strings.GET_DEPOIS(Strings.GET_DEPOIS(linha,">"),">"),"<");

                    // fmt.print("{} = {}",item_nome,item_valor);

                    String nome_bruto = Strings.RETIRAR_ESPACOS(linha);

                    nome_bruto = nome_bruto.replace(" class=\"col-xs-6\"", "");

                    if (nome_bruto.startsWith("<th>") && nome_bruto.endsWith("</th>")) {
                        nome_corrente = linha;
                    }

                    if (nome_bruto.endsWith("</td>")) {

                        String item_valor = "";
                        String item_bruto = Strings.RETIRAR_ESPACOS(linha);

                        if (linha.endsWith("</td>")) {
                            item_valor = Strings.GET_REVERSO_DEPOIS_DE(linha, "<").trim();
                        }

                        item_valor = item_valor.replace("&deg;", "");
                        item_valor = item_valor.trim();

                        String tipo = "";

                        if (item_valor.endsWith("</span>")) {
                            item_valor = item_valor.replace("<td><span ", "");
                            item_valor = item_valor.replace("id=\"", "");

                            tipo = Strings.GET_ATE(item_valor, "\"");
                            item_valor = Strings.GET_DEPOIS(item_valor, ">");
                            item_valor = Strings.GET_ATE(item_valor, "<");
                        }

                        Entidade e_item = ENTT.CRIAR_EM(itens);
                        e_item.at("Nome", Strings.GET_ENTRE_ISSO(nome_corrente, ">", "<"));
                        e_item.at("Tipo", tipo);
                        e_item.at("Valor", item_valor);

                    }
                    // }
                }
            }

            if (ENTT.COLETAR_EXISTE(itens, "Tipo", "currentDate")) {
                ENTT.GET_SEMPRE(itens, "Tipo", "currentDate").at("Nome", "Data");

                Entidade info = ENTT.GET_SEMPRE(itens, "Tipo", "currentDate");
                String valor = info.at("Valor");

                String data = Calendario.GET_DATA_DE_PORTUGUES(valor);
                info.at("Valor", data);

            }

            if (ENTT.COLETAR_EXISTE(itens, "Nome", "Nascer do sol hoje")) {
                ENTT.GET_SEMPRE(itens, "Nome", "Nascer do sol hoje").at("Tipo", "sunrise");
            }

            if (ENTT.COLETAR_EXISTE(itens, "Nome", "Pôr do sol hoje")) {
                ENTT.GET_SEMPRE(itens, "Nome", "Pôr do sol hoje").at("Tipo", "sunset");
            }


            Entidade e_cidade = ENTT.CRIAR_EM(itens);
            e_cidade.at("Nome", "Cidade");
            e_cidade.at("Tipo", "city");
            e_cidade.at("Valor", "BRASÍLIA");

            ENTT.EXIBIR_TABELA(itens);


            if (ENTT.COLETAR_EXISTE(itens, "Tipo", "sunset")) {
                if (ENTT.COLETAR_EXISTE(itens, "Tipo", "sunrise")) {
                    if (ENTT.COLETAR_EXISTE(itens, "Tipo", "timezone")) {

                        e_geral.at("Cidade", ENTT.GET_SEMPRE(itens, "Tipo", "city").at("Valor"));
                        e_geral.at("Fuso", ENTT.GET_SEMPRE(itens, "Tipo", "timezone").at("Valor"));
                        e_geral.at("Tozte", Tronarko.getData(ENTT.GET_SEMPRE(itens, "Tipo", "currentDate").at("Valor")).getTextoZerado());
                        e_geral.at("Hazde", Tronarko.getHora(ENTT.GET_SEMPRE(itens, "Tipo", "currentTime").at("Valor") + ":00").getTextoSemUzzonZerado());
                        e_geral.at("Nascer", Tronarko.getHora(ENTT.GET_SEMPRE(itens, "Tipo", "sunrise").at("Valor") + ":00").getTextoSemUzzonZerado());
                        e_geral.at("Por", Tronarko.getHora(ENTT.GET_SEMPRE(itens, "Tipo", "sunset").at("Valor") + ":00").getTextoSemUzzonZerado());

                    }
                }
            }

            ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(e_geral));


        }

        return e_geral;
    }

    public static Lista<Entidade> GET_PREVISAO() {

        String API_SOL = "https://www.sunrise-and-sunset.com/pt/sun/brasil/brasilia";

        Opcional<String> dados = Internet.GET_PAGINA_HTML_TIMEOUT(API_SOL);

        Lista<Entidade> infos = ENTT.CRIAR_LISTA();

        if (dados.isOK()) {

            Entidade e_item = new Entidade();

            int att = 0;

            for (String linha : Strings.DIVIDIR_LINHAS(dados.get().trim())) {

                linha = linha.trim();
                if (linha.startsWith("<time datetime=") || (linha.contains("Nascer e pôr do sol") && linha.contains("</span></time>"))) {
                    //  fmt.print("{} - {}", ii, linha);

                    if ((linha.contains("Nascer e pôr do sol") && linha.contains("</span></time>"))) {

                        String data = Strings.GET_DEPOIS(linha, " ");
                        data = Strings.GET_DEPOIS(data, " ");
                        data = Strings.GET_DEPOIS(data, " ");
                        data = Strings.GET_ATE(data, ">");
                        data = Strings.GET_ENTRE_ASPAS(data, 1);
                        data = data.replace("Nascer e pôr do sol Brasilia, ", "");

                        e_item = ENTT.CRIAR_EM(infos);
                        e_item.at("Data", Calendario.GET_DATA_DE_PORTUGUES(data));
                        e_item.at("Cidade", "BRASÍLIA");

                        att = 1;
                    } else {
                        e_item.at("AT" + att, Strings.GET_ENTRE_ISSO(linha, ">", "<"));
                        att += 1;
                    }

                }

            }

            ENTT.AT_MUDAR_NOME(infos, "AT1", "Nascer");
            ENTT.AT_MUDAR_NOME(infos, "AT2", "Por");
            ENTT.AT_MUDAR_NOME(infos, "AT3", "Duracao");

            for (Entidade e : infos) {
                e.at("Data", Tronarko.getData(e.at("Data")).getTextoZerado());
                e.at("Nascer", Tronarko.getHora(e.at("Nascer") + ":00").getTextoSemUzzonZerado());
                e.at("Por", Tronarko.getHora(e.at("Por") + ":00").getTextoSemUzzonZerado());
                e.at("Obtido", Tronarko.getTronAgora().getTextoZerado());
            }

            ENTT.AT_ALTERAR_NOME(infos, "Data", "Tozte");
            ENTT.ATRIBUTO_REMOVER(infos, "Duracao");
            ENTT.EXIBIR_TABELA(infos);


        }


        return infos;
    }

    public static Lista<Entidade> GET_PREVISAO_DO_TEMPO() {

        Lista<Entidade> infos = ENTT.CRIAR_LISTA();

        String API_SOL = "https://www.climatempo.com.br/previsao-do-tempo/15-dias/cidade/61/brasilia-df";

        Opcional<String> dados = Internet.GET_PAGINA_HTML_TIMEOUT(API_SOL);

        Entidade e_recente = new Entidade();

        if (dados.isOK()) {

            boolean valido = false;

            Lista<String> linhas = Strings.DIVIDIR_LINHAS(dados.get());

            for (Indexado<String> index_linha : Indexamento.indexe(linhas)) {
                String linha = index_linha.get().trim();
                if (linha.contains("Botao_dia_1_")) {
                    valido = true;
                } else if (linha.contains("Botao_dia_15")) {
                    valido = false;
                }

                if (valido) {
                    //  fmt.print("{}", linha);

                    if (linha.contains("Botao_dia_") && linha.contains("dots ") && linha.contains("timeline")) {
                        e_recente = ENTT.CRIAR_EM(infos, "ID", Strings.GET_ENTRE_ASPAS(linha, 3).replace("Botao_dia_", "").replace("_mais_opcoes_timeline_15_dias", ""));
                    }

                    if (linha.contains("lazyload")) {

                        String embaixo = linhas.get(index_linha.index() + 1);
                        embaixo = Strings.GET_ENTRE_ISSO(embaixo, ">", "<").trim();

                        String valor = linhas.get(index_linha.index());
                        valor = Strings.GET_ENTRE_ASPAS(valor, 7);

                        if (embaixo.length() > 3 && !embaixo.contains(" ")) {
                            e_recente.at(embaixo, valor);
                        }


                    }

                    if (linha.contains("sprite-sunshine-sunrise")) {
                        e_recente.at("Sol", linhas.get(index_linha.index() + 1));
                    }

                    if (linha.contains("date-inside-circle")) {
                        e_recente.at("TempoDia", linhas.get(index_linha.index() + 1));
                    }

                    if (linha.contains("wrapper-chart")) {
                        String novos_dados = Strings.GET_ENTRE_ASPAS(linha, 5);
                        novos_dados = novos_dados.replace("&quot;", "\"");


                        DKG parser = JSONDemanda.JSON_TO_DKG(novos_dados);

                        for (DKGObjeto obj : parser.getObjetos()) {
                            e_recente.at("Data", Strings.GET_ATE(obj.identifique("date").getValor(), "T"));
                        }

                        e_recente.at("Dados", novos_dados);

                    }


                }
            }

        }

        Lista<Entidade> dados_validos = new Lista<Entidade>();
        for (Entidade e : infos) {
            if (e.isValido("Madrugada") && e.isValido("Manhã") && e.isValido("Tarde")&& e.isValido("Noite")&& e.isValido("Data")) {

                if(e.atributo_existe("Dados") && e.atributo_existe("Sol")){
                    e.trocar_posicionalmente("Dados","Sol");
                }

                e.at_renomear("Manhã","Manha");


                e.at_renomear("Data","Tozte");
                e.at("Tozte", Data.toData(e.at("Tozte")).getTempoLegivel());
                e.at("Tozte", Tronarko.getData(e.at("Tozte")).getTextoZerado());

                if(e.at("Sol").contains(" ")){

                    String momento_alfa = Strings.DIVIDIR_ESPACOS(e.at("Sol")).get(0);
                    String momento_beta = Strings.DIVIDIR_ESPACOS(e.at("Sol")).get(2);

                    e.at("Sol",Tronarko.getHora(momento_alfa).getTextoZerado() + " - " + Tronarko.getHora(momento_beta).getTextoZerado());

                }

                //e.at("Tozte",Tronarko.getData(e.at("Tozte")).getTextoZerado());

                dados_validos.adicionar(e);
            }
        }

        ENTT.EXIBIR_TABELA(dados_validos);

        return dados_validos;
    }
}

package servicos;

import libs.dkg.DKG;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.tempo.Calendario;
import libs.tronarko.Tronarko;

public class IMDBServico {

    public static String TAG_INICIAR(String nome) {
        return "<" + nome;
    }

    public static String CSS_VALOR(String nome, String valor) {
        return nome + "=\"" + valor + "\"";
    }

    public static Lista<Entidade> VER_LANCAMENTOS() {

        String LINK_API = "https://www.imdb.com/calendar/?region=US&type=TV_EPISODE&ref_=rlm";

        Opcional<String> dados = Internet.GET_PAGINA_HTML_TIMEOUT(LINK_API);
        Lista<Entidade> infos = ENTT.CRIAR_LISTA();

        String json = "";

        for (String linha : Strings.DIVIDIR_LINHAS_TRIM(dados.get().trim())) {

            Lista<Entidade> tokens = ENTT.VALORES_TO_ENTIDADE_ORDENADO(Strings.DIVIDIR_POR_INCLUIDO(linha, "<"), "TokenID", "Token");
            for (Entidade token : tokens) {
                // if (token.at("Token").startsWith("<script id=\"__NEXT_DATA__\" type=\"application/json\">")) {
                //    json = token.at("Token");
                //  }

                if (token.at("Token").contains("type=\"application/json\">")) {
                    json = token.at("Token");
                }
            }

            ENTT.REMOVER_SE_COMECAR_COM(tokens, "Token", "<script id=\"__NEXT_DATA__\" type=\"application/json\">");

            String data_corrente = "";

            for (Entidade token : tokens) {

                if (token.at("Token").startsWith(TAG_INICIAR("h3")) && token.at("Token").contains(CSS_VALOR("class", "ipc-title__text"))) {

                    // fmt.print(">> {}",token.at("Token"));

                    String data_entrada = Strings.GET_REVERSO_ATE(token.at("Token"), ">");

                    Lista<String> data_valores = Strings.DIVIDIR_ESPACOS(data_entrada.replace(",", "").toUpperCase());

                    if (data_valores.getQuantidade() == 3) {
                        Lista.TROCAR(data_valores, 0, 1);
                    }

                    if (data_valores.getQuantidade() == 3) {
                        Lista.TROCAR(data_valores, 0, 2);
                        data_valores.set(1, Calendario.MES_INGLES_3_PARA_NUMERAL_ZERADO(data_valores.get(1)));
                    }

                    data_corrente = Strings.LISTA_TO_TEXTO_LINHA_COM_SEPARADOR(data_valores, "_");
                }

                if (token.at("Token").contains("8524")) {
                    //   ENTT.EXIBIR_TABELA(ENTT.SLICE(tokens, token.atInt("TokenID") - 30, token.atInt("TokenID") + 30));
                }

                if (token.at("Token").contains(CSS_VALOR("class", "ipc-metadata-list-summary-item__t")) && token.at("Token").contains(CSS_VALOR("tabindex", "0"))) {

                    Entidade serie = ENTT.CRIAR_EM(infos);

                    serie.at("IMDBID", "");
                    serie.at("Data", data_corrente);
                    serie.at("Serie", "");
                    serie.at("EpisodioNome", Strings.GET_REVERSO_ATE(token.at("Token"), ">"));

                    if (serie.at("EpisodioNome").endsWith(")")) {
                        serie.at("EpisodioNome", Strings.GET_REVERSO_DEPOIS_DE(serie.at("EpisodioNome"), "(").trim());
                    }


                    Lista<Entidade> subt = new Lista<Entidade>();

                    int tag_id = 0;
                    Lista<String> tags = new Lista<String>();


                    // Lista<Entidade> pre = ENTT.SLICE_REFERENCIADO(tokens, token.atInt("TokenID") ,25,25);

                    //  ENTT.SEQUENCIAR(pre,"ID",-25);
                    //   ENTT.TORNAR_PRIMEIRO(pre,"ID");
                    // ENTT.EXIBIR_TABELA_COM_NOME(pre,"@PRE");


                    for (Entidade subtoken : ENTT.SLICE(tokens, token.atInt("TokenID") + 1, token.atInt("TokenID") + 50)) {

                        if (subtoken.at("Token").contains(CSS_VALOR("class", "ipc-metadata-list-summary-item__t")) && subtoken.at("Token").contains(CSS_VALOR("tabindex", "0"))) {
                            break;
                        }

                        if (subtoken.at("Token").contains(CSS_VALOR("class", "ipc-metadata-list-summary-item__li")) && subtoken.at("Token").contains(CSS_VALOR("aria-disabled", "false"))) {
                            tag_id += 1;

                            if (tag_id == 1) {
                                serie.at("Serie", Strings.GET_REVERSO_ATE(subtoken.at("Token"), ">"));
                            } else if (tag_id == 2) {
                                serie.at("Temporada", Strings.GET_REVERSO_ATE(subtoken.at("Token"), ">"));
                            } else if (tag_id == 3) {
                                serie.at("Episodio", Strings.GET_REVERSO_ATE(subtoken.at("Token"), ">"));
                            } else {
                                tags.adicionar(Strings.GET_REVERSO_ATE(subtoken.at("Token"), ">"));
                            }

                        }

                        subt.adicionar(subtoken);
                    }

                    //  ENTT.EXIBIR_TABELA(subt);

                    serie.at("Tags", Strings.LISTA_TO_TEXTO_LINHA(tags));
                    serie.at("Exibido", "");

                    serie.at("TronObtido", Tronarko.getTronAgora().getTextoZerado());

                    serie.at("IMDBID", serie.at("Data") + "::" + serie.at("Serie") + "::" + serie.at("EpisodioNome") + "::" + serie.at("Temporada") + "::" + serie.at("Episodio"));
                    serie.at("IMDBID", serie.at("IMDBID").replace(" ", "_"));

                    Lista<String> pre_valores = Strings.DIVIDIR_ESPACOS(token.at("Token"));

                    serie.at("Link", "");

                    for (String serie_cabecalho : pre_valores) {
                        serie_cabecalho = serie_cabecalho.trim();
                        if (serie_cabecalho.startsWith("href")) {
                            serie.at("Link", Strings.GET_ENTRE_ASPAS(serie_cabecalho, 1));
                            if (serie.at("Link").startsWith("/title")) {
                                serie.at("Link", "https://www.imdb.com/pt" + serie.at("Link").replace("/?ref_=rlm", ""));
                            }
                        }
                    }


                    if (infos.getQuantidade() >= 5) {
                        //   break;
                    }

                }

            }

        }


        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.SLICE_PRIMEIROS(infos, 50), "SÉRIES");
        // ENTT.EXIBIR_TABELA_COM_TITULO(infos, "LANÇAMENTO :: EPISÓDIOS DE TV - ESTADOS UNIDOS");


        // fmt.print("JSON : {}",json);


        return infos;
    }

    public static Lista<Entidade> VER_EPISODIO(String LINK_API) {

        if (!LINK_API.endsWith("/")) {
            LINK_API += "/";
        }

        Opcional<String> dados = Internet.GET_PAGINA_HTML_TIMEOUT(LINK_API);
        Lista<Entidade> infos = ENTT.CRIAR_LISTA();

        String PARAMENTRO_DE_BUSCA = "<script type=\"application/ld+json\">{\"@context\":";

        for (String linha : Strings.DIVIDIR_LINHAS_TRIM(dados.get().trim())) {

            Lista<Entidade> tokens = ENTT.VALORES_TO_ENTIDADE_ORDENADO(Strings.DIVIDIR_POR_INCLUIDO(linha, "<"), "TokenID", "Token");


            if (linha.contains(PARAMENTRO_DE_BUSCA)) {

                //  ENTT.EXIBIR_BUSCA_REFERENCIADA_INICIA(tokens, "TokenID", "Token", PARAMENTRO_DE_BUSCA);

                Opcional<Entidade> op_token_json = ENTT.GET_PRIMEIRO_INICIA_COM(tokens, "Token", PARAMENTRO_DE_BUSCA);
                if (op_token_json.isOK()) {
                    // fmt.print("{}", op_token_json.get().at("Token"));

                    String json = op_token_json.get().at("Token");
                    DKG documento = JSONDemanda.JSON_TO_DKG(json);

                    // fmt.print("{}", documento.toDocumento());


                    ENTT.CRIAR_EM(infos, "Nome", "Exibido", "Valor", documento.unicoObjeto("JSON").identifique("datePublished").getValor().replace("-", "_"));
                    ENTT.CRIAR_EM(infos, "Nome", "Duracao", "Valor", documento.unicoObjeto("JSON").identifique("duration").getValor());
                    ENTT.CRIAR_EM(infos, "Nome", "Avaliacao", "Valor", documento.unicoObjeto("JSON").unicoObjeto("aggregateRating").identifique("ratingValue").getValor());
                    ENTT.CRIAR_EM(infos, "Nome", "Avaliadores", "Valor", documento.unicoObjeto("JSON").unicoObjeto("aggregateRating").identifique("ratingCount").getValor());

                    break;
                }
                //   ENTT.EXIBIR_TABELA(tokens);
            }

        }

        ENTT.EXIBIR_TABELA(infos);

        return infos;
    }

    public static Lista<Entidade> VER_LISTA_DE_EPISODIOS(String LINK_API) {


        Opcional<String> dados = Internet.GET_PAGINA_HTML_TIMEOUT(LINK_API);
        Lista<Entidade> infos = ENTT.CRIAR_LISTA();

        String PARAMENTRO_DE_BUSCA = "ipc-lockup-overlay ipc-focusable";

        for (String linha : Strings.DIVIDIR_LINHAS_TRIM(dados.get().trim())) {

            Lista<Entidade> tokens = ENTT.VALORES_TO_ENTIDADE_ORDENADO(Strings.DIVIDIR_POR_INCLUIDO(linha, "<"), "TokenID", "Token");


            if (linha.contains(PARAMENTRO_DE_BUSCA)) {

                for (Entidade e : tokens) {
                    e.at("Token", Strings.LINEARIZAR(e.at("Token")));
                }

                Lista<Entidade> refs = ENTT.EXIBIR_BUSCA_REFERENCIADA_CONTEM_ABAIXO(tokens, "TokenID", "Token", PARAMENTRO_DE_BUSCA, 40);

                for (Entidade ref : refs) {

                    Entidade e = ENTT.CRIAR_EM(infos, "EP", ref.at("Token"));

                    Lista<Entidade> e_escopo = ENTT.SLICE(tokens, ref.atInt("TokenID"), ref.atInt("TokenID") + 50);

                    for (Entidade item : e_escopo) {
                        if (item.at("Token").startsWith("<span class") && Strings.contar(item.at("Token"), ",") == 2) {
                            item.at("Tipo", "DATA");
                            e.at("Data",item.at("Token"));
                        } else if (item.at("Token").startsWith("<span aria-label=\"IMDb rating:")) {
                            item.at("Tipo", "RATING");
                            e.at("Rating",Strings.GET_ENTRE_ASPAS(item.at("Token"),1));
                        } else if (item.at("Token").startsWith("<div class=\"ipc-title__text\">")) {
                            item.at("Tipo", "NOME");
                            e.at("Nome",Strings.GET_REVERSO_ATE(item.at("Token"),">"));
                            e.at("Nome",Strings.GET_ATE(e.at("Nome"),"∙"));
                            e.at("Nome",Strings.GET_REVERSO_ATE(item.at("Token"),">"));
                        }
                    }

                    ENTT.TORNAR_PRIMEIRO(e_escopo, "Tipo");
                    ENTT.TORNAR_PRIMEIRO(e_escopo, "Ref");
                    ENTT.EXIBIR_TABELA(e_escopo);


                }

                //   ENTT.EXIBIR_TABELA(tokens);
            }

        }

        ENTT.EXIBIR_TABELA(infos);

        return infos;
    }

}

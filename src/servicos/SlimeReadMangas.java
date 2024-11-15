package servicos;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;

public class SlimeReadMangas {


    public static void SAINT_SEIYA_EPISODIOS() {


        Opcional<String> dados = Internet.GET_PAGINA_HTML_TIMEOUT("https://pt.wikipedia.org/wiki/Lista_de_epis%C3%B3dios_de_Saint_Seiya");

        Lista<Entidade> sagas = ENTT.CRIAR_LISTA();
        Lista<Entidade> episodios = ENTT.CRIAR_LISTA();

        if (dados.isOK()) {

            Lista<String> linhas = new Lista<String>();
            for (String linha_completa : Strings.DIVIDIR_LINHAS(dados.get())) {
                linhas.adicionar_varios(Strings.LISTA_TRIM(Strings.DIVIDIR_POR(linha_completa, "<")));

            }


            linhas = Strings.LISTA_TRIM(linhas);

            String saga_corrente = "";
            String fase_corrente = "";

            for (Indexado<String> i_linha : Indexamento.indexe(linhas)) {
                //  if(linha.contains("jpg")){

                String linha = i_linha.get();


                if (linha.contains("<h3 id=")) {
                    Lista<Entidade> fases = ENTT.VALORES_ENTRE("Linha", linhas, i_linha.index(), i_linha.index() + 10);
                    ENTT.SEQUENCIAR(fases, "SEQ", 1);
                    ENTT.EXIBIR_TABELA(fases);
                    String t1 = Strings.GET_DEPOIS(ENTT.GET_PRIMEIRO(fases).at("Linha"), ">").trim();
                    String t3 = Strings.GET_DEPOIS(ENTT.GET_SEMPRE(fases, "SEQ", 3).at("Linha"), ">").trim();

                    fase_corrente = Strings.GET_TEXTO_MAIOR(t1, t3);
                    fmt.print("Fase = {}", fase_corrente);

                }


                if (linha.contentEquals("<td colspan=\"2\">")) {

                    Entidade episodio = ENTT.CRIAR_EM(episodios);

                    episodio.at("Sequencia", "");
                    episodio.at("Saga", saga_corrente);
                    episodio.at("Fase", fase_corrente);

                    episodio.at("Titulo", "");
                    episodio.at("Tipo", "MANGÁ");


                    if ((i_linha.index() - 4) > 0) {
                        String numero = linhas.get(i_linha.index() - 4);
                        episodio.at("Sequencia", numero);

                        if (numero.contains("</b>")) {
                            //  ENTT.EXIBIR_TABELA(ENTT.VALORES_ENTRE("Linhas", linhas, i_linha.index() - 4, i_linha.index() + 4));
                            if ((i_linha.index() - 6) > 0) {
                                numero = linhas.get(i_linha.index() - 6);
                                episodio.at("Sequencia", numero);

                                boolean pode_ser_filer = false;

                                if (numero.contains("</a>)")) {
                                    //  ENTT.EXIBIR_TABELA(ENTT.VALORES_ENTRE("Linhas", linhas, i_linha.index() - 4, i_linha.index() + 17));
                                    if ((i_linha.index() - 17) > 0) {
                                        numero = linhas.get(i_linha.index() - 17);
                                        episodio.at("Sequencia", numero);
                                    }

                                    pode_ser_filer = true;
                                }


                                if (numero.contains("</tr>")) {
                                    //   ENTT.EXIBIR_TABELA(ENTT.VALORES_ENTRE("Linhas", linhas, i_linha.index() - 4, i_linha.index() + 17));
                                    if ((i_linha.index() - 13) > 0) {
                                        numero = linhas.get(i_linha.index() - 13);
                                        episodio.at("Sequencia", numero);
                                    }

                                    pode_ser_filer = true;
                                }

                                if (pode_ser_filer) {
                                    if ((i_linha.index() - 7) > 0) {
                                        String filler = linhas.get(i_linha.index() - 7);
                                        if (filler.contains("Filler")) {
                                            episodio.at("Tipo", "FILLER");
                                        } else if (filler.contains("Semifiller")) {
                                            episodio.at("Tipo", "MEIO-FILLER");
                                        }
                                    }
                                }


                            }
                        }


                        //if (episodio.is("Sequencia", "<b>18")) {

                        Lista<Entidade> area_de_busca = ENTT.VALORES_ENTRE("Linha", linhas, i_linha.index() - 1, i_linha.index() + 70);
                        ENTT.SEQUENCIAR(area_de_busca, "SEQ", -1);

                        boolean resumindo = false;

                        for (Entidade e : area_de_busca) {
                            if (e.at("Linha").contains("/wiki/Brasil")) {
                                e.at("Complementar", "BRASIL");

                                Entidade brasil_data = ENTT.GET_SEMPRE(area_de_busca, "SEQ", e.atInt("SEQ") - 2);

                                if (Strings.CONTAGEM_PARTE(brasil_data.at("Linha"), " ", "de") > 0) {
                                    brasil_data.at("Complementar", "BRASIL_DATA");
                                }
                            } else if (e.at("Linha").contains("/wiki/Jap")) {
                                e.at("Complementar", "JAPAO");

                                Entidade japao_data = ENTT.GET_SEMPRE(area_de_busca, "SEQ", e.atInt("SEQ") - 2);

                                if (Strings.CONTAGEM_PARTE(japao_data.at("Linha"), " ", "de") > 0) {
                                    japao_data.at("Complementar", "JAPAO_DATA");
                                }
                            } else if (e.at("Linha").contains("<td colspan=\"5\"")) {
                                e.at("Complementar", "Resumo");
                                e.at("Linha", Strings.GET_DEPOIS(e.at("Linha"), ">"));

                                resumindo = true;
                            }
                            if (resumindo) {
                                if (e.at("Linha").contains("</td>")) {
                                    resumindo = false;
                                }
                                if (resumindo) {
                                    e.at("Complementar", "Resumo");
                                    if (e.at("Linha").contains(">")) {
                                        e.at("Linha", Strings.GET_DEPOIS(e.at("Linha"), ">"));
                                    }
                                }
                            }
                        }

                        ENTT.ATRIBUTO_TORNAR_PRIMEIRO(area_de_busca, "Complementar");
                        ENTT.ATRIBUTO_TORNAR_PRIMEIRO(area_de_busca, "SEQ");

                        //   ENTT.EXIBIR_TABELA(area_de_busca);

                        episodio.at("Japao", ENTT.GET_SEMPRE(area_de_busca, "Complementar", "JAPAO_DATA").at("Linha"));
                        episodio.at("Brasil", ENTT.GET_SEMPRE(area_de_busca, "Complementar", "BRASIL_DATA").at("Linha"));

                        String texto_resumo = "";
                        for (Entidade resumo : ENTT.COLETAR(area_de_busca, "Complementar", "Resumo")) {
                            texto_resumo += resumo.at("Linha") + " ";
                        }

                        texto_resumo = texto_resumo.trim();
                        episodio.at("Resumo", texto_resumo);
                        //     break;
                        // }

                    }

                    episodio.at("Titulo", linhas.get(i_linha.index() + 2));

                    if (episodio.is("Sequencia", "<b>24")) {
                        //    break;
                    }

                }

                if (linha.contains("Saga") && linha.contains("h2")) {
                    //  fmt.print("++ SAGA : {}", linha);
                    ENTT.CRIAR_EM(sagas, "Saga", linha);
                    saga_corrente = Strings.GET_ENTRE_ASPAS(linha, 1);
                    fase_corrente = "";
                } else {
                    //   fmt.print("\t-->> {}", linha.trim());
                }
                //   }
            }


        }


        for (Entidade e : episodios) {
            e.at("Sequencia", Strings.GET_DIGITOS(e.at("Sequencia")));
            e.at("Saga", e.at("Saga").replace("_", " "));
            e.at("Titulo", Strings.GET_DEPOIS(e.at("Titulo"), ">"));
            e.at("Brasil", Strings.GET_DEPOIS(e.at("Brasil"), ">").toUpperCase());
            e.at("Japao", Strings.GET_DEPOIS(e.at("Japao"), ">").toUpperCase());
        }

        ENTT.EXIBIR_TABELA_COM_TITULO(sagas, "@Sagas");
        ENTT.EXIBIR_TABELA_COM_TITULO(episodios, "@Episodios");


        String guardar_aqui = "/home/luan/assets/mangas/saint_seiya.entts";
        ENTT.GUARDAR(episodios, guardar_aqui);
    }

    public static void procurar_saint_seiya() {

        String dados_saint_seiya = "/home/luan/assets/mangas/saint_seiya.entts";
        Lista<Entidade> episodios = ENTT.ABRIR(dados_saint_seiya);

        ENTT.EXIBIR_TABELA_COM_NOME(episodios,"SAINT SEIYA");

        Lista<Entidade> ep_hyoga = new Lista<Entidade>();

        for(Entidade ep : episodios){
            String resumo = ep.at("Resumo").toLowerCase();
            if(resumo.contains("hyoga")){
                ep_hyoga.adicionar(ep);
            }
        }

        ENTT.EXIBIR_TABELA_COM_NOME(ep_hyoga,"SAINT SEIYA - HYOGA");

    }
}

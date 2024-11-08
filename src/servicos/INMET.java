package servicos;



import libs.dkg.DKG;
import libs.dkg.DKGAtributo;
import libs.dkg.DKGObjeto;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.tempo.Calendario;
import libs.tempo.Data;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import libs.xml.XML;
import libs.xml.XMLObjeto;

public class INMET {

    private static final String API_DADOS_CAPITAIS = "https://apiprevmet3.inmet.gov.br/previsao/capitais";



    public static Lista<Entidade> GET_DADOS() {

        Lista<Entidade> entts = new Lista<Entidade>();

        Resultado<DKGObjeto, String> dados = Internet.http_dkg_objeto(API_DADOS_CAPITAIS);
        if (dados.isOK()) {
            DKGObjeto documento = dados.getOK();

            for (DKGObjeto capital : documento.getObjetos()) {
                for (DKGObjeto obj_data : capital.getObjetos()) {
                    for (DKGObjeto obj_turno : obj_data.getObjetos()) {


                        String txt = "";

                        for (DKGAtributo att : obj_turno.getAtributos()) {
                            String v = att.getValor();
                            if (v.length() > 15) {
                                v = v.substring(0, 14);
                            }
                            txt += fmt.espacar_depois(att.getNome(), 15) + " = " + v + "\n";
                        }

                        //   fmt.print("{}", txt);

                        Entidade e = new Entidade();
                        e.at("Capital", capital.getNome());
                        //  e.at("Data", Tronarko.getData(obj_data.getNome()).getTextoZerado());
                        e.at("Data", obj_data.getNome());

                        e.at("Turno", QUALIFICAR_TURNO(obj_turno.getNome()));

                        e.at("Resumo", obj_turno.identifique("resumo").getValor());
                        e.at("Tempo", obj_turno.identifique("tempo").getValor());

                        e.at("Temp.max", obj_turno.identifique("temp_max").getValor());
                        e.at("Temp.min", obj_turno.identifique("temp_min").getValor());
                        e.at("Temp.max.tend", obj_turno.identifique("temp_max_tende").getValor());
                        e.at("Temp.min.tend", obj_turno.identifique("temp_min_tende").getValor());

                        e.at("Umi.max", obj_turno.identifique("umidade_max").getValor());
                        e.at("Umi.min", obj_turno.identifique("umidade_min").getValor());

                        e.at("Vento.dir", obj_turno.identifique("dir_vento").getValor());
                        e.at("Vento.int", obj_turno.identifique("int_vento").getValor());

                        e.at("Sol.nascer", obj_turno.identifique("nascer").getValor());
                        e.at("Sol.por", obj_turno.identifique("ocaso").getValor());

                        entts.adicionar(e);

                    }

                }
            }

        }

        return entts;
    }

    public static Lista<Entidade> ATUALIZAR_TOZTES() {
        Lista<Entidade> entts = GET_DADOS();
        for (Entidade e : entts) {
            e.at("Data", Tronarko.getData(e.at("Data")).getTextoZerado());
        }
        return entts;
    }

    public static Lista<Entidade> GET_DADOS_TOZTES_BRASILIA_HOJE(Tozte HOJE) {
        Lista<Entidade> previsao_do_tempo_hoje = ENTT.COLETAR(ATUALIZAR_TOZTES(), "Data", HOJE.getTextoZerado());
        Lista<Entidade> previsao_do_tempo_brasilia_hoje = (ENTT.COLETAR(previsao_do_tempo_hoje, "Capital", "Brasília"));
        return previsao_do_tempo_brasilia_hoje;
    }

    public static void EXIBIR() {
        Lista<Entidade> entts = GET_DADOS();
        ENTT.EXIBIR_TABELA(ENTT.COLETAR(entts, "Capital", "Brasília"));
    }

    public static void EXIBIR_TOZTE_HOJE() {
        Lista<Entidade> entts = ENTT.COLETAR(ATUALIZAR_TOZTES(), "Data", Tronarko.getTozte().getTextoZerado());
        ENTT.EXIBIR_TABELA(ENTT.COLETAR(entts, "Capital", "Brasília"));
    }


    public static String QUALIFICAR_TURNO(String turno) {

        if (turno.contentEquals("manha")) {
            turno = "MANHÃ";
        } else if (turno.contentEquals("tarde")) {
            turno = "TARDE";
        } else if (turno.contentEquals("noite")) {
            turno = "NOITE";
        }

        return turno;
    }


    public static void EXIBIR_AGORA() {

        Resultado<DKGObjeto, String> dados = Internet.http_dkg_objeto("https://apiprevmet3.inmet.gov.br/estacao/proxima/5300108");
        if (dados.isOK()) {

            DKGObjeto documento = dados.getOK();

            ENTT.EXIBIR_TABELA(ENTT.TRANSFORMAR_DE_OBJETOS(documento.getObjetos()));
        }

    }


    public static Lista<Entidade> GET_ALERTAS_DE_BRASILIA() {

        Lista<Entidade> alertas = new Lista<Entidade>();


        Opcional<String> pagina = Internet.GET_PAGINA_HTML_TIMEOUT("https://www.google.com/search?client=firefox-b-d&q=15000%2F1000");

        if (pagina.isOK()) {

            String pagina_dados = pagina.get();

            fmt.print("Dados = {}", pagina_dados.length());

            for (String linha : Strings.DIVIDIR_POR(pagina_dados, "<")) {
                linha = linha.trim();
                fmt.print("{}", linha);
            }


        }


        return alertas;

    }

    public static Lista<Entidade> GET_ALERTAR_CLIMATICOS(){

        Lista<Entidade> alerta_dados = ENTT.CRIAR_LISTA();

        Opcional<String> dados = Internet.GET_PAGINA_HTML_TIMEOUT("https://apiprevmet3.inmet.gov.br/avisos/rss");

        if (dados.isOK()) {



            XML xml = XML.PARSER_XML(dados.get());
            xml.getObjeto("rss").getObjeto("channel").exibir_quadro();


            for (XMLObjeto alerta : xml.getObjeto("rss").getObjeto("channel").getObjetos()) {
                if (alerta.getObjeto("description").getObjetos().getQuantidade() > 0) {
                    String conteudo = alerta.getObjeto("description").getObjetos().get(0).getConteudo();
                    // fmt.print(">> {}",conteudo);
                    if (conteudo.contains("Distrito Federal")) {
                        //objetos_df.adicionar(alerta);


                        Entidade e = ENTT.CRIAR_EM(alerta_dados);
                        e.at("ID", "");
                        e.at("AlertaRSS", "");

                        e.at("Tozte", "");
                        e.at("Hazde", "");

                        e.at("Titulo", alerta.getObjeto("title").getConteudo());
                        e.at("Data.Publicado", alerta.getObjeto("pubDate").getConteudo());


                        XML xml_conteudo = XML.PARSER_XML(conteudo);
                        //xml_conteudo.exibir_objetos_mais();

                        e.at("Acontecimento", xml_conteudo.getObjeto("table").getObjetoSequencial("tr", 1).getObjeto("td").getConteudo());
                        e.at("Nivel", xml_conteudo.getObjeto("table").getObjetoSequencial("tr", 2).getObjeto("td").getConteudo());
                        e.at("Data.Inicio", xml_conteudo.getObjeto("table").getObjetoSequencial("tr", 3).getObjeto("td").getConteudo());
                        e.at("Data.Fim", xml_conteudo.getObjeto("table").getObjetoSequencial("tr", 4).getObjeto("td").getConteudo());
                        e.at("Descricao", xml_conteudo.getObjeto("table").getObjetoSequencial("tr", 5).getObjeto("td").getConteudo());
                        e.at("Regiao", xml_conteudo.getObjeto("table").getObjetoSequencial("tr", 6).getObjeto("td").getConteudo());


                        e.at("Link", alerta.getObjeto("link").getConteudo());


                        String i_data = Data.toData(Calendario.COMPLETO_GET_DATA(e.at("Data.Inicio"))).getTempoInverso();
                        String i_horario = Calendario.COMPLETO_GET_HORARIO(e.at("Data.Inicio"));

                        e.at("Data.Inicio", i_data + " " + i_horario);

                        String f_data = Data.toData(Calendario.COMPLETO_GET_DATA(e.at("Data.Fim"))).getTempoInverso();
                        String f_horario = Calendario.COMPLETO_GET_HORARIO(e.at("Data.Fim"));

                        e.at("Data.Fim", f_data + " " + f_horario);

                        //    e.at("AA",xml_conteudo.getObjeto("table").getObjetoSequencial("tr",7).toTexto());
                        //  e.at("Conteudo",conteudo);

                        e.at("Tozte", Tronarko.getData(Data.toData(i_data).getTempoLegivel()).getTextoZerado());
                        e.at("Hazde", Tronarko.getHora(f_horario).getTextoZerado());

                        e.at("AlertaRSS", Strings.GET_REVERSO_ATE(e.at("Link"), "/"));

                    }
                }
            }

        }

        return alerta_dados;
    }




}

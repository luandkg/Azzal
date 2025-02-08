package servicos;


import libs.dkg.DKGAtributo;
import libs.dkg.DKGObjeto;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.tempo.Calendario;
import libs.tempo.Data;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import libs.bibliotecas.xml.XML;
import libs.bibliotecas.xml.XMLObjeto;

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

    public static Lista<Entidade> GET_ALERTAR_CLIMATICOS() {

        Lista<Entidade> alerta_dados = ENTT.CRIAR_LISTA();

        Opcional<String> dados = Internet.GET_PAGINA_HTML_TIMEOUT("https://apiprevmet3.inmet.gov.br/avisos/rss");

        if (dados.isOK()) {


            XML xml = XML.PARSER_XML(dados.get());
            //  xml.getObjeto("rss").getObjeto("channel").exibir_quadro();

            //    xml.exibir();

            fmt.print("---------------RAIZ ---------------");
            xml.exibir_objetos();
            fmt.print("------------------------------");

            for (XMLObjeto alerta : xml.getObjeto("rss").getObjeto("channel").getObjetos()) {

                alerta.exibir_objetos();

                // fmt.print(":: ITEM");
                if (alerta.getObjetos().possuiObjetos()) {
                    fmt.print("---------------OBJ---------------");
                    alerta.exibir_objetos();

                    for (XMLObjeto sub_obj : alerta.getObjetos()) {
                        fmt.print("--------------- SUB OBJ ---------------");
                        fmt.print("Nome     :: {}", sub_obj.getNome());
                        fmt.print("Contagem :: {}", sub_obj.contagemCoisas());
                        sub_obj.exibir_objetos();
                        fmt.print("------------------------------");

                    }
                    fmt.print("------------------------------");

                }


                for (XMLObjeto tr_objeto : alerta.getObjetosComNome("tr")) {
                    fmt.print("TR :: Nome     :: {}", tr_objeto.getNome());
                    if (tr_objeto.temObjetoComNome("td")) {

                        tr_objeto.exibir_objetos();

                        XMLObjeto td_objeto = tr_objeto.getObjeto("td");

                        for (XMLObjeto sub_tr_objeto : td_objeto.getObjetosComNome("a")) {

                            fmt.print("--------------- SUB TR ---------------");
                            sub_tr_objeto.exibir_objetos();
                            fmt.print("Contagem :: {}", sub_tr_objeto.contagemCoisas());

                            for (XMLObjeto sub_sub_tr_objeto : sub_tr_objeto.getObjetosComNome("tr")) {
                                fmt.print("--------------- TRILHA AVISO ---------------");

                                if (sub_sub_tr_objeto.temObjetoComNome("th") && sub_sub_tr_objeto.temObjetoComNome("td")) {
                                    XMLObjeto cabecalho = sub_sub_tr_objeto.getObjeto("th");
                                    XMLObjeto aviso_dados = sub_sub_tr_objeto.getObjeto("td");

                                    if (cabecalho.isConteudo("Link Gráfico")) {
                                        fmt.print("Objeto Gráfico : {}", cabecalho.getConteudo());
                                        aviso_dados.exibir();

                                    }

                                }

                                // sub_sub_tr_objeto.exibir();
                                fmt.print("--------------- ------------ ---------------");

                            }


                        }
                    }
                }

                if (alerta.getObjeto("description").getObjetos().getQuantidade() > 0) {


                    String conteudo = alerta.getObjeto("description").getObjetos().get(0).getConteudo();
                    fmt.print(">> {}", conteudo);
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
                        xml_conteudo.exibir();

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


    public static Lista<Entidade> GET_ALERTAR_CLIMATICOS_V2() {

        Lista<Entidade> alerta_dados = ENTT.CRIAR_LISTA();

        Opcional<String> dados = Internet.GET_PAGINA_HTML_TIMEOUT("https://apiprevmet3.inmet.gov.br/avisos/rss");

        if (dados.isOK()) {


            XML xml = XML.PARSER_XML(dados.get());
            //  xml.getObjeto("rss").getObjeto("channel").exibir_quadro();

            //    xml.exibir();

            //  fmt.print("---------------RAIZ ---------------");
            //   xml.exibir_objetos();
            //   fmt.print("------------------------------");


            //   Lista<String> atributos = XML.COLETAR_ATRIBUTOS(xml);
            //ENTT.EXIBIR_TABELA_COM_NOME(ENTT.VALORES_SEQUENCIADOS("ID", "Atributo", atributos), "XML ATRIBUTOS");

            //  Lista<String> objetos = XML.COLETAR_OBJETOS(xml);
            //  ENTT.EXIBIR_TABELA_COM_NOME(ENTT.VALORES_SEQUENCIADOS("ID", "Objeto", objetos), "XML OBJETOS");

            //   Lista<String> conteudos = XML.COLETAR_CONTEUDOS(xml);
            //   ENTT.EXIBIR_TABELA_COM_NOME(ENTT.VALORES_SEQUENCIADOS("ID", "Conteudo", conteudos), "XML CONTEUDOS");


            Lista<XMLObjeto> objetos_com_conteudo_alert = XML.COLETAR_OBJETOS_COM_CONTEUDO(xml, "Alert");

            //    fmt.print("Alertas : {}", objetos_com_conteudo_alert.getQuantidade());


            for (XMLObjeto alerta : objetos_com_conteudo_alert) {
                if (alerta.temPai()) {
                    if (alerta.getPai().temPai()) {

                      //  fmt.print("--------------------- PAI -----------------------");
                        XMLObjeto alerta_avo = alerta.getPai().getPai();
                        // alerta_avo.exibir_quadro_detalhado();

                      //  Lista<String> atributos = XML.COLETAR_CONTEUDOS(alerta_avo);
                     //   ENTT.EXIBIR_TABELA_COM_NOME(ENTT.VALORES_SEQUENCIADOS("ID", "Atributo", atributos), "XML ATRIBUTOS");

                        Lista<XMLObjeto> objetos_com_att = XML.COLETAR_OBJETOS_COM_ATRIBUTO(alerta_avo, "href");
                        for (XMLObjeto oa : objetos_com_att) {
                         //   oa.exibir_se();
                        }

                        Opcional<Entidade> op_alerta = TRANSFORMAR_EM_QUADRO_DO_INMET(alerta_avo);
                        if (op_alerta.isOK()) {
                            if (op_alerta.get().at("Área").contains("Distrito Federal")) {

                                Entidade e_alerta = op_alerta.get();
                                e_alerta.at("AlertaRSS", e_alerta.at("Início") + " :: " + e_alerta.at("Fim"));

                                e_alerta.tornar_primeiro("Duplicado");

                                e_alerta.tornar_primeiro("AlertaRSS");
                                alerta_dados.adicionar(e_alerta);
                            }
                        }
                    }
                }
            }


            for (Entidade e : alerta_dados) {
                Lista<Entidade> iguais = ENTT.COLETAR(alerta_dados, "AlertaRSS", e.at("AlertaRSS"));
                if (iguais.getQuantidade() > 1) {
                    ENTT.ATRIBUTO_TODOS(iguais, "Duplicado", "SIM");
                    ENTT.GET_PRIMEIRO(iguais).at("Duplicado", "");
                }
            }


            ENTT.AT_ALTERAR_NOME(alerta_dados,"Evento","Acontecimento");
            ENTT.AT_ALTERAR_NOME(alerta_dados,"Severidade","Nivel");
            ENTT.AT_ALTERAR_NOME(alerta_dados,"Início","Data.Inicio");
            ENTT.AT_ALTERAR_NOME(alerta_dados,"Fim","Data.Fim");
            ENTT.AT_ALTERAR_NOME(alerta_dados,"Descrição","Descricao");
            ENTT.AT_ALTERAR_NOME(alerta_dados,"Área","Regiao");

            for(Entidade e : alerta_dados){

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

                e.at("AlertaRSS", e.at("Data.Inicio") + " :: " + e.at("Data.Fim"));

            }

            ENTT.REMOVER_SE(alerta_dados,"Duplicado","SIM");
            ENTT.ATRIBUTO_REMOVER(alerta_dados,"Duplicado");
            ENTT.ATRIBUTO_REMOVER(alerta_dados,"Status");

          //  ENTT.EXIBIR_TABELA(alerta_dados);

        }

        return alerta_dados;
    }


    public static Opcional<Entidade> TRANSFORMAR_EM_QUADRO_DO_INMET(XMLObjeto objeto_alerta) {

        Entidade novo = new Entidade();

        boolean tem = false;

        for (XMLObjeto obj : objeto_alerta.getObjetos()) {


            if (obj.temObjetoComNome("th") && obj.temObjetoComNome("td")) {

                XMLObjeto th = obj.getObjeto("th");
                XMLObjeto td = obj.getObjeto("td");

                novo.at(th.getConteudo(), td.getConteudo());
                tem = true;

            }

        }

        if (tem) {
            return Opcional.OK(novo);
        }

        return Opcional.CANCEL();
    }

}

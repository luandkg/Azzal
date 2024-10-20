package apps.app;

import libs.entt.Entidade;
import libs.luan.*;
import libs.tempo.Calendario;
import libs.tempo.Data;

public class Strava {


    public static Lista<Entidade> PERFIL_GET_DADOS_RECENTE(String perfil_atleta_id) {
        Lista<Entidade> dados = new Lista<Entidade>();

        Data hoje = Calendario.getDataHoje();
        int hoje_hora = Calendario.getHoraDoDia();

        if (hoje_hora < 6 || hoje_hora > 18) {
            return dados;
        }


        Opcional<String> strava_pagina_dados = Internet.GET_PAGINA_HTML_TIMEOUT("https://www.strava.com/athletes/" + perfil_atleta_id);

        if (strava_pagina_dados.isOK()) {

            Lista<Data> datas = Calendario.listar_datas_entre_anos(hoje.getAno() - 1, hoje.getAno() + 1);

            for (Indexado<String> linha : Indexamento.indexe(Strings.dividir_linhas(strava_pagina_dados.get()))) {

                Lista<String> valores = Strings.dividir_por(linha.get(), "<");

                for (Indexado<String> fatia : Indexamento.indexe(valores)) {

                    if (fatia.get().contains("RecentActivities_timestamp")) {
                        // fmt.print("{} :: {} ->> {}", linha.index(), fatia.index(), fatia.get());

                        for (int entre = fatia.index() - 20; entre <= fatia.index() + 50; entre++) {
                            if (entre >= 0 && entre < valores.getQuantidade()) {
                                //    fmt.print("\t ++ {} ->> {}", entre - fatia.index(), valores.get(entre));
                            }
                        }


                        String atividade_data = Strings.parser_depois_de(valores.get(fatia.index()), ">").toUpperCase();
                        String atividade_nome = Strings.parser_depois_de(valores.get(fatia.index() + 2), ">");
                        String atividade_info1 = Strings.parser_depois_de(valores.get(fatia.index() + 9), ">").toUpperCase();
                        String atividade_info2 = Strings.parser_depois_de(valores.get(fatia.index() + 22), ">");
                        String atividade_info3 = Strings.parser_depois_de(valores.get(fatia.index() + 26), ">");

                        String atividade_info7 = Strings.parser_depois_de(valores.get(fatia.index() + 32), ">");

                        String atividade_info4 = Strings.parser_depois_de(valores.get(fatia.index() + 36), ">");
                        String atividade_info5 = Strings.parser_depois_de(valores.get(fatia.index() + 42), ">");
                        String atividade_info6 = Strings.parser_depois_de(valores.get(fatia.index() + 46), ">");


                        atividade_info1 = SIMPLIFICAR(atividade_info1);
                        atividade_info2 = SIMPLIFICAR(atividade_info2);
                        atividade_info3 = SIMPLIFICAR(atividade_info3);
                        atividade_info4 = SIMPLIFICAR(atividade_info4);

                        atividade_info5 = SIMPLIFICAR(atividade_info5);
                        atividade_info6 = SIMPLIFICAR(atividade_info6);
                        atividade_info7 = SIMPLIFICAR(atividade_info7);


                        if (atividade_data.contentEquals("YESTERDAY")) {

                            String ontem = "";
                            for (Data data : datas) {
                                if (data.isIgual(hoje)) {
                                    break;
                                }
                                ontem = data.getTempoInverso();
                            }

                            atividade_data = ontem;
                        } else if (atividade_data.contentEquals("TODAY")) {

                            atividade_data = hoje.getTempoInverso();

                        } else {

                            String data_mes = Strings.parser_ate(atividade_data, " ");
                            String data_dia_ano = Strings.parser_depois_de(atividade_data, " ");
                            String data_dia = Strings.parser_ate(data_dia_ano, ",");
                            String data_ano = Strings.parser_depois_de(data_dia_ano, " ");

                            data_dia = fmt.numero_zerado_c2(data_dia);
                            data_mes = Calendario.MES_INGLES_PARA_NUMERAL_ZERADO(data_mes);

                            atividade_data = data_ano + "_" + data_mes + "_" + data_dia;
                        }

                        Entidade e = new Entidade();
                        e.at("Tipo", atividade_info1);
                        e.at("Data", atividade_data);
                        e.at("Nome", atividade_nome);

                        String tempo = "";
                        String altitude = "";
                        String distancia = "";


                        if (atividade_info1.contentEquals("WALK")) {

                            distancia = atividade_info3;
                            altitude = atividade_info4;
                            tempo = atividade_info6;

                            if (altitude.trim().isEmpty()) {
                                altitude = atividade_info7;
                            }

                        } else if (atividade_info1.contentEquals("RUN")) {

                            distancia = atividade_info2;
                            altitude = atividade_info4;
                            tempo = atividade_info5;

                            if (altitude.trim().isEmpty()) {
                                altitude = atividade_info7;
                            }


                        } else if (atividade_info1.contentEquals("RIDE")) {

                            distancia = atividade_info2;
                            altitude = atividade_info4;
                            tempo = atividade_info5;

                        } else if (atividade_info1.contentEquals("WORKOUT")) {

                            if (!atividade_info2.trim().isEmpty()) {
                                tempo = atividade_info2;
                            }
                            if (!atividade_info3.trim().isEmpty()) {
                                tempo = atividade_info3;
                            }
                        }


                        if (distancia.contains(":") && tempo.isEmpty()) {
                            String t1 = tempo;
                            tempo = distancia;
                            distancia = t1;
                        }

                        e.at("Distancia", distancia);
                        e.at("Altitude", altitude);
                        e.at("Tempo", tempo);

                        e.at("Info1", atividade_info1);
                        e.at("Info2", atividade_info2);
                        e.at("Info3", atividade_info3);
                        e.at("Info4", atividade_info4);
                        e.at("Info5", atividade_info5);
                        e.at("Info6", atividade_info6);
                        e.at("Info7", atividade_info7);

                        dados.adicionar(e);

                    }

                }

            }

        }


        return dados;
    }

    private static String SIMPLIFICAR(String info) {
        if (info.toUpperCase().contentEquals("GET STRAVA")) {
            return "";
        } else {
            return info;
        }
    }

    public static Lista<Entidade> PERFIL_GET_DESAFIOS_RECENTE(String perfil_atleta_id) {
        Lista<Entidade> dados = new Lista<Entidade>();

        Data hoje = Calendario.getDataHoje();
        int hoje_hora = Calendario.getHoraDoDia();

        if (hoje_hora < 6 || hoje_hora > 18) {
            return dados;
        }


        Opcional<String> strava_pagina_dados = Internet.GET_PAGINA_HTML_TIMEOUT("https://www.strava.com/athletes/" + perfil_atleta_id);

        if (strava_pagina_dados.isOK()) {

            for (Indexado<String> linha : Indexamento.indexe(Strings.dividir_linhas(strava_pagina_dados.get()))) {

                Lista<String> valores = Strings.dividir_por(linha.get(), "<");

                for (Indexado<String> fatia : Indexamento.indexe(valores)) {

                    //  fmt.print("{} ->> {}",fatia.index(),fatia.get());

                    if (fatia.get().contains("Trophy_description__")) {

                        String desafio_imagem = Strings.parser_entre_aspas(valores.get(fatia.index() - 1));
                        String desafio_nome = Strings.parser_depois_de(valores.get(fatia.index() + 1), ">");
                        String desafio_data = Strings.parser_depois_de(valores.get(fatia.index() + 3), ">");


                        String data_mes = Strings.parser_ate(desafio_data, " ");
                        String data_ano = Strings.parser_depois_de(desafio_data, " ");

                        data_mes = Calendario.MES_INGLES_3_PARA_NUMERAL_ZERADO(data_mes);


                        Entidade e = new Entidade();
                        e.at("Nome", desafio_nome);
                        e.at("Data", data_mes + "_" + data_ano);
                        e.at("Chave", data_mes + "_" + data_ano + "_" + desafio_nome.toUpperCase().replace(" ", "_").replace("-", "_"));
                        e.at("Imagem", desafio_imagem);
                        e.at("Conquistado", Calendario.getTempoCompleto());

                        dados.adicionar(e);

                        for (int entre = fatia.index() - 10; entre <= fatia.index() + 15; entre++) {
                            if (entre >= 0 && entre < valores.getQuantidade()) {
                                //       fmt.print("\t ++ {} ->> {}", entre - fatia.index(), valores.get(entre));
                            }
                        }


                    }

                }

            }


        }

        return dados;
    }
}

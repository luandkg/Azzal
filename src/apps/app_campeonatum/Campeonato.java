package apps.app_campeonatum;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.tronarko.Superarkos;
import libs.tronarko.Tronarko;

public class Campeonato {

    public static TempoCorrente CAMPEONATO_INICIAR() {

        TempoCorrente CAMPEONATO_TOZTE = new TempoCorrente(1, 1, Tronarko.getTozte().getTronarko());

        CAMPEONATO_TOZTE.adicionarSuperarkos(30 + Aleatorio.aleatorio(150));

        return CAMPEONATO_TOZTE;
    }

    public static Lista<String> CAMPEONATO_CONTAGEM_DE_PONTOS(TempoCorrente CAMPEONATO_TOZTE, Lista<String> CAMPEONATO_TIMES_INSCRITOS) {

        CAMPEONATO_TOZTE.adicionarSuperarkos(3 + Aleatorio.aleatorio(10));

        fmt.print("Tozte Contagem de Pontos : {}", CAMPEONATO_TOZTE.getTextoZerado());

        fmt.print("Times Participantes - Embaralhados");
        Embaralhar.emabaralhe(CAMPEONATO_TIMES_INSCRITOS);

        // COMPETICAO : 12 TIMES
        int QUANTIDADE_POR_GRUPO = 4;
        int QUANTIDADE_GRUPOS = 3;

        // COMPETICAO : 16 TIMES
        if (CAMPEONATO_TIMES_INSCRITOS.getQuantidade() >= 16) {
            QUANTIDADE_POR_GRUPO = 4;
            QUANTIDADE_GRUPOS = 4;
        }

        CAMPEONATO_TOZTE.adicionarSuperarkos(3 + Aleatorio.aleatorio(10));

        fmt.print("Sorteio das Grupos : {}", CAMPEONATO_TOZTE.getTextoZerado());

        int MAXIMO_DE_GRUPOS = QUANTIDADE_GRUPOS * QUANTIDADE_POR_GRUPO;

        while (CAMPEONATO_TIMES_INSCRITOS.getQuantidade() != MAXIMO_DE_GRUPOS) {
            Embaralhar.emabaralhe(CAMPEONATO_TIMES_INSCRITOS);

            fmt.print("Desclassificado - " + CAMPEONATO_TIMES_INSCRITOS.get(0));
            CAMPEONATO_TIMES_INSCRITOS.remover_indice(0);
        }

        fmt.print("Times Participantes - Organizados");
        Strings.ORDENAR(CAMPEONATO_TIMES_INSCRITOS);
        fmt.listar_strings_ordenada(CAMPEONATO_TIMES_INSCRITOS);

        return CAMPEONATO_TIMES_INSCRITOS;
    }


    public static int CONTAGEM_DE_GRUPOS(Lista<String> CAMPEONATO_TIMES_INSCRITOS) {
        int QUANTIDADE_POR_GRUPO = 4;
        int QUANTIDADE_GRUPOS = 3;

        // COMPETICAO : 16 TIMES
        if (CAMPEONATO_TIMES_INSCRITOS.getQuantidade() >= 16) {
            QUANTIDADE_POR_GRUPO = 4;
            QUANTIDADE_GRUPOS = 4;
        }

        return QUANTIDADE_GRUPOS;
    }

    public static int CONTAGEM_POR_GRUPO(Lista<String> CAMPEONATO_TIMES_INSCRITOS) {
        int QUANTIDADE_POR_GRUPO = 4;
        int QUANTIDADE_GRUPOS = 3;

        // COMPETICAO : 16 TIMES
        if (CAMPEONATO_TIMES_INSCRITOS.getQuantidade() >= 16) {
            QUANTIDADE_POR_GRUPO = 4;
            QUANTIDADE_GRUPOS = 4;
        }

        return QUANTIDADE_POR_GRUPO;
    }


    public static Lista<Entidade> CAMPEONATO_ORGANIZAR_GRUPOS(Lista<String> CAMPEONATO_TIMES_INSCRITOS, int QUANTIDADE_GRUPOS, int QUANTIDADE_POR_GRUPO) {

        String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Lista<Entidade> TIMES_COMPETICAO_GRUPO = new Lista<Entidade>();

        for (int grupo = 0; grupo < QUANTIDADE_GRUPOS; grupo++) {

            String grupo_letra = String.valueOf(ALFABETO.charAt(grupo));

            // fmt.print("GRUPO {}",grupo_letra);

            Embaralhar.emabaralhe(CAMPEONATO_TIMES_INSCRITOS);
            for (int time = 0; time < QUANTIDADE_POR_GRUPO; time++) {

                String time_nome = CAMPEONATO_TIMES_INSCRITOS.get(0);
                CAMPEONATO_TIMES_INSCRITOS.removerIndex(0);

                Entidade time_sorteado = new Entidade();
                time_sorteado.at("Time", time_nome);
                time_sorteado.at("Grupo", grupo_letra);
                TIMES_COMPETICAO_GRUPO.adicionar(time_sorteado);

            }

        }

        return TIMES_COMPETICAO_GRUPO;
    }

    public static Lista<Entidade> CAMPEONATO_MONTAR_COMPETICOES(TempoCorrente CAMPEONATO_TOZTE, Lista<Entidade> TIMES_COMPETICAO_GRUPO) {

        CAMPEONATO_TOZTE.adicionarSuperarkos(3 + Aleatorio.aleatorio(20));

        Lista<Entidade> TIMES_COMPETICAO = new Lista<Entidade>();


        for (Entidade grupo : ENTT.AGRUPAR(TIMES_COMPETICAO_GRUPO, "Grupo")) {

            int grupo_jopo = 1;
            Lista<Entidade> GRUPO_TIMES_COMPETICAO = new Lista<Entidade>();

            for (Entidade time_grupo_1 : grupo.getEntidades()) {
                String time_1_nome = time_grupo_1.at("Time");
                for (Entidade time_grupo_2 : grupo.getEntidades()) {
                    String time_2_nome = time_grupo_2.at("Time");
                    if (!time_1_nome.contentEquals(time_2_nome)) {

                        boolean existe = false;

                        for (Entidade competicao : GRUPO_TIMES_COMPETICAO) {
                            if (competicao.at("T1").contentEquals(time_1_nome) && competicao.at("T2").contentEquals(time_2_nome)) {
                                existe = true;
                                break;
                            } else if (competicao.at("T1").contentEquals(time_2_nome) && competicao.at("T2").contentEquals(time_1_nome)) {
                                existe = true;
                                break;
                            }

                        }

                        if (!existe) {
                            Entidade competicao = new Entidade();
                            competicao.at("T1", time_1_nome);
                            competicao.at("T2", time_2_nome);

                            GRUPO_TIMES_COMPETICAO.adicionar(competicao);
                        }

                    }
                }
            }


            for (Entidade competicao : GRUPO_TIMES_COMPETICAO) {


                competicao.at("Grupo", grupo.at("Grupo"));
                competicao.at("GrupoJogo", grupo.at("Grupo") + grupo_jopo);

                TIMES_COMPETICAO.adicionar(competicao);
                grupo_jopo += 1;

            }

        }

        int competicao_id = 1;
        for (Entidade competicao : TIMES_COMPETICAO) {
            competicao.at("ID", competicao_id);
            competicao_id += 1;
        }

        return TIMES_COMPETICAO;
    }


    public static void CAMPEONATO_AGENDAR_DATAS_DOS_JOGOS(TempoCorrente CAMPEONATO_TOZTE, Lista<String> eHazdes, Lista<Entidade> TIMES_COMPETICAO, boolean deve_embaralhar) {

        CAMPEONATO_TOZTE.adicionarSuperarkos(3 + Aleatorio.aleatorio(10));

        if (deve_embaralhar) {
            Embaralhar.emabaralhe(TIMES_COMPETICAO);
        }

        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO);

        CAMPEONATO_TOZTE.adicionarSuperarkos(20 + Aleatorio.aleatorio(20));

        String anterior_a = "";
        String anterior_b = "";


        while (true) {
            boolean chocou = false;

            for (Indexado<Entidade> competicao : Indexamento.indexe(TIMES_COMPETICAO)) {

                if (competicao.index() == 0) {
                    anterior_a = competicao.get().at("T1");
                    anterior_b = competicao.get().at("T2");
                } else {
                    String aqui_a = competicao.get().at("T1");
                    String aqui_b = competicao.get().at("T2");

                    if (anterior_a.contentEquals(aqui_a)) {
                        fmt.print("Mudar ->> {} em {}", aqui_a, competicao.get().at("ID"));
                        chocou = true;
                    } else if (anterior_a.contentEquals(aqui_b)) {
                        fmt.print("Mudar ->> {} em {}", aqui_b, competicao.get().at("ID"));
                        chocou = true;
                    } else if (anterior_b.contentEquals(aqui_a)) {
                        fmt.print("Mudar ->> {} em {}", anterior_b, competicao.get().at("ID"));
                        chocou = true;
                    } else if (anterior_b.contentEquals(aqui_b)) {
                        fmt.print("Mudar ->> {} em {}", anterior_b, competicao.get().at("ID"));
                        chocou = true;
                    }

                    anterior_a = competicao.get().at("T1");
                    anterior_b = competicao.get().at("T2");

                    if (chocou) {

                        int m1 = competicao.index();
                        int m2 = Aleatorio.aleatorio(TIMES_COMPETICAO.getQuantidade());

                        // fmt.print("Trocar {} com {}",m1,m2);

                        fmt.print("Realizar ajustes !");

                        Entidade e1 = TIMES_COMPETICAO.get(m1);
                        Entidade e2 = TIMES_COMPETICAO.get(m2);

                        TIMES_COMPETICAO.set(m1, e2);
                        TIMES_COMPETICAO.set(m2, e1);

                        break;
                    }
                }


            }

            ENTT.EXIBIR_TABELA(TIMES_COMPETICAO);
            if (!chocou) {
                break;
            }
        }


        for (Entidade competicao : TIMES_COMPETICAO) {
            CAMPEONATO_TOZTE.proximoSuperarko(Superarkos.OMEGA, Superarkos.SIGMA, Superarkos.SIGMA);

            competicao.at("Tozte", CAMPEONATO_TOZTE.getTextoZerado() + " - " + CAMPEONATO_TOZTE.getTozte().getSuperarko_Status().toString());
            competicao.at("Hazde", Aleatorio.escolha_um(eHazdes));
        }

        int competicao_seq = 1;

        for (Entidade competicao : TIMES_COMPETICAO) {
            competicao.at("ID", competicao_seq);
            competicao_seq += 1;
        }

    }

    public static void CAMPEONATO_MARCAR_PRIMEIROS_E_ULTIMOS(Lista<Entidade> TIMES_COMPETICAO) {

        for (Entidade competicao_grupo : ENTT.AGRUPAR(TIMES_COMPETICAO, "Grupo")) {
            int competicao_grupo_seq = 1;

            int JOGOS_POR_GRUPO = competicao_grupo.getEntidades().getQuantidade();

            for (Entidade competicao : competicao_grupo.getEntidades()) {
                competicao.at("GrupoJogo", competicao_grupo.at("Grupo") + competicao_grupo_seq);

                if (competicao_grupo_seq == 1) {
                    competicao.at("Status", "#PRIMEIRO");
                } else if (competicao_grupo_seq == JOGOS_POR_GRUPO) {
                    competicao.at("Status", "#ULTIMO");
                }

                competicao_grupo_seq += 1;
            }
        }

    }

    public static Lista<Entidade> CAMPEONATO_REALIZAR_CLASSIFICACAO(Lista<Entidade> TIMES_COMPETICAO) {

        Lista<Entidade> TIMES_COMPETICAO_CLASSIFICAO = new Lista<Entidade>();

        for (Entidade competicao_grupo : ENTT.AGRUPAR_E_ORDENAR(TIMES_COMPETICAO, "Grupo")) {

            Unico<String> times = new Unico<String>(Strings.IGUALAVEL());
            for (String time : ENTT.FILTRAR_UNICOS(competicao_grupo.getEntidades(), "T1")) {
                times.item(time);
            }
            for (String time : ENTT.FILTRAR_UNICOS(competicao_grupo.getEntidades(), "T2")) {
                times.item(time);
            }

            fmt.print("Grupo - " + competicao_grupo.at("Grupo"));
            Lista<Entidade> grupo_analise = new Lista<Entidade>();

            for (String time : times) {

                int partidas = 0;

                int vitorias = 0;
                int gols_acumulado = 0;
                int gols_maximo = 0;

                for (Entidade competicao : competicao_grupo.getEntidades()) {

                    if (competicao.at("T1").contentEquals(time) || competicao.at("T2").contentEquals(time)) {

                        partidas += 1;

                        if (competicao.at("Resultado").contentEquals("VITÓRIA")) {

                            if (competicao.at("Vencedor").contentEquals(time)) {
                                vitorias += 1;
                                int partida_gols = competicao.atInt("Gols.Vencedor");
                                if (partida_gols > gols_maximo) {
                                    gols_maximo = partida_gols;
                                }
                                gols_acumulado += partida_gols;
                            } else if (competicao.at("Perdedor").contentEquals(time)) {

                                int partida_gols = competicao.atInt("Gols.Perdedor");
                                if (partida_gols > gols_maximo) {
                                    gols_maximo = partida_gols;
                                }
                                gols_acumulado += partida_gols;
                            }


                        } else if (competicao.at("Resultado").contentEquals("EMPATE")) {

                            int partida_gols = competicao.atInt("Gols");
                            if (partida_gols > gols_maximo) {
                                gols_maximo = partida_gols;
                            }
                            gols_acumulado += partida_gols;
                        }

                    }


                }
                //  fmt.print("\t+ {} = {} vit {} gols", time, vitorias,gols);

                Entidade e_time = new Entidade();
                e_time.at("Grupo", competicao_grupo.at("Grupo"));
                e_time.at("Time", time);
                e_time.at("Partidas", partidas);

                e_time.at("Vitorias", vitorias);
                e_time.at("Gols.Acumulado", gols_acumulado);
                e_time.at("Gols.Maximo", gols_maximo);


                grupo_analise.adicionar(e_time);
            }


            for (Entidade competicao : grupo_analise) {
                competicao.at("Classificacao", 0);
            }

            for (int p = 1; p <= times.getQuantidade(); p++) {
                PontuarClassificatorias.PONTUAR_VITORIAS(ENTT.COLETAR(grupo_analise, "Classificacao", 0), p);
            }

            int eliminar_a_partir = (times.getQuantidade() / 2) + 1;

            for (Entidade competicao : grupo_analise) {
                if (competicao.atInt("Classificacao") >= eliminar_a_partir) {
                    competicao.at("Classificacao.Status", "DESCLASSIFICADO");
                } else {
                    String criterio = competicao.at("Classificacao.Status");
                    competicao.at("Classificacao.Status", "CLASSIFICADO");
                    competicao.at("Classificacao.Criterio", criterio);
                }
            }

            ENTT.ORDENAR_INTEIRO(grupo_analise, "Classificacao");
            ENTT.EXIBIR_TABELA(grupo_analise);

            TIMES_COMPETICAO_CLASSIFICAO.adicionar_varios(grupo_analise);

            VERIFICADOR.IGUALDADE(ENTT.COLETAR(grupo_analise, "Classificacao", 1).getQuantidade(), 1);
            // VERIFICADOR.IGUALDADE(ENTT.COLETAR(grupo_analise,"Classificacao",2).getQuantidade(),1);
            //  VERIFICADOR.IGUALDADE(ENTT.COLETAR(grupo_analise,"Classificacao",3).getQuantidade(),1);
            // VERIFICADOR.IGUALDADE(ENTT.COLETAR(grupo_analise,"Classificacao",4).getQuantidade(),1);

        }

        return TIMES_COMPETICAO_CLASSIFICAO;
    }


    public static Lista<Entidade> CAMPEONATO_TIMES_CLASSIFICADOS(Lista<Entidade> TIMES_COMPETICAO_CLASSIFICAO) {
        Lista<Entidade> TIMES_COMPETICAO_CLASSIFICADOS = new Lista<Entidade>();

        for (Entidade competicao : TIMES_COMPETICAO_CLASSIFICAO) {
            if (competicao.at("Classificacao.Status").contentEquals("CLASSIFICADO")) {
                TIMES_COMPETICAO_CLASSIFICADOS.adicionar(competicao);
            }
        }
        return TIMES_COMPETICAO_CLASSIFICADOS;
    }


    public static Lista<Entidade> CAMPEONATO_CLASSIFICADOS_OITAVAS(Lista<Entidade> TIMES_COMPETICAO_CLASSIFICADOS) {

        Lista<Entidade> TIMES_COMPETICAO_CLASSIFICADOS_OITAVAS = new Lista<Entidade>();


        for (Entidade time : TIMES_COMPETICAO_CLASSIFICADOS) {

            Entidade e_time = new Entidade();
            e_time.at("Time", time.at("Time"));

            TIMES_COMPETICAO_CLASSIFICADOS_OITAVAS.adicionar(e_time);
        }

        Embaralhar.emabaralhe(TIMES_COMPETICAO_CLASSIFICADOS_OITAVAS);

        int i_sorteio = 0;
        String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (Entidade e_time : TIMES_COMPETICAO_CLASSIFICADOS_OITAVAS) {
            String grupo_letra = String.valueOf(ALFABETO.charAt(i_sorteio));
            e_time.at("Chave", grupo_letra);

            i_sorteio += 1;
        }

        return TIMES_COMPETICAO_CLASSIFICADOS_OITAVAS;
    }

    public static Lista<Entidade> CAMPEONATO_ORGANIZAR_OITAVAS(Lista<Entidade> TIMES_COMPETICAO_CLASSIFICADOS_OITAVAS) {

        String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Lista<Entidade> TIMES_COMPETICAO_OITAVAS = new Lista<Entidade>();

        int jogo_id = 1;

        for (int i = 0; i < 4; i++) {

            String chave_t1 = String.valueOf(ALFABETO.charAt(i * 2));
            ;
            String chave_t2 = String.valueOf(ALFABETO.charAt((i * 2) + 1));
            ;


            Entidade jogo = new Entidade();
            jogo.at("Jogo", jogo_id);
            jogo.at("T1", ENTT.GET_UM(TIMES_COMPETICAO_CLASSIFICADOS_OITAVAS, "Chave", chave_t1).at("Time"));
            jogo.at("T2", ENTT.GET_UM(TIMES_COMPETICAO_CLASSIFICADOS_OITAVAS, "Chave", chave_t2).at("Time"));

            TIMES_COMPETICAO_OITAVAS.adicionar(jogo);
            jogo_id += 1;
        }

        return TIMES_COMPETICAO_OITAVAS;
    }


    public static Lista<Entidade> CAMPEONATO_OBTER_VENCEDORES(Lista<Entidade> TIMES_COMPETICAO_CLASSIFICADOS) {

        Lista<Entidade> TIMES_COMPETICAO_VENCEDORES = new Lista<Entidade>();


        for (Entidade time : TIMES_COMPETICAO_CLASSIFICADOS) {

            Entidade e_time = new Entidade();
            e_time.at("Jogo", time.at("Jogo"));
            e_time.at("Time", time.at("Vencedor"));

            TIMES_COMPETICAO_VENCEDORES.adicionar(e_time);
        }

        return TIMES_COMPETICAO_VENCEDORES;
    }

    public static Lista<Entidade> CAMPEONATO_ORGANIZAR_QUARTAS(Lista<Entidade> TIMES_COMPETICAO_JOGOS) {

        String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Lista<Entidade> TIMES_COMPETICAO_QUARTAS = new Lista<Entidade>();

        int jogo_id = 1;

        String jogo_a_vs_b = ENTT.GET_UM(TIMES_COMPETICAO_JOGOS, "Jogo", "1").at("Vencedor");
        String jogo_c_vs_d = ENTT.GET_UM(TIMES_COMPETICAO_JOGOS, "Jogo", "2").at("Vencedor");


        Entidade jogo = new Entidade();
        jogo.at("Jogo", jogo_id);
        jogo.at("T1", jogo_a_vs_b);
        jogo.at("T2", jogo_c_vs_d);

        TIMES_COMPETICAO_QUARTAS.adicionar(jogo);

        String jogo_e_vs_f = ENTT.GET_UM(TIMES_COMPETICAO_JOGOS, "Jogo", "3").at("Vencedor");
        String jogo_g_vs_h = ENTT.GET_UM(TIMES_COMPETICAO_JOGOS, "Jogo", "4").at("Vencedor");

        jogo_id += 1;
        Entidade jogo2 = new Entidade();
        jogo2.at("Jogo", jogo_id);
        jogo2.at("T1", jogo_e_vs_f);
        jogo2.at("T2", jogo_g_vs_h);

        TIMES_COMPETICAO_QUARTAS.adicionar(jogo2);

        return TIMES_COMPETICAO_QUARTAS;
    }

}

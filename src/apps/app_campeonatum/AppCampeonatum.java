package apps.app_campeonatum;

import apps.app_attuz.Sociedade.PessoaNomeadorDeAkkax;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.fmt;
import libs.tronarko.Tronarko;

public class AppCampeonatum {


    public static void init() {

        fmt.print_titulo_central("CAMPEONATO");
        fmt.print();

        TempoCorrente CAMPEONATO_TOZTE = Campeonato.CAMPEONATO_INICIAR();

        C12 c12 = new C12();
        Lista<String> CAMPEONATO_TIMES_INSCRITOS = c12.GET_TIMES();

        fmt.print("Tozte Inscrição : {}", CAMPEONATO_TOZTE.getTextoZerado());

        fmt.print("Times Participantes");

        Strings.ORDENAR(CAMPEONATO_TIMES_INSCRITOS);
        fmt.listar_strings_ordenada(CAMPEONATO_TIMES_INSCRITOS);

        Lista<String> CAMPEONATO_TIMES_PARTICIPIANTES = Campeonato.CAMPEONATO_CONTAGEM_DE_PONTOS(CAMPEONATO_TOZTE, CAMPEONATO_TIMES_INSCRITOS);

        int QUANTIDADE_GRUPOS = Campeonato.CONTAGEM_DE_GRUPOS(CAMPEONATO_TIMES_PARTICIPIANTES);
        int QUANTIDADE_POR_GRUPO = Campeonato.CONTAGEM_POR_GRUPO(CAMPEONATO_TIMES_PARTICIPIANTES);


        Lista<Entidade> TIMES_COMPETICAO_GRUPO = Campeonato.CAMPEONATO_ORGANIZAR_GRUPOS(CAMPEONATO_TIMES_PARTICIPIANTES, QUANTIDADE_GRUPOS, QUANTIDADE_POR_GRUPO);

        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO_GRUPO);


        fmt.print("Divulgação das Competições : {}", CAMPEONATO_TOZTE.getTextoZerado());


        Lista<Entidade> TIMES_COMPETICAO = Campeonato.CAMPEONATO_MONTAR_COMPETICOES(CAMPEONATO_TOZTE, TIMES_COMPETICAO_GRUPO);


        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO);


        fmt.print("Divulgação das Datas Competições : {}", CAMPEONATO_TOZTE.getTextoZerado());

        Lista<String> eHazdesClassificatorias = new Lista<String>();
        eHazdesClassificatorias.adicionar("06:00");
        eHazdesClassificatorias.adicionar("06:50");
        eHazdesClassificatorias.adicionar("07:00");
        eHazdesClassificatorias.adicionar("07:50");
        eHazdesClassificatorias.adicionar("08:00");
        eHazdesClassificatorias.adicionar("08:50");

        Campeonato.CAMPEONATO_AGENDAR_DATAS_DOS_JOGOS(CAMPEONATO_TOZTE, eHazdesClassificatorias, TIMES_COMPETICAO, true);


        Campeonato.CAMPEONATO_MARCAR_PRIMEIROS_E_ULTIMOS(TIMES_COMPETICAO);


        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO);


        Competicao.REALIZAR_PARTIDAS(TIMES_COMPETICAO);


        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO);

        Lista<Entidade> TIMES_COMPETICAO_CLASSIFICAO = Campeonato.CAMPEONATO_REALIZAR_CLASSIFICACAO(TIMES_COMPETICAO);


        //  ENTT.EXIBIR_TABELA(c12.getDados());


        fmt.print("HOJE - {}", Tronarko.getTozte().getTextoZerado());
        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO_CLASSIFICAO);

        Lista<Entidade> TIMES_COMPETICAO_CLASSIFICADOS = Campeonato.CAMPEONATO_TIMES_CLASSIFICADOS(TIMES_COMPETICAO_CLASSIFICAO);


        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO_CLASSIFICADOS);


        Lista<String> eHazdes = new Lista<String>();
        eHazdes.adicionar("07:00");
        eHazdes.adicionar("07:50");
        eHazdes.adicionar("08:00");
        eHazdes.adicionar("08:50");


        // OITAVAS DE FINAL

        Lista<Entidade> TIMES_COMPETICAO_CLASSIFICADOS_CHAVEADOS = Campeonato.CAMPEONATO_CLASSIFICADOS_OITAVAS(TIMES_COMPETICAO_CLASSIFICADOS);


        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO_CLASSIFICADOS_CHAVEADOS);

        Lista<Entidade> TIMES_COMPETICAO_OITAVAS = Campeonato.CAMPEONATO_ORGANIZAR_OITAVAS(TIMES_COMPETICAO_CLASSIFICADOS_CHAVEADOS);

        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO_OITAVAS);


        Campeonato.CAMPEONATO_AGENDAR_DATAS_DOS_JOGOS(CAMPEONATO_TOZTE, eHazdes, TIMES_COMPETICAO_OITAVAS, false);
        Campeonato.CAMPEONATO_MARCAR_PRIMEIROS_E_ULTIMOS(TIMES_COMPETICAO_OITAVAS);
        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO_OITAVAS);


        Competicao.REALIZAR_PARTIDAS_MATA_MATA(TIMES_COMPETICAO_OITAVAS);
        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO_OITAVAS);

        // QUARTAS DE FINAL

        Lista<Entidade> TIMES_COMPETICAO_CLASSIFICADOS_QUARTAS = Campeonato.CAMPEONATO_OBTER_VENCEDORES(TIMES_COMPETICAO_OITAVAS);

        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO_CLASSIFICADOS_QUARTAS);


        Lista<Entidade> TIMES_COMPETICAO_QUARTAS = Campeonato.CAMPEONATO_ORGANIZAR_QUARTAS(TIMES_COMPETICAO_OITAVAS);

        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO_QUARTAS);
        Campeonato.CAMPEONATO_AGENDAR_DATAS_DOS_JOGOS(CAMPEONATO_TOZTE, eHazdes, TIMES_COMPETICAO_QUARTAS, false);
        Campeonato.CAMPEONATO_MARCAR_PRIMEIROS_E_ULTIMOS(TIMES_COMPETICAO_QUARTAS);
        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO_QUARTAS);

        Competicao.REALIZAR_PARTIDAS_MATA_MATA(TIMES_COMPETICAO_QUARTAS);
        ENTT.EXIBIR_TABELA(TIMES_COMPETICAO_QUARTAS);


        ENTT.EXIBIR_DETALHADO(TIMES_COMPETICAO_QUARTAS.get(0));
        CompeticaoDetalhada.init(TIMES_COMPETICAO_QUARTAS.get(0));

        // SEMI-FINAL


        //FINAL


        //  criar();

    }


    public static void criar() {

        for (int t = 0; t < 4; t++) {
            String time_nome = PessoaNomeadorDeAkkax.toCapital(PessoaNomeadorDeAkkax.getSimples()).trim();

            fmt.print("CRIAR_JOGADOR(" + Strings.ASPAS(time_nome) + "," + Strings.ASPAS(PessoaNomeadorDeAkkax.get()) + "," + Strings.ASPAS("TREINADOR") + ");");

            for (int i = 0; i < 24; i++) {
                fmt.print("CRIAR_JOGADOR(" + Strings.ASPAS(time_nome) + "," + Strings.ASPAS(PessoaNomeadorDeAkkax.get()) + "," + Strings.ASPAS("JOGADOR") + ");");
            }

            for (int i = 0; i < 5; i++) {
                fmt.print("CRIAR_JOGADOR(" + Strings.ASPAS(time_nome) + "," + Strings.ASPAS(PessoaNomeadorDeAkkax.get()) + "," + Strings.ASPAS("MÉDICO") + ");");
            }
        }

    }


}

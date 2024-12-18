package apps.app_atzum;

import apps.app.AgendadorDeTarefas;
import apps.app_atzum.analisadores.AnalisadorClimatico;
import apps.app_atzum.analisadores.AnalisadorVegetacao;
import apps.app_atzum.analisadores.Publicador;
import apps.app_atzum.processo_criativo.AtzumAlfaCriativo;
import apps.app_atzum.processo_criativo.AtzumBetaCriativo;
import apps.app_atzum.servicos.*;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.fmt;
import libs.meta_functional.Acao;
import libs.tronarko.Tron;
import libs.tronarko.Tronarko;
import libs.tronarko.utils.StringTronarko;

public class AtzumProcessoCriativoEmTarefas {

    public static final String ARQUIVO_LOCAL_ALFA = "logs/alfa_processo_criativo.entts";
    public static final String ARQUIVO_LOCAL_BETA = "logs/beta_processo_criativo.entts";

    public static final int TRONARKO_INICIAR = 7000;


    public static void INIT(int quantidade_de_passos){

        while (quantidade_de_passos > 0) {

            AtzumProcessoCriativoEmTarefas.EXIBIR_PROCESSO();

            if (Strings.isDiferente(AtzumProcessoCriativoEmTarefas.GET_ALFA_TAREFA(), "TudoOK")) {
                AtzumProcessoCriativoEmTarefas.INIT_ALFA_SEQUENCIAL();
            } else {
                AtzumProcessoCriativoEmTarefas.INIT_BETA_SEQUENCIAL();
            }

            AtzumProcessoCriativoEmTarefas.EXIBIR_PROCESSO();

            quantidade_de_passos -= 1;
        }

    }



    public static void INIT_ALFA_SEQUENCIAL() {

        Lista<Entidade> alfa_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_ALFA));

        Entidade e_atividade = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "TarefaCorrente");
        Entidade e_alfa_tarefas = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "Tarefas");
        Entidade e_alfa_subtarefa = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "SubTarefas");


        Lista<Entidade> alfa_tarefas = e_alfa_tarefas.getEntidades();
        Lista<Entidade> alfa_subtarefas = e_alfa_subtarefa.getEntidades();


        Entidade e_tronarko = MARQUE_INICIO(alfa_tarefas, "TRONARKO");
        Entidade e_sub_atividade = MARQUE_INICIO(alfa_subtarefas, "TRONARKO");

        String tarefa = e_atividade.at("Tarefa");

        AgendadorDeTarefas tarefas = new AgendadorDeTarefas();

        AtzumAlfaCriativo.CRIAR_TAREFAS(tarefas,alfa_tarefas,alfa_subtarefas,e_tronarko,e_sub_atividade);


        tarefas.setTarefaCorrente(tarefa);
        tarefas.executeUma();

        e_atividade.at("Tarefa", tarefas.getTarefaCorrente());

        ENTT.GUARDAR(alfa_dados, AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_ALFA));

    }


    public static void INIT_BETA_SEQUENCIAL() {

        Lista<Entidade> beta_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_BETA));

        Entidade e_atividade = ENTT.GET_SEMPRE(beta_dados, "Conjunto", "TarefaCorrente");
        Entidade e_beta_comparativos = ENTT.GET_SEMPRE(beta_dados, "Conjunto", "Tarefas");

        Lista<Entidade> beta_tarefas = e_beta_comparativos.getEntidades();


        int tronarko = e_atividade.atIntOuPadrao("Tronarko", TRONARKO_INICIAR);
        String tarefa = e_atividade.at("Tarefa");


        fmt.print(">> Tarefa :: {} - {}", tronarko, tarefa);


        Entidade e_tronarko = MARQUE_INICIO(beta_tarefas, "TRONARKO_" + tronarko);


        BETA_EXIBIR_PUBLICACAO(beta_tarefas);


        AgendadorDeTarefas tarefas = new AgendadorDeTarefas();

        AtzumBetaCriativo.CRIAR_TAREFAS(tarefas,beta_tarefas,e_atividade,e_tronarko,tronarko);


        tarefas.setTarefaCorrente(tarefa);
        tarefas.executeUma();

        e_atividade.at("Tarefa", tarefas.getTarefaCorrente());


        ENTT.GUARDAR(beta_dados, AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_BETA));
    }


    public static Entidade MARQUE_INICIO(Lista<Entidade> comparativos, String nome) {
        ENTT.GET_SEMPRE(comparativos, "Acao", nome).at("Inicio", Tronarko.getTronAgora().getTextoZerado());

        return ENTT.GET_SEMPRE(comparativos, "Acao", nome);
    }

    public static void MARQUE_FIM(Lista<Entidade> comparativos, String nome) {

        ENTT.GET_SEMPRE(comparativos, "Acao", nome).at("Fim", Tronarko.getTronAgora().getTextoZerado());

        Entidade item = ENTT.GET_SEMPRE(comparativos, "Acao", nome);

        Tron iniciado = StringTronarko.PARSER_TRON(item.at("Inicio"));
        Tron terminado = StringTronarko.PARSER_TRON(item.at("Fim"));

        item.at("Tempo", "+ " + Tronarko.TRON_DIFERENCA(iniciado, terminado));

    }

    public static void MARQUE_DURACAO(Entidade e_tronarko,String ATIVIDADE_CORRENTE){
        e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));
    }

    public static String OBTER_TEMPO_MARCADO(Lista<Entidade> comparativos, String nome) {
        Entidade item = ENTT.GET_SEMPRE(comparativos, "Acao", nome);
        return item.at("Tempo");
    }

    public static void PUBLICAR(Lista<Entidade> comparativos) {
        ENTT.GUARDAR(comparativos, AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_BETA));
    }

    public static void BETA_EXIBIR_PUBLICACAO(Lista<Entidade> beta_tarefas) {
        ENTT.EXIBIR_TABELA_COM_NOME(beta_tarefas, "PROCESSO CRIATIVO ATZUM - BETA");
    }


    public static void ALFA_EXIBIR_PUBLICACAO(Lista<Entidade> alfa_tarefas) {
        ENTT.EXIBIR_TABELA_COM_NOME(alfa_tarefas, "PROCESSO CRIATIVO ATZUM - ALFA");
    }

    public static void ALFA_SUB_EXIBIR_PUBLICACAO(Lista<Entidade> alfa_subtarefas) {
        ENTT.EXIBIR_TABELA_COM_NOME(alfa_subtarefas, "PROCESSO CRIATIVO ATZUM - ALFA :: SUB");
    }


    public static void EXIBIR_PROCESSO() {

        Lista<Entidade> alfa_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_ALFA));

        Entidade e_atividade = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "TarefaCorrente");
        Entidade e_alfa_tarefas = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "Tarefas");
        Entidade e_alfa_subtarefa = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "SubTarefas");


        ENTT.EXIBIR_TABELA_COM_NOME(alfa_dados,"ALFA - GERAL");

        ALFA_EXIBIR_PUBLICACAO(e_alfa_tarefas.getEntidades());

        Lista<Entidade> alfa_sub_dados = e_alfa_subtarefa.getEntidades();
        if(alfa_sub_dados.getQuantidade()>0){
            ALFA_SUB_EXIBIR_PUBLICACAO(alfa_sub_dados.get(0).getEntidades());
        }

        Lista<Entidade> beta_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_BETA));
        BETA_EXIBIR_PUBLICACAO(beta_dados);

    }


    public static String GET_ALFA_TAREFA() {
        Lista<Entidade> alfa_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_ALFA));

        Entidade e_atividade = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "TarefaCorrente");
        Entidade e_alfa_tarefas = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "Tarefas");
        Entidade e_alfa_subtarefa = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "SubTarefas");


        String tarefa = e_atividade.at("Tarefa");
        return tarefa;
    }

    public static String GET_BETA_TAREFA() {
        Lista<Entidade> beta_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_BETA));
        Entidade e_atividade = ENTT.GET_SEMPRE(beta_dados, "Conjunto", "TarefaCorrente");
        String beta_tronarko = e_atividade.at("Tarefa");

        return beta_tronarko;
    }

    public static String GET_BETA_TRONARKO() {
        Lista<Entidade> beta_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_BETA));
        Entidade e_atividade = ENTT.GET_SEMPRE(beta_dados, "Conjunto", "TarefaCorrente");
        String beta_tronarko = e_atividade.at("Tronarko");

        return beta_tronarko;
    }


    public static void ALFA_ZERAR() {
        ENTT.GUARDAR(new Lista<Entidade>(), AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_ALFA));
    }

    public static void BETA_ZERAR() {
        ENTT.GUARDAR(new Lista<Entidade>(), AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_BETA));
    }


    public static void EXIBIR_EXECUTANDO(){
        fmt.print(">> Alfa -->> {} ", AtzumProcessoCriativoEmTarefas.GET_ALFA_TAREFA());
        fmt.print(">> Beta -->> {} :: {}", AtzumProcessoCriativoEmTarefas.GET_BETA_TRONARKO(),AtzumProcessoCriativoEmTarefas.GET_BETA_TAREFA());
    }
}

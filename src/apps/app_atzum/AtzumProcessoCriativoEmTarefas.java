package apps.app_atzum;

import apps.app.AgendadorDeTarefas;
import apps.app_atzum.analisadores.AnalisadorClimatico;
import apps.app_atzum.analisadores.AnalisadorVegetacao;
import apps.app_atzum.analisadores.Publicador;
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



    public static void INIT(int quantidade_de_passos){

        while (quantidade_de_passos > 0) {

            AtzumProcessoCriativoEmTarefas.EXIBIR_PROCESSO();

            if (Strings.isDiferente(AtzumProcessoCriativoEmTarefas.GET_ALFA_TAREFA(), "TudoOK")) {
                AtzumProcessoCriativoEmTarefas.INIT_ALFA_SEQUENCIAL();
            } else {
                AtzumProcessoCriativoEmTarefas.INIT_BETA_SEQUENCIAL();
            }

            quantidade_de_passos -= 1;
        }

    }



    public static void INIT_ALFA_SEQUENCIAL() {

        Lista<Entidade> alfa_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_ALFA));

        Entidade e_atividade = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "TarefaCorrente");
        Entidade e_alfa_comparativos = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "Tarefas");
        Entidade e_alfa_subtarefa = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "SubTarefas");


        Lista<Entidade> comparativos = e_alfa_comparativos.getEntidades();
        Lista<Entidade> sub_alfa = e_alfa_subtarefa.getEntidades();



        Entidade e_tronarko = MARQUE_INICIO(comparativos, "TRONARKO");
        Entidade e_sub_atividade = MARQUE_INICIO(sub_alfa, "TRONARKO");

        String tarefa = e_atividade.at("Tarefa");

        AgendadorDeTarefas tarefas = new AgendadorDeTarefas();


        tarefas.criarSequenciaDupla("", "ServicoInicial", "ServicoRegioes::ORGANIZAR_REGIOES", new Acao() {
            @Override
            public void fazer() {

                e_tronarko.at("Inicio", Tronarko.getTronAgora().getTextoZerado());
                e_tronarko.at("Fim", "");

                ENTT.GUARDAR(new Lista<Entidade>(), AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_ALFA));


                String ATIVIDADE_CORRENTE = "ServicoInicial";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoInicial.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);

            }
        });


        // SUBTAREFAS

        tarefas.criarSequencia("ServicoRegioes::ORGANIZAR_REGIOES", "ServicoRegioes::EXPANDIR_REGIOES_ATE_A_MARGEM", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoRegioes";
                String SUB_ATIVIDADE_CORRENTE = "ServicoRegioes::ORGANIZAR_REGIOES";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_INICIO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);

                ServicoRegioes.ORGANIZAR_REGIOES();
                //  MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_FIM(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                e_sub_atividade.at(SUB_ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE));

                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);
                ALFA_SUB_EXIBIR_PUBLICACAO(sub_alfa);

            }
        });

        tarefas.criarSequencia("ServicoRegioes::EXPANDIR_REGIOES_ATE_A_MARGEM", "ServicoRegioes::EXTRAIR_REGIOES_CONTORNOS", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoRegioes";
                String SUB_ATIVIDADE_CORRENTE = "ServicoRegioes::EXPANDIR_REGIOES_ATE_A_MARGEM";

                MARQUE_INICIO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);

                //    MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoRegioes.EXPANDIR_REGIOES_ATE_A_MARGEM();
                //  MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                MARQUE_FIM(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                e_sub_atividade.at(SUB_ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);
                ALFA_SUB_EXIBIR_PUBLICACAO(sub_alfa);

            }
        });

        tarefas.criarSequencia("ServicoRegioes::EXTRAIR_REGIOES_CONTORNOS", "ServicoRegioes::ORGANIZAR_DADOS_REGIOES", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoRegioes";
                String SUB_ATIVIDADE_CORRENTE = "ServicoRegioes::EXTRAIR_REGIOES_CONTORNOS";

                MARQUE_INICIO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                ServicoRegioes.EXTRAIR_REGIOES_CONTORNOS();
                MARQUE_FIM(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                e_sub_atividade.at(SUB_ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE));
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);
                ALFA_SUB_EXIBIR_PUBLICACAO(sub_alfa);

            }
        });

        tarefas.criarSequencia("ServicoRegioes::ORGANIZAR_DADOS_REGIOES", "ServicoRegioes::EXTRAIR_CONTORNO_OCEANICO", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoRegioes";
                String SUB_ATIVIDADE_CORRENTE = "ServicoRegioes::ORGANIZAR_DADOS_REGIOES";

                MARQUE_INICIO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                ServicoRegioes.ORGANIZAR_DADOS_REGIOES();
                MARQUE_FIM(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                e_sub_atividade.at(SUB_ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE));
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);
                ALFA_SUB_EXIBIR_PUBLICACAO(sub_alfa);

            }
        });

        tarefas.criarSequencia("ServicoRegioes::EXTRAIR_CONTORNO_OCEANICO", "ServicoRegioes::EXTRAIR_DISTANCIA_OCEANICA", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoRegioes";
                String SUB_ATIVIDADE_CORRENTE = "ServicoRegioes::EXTRAIR_CONTORNO_OCEANICO";

                MARQUE_INICIO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                ServicoRegioes.EXTRAIR_CONTORNO_OCEANICO();
                MARQUE_FIM(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                e_sub_atividade.at(SUB_ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE));
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);
                ALFA_SUB_EXIBIR_PUBLICACAO(sub_alfa);

            }
        });

        tarefas.criarSequencia("ServicoRegioes::EXTRAIR_DISTANCIA_OCEANICA", "ServicoRegioes::PROXIMIDADE_COM_OCEANO", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoRegioes";
                String SUB_ATIVIDADE_CORRENTE = "ServicoRegioes::EXTRAIR_DISTANCIA_OCEANICA";

                MARQUE_INICIO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                ServicoRegioes.EXTRAIR_DISTANCIA_OCEANICA();
                MARQUE_FIM(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                e_sub_atividade.at(SUB_ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE));
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);
                ALFA_SUB_EXIBIR_PUBLICACAO(sub_alfa);

            }
        });

        tarefas.criarSequencia("ServicoRegioes::PROXIMIDADE_COM_OCEANO", "ServicoRegioes::PROXIMIDADE_COM_TERRA", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoRegioes";
                String SUB_ATIVIDADE_CORRENTE = "ServicoRegioes::PROXIMIDADE_COM_OCEANO";

                MARQUE_INICIO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                ServicoRegioes.PROXIMIDADE_COM_OCEANO();
                MARQUE_FIM(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                e_sub_atividade.at(SUB_ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE));
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);
                ALFA_SUB_EXIBIR_PUBLICACAO(sub_alfa);

            }
        });

        tarefas.criarSequencia("ServicoRegioes::PROXIMIDADE_COM_TERRA", "ServicoRegioes::ORGANIZAR_DADOS_PLANETA", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoRegioes";
                String SUB_ATIVIDADE_CORRENTE = "ServicoRegioes::PROXIMIDADE_COM_TERRA";

                MARQUE_INICIO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                ServicoRegioes.PROXIMIDADE_COM_TERRA();
                MARQUE_FIM(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                e_sub_atividade.at(SUB_ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE));
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);
                ALFA_SUB_EXIBIR_PUBLICACAO(sub_alfa);

            }
        });


        tarefas.criarSequencia("ServicoRegioes::ORGANIZAR_DADOS_PLANETA", "ServicoRegioes::ORGANIZAR_OCEANOS", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoRegioes";
                String SUB_ATIVIDADE_CORRENTE = "ServicoRegioes::ORGANIZAR_DADOS_PLANETA";

                MARQUE_INICIO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                ServicoRegioes.ORGANIZAR_DADOS_PLANETA();
                MARQUE_FIM(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                e_sub_atividade.at(SUB_ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE));
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);
                ALFA_SUB_EXIBIR_PUBLICACAO(sub_alfa);

            }
        });

        tarefas.criarSequencia("ServicoRegioes::ORGANIZAR_OCEANOS", "ServicoRegioes::RENDERIZAR_OCEANOS", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoRegioes";
                String SUB_ATIVIDADE_CORRENTE = "ServicoRegioes::ORGANIZAR_OCEANOS";

                MARQUE_INICIO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                ServicoRegioes.ORGANIZAR_OCEANOS();
                MARQUE_FIM(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                e_sub_atividade.at(SUB_ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE));
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);
                ALFA_SUB_EXIBIR_PUBLICACAO(sub_alfa);

            }
        });


        tarefas.criarSequencia("ServicoRegioes::RENDERIZAR_OCEANOS", "ServicoTectonico", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoRegioes";
                String SUB_ATIVIDADE_CORRENTE = "ServicoRegioes::RENDERIZAR_OCEANOS";

                MARQUE_INICIO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                ServicoRegioes.RENDERIZAR_OCEANOS();
                MARQUE_FIM(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                e_sub_atividade.at(SUB_ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE));


                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);
                ALFA_SUB_EXIBIR_PUBLICACAO(sub_alfa);

            }
        });

        tarefas.criarSequencia("ServicoTectonico", "ServicoRelevo", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoTectonico";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoTectonico.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);

            }
        });





        // FIM SUBTAREFAS

        tarefas.criarSequencia("ServicoRelevo", "ServicoUmidade", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoRelevo";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoRelevo.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);

            }
        });

        tarefas.criarSequencia("ServicoUmidade", "ServicoTemperatura", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoUmidade";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoUmidade.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);

            }
        });


        tarefas.criarSequencia("ServicoTemperatura", "ServicoCorrelacionar", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoTemperatura";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoTemperatura.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);

            }
        });


        tarefas.criarSequencia("ServicoCorrelacionar", "ServicoMassasDeAr", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoCorrelacionar";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoCorrelacionar.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);

            }
        });


        tarefas.criarSequencia("ServicoMassasDeAr", "ServicoSensores", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoMassasDeAr";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoMassasDeAr.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);

            }
        });


        tarefas.criarSequencia("ServicoSensores", "EXPORTAR_ATZUM", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoSensores";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoSensores.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);

                e_tronarko.at("Fim", Tronarko.getTronAgora().getTextoZerado());

            }
        });

        tarefas.criarSequencia("EXPORTAR_ATZUM", "TudoOK", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "EXPORTAR_ATZUM";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoExportarTronarko.EXPORTAR_ATZUM();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                ALFA_EXIBIR_PUBLICACAO(comparativos);

                e_tronarko.at("Fim", Tronarko.getTronAgora().getTextoZerado());

            }
        });





        tarefas.setTarefaCorrente(tarefa);
        tarefas.executeUma();

        e_atividade.at("Tarefa", tarefas.getTarefaCorrente());

        ENTT.GUARDAR(alfa_dados, AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_ALFA));

    }


    public static void INIT_BETA_SEQUENCIAL() {

        Lista<Entidade> beta_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_BETA));

        Entidade e_atividade = ENTT.GET_SEMPRE(beta_dados, "Conjunto", "TarefaCorrente");
        Entidade e_beta_comparativos = ENTT.GET_SEMPRE(beta_dados, "Conjunto", "Tarefas");

        Lista<Entidade> comparativos = e_beta_comparativos.getEntidades();


        int tronarko = e_atividade.atIntOuPadrao("Tronarko", 7000);
        String tarefa = e_atividade.at("Tarefa");


        fmt.print(">> Tarefa :: {} - {}", tronarko, tarefa);


        Entidade e_tronarko = MARQUE_INICIO(comparativos, "TRONARKO_" + tronarko);


        BETA_EXIBIR_PUBLICACAO(comparativos);


        AgendadorDeTarefas tarefas = new AgendadorDeTarefas();

        tarefas.criarSequenciaDupla("", "CONSTRUIR_TRONARKO", "CALCULAR_TRONARKO_TRANSICAO", new Acao() {
            @Override
            public void fazer() {

                e_tronarko.at("Inicio", Tronarko.getTronAgora().getTextoZerado());
                e_tronarko.at("Fim", "");

                String ATIVIDADE_CORRENTE = "CONSTRUIR_TRONARKO";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoTronarko.CONSTRUIR_TRONARKO();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                PUBLICAR(comparativos);
                BETA_EXIBIR_PUBLICACAO(comparativos);

            }
        });


        tarefas.criarSequencia("CALCULAR_TRONARKO_TRANSICAO", "SENSORES_ORGANIZAR_POR_SENSORES_COM_QUADRUM", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "CALCULAR_TRONARKO_TRANSICAO";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoTronarko.CALCULAR_TRONARKO_TRANSICAO(tronarko > 7000);
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                PUBLICAR(comparativos);
                BETA_EXIBIR_PUBLICACAO(comparativos);

            }
        });

        tarefas.criarSequencia("SENSORES_ORGANIZAR_POR_SENSORES_COM_QUADRUM", "SENSORES_ORGANIZAR_POR_SENSORES_A_PARTIR_DE_QUADRUM", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "SENSORES_ORGANIZAR_POR_SENSORES_COM_QUADRUM";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoTronarko.SENSORES_ORGANIZAR_POR_SENSORES_COM_QUADRUM();
                ServicoTronarko.EXIBIR_TRONARKO();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));
                PUBLICAR(comparativos);

            }
        });


        tarefas.criarSequencia("SENSORES_ORGANIZAR_POR_SENSORES_A_PARTIR_DE_QUADRUM", "OBSERAR_VARIADORES", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "SENSORES_ORGANIZAR_POR_SENSORES_A_PARTIR_DE_QUADRUM";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoTronarko.SENSORES_ORGANIZAR_POR_SENSORES_A_PARTIR_DE_QUADRUM();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));
                PUBLICAR(comparativos);
                BETA_EXIBIR_PUBLICACAO(comparativos);

            }
        });


        tarefas.criarSequencia("OBSERAR_VARIADORES", "CLIMA", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "OBSERAR_VARIADORES";

                if (tronarko > 7000) {

                    MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);

                    ServicoTronarko.OBSERAR_VARIADORES();

                    MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                    e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));
                    PUBLICAR(comparativos);
                    BETA_EXIBIR_PUBLICACAO(comparativos);

                }


            }
        });


        tarefas.criarSequencia("CLIMA", "VEGETACAO", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "CLIMA";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);

                AnalisadorClimatico.INIT();

                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));
                PUBLICAR(comparativos);
                BETA_EXIBIR_PUBLICACAO(comparativos);


            }
        });

        tarefas.criarSequencia("VEGETACAO", "PUBLICAR_DADOS", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "VEGETACAO";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                AnalisadorVegetacao.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));
                PUBLICAR(comparativos);
                BETA_EXIBIR_PUBLICACAO(comparativos);


            }
        });


        tarefas.criarSequencia("PUBLICAR_DADOS", "PROXIMIDADE_COM_OCEANO", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "PUBLICAR_DADOS";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);

                Publicador.PUBLICAR_INFO_CLIMATICO();
                Publicador.PUBLICAR_INFO_VEGETACAO();

                Publicador.PUBLICAR_DADOS();


                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));
                PUBLICAR(comparativos);
                BETA_EXIBIR_PUBLICACAO(comparativos);


            }
        });


        tarefas.criarSequencia("PROXIMIDADE_COM_OCEANO", "NOMEAR_CIDADES", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "PROXIMIDADE_COM_OCEANO";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                AtzumCentralDados.PROXIMIDADE_COM_OCENAO();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                PUBLICAR(comparativos);
                BETA_EXIBIR_PUBLICACAO(comparativos);

            }
        });


        tarefas.criarSequencia("NOMEAR_CIDADES", "ORGANIZAR_DADOS_TRONARKO", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "NOMEAR_CIDADES";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                AtzumCentralDados.NOMEAR_CIDADES();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                PUBLICAR(comparativos);
                BETA_EXIBIR_PUBLICACAO(comparativos);


            }
        });

        tarefas.criarSequencia("ORGANIZAR_DADOS_TRONARKO", "EXPORTAR_TRONARKO", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ORGANIZAR_DADOS_TRONARKO";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);

                ServicoExportarTronarko.EXPORTAR_SENSORES_SUPERARKO();
                ServicoExportarTronarko.EXPORTAR_MODELOS();
                ServicoExportarTronarko.EXPORTAR_INFOGRAFICOS();
                ServicoExportarTronarko.EXPORTAR_DADOS_CIDADES();
                ServicoExportarTronarko.CONSOLIDAR_DADOS_CIDADES();

                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                PUBLICAR(comparativos);
                BETA_EXIBIR_PUBLICACAO(comparativos);


            }
        });

        tarefas.criarSequencia("EXPORTAR_TRONARKO", "PROXIMO_TRONARKO", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "EXPORTAR_TRONARKO";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);

                ServicoExportarTronarko.EXPORTAR_TRONARKO();

                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                e_tronarko.at(ATIVIDADE_CORRENTE, OBTER_TEMPO_MARCADO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE));

                PUBLICAR(comparativos);
                BETA_EXIBIR_PUBLICACAO(comparativos);


            }
        });


        tarefas.criarSequencia("PROXIMO_TRONARKO", "CONSTRUIR_TRONARKO", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "PROXIMO_TRONARKO";

                e_tronarko.at("Fim", Tronarko.getTronAgora().getTextoZerado());

                e_atividade.at("Tronarko", tronarko + 1);

            }
        });


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


    public static String OBTER_TEMPO_MARCADO(Lista<Entidade> comparativos, String nome) {
        Entidade item = ENTT.GET_SEMPRE(comparativos, "Acao", nome);
        return item.at("Tempo");
    }

    public static void PUBLICAR(Lista<Entidade> comparativos) {
        ENTT.GUARDAR(comparativos, AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_BETA));
    }

    public static void BETA_EXIBIR_PUBLICACAO(Lista<Entidade> comparativos) {
        ENTT.EXIBIR_TABELA_COM_NOME(comparativos, "PROCESSO CRIATIVO ATZUM - BETA");
    }


    public static void ALFA_EXIBIR_PUBLICACAO(Lista<Entidade> comparativos) {
        ENTT.EXIBIR_TABELA_COM_NOME(comparativos, "PROCESSO CRIATIVO ATZUM - ALFA");
    }

    public static void ALFA_SUB_EXIBIR_PUBLICACAO(Lista<Entidade> dados) {
        ENTT.EXIBIR_TABELA_COM_NOME(dados, "PROCESSO CRIATIVO ATZUM - ALFA :: SUB");
    }


    public static void EXIBIR_PROCESSO() {

        Lista<Entidade> alfa_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_ALFA));

        Entidade e_atividade = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "TarefaCorrente");
        Entidade e_alfa_comparativos = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "Tarefas");
        Entidade e_alfa_subtarefa = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "SubTarefas");


        ENTT.EXIBIR_TABELA_COM_NOME(alfa_dados,"ALFA - GERAL");

        ALFA_EXIBIR_PUBLICACAO(e_alfa_comparativos.getEntidades());
        //  ALFA_EXIBIR_PUBLICACAO(alfa_dados.get(1).getEntidades());

        Lista<Entidade> alfa_sub_dados = e_alfa_subtarefa.getEntidades();
        if(alfa_sub_dados.getQuantidade()>0){
            ALFA_SUB_EXIBIR_PUBLICACAO(alfa_sub_dados.get(0).getEntidades());
        }
        // ALFA_SUB_EXIBIR_PUBLICACAO(alfa_sub_dados);

        Lista<Entidade> beta_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_BETA));
        BETA_EXIBIR_PUBLICACAO(beta_dados);

    }


    public static String GET_ALFA_TAREFA() {
        Lista<Entidade> alfa_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO(ARQUIVO_LOCAL_ALFA));

        Entidade e_atividade = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "TarefaCorrente");
        Entidade e_alfa_comparativos = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "Tarefas");
        Entidade e_alfa_subtarefa = ENTT.GET_SEMPRE(alfa_dados, "Conjunto", "SubTarefas");


        String tarefa = e_atividade.at("Tarefa");
        return tarefa;
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
}

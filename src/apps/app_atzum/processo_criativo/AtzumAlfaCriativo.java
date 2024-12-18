package apps.app_atzum.processo_criativo;

import apps.app.AgendadorDeTarefas;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumProcessoCriativoEmTarefas;
import apps.app_atzum.servicos.*;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.meta_functional.Acao;
import libs.tronarko.Tronarko;

public class AtzumAlfaCriativo {

    public static void CRIAR_TAREFAS(AgendadorDeTarefas tarefas, Lista<Entidade> alfa_tarefas,Lista<Entidade> alfa_subtarefas,Entidade e_tronarko, Entidade e_sub_atividade) {


        tarefas.criarSequenciaDupla("", "ServicoInicial", "ServicoRegioes::ORGANIZAR_REGIOES", new Acao() {
            @Override
            public void fazer() {

                e_tronarko.at("Inicio", Tronarko.getTronAgora().getTextoZerado());
                e_tronarko.at("Fim", "");

                AtzumProcessoCriativoEmTarefas.ALFA_ZERAR();
                AtzumProcessoCriativoEmTarefas.BETA_ZERAR();


                String ATIVIDADE_CORRENTE = "ServicoInicial";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoInicial.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);

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

                MARQUE_FIM(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_sub_atividade, SUB_ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);
                ALFA_SUB_EXIBIR_PUBLICACAO(alfa_subtarefas);

            }
        });

        tarefas.criarSequencia("ServicoRegioes::EXPANDIR_REGIOES_ATE_A_MARGEM", "ServicoRegioes::EXTRAIR_REGIOES_CONTORNOS", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoRegioes";
                String SUB_ATIVIDADE_CORRENTE = "ServicoRegioes::EXPANDIR_REGIOES_ATE_A_MARGEM";

                MARQUE_INICIO(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);

                ServicoRegioes.EXPANDIR_REGIOES_ATE_A_MARGEM();

                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                MARQUE_FIM(e_sub_atividade.getEntidades(), SUB_ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_sub_atividade, SUB_ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);
                ALFA_SUB_EXIBIR_PUBLICACAO(alfa_subtarefas);

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
                MARQUE_DURACAO(e_sub_atividade, SUB_ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);
                ALFA_SUB_EXIBIR_PUBLICACAO(alfa_subtarefas);

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
                MARQUE_DURACAO(e_sub_atividade, SUB_ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);
                ALFA_SUB_EXIBIR_PUBLICACAO(alfa_subtarefas);

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
                MARQUE_DURACAO(e_sub_atividade, SUB_ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);
                ALFA_SUB_EXIBIR_PUBLICACAO(alfa_subtarefas);

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
                MARQUE_DURACAO(e_sub_atividade, SUB_ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);
                ALFA_SUB_EXIBIR_PUBLICACAO(alfa_subtarefas);

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
                MARQUE_DURACAO(e_sub_atividade, SUB_ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);
                ALFA_SUB_EXIBIR_PUBLICACAO(alfa_subtarefas);

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
                MARQUE_DURACAO(e_sub_atividade, SUB_ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);
                ALFA_SUB_EXIBIR_PUBLICACAO(alfa_subtarefas);

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
                MARQUE_DURACAO(e_sub_atividade, SUB_ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);
                ALFA_SUB_EXIBIR_PUBLICACAO(alfa_subtarefas);

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
                MARQUE_DURACAO(e_sub_atividade, SUB_ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);
                ALFA_SUB_EXIBIR_PUBLICACAO(alfa_subtarefas);

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
                MARQUE_DURACAO(e_sub_atividade, SUB_ATIVIDADE_CORRENTE);


                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);
                ALFA_SUB_EXIBIR_PUBLICACAO(alfa_subtarefas);

            }
        });

        tarefas.criarSequencia("ServicoTectonico", "ServicoRelevo", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoTectonico";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoTectonico.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);

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
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);

            }
        });

        tarefas.criarSequencia("ServicoUmidade", "ServicoTemperatura", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoUmidade";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoUmidade.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);

            }
        });


        tarefas.criarSequencia("ServicoTemperatura", "ServicoCorrelacionar", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoTemperatura";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoTemperatura.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);

            }
        });


        tarefas.criarSequencia("ServicoCorrelacionar", "ServicoMassasDeAr", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoCorrelacionar";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoCorrelacionar.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);

            }
        });


        tarefas.criarSequencia("ServicoMassasDeAr", "ServicoSensores", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoMassasDeAr";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoMassasDeAr.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);

            }
        });


        tarefas.criarSequencia("ServicoSensores", "EXPORTAR_ATZUM", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "ServicoSensores";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoSensores.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);

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
                MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);

                ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);

                e_tronarko.at("Fim", Tronarko.getTronAgora().getTextoZerado());

            }
        });

    }



    public static void PUBLICAR(Lista<Entidade> comparativos) {
        AtzumProcessoCriativoEmTarefas.PUBLICAR(comparativos);
    }

    public static Entidade MARQUE_INICIO(Lista<Entidade> comparativos, String nome) {
        return AtzumProcessoCriativoEmTarefas.MARQUE_INICIO(comparativos, nome);
    }

    public static void MARQUE_FIM(Lista<Entidade> comparativos, String nome) {
        AtzumProcessoCriativoEmTarefas.MARQUE_FIM(comparativos, nome);
    }

    public static void MARQUE_DURACAO(Entidade e_tronarko, String ATIVIDADE_CORRENTE) {
        AtzumProcessoCriativoEmTarefas.MARQUE_DURACAO(e_tronarko, ATIVIDADE_CORRENTE);
    }

    public static void ALFA_EXIBIR_PUBLICACAO(Lista<Entidade> alfa_tarefas) {
        AtzumProcessoCriativoEmTarefas.ALFA_EXIBIR_PUBLICACAO(alfa_tarefas);
    }

    public static void ALFA_SUB_EXIBIR_PUBLICACAO(Lista<Entidade> alfa_subtarefas) {
        AtzumProcessoCriativoEmTarefas.ALFA_SUB_EXIBIR_PUBLICACAO(alfa_subtarefas);
    }
}

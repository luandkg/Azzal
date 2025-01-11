package apps.app_atzum.processo_criativo;

import apps.app.AgendadorDeTarefas;
import apps.app_atzum.AtzumProcessoCriativoEmTarefas;
import apps.app_atzum.analisadores.AnalisadorClimatico;
import apps.app_atzum.analisadores.AnalisadorVegetacao;
import apps.app_atzum.analisadores.Publicador;
import apps.app_atzum.servicos.AtzumCentralDados;
import apps.app_atzum.servicos.ServicoExportarTronarko;
import apps.app_atzum.servicos.ServicoFenomenoTectonico;
import apps.app_atzum.servicos.ServicoTronarko;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.meta_functional.Acao;
import libs.tronarko.Tronarko;

public class AtzumBetaCriativo {


    public static void CRIAR_TAREFAS(AgendadorDeTarefas tarefas, Lista<Entidade> beta_tarefas , Entidade e_atividade ,Entidade e_tronarko,int tronarko ){

        tarefas.criarSequenciaDupla("", "CONSTRUIR_TRONARKO", "TRONARKO_PROCESSAR_SUPERARKOS", new Acao() {
            @Override
            public void fazer() {

                e_tronarko.at("Inicio", Tronarko.getTronAgora().getTextoZerado());
                e_tronarko.at("Fim", "");

                String ATIVIDADE_CORRENTE = "CONSTRUIR_TRONARKO";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoTronarko.CONSTRUIR_TRONARKO();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko,ATIVIDADE_CORRENTE);

                BETA_EXIBIR_PUBLICACAO(beta_tarefas);

            }
        });


        tarefas.criarSequencia("TRONARKO_PROCESSAR_SUPERARKOS", "FENOMENOS_TECTONICOS", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "TRONARKO_PROCESSAR_SUPERARKOS";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);

                ServicoTronarko.TRONARKO_PROCESSAR_SUPERARKOS(tronarko > AtzumProcessoCriativoEmTarefas.TRONARKO_INICIAR,tronarko);

                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko,ATIVIDADE_CORRENTE);

                BETA_EXIBIR_PUBLICACAO(beta_tarefas);

            }
        });

        tarefas.criarSequencia("FENOMENOS_TECTONICOS", "SENSORES_ORGANIZAR_POR_SENSORES_COM_QUADRUM", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "FENOMENOS_TECTONICOS";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);

                if(tronarko==AtzumProcessoCriativoEmTarefas.TRONARKO_INICIAR){
                    ServicoFenomenoTectonico.ZERAR();
                }

                ServicoFenomenoTectonico.INIT(tronarko);

                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko,ATIVIDADE_CORRENTE);

                BETA_EXIBIR_PUBLICACAO(beta_tarefas);

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
                MARQUE_DURACAO(e_tronarko,ATIVIDADE_CORRENTE);


            }
        });


        tarefas.criarSequencia("SENSORES_ORGANIZAR_POR_SENSORES_A_PARTIR_DE_QUADRUM", "OBSERAR_VARIADORES", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "SENSORES_ORGANIZAR_POR_SENSORES_A_PARTIR_DE_QUADRUM";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                ServicoTronarko.SENSORES_ORGANIZAR_POR_SENSORES_A_PARTIR_DE_QUADRUM();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko,ATIVIDADE_CORRENTE);

                BETA_EXIBIR_PUBLICACAO(beta_tarefas);

            }
        });


        tarefas.criarSequencia("OBSERAR_VARIADORES", "CLIMA", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "OBSERAR_VARIADORES";

                if (tronarko > AtzumProcessoCriativoEmTarefas.TRONARKO_INICIAR) {

                    MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);

                    ServicoTronarko.OBSERAR_VARIADORES();

                    MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                    MARQUE_DURACAO(e_tronarko,ATIVIDADE_CORRENTE);

                    BETA_EXIBIR_PUBLICACAO(beta_tarefas);

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
                MARQUE_DURACAO(e_tronarko,ATIVIDADE_CORRENTE);

                BETA_EXIBIR_PUBLICACAO(beta_tarefas);


            }
        });

        tarefas.criarSequencia("VEGETACAO", "PUBLICAR_DADOS", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "VEGETACAO";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                AnalisadorVegetacao.INIT();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko,ATIVIDADE_CORRENTE);

                BETA_EXIBIR_PUBLICACAO(beta_tarefas);


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
                MARQUE_DURACAO(e_tronarko,ATIVIDADE_CORRENTE);

                BETA_EXIBIR_PUBLICACAO(beta_tarefas);


            }
        });


        tarefas.criarSequencia("PROXIMIDADE_COM_OCEANO", "NOMEAR_CIDADES", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "PROXIMIDADE_COM_OCEANO";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                AtzumCentralDados.PROXIMIDADE_COM_OCENAO();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko,ATIVIDADE_CORRENTE);

                BETA_EXIBIR_PUBLICACAO(beta_tarefas);

            }
        });


        tarefas.criarSequencia("NOMEAR_CIDADES", "ORGANIZAR_DADOS_TRONARKO", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "NOMEAR_CIDADES";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                AtzumCentralDados.NOMEAR_CIDADES();
                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko,ATIVIDADE_CORRENTE);

                BETA_EXIBIR_PUBLICACAO(beta_tarefas);


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
                MARQUE_DURACAO(e_tronarko,ATIVIDADE_CORRENTE);

                BETA_EXIBIR_PUBLICACAO(beta_tarefas);


            }
        });

        tarefas.criarSequencia("EXPORTAR_TRONARKO", "PROXIMO_TRONARKO", new Acao() {
            @Override
            public void fazer() {

                String ATIVIDADE_CORRENTE = "EXPORTAR_TRONARKO";

                MARQUE_INICIO(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);

                ServicoExportarTronarko.EXPORTAR_TRONARKO();

                MARQUE_FIM(e_tronarko.getEntidades(), ATIVIDADE_CORRENTE);
                MARQUE_DURACAO(e_tronarko,ATIVIDADE_CORRENTE);

                BETA_EXIBIR_PUBLICACAO(beta_tarefas);


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

    public static void BETA_EXIBIR_PUBLICACAO(Lista<Entidade> comparativos) {
         AtzumProcessoCriativoEmTarefas.BETA_EXIBIR_PUBLICACAO(comparativos);
    }
}

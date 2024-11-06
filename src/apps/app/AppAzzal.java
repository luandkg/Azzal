package apps.app;

import apps.app_atzum.AtzumProcessoCriativoEmTarefas;
import apps.app_azzal.VamosCalcular;
import libs.aqz.AQZ;
import libs.aqz.AQZArquivoExternamente;
import libs.aqz.AQZUTF8;
import libs.bs.ObservadorItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.meta_functional.FuncaoAlfa;
import libs.tempo.Calendario;
import libs.tempo.Data;
import libs.tempo.Horario;
import libs.tronarko.Hazde;
import libs.tronarko.Intervalos.Hazde_Intervalo;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import libs.tronarko.utils.StringTronarko;
import libs.verkuz.VerkuzImplementador;

public class AppAzzal {

    public static void main(String[] args) {

        // AzzalUnico.unico("AppAlpha", 1600, 1020, new Alpha());

        // AzzalUnico.unico("AppAzzal", 1600, 1020, new AppGlobal());

        // AzzalUnico.unico("apps.AppFuzz", 1600, 1020, new apps.app_fuzz.AppFuzz());

        //  AzzalUnico.unico("Tronarko", 1550, 1100, new apps.app_tronarko.AppTronarko());

        //AzzalUnico .unico("Tronarko.Alarme", 900, 800, new apps.app_tronarko.AppAlarme());
        //  AzzalUnico.unico("Astros", 1550, 1100, new apps.app_tronarko.AppAstros());

        // AzzalUnico.unico("Attuz", 3000, 1000, new AppAttuz());
        // AzzalUnico.unico("Citatte", 2000, 1000, new AppCitatte());

        //  AzzalUnico.unico("Citatte Modelum", 2000, 1000, new AppCitatteModelum());

        // CidadeGeradorAleatorio.init_cidades();
        // CidadeGeradorAleatorio.init_cidade();
        // CidadeGeradorAleatorio.render_cidade_entre_vias("1");
        // CidadeGeradorAleatorio.render_cidade_entre_vias("2");
        // CidadeGeradorAleatorio.render_cidade_entre_vias("3");

        // CidadeGeradorAleatorio.render_cidade_entre_vias("melhor_de_3");
        // CidadeGeradorAleatorio.render_cidade_entre_vias("melhor_de_100");

        // app_testes.init();

        // CidadeGeradorAleatorio.render_cidade();
        // CidadeGeradorAleatorio.render_gama();

        // AppAttuzServittos.init();

        // AzzalUnico.unico("Linha Do Tempo", 900, 1000, new AppLinhaDoTempo());

        // AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new Alpha());
        // AzzalUnico.unico("apps.AppAudio", 700, 1020, new AppAudio());

        // AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new C1());
        // AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new CenaBanco());
        // AzzalUnico.unico("Quadrante Espacial", 1500, 1010, new QuadranteEspacial());

        // AzzalUnico.unico("apps.AppAzzal", 1100, 800, new Fisica.Fisica());

        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new Letras());

        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new C1());
        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new Chiado());
        // AzzalUnico.unico("apps.AppAzzal", 1300, 1000, new CidadeCena());

        // AzzalUnico.unico("Arch", 1300, 1000, new AppArch());

        // Assembler mAssembler = new Assembler();
        // mAssembler.compilar("res/montagem.l1", "res/montagem.l0", "res/montagem.o");

        // AzzalUnico.unico("", 1100, 900, new TerrariaCena());

        // AzzalUnico.unico("", 1100, 900, new FonteGeradorCena());

        // AzzalUnico.unico("", 1100, 900, new CenaLetrador());

        // TG22.init();

        // AzzalUnico.unico("Editor - luan.dkg", 1000, 1000, new AppKrhonos());

        // AppGamaFS.init();

        // Servittor.onServico("Arquivador", new ArquivosServicos());

        // AzzalUnico.unico("Visualizador IM", 1500, 1020, new AppImagem());
        // AzzalUnico.unico("apps.AppAzzal", 1500, 1020, new AppAlbumDeImagens());

        //  AzzalUnico.unico("AppAnimacao", 1100, 900, new AppAnimacao());

        // VideoSequenciador.criar("/home/luan/Vídeos/vi/ecossistema_01.vi", 800, 801,
        // "/home/luan/Imagens/ecossistema_01/S", 0, 97, ".png");
        // VideoSequenciador.criar("/home/luan/Vídeos/vi/ecossistema_02.vi", 800, 801,
        // "/home/luan/Imagens/ecossistema_02/S", 0, 365, ".png");
        // VideoSequenciador.criar("/home/luan/Vídeos/vi/alunos_v2.vi",3000,2700,"/home/luan/Imagens/alunos_vi/S",
        // 0, 99, ".png");

        // VideoCodecador.abrir("/home/luan/Vídeos/vi/ecossistema_01.vi");

        // AzzalUnico.unico("AppVideo", 2000, 1100, new AppVideo());

        String ESCOLA_LOCAL = "/home/luan/Dropbox/CED 1";

        // Documentar.organizar(ESCOLA_LOCAL + "/Planejamento/planejamento_01.txt",
        // ESCOLA_LOCAL + "/Planejamento/PLANEJAMENTO - 1 SEMESTRE - PROFESSOR LUAN
        // FREITAS.pdf");
        // Documentar.organizar(ESCOLA_LOCAL + "/Planejamento/planejamento_02.txt",
        // ESCOLA_LOCAL + "/Planejamento/PLANEJAMENTO - 2 SEMESTRE - PROFESSOR LUAN
        // FREITAS.pdf");

        // planejamento.organizar("/home/luan/Dropbox/CED_01/Planejamento/pd3_8.txt",
        // "/home/luan/Dropbox/CED_01/Planejamento/PLANEJAMENTO - PROFº ELVES_PROFª.
        // IARA_PROFº LUAN -PLANEJAMENTO DE CURSO 2022.pdf");

        // libs.RhoBenchmark.libs.RhoBenchmark.organizar("res/libs.RhoBenchmark.dkg","/home/luan/Imagens/libs.RhoBenchmark.png");

        VamosCalcular vc = new VamosCalcular();
        // vc.init();

        // libs.Tronarko.Testes.init();

        // AzzalUnico.unico("App_LLCripto", 1100, 900, new App_LLCripto());

        VerkuzImplementador vi = new VerkuzImplementador();

        vi.init("/home/luan/dev/azzal/src/libs");
        vi.init("/home/luan/dev/azzal/src/libs/azzal");
        vi.init("/home/luan/dev/azzal/src/libs/mockui");

        // vi.init_bibliotecas("/home/luan/IdeaProjects/Azzal/src/libs");
        // vi.exibir();

        // vi.toClasse("/home/luan/IdeaProjects/Azzal/src/azzal_version/AzzalVersion.java","azzal_version","AzzalVersion");

        // AzzalUnico.unico("Corretor", 1100, 900, new AppCorretor());

        // AppMomentum.init();
        // HarrempluzCreator.criar();
        // HarrempluzCreator.visualizar();

        // GGDNA.init();

        // AppClassificador.init();


        // AppFit.init();

        // GGADF2023.init();

        // tempo_descritores();
        //  tempo();
        //tron_me();
        banco_me();
        //  tozterum();
        // metropoles();

        // AppFerias.recesso_2024_janeiro();

        //aqz_geral();

        // sequenciador();

        // Projettum.init_geral();

        // AzzalUnico.unico("", 1540, 900, new apps.app_workum.AppWorkum());

        // AppCampeonatum.init();

        // FITParser.init();

        fmt.print("Tronarko : {}", Tronarko.getTronAgora().getTextoZerado());

        //AtzumProcessoCriativo.init();

        // AtzumProcessoCriativoEmTarefas.EXIBIR_PROCESSO();

        // LimonTorrents.INIT();

        // ServicoExportarTronarko.EXPORTAR_ATZUM();
        // ServicoExportarTronarko.VER_ATZUM();

        // AppAtzum.INICIAR();

        //  TesteArquivoDS.alterar_ultimo();
        //  TesteArquivoDS.alterar_ultimo_dm();

        // ServicoTectonico.INIT();
        //  ServicoTectonico.RASTERIZAR();
        //ServicoTectonico.PLACA_AREA();

        int a = 0;
        while (a > 0) {

            AtzumProcessoCriativoEmTarefas.EXIBIR_PROCESSO();

            if (Strings.isDiferente(AtzumProcessoCriativoEmTarefas.GET_ALFA_TAREFA(), "TudoOK")) {
                AtzumProcessoCriativoEmTarefas.INIT_ALFA_SEQUENCIAL();
            } else {
                AtzumProcessoCriativoEmTarefas.INIT_BETA_SEQUENCIAL();
            }

            a -= 1;
        }

        String tronarko_corrente = AtzumProcessoCriativoEmTarefas.GET_BETA_TRONARKO();
        fmt.print(">> {}", tronarko_corrente);

        //  Palkum.init();

        // AtzumProcessoCriativoEmTarefas.INIT_BETA_SEQUENCIAL();

        //   AnalisadorEstruturado. SENSORES_DADOS_ORGANIZAR();
        // AnalisadorEstruturado.COMPACTAR_SENSORES_SUPERARKO();

        // ServicoExportarTronarko.EXPORTAR_SENSORES_SUPERARKO();
        // ServicoExportarTronarko.EXPORTAR_MODELOS();
        //  ServicoExportarTronarko.EXPORTAR_DADOS_CIDADES();
        //  ServicoExportarTronarko.CONSOLIDAR_DADOS_CIDADES();
        //   ServicoExportarTronarko.VER_DADOS_VEGETACAO();
        // ServicoExportarTronarko.VER_DADOS_CIDADES();

        //  AnalisadorEstruturado.VER_DADOS_V9();

        //TesteComQTTCacheVer.init();

        // JujutsuKaizen.init();

        //  PessoaNomeadorDeAkkax.VISUALIZAR_AMOSTRA_PEQUENA();

        // AppStrava.init();

        // Teste.init();

        // tempo();

        // Cartaze.init();

        // TronarkoOrganizarAssets.signos();

        //AppCartaze.INIT();

    }

    public static void tempo_descritores() {

        String arquivo_banco = "/home/luan/assets/tronarkum.az";


        AQZ.EM_ATUALIZACAO(arquivo_banco, "Tempo_v2", new FuncaoAlfa<RefBool, Entidade>() {
            @Override
            public RefBool fazer(Entidade entidade) {

                String data = Strings.GET_ATE(entidade.at("time"), " ");
                String horario = Strings.GET_DEPOIS(entidade.at("time"), " ");

                // if (Strings.isIgual(data, "2024-11-08") || Strings.isIgual(data, "2024-11-09")) {


                entidade.at("precipitation_probability", fmt.f3(entidade.at("precipitation_probability")));

                entidade.at("umidade", Strings.getParteNumerica(entidade.at("relative_humidity_2m")));

                if (entidade.atInt("umidade") >= 75) {
                    entidade.at("umidade", "VaiTerChuva");
                } else if (entidade.atInt("umidade") >= 60) {
                    entidade.at("umidade", "Umido");
                } else {
                    entidade.at("umidade", "Normal");
                }

                entidade.at("Tozte", Tronarko.getData(Data.toData(data).getTempoLegivel()).getTextoZerado());
                entidade.at("Hazde", Tronarko.getHora(Horario.toHorarioSemSegundos(horario).getTempo()).getTextoSemUzzonZerado());


                return new RefBool(true);
                //  }

                // return new RefBool(false);
            }
        });


        AQZ.EXIBIR_AMOSTRA(arquivo_banco, "Tempo_v2");

        AQZ.EXIBIR_DESCRITORES(arquivo_banco, "Tempo_v2");

        Lista<Entidade> pp_disp_humidity = AQZ.OBTER_DISPERSAO_SEM_EM_ZONAS(arquivo_banco, "Tempo_v2", "relative_humidity_2m", "%", ENTT.GET_ZONAS_PORCENTAGEM());
        ENTT.EXIBIR_TABELA_COM_TITULO(pp_disp_humidity, "relative_humidity_2m");


        Lista<Entidade> pp_temperature_2m = AQZ.OBTER_DISPERSAO_SEM_EM_4ZONAS_DOUBLE(arquivo_banco, "Tempo_v2", "temperature_2m", "°C");
        ENTT.EXIBIR_TABELA_COM_TITULO(pp_temperature_2m, "temperature_2m");

        fmt.print("Quantidade : {}", ENTT.ATRIBUTO_SOMAR(pp_disp_humidity, "Quantidade"));
        fmt.print("Quantidade : {}", ENTT.ATRIBUTO_SOMAR(pp_temperature_2m, "Quantidade"));


        // fmt.print("ISnumero : {}",Matematica.isNumeroInteiro("6.9"));

    }

    public static void tempo() {

        String arquivo_banco = "/home/luan/assets/tronarkum.az";

        Lista<Entidade> previsao = AppPrevisaoDoTempo.init();

        ENTT.EXIBIR_TABELA(ENTT.GET_ATRIBUTOS(previsao));

        ENTT.EXIBIR_TABELA(ENTT.GET_CAMPOS(previsao,
                Strings.CRIAR_LISTA("time", "temperature_2m", "relative_humidity_2m",
                        "windspeed_10m", "precipitation_probability", "rain")));


        Unico<String> tempos_insercao = new Unico<String>(Strings.IGUALAVEL());

        for (Entidade e : previsao) {
            String data = Strings.GET_ATE(e.at("time"), " ");
            tempos_insercao.item(data);
        }


        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "Tempo");
        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "Tempo_v2");


        for (String tempo_inserindo : tempos_insercao) {

            AQZ.EM_DESTRUICAO(arquivo_banco, "Tempo", new FuncaoAlfa<RefBool, Entidade>() {
                @Override
                public RefBool fazer(Entidade entidade) {

                    String data = Strings.GET_ATE(entidade.at("time"), " ");

                    if (Strings.isIgual(data, tempo_inserindo)) {
                        fmt.print(">> REMOVER PARA INSERCAO :: {} ->> {}", entidade.at("ID"), "<" + data + ">");
                        return new RefBool(true);
                    }

                    return new RefBool(false);
                }
            });

            AQZ.EM_DESTRUICAO(arquivo_banco, "Tempo_v2", new FuncaoAlfa<RefBool, Entidade>() {
                @Override
                public RefBool fazer(Entidade entidade) {

                    String data = Strings.GET_ATE(entidade.at("time"), " ");

                    if (Strings.isIgual(data, tempo_inserindo)) {
                        fmt.print(">> REMOVER PARA INSERCAO :: {} ->> {}", entidade.at("ID"), "<" + data + ">");
                        return new RefBool(true);
                    }

                    return new RefBool(false);
                }
            });

        }


        AQZ.INSERIR_VARIOS(arquivo_banco, "Tempo", previsao);

        AQZ.INSERIR_VARIOS(arquivo_banco, "Tempo_v2", ENTT.GET_CAMPOS(previsao,
                Strings.CRIAR_LISTA("time", "temperature_2m", "relative_humidity_2m",
                        "windspeed_10m", "precipitation_probability", "rain")));

        fmt.print("TEMPO ATUALIZADO !");


        Lista<Entidade> tempos = AQZ.EM_DISPERSAO(arquivo_banco, "Tempo_v2", new FuncaoAlfa<String, Entidade>() {
            @Override
            public String fazer(Entidade entidade) {

                String data = Strings.GET_ATE(entidade.at("time"), " ");
                // fmt.print(">> {} ->> {}",entidade.at("ID"),"<"+data+">");
                return data;
            }
        });


        ENTT.EXIBIR_TABELA_COM_NOME(tempos, "Tempo_v2 @Datas");


        AQZ.EM_DESTRUICAO(arquivo_banco, "Tempo_v2", new FuncaoAlfa<RefBool, Entidade>() {
            @Override
            public RefBool fazer(Entidade entidade) {

                String data = Strings.GET_ATE(entidade.at("time"), " ");

                if (Data.isDataValida(data)) {

                    Data data_hoje = Calendario.getDataHoje();
                    Data data_corrente = Data.toData(data);

                    if (data_corrente.isMenor(data_hoje)) {
                        fmt.print(">> REMOVER :: {} ->> {}", entidade.at("ID"), "<" + data_corrente.getTempo() + ">");
                        return new RefBool(true);
                    }

                }


                return new RefBool(false);
            }
        });

        fmt.print("Tempo    = {}", AQZ.COLECAO_CONTAGEM(arquivo_banco, "Tempo"));
        fmt.print("Tempo_v2 = {}", AQZ.COLECAO_CONTAGEM(arquivo_banco, "Tempo_v2"));


        // AQZ.ANALISAR(arquivo_banco);
        //AQZ.EXIBIR_COLECAO_PRIMARIA(arquivo_banco,"@Analise");


        AQZ.EXIBIR_DESCRITORES(arquivo_banco, "Tempo_v2");

    }

    public static void tozterum() {

        String arquivo_banco = "/home/luan/assets/testes/teste_alfa.az";

        AQZ.EXIBIR_ESTRUTURA(arquivo_banco);

        AQZ.EXIBIR_AMOSTRA(arquivo_banco, "STRAVA_ACOMPANHAMENTO_DADOS(LL)");


        AQZUTF8.EXIBIR_AMOSTRA(arquivo_banco, "STRAVA_ACOMPANHAMENTO_DADOS(LL)");
        AQZUTF8.EXIBIR_DESCRITORES(arquivo_banco, "STRAVA_ACOMPANHAMENTO_DADOS(LL)");

        //   AQZ.EXIBIR_DISPERSAO(arquivo_banco,"INMET.DADOS","Data");

    }

    public static void tron_me() {

        String arquivo_banco = "/home/luan/assets/tronarkum.az";

        String seq = Aleatorio.aleatorio_desses("BCDFGHJKLMNPQRSTVWXYZ", 10);

        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "Tronarkum");
        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "TronarkumDiario");
        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "Tempo");
        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "Tempo_v2");

        fmt.print(">> Inserir Tronarkum");

        AQZ.INSERIR(arquivo_banco, "Tronarkum",
                ENTT.CRIAR("Agora", Tronarko.getTronAgora().getTextoZerado(), "Sequencia", seq));

        fmt.print(">> Limpar TronarkumDiario");
        AQZ.LIMPAR_TUDO(arquivo_banco, "TronarkumDiario");

        Lista<Entidade> tronarko_logs = AQZ.COLECAO_ITENS(arquivo_banco, "Tronarkum");

        Unico<Tozte> toztes = new Unico<Tozte>(Tozte.IGUALDADE());

        StringTronarko st = new StringTronarko();

        for (Entidade obj : tronarko_logs) {
            Tozte proc_tozte = st.getTozteDeComplexo(obj.at("Agora"));

            if (toztes.item(proc_tozte)) {

                int quantidade = 0;

                Extremos<Hazde> hazde_extremos = new Extremos<Hazde>(Hazde.ORDENADOR());

                hazde_extremos.set(st.getHazdeDeComplexo(obj.at("Agora")));

                for (Entidade obj2 : tronarko_logs) {
                    Tozte proc_tozte_aqui = st.getTozteDeComplexo(obj2.at("Agora"));

                    if (proc_tozte_aqui.isIgual(proc_tozte)) {
                        Hazde proc_hazde = st.getHazdeDeComplexo(obj2.at("Agora"));

                        hazde_extremos.set(proc_hazde);

                        quantidade += 1;
                    }
                }

                fmt.print("TOZTE - {} :: {}", proc_tozte.getTextoZerado(), quantidade);
                fmt.print("\t Primeiro - {}", hazde_extremos.getMenor().getTextoZerado());
                fmt.print("\t Recente  - {}", hazde_extremos.getMaior().getTextoZerado());

                Entidade info = new Entidade();
                info.at("Tozte", proc_tozte.getTextoZerado());
                info.at("Quantidade", quantidade);

                info.at("Primeiro", hazde_extremos.getMenor().getTextoZerado());
                info.at("Recente", hazde_extremos.getMaior().getTextoZerado());
                info.at("Intervalo",
                        new Hazde_Intervalo("I", hazde_extremos.getMenor(), hazde_extremos.getMaior())
                                .getDiferencaZerado());

                AQZ.INSERIR(arquivo_banco, "TronarkumDiario", info);

            }

        }

        AQZ.REMOVER_VIEW(arquivo_banco, "VW_TRONAKUMDIARIO_ITQ");
        AQZ.REMOVER_VIEW(arquivo_banco, "VW_TRONAKUMDIARIO");

        AQZ.DEFINIR_VIEW(arquivo_banco, "VW_TronarkumDiario_ITQ", "TronarkumDiario",
                Strings.CRIAR_LISTA("ID", "Tozte", "Quantidade"));
        AQZ.DEFINIR_VIEW(arquivo_banco, "VW_TronarkumDiario", "TronarkumDiario",
                Strings.CRIAR_LISTA("ID", "Tozte", "Quantidade", "Primeiro", "Recente", "Intervalo"));

        AQZ.EXIBIR_VIEW(arquivo_banco, "VW_TronarkumDiario_ITQ");
        AQZ.EXIBIR_VIEW(arquivo_banco, "VW_TronarkumDiario");


    }

    public static void banco_me() {

        String arquivo_banco = "/home/luan/assets/tronarkum.az";
        String arquivo_imagem_cidade = "/home/luan/assets/cidade_gama.png";

        AQZ.COLECOES_EXIBIR(arquivo_banco);

        fmt.print("Auto Analisar...");
        AQZ.AUTO_ANALISAR(arquivo_banco);

        fmt.print("Auto Analisar...");
        AQZ.ANALISAR(arquivo_banco);

        fmt.print("Exibir...");
        AQZ.EXIBIR_COLECAO_PRIMARIA(arquivo_banco, "@Analise");

        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "Tronakum");
        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "TronakumDiario");
        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "Tempo");
        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "Tempo_v2");


        AQZ.EXIBIR_COLECAO(arquivo_banco, "Tronakum");
        AQZ.EXIBIR_COLECAO(arquivo_banco, "TronakumDiario");
        AQZ.EXIBIR_COLECAO(arquivo_banco, "Tempo");
        AQZ.EXIBIR_COLECAO(arquivo_banco, "Tempo_v2");


        AQZ.EXIBIR_TUDO(arquivo_banco);


        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "Numeros");
        // AQZ.LIMPAR_TUDO(arquivo_banco, "Numeros");

        if (AQZ.UNICO_EXISTE(arquivo_banco, "Numeros", "Tozte", Tronarko.getTozte().getTextoZerado())) {

            Entidade e = AQZ.UNICO_OBTER(arquivo_banco, "Numeros", "Tozte", Tronarko.getTozte().getTextoZerado());
            e.at("Atualizado", Tronarko.getTronAgora().getTextoZerado());
            e.at("Atualizacoes", e.atIntOuPadrao("Atualizacoes", 0) + 1);

            AQZ.UNICO_ATUALIZAR(arquivo_banco, "Numeros", "Tozte", Tronarko.getTozte().getTextoZerado(), e);

        } else {

            Entidade e = new Entidade();
            e.at("Numero", Aleatorio.aleatorio_entre(0, 100));
            e.at("Criado", Tronarko.getTronAgora().getTextoZerado());

            AQZ.UNICO_ATUALIZAR(arquivo_banco, "Numeros", "Tozte", Tronarko.getTozte().getTextoZerado(), e);
        }

        Lista<Entidade> valores = AQZ.COLECAO_ITENS(arquivo_banco, "Numeros");
        ENTT.EXIBIR_TABELA(valores);

        ObservadorItem numero_hoje = AQZ.OBTER_OBSERVADOR(arquivo_banco, "Numeros", "Tozte", Tronarko.getTozte().getTextoZerado());
        numero_hoje.exibir();

        numero_hoje.at("Numero", numero_hoje.atInt("Numero") + Aleatorio.aleatorio_entre(10, 30));

        if (numero_hoje.atInt("Numero") > 100) {
            numero_hoje.at("Numero", numero_hoje.atInt("Numero") - 100);
            numero_hoje.at("Estourou", "SIM");
            numero_hoje.at("EstourouQuando", Tronarko.getTronAgora().getTextoZerado());
            numero_hoje.at("EstourouVezes", numero_hoje.atIntOuPadrao("EstourouVezes", 0) + 1);
        }

        numero_hoje.atualizar();
        numero_hoje.exibir();

        //    AQZ.VOLUMES_ZERAR(arquivo_banco);

        Lista<Entidade> volumes = AQZ.GET_VOLUMES(arquivo_banco);

        if (volumes.getQuantidade() < 3) {
            //   AQZ.CRIAR_VOLUME(arquivo_banco);
        }


        if (AQZ.TEM_BLOCO_DISPONIVEL(arquivo_banco)) {

            String conteudo = "";

            conteudo = Tronarko.getTronAgora().getTextoZerado() + "\n";

            int numero_aleatorio = Aleatorio.aleatorio_entre(5, 10);
            for (int n = 0; n < numero_aleatorio; n++) {
                conteudo += "\t " + n + " -->> " + Aleatorio.escolha_um(Lista.CRIAR("Java", "Rust", "Python", "C", "C++")) + "\n";
            }

            //    AQZ.ARQUIVO_ALOCAR(arquivo_banco, "@Status/LuanFreitas/" + Tronarko.getTozte().getTextoInversoZerado().replace("/", "_") + ".status", conteudo);
        }

        if (AQZ.TEM_BLOCO_DISPONIVEL(arquivo_banco)) {
            //   AQZ.ARQUIVO_ALOCAR(arquivo_banco, "@Imagem/Cidade.png", Arquivador.GET_BYTES(arquivo_imagem_cidade));
        }

        String imagem_grande = "/home/luan/Imagens/32c24851b9671e70305e7f85aee7059e.png";

        if (AQZ.TEM_BLOCO_DISPONIVEL(arquivo_banco)) {
            //  AQZ.ARQUIVO_ALOCAR(arquivo_banco, "@Imagem/Mobile.png", Arquivador.GET_BYTES(imagem_grande));
        }


        //  volumes = AQZ.GET_VOLUMES(arquivo_banco);
        // ENTT.EXIBIR_TABELA_COM_NOME(volumes, "@VOLUMES");


        AQZ.ARQUIVO_DUMP(arquivo_banco);

        AQZ.VOLUMES_DUMP(arquivo_banco);

        Opcional<AQZArquivoExternamente> arq_mob = AQZ.ARQUIVO_PROCURAR_EXTERNAMENTE(arquivo_banco, "@Imagem/Mobile.png");

        if (arq_mob.isOK()) {
            // ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(arq_mob.get()));

            fmt.print("Nome : {} ->> {}", arq_mob.get().getNome(), fmt.formatar_tamanho_precisao_dupla(arq_mob.get().getTamanho()));

            //Arquivador.CONSTRUIR_ARQUIVO("/home/luan/assets/tronarkum_arquivo_dentro.png", arq_mob.get().getBytes());

            arq_mob.get().remover();

        }

        AQZ.VOLUMES_DUMP(arquivo_banco);

        fmt.print("Volume Blocos Livres :: {}", AQZ.VOLUME_BLOCOS_LIVRES(arquivo_banco));

    }

    public static void sequenciador() {

        for (Integer indice : Seq.SEQUENCIE(0, 100)) {
            fmt.print("{}", indice);
        }

    }

    public static void aqz_geral() {

        String arquivo_banco = "/home/luan/assets/tronarkum.az";

        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "Valores");
        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "Tronakum");
        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "TronakumDiario");
        AQZ.COLECOES_ORGANIZAR(arquivo_banco, "Tempo");

        // AQZ.LIMPAR_TUDO(arquivo_banco,"VALORES");

        String arquivo_banco_trons = "/home/luan/assets/trons.az";

        // MigrarToAQZ.MIGRAR(arquivo_banco_trons,"Tronakum",arquivo_banco,"Tronakum");
        // MigrarToAQZ.MIGRAR(arquivo_banco_trons,"TronakumDiario",arquivo_banco,"TronakumDiario");

        AQZ.COLECOES_EXIBIR(arquivo_banco);

        String antes = Calendario.getTempoCompleto();
        for (int v = 0; v < 100; v++) {

            long numero_sequencial = AQZ.QUANTIDADE(arquivo_banco, "VALORES");

            String seq = Aleatorio.aleatorio_desses("BCDFGHJKLMNPQRSTVWXYZ", 10);

            AQZ.INSERIR(arquivo_banco, "VALORES", ENTT.CRIAR("Sequencial", String.valueOf(numero_sequencial), "Valor", seq));
            fmt.print("Inserindo :: {}", v);
        }
        String depois = Calendario.getTempoCompleto();

        AQZ.EXIBIR_COLECAO(arquivo_banco, "VALORES");
        AQZ.INSERIR(arquivo_banco, "Tempo", ENTT.CRIAR("Inicio", antes, "Termino", depois));
        AQZ.EXIBIR_COLECAO(arquivo_banco, "Tempo");

        AQZ.COLECOES_DESTRUIR(arquivo_banco, "Drafts");

        AQZ.AUTO_ANALISAR(arquivo_banco);
        AQZ.ANALISAR(arquivo_banco);

        AQZ.EXIBIR_COLECAO_PRIMARIA(arquivo_banco, "@Init");
        AQZ.EXIBIR_COLECAO_PRIMARIA(arquivo_banco, "@Bancos");
        AQZ.EXIBIR_COLECAO_PRIMARIA(arquivo_banco, "@Sequencias");
        AQZ.EXIBIR_COLECAO_PRIMARIA(arquivo_banco, "@AutoAnalise");
        AQZ.EXIBIR_COLECAO_PRIMARIA(arquivo_banco, "@Analise");

    }

    public static void banco_testes() {

        String arquivo_banco = "/home/luan/assets/max.aqz";

        for (int id = 0; id <= 10000; id++) {
            // AQZ.COLECOES_ORGANIZAR(arquivo_banco, "Tronakum_"+id);
            // fmt.print("Banco :: "+"Tronakum_"+id);
        }

        AQZ.AUTO_ANALISAR(arquivo_banco);
        AQZ.ANALISAR(arquivo_banco);

        AQZ.EXIBIR_COLECAO_PRIMARIA(arquivo_banco, "@Init");
        AQZ.EXIBIR_COLECAO_PRIMARIA(arquivo_banco, "@Bancos");
        AQZ.EXIBIR_COLECAO_PRIMARIA(arquivo_banco, "@Sequencias");
        AQZ.EXIBIR_COLECAO_PRIMARIA(arquivo_banco, "@AutoAnalise");
        AQZ.EXIBIR_COLECAO_PRIMARIA(arquivo_banco, "@Analise");

    }

    public static void metropoles() {

        String arquivo_banco = "/home/luan/assets/coisas/Metropoles.az";

        AQZ.EXIBIR_ESTRUTURA(arquivo_banco);

        AQZ.EXIBIR_AMOSTRA(arquivo_banco, "METROPOLES(DF).ARQUIVADOS");

        Unico<String> toztes = AQZ.FILTRAR_UNICOS(arquivo_banco, "METROPOLES(DF).ARQUIVADOS", "Tozte.Obtido");

        Lista<Entidade> por_toztes = ENTT.CRIAR_DE_STRINGS(toztes.toLista());

        ENTT.AT_ALTERAR_NOME(por_toztes, "Item", "Tozte");
        ENTT.SEQUENCIAR(por_toztes, "ID", 0);
        ENTT.ATRIBUTO_TORNAR_PRIMEIRO(por_toztes, "ID");


        Lista<Entidade> tudo = AQZ.COLECAO_ITENS(arquivo_banco, "METROPOLES(DF).ARQUIVADOS");

        for (Entidade e : por_toztes) {

            e.at("Recente", e.at("Tozte"));

            Tozte recente = StringTronarko.parseTozte(e.at("Recente"));

            for (Entidade outra : ENTT.COLETAR(tudo, "Tozte.Obtido", e.at("Tozte"))) {
                Tozte outra_tozte = StringTronarko.parseTozte(outra.at("Tozte.Atualizado"));
                if (outra_tozte.isMaiorIgualQue(recente)) {

                    e.at("Recente", outra.at("Tozte.Atualizado"));
                    recente = StringTronarko.parseTozte(e.at("Recente"));

                    e.at("NoticiaID", outra.at("ID"));
                    e.at("Noticia", outra.at("Noticia"));

                }
            }

            e.at("CicloDeVida", Tronarko.TOZTE_DIFERENCA(StringTronarko.parseTozte(e.at("Recente")), StringTronarko.parseTozte(e.at("Tozte"))));


            e.at("Status", e.at("Recente") + " :: " + e.at("CicloDeVida"));

        }

        ENTT.ATRIBUTO_TORNAR_ULTIMO(por_toztes, "NoticiaID");
        ENTT.ATRIBUTO_TORNAR_ULTIMO(por_toztes, "Noticia");

        ENTT.EXIBIR_TABELA(por_toztes);

    }
}

package testes;

import apps.app.AppPrevisaoDoTempo;
import apps.app_attuz.Sociedade.PessoaNomeadorDeAkkax;
import libs.aqz.AQZParticoes;
import libs.aqz.AQZPasta;
import libs.aqz.AQZUTF8;
import libs.aqz.SuperAQZ_UTF8;
import libs.aqz.utils.ObservadorItemUTF8;
import libs.aqz.volume.AQZArquivoExternamente;
import libs.aqz.volume.AQZVolumes;
import libs.arquivos.binario.Arquivador;
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

public class TesteAZ {

    public static void teste_colecoes() {

        String arquivo_migratorium = "/home/luan/assets/migratorium.az";


        AQZUTF8.COLECOES_ORGANIZAR(arquivo_migratorium, "Tronz");
        //  AQZUTF8.LIMPAR_TUDO(arquivo_migratorium,"Tronz");

        Entidade tozte = new Entidade();
        tozte.at("Tron", Tronarko.getTronAgora().getTextoZerado());

        AQZUTF8.INSERIR(arquivo_migratorium, "Tronz", tozte);


        AQZUTF8.EXIBIR_TUDO(arquivo_migratorium);

        AQZParticoes.EXIBIR_PARTICOES(arquivo_migratorium);
        AQZParticoes.EXIBIR_ESTRUTURA_INTERNA(arquivo_migratorium);


    }

    public static void teste_valorantes() {

        String arquivo_banco = "/home/luan/assets/migratorium.az";

        String seq = Aleatorio.aleatorio_desses("BCDFGHJKLMNPQRSTVWXYZ", 10);

        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "Valorantum");
        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "ValorantumDiario");
        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "Tempo");
        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "Tempo_v2");

        fmt.print(">> Inserir Valorantum");

        SuperAQZ_UTF8.INSERIR(arquivo_banco, "Valorantum",
                ENTT.CRIAR("Agora", Tronarko.getTronAgora().getTextoZerado(), "Sequencia", seq));

        fmt.print(">> Limpar ValorantumDiario");
        SuperAQZ_UTF8.LIMPAR_TUDO(arquivo_banco, "ValorantumDiario");

        Lista<Entidade> tronarko_logs = SuperAQZ_UTF8.COLECAO_ITENS(arquivo_banco, "Valorantum");

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

                SuperAQZ_UTF8.INSERIR(arquivo_banco, "ValorantumDiario", info);

            }

        }

        //   AQZ.REMOVER_VIEW(arquivo_banco, "VW_TRONAKUMDIARIO_ITQ");
        //   AQZ.REMOVER_VIEW(arquivo_banco, "VW_TRONAKUMDIARIO");

        //    AQZ.DEFINIR_VIEW(arquivo_banco, "VW_TronarkumDiario_ITQ", "TronarkumDiario",
        //         Strings.CRIAR_LISTA("ID", "Tozte", "Quantidade"));
        //   AQZ.DEFINIR_VIEW(arquivo_banco, "VW_TronarkumDiario", "TronarkumDiario",
        //          Strings.CRIAR_LISTA("ID", "Tozte", "Quantidade", "Primeiro", "Recente", "Intervalo"));

        //   AQZ.EXIBIR_VIEW(arquivo_banco, "VW_TronarkumDiario_ITQ");
        //  AQZ.EXIBIR_VIEW(arquivo_banco, "VW_TronarkumDiario");


    }

    public static void teste_valorantes_2() {

        String arquivo_banco = "/home/luan/assets/migratorium.az";

        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "Valores");
        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "Valorantum");
        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "ValorantumDiario");
        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "Tempo");

        // AQZ.LIMPAR_TUDO(arquivo_banco,"VALORES");

        String arquivo_banco_trons = "/home/luan/assets/trons.az";

        // MigrarToAQZ.MIGRAR(arquivo_banco_trons,"Tronakum",arquivo_banco,"Tronakum");
        // MigrarToAQZ.MIGRAR(arquivo_banco_trons,"TronakumDiario",arquivo_banco,"TronakumDiario");

        SuperAQZ_UTF8.COLECOES_EXIBIR(arquivo_banco);

        String antes = Calendario.getTempoCompleto();

        Lista<Entidade> inserir_varios = new Lista<Entidade>();

        for (int v = 0; v < 100; v++) {

            long numero_sequencial = SuperAQZ_UTF8.QUANTIDADE(arquivo_banco, "VALORES");

            String seq = Aleatorio.aleatorio_desses("BCDFGHJKLMNPQRSTVWXYZ", 10);

            fmt.print("Inserindo :: {}", v);
            inserir_varios.adicionar(ENTT.CRIAR("Sequencial", String.valueOf(numero_sequencial), "Valor", seq));

        }

        SuperAQZ_UTF8.INSERIR_VARIOS(arquivo_banco, "VALORES", inserir_varios);

        String depois = Calendario.getTempoCompleto();

        SuperAQZ_UTF8.EXIBIR_COLECAO(arquivo_banco, "VALORES");
        SuperAQZ_UTF8.INSERIR(arquivo_banco, "Tempo", ENTT.CRIAR("Inicio", antes, "Termino", depois));
        SuperAQZ_UTF8.EXIBIR_COLECAO(arquivo_banco, "Tempo");

        SuperAQZ_UTF8.COLECOES_DESTRUIR(arquivo_banco, "Drafts");

        //  AQZ.AUTO_ANALISAR(arquivo_banco);
        //  AQZ.ANALISAR(arquivo_banco);


    }

    public static void teste_valorantes_inserir() {

        String arquivo_banco = "/home/luan/assets/migratorium.az";
        String arquivo_imagem_cidade = "/home/luan/assets/cidade_gama.png";

        SuperAQZ_UTF8.COLECOES_EXIBIR(arquivo_banco);

        fmt.print("Auto Analisar...");
        // AQZ.AUTO_ANALISAR(arquivo_banco);

        fmt.print("Auto Analisar...");
        //  AQZ.ANALISAR(arquivo_banco);

        fmt.print("Exibir...");
        //   AQZ.EXIBIR_COLECAO_PRIMARIA(arquivo_banco, "@Analise");

        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "Valorantum");
        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "ValorantumDiario");
        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "Tempo");
        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "Tempo_v2");


        SuperAQZ_UTF8.EXIBIR_COLECAO(arquivo_banco, "Valorantum");
        SuperAQZ_UTF8.EXIBIR_COLECAO(arquivo_banco, "ValorantumDiario");
        SuperAQZ_UTF8.EXIBIR_COLECAO(arquivo_banco, "Tempo");
        SuperAQZ_UTF8.EXIBIR_COLECAO(arquivo_banco, "Tempo_v2");


        //   AQZ.EXIBIR_TUDO(arquivo_banco);


        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "Numeros");
        // AQZ.LIMPAR_TUDO(arquivo_banco, "Numeros");

        if (SuperAQZ_UTF8.UNICO_EXISTE(arquivo_banco, "Numeros", "Tozte", Tronarko.getTozte().getTextoZerado())) {

            Entidade e = SuperAQZ_UTF8.UNICO_OBTER(arquivo_banco, "Numeros", "Tozte", Tronarko.getTozte().getTextoZerado());
            e.at("Atualizado", Tronarko.getTronAgora().getTextoZerado());
            e.at("Atualizacoes", e.atIntOuPadrao("Atualizacoes", 0) + 1);

            SuperAQZ_UTF8.UNICO_ATUALIZAR(arquivo_banco, "Numeros", "Tozte", Tronarko.getTozte().getTextoZerado(), e);

        } else {

            Entidade e = new Entidade();
            e.at("Numero", Aleatorio.aleatorio_entre(0, 100));
            e.at("Criado", Tronarko.getTronAgora().getTextoZerado());

            SuperAQZ_UTF8.UNICO_ATUALIZAR(arquivo_banco, "Numeros", "Tozte", Tronarko.getTozte().getTextoZerado(), e);
        }

        Lista<Entidade> valores = SuperAQZ_UTF8.COLECAO_ITENS(arquivo_banco, "Numeros");
        ENTT.EXIBIR_TABELA(valores);

        ObservadorItemUTF8 numero_hoje = SuperAQZ_UTF8.OBTER_OBSERVADOR(arquivo_banco, "Numeros", "Tozte", Tronarko.getTozte().getTextoZerado());
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

        //AQZ.VOLUMES_ZERAR(arquivo_banco);

        Lista<Entidade> volumes = AQZVolumes.GET_VOLUMES(arquivo_banco);

        if (volumes.getQuantidade() < 3) {
            //   AQZ.CRIAR_VOLUME(arquivo_banco);
        }


        if (AQZVolumes.TEM_BLOCO_DISPONIVEL(arquivo_banco)) {

            String conteudo = "";

            conteudo = Tronarko.getTronAgora().getTextoZerado() + "\n";

            int numero_aleatorio = Aleatorio.aleatorio_entre(5, 10);
            for (int n = 0; n < numero_aleatorio; n++) {
                conteudo += "\t " + n + " -->> " + Aleatorio.escolha_um(Lista.CRIAR("Java", "Rust", "Python", "C", "C++")) + "\n";
            }

            //    AQZ.ARQUIVO_ALOCAR(arquivo_banco, "@Status/LuanFreitas/" + Tronarko.getTozte().getTextoInversoZerado().replace("/", "_") + ".status", conteudo);
        }

        if (AQZVolumes.TEM_BLOCO_DISPONIVEL(arquivo_banco)) {
            //   AQZ.ARQUIVO_ALOCAR(arquivo_banco, "@Imagem/Cidade.png", Arquivador.GET_BYTES(arquivo_imagem_cidade));
        }

        String imagem_grande = "/home/luan/Imagens/1002702.png";

        if (AQZVolumes.TEM_BLOCO_DISPONIVEL(arquivo_banco)) {
            //   Opcional<Long> op_ponteiro = AQZ.ARQUIVO_ALOCAR(arquivo_banco, "@Imagem/Mobile.png", Arquivador.GET_BYTES(imagem_grande));
        }


        //  volumes = AQZ.GET_VOLUMES(arquivo_banco);
        // ENTT.EXIBIR_TABELA_COM_NOME(volumes, "@VOLUMES");


        AQZVolumes.ARQUIVO_DUMP(arquivo_banco);

        AQZVolumes.EXIBIR_VOLUMES(arquivo_banco);

        Opcional<AQZArquivoExternamente> arq_mob = AQZVolumes.ARQUIVO_PROCURAR_EXTERNAMENTE(arquivo_banco, "@Imagem/Mobile.png");

        if (arq_mob.isOK()) {
            // ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(arq_mob.get()));

            fmt.print("Nome : {} ->> {}", arq_mob.get().getNome(), fmt.formatar_tamanho_precisao_dupla(arq_mob.get().getTamanho()));

            //Arquivador.CONSTRUIR_ARQUIVO("/home/luan/assets/tronarkum_arquivo_dentro.png", arq_mob.get().getBytes());
            // arq_mob.get().remover();

        }

        AQZVolumes.EXIBIR_VOLUMES(arquivo_banco);

        fmt.print("Volume Blocos Livres :: {}", AQZVolumes.VOLUME_BLOCOS_LIVRES(arquivo_banco));


        AQZPasta dados_assets = new AQZPasta(arquivo_banco, "Memcached_v2");

        // dados_assets.limpar();

        boolean adicionado = dados_assets.adicionar_ou_atualizar("@Imagem/Zeta.png", Arquivador.GET_BYTES(imagem_grande));


        Lista<Entidade> e_omega = new Lista<Entidade>();

        if (dados_assets.existe("@Documento/Omega.entts")) {
            e_omega = ENTT.PARSER(dados_assets.getTexto("@Documento/Omega.entts"));

            Entidade novo = ENTT.CRIAR_EM(e_omega);
            novo.at("SequencialID", ENTT.CONTAGEM(e_omega));
            novo.at("Tron", Tronarko.getTronAgora().getTextoZerado());

        }


        //   ENTT.EXIBIR_TABELA_COM_NOME(e_omega, "OMEGA DADOS");

        boolean adicionado2 = dados_assets.adicionar_ou_atualizar("@Documento/Omega.entts", ENTT.TO_DOCUMENTO(e_omega));
        //  boolean adicionado3 = dados_assets.adicionar_ou_atualizar("@Documento/INMET_HOJE.entts", ENTT.TO_DOCUMENTO(INMET.GET_DADOS()));

        //  dados_assets.dump();


        if (dados_assets.existe("@Documento/INMET_HOJE.entts")) {
            // Lista<Entidade> inmet_dados = ENTT.PARSER(dados_assets.getTexto("@Documento/INMET_HOJE.entts"));
            //   ENTT.EXIBIR_TABELA_COM_NOME(inmet_dados, "INMET DADOS");
        }


        for (int indice = 1; indice <= 3; indice++) {

            fmt.print("Criando arquivo indexado : {}", indice);
            boolean adicionado_indice = dados_assets.adicionar_ou_atualizar("@Cache/v2_" + fmt.zerado(indice, 10) + ".o", PessoaNomeadorDeAkkax.getSimples());

        }

        dados_assets.fechar();
    }

    public static void teste_tempo() {

        String arquivo_banco = "/home/luan/assets/migratorium.az";

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


        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "Tempo");
        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "Tempo_v2");


        for (String tempo_inserindo : tempos_insercao) {

            SuperAQZ_UTF8.EM_DESTRUICAO(arquivo_banco, "Tempo", new FuncaoAlfa<RefBool, Entidade>() {
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

            SuperAQZ_UTF8.EM_DESTRUICAO(arquivo_banco, "Tempo_v2", new FuncaoAlfa<RefBool, Entidade>() {
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


        SuperAQZ_UTF8.INSERIR_VARIOS(arquivo_banco, "Tempo", previsao);

        SuperAQZ_UTF8.INSERIR_VARIOS(arquivo_banco, "Tempo_v2", ENTT.GET_CAMPOS(previsao,
                Strings.CRIAR_LISTA("time", "temperature_2m", "relative_humidity_2m",
                        "windspeed_10m", "precipitation_probability", "rain")));

        fmt.print("TEMPO ATUALIZADO !");


        Lista<Entidade> tempos = SuperAQZ_UTF8.EM_DISPERSAO(arquivo_banco, "Tempo_v2", new FuncaoAlfa<String, Entidade>() {
            @Override
            public String fazer(Entidade entidade) {

                String data = Strings.GET_ATE(entidade.at("time"), " ");
                // fmt.print(">> {} ->> {}",entidade.at("ID"),"<"+data+">");
                return data;
            }
        });


        ENTT.EXIBIR_TABELA_COM_NOME(tempos, "Tempo_v2 @Datas");


        SuperAQZ_UTF8.EM_DESTRUICAO(arquivo_banco, "Tempo_v2", new FuncaoAlfa<RefBool, Entidade>() {
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

        fmt.print("Tempo    = {}", SuperAQZ_UTF8.COLECAO_CONTAGEM(arquivo_banco, "Tempo"));
        fmt.print("Tempo_v2 = {}", SuperAQZ_UTF8.COLECAO_CONTAGEM(arquivo_banco, "Tempo_v2"));


        // SuperAQZ_UTF8.ANALISAR(arquivo_banco);
        //SuperAQZ_UTF8.EXIBIR_COLECAO_PRIMARIA(arquivo_banco,"@Analise");


        SuperAQZ_UTF8.EXIBIR_DESCRITORES(arquivo_banco, "Tempo_v2");

    }

    public static void teste_tempo_descritores() {

        String arquivo_banco = "/home/luan/assets/migratorium.az";


        SuperAQZ_UTF8.COLECOES_ORGANIZAR(arquivo_banco, "Tempo_v2");


        SuperAQZ_UTF8.EM_ATUALIZACAO(arquivo_banco, "Tempo_v2", new FuncaoAlfa<RefBool, Entidade>() {
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


        SuperAQZ_UTF8.EXIBIR_AMOSTRA(arquivo_banco, "Tempo_v2");

        SuperAQZ_UTF8.EXIBIR_DESCRITORES(arquivo_banco, "Tempo_v2");

        Lista<Entidade> pp_disp_humidity = SuperAQZ_UTF8.OBTER_DISPERSAO_SEM_EM_ZONAS(arquivo_banco, "Tempo_v2", "relative_humidity_2m", "%", ENTT.GET_ZONAS_PORCENTAGEM());
        ENTT.EXIBIR_TABELA_COM_TITULO(pp_disp_humidity, "relative_humidity_2m");


        Lista<Entidade> pp_temperature_2m = SuperAQZ_UTF8.OBTER_DISPERSAO_SEM_EM_4ZONAS_DOUBLE(arquivo_banco, "Tempo_v2", "temperature_2m", "°C");
        ENTT.EXIBIR_TABELA_COM_TITULO(pp_temperature_2m, "temperature_2m");

        fmt.print("Quantidade : {}", ENTT.ATRIBUTO_SOMAR(pp_disp_humidity, "Quantidade"));
        fmt.print("Quantidade : {}", ENTT.ATRIBUTO_SOMAR(pp_temperature_2m, "Quantidade"));


        // fmt.print("ISnumero : {}",Matematica.isNumeroInteiro("6.9"));

    }

    public static void teste_metropoles() {

        String arquivo_banco = "/home/luan/assets/coisas/Metropoles.az";

        AQZParticoes.EXIBIR_PARTICOES(arquivo_banco);
        //  AQZParticoes.EXIBIR_ESTRUTURA_INTERNA(arquivo_banco);

        Lista<Entidade> tudo = SuperAQZ_UTF8.COLECAO_ITENS(arquivo_banco, "METROPOLES(DF).ARQUIVADOS");

        Lista<String> toztes = ENTT.FILTRAR_UNICOS(tudo, "Tozte.Obtido");

        Lista<Entidade> por_toztes = ENTT.CRIAR_DE_STRINGS(toztes);

        ENTT.AT_ALTERAR_NOME(por_toztes, "Item", "Tozte");
        ENTT.SEQUENCIAR(por_toztes, "ID", 0);
        ENTT.ATRIBUTO_TORNAR_PRIMEIRO(por_toztes, "ID");


        for (Entidade e : por_toztes) {

            Lista<Entidade> noticias_do_tozte = ENTT.COLETAR(tudo, "Tozte.Obtido", e.at("Tozte"));

            e.at("Noticas", ENTT.CONTAGEM(noticias_do_tozte));

            if (noticias_do_tozte.possuiObjetos()) {
                e.at("Noticia->Antiga", ENTT.GET_PRIMEIRO(noticias_do_tozte).at("Noticia"));
                e.at("Noticia->Recente", ENTT.GET_ULTIMO(noticias_do_tozte).at("Noticia"));
            }
        }

        ENTT.ATRIBUTO_TORNAR_ULTIMO(por_toztes, "Noticia->Antiga");
        ENTT.ATRIBUTO_TORNAR_ULTIMO(por_toztes, "Noticia->Recente");

        ENTT.EXIBIR_TABELA(por_toztes);

    }


}

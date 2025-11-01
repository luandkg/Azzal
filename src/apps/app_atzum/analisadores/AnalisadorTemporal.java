package apps.app_atzum.analisadores;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.utils.ArquivoAtzumGeral;
import apps.app_atzum.utils.ArquivoAtzumTronarko;
import libs.arquivos.DSInterno;
import libs.arquivos.ds.DS;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fs.Arquivo;
import libs.fs.PastaFS;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.fmt;
import libs.tronarko.Tozte;
import libs.tronarko.utils.StringTronarko;

public class AnalisadorTemporal {

    public static void analisar_fenomenos_tectonicos() {

        PastaFS pasta_tronarkos = new PastaFS(AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos"));


        ArquivoAtzumGeral geral = new ArquivoAtzumGeral();


        Lista<Entidade> vulcoes = geral.GET_VULCOES();
        ENTT.EXIBIR_TABELA(vulcoes);

        Lista<Entidade> cidades = geral.GET_CIDADES();

        // ENTT.EXIBIR_TABELA(cidades);


        Lista<Entidade> tronarkos_periodos = new Lista<Entidade>();

        for (Arquivo arquivo : pasta_tronarkos.getArquivosComExtensao(".ds")) {
            if (arquivo.getNome().startsWith("atzum_")) {
                Entidade e_tronarko = ENTT.CRIAR_EM(tronarkos_periodos);
                e_tronarko.at("Tronarko", arquivo.getNome());
                e_tronarko.at("Arquivo", arquivo.getLocal());
                e_tronarko.at("Tronarko", e_tronarko.at("Tronarko").replace("atzum_tronarko_", "").replace(".ds", ""));
            }
        }

        ENTT.ORDENAR_INTEIRO(tronarkos_periodos, "Tronarko");

        int aa = 0;

        Lista<Entidade> terremotos_globais = new Lista<Entidade>();


        for (Entidade e_tronarko : tronarkos_periodos) {
            Arquivo arquivo = new Arquivo(e_tronarko.at("Arquivo"));

            String tronarko_corrente = e_tronarko.at("Tronarko");

            // DS.DUMP_TABELA(arquivo.getLocal());


            // Lista<Entidade> dados_cidades = DSInterno.ENTT_ABRIR(DS.buscar_item(arquivo.getLocal(), "@dados/tronarko_cidades_dados_publicados.entts").get());
            Lista<Entidade> fenomenos_tectonicos = DSInterno.ENTT_ABRIR(DS.buscar_item(arquivo.getLocal(), "@informacoes/fenomenos_tectonicos.entts").get());

            ENTT.EXIBIR_TABELA(fenomenos_tectonicos);
            // ENTT.EXIBIR_TABELA(dados_cidades);

            for (Entidade cidade : cidades) {
                //   Entidade cidade_tronarko = ENTT.GET_SEMPRE(dados_cidades, "CidadeNome", cidade.at("Nome"));
                //cidade.at("Chuva_" + tronarko_corrente, cidade_tronarko.at("Chuva"));
                //  cidade.at("Vegetacao_" + tronarko_corrente, cidade_tronarko.at("Vegetacao"));

                for (Entidade fenomenos_tectonico : fenomenos_tectonicos) {

                    double distancia = Espaco2D.distancia_entre_pontos(cidade.atInt("X"), cidade.atInt("Y"), fenomenos_tectonico.atInt("X"), fenomenos_tectonico.atInt("Y"));

                    int escala = fenomenos_tectonico.atInt("Escala");


                    double distancia_maxima = escala * 10;

                    if (distancia <= distancia_maxima) {

                        cidade.at("Erupcoes", cidade.at("Erupcoes"));
                        cidade.at("Terremotos", cidade.atIntOuPadrao("Terremotos", 0) + 1);

                        if (fenomenos_tectonico.is("Evento", "VULCANISMO")) {
                            cidade.at("Erupcoes", cidade.atIntOuPadrao("Erupcoes", 0) + 1);
                        }

                        double distancia_proporcao = (1.0 - ((distancia) / distancia_maxima));
                        double tremor = (1.0 - ((distancia) / distancia_maxima)) * escala;

                        cidade.at("Terremoto_" + StringTronarko.PARSER_TOZTE(fenomenos_tectonico.at("Tozte")).getTextoInversoZerado_UnderLine(), "Limite = " + fmt.f2(distancia_maxima) + " Distancia = " + fmt.f2(distancia) + " Escala = " + escala + " Proporcao = " + fmt.f2(distancia_proporcao) + " Tremor = " + fmt.f2(tremor));


                        Entidade terremoto_global = ENTT.CRIAR_EM(terremotos_globais);
                        terremoto_global.at("X", cidade.at("X"));
                        terremoto_global.at("Y", cidade.at("Y"));
                        terremoto_global.at("Nome", cidade.at("Nome"));
                        terremoto_global.at("Tozte", fenomenos_tectonico.at("Tozte"));
                        terremoto_global.at("TozteReverso", StringTronarko.PARSER_TOZTE(fenomenos_tectonico.at("Tozte")).getTextoInversoZerado());
                        terremoto_global.at("Origem", fenomenos_tectonico.atInt("X") + "::" + fenomenos_tectonico.atInt("Y"));
                        terremoto_global.at("Escala", escala);
                        terremoto_global.at("Limite", distancia_maxima);
                        terremoto_global.at("Distancia", distancia);
                        terremoto_global.at("Proporcao", fmt.f2(distancia_proporcao));
                        terremoto_global.at("Tremor", fmt.f2(tremor));

                    }

                }

            }


            //  ENTT.EXIBIR_TABELA(ENTT.GET_ATRIBUTOS_E_SEQUENCIAIS(dados_cidades));

            //  ENTT.ORDENAR_DOUBLE(dados_cidades, "tMax");

            // e_tronarko.at("FriaCidade_Nome", ENTT.GET_PRIMEIRO(dados_cidades).at("CidadeNome"));
            // e_tronarko.at("FriaCidade_Valor", ENTT.GET_PRIMEIRO(dados_cidades).at("tMax"));


            //e_tronarko.at("QuenteCidade_Nome", ENTT.GET_ULTIMO(dados_cidades).at("CidadeNome"));
            //e_tronarko.at("QuenteCidade_Valor", ENTT.GET_ULTIMO(dados_cidades).at("tMax"));


            if (aa == 0) {
                //   break;
            }

            aa += 1;

            // fmt.print(">> {}",arquivo.getLocal());
        }

        ENTT.ORDENAR_INTEIRO(tronarkos_periodos, "Tronarko");
        ENTT.EXIBIR_TABELA_COM_TITULO(tronarkos_periodos, "TRONARKOS");

        ENTT.EXIBIR_TABELA(cidades);

        ENTT.ORDENAR_TEXTO(terremotos_globais, "TozteReverso");
        ENTT.EXIBIR_TABELA(terremotos_globais);
    }


    public static void ver_cidade() {

        ArquivoAtzumGeral geral = new ArquivoAtzumGeral();

        Lista<Entidade> tronarkos_periodos = new Lista<Entidade>();
        PastaFS pasta_tronarkos = new PastaFS(AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos"));

        for (Arquivo arquivo : pasta_tronarkos.getArquivosComExtensao(".ds")) {
            if (arquivo.getNome().startsWith("atzum_")) {
                Entidade e_tronarko = ENTT.CRIAR_EM(tronarkos_periodos);
                e_tronarko.at("Tronarko", arquivo.getNome());
                e_tronarko.at("Arquivo", arquivo.getLocal());
                e_tronarko.at("Tronarko", e_tronarko.at("Tronarko").replace("atzum_tronarko_", "").replace(".ds", ""));
            }
        }

        ENTT.ORDENAR_INTEIRO(tronarkos_periodos, "Tronarko");


        Lista<Entidade> cidades = geral.GET_CIDADES();

        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(cidades));

        String cidade_analisar = "Colga";

        int tozte_superarko = 10;
        int tozte_hiperarko = 5;

        int superarko_geral = ((tozte_hiperarko-1)*50)+tozte_superarko;


        Opcional<Entidade> op_cidade = ENTT.GET_OPCIONAL(cidades, "Nome", cidade_analisar);

        if (op_cidade.isOK()) {
            fmt.print("Cidade        : {}", op_cidade.get().at("Nome"));
            fmt.print("Localizacação : {}", op_cidade.get().at("X") + " :: " + op_cidade.get().at("Y"));

            Lista<Entidade> mapa_dados = ENTT.CRIAR_LISTA();

            for (Entidade e_tronarko : tronarkos_periodos) {

                Lista<Entidade> dados = DSInterno.ENTT_ABRIR(DS.buscar_item(e_tronarko.at("Arquivo"), "@dados/tronarko_cidades_dados_publicados.entts").get());

                Opcional<Entidade> op_dados = ENTT.GET_OPCIONAL(dados, "CidadeNome", cidade_analisar);

                if(op_dados.isOK()){
                    Entidade e = ENTT.CRIAR_EM(mapa_dados);
                    e.at("Tronarko",e_tronarko.at("Tronarko"));
                    e.at("Tozte",new Tozte(tozte_superarko,tozte_hiperarko,e_tronarko.atInt("Tronarko")).getTextoZerado());

                    e.at("Temperatura",op_dados.get().at("T"+superarko_geral));

                    e.at("tMin",op_dados.get().at("tMin"));
                    e.at("tMax",op_dados.get().at("tMax"));

                }

                ENTT.EXIBIR_TABELA(mapa_dados);

            }

            ENTT.EXIBIR_TABELA(mapa_dados);

        }


    }

}

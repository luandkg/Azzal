package servicos;

import libs.dkg.DKG;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Internet;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Strings;
import libs.tempo.Calendario;
import libs.tempo.Data;
import libs.tronarko.Tronarko;
import libs.zetta.ZettaArquivo;
import libs.zetta.ZettaPasta;
import libs.zetta.ZettaPastas;
import libs.zetta.features.ZettaArquivoENTT;

public class IMDB {


    public static String GET_ARQUIVO_SERIES() {
        return ASSETS.GET_PASTA("coisas/tozterum").getArquivo("Series.az");
    }

    public static void ACOMPANHAR() {

        Lista<Entidade> series = IMDBServico.VER_LANCAMENTOS();

        ENTT.EXIBIR_TABELA_COM_TITULO(series, "LANÇAMENTO :: EPISÓDIOS DE TV - ESTADOS UNIDOS");


        ZettaPastas zetta_pastas = new ZettaPastas(GET_ARQUIVO_SERIES());
        ZettaPasta pasta_calendario = zetta_pastas.getPastaSempre("@IMDB::Indexamento");


        for (Entidade series_por_dia : ENTT.AGRUPAR(series, "Data")) {

            ENTT.EXIBIR_TABELA_COM_TITULO(series_por_dia.getEntidades(), "LANÇAMENTO :: EPISÓDIOS DE TV - ESTADOS UNIDOS");

            Opcional<ZettaArquivo> op = pasta_calendario.procurar_arquivo(series_por_dia.at("Data") + ".entts");

            if (op.isOK()) {

                ZettaArquivoENTT arquivo_entts = new ZettaArquivoENTT(op.get());

                Lista<Entidade> ja_existe = arquivo_entts.getEntidades();

                for (Entidade serie : series_por_dia.getEntidades()) {
                    if (!ENTT.EXISTE(ja_existe, "IMDBID", serie.at("IMDBID"))) {
                        ja_existe.adicionar(serie);
                    }
                }

                arquivo_entts.atualizar(ja_existe);


            } else {
                ZettaArquivoENTT.CRIAR_ARQUIVO(pasta_calendario, series_por_dia.at("Data") + ".entts", series_por_dia.getEntidades());
            }

        }

        pasta_calendario.dump_arquivos();

        zetta_pastas.fechar();

    }

    public static void VER_ARQUIVOS() {

        ZettaPastas zetta_pastas = new ZettaPastas(GET_ARQUIVO_SERIES());
        ZettaPasta pasta_calendario = zetta_pastas.getPastaSempre("@IMDB::Indexamento");

        for (ZettaArquivo arquivo : pasta_calendario.getArquivosAtualizaveis()) {
            if (arquivo.getNome().endsWith(".entts")) {

                ZettaArquivoENTT arquivo_entts = new ZettaArquivoENTT(arquivo);

                Lista<Entidade> series_por_dia = arquivo_entts.getEntidades();

                ENTT.EXIBIR_TABELA_COM_TITULO(series_por_dia, "LANÇAMENTO :: EPISÓDIOS DE TV - ESTADOS UNIDOS :: " + arquivo.getNome());


            }
        }


        zetta_pastas.fechar();

    }



    public static void ACOMPANHAR_DETALHES() {

        ZettaPastas zetta_pastas = new ZettaPastas(GET_ARQUIVO_SERIES());
        ZettaPasta pasta_calendario = zetta_pastas.getPastaSempre("@IMDB::Indexamento");

        for (ZettaArquivo arquivo : pasta_calendario.getArquivosAtualizaveis()) {
            if (arquivo.getNome().endsWith(".entts")) {

                ZettaArquivoENTT arquivo_entts = new ZettaArquivoENTT(arquivo);

                Lista<Entidade> series_por_dia = arquivo_entts.getEntidades();

                int tem = 0;
                for (Entidade e : series_por_dia) {
                    if (e.at("Exibido").length() == 0 && e.atIntOuPadrao("Buscado", 0) < 10) {
                        tem += 1;
                    }
                    if (tem > 30) {
                        break;
                    }
                }

                if (tem > 0) {

                    for (Entidade e : series_por_dia) {
                        if (e.at("Exibido").length() == 0 && e.atIntOuPadrao("Buscado", 0) < 10) {

                            Lista<Entidade> detalhes = IMDBServico.VER_EPISODIO(e.at("Link"));

                            e.at("Exibido", ENTT.GET_SEMPRE(detalhes, "Nome", "Exibido").at("Valor"));
                            e.at("Duracao", ENTT.GET_SEMPRE(detalhes, "Nome", "Duracao").at("Valor"));
                            e.at("Avaliacao", ENTT.GET_SEMPRE(detalhes, "Nome", "Avaliacao").at("Valor"));
                            e.at("Avaliadores", ENTT.GET_SEMPRE(detalhes, "Nome", "Avaliadores").at("Valor"));

                            e.at("Buscado", e.atIntOuPadrao("Buscado", 0) + 1);
                            e.at("BuscadoTron", Tronarko.getTronAgora().getTextoZerado());

                            tem -= 1;
                        }
                        if (tem < 0) {
                            break;
                        }
                    }

                    arquivo_entts.atualizar(series_por_dia);
                    break;
                }

                //  ENTT.EXIBIR_TABELA_COM_TITULO(series_por_dia, "LANÇAMENTO :: EPISÓDIOS DE TV - ESTADOS UNIDOS :: " + arquivo.getNome());


            }
        }


        zetta_pastas.fechar();

    }

    public static void VER_ARQUIVOS_SIMPLIFICADO() {

        ZettaPastas zetta_pastas = new ZettaPastas(GET_ARQUIVO_SERIES());
        ZettaPasta pasta_calendario = zetta_pastas.getPastaSempre("@IMDB::Indexamento");

        for (ZettaArquivo arquivo : pasta_calendario.getArquivosAtualizaveis()) {
            if (arquivo.getNome().endsWith(".entts")) {

                ZettaArquivoENTT arquivo_entts = new ZettaArquivoENTT(arquivo);

                Lista<Entidade> series_por_dia = arquivo_entts.getEntidades();

                ENTT.ATRIBUTO_REMOVER(series_por_dia,"IMDBID");
                ENTT.ATRIBUTO_REMOVER(series_por_dia,"Link");
                ENTT.ATRIBUTO_REMOVER(series_por_dia,"Buscado");
                ENTT.ATRIBUTO_REMOVER(series_por_dia,"TronObtido");
                ENTT.ATRIBUTO_REMOVER(series_por_dia,"BuscadoTron");

                ENTT.REMOVER_SE(series_por_dia,"Temporada","");
                ENTT.REMOVER_SE(series_por_dia,"Temporada","News");
                ENTT.REMOVER_SE(series_por_dia,"Temporada","Sport");
                ENTT.REMOVER_SE(series_por_dia,"Temporada","Comedy");
                ENTT.REMOVER_SE(series_por_dia,"Temporada","Talk-Show");


                for(Entidade e : series_por_dia){
                    if(e.at("Data").length()==10){
                        e.at("Tozte",Tronarko.getData(Data.toData(e.at("Data")).getTempoLegivel()).getTextoZerado());
                    }
                    if(e.at("Exibido").length()==10){
                        e.at("Exibido",Tronarko.getData(Data.toData(e.at("Exibido")).getTempoLegivel()).getTextoZerado());
                    }
                }

                ENTT.ATRIBUTO_REMOVER(series_por_dia,"Data");
                ENTT.ATRIBUTO_TORNAR_PRIMEIRO(series_por_dia,"Tozte");

                ENTT.SEQUENCIAR(series_por_dia,"ID",1);
                ENTT.ATRIBUTO_TORNAR_PRIMEIRO(series_por_dia,"ID");

                ENTT.EXIBIR_TABELA_COM_TITULO(series_por_dia, "LANÇAMENTO :: EPISÓDIOS DE TV - ESTADOS UNIDOS :: " + Strings.GET_ATE(arquivo.getNome(),"."));


            }
        }


        zetta_pastas.fechar();

    }

}

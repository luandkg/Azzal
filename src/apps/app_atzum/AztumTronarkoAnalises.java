package apps.app_atzum;

import apps.app_atzum.utils.ArquivoAtzumGeral;
import apps.app_atzum.utils.ArquivoAtzumTronarko;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.tronarko.Tronarko;
import libs.zetta.ZettaColecao;
import libs.zetta.ZettaQuorum;
import libs.zetta_monitorum.ItemColecionavelMonitorum;
import libs.zetta_monitorum.ZettaColecaoMonitorum;
import libs.zetta_monitorum.ZettaQuorumMonitorum;

public class AztumTronarkoAnalises {


    public static void INIT() {

        String arquivo = AtzumCreator.LOCAL_GET_ARQUIVO("AtzumAnalises.az");
        ZettaQuorumMonitorum colecoes = new ZettaQuorumMonitorum(arquivo);

        String mTronarko = "7000";

        Atzum mAtzum = new Atzum();

        ArquivoAtzumGeral mArquivoAtzumGeral = new ArquivoAtzumGeral();
        ArquivoAtzumTronarko mArquivoAtzumTronarko = new ArquivoAtzumTronarko(mTronarko);

        ZettaColecaoMonitorum cidades = colecoes.getColecaoSempre("Cidades");
        ZettaColecaoMonitorum fatores_climaticos = colecoes.getColecaoSempre("Cidades::FatoresClimaticos");
        ZettaColecaoMonitorum temperaturas = colecoes.getColecaoSempre("Cidades::Temperatura");
        ZettaColecaoMonitorum umidades = colecoes.getColecaoSempre("Cidades::Umidade");
        ZettaColecaoMonitorum massas_de_ar = colecoes.getColecaoSempre("Cidades::MassasDeAr");

        colecoes.logs_limpar();

        cidades.zerar();
        fatores_climaticos.zerar();
        temperaturas.zerar();
        umidades.zerar();
        massas_de_ar.zerar();

        for (Entidade cidade : Atzum.GET_CIDADES_NOMES()) {


            cidade.trocar_valores("CidadeID", "Nome");
            cidade.at_renomear("CidadeID", "CidadeNome");
            cidade.at_renomear("Cidade", "CidadePos");
            cidade.at_remover("Nome");


            int regiao_corrente = mArquivoAtzumGeral.GET_REGIAO(cidade.atInt("X"), cidade.atInt("Y"));
            int sub_regiao_corrente = mArquivoAtzumGeral.GET_SUBREGIAO(cidade.atInt("X"), cidade.atInt("Y"));

            int altitude = mArquivoAtzumGeral.GET_RELEVO_ALTITUDE(cidade.atInt("X"), cidade.atInt("Y"));

            String regiao_nome = mAtzum.GET_REGIAO_NOME(regiao_corrente);

            Entidade mCidade = mArquivoAtzumTronarko.GET_CIDADE_DADOS(cidade.atInt("X") + "::" + cidade.atInt("Y"));


            // ENTT.EXIBIR_TABELA(ENTT.GET_ATRIBUTOS(mCidade));


            cidade.at("Regiao", regiao_corrente);
            cidade.at("SubRegiao", sub_regiao_corrente);
            cidade.at("RegiaoNome", regiao_nome);
            cidade.at("Altitude", altitude);
            cidade.at("CidadeTipo", Portugues.VALIDAR(mCidade.atInt("Oceano_Distancia") <= 15, "Litoranea", "Continental"));
            cidade.at("OceanoDistancia", mCidade.at("Oceano_Distancia"));
            cidade.at("OceanoNome", mCidade.at("Oceano_Nome"));

            cidade.at("Hiperestacao", mCidade.at("Hiperestacao"));
            cidade.at("Vegetacao", mCidade.at("Vegetacao"));

            cidade.at("CidadeHash", Aleatorio.aleatorio_com_esses("0123456789BCDFGHJKLMNPQRSTVWXZ", 10) + "/" + Aleatorio.aleatorio_com_esses("0123456789", 3));

            cidades.adicionar(cidade);

        }


        for (ItemColecionavelMonitorum ic_cidade : Embaralhar.emabaralhe(cidades.getItensEditaveis())) {

            Entidade cidade = ic_cidade.get();

            cidade.at("CidadeID", cidade.at("@ID"));

            Entidade mCidade = mArquivoAtzumTronarko.GET_CIDADE_DADOS(cidade.atInt("X") + "::" + cidade.atInt("Y"));

            Entidade e_cidade_fatores_climaticos = new Entidade();
            e_cidade_fatores_climaticos.at("CidadeID", cidade.at("@ID"));
            e_cidade_fatores_climaticos.at("CidadeNome", cidade.at("CidadeNome"));

            Unico<String> fatores_climaticos_da_cidade = new Unico<String>(Strings.IGUALAVEL());


            for (int s = 1; s <= 500; s++) {
                if (Strings.isValida(mCidade.at("FC" + s))) {
                    fatores_climaticos_da_cidade.item(mCidade.at("FC" + s));
                }
            }

            for (String fator_climatico : fatores_climaticos_da_cidade) {
                e_cidade_fatores_climaticos.at(fator_climatico, "SIM");
            }


            ic_cidade.referenciar("FCID", fatores_climaticos, e_cidade_fatores_climaticos);

        }

        for (ItemColecionavelMonitorum ic_cidade : Embaralhar.emabaralhe(cidades.getItensEditaveis())) {

            Entidade cidade = ic_cidade.get();

            cidade.at("CidadeID", cidade.at("@ID"));

            Entidade mCidade = mArquivoAtzumTronarko.GET_CIDADE_DADOS(cidade.atInt("X") + "::" + cidade.atInt("Y"));

            Entidade e_cidade_temperatura = new Entidade();
            e_cidade_temperatura.at("CidadeID", cidade.at("@ID"));
            e_cidade_temperatura.at("CidadeNome", cidade.at("CidadeNome"));

            for (int s = 1; s <= 500; s++) {

                double temperatura = mCidade.atDouble("T" + s);
                e_cidade_temperatura.at("T" + s, temperatura);

            }

            ic_cidade.referenciar("TID", temperaturas, e_cidade_temperatura);

        }
        for (ItemColecionavelMonitorum ic_cidade : Embaralhar.emabaralhe(cidades.getItensEditaveis())) {

            Entidade cidade = ic_cidade.get();

            cidade.at("CidadeID", cidade.at("@ID"));

            Entidade mCidade = mArquivoAtzumTronarko.GET_CIDADE_DADOS(cidade.atInt("X") + "::" + cidade.atInt("Y"));

            Entidade e_cidade_umidade = new Entidade();
            e_cidade_umidade.at("CidadeID", cidade.at("@ID"));
            e_cidade_umidade.at("CidadeNome", cidade.at("CidadeNome"));

            for (int s = 1; s <= 500; s++) {

                double umidade = mCidade.atDouble("U" + s);
                e_cidade_umidade.at("U" + s, umidade);

            }


            ic_cidade.referenciar("UID", umidades, e_cidade_umidade);

        }

        for (ItemColecionavelMonitorum ic_cidade : Embaralhar.emabaralhe(cidades.getItensEditaveis())) {

            Entidade cidade = ic_cidade.get();

            cidade.at("CidadeID", cidade.at("@ID"));

            Entidade mCidade = mArquivoAtzumTronarko.GET_CIDADE_DADOS(cidade.atInt("X") + "::" + cidade.atInt("Y"));


            Entidade e_cidade_massa_de_ar = new Entidade();
            e_cidade_massa_de_ar.at("CidadeID", cidade.at("@ID"));
            e_cidade_massa_de_ar.at("CidadeNome", cidade.at("CidadeNome"));

            for (int s = 1; s <= 500; s++) {

                String massa_de_ar = mCidade.at("M" + s);
                e_cidade_massa_de_ar.at("M" + s, massa_de_ar);

            }


            ic_cidade.referenciar("MID", massas_de_ar, e_cidade_massa_de_ar);

        }


        ENTT.EXIBIR_TABELA_COM_NOME(cidades.getItens(), "Cidades");
        //  ENTT.EXIBIR_TABELA_COM_NOME(fatores_climaticos.getItens(), "Cidades::FatoresClimaticos");
        //  ENTT.EXIBIR_TABELA_COM_NOME(temperaturas.getItens(), "Cidades::Temperatura");
        // ENTT.EXIBIR_TABELA_COM_NOME(umidades.getItens(), "Cidades::Umidade");
        ENTT.EXIBIR_TABELA_COM_NOME(massas_de_ar.getItens(), "Cidades::MassasDeAr");

        fmt.print(">> DUMP : COLEÇÕES");
        colecoes.dump();


        fmt.print(">> Logs");
        colecoes.logs_ver();

        colecoes.fechar();


    }


    public static void VER() {

        String arquivo = AtzumCreator.LOCAL_GET_ARQUIVO("AtzumAnalises.az");
        ZettaQuorumMonitorum colecoes = new ZettaQuorumMonitorum(arquivo);


        ZettaColecaoMonitorum cidades = colecoes.getColecaoSempre("Cidades");
        ZettaColecaoMonitorum temperaturas = colecoes.getColecaoSempre("Cidades::Temperatura");
        ZettaColecaoMonitorum umidades = colecoes.getColecaoSempre("Cidades::Umidade");
        ZettaColecaoMonitorum fatores_climaticos = colecoes.getColecaoSempre("Cidades::FatoresClimaticos");

        ZettaColecaoMonitorum cidades_resumo = colecoes.getColecaoSempre("Cidades::Resumo");
        cidades_resumo.zerar();

        ZettaColecaoMonitorum informativos = colecoes.getColecaoSempre("Atzum::Informativos");
        informativos.zerar();


        Lista<Entidade> cidades_dados = cidades.getItens();

        for (Entidade cidade : cidades_dados) {

            Opcional<Entidade> op_cidade_temperaturas = temperaturas.procurar_item_por_indice(cidade.atLong("TID"));
            Opcional<Entidade> op_cidade_umidade = umidades.procurar_item_por_indice(cidade.atLong("UID"));
            Opcional<Entidade> op_cidade_fatores_climaticos = fatores_climaticos.procurar_item_por_indice(cidade.atLong("FCID"));


            if (op_cidade_temperaturas.isOK()) {
                Entidade cidade_temperaturas = op_cidade_temperaturas.get();

                double tMin = ENTT.ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MENOR(cidade_temperaturas, "T", 1, 500);
                double tMed = ENTT.ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MEDIA(cidade_temperaturas, "T", 1, 500);
                double tMax = ENTT.ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MAIOR(cidade_temperaturas, "T", 1, 500);

                cidade.at("tCidadeNome", cidade_temperaturas.at("CidadeNome"));

                cidade.at("tMin", fmt.f2(tMin));
                cidade.at("tMed", fmt.f2(tMed));
                cidade.at("tMax", fmt.f2(tMax));

            }

            if (op_cidade_umidade.isOK()) {
                Entidade cidade_umidade = op_cidade_umidade.get();

                cidade.at("uCidadeNome", cidade_umidade.at("CidadeNome"));

                double uMin = ENTT.ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MENOR(cidade_umidade, "U", 1, 500);
                double uMed = ENTT.ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MEDIA(cidade_umidade, "U", 1, 500);
                double uMax = ENTT.ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MAIOR(cidade_umidade, "U", 1, 500);

                cidade.at("uMin", fmt.f2(uMin));
                cidade.at("uMed", fmt.f2(uMed));
                cidade.at("uMax", fmt.f2(uMax));

            }

            if (op_cidade_fatores_climaticos.isOK()) {
                Entidade cidade_fatores_climaticos = op_cidade_fatores_climaticos.get();
                cidade.at("FCQ", ENTT.COLETAR_ATRIBUTOS_NOME_QUANDO(cidade_fatores_climaticos, "SIM").getQuantidade());
                cidade.at("FatoresClimaticos", Strings.LISTA_TO_TEXTO_LINHA(ENTT.COLETAR_ATRIBUTOS_NOME_QUANDO(cidade_fatores_climaticos, "SIM")));
            }

            cidades_resumo.adicionar(cidade);
        }


        ENTT.EXIBIR_TABELA(ENTT.SLICE_PRIMEIROS(cidades_dados, 10));



        CRIAR_INFO_CIDADES_QUENTES(informativos, cidades_dados);
        CRIAR_INFO_CIDADES_FRIAS(informativos, cidades_dados);

        CRIAR_INFO_CIDADES_BAIXAS(informativos, cidades_dados);
        CRIAR_INFO_CIDADES_ALTAS(informativos, cidades_dados);

        CRIAR_INFO_CIDADES_LITORANEAS(informativos, cidades_dados);
        CRIAR_INFO_CIDADES_CONTINENTAIS(informativos, cidades_dados);

        CRIAR_INFO_CIDADES_CLIMA_DOIDO(informativos, cidades_dados);

        CRIAR_INFO_CIDADES_CLIMA_PARADO(informativos, cidades_dados);

        ENTT.EXIBIR_TABELA_COM_TITULO(informativos.getItens(), "INFORMATIVOS");

        for (Entidade info : informativos.getItens()) {

            fmt.print(">> {}", info.at("Nome"));
           ENTT.EXIBIR_TABELA_PREFIXO("\t", info.getEntidades());

        }

        fmt.print(">> Logs");
        colecoes.logs_ver();


        colecoes.fechar();
    }


    public static void PUBLIQUE_INFORMATIVO(ZettaColecaoMonitorum informativos, String nome, Lista<Entidade> dados, String att_alfa, String att_beta, String att_gama) {

        Entidade e_informativo = new Entidade();
        e_informativo.at("Nome", nome);
        e_informativo.at("Tron", Tronarko.getTronAgora().getTextoZerado());

        for (Entidade e : dados) {
            Entidade novo = ENTT.CRIAR_EM(e_informativo.getEntidades());
            novo.at(att_alfa, e.at(att_alfa));
            novo.at(att_beta, e.at(att_beta));
            novo.at(att_gama, e.at(att_gama));
        }


        long info_id = informativos.adicionar(e_informativo);

    }


    public static void VER_ESQUEMAS() {

        String arquivo = AtzumCreator.LOCAL_GET_ARQUIVO("AtzumAnalises.az");
        ZettaQuorum colecoes = new ZettaQuorum(arquivo);

        for (ZettaColecao colecao : colecoes.getColecoes()) {
            ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.GET_ATRIBUTOS_E_SEQUENCIAIS(colecao.getItens()), "ESQUEMA :: " + colecao.getNome());
        }

        colecoes.fechar();
    }


    public static void CRIAR_INFO_CIDADES_QUENTES(ZettaColecaoMonitorum informativos, Lista<Entidade> cidades_dados) {

        ENTT.ORDENAR_DOUBLE_DECRESCENTE(cidades_dados, "tMed");
        ENTT.EXIBIR_TABELA(ENTT.SLICE_PRIMEIROS(cidades_dados, 10));
        PUBLIQUE_INFORMATIVO(informativos, "CidadesQuentes", ENTT.SLICE_PRIMEIROS(cidades_dados, 10), "CidadeNome", "CidadePos", "tMed");


    }

    public static void CRIAR_INFO_CIDADES_FRIAS(ZettaColecaoMonitorum informativos, Lista<Entidade> cidades_dados) {

        ENTT.ORDENAR_DOUBLE(cidades_dados, "tMed");
        ENTT.EXIBIR_TABELA(ENTT.SLICE_PRIMEIROS(cidades_dados, 10));
        PUBLIQUE_INFORMATIVO(informativos, "CidadesFrias", ENTT.SLICE_PRIMEIROS(cidades_dados, 10), "CidadeNome", "CidadePos", "tMed");

    }

    public static void CRIAR_INFO_CIDADES_ALTAS(ZettaColecaoMonitorum informativos, Lista<Entidade> cidades_dados) {

        ENTT.ORDENAR_INTEIRO_DECRESCENTE(cidades_dados, "Altitude");
        ENTT.EXIBIR_TABELA(ENTT.SLICE_PRIMEIROS(cidades_dados, 10));
        PUBLIQUE_INFORMATIVO(informativos, "CidadesAltas", ENTT.SLICE_PRIMEIROS(cidades_dados, 10), "CidadeNome", "CidadePos", "Altitude");

    }

    public static void CRIAR_INFO_CIDADES_BAIXAS(ZettaColecaoMonitorum informativos, Lista<Entidade> cidades_dados) {

        ENTT.ORDENAR_INTEIRO(cidades_dados, "Altitude");
        ENTT.EXIBIR_TABELA(ENTT.SLICE_PRIMEIROS(cidades_dados, 10));
        PUBLIQUE_INFORMATIVO(informativos, "CidadesBaixas", ENTT.SLICE_PRIMEIROS(cidades_dados, 10), "CidadeNome", "CidadePos", "Altitude");

    }

    public static void CRIAR_INFO_CIDADES_LITORANEAS(ZettaColecaoMonitorum informativos, Lista<Entidade> cidades_dados) {

        Lista<Entidade> cidades_criterio = ENTT.COLETAR(cidades_dados, "CidadeTipo", "Litoranea");

        ENTT.EXIBIR_TABELA(cidades_criterio);
        PUBLIQUE_INFORMATIVO(informativos, "CidadesLitoraneas", cidades_criterio, "CidadeNome", "CidadePos", "OceanoDistancia");

    }

    public static void CRIAR_INFO_CIDADES_CONTINENTAIS(ZettaColecaoMonitorum informativos, Lista<Entidade> cidades_dados) {

        Lista<Entidade> cidades_criterio = ENTT.COLETAR(cidades_dados, "CidadeTipo", "Continental");

        ENTT.EXIBIR_TABELA(cidades_criterio);
        PUBLIQUE_INFORMATIVO(informativos, "CidadesContinentais", cidades_criterio, "CidadeNome", "CidadePos", "OceanoDistancia");

    }


    public static void CRIAR_INFO_CIDADES_CLIMA_DOIDO(ZettaColecaoMonitorum informativos, Lista<Entidade> cidades_dados) {


        ENTT.ORDENAR_INTEIRO_DECRESCENTE(cidades_dados, "FCQ");

     //   ENTT.EXIBIR_TABELA(ENTT.SLICE_PRIMEIROS(cidades_dados,10));

        Lista<Entidade> cidades_criterio = ENTT.COLETAR_INTEIRO_MAIOR_OU_IGUAL(cidades_dados, "FCQ", 3);

        ENTT.EXIBIR_TABELA_COM_TITULO(cidades_criterio,":: CLIMA DOIDO");
        PUBLIQUE_INFORMATIVO(informativos, "CidadesClimaDoido", cidades_criterio, "CidadeNome", "CidadePos", "FatoresClimaticos");

    }


    public static void CRIAR_INFO_CIDADES_CLIMA_PARADO(ZettaColecaoMonitorum informativos, Lista<Entidade> cidades_dados) {


        ENTT.ORDENAR_INTEIRO_DECRESCENTE(cidades_dados, "FCQ");

        //   ENTT.EXIBIR_TABELA(ENTT.SLICE_PRIMEIROS(cidades_dados,10));

        Lista<Entidade> cidades_criterio = ENTT.COLETAR_INTEIRO_IGUAL(cidades_dados, "FCQ", 0);

        ENTT.EXIBIR_TABELA_COM_TITULO(cidades_criterio,":: CLIMA PARADO");
        PUBLIQUE_INFORMATIVO(informativos, "CidadesClimaParado", cidades_criterio, "CidadeNome", "CidadePos", "FatoresClimaticos");

    }

}

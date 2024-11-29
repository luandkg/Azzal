package apps.app_atzum;

import apps.app_atzum.utils.ArquivoAtzumGeral;
import apps.app_atzum.utils.ArquivoAtzumTronarko;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.zetta.ZettaColecao;
import libs.zetta.ZettaQuorum;

public class AztumTronarkoAnalises {


    public static void INIT() {

        String arquivo = AtzumCreator.LOCAL_GET_ARQUIVO("AtzumAnalises.az");
        ZettaQuorum colecoes = new ZettaQuorum(arquivo);

        String mTronarko = "7000";

        Atzum mAtzum = new Atzum();

        ArquivoAtzumGeral mArquivoAtzumGeral = new ArquivoAtzumGeral();
        ArquivoAtzumTronarko mArquivoAtzumTronarko = new ArquivoAtzumTronarko(mTronarko);

        ZettaColecao cidades = colecoes.getColecaoSempre("Cidades");
        ZettaColecao fatores_climaticos = colecoes.getColecaoSempre("Cidades::FatoresClimaticos");
        ZettaColecao temperaturas = colecoes.getColecaoSempre("Cidades::Temperatura");
        ZettaColecao umidades = colecoes.getColecaoSempre("Cidades::Umidade");
        ZettaColecao massas_de_ar = colecoes.getColecaoSempre("Cidades::MassasDeAr");

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

            String altitude = mArquivoAtzumGeral.GET_RELEVO_ALTITUDE(cidade.atInt("X"), cidade.atInt("Y")) + "m";

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

            long cidade_idx = cidades.adicionar(cidade);


        }


        for (Entidade cidade : cidades.getItens()) {

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

            fatores_climaticos.adicionar(e_cidade_fatores_climaticos);


            Entidade e_cidade_temperatura = new Entidade();
            e_cidade_temperatura.at("CidadeID", cidade.at("@ID"));
            e_cidade_temperatura.at("CidadeNome", cidade.at("CidadeNome"));

            for (int s = 1; s <= 500; s++) {

                double temperatura = mCidade.atDouble("T" + s);
                e_cidade_temperatura.at("T" + s, temperatura);

            }


            temperaturas.adicionar(e_cidade_temperatura);


            Entidade e_cidade_umidade = new Entidade();
            e_cidade_umidade.at("CidadeID", cidade.at("@ID"));
            e_cidade_umidade.at("CidadeNome", cidade.at("CidadeNome"));

            for (int s = 1; s <= 500; s++) {

                double umidade = mCidade.atDouble("U" + s);
                e_cidade_umidade.at("U" + s, umidade);

            }


            umidades.adicionar(e_cidade_umidade);


            Entidade e_cidade_massa_de_ar = new Entidade();
            e_cidade_massa_de_ar.at("CidadeID", cidade.at("@ID"));
            e_cidade_massa_de_ar.at("CidadeNome", cidade.at("CidadeNome"));

            for (int s = 1; s <= 500; s++) {

                String massa_de_ar = mCidade.at("M" + s);
                e_cidade_massa_de_ar.at("M" + s, massa_de_ar);

            }


            massas_de_ar.adicionar(e_cidade_massa_de_ar);


        }


        ENTT.EXIBIR_TABELA_COM_NOME(cidades.getItens(), "Cidades");
        //  ENTT.EXIBIR_TABELA_COM_NOME(fatores_climaticos.getItens(), "Cidades::FatoresClimaticos");
        //  ENTT.EXIBIR_TABELA_COM_NOME(temperaturas.getItens(), "Cidades::Temperatura");
        // ENTT.EXIBIR_TABELA_COM_NOME(umidades.getItens(), "Cidades::Umidade");
        ENTT.EXIBIR_TABELA_COM_NOME(massas_de_ar.getItens(), "Cidades::MassasDeAr");

        fmt.print(">> DUMP : COLEÇÕES");
        colecoes.dump();
        colecoes.fechar();


    }


    public static void VER() {

        String arquivo = AtzumCreator.LOCAL_GET_ARQUIVO("AtzumAnalises.az");
        ZettaQuorum colecoes = new ZettaQuorum(arquivo);


        ZettaColecao cidades = colecoes.getColecaoSempre("Cidades");
        ZettaColecao temperaturas = colecoes.getColecaoSempre("Cidades::Temperatura");
        ZettaColecao umidades = colecoes.getColecaoSempre("Cidades::Umidade");
        ZettaColecao fatores_climaticos = colecoes.getColecaoSempre("Cidades::FatoresClimaticos");

        Lista<Entidade> temperatura_dados = temperaturas.getItens();
        Lista<Entidade> umidade_dados = umidades.getItens();
        Lista<Entidade> fatores_climaticos_dados = fatores_climaticos.getItens();

        Lista<Entidade> cidades_dados = ENTT.SLICE_PRIMEIROS(cidades.getItens(), 10);

        for (Entidade cidade : cidades_dados) {

            //Opcional<Entidade> cidade_temperaturas = temperaturas.procurar_por_chave_estrangeira("CidadeID", cidade.at("@ID"));

            Entidade cidade_temperaturas = ENTT.GET_SEMPRE(temperatura_dados, "CidadeNome", cidade.at("CidadeNome"));
            Entidade cidade_umidade = ENTT.GET_SEMPRE(umidade_dados, "CidadeNome", cidade.at("CidadeNome"));
            Entidade cidade_fatores_climaticos = ENTT.GET_SEMPRE(fatores_climaticos_dados, "CidadeNome", cidade.at("CidadeNome"));

            double tMin = ENTT.ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MENOR(cidade_temperaturas, "T", 1, 500);
            double tMed = ENTT.ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MEDIA(cidade_temperaturas, "T", 1, 500);
            double tMax = ENTT.ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MAIOR(cidade_temperaturas, "T", 1, 500);

            cidade.at("tMin", tMin);
            cidade.at("tMed", tMed);
            cidade.at("tMax", tMax);

            double uMin = ENTT.ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MENOR(cidade_umidade, "U", 1, 500);
            double uMed = ENTT.ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MEDIA(cidade_umidade, "U", 1, 500);
            double uMax = ENTT.ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MAIOR(cidade_umidade, "U", 1, 500);

            cidade.at("uMin", uMin);
            cidade.at("uMed", uMed);
            cidade.at("uMax", uMax);

            cidade.at("FatoresClimaticos", Strings.LISTA_TO_TEXTO_LINHA(ENTT.COLETAR_ATRIBUTOS_NOME_QUANDO(cidade_fatores_climaticos, "SIM")));

        }


        ENTT.EXIBIR_TABELA(cidades_dados);


        colecoes.fechar();
    }

}

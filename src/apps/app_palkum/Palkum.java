package apps.app_palkum;

import apps.app_humanidade.Idioma;
import apps.app_humanidade.idiomas.*;
import libs.arquivos.IO;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fs.PastaFS;
import libs.luan.*;
import libs.meta_functional.Acao;
import libs.meta_functional.AcaoAlfa;
import libs.tronarko.utils.TronarkoAleatorium;
import libs.zetta.ItemColecionavel;
import libs.zetta.ZettaColecao;
import libs.zetta.ZettaQuorum;
import libs.zetta.features.ZQC;

public class Palkum {

    public static void init() {

        PastaFS pasta_palkum = new PastaFS("/home/luan/assets/palkum");

        String arquivo_palkum = pasta_palkum.getArquivo("palkum.az");

        Lista<Idioma> idiomas = new Lista<Idioma>();

        idiomas.adicionar(new IdiomaTraddes());
        idiomas.adicionar(new IdiomaMokkom());
        idiomas.adicionar(new IdiomaRequiz());
        idiomas.adicionar(new IdiomaPlaque());
        idiomas.adicionar(new IdiomaDommus());
        idiomas.adicionar(new IdiomaAlkoz());
        idiomas.adicionar(new IdiomaInmeb());
        idiomas.adicionar(new IdiomaUppuma());


        if (ZQC.COLECAO_CONTAGEM(arquivo_palkum, "Cidades") == 0) {

            ZQC.INSERIR(arquivo_palkum, "Cidades", ENTT.CRIAR("Nome", "Traddes"));
            ZQC.INSERIR(arquivo_palkum, "Cidades", ENTT.CRIAR("Nome", "Mokkom"));
            ZQC.INSERIR(arquivo_palkum, "Cidades", ENTT.CRIAR("Nome", "Requiz"));
            ZQC.INSERIR(arquivo_palkum, "Cidades", ENTT.CRIAR("Nome", "Plaque"));
            ZQC.INSERIR(arquivo_palkum, "Cidades", ENTT.CRIAR("Nome", "Dommus"));
            ZQC.INSERIR(arquivo_palkum, "Cidades", ENTT.CRIAR("Nome", "Alkoz"));
            ZQC.INSERIR(arquivo_palkum, "Cidades", ENTT.CRIAR("Nome", "Inmeb"));
            ZQC.INSERIR(arquivo_palkum, "Cidades", ENTT.CRIAR("Nome", "Uppuma"));


            ZQC.LIMPAR_TUDO(arquivo_palkum, "Sobrenomes");
            ZQC.LIMPAR_TUDO(arquivo_palkum, "Pessoas");

        }


        ZQC.EXIBIR_COLECAO(arquivo_palkum, "Cidades");

        ZQC.EXIBIR_COLECOES_RESUMO(arquivo_palkum);


        for (Entidade cidade : ZQC.COLECAO_ENTIDADES(arquivo_palkum, "Cidades")) {

            String cidade_nome = cidade.at("Nome").toUpperCase();

            Lista<Entidade> cidade_sobrenomes = ENTT.COLETAR(ZQC.COLECAO_ENTIDADES(arquivo_palkum,"Sobrenomes"),"Idioma",cidade_nome);


            if (ENTT.CONTAGEM(cidade_sobrenomes,"SobrenomeTipo","TIPO_1") == 0 && ENTT.CONTAGEM(cidade_sobrenomes,"SobrenomeTipo","TIPO_2") == 0) {

                fmt.print("Organizar nomes de {} ->> <{}> e <{}>", cidade_nome, ENTT.CONTAGEM(cidade_sobrenomes,"SobrenomeTipo","TIPO_1") , ENTT.CONTAGEM(cidade_sobrenomes,"SobrenomeTipo","TIPO_2") );

                Unico<String> sobrenomes = new Unico<String>(Strings.IGUALAVEL());

                Lista<String> sobrenomes_1 = new Lista<String>();
                Lista<String> sobrenomes_2 = new Lista<String>();

                Idioma idioma = Lista.OBTER_COM_ATRIBUTO(Idioma.PROCURAVEL_COM_NOME(),idiomas,cidade.at("Nome"));

                int sobrenomes_quantidade = 300 + Aleatorio.aleatorio_entre(100, 200);

                while (sobrenomes.getQuantidade() <= sobrenomes_quantidade) {
                    String nome = idioma.getUnico(sobrenomes);
                    if (Aleatorio.aleatorio(100) >= 50) {
                        sobrenomes_1.adicionar(nome);
                    } else {
                        sobrenomes_2.adicionar(nome);
                    }
                    sobrenomes.item(nome);
                }

                Lista<Entidade> s_sobrenomes_1 = ENTT.VALORES_PARA_ENTIDADES(sobrenomes_1, "Sobrenome");
                ENTT.ATRIBUTO_TODOS(s_sobrenomes_1,"Idioma",cidade_nome);
                ENTT.ATRIBUTO_TODOS(s_sobrenomes_1,"SobrenomeTipo","TIPO_1");
                ZQC.INSERIR_VARIOS(arquivo_palkum, "Sobrenomes", s_sobrenomes_1);

                Lista<Entidade> s_sobrenomes_2 = ENTT.VALORES_PARA_ENTIDADES(sobrenomes_2, "Sobrenome");
                ENTT.ATRIBUTO_TODOS(s_sobrenomes_2,"Idioma",cidade_nome);
                ENTT.ATRIBUTO_TODOS(s_sobrenomes_2,"SobrenomeTipo","TIPO_2");
                ZQC.INSERIR_VARIOS(arquivo_palkum, "Sobrenomes", s_sobrenomes_2);



            }

        }


        ZQC.ATUALIZAR(arquivo_palkum, "Cidades", new AcaoAlfa<ItemColecionavel>() {
            @Override
            public void fazer(ItemColecionavel item) {

                // TEM REGRAS PARA OS NOMES

                Entidade cidade = item.get();

                if (cidade.isDiferente("TemPopulacao", "SIM")) {

                    cidade.at("Populacao", String.valueOf(String.valueOf(Aleatorio.aleatorio_entre(1, 9))) + String.valueOf(Aleatorio.aleatorio_entre(0, 999)));
                    cidade.at("Populacionando", 0);
                    cidade.at("TemPopulacao", "SIM");

                    item.atualizar();
                }

            }
        });


        TronarkoAleatorium ta = TronarkoAleatorium.CRIAR_TRONARKOS(6500, 7500);


        ZettaQuorum palkum = new ZettaQuorum(arquivo_palkum);

        ZettaColecao cidades = palkum.getColecaoSempre("Cidades");
        ZettaColecao pessoas = palkum.getColecaoSempre("Pessoas");


        for (ItemColecionavel item_cidade : cidades.getItensEditaveis()) {

            Entidade cidade = item_cidade.get();

            fmt.print("Cidade : {}", cidade.at("Nome"));

            String cidade_nome = cidade.at("Nome").toUpperCase();

            Idioma idioma = Lista.OBTER_COM_ATRIBUTO(Idioma.PROCURAVEL_COM_NOME(),idiomas,cidade.at("Nome"));

            Lista<Entidade> cidade_sobrenomes = ZQC.COLECAO_ENTIDADES_COLETAR_SE(arquivo_palkum,"Sobrenomes","Idioma",cidade_nome);
            Lista<Entidade> sobrenomes_1 =ENTT.COLETAR(cidade_sobrenomes,"SobrenomeTipo","TIPO_1");
            Lista<Entidade> sobrenomes_2 =  ENTT.COLETAR(cidade_sobrenomes,"SobrenomeTipo","TIPO_2");

            PessoasCriadorDeNomes sociedade = new PessoasCriadorDeNomes(idioma, sobrenomes_1, sobrenomes_2);

            int populacao = cidade.atInt("Populacao");
            int populacionando = cidade.atInt("Populacionando");


            fmt.print("\t ++ Populacao      : {}", populacao);
            fmt.print("\t ++ Populacionando : {}", populacionando);

            if (populacionando < populacao) {

                int criando = 0;
                int criar_aqui = Aleatorio.aleatorio_entre(50, 200);

                fmt.print("\t ++ Criando        : {}", criar_aqui);

                while (populacionando < populacao && criando < criar_aqui) {

                    Entidade e_pessoa = new Entidade();
                    e_pessoa.at("Nome", Strings.CAPTALIZAR_FRASE(sociedade.get()));
                    e_pessoa.at("TDN", ta.getTozte().getTextoZerado());
                    e_pessoa.at("Cidade", cidade.at("Nome"));

                    pessoas.adicionar(e_pessoa);

                    criando += 1;
                    populacionando += 1;

                    cidade.atInt("Populacionando", populacionando);

                    cidade.at("Status", fmt.f2Porcentagem(cidade.atInt("Populacionando"), cidade.atInt("Populacao")));

                    item_cidade.atualizar();
                }


            }


        }


        palkum.fechar();

        ZQC.EXIBIR_COLECAO_ALGUNS(arquivo_palkum, "Pessoas");
        ZQC.EXIBIR_COLECAO(arquivo_palkum, "Cidades");


        fmt.print("Pessoas : {}", ZQC.COLECAO_CONTAGEM(arquivo_palkum, "Pessoas"));
    }

    public static void infos() {

        PastaFS pasta_palkum = new PastaFS("/home/luan/assets/palkum");

        String arquivo_palkum = pasta_palkum.getArquivo("palkum.az");

        ZQC.EXIBIR_COLECAO_ALGUNS(arquivo_palkum, "Pessoas");
        ZQC.EXIBIR_COLECAO_ALGUNS(arquivo_palkum, "Sobrenomes");
        ZQC.EXIBIR_COLECAO(arquivo_palkum, "Cidades");

        fmt.print("Pessoas    : {}", ZQC.COLECAO_CONTAGEM(arquivo_palkum, "Pessoas"));
        fmt.print("Sobrenomes : {}", ZQC.COLECAO_CONTAGEM(arquivo_palkum, "Sobrenomes"));



    }

    public static void nomes_comuns() {

        PastaFS pasta_palkum = new PastaFS("/home/luan/assets/palkum");

        String arquivo_palkum = pasta_palkum.getArquivo("palkum.az");



        Lista<Entidade> nomes_comuns = new Lista<Entidade>();


        ZQC.PROCESSAR_EM_INTERVALOS_COM_ANALITICO(arquivo_palkum, "Pessoas", 100, true, 3000, new AcaoAlfa<Entidade>() {
            @Override
            public void fazer(Entidade pessoa) {

                String nome = Strings.DIVIDIR_ESPACOS(pessoa.at("Nome")).get(1);

                Entidade nome_comum = ENTT.GET_SEMPRE(nomes_comuns, "Nome", nome);
                nome_comum.atIntSomar("Quantidade", 0, 1);

            }
        }, new Acao() {
            @Override
            public void fazer() {

                Lista<Entidade> analiticos_trendings = ENTT.COLETAR_INTEIRO_MAIOR_OU_IGUAL(nomes_comuns,"Quantidade",10);
                fmt.print("Analiticos Trendings : {}",ENTT.CONTAGEM(analiticos_trendings));

                ENTT.ORDENAR_INTEIRO_DECRESCENTE(analiticos_trendings,"Quantidade");
                ENTT.EXIBIR_TABELA(ENTT.SLICE_PRIMEIROS(analiticos_trendings,5));

            }
        });

        Lista<Entidade> nomes_trendings = ENTT.COLETAR_INTEIRO_MAIOR_OU_IGUAL(nomes_comuns,"Quantidade",10);


        fmt.print("Quantidade : {}",ENTT.CONTAGEM(nomes_comuns));
        fmt.print("Trendings : {}",ENTT.CONTAGEM(nomes_trendings));


        fmt.print("Ordenando....");

        ENTT.ORDENAR_INTEIRO_DECRESCENTE(nomes_trendings,"Quantidade");
        ENTT.EXIBIR_TABELA(ENTT.SLICE_PRIMEIROS(nomes_trendings,100));

    }


    public static void EXIBIR_RESUMO(){

        PastaFS pasta_palkum = new PastaFS("/home/luan/assets/palkum");

        String arquivo_palkum = pasta_palkum.getArquivo("palkum.az");

        ZQC.EXIBIR_COLECOES_RESUMO(arquivo_palkum);

        ZQC.EXIBIR_COLECAO_ALGUNS(arquivo_palkum,"Sobrenomes");


    }
}

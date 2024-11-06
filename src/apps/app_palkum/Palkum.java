package apps.app_palkum;

import libs.arquivos.DSInterno;
import libs.arquivos.ds.DS;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fs.PastaFS;
import libs.luan.Aleatorio;
import libs.luan.Lista;
import libs.luan.fmt;

public class Palkum {

    public static void init() {

        PastaFS pasta_palkum = new PastaFS("/home/luan/assets/palkum");

        String arquivo_populacao = pasta_palkum.getArquivo("populacao.ds");

        Lista<String> cidades = new Lista<String>();

        cidades.adicionar("Traddes");
        cidades.adicionar("Mokkom");
        cidades.adicionar("Requiz");
        cidades.adicionar("Plaque");
        cidades.adicionar("Dommus");
        cidades.adicionar("Alkoz");
        cidades.adicionar("Inmeb");
        cidades.adicionar("Uppuma");


        Lista<Entidade> cidade_populacionar = ENTT.CRIAR_LISTA();

        for (String cidade : cidades) {
            Entidade e_cidade = ENTT.CRIAR_EM_SEQUENCIALMENTE(cidade_populacionar, "CidadeID");
            e_cidade.at("Nome", cidade);
            e_cidade.at("Populacao", String.valueOf(Aleatorio.aleatorio_entre(0, 3)) + String.valueOf(Aleatorio.aleatorio_entre(1, 9)) + String.valueOf(Aleatorio.aleatorio_entre(1, 9)) + fmt.f3(Aleatorio.aleatorio_entre(0, 999)));
            e_cidade.at("Populacionando", 0);
        }

        // ENTT.EXIBIR_TABELA_COM_NOME(cidade_populacionar,"Cidade");

        //  DS.limpar(arquivo_populacao);
        //  DS.adicionar_pre_alocado(arquivo_populacao,"@populacao.entts", Matematica.KB(400));
        //  DS.alterar_pre_alocado(arquivo_populacao,"@populacao.entts",ENTT.TO_DOCUMENTO(cidade_populacionar));

        Lista<Entidade> cidades_dados = DSInterno.PARSER_ENTIDADES(DS.buscar_item(arquivo_populacao, "@populacao.entts").get());


        for (Entidade cidade : cidades_dados) {

            fmt.print("Cidade : {}", cidade.at("Nome"));

            int populacao = cidade.atInt("Populacao");
            int populacionando = cidade.atInt("Populacionando");

            int criando = 0;
            int criar_aqui = Aleatorio.aleatorio_entre(50, 200);

            fmt.print("\t ++ Populacao      : {}", populacao);
            fmt.print("\t ++ Populacionando : {}", populacionando);
            fmt.print("\t ++ Criando        : {}", criar_aqui);

            while (populacionando < populacao && criando < criar_aqui) {

                DS.adicionar(arquivo_populacao,cidade.at("CidadeID") +":"+populacionando+".pessoa","");

                criando += 1;
                populacionando += 1;
            }

            cidade.atInt("Populacionando", populacionando);
        }

        DS.alterar_pre_alocado(arquivo_populacao, "@populacao.entts", ENTT.TO_DOCUMENTO(cidades_dados));

        ENTT.EXIBIR_TABELA(cidades_dados);


        fmt.print("Itens : {}",DS.contar(arquivo_populacao));
    }

}

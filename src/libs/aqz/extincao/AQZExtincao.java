package libs.aqz.extincao;

import libs.armazenador.ParticaoEmExtincao;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.fmt;

public class AQZExtincao {

    public static void EXIBIR_ESTRUTURA_INTERNA(String arquivo_banco) {

        AZInternamenteAntigamente aqz = new AZInternamenteAntigamente(arquivo_banco);

        Lista<Entidade> entidades_exibir = new Lista<Entidade>();

        for (ParticaoEmExtincao particao_primaria : aqz.particoes_primarias()) {
            Entidade particao = new Entidade();

            particao.at("Nome", particao_primaria.getNome());
            particao.at("Itens", particao_primaria.getItensContagem());
            particao.at("Usabilidade", particao_primaria.getUsabilidade());
            particao.at("Disponibilidade", particao_primaria.getDisponibilidade());
            particao.at("Tamanho", fmt.formatar_tamanho(particao_primaria.getTamanho()));

            entidades_exibir.adicionar(particao);
        }



        aqz.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(entidades_exibir, "ESTRUTURA INTERNA - PARTIÇÕES PRIMÁRIAS");


    }

    public static void EXIBIR_ESTRUTURA_PUBLICA(String arquivo_banco) {

        AZInternamenteAntigamente aqz = new AZInternamenteAntigamente(arquivo_banco);

        Lista<Entidade> objetos_publicos = new Lista<Entidade>();

        for (ParticaoEmExtincao item : aqz.colecoes_listar()) {
            Entidade banco_item = new Entidade();

            banco_item.at("Nome", item.getNome());
            banco_item.at("Itens", item.getItensContagem());
            banco_item.at("Usabilidade", item.getUsabilidade());
            banco_item.at("Disponibilidade", item.getDisponibilidade());
            banco_item.at("Tamanho", fmt.formatar_tamanho(item.getTamanho()));

            objetos_publicos.adicionar(banco_item);
        }

        aqz.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(objetos_publicos, "ESTRUTURA PÚBLICA");


    }

    public static void EXIBIR_COLECAO(String arquivo_banco, String colecao_nome) {

        AZInternamenteAntigamente aqz = new AZInternamenteAntigamente(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBancoEmExtincao item : aqz.colecoes_obter(colecao_nome).getItens()) {
            objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTexto()));
        }

        ENTT.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }


    public static Lista<Entidade> COLECAO_ITENS(String arquivo_banco, String colecao) {

        AZInternamenteAntigamente aqz = new AZInternamenteAntigamente(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBancoEmExtincao item : aqz.colecoes_obter(colecao).getItens()) {
            objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTexto()));
        }

        aqz.fechar();

        return objetos;
    }

    public static Lista<String> COLECOES_LISTAR(String arquivo_banco) {

        Lista<String> colecoes = new Lista<String>();

        AZInternamenteAntigamente aqz = new AZInternamenteAntigamente(arquivo_banco);

        for (ParticaoEmExtincao b : aqz.colecoes_listar()) {
            colecoes.adicionar(b.getNome());
        }

        aqz.fechar();

        return colecoes;
    }
}

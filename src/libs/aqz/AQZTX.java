package libs.aqz;

import libs.aqz.colecao.AZInternamenteTX;
import libs.aqz.colecao.ColecaoTX;
import libs.aqz.utils.ItemDoBancoTX;
import libs.armazenador.ParticaoMestre;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.fmt;

public class AQZTX {

    public static void EXIBIR_ESTRUTURA_PUBLICA(String arquivo_banco) {

        AZInternamenteTX aqz = new AZInternamenteTX(arquivo_banco);

        Lista<Entidade> objetos_publicos = new Lista<Entidade>();

        for (ParticaoMestre item : aqz.particoes_listar()) {
            Entidade banco_item = new Entidade();

            banco_item.at("Nome", item.getNome());
            banco_item.at("Itens", item.getItensContagem());
            banco_item.at("Usabilidade", item.getUsabilidade());
            banco_item.at("Disponibilidade", item.getDisponibilidade());
            banco_item.at("Tamanho", fmt.formatar_tamanho(item.getTamanho()));

            objetos_publicos.adicionar(banco_item);
        }

        aqz.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(objetos_publicos, "TX - ESTRUTURA PÚBLICA");


    }

    public static void EXIBIR_COLECAO(String arquivo_banco, String colecao_nome) {

        AZInternamenteTX aqz = new AZInternamenteTX(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBancoTX item : aqz.colecoes_obter(colecao_nome).getItens()) {
            objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTextoTX()));
        }

        ENTT.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }

    public static void EXIBIR_TUDO(String arquivo_banco) {

        AZInternamenteTX aqz = new AZInternamenteTX(arquivo_banco);

        for (ParticaoMestre particaoPrimaria : aqz.particoes_primarias()) {

            Lista<Entidade> objetos = new Lista<Entidade>();
            for (ItemDoBancoTX item : particaoPrimaria.getItensTX()) {
                objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTextoTX()));
            }

            ENTT.EXIBIR_TABELA_COM_TITULO(objetos, "TX :: INTERNO - " + particaoPrimaria.getNome());

        }

        for (ColecaoTX colecao : aqz.colecoes_listar()) {

            Lista<Entidade> objetos = new Lista<Entidade>();
            for (ItemDoBancoTX item : colecao.getItens()) {
                objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTextoTX()));
            }

            ENTT.EXIBIR_TABELA_COM_TITULO(objetos, "TX :: PUBLICO - " + colecao.getNome());

        }


        aqz.fechar();

    }

}

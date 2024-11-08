package libs.aqz.tabela;

import libs.armazenador.Banco;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Resultado;
import libs.luan.fmt;

public class AQZTabelas {

    public static void EXIBIR_TABELAS_DADOS(String arquivo_banco) {

        AZTabelasInternamente aqz = new AZTabelasInternamente(arquivo_banco);

        Lista<Entidade> objetos_publicos = new Lista<Entidade>();

        for (Banco item : aqz.tabelas_listar()) {
            Entidade banco_item = new Entidade();

            banco_item.at("Nome", item.getNome());
            banco_item.at("Itens", item.getItensContagem());
            banco_item.at("Usabilidade", item.getUsabilidade());
            banco_item.at("Disponibilidade", item.getDisponibilidade());
            banco_item.at("Tamanho", fmt.formatar_tamanho(item.getTamanho()));

            objetos_publicos.adicionar(banco_item);
        }

        aqz.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(objetos_publicos, "TABELAS @DADOS");


    }

    public static void EXIBIR_ESQUEMAS_DADOS(String arquivo_banco) {

        AZTabelasInternamente aqz = new AZTabelasInternamente(arquivo_banco);

        Lista<Entidade> objetos_publicos = new Lista<Entidade>();

        for (Banco item : aqz.esquemas_listar()) {
            Entidade banco_item = new Entidade();

            banco_item.at("Nome", item.getNome());
            banco_item.at("Itens", item.getItensContagem());
            banco_item.at("Usabilidade", item.getUsabilidade());
            banco_item.at("Disponibilidade", item.getDisponibilidade());
            banco_item.at("Tamanho", fmt.formatar_tamanho(item.getTamanho()));

            objetos_publicos.adicionar(banco_item);
        }

        aqz.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(objetos_publicos, "TABELAS @ESQUEMAS");


    }


    public static void ADICIONAR_OU_EXIBIR_ERRO(AQZTabela pessoas, Entidade novo) {
        Resultado<Boolean, String> resultado = pessoas.adicionar(novo);
        if (resultado.isErro()) {
            fmt.print(">> ERRO :: {}",resultado.getErro());
        }
    }
}

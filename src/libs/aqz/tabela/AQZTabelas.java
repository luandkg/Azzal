package libs.aqz.tabela;

import libs.armazenador.Banco;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Resultado;
import libs.luan.Strings;
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
            fmt.print(">> ERRO :: {}", resultado.getErro());
        }
    }

    public static void ATUALIZAR_OU_EXIBIR_ERRO(RefLinhaDaTabela item_ref, Entidade atualizando) {
        Resultado<Boolean, String> resultado = item_ref.atualizar(atualizando);
        if (resultado.isErro()) {
            fmt.print(">> ERRO :: {}", resultado.getErro());
        }
    }


    public static Entidade CRIAR_VERIFICADOR(String eTipo, String eValor) {
        return ENTT.CRIAR("Tipo", eTipo, "Valor", eValor);
    }

    public static Entidade CRIAR_VERIFICADOR(String eTipo, int eValor) {
        return ENTT.CRIAR("Tipo", eTipo, "Valor", eValor);
    }

    public static Entidade CRIAR_VERIFICADOR_CONTEM(String eTipo, Lista<String> valores) {
        return ENTT.CRIAR("Tipo", eTipo, "Valor", Strings.LISTA_TO_TEXTO_TAB(valores));
    }


}

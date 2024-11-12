package libs.aqz;

import libs.aqz.colecao.AZInternamenteUTF8;
import libs.aqz.colecao.AZInternamenteUTF8Visualizadores;
import libs.aqz.utils.ItemDoBancoUTF8;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;

public class AQZUTF8View {

    public static void DEFINIR_VIEW(String arquivo_banco, String view_nome, String colecao_nome, Lista<String> colunas) {

        AZInternamenteUTF8Visualizadores aqz = new AZInternamenteUTF8Visualizadores(arquivo_banco);

        if (aqz.views_existe(view_nome)) {
            aqz.views_remover(view_nome);
        }

        aqz.views_criar(view_nome, colecao_nome, colunas);

        aqz.fechar();
    }

    public static void EXIBIR_VIEW(String arquivo_banco, String view_nome) {

        view_nome = view_nome.toUpperCase();

        AZInternamenteUTF8Visualizadores aqz = new AZInternamenteUTF8Visualizadores(arquivo_banco);

        Entidade view = aqz.views_obter(view_nome);

        Lista<String> colunas = new Lista<String>();
        String colecao_nome = view.at("Banco");

        for (Entidade col : view.getEntidades()) {
            colunas.adicionar(col.at("Nome"));
        }

        Lista<Entidade> objetos = new Lista<Entidade>();

        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade obj = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            // fmt.print(">>{}",item.lerTexto());

            Entidade obj_em_view = ENTT.CRIAR_EM(objetos);
            for (String coluna : colunas) {
                obj_em_view.at(coluna, obj.at(coluna));
            }

        }

        aqz.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(objetos, "@VIEW - " + view_nome);

    }

    public static void REMOVER_VIEW(String arquivo_banco, String view_nome) {

        AZInternamenteUTF8Visualizadores aqz = new AZInternamenteUTF8Visualizadores(arquivo_banco);

        if (aqz.views_existe(view_nome)) {
            aqz.views_remover(view_nome);
        }

        aqz.fechar();
    }
}

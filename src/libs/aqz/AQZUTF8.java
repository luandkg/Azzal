package libs.aqz;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;

public class AQZUTF8 {

    // LUAN FREITAS
    // CRIADO EM : 2024 11 05

    public static void EXIBIR_CONTEUDO(String arquivo_banco, String colecao) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter_utf8(colecao).getObjetosUTF8();

        aqz.fechar();

        ENTT.EXIBIR_TABELA(dados);

    }


    public static void EXIBIR_AMOSTRA(String arquivo_banco, String colecao) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter_utf8(colecao).getObjetosUTF8();

        aqz.fechar();

        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(dados));

    }

    public static void EXIBIR_DESCRITORES(String arquivo_banco, String colecao) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter_utf8(colecao).getObjetosUTF8();

        aqz.fechar();


        ENTT.EXIBIR_TABELA(ENTT.GET_DESCRITORES(dados));

    }

}

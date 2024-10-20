package libs.ez;


import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;


public class AQ {

    public static Entidade CRIAR_ITEM() {
        return new Entidade();
    }


    public static Lista<Entidade> GET_COLECAO(String arquivo_banco, String colecao) {

        Lista<Entidade> ls = new Lista<Entidade>();

        AQColecionador.checar(arquivo_banco);

        AQColecionador m = new AQColecionador(arquivo_banco);

        for (ItemDoBanco item : m.getColecao(colecao).getItens()) {
            ls.adicionar(item.toEntidade());
        }


        m.fechar();

        return ls;
    }


    public static void CHECAR(String arquivo_banco) {
        AQColecionador.checar(arquivo_banco);
    }

    public static void ZERAR_COLECAO(String arquivo_banco, String colecao) {

        AQColecionador.checar(arquivo_banco);

        AQColecionador m = new AQColecionador(arquivo_banco);

        m.getColecao(colecao).limpar();
        m.getColecao(colecao).zerarSequencial();

        m.fechar();

    }

    public static long CONTAR_COLECAO(String arquivo_banco, String colecao) {

        AQColecionador.checar(arquivo_banco);

        AQColecionador m = new AQColecionador(arquivo_banco);

        long quantidade = m.getColecao(colecao).getItensContagem();

        m.fechar();

        return quantidade;
    }

    public static void DUMP_COLECAO(String arquivo_banco, String colecao) {
        ENTT.EXIBIR_TABELA(AQ.GET_COLECAO(arquivo_banco, colecao));
    }

    public static void AUTO_ANALISAR(String arquivo_banco) {
        AQColecionador col = new AQColecionador(arquivo_banco);
        col.auto_analisar();
        col.fechar();
    }



    public static void INSERIR(String arquivo_banco, String colecao, Entidade objeto) {

        AQColecionador m = new AQColecionador(arquivo_banco);
        m.getColecao(colecao).adicionar(objeto);
        m.fechar();

    }


    public static void ZERAR_COLECAO(String arquivo_banco, String colecao, String info_log) {

        AQColecionador.checar(arquivo_banco);

        AQColecionador m = new AQColecionador(arquivo_banco);

        m.getColecao(colecao).limpar_com_log(info_log);
        m.getColecao(colecao).zerarSequencial();

        m.fechar();

    }

    public static void REMOVER_COLECAO(String arquivo_banco, String colecao) {

        AQColecionador.checar(arquivo_banco);

        AQColecionador m = new AQColecionador(arquivo_banco);

        m.getColecao(colecao).limpar();
        m.getColecao(colecao).zerarSequencial();
        m.remover_colecao(colecao);

        m.fechar();

    }

    public static Entidade GET_COLECAO_INFO(String arquivo_banco, String colecao) {

        Lista<Entidade> infos = AQ.GET_COLECAO(arquivo_banco, "@Analise");
        for (Entidade col : infos) {
            if (col.is("Nome",colecao)) {
                return col;
            }
        }

        return null;
    }


    public static void ATUALIZAR_ITEM(String arquivo_banco, String colecao, int eObjetoID, Entidade objeto) {


        AQColecionador.checar(arquivo_banco);

        AQColecionador m = new AQColecionador(arquivo_banco);

        m.getColecao(colecao).atualizar_por_chave(eObjetoID, objeto);

        m.fechar();

    }

    public static void REMOVER_ITEM(String arquivo_banco, String colecao, int eObjetoID) {


        AQColecionador.checar(arquivo_banco);

        AQColecionador m = new AQColecionador(arquivo_banco);

        m.getColecao(colecao).remover_por_chave(eObjetoID);

        m.fechar();

    }


    public static Entidade OBTER_ITEM_EXISTENCIAL(String arquivo_banco, String colecao, String nome) {

        boolean existe = false;
        Entidade ret = null;

        for (Entidade objeto : AQ.GET_COLECAO(arquivo_banco, colecao)) {
            if (objeto.is("Nome",nome)) {
                existe = true;
                ret = objeto;
                break;
            }
        }

        if (!existe) {
            ret = AQ.CRIAR_ITEM();
            ret.at("Nome", nome);
            AQ.INSERIR(arquivo_banco, colecao, ret);
        }

        return ret;
    }


    public static void DUMP_ANALISE(String arquivo_banco){
        AQ.DUMP_COLECAO(arquivo_banco, "@Analise");
    }

}

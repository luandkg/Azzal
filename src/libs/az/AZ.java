package libs.az;

import libs.dkg.DKGFeatures;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;


public class AZ {


    public static void INSERIR(String arquivo_banco, String colecao_nome, DKGObjeto objeto) {

        AZColecionador.checar(arquivo_banco);

        AZColecionador m = new AZColecionador(arquivo_banco);

        m.getColecao(colecao_nome).adicionar(objeto);

        m.fechar();

    }


    public static void LIMPAR(String arquivo_banco, String colecao_nome) {

        AZColecionador.checar(arquivo_banco);

        AZColecionador m = new AZColecionador(arquivo_banco);

        m.getColecao(colecao_nome).limpar();
        m.getColecao(colecao_nome).zerarSequencial();

        m.fechar();

    }


    public static void ATUALIZAR(String arquivo_banco) {

        AZColecionador.checar(arquivo_banco);

        AZColecionador m = new AZColecionador(arquivo_banco);

        m.auto_analisar();

        m.fechar();

    }


    public static void EXIBIR_COLECAO(String arquivo_banco,String colecao_nome) {

        AZColecionador.checar(arquivo_banco);

        AZColecionador m = new AZColecionador(arquivo_banco);

        DKGFeatures.EXIBIR_TABELA(m.getColecao(colecao_nome).getObjetos());

        m.fechar();

    }


    public static Lista<DKGObjeto> GET_COLECAO(String arquivo_banco, String colecao_nome) {

        AZColecionador.checar(arquivo_banco);

        AZColecionador m = new AZColecionador(arquivo_banco);

        Lista<DKGObjeto>  colecao = m.getColecao(colecao_nome).getObjetos();

        m.fechar();

        return colecao;
    }


}

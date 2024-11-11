package libs.aqz;

import libs.aqz.colecao.AZInternamenteUTF8;
import libs.aqz.colecao.ColecaoUTF8;
import libs.aqz.utils.ItemDoBancoTX;
import libs.aqz.utils.ItemDoBancoUTF8;
import libs.armazenador.ParticaoMestre;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;
import libs.luan.fmt;

public class AQZUTF8 {

    public static void EXIBIR_ESTRUTURA_PUBLICA(String arquivo_banco) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

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

        ENTT.EXIBIR_TABELA_COM_NOME(objetos_publicos, "UTF8 - ESTRUTURA PÚBLICA");


    }

    public static void EXIBIR_COLECAO(String arquivo_banco, String colecao_nome) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {
            objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTextoUTF8()));
        }

        ENTT.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }

    public static void EXIBIR_TUDO(String arquivo_banco) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        for (ParticaoMestre particaoPrimaria : aqz.particoes_primarias()) {

            Lista<Entidade> objetos = new Lista<Entidade>();
            for (ItemDoBancoTX item : particaoPrimaria.getItensTX()) {
                objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTextoTX()));
            }

            ENTT.EXIBIR_TABELA_COM_TITULO(objetos, "UTF8 :: INTERNO - " + particaoPrimaria.getNome());

        }

        for (ColecaoUTF8 colecao : aqz.colecoes_listar()) {

            Lista<Entidade> objetos = new Lista<Entidade>();
            for (ItemDoBancoUTF8 item : colecao.getItens()) {
                objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTextoUTF8()));
            }

            ENTT.EXIBIR_TABELA_COM_TITULO(objetos, "UTF8 :: PUBLICO - " + colecao.getNome());

        }


        aqz.fechar();

    }



    public static void COLECOES_ORGANIZAR(String arquivo_banco, String colecao_nome) {
        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);
        aqz.colecao_orgarnizar_e_obter(colecao_nome);
        aqz.fechar();
    }


    public static void LIMPAR_TUDO(String arquivo_banco, String colecao_nome) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);
        ColecaoUTF8 colecao = aqz.colecao_orgarnizar_e_obter(colecao_nome);
        colecao.limpar();
        colecao.zerarSequencial();
        aqz.fechar();

    }

    public static void INSERIR(String arquivo_banco, String colecao_nome, Entidade novo) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);
        ColecaoUTF8 colecao = aqz.colecao_orgarnizar_e_obter(colecao_nome);
        colecao.adicionar(novo);
        aqz.fechar();

    }




    public static void INSERIR_VARIOS(String arquivo_banco, String colecao_nome, Lista<Entidade> novos) {
        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        ColecaoUTF8 colecao = aqz.colecao_orgarnizar_e_obter(colecao_nome);

        for (Entidade novo : novos) {
            colecao.adicionar(novo);
        }

        aqz.fechar();
    }




    public static Entidade NOVA_ENTIDADE() {
        return new Entidade();
    }

    public static Lista<Entidade> COLECAO_ENTIDADES(String arquivo_banco, String colecao_nome) {
        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);
        Lista<Entidade> dados = aqz.colecao_orgarnizar_e_obter(colecao_nome).getObjetos();
        aqz.fechar();

        return dados;
    }

    public static long COLECAO_CONTAGEM(String arquivo_banco, String colecao_nome) {
        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);
        long contagem = aqz.colecao_orgarnizar_e_obter(colecao_nome).getItensContagem();
        aqz.fechar();

        return contagem;
    }


    public static Opcional<Par<ItemDoBancoUTF8, Entidade>> GET_UNICO(String arquivo_banco, String colecao_nome, String att_nome, String att_valor) {

        Opcional<Par<ItemDoBancoUTF8, Entidade>> ret = Opcional.CANCEL();

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        ColecaoUTF8 colecao = aqz.colecao_orgarnizar_e_obter(colecao_nome);
        for (ItemDoBancoUTF8 item : colecao.getItens()) {
            Entidade e_item = item.toEntidadeUTF8();
            if (e_item.is(att_nome, att_valor)) {
                ret = Opcional.OK(new Par<ItemDoBancoUTF8, Entidade>(item, e_item));
                break;
            }

        }


        aqz.fechar();

        return ret;
    }

    public static void ATUALIZAR(String arquivo_banco, String colecao_nome, ItemDoBancoUTF8 item, Entidade entidade) {
        throw new RuntimeException("NÃO IMPLEMENTADO !");
        // item.atualizarUTF8();
    }

}

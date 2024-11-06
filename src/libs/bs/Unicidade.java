package libs.bs;

import libs.armazenador.Banco;
import libs.armazenador.ItemDoBanco;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;


public class Unicidade {

    private String mNome;
    private Banco mSequencias;
    private Banco mColecao;

    public Unicidade(String eNome,  Banco eSequencias, Banco eColecao) {
        mNome = eNome;
        mSequencias = eSequencias;
        mColecao = eColecao;

        Sequenciador.organizar_sequencial(mSequencias, mNome);

    }

    public boolean contem_valor(String valor) {

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (objeto.is("Valor",valor)) {
                return true;
            }
        }

        return false;
    }

    public boolean adicionar(String valor) {

        if (!contem_valor(valor)) {

            int chave = Sequenciador.aumentar_sequencial(mSequencias, mNome);

            Entidade objeto = new Entidade();
            objeto.at("ID",chave);
            objeto.at("Valor",valor);

            mColecao.adicionar(objeto);
        }

        return false;
    }

    public void remover_valor(String valor) {

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (objeto.is("Valor",valor)) {
                mColecao.remover(item);
                break;
            }
        }

    }

    public Lista<ItemDoBanco> getItens() {
        return mColecao.getItens();
    }
}

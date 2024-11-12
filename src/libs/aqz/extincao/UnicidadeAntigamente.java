package libs.aqz.extincao;

import libs.armazenador.ParticaoEmExtincao;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;


public class UnicidadeAntigamente {

    private String mNome;
    private ParticaoEmExtincao mSequencias;
    private ParticaoEmExtincao mColecao;

    public UnicidadeAntigamente(String eNome, ParticaoEmExtincao eSequencias, ParticaoEmExtincao eColecao) {
        mNome = eNome;
        mSequencias = eSequencias;
        mColecao = eColecao;

        Sequenciador.organizar_sequencial(mSequencias, mNome);

    }

    public boolean contem_valor(String valor) {

        for (ItemDoBancoEmExtincao item : mColecao.getItens()) {
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

        for (ItemDoBancoEmExtincao item : mColecao.getItens()) {
            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (objeto.is("Valor",valor)) {
                mColecao.remover(item);
                break;
            }
        }

    }

    public Lista<ItemDoBancoEmExtincao> getItens() {
        return mColecao.getItens();
    }
}

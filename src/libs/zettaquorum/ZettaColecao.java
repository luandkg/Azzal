package libs.zettaquorum;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.Armazem;
import libs.fazendario.ItemAlocado;
import libs.luan.Lista;

public class ZettaColecao {

    private Armazem mArmazem;
    private ZettaSequenciador mSequenciador;

    public ZettaColecao(Armazem eArmazem, ZettaSequenciador eSequenciador) {
        mArmazem = eArmazem;
        mSequenciador = eSequenciador;
    }


    public Lista<Entidade> getItens() {

        Lista<Entidade> lista = new Lista<Entidade>();

        for (ItemAlocado item : mArmazem.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            e_item.at("@PTR", item.getPonteiroDados());

            e_item.tornar_primeiro("@PTR");
            e_item.tornar_primeiro("@ID");

            lista.adicionar(e_item);
        }

        return lista;
    }


    public void adicionar(Entidade e) {

        long proximo = mSequenciador.getProximo();

        e.at("@ID", proximo);

        mArmazem.item_adicionar(ENTT.TO_DOCUMENTO(e));
    }


    public void exibir_colecao() {
        ENTT.EXIBIR_TABELA_COM_NOME(getItens(), "COLEÇÃO :: " + mArmazem.getNome());
    }

}

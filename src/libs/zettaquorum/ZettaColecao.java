package libs.zettaquorum;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.Armazem;
import libs.fazendario.ArmazemIndiceSumario;
import libs.fazendario.IndiceLocalizado;
import libs.fazendario.ItemAlocado;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Strings;
import testes.ItemColecionavel;

public class ZettaColecao {

    private Armazem mArmazem;
    private ZettaSequenciador mSequenciador;
    private ArmazemIndiceSumario mIndice;

    public ZettaColecao(Armazem eArmazem, ZettaSequenciador eSequenciador, ArmazemIndiceSumario eIndice) {
        mArmazem = eArmazem;
        mSequenciador = eSequenciador;
        mIndice = eIndice;
    }

    public long contagem() {
        return mArmazem.getItensUtilizadosContagem();
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

    public Lista<ItemColecionavel> getItensEditaveis() {
        Lista<ItemColecionavel> lista = new Lista<ItemColecionavel>();

        for (ItemAlocado item : mArmazem.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            e_item.at("@PTR", item.getPonteiroDados());

            e_item.tornar_primeiro("@PTR");
            e_item.tornar_primeiro("@ID");

            lista.adicionar(new ItemColecionavel(mIndice, item, e_item));
        }

        return lista;
    }

    public void adicionar(Entidade e) {

        long proximo = mSequenciador.getProximo();

        e.at("@ID", proximo);


        ItemAlocado item = mArmazem.item_adicionar(ENTT.TO_DOCUMENTO(e));

        //  fmt.print("@ID :: {}",e.at("@ID"));

        // fmt.print("Ponteiro Dados :: {}",item.getPonteiroDados());

        mIndice.setItem(proximo, item.getPonteiroDados());
    }


    public Lista<Entidade> getIndices() {

        Lista<Entidade> lista = new Lista<Entidade>();

        for (IndiceLocalizado item : mIndice.getIndices()) {

            Entidade e_item = new Entidade();
            e_item.at("Indice", item.getIndice());
            e_item.at("Ponteiro", item.getPonteiro());


            lista.adicionar(e_item);
        }

        return lista;
    }


    public void exibir_colecao() {
        ENTT.EXIBIR_TABELA_COM_NOME(getItens(), "COLEÇÃO :: " + mArmazem.getNome());
    }

    public void exibir_indice() {
        ENTT.EXIBIR_TABELA_COM_NOME(getIndices(), "INDICE :: " + mArmazem.getNome());
    }

    public Opcional<IndiceLocalizado> procurar_indice(long indice) {
        return mIndice.procurar_indice(indice);
    }

    public Opcional<IndiceLocalizado> getIndiceMaior() {
        return mIndice.getIndiceMaior();
    }

    public Opcional<Entidade> procurar_item_por_indice(long indice) {

        Opcional<IndiceLocalizado> op_indice = procurar_indice(indice);

        if (op_indice.isOK()) {

            mArmazem.getArquivador().setPonteiro(op_indice.get().getPonteiro());

            int tam = mArmazem.getArquivador().get_u32();
            byte[] dados = mArmazem.getArquivador().get_u8_array(tam);

            Entidade e_item = ENTT.PARSER_ENTIDADE(Strings.GET_STRING_VIEW(dados));

            e_item.at("@PTR", op_indice.get().getPonteiro());

            e_item.tornar_primeiro("@PTR");
            e_item.tornar_primeiro("@ID");

            return Opcional.OK(e_item);

        }

        return Opcional.CANCEL();
    }
}

package libs.zetta;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.zetta.fazendario.*;

public class ZettaColecao {

    private String mNome;
    private Armazem mDados;
    private ZettaSequenciador mSequenciador;
    private ArmazemIndiceSumario mIndice;

    public ZettaColecao(String eNome, Armazem eArmazem, ZettaSequenciador eSequenciador, ArmazemIndiceSumario eIndice) {
        mNome = eNome;
        mDados = eArmazem;
        mSequenciador = eSequenciador;
        mIndice = eIndice;
    }

    public long getIdentificador() {
        return mDados.getIndice();
    }

    public String getNome() {
        return mNome;
    }

    public long contagem() {
        return mDados.getItensUtilizadosContagem();
    }


    public long alocados() {
        return mDados.getItensAlocadosContagem();
    }

    public void zerar() {
        mIndice.zerar();
        mSequenciador.zerar();
        mDados.zerar();
    }

    public Lista<Entidade> getItens() {

        Lista<Entidade> lista = new Lista<Entidade>();

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            e_item.at("@PTR", item.getPonteiroDados());

            e_item.tornar_primeiro("@PTR");
            e_item.tornar_primeiro("@ID");

            lista.adicionar(e_item);
        }

        return lista;
    }

    public Lista<Entidade> getItensAlguns(int quantidade) {

        Lista<Entidade> lista = new Lista<Entidade>();

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            e_item.at("@PTR", item.getPonteiroDados());

            e_item.tornar_primeiro("@PTR");
            e_item.tornar_primeiro("@ID");

            lista.adicionar(e_item);

            if (lista.getQuantidade() >= quantidade) {
                break;
            }
        }

        return lista;
    }

    public Lista<ItemColecionavel> getItensEditaveis() {
        Lista<ItemColecionavel> lista = new Lista<ItemColecionavel>();

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            e_item.at("@PTR", item.getPonteiroDados());

            e_item.tornar_primeiro("@PTR");
            e_item.tornar_primeiro("@ID");

            lista.adicionar(new ItemColecionavel(mIndice, item, e_item));
        }

        return lista;
    }

    public long adicionar(Entidade e) {

        long proximo = mSequenciador.getProximo();

        e.at("@ID", proximo);


        ItemAlocado item = mDados.item_adicionar(ENTT.TO_DOCUMENTO(e));

        //  fmt.print("@ID :: {}",e.at("@ID"));

        // fmt.print("Ponteiro Dados :: {}",item.getPonteiroDados());

        mIndice.setItem(proximo, item.getPonteiroDados());

        return proximo;
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
        ENTT.EXIBIR_TABELA_COM_NOME(getItens(), "COLEÇÃO :: " + mDados.getNome());
    }

    public void exibir_indice() {
        ENTT.EXIBIR_TABELA_COM_NOME(getIndices(), "INDICE :: " + mDados.getNome());
    }

    public Opcional<IndiceLocalizado> procurar_indice(long indice) {
        return mIndice.procurar_indice(indice);
    }

    public Opcional<IndiceLocalizado> getIndiceMaior() {
        return mIndice.getIndiceMaior();
    }

    public Opcional<ItemColecionavel> procurar_item_atualizavel_por_indice(long indice) {

        Opcional<IndiceLocalizado> op_indice = procurar_indice(indice);
        if (op_indice.isOK()) {

            String direto_texto = ItemAcessarDireto.LER(mDados.getArquivador(), op_indice.get().getPonteiro());


            Entidade e_item = ENTT.PARSER_ENTIDADE(direto_texto);

            e_item.at("@PTR", op_indice.get().getPonteiro());

            e_item.tornar_primeiro("@PTR");
            e_item.tornar_primeiro("@ID");

            // return Opcional.OK(new ItemColecionavel(mIndice, item, e_item));

        }

        return Opcional.CANCEL();
    }

    public Opcional<Entidade> procurar_item_por_indice(long indice) {

        Opcional<IndiceLocalizado> op_indice = procurar_indice(indice);

        if (op_indice.isOK()) {

            String direto_texto = ItemAcessarDireto.LER(mDados.getArquivador(), op_indice.get().getPonteiro());


            Entidade e_item = ENTT.PARSER_ENTIDADE(direto_texto);

            e_item.at("@PTR", op_indice.get().getPonteiro());

            e_item.tornar_primeiro("@PTR");
            e_item.tornar_primeiro("@ID");

            return Opcional.OK(e_item);

        }

        return Opcional.CANCEL();
    }


    public Lista<Entidade> getItensIntervalo(long minimo, long maximo) {

        Lista<Entidade> lista = new Lista<Entidade>();

        for (ItemAlocado item : mDados.getItensAlocadosIntervalo(minimo, maximo)) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            e_item.at("@PTR", item.getPonteiroDados());

            e_item.tornar_primeiro("@PTR");
            e_item.tornar_primeiro("@ID");

            lista.adicionar(e_item);
        }

        return lista;
    }

    public void exibir_colecao(long minimo, long maximo) {
        ENTT.EXIBIR_TABELA_COM_NOME(getItensIntervalo(minimo, maximo), "COLEÇÃO :: " + mDados.getNome());
    }

    public void adicionar_varios(Lista<Entidade> varios) {
        for (Entidade item : varios) {
            adicionar(item);
        }
    }

    public Opcional<ItemColecionavel> obter_opcional(String att_nome, String att_valor) {

        for (ItemColecionavel ic : getItensEditaveis()) {
            if (ic.get().is(att_nome, att_valor)) {
                return Opcional.OK(ic);
            }
        }

        return Opcional.CANCEL();
    }
}

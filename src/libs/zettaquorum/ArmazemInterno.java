package libs.zettaquorum;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.Armazem;
import libs.fazendario.Fazendario;
import libs.fazendario.ItemAlocado;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;

public class ArmazemInterno {

    private Fazendario mFazendario;
    private Armazem mArmazem;
    private String mNome;

    public ArmazemInterno(Fazendario eFazendario, String eNome) {

        mFazendario = eFazendario;
        mNome = eNome;

        if (!mFazendario.existe_armazem(mNome)) {
            mFazendario.alocar_armazem(mNome);
        }

        mArmazem = Opcional.SOME(mFazendario.procurar_armazem(mNome));

    }

    public Lista<ItemAlocado> getItensAlocados() {
        return mArmazem.getItensAlocados();
    }

    public Lista<Entidade> getItens() {

        Lista<Entidade> lista = new Lista<Entidade>();

        for (ItemAlocado item : mArmazem.getItensAlocados()) {
            lista.adicionar(ENTT.PARSER_ENTIDADE(item.lerTextoUTF8()));
        }

        return lista;
    }


    public void adicionar(Entidade item) {
        mArmazem.item_adicionar(ENTT.TO_DOCUMENTO(item));
    }

    public Opcional<Entidade> procurar_unico(String att_nome, String att_valor) {

        for (Entidade e_item : getItens()) {
            if (e_item.is(att_nome, att_valor)) {
                return Opcional.OK(e_item);
            }
        }

        return Opcional.CANCEL();
    }

    public Opcional<Par<ItemAlocado, Entidade>> procurar_unico_atualizavel(String att_nome, String att_valor) {

        for (ItemAlocado item : mArmazem.getItensAlocados()) {
            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (e_item.is(att_nome, att_valor)) {
                return Opcional.OK(new Par<>(item, e_item));
            }
        }

        return Opcional.CANCEL();
    }


    public void zerar() {
        mArmazem.zerar();
    }

    public void dump() {
        ENTT.EXIBIR_TABELA_COM_NOME(getItens(), "ARMAZEM -- " + mNome);
    }

    public void dump_plantacoes() {
        Lista<Entidade> dump_plantacoes = mArmazem.getDumpPlantacoes();

        ENTT.EXIBIR_TABELA_COM_NOME(dump_plantacoes, "PLANTACOES -- "+mNome);

    }

}

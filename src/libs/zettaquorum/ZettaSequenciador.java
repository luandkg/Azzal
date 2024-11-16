package libs.zettaquorum;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.ItemAlocado;
import libs.tronarko.Tronarko;

public class ZettaSequenciador {

    private ItemAlocado mItemAlocado;
    private Entidade mEntidade;

    public ZettaSequenciador(ItemAlocado eItemAlocado, Entidade eEntidade) {
        mItemAlocado = eItemAlocado;
        mEntidade = eEntidade;
    }

    public long getProximo() {

        long corrente = mEntidade.atLong("Corrente");

        mEntidade.at("Corrente", corrente + 1);
        mEntidade.at("DDA", Tronarko.getTronAgora().getTextoZerado());

        mItemAlocado.atualizarUTF8(ENTT.TO_DOCUMENTO(mEntidade));

        return corrente;
    }

}

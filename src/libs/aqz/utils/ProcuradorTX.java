package libs.aqz.utils;

import libs.armazenador.ParticaoMestre;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;

public class ProcuradorTX {

    public static Opcional<Par<ItemDoBancoTX, Entidade>> procurar(ParticaoMestre particao, String att_nome, String att_valor) {

        Entidade ref_entidade = new Entidade();
        ItemDoBancoTX ref_item = null;
        boolean existe = false;

        for (ItemDoBancoTX item : particao.getItensTX()) {
            Entidade item_dkg = ENTT.PARSER_ENTIDADE(item.lerTextoTX());
            if (item_dkg.is(att_nome, att_valor)) {
                ref_item = item;
                ref_entidade = item_dkg;
                existe = true;
                break;
            }
        }

        if (existe) {
            return Opcional.OK(new Par<ItemDoBancoTX, Entidade>(ref_item, ref_entidade));
        } else {
            return Opcional.CANCEL();
        }

    }


    public static Lista<Entidade> listar_dados(ParticaoMestre particao) {

        Lista<Entidade> dados = new Lista<Entidade>();

        for (ItemDoBancoTX item : particao.getItensTX()) {
            dados.adicionar(ENTT.PARSER_ENTIDADE(item.lerTextoTX()));
        }

        return dados;
    }


}

package libs.dg;

import libs.Luan.Opcional;
import libs.tronarko.Tronarko;

public class Features {

    // FEATURES

    public static Opcional<DGItem> PROCURAR(DGColecao eColecao, String eAtributoNome, String eAtributoValor) {

        Opcional<DGItem> ret = new Opcional<DGItem>();

        for (DGItem item : eColecao.getItens()) {
            if (item.to().id_valor(eAtributoNome).contentEquals(eAtributoValor)) {
                ret.set(item);
                break;
            }
        }

        return ret;
    }

    public static Opcional<DGItem> PROCURAR(DG eDG, String eColecao, String eAtributoNome, int eAtributoValor) {

        Opcional<DGItem> ret = new Opcional<DGItem>();

        for (DGItem item : eDG.colecao(eColecao).getItens()) {
            if (item.to().id_int(eAtributoNome) == eAtributoValor) {
                ret.set(item);
                break;
            }
        }

        return ret;
    }

    public static Opcional<DGItem> PROCURAR(DGColecao eColecao, String eAtributoNome, int eAtributoValor) {

        Opcional<DGItem> ret = new Opcional<DGItem>();

        for (DGItem item : eColecao.getItens()) {
            if (item.to().id_int(eAtributoNome) == eAtributoValor) {
                ret.set(item);
                break;
            }
        }

        return ret;
    }

    public static Opcional<DGItem> PROCURAR_ULTIMO(DG eDG, String eColecao, String eAtributoNome) {

        Opcional<DGItem> ret = new Opcional<DGItem>();

        int ultimo = eDG.ultimoIndice(eColecao);

        for (DGItem item : eDG.colecao(eColecao).getItens()) {
            if (item.to().id_int(eAtributoNome) == ultimo) {
                ret.set(item);
                break;
            }
        }

        return ret;
    }

    public static int INDEX(DG eDG, String eID) {

        int v = 0;

        Opcional<DGItem> item_indexado = PROCURAR(eDG.colecao("INDEXADORES"), "NOME", eID);

        if (item_indexado.isOK()) {

            DGItem item = item_indexado.get();

            v = item.to().identifique("VALOR").getInteiro(0) + 1;

            item.to().identifique("VALOR").setInteiro(v);
            item.to().identifique("DDM").setValor(Tronarko.getAgora());

            item.commit();

        } else {

            DGObjeto novo = new DGObjeto();

            novo.identifique("NOME").setValor(eID);
            novo.identifique("VALOR").getInteiro(0);
            novo.identifique("DDC").setValor(Tronarko.getAgora());

            eDG.colecao("INDEXADORES").adicionar(novo);

        }

        return v;
    }

    public static int INDEX_ANTERIOR(DG eDG, String eID) {

        int v = -1;

        Opcional<DGItem> item_indexado = PROCURAR(eDG.colecao("INDEXADORES"), "NOME", eID);

        if (item_indexado.isOK()) {

            DGItem item = item_indexado.get();

            v = item.to().identifique("VALOR").getInteiro(0);

        }

        return v;
    }

}

package libs.dkg;

import libs.luan.Lista;

public class DKGColecao {

    // AUTOR : LUAN FREITAS
    // DATA : 2023 03 25


    private Lista<DKGGrupo> mGrupos;
    private Lista<DKGObjeto> mObjetos;

    public DKGColecao(Lista<DKGObjeto> eObjetos, String atributo_nome) {

        mGrupos = new Lista<DKGGrupo>();
        mObjetos = eObjetos;

        for (DKGObjeto objeto : mObjetos) {

            DKGGrupo grupo = getGrupo(objeto.identifique(atributo_nome).getValor());
            grupo.adicionar(objeto);

        }
    }

    public Lista<DKGObjeto> getObjetos() {
        return mObjetos;
    }

    public Lista<DKGGrupo> getGrupos() {
        return mGrupos;
    }

    public int getQuantidade() {
        return mObjetos.getQuantidade();
    }

    public DKGGrupo getGrupo(String eNome) {
        DKGGrupo ret = null;
        boolean enc = false;

        for (DKGGrupo grupo : mGrupos) {
            if (grupo.getNome().contentEquals(eNome)) {
                ret = grupo;
                enc = true;
                break;
            }
        }

        if (!enc) {
            ret = new DKGGrupo(eNome);
            mGrupos.adicionar(ret);
        }

        return ret;

    }

}
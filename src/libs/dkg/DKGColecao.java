package libs.dkg;

import java.util.ArrayList;

public class DKGColecao {

    // AUTOR : LUAN FREITAS
    // DATA : 2023 03 25


    private ArrayList<DKGGrupo> mGrupos;
    private ArrayList<DKGObjeto> mObjetos;

    public DKGColecao(ArrayList<DKGObjeto> eObjetos, String atributo_nome) {

        mGrupos = new ArrayList<DKGGrupo>();
        mObjetos = eObjetos;

        for (DKGObjeto objeto : mObjetos) {

            DKGGrupo grupo = getGrupo(objeto.identifique(atributo_nome).getValor());
            grupo.adicionar(objeto);

        }
    }

    public ArrayList<DKGObjeto> getObjetos() {
        return mObjetos;
    }

    public ArrayList<DKGGrupo> getGrupos() {
        return mGrupos;
    }

    public int getQuantidade() {
        return mObjetos.size();
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
            mGrupos.add(ret);
        }

        return ret;

    }

}
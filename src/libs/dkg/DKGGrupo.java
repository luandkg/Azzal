package libs.dkg;

import java.util.ArrayList;

public class DKGGrupo {

    private String mNome;
    private ArrayList<DKGObjeto> mObjetos;

    public DKGGrupo(String eNome) {
        mNome = eNome;
        mObjetos = new ArrayList<DKGObjeto>();
    }


    public String getNome() {
        return mNome;
    }

    public void adicionar(DKGObjeto eObjeto) {
        mObjetos.add(eObjeto);
    }

    public ArrayList<DKGObjeto> getObjetos() {
        return mObjetos;
    }


    public DKGObjeto get(String atributo_nome, String atributo_valor) {
        DKGObjeto ret = null;

        for (DKGObjeto objeto : mObjetos) {
            if (objeto.identifique(atributo_nome).isValor(atributo_valor)) {
                ret = objeto;
                break;
            }
        }

        return ret;
    }

    public DKGObjeto get(String atributo_nome, int atributo_valor) {
        DKGObjeto ret = null;

        for (DKGObjeto objeto : mObjetos) {
            if (objeto.identifique(atributo_nome).isValor(atributo_valor)) {
                ret = objeto;
                break;
            }
        }

        return ret;
    }

}

package libs.dkg;

import libs.luan.Lista;

public class DKGGrupo {

    private String mNome;
    private Lista<DKGObjeto> mObjetos;

    public DKGGrupo(String eNome) {
        mNome = eNome;
        mObjetos = new Lista<DKGObjeto>();
    }


    public String getNome() {
        return mNome;
    }

    public void adicionar(DKGObjeto eObjeto) {
        mObjetos.adicionar(eObjeto);
    }

    public Lista<DKGObjeto> getObjetos() {
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

package libs.dkg;

import java.util.ArrayList;

public class DKGObjeto {

    private String mNome;
    private ArrayList<DKGAtributo> mDKGAtributos;
    private ArrayList<DKGObjeto> mDKGObjetos;

    public DKGObjeto(String eNome) {
        mNome = eNome;

        mDKGAtributos = new ArrayList<DKGAtributo>();
        mDKGObjetos = new ArrayList<DKGObjeto>();

    }

    public void setNome(String eNome) {
        mNome = eNome;
    }

    public String getNome() {
        return mNome;
    }

    public int contagem() {

        int t = 1;


        for (DKGAtributo ePacote : mDKGAtributos) {
            t += 2;
        }


        for (DKGObjeto mDKGObjeto : mDKGObjetos) {
            t += mDKGObjeto.contagem();
        }


        return t;

    }

    // ATRIBUTOS

    public DKGAtributo identifique(String eNome) {

        boolean enc = false;
        DKGAtributo ret = null;

        for (DKGAtributo mIdentificador : mDKGAtributos) {

            if (mIdentificador.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mIdentificador;
                break;
            }

        }

        if (enc == false) {
            ret = new DKGAtributo(eNome, "");
            mDKGAtributos.add(ret);
        }

        return ret;

    }

    public boolean existeIdentificador(String eNome) {

        boolean enc = false;

        for (DKGAtributo mIdentificador : mDKGAtributos) {

            if (mIdentificador.getNome().contentEquals(eNome)) {
                enc = true;

                break;
            }

        }

        return enc;
    }


    public DKGAtributo identifique(String eNome, short eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DKGAtributo identifique(String eNome, int eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DKGAtributo identifique(String eNome, float eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DKGAtributo identifique(String eNome, double eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DKGAtributo identifique(String eNome, boolean eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DKGAtributo identifique(String eNome, String eValor) {

        boolean enc = false;
        DKGAtributo ret = null;

        for (DKGAtributo mIdentificador : mDKGAtributos) {

            if (mIdentificador.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mIdentificador;
                ret.setValor(eValor);
                break;
            }

        }

        if (enc == false) {
            ret = new DKGAtributo(eNome, eValor);
            mDKGAtributos.add(ret);
        }

        return ret;
    }

    public ArrayList<DKGAtributo> getAtributos() {
        return mDKGAtributos;
    }


    // OBJETO

    public ArrayList<DKGObjeto> getObjetos() {
        return mDKGObjetos;
    }

    public DKGObjeto criarObjeto(String eNome) {

        DKGObjeto ret = new DKGObjeto(eNome);
        mDKGObjetos.add(ret);

        return ret;
    }

    public DKGObjeto criarObjeto(String eNome, String a1, String v1) {

        DKGObjeto ret = new DKGObjeto(eNome);
        mDKGObjetos.add(ret);

        ret.identifique(a1, v1);

        return ret;
    }

    public DKGObjeto criarObjeto(String eNome, String a1, String v1, String a2, String v2) {

        DKGObjeto ret = new DKGObjeto(eNome);
        mDKGObjetos.add(ret);

        ret.identifique(a1, v1);
        ret.identifique(a2, v2);

        return ret;
    }

    public DKGObjeto criarObjeto(String eNome, String a1, int v1, String a2, int v2) {

        DKGObjeto ret = new DKGObjeto(eNome);
        mDKGObjetos.add(ret);

        ret.identifique(a1, v1);
        ret.identifique(a2, v2);

        return ret;
    }

    public DKGObjeto criarObjeto(String eNome, String a1, double v1, String a2, double v2) {

        DKGObjeto ret = new DKGObjeto(eNome);
        mDKGObjetos.add(ret);

        ret.identifique(a1, v1);
        ret.identifique(a2, v2);

        return ret;
    }


    public DKGObjeto unicoObjeto(String eNome) {

        boolean enc = false;
        DKGObjeto ret = null;

        for (DKGObjeto mDKGObjeto : mDKGObjetos) {

            if (mDKGObjeto.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mDKGObjeto;
                break;
            }

        }

        if (enc == false) {
            ret = new DKGObjeto(eNome);
            mDKGObjetos.add(ret);
        }

        return ret;
    }

    public void removerObjeto(DKGObjeto eDKGObjeto) {

        for (DKGObjeto mDKGObjeto : mDKGObjetos) {

            if (mDKGObjeto == eDKGObjeto) {
                mDKGObjetos.remove(eDKGObjeto);
                break;
            }

        }

    }

    public void removerObjetoPorNome(String eNome) {

        for (DKGObjeto mDKGObjeto : mDKGObjetos) {

            if (mDKGObjeto.getNome().contentEquals(eNome)) {
                mDKGObjetos.remove(mDKGObjeto);
                break;
            }

        }

    }


    public void paraCadaObjeto(CadaObjeto eCadaObjeto) {

        for (DKGObjeto objeto : getObjetos()) {

            eCadaObjeto.dentro(objeto);

            if (eCadaObjeto.foiCancelado()) {
                break;
            }

        }

    }


    // FACILITADORES DE IDENTIFICADORES

    public boolean id_existe(String eNome){
        return existeIdentificador(eNome);
    }

    public String id_valor(String eNome){
        return identifique(eNome).getValor();
    }

    public int id_int(String eNome){
        return identifique(eNome).getInteiro();
    }

    public boolean id_bool(String eNome){
        return identifique(eNome).getBool();
    }

    public float id_float(String eNome){
        return identifique(eNome).getFloat();
    }

    public double id_double(String eNome){
        return identifique(eNome).getDouble();
    }

    public long id_long(String eNome){
        return identifique(eNome).getLong();
    }

}

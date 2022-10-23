package libs.dkg;

import libs.Luan.Opcional;

import java.util.ArrayList;

public class DKGObjeto {

    private String mNome;
    private ArrayList<DKGAtributo> mAtributos;
    private ArrayList<DKGObjeto> mObjetos;

    public DKGObjeto(String eNome) {
        mNome = eNome;

        mAtributos = new ArrayList<DKGAtributo>();
        mObjetos = new ArrayList<DKGObjeto>();

    }

    public void setNome(String eNome) {
        mNome = eNome;
    }

    public String getNome() {
        return mNome;
    }

    public int contagem() {

        int t = 1;


        for (DKGAtributo ePacote : mAtributos) {
            t += 2;
        }


        for (DKGObjeto mDKGObjeto : mObjetos) {
            t += mDKGObjeto.contagem();
        }


        return t;

    }

    // ATRIBUTOS

    public DKGAtributo identifique(String eNome) {

        boolean enc = false;
        DKGAtributo ret = null;

        for (DKGAtributo mIdentificador : mAtributos) {

            if (mIdentificador.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mIdentificador;
                break;
            }

        }

        if (enc == false) {
            ret = new DKGAtributo(eNome, "");
            mAtributos.add(ret);
        }

        return ret;

    }

    public boolean existeIdentificador(String eNome) {

        boolean enc = false;

        for (DKGAtributo mIdentificador : mAtributos) {

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

        for (DKGAtributo mIdentificador : mAtributos) {

            if (mIdentificador.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mIdentificador;
                ret.setValor(eValor);
                break;
            }

        }

        if (enc == false) {
            ret = new DKGAtributo(eNome, eValor);
            mAtributos.add(ret);
        }

        return ret;
    }

    public ArrayList<DKGAtributo> getAtributos() {
        return mAtributos;
    }


    // OBJETO

    public ArrayList<DKGObjeto> getObjetos() {
        return mObjetos;
    }

    public DKGObjeto criarObjeto(String eNome) {

        DKGObjeto ret = new DKGObjeto(eNome);
        mObjetos.add(ret);

        return ret;
    }

    public DKGObjeto criarObjeto(String eNome, String a1, String v1) {

        DKGObjeto ret = new DKGObjeto(eNome);
        mObjetos.add(ret);

        ret.identifique(a1, v1);

        return ret;
    }

    public DKGObjeto criarObjeto(String eNome, String a1, String v1, String a2, String v2) {

        DKGObjeto ret = new DKGObjeto(eNome);
        mObjetos.add(ret);

        ret.identifique(a1, v1);
        ret.identifique(a2, v2);

        return ret;
    }

    public DKGObjeto criarObjeto(String eNome, String a1, int v1, String a2, int v2) {

        DKGObjeto ret = new DKGObjeto(eNome);
        mObjetos.add(ret);

        ret.identifique(a1, v1);
        ret.identifique(a2, v2);

        return ret;
    }

    public DKGObjeto criarObjeto(String eNome, String a1, double v1, String a2, double v2) {

        DKGObjeto ret = new DKGObjeto(eNome);
        mObjetos.add(ret);

        ret.identifique(a1, v1);
        ret.identifique(a2, v2);

        return ret;
    }


    public DKGObjeto unicoObjeto(String eNome) {

        boolean enc = false;
        DKGObjeto ret = null;

        for (DKGObjeto mDKGObjeto : mObjetos) {

            if (mDKGObjeto.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mDKGObjeto;
                break;
            }

        }

        if (enc == false) {
            ret = new DKGObjeto(eNome);
            mObjetos.add(ret);
        }

        return ret;
    }

    public void removerObjeto(DKGObjeto eDKGObjeto) {

        for (DKGObjeto mDKGObjeto : mObjetos) {

            if (mDKGObjeto == eDKGObjeto) {
                mObjetos.remove(eDKGObjeto);
                break;
            }

        }

    }

    public void removerObjetoPorNome(String eNome) {

        for (DKGObjeto mDKGObjeto : mObjetos) {

            if (mDKGObjeto.getNome().contentEquals(eNome)) {
                mObjetos.remove(mDKGObjeto);
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

    public boolean id_existe(String eNome) {
        return existeIdentificador(eNome);
    }

    public String id_valor(String eNome) {
        return identifique(eNome).getValor();
    }

    public int id_int(String eNome) {
        return identifique(eNome).getInteiro();
    }

    public boolean id_bool(String eNome) {
        return identifique(eNome).getBool();
    }

    public float id_float(String eNome) {
        return identifique(eNome).getFloat();
    }

    public double id_double(String eNome) {
        return identifique(eNome).getDouble();
    }

    public long id_long(String eNome) {
        return identifique(eNome).getLong();
    }


    public void limpar() {

        getAtributos().clear();
        getObjetos().clear();

    }

    public String toDocumento() {

        DKG escritor = new DKG();
        escritor.getObjetos().add(this);

        return escritor.toDocumento();
    }

    // FEATURE 2022.07

    public Opcional<DKGObjeto> procurar(String atributo_nome, String atributo_valor) {

        for (DKGObjeto objeto : mObjetos) {
            if (objeto.identifique(atributo_nome).isValor(atributo_valor)) {
                return Opcional.OK(objeto);
            }
        }

        return Opcional.CANCEL();
    }




    // FEATURE 2022.08

    public void adicionarObjetosDe(DKGObjeto eEmissor) {

        for (DKGObjeto passando_objeto : eEmissor.getObjetos()) {
            mObjetos.add(passando_objeto);
        }

    }

    // FEATURE 2022.09

    public void adicionarAtributosDe(DKGObjeto eEmissor) {

        for (DKGAtributo copiando_atributo : eEmissor.getAtributos()) {

            boolean existe = false;
            for (DKGAtributo passando_atributo : getAtributos()) {
                if (passando_atributo.getNome().contentEquals(copiando_atributo.getNome())) {
                    passando_atributo.setValor(copiando_atributo.getValor());
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                DKGAtributo atributo = new DKGAtributo(copiando_atributo.getNome(), copiando_atributo.getValor());
                mAtributos.add(atributo);
            }
        }

    }


    // FEATURE 22.09

    public ArrayList<String> toListaDeString(String atributo) {

        ArrayList<String> ls = new ArrayList<String>();

        for (DKGObjeto objeto : getObjetos()) {
            ls.add(objeto.identifique(atributo).getValor());
        }

        return ls;
    }

    // FEATURE 22.09
    public DKGObjeto unicamente(String ePrimeiro, String eSegundo) {
        return unicoObjeto(ePrimeiro).unicoObjeto(eSegundo);
    }

    // FEATURE 22.09
    public DKGObjeto unicamente(String ePrimeiro, String eSegundo, String eTerceiro) {
        return unicoObjeto(ePrimeiro).unicoObjeto(eSegundo).unicoObjeto(eTerceiro);
    }


    // FEATURE 22.09

    public int inteiro_mais(String eAtributo, int v) {
        int valor = identifique(eAtributo).getInteiro(0) + v;
        identifique(eAtributo).setInteiro(valor);
        return valor;
    }

    public int inteiro_mais(DKGAtributo eAtributo, int v) {
        int valor = eAtributo.getInteiro(0) + v;
        eAtributo.setInteiro(valor);
        return valor;
    }

    public int inteiro_menos(String eAtributo, int v) {
        int valor = identifique(eAtributo).getInteiro(0) - v;
        identifique(eAtributo).setInteiro(valor);
        return valor;
    }

    public int inteiro_menos(DKGAtributo eAtributo, int v) {
        int valor = eAtributo.getInteiro(0) - v;
        eAtributo.setInteiro(valor);
        return valor;
    }


    // FEATURE 22.10
    public ArrayList<DKGObjetoOuAtributo> getTodos(){

        ArrayList<DKGObjetoOuAtributo> lista = new ArrayList<DKGObjetoOuAtributo>();

        for (DKGAtributo att : getAtributos()) {
            lista.add(new DKGObjetoOuAtributo(att));
        }
        for (DKGObjeto obj : getObjetos()) {
            lista.add(new DKGObjetoOuAtributo(obj));
        }

        return lista;
    }

}

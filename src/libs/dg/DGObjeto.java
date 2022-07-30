package libs.dg;

import libs.dkg.IO.EscritorDKG;
import libs.dkg.IO.ParserDKG;

import java.util.ArrayList;

public class DGObjeto {

    private ArrayList<DGAtributo> mDKGAtributos;

    private int mDeveConstruirChave = 0;
    private String mNomeChave = "";

    public DGObjeto() {

        mDKGAtributos = new ArrayList<DGAtributo>();

    }

    public DGObjeto(int eConstruirChave, String eNomeChave) {

        mDKGAtributos = new ArrayList<DGAtributo>();

        mDeveConstruirChave = eConstruirChave;
        mNomeChave = eNomeChave;

    }

    public boolean deveConstruirChave() {
        return mDeveConstruirChave == DG.CHAVE_UNICA;
    }

    public String getIndice() {
        return mNomeChave;
    }

    public int getTamanho() {
        return toString().length();
    }

    public void parser(String eTexto) {
        IOParser parser = new IOParser();
        parser.parse(eTexto, this);
    }

    public String toString() {

        IOParser escritor = new IOParser();

        escritor.montar("", this);

        return escritor.getTexto();
    }

    public int contagem() {

        int t = 1;

        for (DGAtributo ePacote : mDKGAtributos) {
            t += 2;
        }

        return t;

    }

    // ATRIBUTOS

    public DGAtributo identifique_comeco(String eNome) {

        boolean enc = false;
        DGAtributo ret = null;

        for (DGAtributo mIdentificador : mDKGAtributos) {

            if (mIdentificador.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mIdentificador;
                break;
            }

        }

        if (enc == false) {
            ret = new DGAtributo(eNome, "");
            mDKGAtributos.add(0, ret);
        }

        return ret;

    }

    public DGAtributo identifique(String eNome) {

        boolean enc = false;
        DGAtributo ret = null;

        for (DGAtributo mIdentificador : mDKGAtributos) {

            if (mIdentificador.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mIdentificador;
                break;
            }

        }

        if (enc == false) {
            ret = new DGAtributo(eNome, "");
            mDKGAtributos.add(ret);
        }

        return ret;

    }

    public boolean existeIdentificador(String eNome) {

        boolean enc = false;

        for (DGAtributo mIdentificador : mDKGAtributos) {

            if (mIdentificador.getNome().contentEquals(eNome)) {
                enc = true;

                break;
            }

        }

        return enc;
    }

    public DGAtributo identifique(String eNome, short eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DGAtributo identifique(String eNome, int eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DGAtributo identifique(String eNome, float eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DGAtributo identifique(String eNome, double eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DGAtributo identifique(String eNome, boolean eValor) {
        return this.identifique(eNome, String.valueOf(eValor));
    }

    public DGAtributo identifique(String eNome, String eValor) {

        boolean enc = false;
        DGAtributo ret = null;

        for (DGAtributo mIdentificador : mDKGAtributos) {

            if (mIdentificador.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mIdentificador;
                ret.setValor(eValor);
                break;
            }

        }

        if (enc == false) {
            ret = new DGAtributo(eNome, eValor);
            mDKGAtributos.add(ret);
        }

        return ret;
    }

    public ArrayList<DGAtributo> getAtributos() {
        return mDKGAtributos;
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

}

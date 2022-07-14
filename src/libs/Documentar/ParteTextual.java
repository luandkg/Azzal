package libs.Documentar;

import java.util.ArrayList;

public class ParteTextual {

    private String mTipo;
    private String mConteudo;
    private ArrayList<String> mAtributos;

    public ParteTextual(String eTipo) {
        mTipo = eTipo;
        mConteudo = "";
        mAtributos = new ArrayList<String>();
    }

    public String getTipo() {
        return mTipo;
    }

    public String getConteudo() {
        return mConteudo;
    }

    public void setTipo(String eTipo) {
        mTipo = eTipo;
    }

    public void setConteudo(String eConteudo) {
        mConteudo = eConteudo;
    }

    public ArrayList<String> getAtributos() {
        return mAtributos;
    }

    public void atribuir(String atributo) {
        mAtributos.add(atributo);
    }

    public boolean eDoTipo(String qualTipo) {
        return mTipo.toUpperCase().contentEquals(qualTipo.toUpperCase());
    }
}

package libs.arquivos;

import libs.dkg.DKG;

import java.io.File;

public class PreferenciasOrganizadas {

    private String mArquivo;
    private DKG eDKG;

    public PreferenciasOrganizadas(String eArquivo) {
        mArquivo = eArquivo;
        eDKG = new DKG();
    }

    public void abrir() {
        eDKG = new DKG();
        eDKG.abrir(mArquivo);
    }

    public void abrirSeExistir() {
        if (new File(mArquivo).exists()) {
            abrir();
        }
    }

    public void salvar() {
        eDKG.salvar(mArquivo);
    }


    public String getOpcao(String eSecao, String eAtributo) {
        String v = eDKG.unicoObjeto("Preferencias").unicoObjeto(eSecao).identifique(eAtributo).getValor();
        return v;
    }

    public void setOpcao(String eSecao, String eAtributo, String eValor) {
        eDKG.unicoObjeto("Preferencias").unicoObjeto(eSecao).identifique(eAtributo).setValor(eValor);
    }

    public boolean getLogico(String eSecao, String eAtributo) {

        String v = eDKG.unicoObjeto("Preferencias").unicoObjeto(eSecao).identifique(eAtributo).getValor();

        if (v.contentEquals("SIM")) {
            return true;
        } else {
            return false;
        }
    }


    public void setLogico(String eSecao, String eAtributo, boolean eValor) {

        if (eValor) {
            eDKG.unicoObjeto("Preferencias").unicoObjeto(eSecao).identifique(eAtributo).setValor("SIM");
        } else {
            eDKG.unicoObjeto("Preferencias").unicoObjeto(eSecao).identifique(eAtributo).setValor("NAO");
        }

    }
}

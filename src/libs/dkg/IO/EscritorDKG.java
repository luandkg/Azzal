package libs.dkg.IO;


import libs.dkg.DKGAtributo;
import libs.dkg.DKGObjeto;

import java.util.ArrayList;

public class EscritorDKG {

    private String mTexto;

    public EscritorDKG() {
        mTexto = "";
    }

    public void adicionarLinha(String eLinha) {
        mTexto += eLinha + "\n";
    }

    public void adicionar(String eMais) {
        mTexto += eMais;
    }

    public String toString() {
        return mTexto;
    }

    public String getTexto() {
        return mTexto;
    }


    public void montar(String ePrefixo, ArrayList<DKGObjeto> lsPacotes) {

        for (DKGObjeto PacoteC : lsPacotes) {

            if (PacoteC.getObjetos().size() == 0 && PacoteC.getAtributos().size() == 0) {

                adicionarLinha(ePrefixo + "!" + Textum.codifica(PacoteC.getNome()) + " :: { } ");

            } else if (PacoteC.getObjetos().size() == 0 && PacoteC.getAtributos().size() > 0) {

                String eIdentificadores = "";

                for (DKGAtributo IdentificadorC : PacoteC.getAtributos()) {
                    eIdentificadores += " @" + Textum.codifica(IdentificadorC.getNome()) + " = " + "\"" + Textum.codifica(IdentificadorC.getValor()) + "\"";
                }

                adicionarLinha(ePrefixo + "!" + Textum.codifica(PacoteC.getNome()) + " :: { " + eIdentificadores + " } ");

            } else if (PacoteC.getObjetos().size() > 0) {

                adicionarLinha(ePrefixo + "!" + Textum.codifica(PacoteC.getNome()) + " :: { ");

                for (DKGAtributo IdentificadorC : PacoteC.getAtributos()) {
                    adicionarLinha(ePrefixo+"  @" + Textum.codifica(IdentificadorC.getNome()) + " = " + "\"" + Textum.codifica(IdentificadorC.getValor()) + "\"");
                }

                montar(ePrefixo + "  ", PacoteC.getObjetos());

                adicionarLinha(ePrefixo+ "}");

            }


        }

    }
}


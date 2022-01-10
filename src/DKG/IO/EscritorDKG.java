package DKG.IO;


import DKG.DKGAtributo;
import DKG.DKGObjeto;

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
                    adicionarLinha("  @" + Textum.codifica(IdentificadorC.getNome()) + " = " + "\"" + Textum.codifica(IdentificadorC.getValor()) + "\"");
                }

                montar(ePrefixo + "  ", PacoteC.getObjetos());

                adicionarLinha(ePrefixo+ "}");

            }


        }

    }
}


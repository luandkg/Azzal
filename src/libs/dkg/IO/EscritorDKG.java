package libs.dkg.IO;


import libs.dkg.DKGAtributo;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;

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


    public void montar(String ePrefixo, Lista<DKGObjeto> lsPacotes) {

        for (DKGObjeto PacoteC : lsPacotes) {

            if (PacoteC.getObjetos().getQuantidade() == 0 && PacoteC.getAtributos().getQuantidade() == 0) {

                adicionarLinha(ePrefixo + "!" + Textum.codifica(PacoteC.getNome()) + " :: { } ");

            } else if (PacoteC.getObjetos().getQuantidade() == 0 && PacoteC.getAtributos().getQuantidade() > 0) {

                String eIdentificadores = "";

                for (DKGAtributo IdentificadorC : PacoteC.getAtributos()) {
                    eIdentificadores += " @" + Textum.codifica(IdentificadorC.getNome()) + " = " + "\"" + Textum.codifica(IdentificadorC.getValor()) + "\"";
                }

                adicionarLinha(ePrefixo + "!" + Textum.codifica(PacoteC.getNome()) + " :: { " + eIdentificadores + " } ");

            } else if (PacoteC.getObjetos().getQuantidade() > 0) {

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


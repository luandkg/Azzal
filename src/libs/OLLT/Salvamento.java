package libs.OLLT;

import java.util.ArrayList;

public class Salvamento {

    public String Codifica(String e) {
        //e = e.replace("@", "@A");
        //e = e.replace("'", "@S");
        //e = e.replace("\"", "@D");
        //e = e.replace("-", "@H");

        int i = 0;
        int o = e.length();
        String ret = "";

        while (i < o) {
            String l = e.charAt(i) + "";
            if (l.contentEquals("@")) {
                ret += "@A";
            } else if (l.contentEquals("'")) {
                ret += "@S";
            } else if (l.contentEquals("\"")) {
                ret += "@D";
            } else if (l.contentEquals("-")) {
                ret += "@H";
            } else {
                ret += l;
            }
            i += 1;
        }


        return ret;
    }

    private void Identificadores(TextoDocumento TextoDocumentoC, String ePrefixo, ArrayList<Identificador> mIdentificadores) {

        for (Identificador IdentificadorC : mIdentificadores) {

            TextoDocumentoC.AdicionarLinha(ePrefixo + "   " + "ID " + Codifica(IdentificadorC.getNome()) + " = " + "\""
                    + Codifica(IdentificadorC.getValor()) + "\"");

        }

    }

    private void Listas(TextoDocumento TextoDocumentoC, String ePrefixo, ArrayList<Lista> mListas) {

        for (Lista ListaC : mListas) {

            if (ListaC.getItens().size() == 0) {

                TextoDocumentoC.AdicionarLinha(ePrefixo + "   " + "LISTA " + Codifica(ListaC.getNome()) + " { } ");

            } else {

                String Itens = "";

                for (String eItem : ListaC.getItens()) {

                    Itens += eItem + " ";

                }

                TextoDocumentoC.AdicionarLinha(ePrefixo + "   " + "LISTA " + Codifica(ListaC.getNome()) + " { " + Itens + "} ");

            }

        }

    }

    private void Comentarios(TextoDocumento TextoDocumentoC, String ePrefixo, ArrayList<Comentario> mComentarios) {

        for (Comentario ComentarioC : mComentarios) {

            TextoDocumentoC.AdicionarLinha(ePrefixo + "   " + "-- " + Codifica(ComentarioC.getNome()) + " : " + "\""
                    + Codifica(ComentarioC.getValor()) + "\" --");

        }

    }

    public void Objetos(TextoDocumento TextoDocumentoC, String ePrefixo, ArrayList<Objeto> mObjetos) {

        for (Objeto ObjetoC : mObjetos) {

            // if (ObjetoC.getIdentificadores().size() == 0 && ObjetoC.getListas().size() ==
            // 0
            // && ObjetoC.getComentarios().size() == 0) {

            // ITextoC.AdicionarLinha(ePrefixo + " " + "OBJETO " + ObjetoC.getNome() + " { }
            // ");

            // } else {

            TextoDocumentoC.AdicionarLinha(ePrefixo + "   " + "OBJETO " + ObjetoC.getNome() + " { ");

            for (Identificador IdentificadorC : ObjetoC.getIdentificadores()) {

                TextoDocumentoC.AdicionarLinha(ePrefixo + "   " + "   " + "ID " + Codifica(IdentificadorC.getNome()) + " = " + "\""
                        + Codifica(IdentificadorC.getValor()) + "\"");

            }

            for (Lista ListaC : ObjetoC.getListas()) {

                // if (ListaC.getItens().size() == 0) {

                // ITextoC.AdicionarLinha(
                // ePrefixo + " " + " " + "LISTA " + Codifica(ListaC.getNome()) + " = { } ");

                // } else {

                String Itens = "";
                int o = ListaC.getItens().size();
                int i = 1;

                for (String eItem : ListaC.getItens()) {

                    if (i == o) {
                        Itens += eItem + " ";
                    } else {
                        Itens += eItem + " , ";
                    }
                    i += 1;
                }

                TextoDocumentoC.AdicionarLinha(
                        ePrefixo + "   " + "   " + "LISTA " + Codifica(ListaC.getNome()) + " = { " + Itens + "} ");

            }

            // }

            for (Comentario ComentarioC : ObjetoC.getComentarios()) {

                TextoDocumentoC.AdicionarLinha(ePrefixo + "   " + "   " + "-- " + Codifica(ComentarioC.getNome()) + " : \""
                        + Codifica(ComentarioC.getValor()) + "\" -- ");

            }

            TextoDocumentoC.AdicionarLinha(ePrefixo + "   " + "}");
        }

    }

    public void Vetores(TextoDocumento TextoDocumentoC, String ePrefixo, ArrayList<Vetor> mVetores) {


        for (Vetor ObjetoC : mVetores) {


            TextoDocumentoC.Adicionar( ePrefixo + "   " + "VETOR " + ObjetoC.getNome() + " { ");

            int i = 0;
            int o = ObjetoC.getValores().size() - 1;


            for (String IdentificadorC : ObjetoC.getValores()) {


                TextoDocumentoC.Adicionar("   " + "\"" + Codifica(IdentificadorC) + "\"");


            }


            TextoDocumentoC.AdicionarLinha( ePrefixo + "   " + "}");
        }

    }

    public void Matrizes(TextoDocumento TextoDocumentoC, String ePrefixo, ArrayList<Matriz> mMatrizes) {

        for (Matriz ObjetoC : mMatrizes) {


            TextoDocumentoC.AdicionarLinha(ePrefixo + "   " + "MATRIZ " + ObjetoC.getNome() + " { ");

            int i = 0;
            int o = ObjetoC.getValores().size() - 1;

            for (ArrayList<String> VetorValor : ObjetoC.getValores()) {

                TextoDocumentoC.Adicionar("" + ePrefixo + "   " + "   { ");

                for (String IdentificadorC : VetorValor) {
                    TextoDocumentoC.Adicionar("   " + "\"" + Codifica(IdentificadorC) + "\" ");
                }

                if (i < o) {
                    TextoDocumentoC.Adicionar("   }\n");
                } else {
                    TextoDocumentoC.Adicionar("   }");
                }
                i += 1;

            }


            TextoDocumentoC.AdicionarLinha("\n" + ePrefixo + "   " + "}");
        }

    }

    public void Pacote_Listar(TextoDocumento TextoDocumentoC, String ePrefixo, ArrayList<Pacote> lsPacotes) {

        for (Pacote PacoteC : lsPacotes) {

            if (PacoteC.getPacotes().size() == 0 && PacoteC.getListas().size() == 0
                    && PacoteC.getIdentificadores().size() == 0 && PacoteC.getObjetos().size() == 0 && PacoteC.getVetores().size() == 0 && PacoteC.getMatrizes().size() == 0) {

                TextoDocumentoC.AdicionarLinha(ePrefixo + "PACOTE " + Codifica(PacoteC.getNome()) + " { } ");

            } else if (PacoteC.getPacotes().size() == 0 && PacoteC.getListas().size() == 0
                    && PacoteC.getObjetos().size() == 0 && PacoteC.getVetores().size() == 0 && PacoteC.getMatrizes().size() == 0 && PacoteC.getLinear()) {


                String eLinha = "";

                for (Identificador IdentificadorC : PacoteC.getIdentificadores()) {

                    eLinha += "\t\t" + "ID " + Codifica(IdentificadorC.getNome()) + " = " + "\"" + Codifica(IdentificadorC.getValor()) + "\"";

                }


                TextoDocumentoC.AdicionarLinha(ePrefixo + "PACOTE " + Codifica(PacoteC.getNome()) + " { " + eLinha + " } ");

            } else {

                TextoDocumentoC.AdicionarLinha(ePrefixo + "PACOTE " + Codifica(PacoteC.getNome()));
                TextoDocumentoC.AdicionarLinha(ePrefixo + " {");

                Identificadores(TextoDocumentoC, ePrefixo, PacoteC.getIdentificadores());

                Listas(TextoDocumentoC, ePrefixo, PacoteC.getListas());

                Comentarios(TextoDocumentoC, ePrefixo, PacoteC.getComentarios());

                Objetos(TextoDocumentoC, ePrefixo, PacoteC.getObjetos());


                Vetores(TextoDocumentoC, ePrefixo, PacoteC.getVetores());

                Matrizes(TextoDocumentoC, ePrefixo, PacoteC.getMatrizes());


                Pacote_Listar(TextoDocumentoC, ePrefixo + "   ", PacoteC.getPacotes());


                TextoDocumentoC.AdicionarLinha(ePrefixo + " } ");
            }


        }

    }
}

package libs.ollt;

import java.util.ArrayList;

public class SalvamentoReduzido {

    public String Codifica(String e) {
        // e = e.replace("@", "@A");
        //  e = e.replace("'", "@S");
        //  e = e.replace("\"", "@D");
        //  e = e.replace("-", "@H");

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
        //  return e;
    }

    private void Identificadores(TextoDocumento TextoDocumentoC, ArrayList<Identificador> mIdentificadores) {

        for (Identificador IdentificadorC : mIdentificadores) {

            TextoDocumentoC.Adicionar(" ID " + Codifica(IdentificadorC.getNome()) + " = " + "\""
                    + Codifica(IdentificadorC.getValor()) + "\" ");

        }

    }

    private void Listas(TextoDocumento TextoDocumentoC, ArrayList<Lista> mListas) {

        for (Lista ListaC : mListas) {

            if (ListaC.getItens().size() == 0) {

                TextoDocumentoC.Adicionar(" LISTA " + Codifica(ListaC.getNome()) + " { } ");

            } else {

                String Itens = "";

                for (String eItem : ListaC.getItens()) {

                    Itens += eItem + " ";

                }

                TextoDocumentoC.Adicionar(" LISTA " + Codifica(ListaC.getNome()) + " { " + Itens + " } ");

            }

        }

    }

    private void Comentarios(TextoDocumento TextoDocumentoC, ArrayList<Comentario> mComentarios) {

        for (Comentario ComentarioC : mComentarios) {

            TextoDocumentoC.Adicionar(" -- " + Codifica(ComentarioC.getNome()) + " : " + "\""
                    + Codifica(ComentarioC.getValor()) + "\" --");

        }

    }

    public void Objetos(TextoDocumento TextoDocumentoC, ArrayList<Objeto> mObjetos) {

        for (Objeto ObjetoC : mObjetos) {


            TextoDocumentoC.Adicionar(" OBJETO " + ObjetoC.getNome() + " { ");

            for (Identificador IdentificadorC : ObjetoC.getIdentificadores()) {

                TextoDocumentoC.Adicionar(" ID " + IdentificadorC.getNome() + " = " + "\""
                        + IdentificadorC.getValor() + "\"");

            }

            for (Lista ListaC : ObjetoC.getListas()) {


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

                TextoDocumentoC.Adicionar(
                        " LISTA " + Codifica(ListaC.getNome()) + " = { " + Itens + "} ");

            }

            // }

            for (Comentario ComentarioC : ObjetoC.getComentarios()) {

                TextoDocumentoC.Adicionar("-- " + Codifica(ComentarioC.getNome()) + " : \""
                        + Codifica(ComentarioC.getValor()) + "\" -- ");

            }

            TextoDocumentoC.Adicionar(" } ");
        }

    }

    public void Vetores(TextoDocumento TextoDocumentoC, ArrayList<Vetor> mVetores) {


        for (Vetor ObjetoC : mVetores) {


            TextoDocumentoC.Adicionar("  " + "VETOR " + ObjetoC.getNome() + " { ");

            int i = 0;
            int o = ObjetoC.getValores().size() - 1;


            for (String IdentificadorC : ObjetoC.getValores()) {


                TextoDocumentoC.Adicionar("   " + "\"" + Codifica(IdentificadorC) + "\"");


            }


            TextoDocumentoC.Adicionar("   " + "}");
        }

    }

    public void Matrizes(TextoDocumento TextoDocumentoC, ArrayList<Matriz> mMatrizes) {

        for (Matriz ObjetoC : mMatrizes) {


            TextoDocumentoC.Adicionar("   " + "MATRIZ " + ObjetoC.getNome() + " { ");

            int i = 0;
            int o = ObjetoC.getValores().size() - 1;

            for (ArrayList<String> VetorValor : ObjetoC.getValores()) {

                TextoDocumentoC.Adicionar("   " + "   { ");

                for (String IdentificadorC : VetorValor) {
                    TextoDocumentoC.Adicionar("   " + "\"" + Codifica(IdentificadorC) + "\" ");
                }

                if (i < o) {
                    TextoDocumentoC.Adicionar("   }");
                } else {
                    TextoDocumentoC.Adicionar("   }");
                }
                i += 1;

            }


            TextoDocumentoC.Adicionar("   " + "}");
        }

    }

    public void Pacote_Listar(TextoDocumento TextoDocumentoC, ArrayList<Pacote> lsPacotes) {

        for (Pacote PacoteC : lsPacotes) {

            if (PacoteC.getPacotes().size() == 0 && PacoteC.getListas().size() == 0
                    && PacoteC.getIdentificadores().size() == 0 && PacoteC.getObjetos().size() == 0) {

                TextoDocumentoC.Adicionar(" PACOTE " + Codifica(PacoteC.getNome()) + " { } ");

            } else {

                TextoDocumentoC.Adicionar(" PACOTE " + Codifica(PacoteC.getNome()));
                TextoDocumentoC.Adicionar(" {");

                Identificadores(TextoDocumentoC, PacoteC.getIdentificadores());


                Listas(TextoDocumentoC, PacoteC.getListas());


                Comentarios(TextoDocumentoC, PacoteC.getComentarios());


                Objetos(TextoDocumentoC, PacoteC.getObjetos());

                Vetores(TextoDocumentoC, PacoteC.getVetores());

                Matrizes(TextoDocumentoC, PacoteC.getMatrizes());


                Pacote_Listar(TextoDocumentoC, PacoteC.getPacotes());

            }


            TextoDocumentoC.Adicionar(" } ");

        }

    }
}

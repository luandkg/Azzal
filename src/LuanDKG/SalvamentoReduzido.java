package LuanDKG;

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

    private void Identificadores(ITexto ITextoC, ArrayList<Identificador> mIdentificadores) {

        for (Identificador IdentificadorC : mIdentificadores) {

            ITextoC.Adicionar( " ID " + Codifica(IdentificadorC.getNome()) + " = " + "\""
                    + Codifica(IdentificadorC.getValor()) + "\" ");

        }

    }

    private void Listas(ITexto ITextoC, ArrayList<Lista> mListas) {

        for (Lista ListaC : mListas) {

            if (ListaC.getItens().size() == 0) {

                ITextoC.Adicionar( " LISTA " + Codifica(ListaC.getNome()) + " { } ");

            } else {

                String Itens = "";

                for (String eItem : ListaC.getItens()) {

                    Itens += eItem + " ";

                }

                ITextoC.Adicionar( " LISTA " + Codifica(ListaC.getNome()) + " { " + Itens + " } ");

            }

        }

    }

    private void Comentarios(ITexto ITextoC, ArrayList<Comentario> mComentarios) {

        for (Comentario ComentarioC : mComentarios) {

            ITextoC.Adicionar( " -- " + Codifica(ComentarioC.getNome()) + " : " + "\""
                    + Codifica(ComentarioC.getValor()) + "\" --");

        }

    }

    public void Objetos(ITexto ITextoC, ArrayList<Objeto> mObjetos) {

        for (Objeto ObjetoC : mObjetos) {



            ITextoC.Adicionar( " OBJETO " + ObjetoC.getNome() + " { ");

            for (Identificador IdentificadorC : ObjetoC.getIdentificadores()) {

                ITextoC.Adicionar( " ID " + IdentificadorC.getNome() + " = " + "\""
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

                ITextoC.Adicionar(
                         " LISTA " + Codifica(ListaC.getNome()) + " = { " + Itens + "} ");

            }

            // }

            for (Comentario ComentarioC : ObjetoC.getComentarios()) {

                ITextoC.Adicionar("-- " + Codifica(ComentarioC.getNome()) + " : \""
                        + Codifica(ComentarioC.getValor()) + "\" -- ");

            }

            ITextoC.Adicionar( " } ");
        }

    }
    public void Vetores(ITexto ITextoC, ArrayList<Vetor> mVetores) {


        for (Vetor ObjetoC : mVetores) {


            ITextoC.Adicionar(   "  " + "VETOR " + ObjetoC.getNome() + " { ");

            int i = 0;
            int o = ObjetoC.getValores().size() - 1;


            for (String IdentificadorC : ObjetoC.getValores()) {


                ITextoC.Adicionar("   " + "\"" + Codifica(IdentificadorC) + "\"");


            }


            ITextoC.Adicionar(   "   " + "}");
        }

    }

    public void Matrizes(ITexto ITextoC, ArrayList<Matriz> mMatrizes) {

        for (Matriz ObjetoC : mMatrizes) {


            ITextoC.Adicionar(  "   " + "MATRIZ " + ObjetoC.getNome() + " { ");

            int i = 0;
            int o = ObjetoC.getValores().size() - 1;

            for (ArrayList<String> VetorValor : ObjetoC.getValores()) {

                ITextoC.Adicionar(  "   " + "   { ");

                for (String IdentificadorC : VetorValor) {
                    ITextoC.Adicionar("   " + "\"" + Codifica(IdentificadorC) + "\" ");
                }

                if (i < o) {
                    ITextoC.Adicionar("   }");
                } else {
                    ITextoC.Adicionar("   }");
                }
                i += 1;

            }


            ITextoC.Adicionar("   " + "}");
        }

    }

    public void Pacote_Listar(ITexto ITextoC, ArrayList<Pacote> lsPacotes) {

        for (Pacote PacoteC : lsPacotes) {

            if (PacoteC.getPacotes().size() == 0 && PacoteC.getListas().size() == 0
                    && PacoteC.getIdentificadores().size() == 0 && PacoteC.getObjetos().size() == 0) {

                ITextoC.Adicionar( " PACOTE " + Codifica(PacoteC.getNome()) + " { } ");

            } else {

                ITextoC.Adicionar( " PACOTE " + Codifica(PacoteC.getNome()));
                ITextoC.Adicionar( " {");

                Identificadores(ITextoC, PacoteC.getIdentificadores());


                Listas(ITextoC,  PacoteC.getListas());


                Comentarios(ITextoC, PacoteC.getComentarios());


                Objetos(ITextoC, PacoteC.getObjetos());

                Vetores(ITextoC, PacoteC.getVetores());

                Matrizes(ITextoC, PacoteC.getMatrizes());


                Pacote_Listar(ITextoC, PacoteC.getPacotes());

            }


            ITextoC.Adicionar(" } ");

        }

    }
}

package LuanDKG;

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

    private void Identificadores(ITexto ITextoC, String ePrefixo, ArrayList<Identificador> mIdentificadores) {

        for (Identificador IdentificadorC : mIdentificadores) {

            ITextoC.AdicionarLinha(ePrefixo + "   " + "ID " + Codifica(IdentificadorC.getNome()) + " = " + "\""
                    + Codifica(IdentificadorC.getValor()) + "\"");

        }

    }

    private void Listas(ITexto ITextoC, String ePrefixo, ArrayList<Lista> mListas) {

        for (Lista ListaC : mListas) {

            if (ListaC.getItens().size() == 0) {

                ITextoC.AdicionarLinha(ePrefixo + "   " + "LISTA " + Codifica(ListaC.getNome()) + " { } ");

            } else {

                String Itens = "";

                for (String eItem : ListaC.getItens()) {

                    Itens += eItem + " ";

                }

                ITextoC.AdicionarLinha(ePrefixo + "   " + "LISTA " + Codifica(ListaC.getNome()) + " { " + Itens + "} ");

            }

        }

    }

    private void Comentarios(ITexto ITextoC, String ePrefixo, ArrayList<Comentario> mComentarios) {

        for (Comentario ComentarioC : mComentarios) {

            ITextoC.AdicionarLinha(ePrefixo + "   " + "-- " + Codifica(ComentarioC.getNome()) + " : " + "\""
                    + Codifica(ComentarioC.getValor()) + "\" --");

        }

    }

    public void Objetos(ITexto ITextoC, String ePrefixo, ArrayList<Objeto> mObjetos) {

        for (Objeto ObjetoC : mObjetos) {

            // if (ObjetoC.getIdentificadores().size() == 0 && ObjetoC.getListas().size() ==
            // 0
            // && ObjetoC.getComentarios().size() == 0) {

            // ITextoC.AdicionarLinha(ePrefixo + " " + "OBJETO " + ObjetoC.getNome() + " { }
            // ");

            // } else {

            ITextoC.AdicionarLinha(ePrefixo + "   " + "OBJETO " + ObjetoC.getNome() + " { ");

            for (Identificador IdentificadorC : ObjetoC.getIdentificadores()) {

                ITextoC.AdicionarLinha(ePrefixo + "   " + "   " + "ID " + Codifica(IdentificadorC.getNome()) + " = " + "\""
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

                ITextoC.AdicionarLinha(
                        ePrefixo + "   " + "   " + "LISTA " + Codifica(ListaC.getNome()) + " = { " + Itens + "} ");

            }

            // }

            for (Comentario ComentarioC : ObjetoC.getComentarios()) {

                ITextoC.AdicionarLinha(ePrefixo + "   " + "   " + "-- " + Codifica(ComentarioC.getNome()) + " : \""
                        + Codifica(ComentarioC.getValor()) + "\" -- ");

            }

            ITextoC.AdicionarLinha(ePrefixo + "   " + "}");
        }

    }

    public void Vetores(ITexto ITextoC, String ePrefixo, ArrayList<Vetor> mVetores) {


        for (Vetor ObjetoC : mVetores) {


            ITextoC.Adicionar( ePrefixo + "   " + "VETOR " + ObjetoC.getNome() + " { ");

            int i = 0;
            int o = ObjetoC.getValores().size() - 1;


            for (String IdentificadorC : ObjetoC.getValores()) {


                ITextoC.Adicionar("   " + "\"" + Codifica(IdentificadorC) + "\"");


            }


            ITextoC.AdicionarLinha( ePrefixo + "   " + "}");
        }

    }

    public void Matrizes(ITexto ITextoC, String ePrefixo, ArrayList<Matriz> mMatrizes) {

        for (Matriz ObjetoC : mMatrizes) {


            ITextoC.AdicionarLinha(ePrefixo + "   " + "MATRIZ " + ObjetoC.getNome() + " { ");

            int i = 0;
            int o = ObjetoC.getValores().size() - 1;

            for (ArrayList<String> VetorValor : ObjetoC.getValores()) {

                ITextoC.Adicionar("" + ePrefixo + "   " + "   { ");

                for (String IdentificadorC : VetorValor) {
                    ITextoC.Adicionar("   " + "\"" + Codifica(IdentificadorC) + "\" ");
                }

                if (i < o) {
                    ITextoC.Adicionar("   }\n");
                } else {
                    ITextoC.Adicionar("   }");
                }
                i += 1;

            }


            ITextoC.AdicionarLinha("\n" + ePrefixo + "   " + "}");
        }

    }

    public void Pacote_Listar(ITexto ITextoC, String ePrefixo, ArrayList<Pacote> lsPacotes) {

        for (Pacote PacoteC : lsPacotes) {

            if (PacoteC.getPacotes().size() == 0 && PacoteC.getListas().size() == 0
                    && PacoteC.getIdentificadores().size() == 0 && PacoteC.getObjetos().size() == 0 && PacoteC.getVetores().size() == 0 && PacoteC.getMatrizes().size() == 0) {

                ITextoC.AdicionarLinha(ePrefixo + "PACOTE " + Codifica(PacoteC.getNome()) + " { } ");

            } else if (PacoteC.getPacotes().size() == 0 && PacoteC.getListas().size() == 0
                    && PacoteC.getObjetos().size() == 0 && PacoteC.getVetores().size() == 0 && PacoteC.getMatrizes().size() == 0 && PacoteC.getLinear()) {


                String eLinha = "";

                for (Identificador IdentificadorC : PacoteC.getIdentificadores()) {

                    eLinha += "\t\t" + "ID " + Codifica(IdentificadorC.getNome()) + " = " + "\"" + Codifica(IdentificadorC.getValor()) + "\"";

                }


                ITextoC.AdicionarLinha(ePrefixo + "PACOTE " + Codifica(PacoteC.getNome()) + " { " + eLinha + " } ");

            } else {

                ITextoC.AdicionarLinha(ePrefixo + "PACOTE " + Codifica(PacoteC.getNome()));
                ITextoC.AdicionarLinha(ePrefixo + " {");

                Identificadores(ITextoC, ePrefixo, PacoteC.getIdentificadores());

                Listas(ITextoC, ePrefixo, PacoteC.getListas());

                Comentarios(ITextoC, ePrefixo, PacoteC.getComentarios());

                Objetos(ITextoC, ePrefixo, PacoteC.getObjetos());


                Vetores(ITextoC, ePrefixo, PacoteC.getVetores());

                Matrizes(ITextoC, ePrefixo, PacoteC.getMatrizes());


                Pacote_Listar(ITextoC, ePrefixo + "   ", PacoteC.getPacotes());


                ITextoC.AdicionarLinha(ePrefixo + " } ");
            }


        }

    }
}

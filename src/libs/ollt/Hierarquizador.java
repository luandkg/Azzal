package libs.ollt;

import java.util.ArrayList;

public class Hierarquizador {

    public void Hierarquizar(TextoDocumento TextoDocumentoC, String ePrefixo, ArrayList<Pacote> lsPacotes) {

        for (Pacote PacoteC : lsPacotes) {

            TextoDocumentoC.AdicionarLinha(ePrefixo + "PACOTE : " + PacoteC.getNome());

            for (Identificador IdentificadorC : PacoteC.getIdentificadores()) {

                TextoDocumentoC.AdicionarLinha(
                        ePrefixo + "   " + "ID : " + IdentificadorC.getNome() + " = " + IdentificadorC.getValor());

            }

            for (Lista ListaC : PacoteC.getListas()) {

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
                        ePrefixo + "   " + "LISTA : " + Codifica(ListaC.getNome()) + " = { " + Itens + "} ");

            }

            for (Comentario ComentarioC : PacoteC.getComentarios()) {

                TextoDocumentoC.AdicionarLinha(ePrefixo + "   " + "COMENTARIO : " + Codifica(ComentarioC.getNome()) + " = \""
                        + Codifica(ComentarioC.getValor()) + "\"");

            }

            for (Objeto ObjetoC : PacoteC.getObjetos()) {

                TextoDocumentoC.AdicionarLinha(ePrefixo + "   " + "OBJETO : " + ObjetoC.getNome());

                for (Identificador IdentificadorC : ObjetoC.getIdentificadores()) {

                    TextoDocumentoC.AdicionarLinha(ePrefixo + "      " + "ID : " + IdentificadorC.getNome() + " = "
                            + IdentificadorC.getValor());

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

                    TextoDocumentoC.AdicionarLinha(
                            ePrefixo + "      " + "LISTA : " + Codifica(ListaC.getNome()) + " = { " + Itens + "} ");

                }

                for (Comentario ComentarioC : ObjetoC.getComentarios()) {

                    TextoDocumentoC.AdicionarLinha(ePrefixo + "      " + "COMENTARIO : " + Codifica(ComentarioC.getNome())
                            + " = \"" + Codifica(ComentarioC.getValor()) + "\"");

                }

            }

            Hierarquizar(TextoDocumentoC, ePrefixo + "   ", PacoteC.getPacotes());

        }

    }

    public String Codifica(String e) {
        e = e.replace("@", "@A");
        e = e.replace("'", "@S");
        e = e.replace("\"", "@D");
        e = e.replace("-", "@H");

        return e;
    }

}

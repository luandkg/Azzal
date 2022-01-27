package AppKhronos;

import java.util.ArrayList;

public class Spanizador {

    public ArrayList<Span> getSpans(String texto) {
        ArrayList<Span> spans = new ArrayList<Span>();

        int i = 0;
        int o = texto.length();
        String montando = "";

        boolean isTexto = false;

        while (i < o) {
            String letra = String.valueOf(texto.charAt(i));

            if (!isTexto) {
                if (letra.contentEquals("\"")) {
                    if (montando.length() > 0) {
                        spans.add(new Span(montando));
                    }
                    isTexto = true;
                    montando = "\"";
                } else if (letra.contentEquals(" ") || letra.contentEquals("\t")) {
                    if (montando.length() > 0) {
                        spans.add(new Span(montando));
                        spans.add(new Span(" "));
                    } else {
                        spans.add(new Span(" "));
                    }
                    montando = "";
                } else if (letra.contentEquals(";") || letra.contentEquals(",") || letra.contentEquals(":") || letra.contentEquals("-")) {
                    if (montando.length() > 0) {
                        spans.add(new Span(montando));
                        spans.add(new Span(letra));
                    } else {
                        spans.add(new Span(letra));
                    }
                    montando = "";
                } else {
                    montando += letra;
                }
            } else {
                if (letra.contentEquals("\"")) {
                    Span s = new Span(montando + "\"");
                    s.especializar();
                    spans.add(s);
                    montando = "";
                    isTexto = false;
                }else{
                    montando += letra;
                }

            }


            i += 1;
        }

        if (montando.length() > 0) {
            if (isTexto){
                Span s = new Span(montando );
                s.especializar();
                spans.add(s);
            }else{
                spans.add(new Span(montando));
            }
        }

        return spans;
    }
}

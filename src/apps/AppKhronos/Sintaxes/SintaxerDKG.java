package apps.AppKhronos.Sintaxes;

import apps.AppKhronos.Sintaxer;
import apps.AppKhronos.Span;
import azzal.Cores;
import azzal.Renderizador;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;

import java.util.ArrayList;

public class SintaxerDKG implements Sintaxer {

    private Fonte preto;
    private Fonte amarelo;
    private Fonte vermelho;
    private Fonte verde;
    private Fonte texto;


    public SintaxerDKG(String eNome,int eTamanho) {

        preto = new FonteRunTime(Cores.hexToCor("#ebdbb2"),eNome, eTamanho);
        vermelho = new FonteRunTime(Cores.hexToCor("#cc241d"),eNome, eTamanho);
        amarelo = new FonteRunTime(Cores.hexToCor("#d79921"),eNome, eTamanho);
        verde = new FonteRunTime(Cores.hexToCor("#98971a"),eNome, eTamanho);
        texto = new FonteRunTime(Cores.hexToCor("#b16286"), eNome,eTamanho);


    }

    public void onRender(Renderizador eRender, String linha, int pos_x, int altura) {

        preto.setRenderizador(eRender);
        vermelho.setRenderizador(eRender);
        amarelo.setRenderizador(eRender);
        verde.setRenderizador(eRender);
        texto.setRenderizador(eRender);

        for (Span eSpan : getSpans(linha)) {

            if (eSpan.isEspecial()) {
                texto.escreva(pos_x, altura, eSpan.getTexto());
            } else if (eSpan.getTexto().startsWith("@")) {
                amarelo.escreva(pos_x, altura, eSpan.getTexto());
            } else if (eSpan.getTexto().startsWith("!")) {
                vermelho.escreva(pos_x, altura, eSpan.getTexto());
            } else {
                preto.escreva(pos_x, altura, eSpan.getTexto());
            }


            pos_x += preto.getLarguraDe(eSpan.getTexto());
        }

    }

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
                } else {
                    montando += letra;
                }

            }


            i += 1;
        }

        if (montando.length() > 0) {
            if (isTexto) {
                Span s = new Span(montando);
                s.especializar();
                spans.add(s);
            } else {
                spans.add(new Span(montando));
            }
        }

        return spans;
    }

}

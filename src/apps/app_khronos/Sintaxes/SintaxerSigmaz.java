package apps.app_khronos.Sintaxes;

import apps.app_khronos.Sintaxer;
import apps.app_khronos.Span;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;

import java.util.ArrayList;

public class SintaxerSigmaz implements Sintaxer {

    private Fonte preto;
    private Fonte amarelo;
    private Fonte vermelho;
    private Fonte verde;
    private Fonte texto;


    private ArrayList<String> palavras;
    private ArrayList<String> tipos;
    private ArrayList<String> debug;

    public SintaxerSigmaz(String eNome,int eTamanho) {

        preto = new FonteRunTime(Cores.hexToCor("#ebdbb2"), eNome, eTamanho);
        vermelho = new FonteRunTime(Cores.hexToCor("#cc241d"), eNome, eTamanho);
        amarelo = new FonteRunTime(Cores.hexToCor("#d79921"), eNome, eTamanho);
        verde = new FonteRunTime(Cores.hexToCor("#98971a"), eNome, eTamanho);
        texto = new FonteRunTime(Cores.hexToCor("#b16286"), eNome, eTamanho);

        palavras = new ArrayList<String>();
        tipos = new ArrayList<String>();
        debug = new ArrayList<String>();

        palavras.add("call");
        palavras.add("refer");
        palavras.add("require");

        palavras.add("act");
        palavras.add("func");

        palavras.add("mockiz");
        palavras.add("define");

        palavras.add("struct");
        palavras.add("type");
        palavras.add("model");
        palavras.add("modelable");
        palavras.add("package");

        palavras.add("moc");
        palavras.add("def");

        palavras.add("mut");

        palavras.add("let");
        palavras.add("voz");

        palavras.add("if");
        palavras.add("not");
        palavras.add("while");
        palavras.add("each");

        palavras.add("init");
        palavras.add("all");
        palavras.add("restrict");

        tipos.add("string");
        tipos.add("int");
        tipos.add("num");

        debug.add("DEBUG");
        debug.add("ALL");
        debug.add("REGRESSIVE");
        debug.add("LOCAL");
        debug.add("STRUCTS");
        debug.add("STACK");

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
            } else {
                if (palavras.contains(eSpan.getTexto())) {
                    vermelho.escreva(pos_x, altura, eSpan.getTexto());
                } else if (tipos.contains(eSpan.getTexto())) {
                    amarelo.escreva(pos_x, altura, eSpan.getTexto());
                } else if (debug.contains(eSpan.getTexto())) {
                    verde.escreva(pos_x, altura, eSpan.getTexto());
                } else {
                    preto.escreva(pos_x, altura, eSpan.getTexto());
                }
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

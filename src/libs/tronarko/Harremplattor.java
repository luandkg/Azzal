package libs.tronarko;

import azzal.Cores;
import libs.luan.Par;
import libs.tronarko.utils.AstroLocal;

import java.util.ArrayList;

public class Harremplattor {

    public static ArrayList<Par<String, String>> get(Signos eSigno, Tozte eTozte) {

        ArrayList<Par<String, String>> lista = new ArrayList<Par<String, String>>();


        ArrayList<String> cores = LISTA_DE_CORES();
        ArrayList<String> sentimentos = LISTA_DE_SENTIMENTOS();
        ArrayList<String> escolhas = LISTA_DE_ESCOLHAS();
        ArrayList<String> direcoes = LISTA_DE_DIRECOES();
        ArrayList<String> consoantes = LISTA_DE_CONSOANTES();
        ArrayList<String> vogais = LISTA_DE_VOGAIS();
        ArrayList<String> elementos = LISTA_DE_ELEMENTOS();
        ArrayList<String> letras = LISTA_DE_LETRAS();

        ArrayList<String> n10 = LISTA_DE_NUMEROS_SIMPLES();
        ArrayList<String> n100 = LISTA_DE_NUMEROS_COMPLEXOS();
        ArrayList<String> numeros_do_jogo = LISTA_DE_NUMEROS_JOGO();


        ArrayList<AstroLocal> mLocais = getLocais(eSigno, eTozte);

        lista.add(new Par<String, String>("Sentimento", sentimentos.get(distancia(mLocais, "A", "Vermelho") % sentimentos.size())));
        lista.add(new Par<String, String>("Cor", cores.get(distancia(mLocais, "A", "Amarelo") % cores.size())));
        lista.add(new Par<String, String>("Elemento", elementos.get(distancia(mLocais, "A", "Azul") % elementos.size())));
        lista.add(new Par<String, String>("Direção", direcoes.get(distancia(mLocais, "A", "Laranja") % direcoes.size())));
        lista.add(new Par<String, String>("Escolha", escolhas.get(distancia(mLocais, "A", "Verde") % escolhas.size())));


        lista.add(new Par<String, String>("N10", n10.get(distancia(mLocais, "B", "Vermelho") % n10.size())));
        lista.add(new Par<String, String>("N100", n100.get(distancia(mLocais, "B", "Laranja") % n100.size())));

        lista.add(new Par<String, String>("Letra", letras.get(distancia(mLocais, "C", "Azul") % letras.size())));
        lista.add(new Par<String, String>("Consoante", consoantes.get(distancia(mLocais, "C", "Verde") % consoantes.size())));
        lista.add(new Par<String, String>("Vogal", vogais.get(distancia(mLocais, "C", "Vermelho") % vogais.size())));

        for (int v = 0; v < 100; v++) {
            slicer_numerico(numeros_do_jogo);
        }

        String j1 = numeros_do_jogo.get(distancia(mLocais, "A", "Vermelho") % numeros_do_jogo.size());

        slicer_numerico_retirar(numeros_do_jogo, j1);
        //slicer_numerico(numeros_do_jogo);

        String j2 = numeros_do_jogo.get(distancia(mLocais, "B", "Amarelo") % numeros_do_jogo.size());

        slicer_numerico_retirar(numeros_do_jogo, j2);
        // slicer_numerico(numeros_do_jogo);


        String j3 = numeros_do_jogo.get(distancia(mLocais, "C", "Azul") % numeros_do_jogo.size());

        slicer_numerico_retirar(numeros_do_jogo, j3);
        // slicer_numerico(numeros_do_jogo);

        String j4 = numeros_do_jogo.get(normalizar_triangulacao(mLocais, "A", "B", "Laranja") % numeros_do_jogo.size());

        slicer_numerico_retirar(numeros_do_jogo, j4);
        // slicer_numerico(numeros_do_jogo);

        String j5 = numeros_do_jogo.get(normalizar_triangulacao(mLocais, "A", "B", "Verde") % numeros_do_jogo.size());

        slicer_numerico_retirar(numeros_do_jogo, j5);
        //  slicer_numerico(numeros_do_jogo);

        String j6 = numeros_do_jogo.get(normalizar_triangulacao(mLocais, "A", "C", "Vermelho") % numeros_do_jogo.size());


        lista.add(new Par<String, String>("Jogo", j1 + " " + j2 + " " + j3 + " " + j4 + " " + j5 + " " + j6));


        return lista;
    }


    public static String get_item(Signos eSigno, Tozte eTozte, String eItem) {
        for (Par<String, String> item : Harremplattor.get(eSigno, eTozte)) {
            if (eItem.contentEquals(item.getChave())) {
                return item.getValor();
            }
        }
        return "";
    }


    public static void slicer_numerico_retirar(ArrayList<String> sequencia, String item) {
        int index = 0;
        for (String proc : sequencia) {
            if (proc.contentEquals(item)) {
                sequencia.remove(index);
                break;
            }
            index += 1;
        }
    }

    public static void slicer_numerico(ArrayList<String> sequencia) {

        int p1 = sequencia.size() / 4;
        int p2 = sequencia.size();


        for (int n1 = p1; n1 < p2; n1++) {

            int n2 = (sequencia.size() - 1) - n1;

            String v_origem = sequencia.get(n1);
            String v_destino = sequencia.get(n2);

            sequencia.set(n1, v_destino);
            sequencia.set(n2, v_origem);

        }


    }

    public static ArrayList<AstroLocal> getLocais(Signos eSigno, Tozte eTozte) {

        ArrayList<AstroLocal> mLocais = new ArrayList<AstroLocal>();

        for (AstroLocal p : getEixos(eSigno)) {
            mLocais.add(p);
        }

        for (Astro p : getAstros(eTozte)) {
            mLocais.add(new AstroLocal(p.getNome(), p.getPosX(), p.getPosY()));
        }

        return mLocais;

    }

    public static ArrayList<AstroLocal> getEixos(Signos eSigno) {


        ArrayList<AstroLocal> mEixos = new ArrayList<AstroLocal>();

        if (eSigno == Signos.LEAO) {

            mEixos.add(new AstroLocal("A", 600, 200));
            mEixos.add(new AstroLocal("B", 1200, 400));
            mEixos.add(new AstroLocal("C", 400, 800));

        } else if (eSigno == Signos.CARPA) {

            mEixos.add(new AstroLocal("A", 800, 300));
            mEixos.add(new AstroLocal("B", 1500, 600));
            mEixos.add(new AstroLocal("C", 300, 200));

        } else if (eSigno == Signos.GATO) {


            mEixos.add(new AstroLocal("A", 800, 900));
            mEixos.add(new AstroLocal("B", 300, 900));
            mEixos.add(new AstroLocal("C", 600, 300));

        } else if (eSigno == Signos.GAVIAO) {


            mEixos.add(new AstroLocal("A", 300, 200));
            mEixos.add(new AstroLocal("B", 1400, 300));
            mEixos.add(new AstroLocal("C", 600, 800));

        } else if (eSigno == Signos.LEOPARDO) {


            mEixos.add(new AstroLocal("A", 800, 300));
            mEixos.add(new AstroLocal("B", 800, 600));
            mEixos.add(new AstroLocal("C", 600, 900));

        } else if (eSigno == Signos.LOBO) {


            mEixos.add(new AstroLocal("A", 100, 500));
            mEixos.add(new AstroLocal("B", 1200, 600));
            mEixos.add(new AstroLocal("C", 600, 900));

        } else if (eSigno == Signos.RAPOSA) {


            mEixos.add(new AstroLocal("A", 200, 600));
            mEixos.add(new AstroLocal("B", 1300, 700));
            mEixos.add(new AstroLocal("C", 1400, 600));

        } else if (eSigno == Signos.SERPENTE) {


            mEixos.add(new AstroLocal("A", 500, 200));
            mEixos.add(new AstroLocal("B", 1200, 300));
            mEixos.add(new AstroLocal("C", 600, 800));

        } else if (eSigno == Signos.TIGRE) {


            mEixos.add(new AstroLocal("A", 1200, 800));
            mEixos.add(new AstroLocal("B", 1200, 300));
            mEixos.add(new AstroLocal("C", 600, 800));

        } else if (eSigno == Signos.TOURO) {


            mEixos.add(new AstroLocal("A", 800, 200));
            mEixos.add(new AstroLocal("B", 1200, 900));
            mEixos.add(new AstroLocal("C", 1000, 800));

        }


        return mEixos;
    }


    public static ArrayList<Astro> getAstros(Tozte eTozte) {


        ArrayList<Astro> mAstros = new ArrayList<Astro>();

        Cores mCores = new Cores();

        mAstros.add(new Astro("Vermelho", mCores.getVermelho(), 400, 200, 1200, 700, 5, 1, 100));
        mAstros.add(new Astro("Amarelo", mCores.getAmarelo(), 500, 300, 1300, 500, 10, -1, 150));
        mAstros.add(new Astro("Azul", mCores.getAzul(), 1300, 300, 400, 800, 15, 1, 80));
        mAstros.add(new Astro("Verde", mCores.getVerde(), 600, 150, 600, 900, 6, -1, 50));
        mAstros.add(new Astro("Laranja", mCores.getLaranja(), 350, 600, 1300, 600, 20, -1, 60));


        long harrem = (long) eTozte.getMegarkoDoTronarko() + ((long) eTozte.getTronarko() * 50);


        for (Astro astro : mAstros) {

            astro.setPasso((int) (harrem % astro.getMaximo()));

            int ax1 = astro.getX1();
            int ax2 = astro.getX2();
            int ay1 = astro.getY1();
            int ay2 = astro.getY2();

            if (astro.getNome().contentEquals("Verde")) {

                ax1 += ((eTozte.getTronarko()) % 100) * 50;
                ax2 += ((eTozte.getTronarko()) % 100) * 50;


            } else if (astro.getNome().contentEquals("Laranja")) {

                ay1 += ((eTozte.getTronarko()) % 50) * 20;
                ay2 += ((eTozte.getTronarko()) % 50) * 20;

            } else if (astro.getNome().contentEquals("Azul")) {

                ay1 += ((eTozte.getTronarko()) % 30) * 20;
                ax2 += ((eTozte.getTronarko()) % 30) * 20;


            }

            //  r.drawLinha(ax1, ay1, ax2, ay2, astro.getCor());

            int indo_x = ((ax2 - ax1) / astro.getMaximo()) * astro.getPasso();
            int indo_y = ((ay2 - ay1) / astro.getMaximo()) * astro.getPasso();


            astro.setPos((ax1 + indo_x), (ay1 + indo_y));

        }

        return mAstros;
    }


    public static int normalizar_triangulacao(ArrayList<AstroLocal> tudo, String a, String b, String c) {
        int v1 = distancia(tudo, a, b);
        int v2 = distancia(tudo, b, c);
        int v3 = distancia(tudo, a, c);

        return v1 + v2 + v3;
    }


    public static int distancia(ArrayList<AstroLocal> tudo, String a, String b) {

        int e = 0;

        AstroLocal local_a = null;
        AstroLocal local_b = null;

        for (AstroLocal l : tudo) {
            if (l.getNome().contentEquals(a)) {
                local_a = l;
                e += 1;
            } else if (l.getNome().contentEquals(b)) {
                local_b = l;
                e += 1;
            }
            if (e >= 2) {
                break;
            }
        }

        if (e >= 2) {
            int px = (local_a.getX() - local_b.getX());
            int py = (local_a.getY() - local_b.getY());
            return (int) Math.sqrt((px * px) + (py * py));
        }


        return 0;

    }

    public static ArrayList<String> LISTA_DE_CORES() {

        ArrayList<String> cores = new ArrayList<String>();

        cores.add("AMARELO");
        cores.add("VERDE");
        cores.add("VERMELHO");
        cores.add("AZUL");
        cores.add("PRETO");
        cores.add("BRANCO");
        cores.add("ROSA");
        cores.add("LARANJA");
        cores.add("MARROM");
        cores.add("CINZA");
        cores.add("ROXO");

        return cores;
    }

    public static ArrayList<String> LISTA_DE_SENTIMENTOS() {

        ArrayList<String> sentimentos = new ArrayList<String>();

        sentimentos.add("DIVERSÃO");
        sentimentos.add("ANSIEDADE");
        sentimentos.add("ESTRANHAMENTO");
        sentimentos.add("DESEJO");
        sentimentos.add("EXCITAÇÃO");
        sentimentos.add("TEMOR");
        sentimentos.add("MEDO");
        sentimentos.add("HORROR");
        sentimentos.add("TÉDIO");
        sentimentos.add("CALMA");
        sentimentos.add("EMPATIA");
        sentimentos.add("DÚVIDA");
        sentimentos.add("NOJO");
        sentimentos.add("ENCANTAMENTO");
        sentimentos.add("NOSTALGIA");
        sentimentos.add("SATISFAÇÃO");
        sentimentos.add("ADORAÇÃO");
        sentimentos.add("ADMIRAÇÃO");
        sentimentos.add("APREÇO");
        sentimentos.add("INVEJA");
        sentimentos.add("ROMANCE");
        sentimentos.add("TRISTEZA");
        sentimentos.add("SUPRESA");
        sentimentos.add("SIMPATIA");
        sentimentos.add("TRIUNFO");
        sentimentos.add("INTERESSE");
        sentimentos.add("ALEGRIA");


        return sentimentos;
    }


    public static ArrayList<String> LISTA_DE_ESCOLHAS() {

        ArrayList<String> escolhas = new ArrayList<String>();

        escolhas.add("SIM");
        escolhas.add("NÃO");

        return escolhas;
    }


    public static ArrayList<String> LISTA_DE_ELEMENTOS() {

        ArrayList<String> elementos = new ArrayList<String>();

        elementos.add("ÁGUA");
        elementos.add("FOGO");
        elementos.add("TERRA");
        elementos.add("AR");

        return elementos;
    }

    public static ArrayList<String> LISTA_DE_DIRECOES() {

        ArrayList<String> direcoes = new ArrayList<String>();

        direcoes.add("NORTE");
        direcoes.add("SUL");
        direcoes.add("LESTE");
        direcoes.add("OESTE");
        direcoes.add("NORDESTE");
        direcoes.add("NOROESTE");
        direcoes.add("SUDESTE");
        direcoes.add("SUDOESTE");

        return direcoes;
    }

    public static ArrayList<String> LISTA_DE_CONSOANTES() {

        ArrayList<String> consoantes = new ArrayList<String>();
        String s_consoantes = "BCDFGHJKLMNPQRSTVWXYZ";

        for (int i = 0; i < s_consoantes.length(); i++) {
            consoantes.add(String.valueOf(s_consoantes.charAt(i)));
        }

        return consoantes;
    }

    public static ArrayList<String> LISTA_DE_VOGAIS() {

        ArrayList<String> vogais = new ArrayList<String>();
        String s_vogais = "AEIOU";

        for (int i = 0; i < s_vogais.length(); i++) {
            vogais.add(String.valueOf(s_vogais.charAt(i)));
        }

        return vogais;
    }


    public static ArrayList<String> LISTA_DE_LETRAS() {

        ArrayList<String> letras = new ArrayList<String>();
        String s_letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < s_letras.length(); i++) {
            letras.add(String.valueOf(s_letras.charAt(i)));
        }

        return letras;
    }

    public static ArrayList<String> LISTA_DE_NUMEROS_SIMPLES() {

        ArrayList<String> numeros = new ArrayList<String>();

        for (int i = 0; i < 10; i++) {
            String si = String.valueOf(i);
            if(si.length()==1){
                si="0" + si;
            }
            numeros.add(si);
        }

        return numeros;
    }

    public static ArrayList<String> LISTA_DE_NUMEROS_COMPLEXOS() {

        ArrayList<String> numeros = new ArrayList<String>();

        for (int i = 0; i < 100; i++) {
            String si = String.valueOf(i);
            if(si.length()==1){
                si="0" + si;
            }
            numeros.add(si);
        }

        return numeros;
    }

    public static ArrayList<String> LISTA_DE_NUMEROS_JOGO() {

        ArrayList<String> numeros = new ArrayList<String>();

        for (int i = 0; i < 60; i++) {
            String si = String.valueOf(i);
            if(si.length()==1){
                si="0" + si;
            }
            numeros.add(si);
        }

        return numeros;
    }

}

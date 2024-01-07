package libs.tronarko;

import libs.azzal.Cores;
import libs.luan.Lista;
import libs.luan.Par;
import libs.tronarko.utils.AstroLocal;

public class Harremplattor {

    public static Lista<Par<String, String>> get(Signos eSigno, Tozte eTozte) {

        Lista<Par<String, String>> lista = new Lista<Par<String, String>>();


        Lista<String> cores = LISTA_DE_CORES();
        Lista<String> sentimentos = LISTA_DE_SENTIMENTOS();
        Lista<String> escolhas = LISTA_DE_ESCOLHAS();
        Lista<String> direcoes = LISTA_DE_DIRECOES();
        Lista<String> consoantes = LISTA_DE_CONSOANTES();
        Lista<String> vogais = LISTA_DE_VOGAIS();
        Lista<String> elementos = LISTA_DE_ELEMENTOS();
        Lista<String> letras = LISTA_DE_LETRAS();

        Lista<String> n10 = LISTA_DE_NUMEROS_SIMPLES();
        Lista<String> n100 = LISTA_DE_NUMEROS_COMPLEXOS();
        Lista<String> numeros_do_jogo = LISTA_DE_NUMEROS_JOGO();


        Lista<AstroLocal> mLocais = getLocais(eSigno, eTozte);

        lista.adicionar(new Par<String, String>("Sentimento", sentimentos.get(distancia(mLocais, "A", "Vermelho") % sentimentos.getQuantidade())));
        lista.adicionar(new Par<String, String>("Cor", cores.get(distancia(mLocais, "A", "Amarelo") % cores.getQuantidade())));
        lista.adicionar(new Par<String, String>("Elemento", elementos.get(distancia(mLocais, "A", "Azul") % elementos.getQuantidade())));
        lista.adicionar(new Par<String, String>("Direção", direcoes.get(distancia(mLocais, "A", "Laranja") % direcoes.getQuantidade())));
        lista.adicionar(new Par<String, String>("Escolha", escolhas.get(distancia(mLocais, "A", "Verde") % escolhas.getQuantidade())));


        lista.adicionar(new Par<String, String>("N10", n10.get(distancia(mLocais, "B", "Vermelho") % n10.getQuantidade())));
        lista.adicionar(new Par<String, String>("N100", n100.get(distancia(mLocais, "B", "Laranja") % n100.getQuantidade())));

        lista.adicionar(new Par<String, String>("Letra", letras.get(distancia(mLocais, "C", "Azul") % letras.getQuantidade())));
        lista.adicionar(new Par<String, String>("Consoante", consoantes.get(distancia(mLocais, "C", "Verde") % consoantes.getQuantidade())));
        lista.adicionar(new Par<String, String>("Vogal", vogais.get(distancia(mLocais, "C", "Vermelho") % vogais.getQuantidade())));

        for (int v = 0; v < 100; v++) {
            slicer_numerico(numeros_do_jogo);
        }

        String j1 = numeros_do_jogo.get(distancia(mLocais, "A", "Vermelho") % numeros_do_jogo.getQuantidade());

        slicer_numerico_retirar(numeros_do_jogo, j1);
        //slicer_numerico(numeros_do_jogo);

        String j2 = numeros_do_jogo.get(distancia(mLocais, "B", "Amarelo") % numeros_do_jogo.getQuantidade());

        slicer_numerico_retirar(numeros_do_jogo, j2);
        // slicer_numerico(numeros_do_jogo);


        String j3 = numeros_do_jogo.get(distancia(mLocais, "C", "Azul") % numeros_do_jogo.getQuantidade());

        slicer_numerico_retirar(numeros_do_jogo, j3);
        // slicer_numerico(numeros_do_jogo);

        String j4 = numeros_do_jogo.get(normalizar_triangulacao(mLocais, "A", "B", "Laranja") % numeros_do_jogo.getQuantidade());

        slicer_numerico_retirar(numeros_do_jogo, j4);
        // slicer_numerico(numeros_do_jogo);

        String j5 = numeros_do_jogo.get(normalizar_triangulacao(mLocais, "A", "B", "Verde") % numeros_do_jogo.getQuantidade());

        slicer_numerico_retirar(numeros_do_jogo, j5);
        //  slicer_numerico(numeros_do_jogo);

        String j6 = numeros_do_jogo.get(normalizar_triangulacao(mLocais, "A", "C", "Vermelho") % numeros_do_jogo.getQuantidade());


        lista.adicionar(new Par<String, String>("Jogo", j1 + " " + j2 + " " + j3 + " " + j4 + " " + j5 + " " + j6));


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


    public static void slicer_numerico_retirar(Lista<String> sequencia, String item) {
        int index = 0;
        for (String proc : sequencia) {
            if (proc.contentEquals(item)) {
                sequencia.remover_indice(index);
                break;
            }
            index += 1;
        }
    }

    public static void slicer_numerico(Lista<String> sequencia) {

        int p1 = sequencia.getQuantidade() / 4;
        int p2 = sequencia.getQuantidade();


        for (int n1 = p1; n1 < p2; n1++) {

            int n2 = (sequencia.getQuantidade() - 1) - n1;

            String v_origem = sequencia.get(n1);
            String v_destino = sequencia.get(n2);

            sequencia.set(n1, v_destino);
            sequencia.set(n2, v_origem);

        }


    }

    public static Lista<AstroLocal> getLocais(Signos eSigno, Tozte eTozte) {

        Lista<AstroLocal> mLocais = new Lista<AstroLocal>();

        for (AstroLocal p : getEixos(eSigno)) {
            mLocais.adicionar(p);
        }

        for (Astro p : getAstros(eTozte)) {
            mLocais.adicionar(new AstroLocal(p.getNome(), p.getPosX(), p.getPosY()));
        }

        return mLocais;

    }

    public static Lista<AstroLocal> getEixos(Signos eSigno) {


        Lista<AstroLocal> mEixos = new Lista<AstroLocal>();

        if (eSigno == Signos.LEAO) {

            mEixos.adicionar(new AstroLocal("A", 600, 200));
            mEixos.adicionar(new AstroLocal("B", 1200, 400));
            mEixos.adicionar(new AstroLocal("C", 400, 800));

        } else if (eSigno == Signos.CARPA) {

            mEixos.adicionar(new AstroLocal("A", 800, 300));
            mEixos.adicionar(new AstroLocal("B", 1500, 600));
            mEixos.adicionar(new AstroLocal("C", 300, 200));

        } else if (eSigno == Signos.GATO) {


            mEixos.adicionar(new AstroLocal("A", 800, 900));
            mEixos.adicionar(new AstroLocal("B", 300, 900));
            mEixos.adicionar(new AstroLocal("C", 600, 300));

        } else if (eSigno == Signos.GAVIAO) {


            mEixos.adicionar(new AstroLocal("A", 300, 200));
            mEixos.adicionar(new AstroLocal("B", 1400, 300));
            mEixos.adicionar(new AstroLocal("C", 600, 800));

        } else if (eSigno == Signos.LEOPARDO) {


            mEixos.adicionar(new AstroLocal("A", 800, 300));
            mEixos.adicionar(new AstroLocal("B", 800, 600));
            mEixos.adicionar(new AstroLocal("C", 600, 900));

        } else if (eSigno == Signos.LOBO) {


            mEixos.adicionar(new AstroLocal("A", 100, 500));
            mEixos.adicionar(new AstroLocal("B", 1200, 600));
            mEixos.adicionar(new AstroLocal("C", 600, 900));

        } else if (eSigno == Signos.RAPOSA) {


            mEixos.adicionar(new AstroLocal("A", 200, 600));
            mEixos.adicionar(new AstroLocal("B", 1300, 700));
            mEixos.adicionar(new AstroLocal("C", 1400, 600));

        } else if (eSigno == Signos.SERPENTE) {


            mEixos.adicionar(new AstroLocal("A", 500, 200));
            mEixos.adicionar(new AstroLocal("B", 1200, 300));
            mEixos.adicionar(new AstroLocal("C", 600, 800));

        } else if (eSigno == Signos.TIGRE) {


            mEixos.adicionar(new AstroLocal("A", 1200, 800));
            mEixos.adicionar(new AstroLocal("B", 1200, 300));
            mEixos.adicionar(new AstroLocal("C", 600, 800));

        } else if (eSigno == Signos.TOURO) {


            mEixos.adicionar(new AstroLocal("A", 800, 200));
            mEixos.adicionar(new AstroLocal("B", 1200, 900));
            mEixos.adicionar(new AstroLocal("C", 1000, 800));

        }


        return mEixos;
    }


    public static Lista<Astro> getAstros(Tozte eTozte) {


        Lista<Astro> mAstros = new Lista<Astro>();

        Cores mCores = new Cores();

        mAstros.adicionar(new Astro("Vermelho", mCores.getVermelho(), 400, 200, 1200, 700, 5, 1, 100));
        mAstros.adicionar(new Astro("Amarelo", mCores.getAmarelo(), 500, 300, 1300, 500, 10, -1, 150));
        mAstros.adicionar(new Astro("Azul", mCores.getAzul(), 1300, 300, 400, 800, 15, 1, 80));
        mAstros.adicionar(new Astro("Verde", mCores.getVerde(), 600, 150, 600, 900, 6, -1, 50));
        mAstros.adicionar(new Astro("Laranja", mCores.getLaranja(), 350, 600, 1300, 600, 20, -1, 60));


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


    public static int normalizar_triangulacao(Lista<AstroLocal> tudo, String a, String b, String c) {
        int v1 = distancia(tudo, a, b);
        int v2 = distancia(tudo, b, c);
        int v3 = distancia(tudo, a, c);

        return v1 + v2 + v3;
    }


    public static int distancia(Lista<AstroLocal> tudo, String a, String b) {

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

    public static Lista<String> LISTA_DE_CORES() {

        Lista<String> cores = new Lista<String>();

        cores.adicionar("AMARELO");
        cores.adicionar("VERDE");
        cores.adicionar("VERMELHO");
        cores.adicionar("AZUL");
        cores.adicionar("PRETO");
        cores.adicionar("BRANCO");
        cores.adicionar("ROSA");
        cores.adicionar("LARANJA");
        cores.adicionar("MARROM");
        cores.adicionar("CINZA");
        cores.adicionar("ROXO");

        return cores;
    }

    public static Lista<String> LISTA_DE_SENTIMENTOS() {

        Lista<String> sentimentos = new Lista<String>();

        sentimentos.adicionar("DIVERSÃO");
        sentimentos.adicionar("ANSIEDADE");
        sentimentos.adicionar("ESTRANHAMENTO");
        sentimentos.adicionar("DESEJO");
        sentimentos.adicionar("EXCITAÇÃO");
        sentimentos.adicionar("TEMOR");
        sentimentos.adicionar("MEDO");
        sentimentos.adicionar("HORROR");
        sentimentos.adicionar("TÉDIO");
        sentimentos.adicionar("CALMA");
        sentimentos.adicionar("EMPATIA");
        sentimentos.adicionar("DÚVIDA");
        sentimentos.adicionar("NOJO");
        sentimentos.adicionar("ENCANTAMENTO");
        sentimentos.adicionar("NOSTALGIA");
        sentimentos.adicionar("SATISFAÇÃO");
        sentimentos.adicionar("ADORAÇÃO");
        sentimentos.adicionar("ADMIRAÇÃO");
        sentimentos.adicionar("APREÇO");
        sentimentos.adicionar("INVEJA");
        sentimentos.adicionar("ROMANCE");
        sentimentos.adicionar("TRISTEZA");
        sentimentos.adicionar("SUPRESA");
        sentimentos.adicionar("SIMPATIA");
        sentimentos.adicionar("TRIUNFO");
        sentimentos.adicionar("INTERESSE");
        sentimentos.adicionar("ALEGRIA");


        return sentimentos;
    }


    public static Lista<String> LISTA_DE_ESCOLHAS() {

        Lista<String> escolhas = new Lista<String>();

        escolhas.adicionar("SIM");
        escolhas.adicionar("NÃO");

        return escolhas;
    }


    public static Lista<String> LISTA_DE_ELEMENTOS() {

        Lista<String> elementos = new Lista<String>();

        elementos.adicionar("ÁGUA");
        elementos.adicionar("FOGO");
        elementos.adicionar("TERRA");
        elementos.adicionar("AR");

        return elementos;
    }

    public static Lista<String> LISTA_DE_DIRECOES() {

        Lista<String> direcoes = new Lista<String>();

        direcoes.adicionar("NORTE");
        direcoes.adicionar("SUL");
        direcoes.adicionar("LESTE");
        direcoes.adicionar("OESTE");
        direcoes.adicionar("NORDESTE");
        direcoes.adicionar("NOROESTE");
        direcoes.adicionar("SUDESTE");
        direcoes.adicionar("SUDOESTE");

        return direcoes;
    }

    public static Lista<String> LISTA_DE_CONSOANTES() {

        Lista<String> consoantes = new Lista<String>();
        String s_consoantes = "BCDFGHJKLMNPQRSTVWXYZ";

        for (int i = 0; i < s_consoantes.length(); i++) {
            consoantes.adicionar(String.valueOf(s_consoantes.charAt(i)));
        }

        return consoantes;
    }

    public static Lista<String> LISTA_DE_VOGAIS() {

        Lista<String> vogais = new Lista<String>();
        String s_vogais = "AEIOU";

        for (int i = 0; i < s_vogais.length(); i++) {
            vogais.adicionar(String.valueOf(s_vogais.charAt(i)));
        }

        return vogais;
    }


    public static Lista<String> LISTA_DE_LETRAS() {

        Lista<String> letras = new Lista<String>();
        String s_letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < s_letras.length(); i++) {
            letras.adicionar(String.valueOf(s_letras.charAt(i)));
        }

        return letras;
    }

    public static Lista<String> LISTA_DE_NUMEROS_SIMPLES() {

        Lista<String> numeros = new Lista<String>();

        for (int i = 0; i < 10; i++) {
            String si = String.valueOf(i);
            if (si.length() == 1) {
                si = "0" + si;
            }
            numeros.adicionar(si);
        }

        return numeros;
    }

    public static Lista<String> LISTA_DE_NUMEROS_COMPLEXOS() {

        Lista<String> numeros = new Lista<String>();

        for (int i = 0; i < 100; i++) {
            String si = String.valueOf(i);
            if (si.length() == 1) {
                si = "0" + si;
            }
            numeros.adicionar(si);
        }

        return numeros;
    }

    public static Lista<String> LISTA_DE_NUMEROS_JOGO() {

        Lista<String> numeros = new Lista<String>();

        for (int i = 0; i < 60; i++) {
            String si = String.valueOf(i);
            if (si.length() == 1) {
                si = "0" + si;
            }
            numeros.adicionar(si);
        }

        return numeros;
    }

}

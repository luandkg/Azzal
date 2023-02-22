package apps.app_momentum;

import libs.dkg.DKGObjeto;

import java.util.ArrayList;
import java.util.Random;

public class Harrumpluz {

    public static DKGObjeto construir(String eTitulo) {


        Random sorte = new Random();

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

        ArrayList<String> letras = new ArrayList<String>();
        String s_letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < s_letras.length(); i++) {
            letras.add(String.valueOf(s_letras.charAt(i)));
        }

        ArrayList<String> vogais = new ArrayList<String>();
        String s_vogais = "AEIOU";

        for (int i = 0; i < s_vogais.length(); i++) {
            vogais.add(String.valueOf(s_vogais.charAt(i)));
        }

        ArrayList<String> consoantes = new ArrayList<String>();
        String s_consoantes = "BCDFGHJKLMNPQRSTVWXYZ";

        for (int i = 0; i < s_consoantes.length(); i++) {
            consoantes.add(String.valueOf(s_consoantes.charAt(i)));
        }


        ArrayList<String> sentidos = new ArrayList<String>();

        sentidos.add("NORTE");
        sentidos.add("SUL");
        sentidos.add("LESTE");
        sentidos.add("OESTE");
        sentidos.add("NORDESTE");
        sentidos.add("NOROESTE");
        sentidos.add("SUDESTE");
        sentidos.add("SUDOESTE");


        ArrayList<String> elementos = new ArrayList<String>();

        elementos.add("ÁGUA");
        elementos.add("FOGO");
        elementos.add("TERRA");
        elementos.add("AR");

        ArrayList<String> escolhas = new ArrayList<String>();

        escolhas.add("SIM");
        escolhas.add("NÃO");

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

        DKGObjeto obj_signo = new DKGObjeto(eTitulo);

        obj_signo.identifique("N10", sorte.nextInt(10));
        obj_signo.identifique("N100", sorte.nextInt(100));
        obj_signo.identifique("COR", escolher_um(cores));
        obj_signo.identifique("LETRA", escolher_um(letras));
        obj_signo.identifique("VOGAL", escolher_um(vogais));
        obj_signo.identifique("CONSOANTE", escolher_um(consoantes));
        obj_signo.identifique("SENTIDO", escolher_um(sentidos));
        obj_signo.identifique("ELEMENTO", escolher_um(elementos));
        obj_signo.identifique("ESCOLHA", escolher_um(escolhas));
        obj_signo.identifique("SENTIMENTO", escolher_um(sentimentos));

        return obj_signo;
    }

    public static String escolher_um(ArrayList<String> valores) {

        Random sorte = new Random();
        String ret = "";

        if (valores.size() > 0) {
            ret = valores.get(sorte.nextInt(valores.size()));
        }

        return ret;
    }



}

package libs.RhoBenchmark;

import libs.tempo.Calendario;

import java.util.ArrayList;

public class RhoDuracao {

    private static ArrayList<RhoTempo> sMarcas;
    private static boolean sIniciado = false;

    public static String iniciar(String nome) {

        if (!sIniciado) {
            sMarcas = new ArrayList<RhoTempo>();
            sIniciado = true;
        }

        boolean enc = false;
        for (RhoTempo cron : sMarcas) {
            if (cron.getNome().contentEquals(nome)) {
                enc = true;
                cron.setIniciado(System.currentTimeMillis());
                cron.setFinalizado(System.currentTimeMillis());
                break;
            }
        }
        if (!enc) {
            sMarcas.add(new RhoTempo(nome, System.currentTimeMillis(), System.currentTimeMillis()));
        }

        return Calendario.getDataHoje().getTempo() + " :: " + Calendario.getHoraCompleta();
    }

    public static String terminar(String nome) {

        if (!sIniciado) {
            sMarcas = new ArrayList<RhoTempo>();
            sIniciado = true;
        }

        boolean enc = false;
        for (RhoTempo cron : sMarcas) {
            if (cron.getNome().contentEquals(nome)) {
                enc = true;
                cron.setFinalizado(System.nanoTime());
                break;
            }
        }
        if (!enc) {
            sMarcas.add(new RhoTempo(nome, System.currentTimeMillis(), System.currentTimeMillis()));
        }

        return Calendario.getDataHoje().getTempo() + " :: " + Calendario.getHoraCompleta();
    }

    public static long tempo(String nome) {

        if (!sIniciado) {
            sMarcas = new ArrayList<RhoTempo>();
            sIniciado = true;
        }

        long duracao = 0;

        boolean enc = false;
        for (RhoTempo cron : sMarcas) {
            if (cron.getNome().contentEquals(nome)) {
                enc = true;
                duracao = cron.getFinalizado() - cron.getIniciado();
                break;
            }
        }
        if (!enc) {
            sMarcas.add(new RhoTempo(nome, System.currentTimeMillis(), System.currentTimeMillis()));
            duracao = 0;
        }

        return duracao;
    }

    public static String duracao(String nome) {

        if (!sIniciado) {
            sMarcas = new ArrayList<RhoTempo>();
            sIniciado = true;
        }

        long duracao = 0;

        boolean enc = false;
        for (RhoTempo cron : sMarcas) {
            if (cron.getNome().contentEquals(nome)) {
                enc = true;
                duracao = cron.getFinalizado() - cron.getIniciado();
                break;
            }
        }
        if (!enc) {
            sMarcas.add(new RhoTempo(nome, System.currentTimeMillis(), System.currentTimeMillis()));
            duracao = 0;
        }

        return Calendario.formate_tempo(duracao);
    }
}

package libs.tronarko;

import libs.luan.RefInt;
import libs.tronarko.Intervalos.Tozte_Intervalo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


// 		AUTOR : LUAN ALVES FREITAS
// 		DATA  : 21 09 2018
//
//       	ATUALIZACOES
//
//	CRIACAO		  : 21/09/2018 - Desenvolvimento do Sistema Temporal Tronarko
//
//	ORGANIZACAO 1 : 22/09/2018 
//  ORGANIZACAO 2 : 25/09/2018 
//  ORGANIZACAO 3 : 12/10/2018 
//  ORGANIZACAO 4 : 10/03/2019
//  ORGANIZACAO 5 : 25/04/2021
//  ORGANIZACAO 6 : 27/03/2022
//
//
//	ATUALIZACAO 1  : 29/10/2018 - Implementacao Modarko e Periarko
//	ATUALIZACAO 2  : 01/12/2019 - Alteracao de Nomenclatura dos Superarkos
//  ATUALIZACAO 3  : 29/12/2019 - Metodo de Sincronizacao
//	ATUALIZACAO 4  : 24/03/2020 - Organizacao dos Satelites
//	ATUALIZACAO 5  : 25/03/2020 - Aprimoramento do Metodo de Eventos e Criacao das Classes de Intervalo
// 	ATUALIZACAO 6  : 26/03/2020 - Metodo de Ordenacao , Fusos Horarios e Enumeradores : Hiperarkos, Superarkos, Signos, Periakos e Modarkos.
//	ATUALIZACAO 7  : 26/03/2020 - TozteCor e Interface UI
//	ATUALIZACAO 8  : 30/03/2020 - Avisos
//	ATUALIZACAO 9  : 25/04/2021 - ReOrganizacao do Pacote Tronarko
//	ATUALIZACAO 10 : 15/05/2021 - Hizarkos Com Cor
//  ATUALIZACAO 11 : 06/03/2022 - Fases GIBOSAS DOS SATELITES
//  ATUALIZACAO 12 : 11/03/2022 - AGENDA E FLUXO TEMPORAL
//  ATUALIZACAO 13 : 27/03/2022 - TRONARKO FALSUM e PSEUDO TRONARKO


public class Tronarko {

    private static final String DATA_INICIO = "21/09/2018";
    private static final int TRONARKO_INICIO = 7000;

    // TRON
    public static String getAgora() {
        return getTronAgora().getTextoZerado();
    }

    public static Tron getTronAgora() {

        Calendar c = Calendar.getInstance();

        int eMilisegundo = c.get(Calendar.MILLISECOND);
        int eSegundo = c.get(Calendar.SECOND);
        int eMinuto = c.get(Calendar.MINUTE);
        int eHora = c.get(Calendar.HOUR_OF_DAY);

        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH) + 1;
        int ano = c.get(Calendar.YEAR);


        Hazde eHazde = getHora(eHora, eMinuto, eSegundo, eMilisegundo);
        Tozte eTozte = getData(dia, mes, ano);

        return new Tron(eHazde, eTozte);
    }


    // TOZTE
    public static Tozte getTozte() {

        Calendar c = Calendar.getInstance();

        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH) + 1;
        int ano = c.get(Calendar.YEAR);


        return getData(dia, mes, ano);
    }

    public static Tozte getData(String entrada) {

        String dia = String.valueOf(entrada.charAt(0)) + String.valueOf(entrada.charAt(1));
        String mes = String.valueOf(entrada.charAt(3)) + String.valueOf(entrada.charAt(4));
        String ano = String.valueOf(entrada.charAt(6)) + String.valueOf(entrada.charAt(7));
        ano += String.valueOf(entrada.charAt(8)) + String.valueOf(entrada.charAt(9));


        return getData(Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(ano));
    }

    public static Tozte getData(int eDia, int eMes, int eAno) {

        // System.out.printf("\nHCC : %s",eData);

        String sDia = S(eDia);
        String sMes = S(eMes);
        String sAno = S(eAno);

        String DATA_AQUI = sDia + "/" + sMes + "/" + sAno;

        RefInt diferenca_de_dias = new RefInt(getDias(DATA_INICIO, DATA_AQUI));


        RefInt iTronarko = new RefInt(TRONARKO_INICIO);
        RefInt iHiperarko = new RefInt(1);
        RefInt iSuperarko = new RefInt(1);

        if (diferenca_de_dias.get() >= 0) {

            diferenca_de_dias.reduzir(iTronarko, 500);
            diferenca_de_dias.reduzir(iHiperarko, 50);

            iSuperarko.set(1 + diferenca_de_dias.get());

        } else if (diferenca_de_dias.get() < 0) {

            iTronarko.subtrair(1);
            iHiperarko.set(10);

            diferenca_de_dias.aumentar(iTronarko, 500);
            diferenca_de_dias.aumentar(iHiperarko, 50);


            iSuperarko.set(50 + 1 + diferenca_de_dias.get());
        }

        return new Tozte(iSuperarko.get(), iHiperarko.get(), iTronarko.get());
    }


    // HAZDE
    public static Hazde getHora(String entrada) {

        String hora = String.valueOf(entrada.charAt(0)) + String.valueOf(entrada.charAt(1));
        String minuto = String.valueOf(entrada.charAt(3)) + String.valueOf(entrada.charAt(4));
        String segundo = String.valueOf(entrada.charAt(6)) + String.valueOf(entrada.charAt(7));

        return getHora(Integer.parseInt(hora), Integer.parseInt(minuto), Integer.parseInt(segundo), 0);
    }

    public static Hazde getHora(int eHora, int eMinuto, int eSegundo, int eMilissegundo) {


        double HAZDE_COMPLETO = (24 * 1000 * 60 * 60);
        double TOTAL_SEGUNDOS = (10 * 1000 * 100 * 100);
        double TAXADOR = HAZDE_COMPLETO / TOTAL_SEGUNDOS;

        double toSegundos = eMilissegundo + (eSegundo * 1000) + (eMinuto * 1000 * 60) + (eHora * 1000 * 60 * 60);


        RefInt iArco = new RefInt(0);
        RefInt iIttas = new RefInt(0);
        RefInt iUzzons = new RefInt((int) ((toSegundos / TAXADOR) / 1000));

        iUzzons.reduzir(iIttas, 100);
        iIttas.reduzir(iArco, 100);


        return new Hazde(iArco.get(), iIttas.get(), iUzzons.get());
    }

    public static Hazde getHora(int eHora, int eMinuto, int eSegundo) {

        int eMilissegundo = 0;

        double HAZDE_COMPLETO = (24 * 1000 * 60 * 60);
        double TOTAL_SEGUNDOS = (10 * 1000 * 100 * 100);
        double TAXADOR = HAZDE_COMPLETO / TOTAL_SEGUNDOS;

        double toSegundos = eMilissegundo + (eSegundo * 1000) + (eMinuto * 1000 * 60) + (eHora * 1000 * 60 * 60);


        RefInt iArco = new RefInt(0);
        RefInt iIttas = new RefInt(0);
        RefInt iUzzons = new RefInt((int) ((toSegundos / TAXADOR) / 1000));

        iUzzons.reduzir(iIttas, 100);
        iIttas.reduzir(iArco, 100);


        return new Hazde(iArco.get(), iIttas.get(), iUzzons.get());
    }

    public static Hazde getHazde() {

        Calendar c = Calendar.getInstance();

        int eMilisegundo = c.get(Calendar.MILLISECOND);
        int eSegundo = c.get(Calendar.SECOND);
        int eMinuto = c.get(Calendar.MINUTE);
        int eHora = c.get(Calendar.HOUR_OF_DAY);

        return getHora(eHora, eMinuto, eSegundo, eMilisegundo);

    }

    // FACILITADORES

    public static Tozte make_tozte(int s, int h, int t) {
        return new Tozte(s, h, t);
    }

    public static Hazde make_hazde(int a, int i, int u) {
        return new Hazde(a, i, u);
    }

    public static Hazde getHazdeComecar() {
        return new Hazde(0, 0, 0);
    }

    public static Hazde getHazdeTerminar() {
        return new Hazde(9, 9, 99);
    }

    private static String S(int valor) {

        String sValor = String.valueOf(valor);
        if (sValor.length() == 1) {
            sValor = "0" + sValor;
        }
        return sValor;
    }

    private static int getDias(String DATA_INICIO, String DATA_FIM) {

        int diferenca_de_dias = 0;

        DateFormat CALENDARIO_GREGORIANO = new SimpleDateFormat("dd/MM/yyyy");
        CALENDARIO_GREGORIANO.setLenient(false);

        try {
            long l = (CALENDARIO_GREGORIANO.parse(DATA_FIM).getTime() - CALENDARIO_GREGORIANO.parse(DATA_INICIO).getTime()) / 86400000L;
            diferenca_de_dias = (int) l;

        } catch (java.text.ParseException ignored) {
        }

        return diferenca_de_dias;
    }

    public static long SUPERARKOS_ENTRE_COM(Tozte inicio, Tozte fim) {
        return new Tozte_Intervalo("ENTRE", inicio, fim).getSuperarkos();
    }

    public static long SUPERARKOS_ENTRE_COM_FIM(Tozte inicio, Tozte fim) {
        return new Tozte_Intervalo("ENTRE", inicio, fim).getSuperarkosComFim();
    }


    public static long HAZDE_DIFERENCA(Hazde a,Hazde b){
        return a.getTotalEttons() - b.getTotalEttons();
    }

    public static long TOZTE_DIFERENCA(Tozte a,Tozte b){
        return a.getSuperarkosTotal() - b.getSuperarkosTotal();
    }


    public static String TRON_DIFERENCA(Tron iniciado,Tron terminado){

        String s_diferenca=  "";

        if (iniciado.getTozte().isIgual(terminado.getTozte())) {

            long uzzons = Tronarko.HAZDE_DIFERENCA(terminado.getHazde(), iniciado.getHazde());
            s_diferenca= uzzons + " uz";

        }else         if (iniciado.getTozte().adicionar_Superarko(1).isIgual(terminado.getTozte())) {

            long p1 = Tronarko.HAZDE_DIFERENCA(new Hazde(9, 99, 99), iniciado.getHazde());
            long p2 = Tronarko.HAZDE_DIFERENCA(terminado.getHazde(), new Hazde(0, 0, 0));

            s_diferenca=(p1 + p2) + " uz";

        }else         if (terminado.getTozte().isMaiorQue(iniciado.getTozte().adicionar_Superarko(1))) {

            long t = Tronarko.SUPERARKOS_ENTRE_COM(iniciado.getTozte(),terminado.getTozte());

            long p1 = Tronarko.HAZDE_DIFERENCA(new Hazde(9, 99, 99), iniciado.getHazde());
            long p2 = Tronarko.HAZDE_DIFERENCA(terminado.getHazde(), new Hazde(0, 0, 0));

            s_diferenca = t + " tz " + (p1 + p2) + " uz";

        }

        return s_diferenca;
    }


}

package Tronarko;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


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
        return getTronAgora().getTexto();
    }

    public static Tron getTronAgora() {

        Hazde h = getHazde();
        Tozte t = getTozte();

        Tron ret = new Tron(h.getArco(), h.getItta(), h.getUzzon(), t.getSuperarko(), t.getHiperarko(), t.getTronarko());

        return ret;
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

        long diferencia_de_dias = 0;

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        String sDia = String.valueOf(eDia);
        if (sDia.length() == 1) {
            sDia = "0" + sDia;
        }

        String sMes = String.valueOf(eMes);
        if (sMes.length() == 1) {
            sMes = "0" + sMes;
        }

        String sAno = String.valueOf(eAno);


        try {
            Date DTI = df.parse(DATA_INICIO);
            Date DTE = df.parse(sDia + "/" + sMes + "/" + sAno);
            diferencia_de_dias = (DTE.getTime() - DTI.getTime()) / 86400000L;

        } catch (java.text.ParseException evt) {
        }

        int iTronarko = TRONARKO_INICIO;
        int iHiperarko = 1;
        int iSuperarko = 1;

        if (diferencia_de_dias >= 0) {

            while (diferencia_de_dias >= 500) {
                diferencia_de_dias -= 500;
                iTronarko += 1;
            }

            iHiperarko = 1;
            while (diferencia_de_dias >= 50) {
                diferencia_de_dias -= 50;
                iHiperarko += 1;
            }
            iSuperarko = 1 + (int) diferencia_de_dias;

        } else if (diferencia_de_dias < 0) {

            iTronarko -= 1;
            while (diferencia_de_dias <= -500) {
                diferencia_de_dias += 500;
                iTronarko -= 1;
            }
            iHiperarko = 10;
            while (diferencia_de_dias <= -50) {
                diferencia_de_dias += 50;
                iHiperarko -= 1;
            }
            iSuperarko = 50 + 1 + (int) diferencia_de_dias;
        }

        return new Tozte(iSuperarko, iHiperarko, iTronarko);
    }


    // HAZDE
    public static Hazde getHora(String entrada) {

        String hora = String.valueOf(entrada.charAt(0)) + String.valueOf(entrada.charAt(1));
        String minuto = String.valueOf(entrada.charAt(3)) + String.valueOf(entrada.charAt(4));
        String segundo = String.valueOf(entrada.charAt(6)) + String.valueOf(entrada.charAt(7));

        return getHora(Integer.parseInt(hora), Integer.parseInt(minuto), Integer.parseInt(segundo));
    }

    public static Hazde getHora(int eHora, int eMinuto, int eSegundo) {

        int iArco = 0;
        int iIttas = 0;
        int iUzzons = 0;

        int eMilissegundo = 0;

        long Tudo = eMilissegundo + (eSegundo * 1000) + (eMinuto * 1000 * 60) + (eHora * 1000 * 60 * 60);

        double Taxa1 = (24 * 1000 * 60 * 60);
        double Taxa2 = (10 * 1000 * 100 * 100);

        double Taxador = Taxa1 / Taxa2;

        double Entaxa = Tudo / Taxador;
        long IEntaxa = (long) Entaxa;

        iUzzons = (int) (IEntaxa / 1000);

        while (iUzzons >= 100) {
            iUzzons -= 100;
            iIttas += 1;
        }

        while (iIttas >= 100) {
            iIttas -= 100;
            iArco += 1;
        }

        //iArco += 1;

        return new Hazde(iArco, iIttas, iUzzons);
    }

    public static Hazde getHazde() {

        Calendar c = Calendar.getInstance();

        int eSegundo = c.get(Calendar.SECOND);
        int eMinuto = c.get(Calendar.MINUTE);
        int eHora = c.get(Calendar.HOUR_OF_DAY);

        return getHora(eHora, eMinuto, eSegundo);

    }


}

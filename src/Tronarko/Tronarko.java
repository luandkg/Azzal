package Tronarko;

import Tronarko.Intervalos.Tozte_Intervalo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


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

public class Tronarko {

    private final String DATA_INICIO = "21/09/2018";
    private final int TRONARKO_INICIO = 7000;

    public String getAgora() {
        return getTronAgora().getTexto();
    }

    public Tron getTronAgora() {

        Hazde h = getHazde();
        Tozte t = getTozte();

        Tron ret = new Tron(h.getArco(), h.getItta(), h.getUzzon(), t.getSuperarko(), t.getHiperarko(),
                t.getTronarko());

        return ret;
    }

    public final String toString() {
        return getAgora();
    }

    public Tozte getTozte_Aleatorio() {
        Random rd = new Random();

        return new Tozte(rd.nextInt(50) + 1, rd.nextInt(10) + 1, rd.nextInt(8000));

    }

    public long getLong(Tozte T, Hazde H) {

        long tta = 10 * 100 * 100;

        long total = (T.getSuperarkosTotal() * tta) + H.getTotalEttons();

        return total;
    }

    public String setLong(long total) {

        long tta = 10 * 100 * 100;

        long vcc = total;
        long supers = 0;

        while (vcc >= tta) {
            vcc -= tta;
            supers += 1;
        }

        int vt = 0;
        int vh = 0;
        int vs = 0;

        while (supers >= 500) {
            vt += 1;
            supers -= 500;
        }
        while (supers >= 50) {
            vh += 1;
            supers -= 50;
        }

        vh += 1;


        int va1 = 0;
        int va2 = 0;

        while (vcc >= 10000) {
            va1 += 1;
            vcc -= 10000;
        }

        while (vcc >= 100) {
            va2 += 1;
            vcc -= 100;
        }

        va1 += 1;


        String v1 = supers + "/" + vh + "/" + vt;

        String v2 = va1 + ":" + va2 + ":" + vcc;

        return v1 + " " + v2;
    }


    public Tozte getTozte_AleatorioDoTronarko(int eTronarko) {
        Random rd = new Random();

        return new Tozte(rd.nextInt(50) + 1, rd.nextInt(10) + 1, eTronarko);

    }

    public Tozte getTozte_AleatorioDoHiperarko(int eHiperarko, int eTronarko) {
        Random rd = new Random();

        return new Tozte(rd.nextInt(50) + 1, eHiperarko, eTronarko);

    }

    public Tozte getTozte() {

        Calendar c = Calendar.getInstance();

        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH) + 1;
        int ano = c.get(Calendar.YEAR);


        return getData(dia + "/" + mes + "/" + ano);

    }

    public Tozte getData(String eData) {

        // System.out.printf("\nHCC : %s",eData);

        long Diferenca = 0;

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            Date DTI = df.parse(DATA_INICIO);
            Date DTE = df.parse(eData);
            Diferenca = (DTE.getTime() - DTI.getTime()) / 86400000L;

        } catch (java.text.ParseException evt) {
        }

        int iTronarko = TRONARKO_INICIO;
        int iHiperarko = 1;
        int iSuperarko = 1;

        if (Diferenca >= 0) {

            while (Diferenca >= 500) {
                Diferenca -= 500;
                iTronarko += 1;
            }

            iHiperarko = 1;
            while (Diferenca >= 50) {
                Diferenca -= 50;
                iHiperarko += 1;
            }
            iSuperarko = 1 + (int) Diferenca;

        } else if (Diferenca < 0) {

            iTronarko -= 1;
            while (Diferenca <= -500) {
                Diferenca += 500;
                iTronarko -= 1;
            }
            iHiperarko = 10;
            while (Diferenca <= -50) {
                Diferenca += 50;
                iHiperarko -= 1;
            }
            iSuperarko = 50 + 1 + (int) Diferenca;
        }

        return new Tozte(iSuperarko, iHiperarko, iTronarko);
    }

    public Hazde getHora(String entrada) {

        String hora = String.valueOf(entrada.charAt(0)) + String.valueOf(entrada.charAt(1));
        String minuto = String.valueOf(entrada.charAt(3)) + String.valueOf(entrada.charAt(4));
        String segundo = String.valueOf(entrada.charAt(6)) + String.valueOf(entrada.charAt(7));

        return getHora(Integer.parseInt(hora), Integer.parseInt(minuto), Integer.parseInt(segundo));
    }

    public Hazde getHora(int eHora, int eMinuto, int eSegundo) {

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

    public Hazde getHazde() {

        Calendar c = Calendar.getInstance();

        int eSegundo = c.getTime().getSeconds();
        int eMinuto = c.getTime().getMinutes();
        int eHora = c.getTime().getHours();

        return getHora(eHora, eMinuto, eSegundo);

    }

    public int getSincronizanteHazde(Hazde Local, Hazde Origem) {
        return (Origem.getTotalEttons() - Local.getTotalEttons());
    }

    public long getSincronizanteTozte(Tozte Local, Tozte Origem) {
        return (Origem.getSuperarkosTotal() - Local.getSuperarkosTotal());
    }

    public Tozte_Intervalo getIntervalo(String eNome, Tozte eInicio, Tozte eFim) {
        return new Tozte_Intervalo(eNome, eInicio, eFim);
    }


    // ESTATICOS

    public static Tozte getTozteDireto() {
        Tronarko t = new Tronarko();
        return t.getTozte();
    }

    public static Hazde getHazdeDireto() {
        Tronarko t = new Tronarko();
        return t.getHazde();
    }
}

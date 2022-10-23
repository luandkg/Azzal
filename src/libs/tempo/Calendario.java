package libs.tempo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Calendario {

    public static final int RETITAR = 0;
    public static final int ADICIONAR = 0;
    public static final int MIN_ADICIONAR = 0;

    public static final String SEGUNDA = "SEGUNDA";
    public static final String TERCA = "TERCA";
    public static final String QUARTA = "QUARTA";
    public static final String QUINTA = "QUINTA";
    public static final String SEXTA = "SEXTA";
    public static final String SABADO = "SABADO";
    public static final String DOMINGO = "DOMINGO";

    public static boolean isAntes(String a, String b) {
        return false;
    }

    public static String getDiaDaSemana() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String ret = "";

        int dia = cal.get(Calendar.DAY_OF_WEEK);

        if (dia == 1) {
            ret = DOMINGO;
        } else if (dia == 2) {
            ret = SEGUNDA;
        } else if (dia == 3) {
            ret = TERCA;
        } else if (dia == 4) {
            ret = QUARTA;
        } else if (dia == 5) {
            ret = QUINTA;
        } else if (dia == 6) {
            ret = SEXTA;
        } else if (dia == 7) {
            ret = SABADO;
        }

        //  ret = QUARTA;

        return ret;
    }

    public static int getTempoDoDia() {

        Calendar c = Calendar.getInstance();

        int seg = c.get(Calendar.SECOND);
        int min = c.get(Calendar.MINUTE);
        int hora = c.get(Calendar.HOUR_OF_DAY);


        hora -= RETITAR;
        hora += ADICIONAR;
        min += MIN_ADICIONAR;

        //   hora = 9;
        //  min = min + 0;
        // System.out.println("Agora :: " + hora + ":" + min);


        return (hora * 60 * 60) + (min * 60) + seg;
    }

    public static int getHoraDoDia() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int hora = cal.get(Calendar.HOUR_OF_DAY);

        hora -= RETITAR;
        hora += ADICIONAR;

        return hora;
    }

    public static int getMinutoDoDia() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int min = cal.get(Calendar.MINUTE);

        return min;
    }

    public static boolean isIgual(String a, String b) {
        return a.contentEquals(b);
    }

    public static boolean isDiferente(String a, String b) {
        return !a.contentEquals(b);
    }


    public static boolean isDiaDaSemana(String eDia) {
        boolean ret = false;

        if (eDia.contentEquals(SEGUNDA)) {
            ret = true;
        } else if (eDia.contentEquals(TERCA)) {
            ret = true;
        } else if (eDia.contentEquals(QUARTA)) {
            ret = true;
        } else if (eDia.contentEquals(QUINTA)) {
            ret = true;
        } else if (eDia.contentEquals(SEXTA)) {
            ret = true;
        }

        return ret;
    }

    public static boolean isFimDeSemana(String eDia) {
        boolean ret = false;

        if (eDia.contentEquals(DOMINGO)) {
            ret = true;
        } else if (eDia.contentEquals(SABADO)) {
            ret = true;
        }

        return ret;
    }


    public static String getMesPrefixo(String mes) {

        String sMes = "";

        if (mes.contentEquals("01")) {
            sMes = "JAN";
        } else if (mes.contentEquals("02")) {
            sMes = "FEV";
        } else if (mes.contentEquals("03")) {
            sMes = "MAR";
        } else if (mes.contentEquals("04")) {
            sMes = "ABR";
        } else if (mes.contentEquals("05")) {
            sMes = "MAI";
        } else if (mes.contentEquals("06")) {
            sMes = "JUN";
        } else if (mes.contentEquals("07")) {
            sMes = "JUL";
        } else if (mes.contentEquals("08")) {
            sMes = "OUT";

        } else if (mes.contentEquals("09")) {
            sMes = "SET";
        } else if (mes.contentEquals("10")) {
            sMes = "OUT";
        } else if (mes.contentEquals("11")) {
            sMes = "NOV";
        } else if (mes.contentEquals("12")) {
            sMes = "DEZ";
        }
        return sMes;
    }


    public static int getIndice(ArrayList<Data> datas, String qualdata) {
        int v = 0;
        boolean enc = false;
        for (Data data : datas) {

            if (data.getTempoLegivel().contentEquals(qualdata)) {
                enc = true;
                break;
            }

            v += 1;
        }
        if (!enc) {
            v = -1;
        }
        return v;
    }

    public static ArrayList<Data> filtrar_ate(ArrayList<Data> datas, int pos) {
        int v = 0;
        ArrayList<Data> ret = new ArrayList<Data>();

        for (Data data : datas) {

            if (v < pos) {
                ret.add(data);
            }


            v += 1;
        }

        return ret;
    }


    public static String inverter_mes_dia(String entrada) {

        String saida = "";

        if (entrada.length() >= 5) {
            saida = String.valueOf(entrada.charAt(3)) + String.valueOf(entrada.charAt(4));
            saida += "/" + String.valueOf(entrada.charAt(0)) + String.valueOf(entrada.charAt(1));
        }

        return saida;
    }


    public static String getAMDComTracoInferior() {
        String date = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(new Date());
        return date;
    }

    public static String getADMComBarras() {
        String date = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(new Date()).replace("_", "/");
        return date;
    }

    public static String getData() {
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        return date;
    }

    public static String getHora() {
        String date = new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());
        return date;
    }

    public static String getHoraCompleta() {
        String date = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());
        return date;
    }

    public static Data getDataHoje() {
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        return parse(date);
    }

    public static String getTempoCompleto() {
        return getData() + " " + getHoraCompleta();
    }

    public static Data parse(String sData) {

        int i = 0;
        int o = sData.length();

        String sDia = "";
        String sMes = "";
        String sAno = "";

        int f = 0;

        while (i < o) {
            String l = String.valueOf(sData.charAt(i));

            if (f == 0) {
                if (l.contentEquals("/")) {
                    f += 1;
                } else {
                    sDia += l;
                }
            } else if (f == 1) {
                if (l.contentEquals("/")) {
                    f += 1;
                } else {
                    sMes += l;
                }
            } else if (f == 2) {
                if (l.contentEquals("/")) {
                    f += 1;
                } else {
                    sAno += l;
                }
            }


            i += 1;
        }

        return new Data(Integer.parseInt(sAno), Integer.parseInt(sMes), Integer.parseInt(sDia), DiaSemanal.Domingo);
    }


    public static DiaSemanal proximoDia(DiaSemanal eDiaSemanal) {

        DiaSemanal eRetorno = eDiaSemanal;

        if (eDiaSemanal == DiaSemanal.Domingo) {
            eRetorno = DiaSemanal.Segunda;
        } else if (eDiaSemanal == DiaSemanal.Segunda) {
            eRetorno = DiaSemanal.Terca;
        } else if (eDiaSemanal == DiaSemanal.Terca) {
            eRetorno = DiaSemanal.Quarta;
        } else if (eDiaSemanal == DiaSemanal.Quarta) {
            eRetorno = DiaSemanal.Quinta;
        } else if (eDiaSemanal == DiaSemanal.Quinta) {
            eRetorno = DiaSemanal.Sexta;
        } else if (eDiaSemanal == DiaSemanal.Sexta) {
            eRetorno = DiaSemanal.Sabado;
        } else if (eDiaSemanal == DiaSemanal.Sabado) {
            eRetorno = DiaSemanal.Domingo;
        }

        return eRetorno;
    }

    public static ArrayList<Data> construirAno(int eAno, DiaSemanal eDiaSemanal, int eJan, int eFev, int eMar, int eAbr, int eMai, int eJun, int eJul, int eAgo, int eSet, int eOut, int eNov, int eDez) {

        ArrayList<Data> eDatas = new ArrayList<Data>();

        for (int eDia = 1; eDia <= eJan; eDia++) {
            eDatas.add(new Data(eAno, 1, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eFev; eDia++) {
            eDatas.add(new Data(eAno, 2, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eMar; eDia++) {
            eDatas.add(new Data(eAno, 3, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eAbr; eDia++) {
            eDatas.add(new Data(eAno, 4, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eMai; eDia++) {
            eDatas.add(new Data(eAno, 5, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eJun; eDia++) {
            eDatas.add(new Data(eAno, 6, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eJul; eDia++) {
            eDatas.add(new Data(eAno, 7, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eAgo; eDia++) {
            eDatas.add(new Data(eAno, 8, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eSet; eDia++) {
            eDatas.add(new Data(eAno, 9, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eOut; eDia++) {
            eDatas.add(new Data(eAno, 10, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eNov; eDia++) {
            eDatas.add(new Data(eAno, 11, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eDez; eDia++) {
            eDatas.add(new Data(eAno, 12, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        return eDatas;
    }


    public static void mostrarDatas(ArrayList<Data> datas) {

        for (Data eData : datas) {
            System.out.println("-->> " + eData.getTitulo());
        }

    }

    public static ArrayList<Data> filtrar(ArrayList<Data> mDatas, Data comecar, Data terminar) {

        ArrayList<Data> datas = new ArrayList<Data>();

        boolean dentro = false;

        for (Data eData : mDatas) {

            if (comecar.isIgual(eData)) {
                dentro = true;
            }

            if (dentro) {
                if (terminar.isIgual(eData)) {
                    datas.add(eData);
                    dentro = false;
                } else {
                    datas.add(eData);
                }
            }

        }

        return datas;
    }

    public static String formate_dia_mes(String faixa) {

        String dia = String.valueOf(faixa.charAt(8)) + String.valueOf(faixa.charAt(9));
        String mes = String.valueOf(faixa.charAt(5)) + String.valueOf(faixa.charAt(6));

        String sMes = Calendario.getMesPrefixo(mes);

        return dia + " de " + sMes;

    }

    public static String formate_hora_min(String faixa) {
        String hora = String.valueOf(faixa.charAt(0)) + String.valueOf(faixa.charAt(1));
        String min = String.valueOf(faixa.charAt(3)) + String.valueOf(faixa.charAt(4));

        return hora + ":" + min;
    }


    public static Data filtrar_primeira(ArrayList<Data> mDatas) {
        return mDatas.get(0);
    }

    public static Data filtrar_ultima(ArrayList<Data> mDatas) {
        return mDatas.get(mDatas.size() - 1);
    }


    public static String getMesNome(int mes) {

        String sMes = "";

        if (mes == 1) {
            sMes = "JANEIRO";
        } else if (mes == 2) {
            sMes = "FEVEREIRO";
        } else if (mes == 3) {
            sMes = "MARÇO";
        } else if (mes == 4) {
            sMes = "ABRIL";
        } else if (mes == 5) {
            sMes = "MAIO";
        } else if (mes == 6) {
            sMes = "JUNHO";
        } else if (mes == 7) {
            sMes = "JULHO";

        } else if (mes == 8) {
            sMes = "AGOSTO";
        } else if (mes == 9) {
            sMes = "SETEMBRO";
        } else if (mes == 10) {
            sMes = "OUTUBRO";
        } else if (mes == 11) {
            sMes = "NOVEMBRO";
        } else if (mes == 12) {
            sMes = "DEZEMBRO";
        }

        return sMes;
    }


    public static int pular_quantos_dias(Data eData) {

        int pular = 0;

        if (eData.getDiaSemanal() == DiaSemanal.Segunda) {
            pular = 1;
        } else if (eData.getDiaSemanal() == DiaSemanal.Terca) {
            pular = 2;
        } else if (eData.getDiaSemanal() == DiaSemanal.Quarta) {
            pular = 3;
        } else if (eData.getDiaSemanal() == DiaSemanal.Quinta) {
            pular = 4;
        } else if (eData.getDiaSemanal() == DiaSemanal.Sexta) {
            pular = 5;
        } else if (eData.getDiaSemanal() == DiaSemanal.Sabado) {
            pular = 6;
        }

        return pular;
    }

    public static Data getPrimeiroDiaDoMes(ArrayList<Data> eDatas, int eMes) {

        Data mes_primeiro = new Data(1, 1, 1, DiaSemanal.Domingo);

        for (Data data : eDatas) {
            if (data.getMes() == eMes) {
                mes_primeiro = data;
                break;
            }
        }

        return mes_primeiro;
    }

    public static Data getUltimoDiaDoMes(ArrayList<Data> eDatas, int eMes) {

        int ultimo_dia_do_mes = 0;
        Data eUltimaData = null;

        for (Data data : eDatas) {
            if (data.getMes() == eMes && ultimo_dia_do_mes == 0) {
                eUltimaData = data;
                ultimo_dia_do_mes = data.getDia();
            } else if (data.getMes() == eMes) {
                eUltimaData = data;
                ultimo_dia_do_mes = data.getDia();
            }
        }

        return eUltimaData;

    }

    public static final String UNIDADE_SEGUNDO = "s";
    public static final String UNIDADE_MILISSEGUNDO = "ms";
    public static final String UNIDADE_MINUTO = "min";

    public static String formate_tempo(long duracao) {

        String sufixo = UNIDADE_MILISSEGUNDO;


        if (duracao > 1000) {

            long escala_maior = duracao / 1000;
            long resto = duracao - (escala_maior * 1000);

            duracao = escala_maior;
            sufixo = UNIDADE_SEGUNDO;

            if (resto > 0) {
                sufixo = UNIDADE_SEGUNDO + " " + resto + " " + UNIDADE_MILISSEGUNDO;

                if (duracao > 60) {

                    escala_maior = duracao / 60;
                    resto = duracao - (escala_maior * 60);

                    duracao = escala_maior;
                    sufixo = UNIDADE_MINUTO;

                    if (resto > 0) {
                        sufixo = UNIDADE_MINUTO + " " + resto + " " + UNIDADE_SEGUNDO;
                    }

                }

            }
        }

        return String.valueOf(duracao) + " " + sufixo;

    }
}


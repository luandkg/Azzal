package libs.tempo;

import libs.luan.Lista;
import libs.luan.Strings;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        String date = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        return date;
    }

    public static String getHoraCompleta() {
        String date = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
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


    // IMPLEMENTADO 2022.11

    public static final int INT_DOMINGO = 1;
    public static final int INT_SEGUNDA = 2;
    public static final int INT_TERCA = 3;
    public static final int INT_QUARTA = 4;
    public static final int INT_QUINTA = 5;
    public static final int INT_SEXTA = 6;
    public static final int INT_SABADO = 7;


    public static int toIntDia(String DIA) {

        int v = 0;

        if (isSegunda(DIA)) {
            v = INT_SEGUNDA;
        } else if (isTerca(DIA)) {
            v = INT_TERCA;
        } else if (isQuarta(DIA)) {
            v = INT_QUARTA;
        } else if (isQuinta(DIA)) {
            v = INT_QUINTA;
        } else if (isSexta(DIA)) {
            v = INT_SEXTA;
        } else if (isSabado(DIA)) {
            v = INT_SABADO;
        } else if (isDomingo(DIA)) {
            v = INT_DOMINGO;
        }

        return v;
    }


    public static boolean isSegunda(String eDia) {
        return Calendario.SEGUNDA.contentEquals(eDia);
    }

    public static boolean isTerca(String eDia) {
        return Calendario.TERCA.contentEquals(eDia);
    }

    public static boolean isQuarta(String eDia) {
        return Calendario.QUARTA.contentEquals(eDia);
    }

    public static boolean isQuinta(String eDia) {
        return Calendario.QUINTA.contentEquals(eDia);
    }

    public static boolean isSexta(String eDia) {
        return Calendario.SEXTA.contentEquals(eDia);
    }

    public static boolean isSabado(String eDia) {
        return Calendario.SABADO.contentEquals(eDia);
    }

    public static boolean isDomingo(String eDia) {
        return Calendario.DOMINGO.contentEquals(eDia);
    }


    public static int quantos_dias_entre(Data d1, Data d2, ArrayList<Data> datas) {

        int p1 = 0;
        int p2 = 0;

        int indice = 0;
        int v = 0;

        for (Data d : datas) {

            if (d.isIgual(d1)) {
                p1 = indice;
                v += 1;
            }

            if (d.isIgual(d2)) {
                p2 = indice;
                v += 1;
            }

            if (v == 2) {
                break;
            }

            indice += 1;

        }

        return p2 - p1;
    }

    public static String toFaixaTemporal(String tempo_AMD) {
        String faixa_tempo = "";

        if (tempo_AMD.length() == 10) {

            String sDia = String.valueOf(tempo_AMD.charAt(8)) + String.valueOf(tempo_AMD.charAt(9));
            String sMes = String.valueOf(tempo_AMD.charAt(5)) + String.valueOf(tempo_AMD.charAt(6));

            faixa_tempo = sDia + " de " + Calendario.getMesPrefixo(sMes);

        }

        return faixa_tempo;
    }

    public static boolean isTempoEntre(int eComecar, int eTerminar, int eAgora) {

        boolean ret = false;

        if (eAgora >= eComecar && eAgora < eTerminar) {
            ret = true;
        }

        return ret;
    }


    public static Lista<Data> listar_datas_entre(Data comecar, Data terminar) {

        Lista<Data> ls = new Lista<Data>();


        // System.out.println(comecar.getTempoLegivel());
        // System.out.println(terminar.getTempoLegivel());


        LocalDate data_comecar = LocalDate.parse(Strings.numero_zerado(comecar.getAno()) + "-" + Strings.numero_zerado(comecar.getMes()) + "-" + Strings.numero_zerado(comecar.getDia()));
        LocalDate data_terminar = LocalDate.parse(Strings.numero_zerado(terminar.getAno()) + "-" + Strings.numero_zerado(terminar.getMes()) + "-" + Strings.numero_zerado(terminar.getDia()));

        while (!data_comecar.isAfter(data_terminar)) {

            int dia = data_comecar.getDayOfMonth();
            int mes = data_comecar.getMonthValue();
            int ano = data_comecar.getYear();
            int dia_da_semana = data_comecar.getDayOfWeek().getValue();

            DiaSemanal ds = DiaSemanal.Domingo;

            if (dia_da_semana == 0) {
                ds = DiaSemanal.Domingo;
            } else if (dia_da_semana == 1) {
                ds = DiaSemanal.Segunda;
            } else if (dia_da_semana == 2) {
                ds = DiaSemanal.Terca;
            } else if (dia_da_semana == 3) {
                ds = DiaSemanal.Quarta;
            } else if (dia_da_semana == 4) {
                ds = DiaSemanal.Quinta;
            } else if (dia_da_semana == 5) {
                ds = DiaSemanal.Sexta;
            } else if (dia_da_semana == 6) {
                ds = DiaSemanal.Sabado;

            }

            ls.adicionar(new Data(ano, mes, dia, ds));

            data_comecar = data_comecar.plusDays(1);
        }


        return ls;
    }


    public static int contar_ate(Lista<Data> datas, Data proc) {

        int v = 0;

        for (Data d : datas) {
            if (d.isIgual(proc)) {
                break;
            }


            v += 1;
        }

        return v;
    }


    public static Lista<Data> toDatas(Lista<String> s_datas) {
        Lista<Data> ret = new Lista<Data>();

        for (String s_data : s_datas) {
            ret.adicionar(Data.toData(s_data));
        }

        return ret;
    }

    public static boolean data_contem(Lista<Data> datas, Data data_proc) {
        boolean ret = false;

        for (Data data_corrente : datas) {
            if (data_corrente.isIgual(data_proc)) {
                ret = true;
                break;
            }
        }

        return ret;
    }

    public static void adicionar_unico(Lista<Data> datas, Data data_adicionar) {
        if (!data_contem(datas, data_adicionar)) {
            datas.adicionar(data_adicionar.getCopia());
        }
    }

}


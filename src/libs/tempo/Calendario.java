package libs.tempo;

import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.fmt;

import java.text.ParseException;
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

    public static String getHoraMinuto() {
        String date = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        return date;
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

    public static long getTempoMillis() {
        return System.currentTimeMillis();
    }

    public static long getTempoSegundos() {
        return System.currentTimeMillis() / 1000;
    }

    public static String getIntervalo(long d1, long d2) {
        return fmt.getTempoFormatado(d2 - d1);
    }


    public static String GET_DATA_DE_AMDHM(String tempo) {

        String ano = tempo.substring(0, 4);
        String mes = tempo.substring(5, 7);
        String dia = tempo.substring(8, 10);

        return dia + "/" + mes + "/" + ano;
    }

    public static String GET_HORAMIN_DE_AMDHM(String tempo) {

        String hora = tempo.substring(11, 13);
        String min = tempo.substring(14, 16);

        return hora + ":" + min;
    }

    public static Horario SEGUNDOS_TO_HORARIO(int s) {

        int h = 0;
        int m = 0;
        while (s >= 60) {
            m += 1;
            s -= 60;
        }

        while (m >= 60) {
            m -= 60;
            h += 1;
        }

        return new Horario(h, m, s);
    }

    public static Data getDataAnterior(Data essa) {

        Data antes = null;
        for (Data data_corrente : Calendario.listar_datas_entre("01/01/" + (essa.getAno() - 1), "31/12/" + essa.getAno())) {
            if (data_corrente.isIgual(essa)) {
                break;
            }
            antes = data_corrente;
        }
        return antes;
    }


    public static String DATA_HORA_TO_BRASIL(String data_horario) {

        //  fmt.println("Entrada : " + data_horario);
        int FUSO_BRASIL = -3;

        String data = data_horario.substring(0, 10);
        String horario = data_horario.substring(11, 16);

        int horario_hora = Integer.parseInt(horario.substring(0, 2)) + FUSO_BRASIL;

        if (horario_hora < 0) {
            horario_hora = 24 + horario_hora;

            Data data_corrente = Data.toData(data.replace("-", "_"));


            int data_dia = data_corrente.getDia();
            int data_mes = data_corrente.getMes();
            int data_ano = data_corrente.getAno();

            Data nova = new Data(data_ano, data_mes, data_dia);
            Data antes = Calendario.getDataAnterior(nova);

            // throw new RuntimeException("Hora ::" + data + " -->> " + antes.getTempo());
            data = antes.getTempo().replace("_", "-");
        }


        String horario_minuto = horario.substring(3, 5);

        String s_horario_hora = String.valueOf(horario_hora);
        if (s_horario_hora.length() == 1) {
            s_horario_hora = "0" + s_horario_hora;
        }

        horario = data + "T" + s_horario_hora + ":" + horario_minuto;

        // fmt.println("Data    : " + data);
        //  fmt.println("Horario : " + horario);
        //  fmt.println("Hora    : " + horario_hora);
        //   fmt.println("Minuto  : " + horario_minuto);

        return horario;
        //  throw new RuntimeException("PARE");

    }


    public static String GMT_DATA(String data_hora) {
        return data_hora.substring(0, 10);
    }

    public static String GMT_HORA_MINUTO(String data_hora) {
        return data_hora.substring(11, 16);
    }


    public static ArrayList<Data> listar_datas_entre(String data_corrente, String ultima_data) {

        ArrayList<Data> ls = new ArrayList<Data>();

        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dateTime = sf.parse(data_corrente);

            Calendar cal = Calendar.getInstance();
            cal.setTime(dateTime);
            int DIA = cal.get(Calendar.DAY_OF_MONTH);
            int MES = cal.get(Calendar.MONTH) + 1;
            int ANO = cal.get(Calendar.YEAR);
            int DDS = cal.get(Calendar.DAY_OF_WEEK);

            DiaSemanal DIA_DA_SEMANA = toDiaSemanal(DDS);

            //System.out.println("DATA -->>> " + dateTime);
            //System.out.println("D -->>> " +DIA);
            // System.out.println("M -->>> " + MES);
            //System.out.println("A -->>> " + ANO);
            // System.out.println("S -->>> " + DIA_DA_SEMANA.toString());


            Date fim_dateTime = sf.parse(ultima_data);

            Calendar fim_cal = Calendar.getInstance();
            fim_cal.setTime(fim_dateTime);
            int FIM_DIA = fim_cal.get(Calendar.DAY_OF_MONTH);
            int FIM_MES = fim_cal.get(Calendar.MONTH) + 1;
            int FIM_ANO = fim_cal.get(Calendar.YEAR);


            Data inicio = new Data(ANO, MES, DIA, DIA_DA_SEMANA);
            Data fim = new Data(FIM_ANO, FIM_MES, FIM_DIA, DIA_DA_SEMANA);

            //System.out.println("I :: " + inicio.getTempoLegivel());
            //System.out.println("F :: " + fim.getTempoLegivel());

            Data corrente1 = new Data(ANO, MES, DIA, DIA_DA_SEMANA);
            ls.add(corrente1);

            while (inicio.isDiferente(fim)) {

                Date dt = sf.parse(DIA + "/" + MES + "/" + ANO);
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                c.add(Calendar.DATE, 1);
                dt = c.getTime();
                c.setTime(dt);

                DIA = c.get(Calendar.DAY_OF_MONTH);
                MES = c.get(Calendar.MONTH) + 1;
                ANO = c.get(Calendar.YEAR);
                DDS = c.get(Calendar.DAY_OF_WEEK);

                DIA_DA_SEMANA = toDiaSemanal(DDS);

                //System.out.println("DATA -->>> " + dt);
                //System.out.println("\tD -->>> " +DIA);
                //System.out.println("\tM -->>> " + MES);
                //System.out.println("\tA -->>> " + ANO);
                // System.out.println("\tS -->>> " + DIA_DA_SEMANA.toString());

                Data corrente = new Data(ANO, MES, DIA, DIA_DA_SEMANA);
                ls.add(corrente);
                inicio = new Data(ANO, MES, DIA, DIA_DA_SEMANA);
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }


        return ls;
    }

    public static DiaSemanal toDiaSemanal(int DDS) {
        DiaSemanal DIA_DA_SEMANA = DiaSemanal.Domingo;
        if (DDS == 1) {
            DIA_DA_SEMANA = DiaSemanal.Domingo;
        } else if (DDS == 2) {
            DIA_DA_SEMANA = DiaSemanal.Segunda;
        } else if (DDS == 3) {
            DIA_DA_SEMANA = DiaSemanal.Terca;
        } else if (DDS == 4) {
            DIA_DA_SEMANA = DiaSemanal.Quarta;
        } else if (DDS == 5) {
            DIA_DA_SEMANA = DiaSemanal.Quinta;
        } else if (DDS == 6) {
            DIA_DA_SEMANA = DiaSemanal.Sexta;
        } else if (DDS == 7) {
            DIA_DA_SEMANA = DiaSemanal.Sabado;
        }
        return DIA_DA_SEMANA;
    }

    public static ArrayList<Data> GET_SEMANA_DATAS(ArrayList<Data> datas, int semana) {
        ArrayList<Data> ls = new ArrayList<Data>();

        int semana_proxima = semana + 1;

        for (Data ds : datas) {
            if (ds.getSemana() == semana) {
                ls.add(ds);
            }
            if (ds.getSemana() == semana_proxima) {
                break;
            }
        }

        return ls;
    }

    public static void MARCAR_SEMANA(ArrayList<Data> DATAS_SEQUENCIAIS, Data DataCorrente) {

        for (Data d : DATAS_SEQUENCIAIS) {
            if (d.isIgual(DataCorrente)) {
                DataCorrente.setDiaSemanal(d.getDiaSemanal());
                DataCorrente.setSemana(d.getSemana());
                break;
            }
        }

    }

    public static void SEQUENCIAR_SEMANAS(ArrayList<Data> DATAS_SEQUENCIAIS) {

        int semana_id = 0;

        for (Data d : DATAS_SEQUENCIAIS) {

            if (d.getDiaSemanal() == DiaSemanal.Domingo) {
                semana_id += 1;
            }

            d.setSemana(semana_id);

        }

        int si = -1;

        for (Data d : DATAS_SEQUENCIAIS) {
            if (d.getSemana() != si) {
                si = d.getSemana();
            }
        }

    }

    public static String GET_PRIMEIRA_DATA(int eAno) {
        return "01/01/" + String.valueOf(eAno);
    }

    public static String GET_ULTIMA_DATA(int eAno) {
        return "31/12/" + String.valueOf(eAno);
    }

    public static int DIAS_TO_SEGUNDOS(int v) {
        return (v) * (24 * 60 * 60);
    }

    public static int HORAS_TO_SEGUNDOS(int v) {
        return (v) * (60 * 60);
    }

    public static int MINUTOS_TO_SEGUNDOS(int v) {
        return (v) * (60);
    }

    public static int CALCULAR_IDADE_HUMANA(Data aniversiario, Data referencia) {
        int ddn_ano = aniversiario.getAno();
        int ddn_mes = aniversiario.getMes();
        int ddn_dia = aniversiario.getDia();

        int idade = referencia.getAno() - ddn_ano;

        if (ddn_mes > referencia.getMes()) {
            idade -= 1;
        } else if (ddn_mes == referencia.getMes()) {
            if (ddn_dia > referencia.getDia()) {
                idade -= 1;
            }
        }

        return idade;
    }


    public static ArrayList<Data> filtrar_mes(ArrayList<Data> datas, int mes) {
        ArrayList<Data> ret = new ArrayList<Data>();

        for (Data d : datas) {
            if (d.getMes() == mes) {
                ret.add(d);
            }
        }

        return ret;
    }


    public static ArrayList<Data> FILTRAR_MES_E_ANO(ArrayList<Data> mDatas, int mes, int ano) {
        ArrayList<Data> datas_mes = new ArrayList<Data>();

        for (Data data : mDatas) {
            if (data.getMes() == mes && data.getAno() == ano) {
                datas_mes.add(data);
            }
        }
        return datas_mes;
    }


    public static ArrayList<String> GET_TITULOS_DIAS_COM3() {
        ArrayList<String> ls = new ArrayList<String>();

        ls.add("DOM");
        ls.add("SEG");
        ls.add("TER");
        ls.add("QUA");
        ls.add("QUI");
        ls.add("SEX");
        ls.add("SAB");


        return ls;
    }

    public static boolean temEssaData(Data data, ArrayList<Data> datas) {
        boolean ret = false;

        for (Data d : datas) {
            if (d.isIgual(data)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public static String DATA_INVERTER(String data) {
        String inversa = "";

        String dia_1 = String.valueOf(data.charAt(0));
        String dia_2 = String.valueOf(data.charAt(1));

        String mes_1 = String.valueOf(data.charAt(3));
        String mes_2 = String.valueOf(data.charAt(4));

        String ano_1 = String.valueOf(data.charAt(6));
        String ano_2 = String.valueOf(data.charAt(7));
        String ano_3 = String.valueOf(data.charAt(8));
        String ano_4 = String.valueOf(data.charAt(9));

        String s_ano = ano_1 + ano_2 + ano_3 + ano_4;
        String s_mes = mes_1 + mes_2;
        String s_dia = dia_1 + dia_2;

        inversa = s_ano + "_" + s_mes + "_" + s_dia;

        return inversa;
    }

    public static String DATA_COM_TRACO_INFERIOR(String data) {
        return data.replace("/", "_");
    }

    public static String DATA_GET_DIA(String data) {
        String dia_1 = String.valueOf(data.charAt(0));
        String dia_2 = String.valueOf(data.charAt(1));

        return dia_1 + dia_2;
    }

    public static String DATA_GET_MES(String data) {
        String dia_1 = String.valueOf(data.charAt(3));
        String dia_2 = String.valueOf(data.charAt(4));

        return dia_1 + dia_2;
    }

    public static boolean isDataValida(ArrayList<String> datas, String data) {
        boolean ret = false;
        for (String at : datas) {
            if (at.contentEquals(data)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public static Data GET_SEMANA_INICIO(ArrayList<Data> datas, int semana) {
        Data d = new Data(1, 1, 1);

        for (Data ds : datas) {
            if (ds.getSemana() == semana) {
                d = ds;
                break;
            }
        }

        return d;
    }

    public static Data GET_SEMANA_FIM(ArrayList<Data> datas, int semana) {
        Data d = new Data(1, 1, 1);

        int semana_proxima = semana + 1;

        for (Data ds : datas) {
            if (ds.getSemana() == semana) {
                d = ds;
            }
            if (ds.getSemana() == semana_proxima) {
                break;
            }
        }

        return d;
    }

    public static int contar_datas_entre(String data_corrente, String ultima_data) {

        int contagem = 0;

        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dateTime = sf.parse(data_corrente);

            Calendar cal = Calendar.getInstance();
            cal.setTime(dateTime);
            int DIA = cal.get(Calendar.DAY_OF_MONTH);
            int MES = cal.get(Calendar.MONTH) + 1;
            int ANO = cal.get(Calendar.YEAR);
            int DDS = cal.get(Calendar.DAY_OF_WEEK);

            DiaSemanal DIA_DA_SEMANA = toDiaSemanal(DDS);

            //System.out.println("DATA -->>> " + dateTime);
            //System.out.println("D -->>> " +DIA);
            // System.out.println("M -->>> " + MES);
            //System.out.println("A -->>> " + ANO);
            // System.out.println("S -->>> " + DIA_DA_SEMANA.toString());


            Date fim_dateTime = sf.parse(ultima_data);

            Calendar fim_cal = Calendar.getInstance();
            fim_cal.setTime(fim_dateTime);
            int FIM_DIA = fim_cal.get(Calendar.DAY_OF_MONTH);
            int FIM_MES = fim_cal.get(Calendar.MONTH) + 1;
            int FIM_ANO = fim_cal.get(Calendar.YEAR);


            Data inicio = new Data(ANO, MES, DIA, DIA_DA_SEMANA);
            Data fim = new Data(FIM_ANO, FIM_MES, FIM_DIA, DIA_DA_SEMANA);

            //System.out.println("I :: " + inicio.getTempoLegivel());
            //System.out.println("F :: " + fim.getTempoLegivel());

            Data corrente1 = new Data(ANO, MES, DIA, DIA_DA_SEMANA);
            contagem += 1;

            while (inicio.isDiferente(fim)) {

                Date dt = sf.parse(DIA + "/" + MES + "/" + ANO);
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                c.add(Calendar.DATE, 1);
                dt = c.getTime();
                c.setTime(dt);

                DIA = c.get(Calendar.DAY_OF_MONTH);
                MES = c.get(Calendar.MONTH) + 1;
                ANO = c.get(Calendar.YEAR);
                DDS = c.get(Calendar.DAY_OF_WEEK);

                DIA_DA_SEMANA = toDiaSemanal(DDS);

                //System.out.println("DATA -->>> " + dt);
                //System.out.println("\tD -->>> " +DIA);
                //System.out.println("\tM -->>> " + MES);
                //System.out.println("\tA -->>> " + ANO);
                // System.out.println("\tS -->>> " + DIA_DA_SEMANA.toString());

                Data corrente = new Data(ANO, MES, DIA, DIA_DA_SEMANA);
                contagem += 1;
                inicio = new Data(ANO, MES, DIA, DIA_DA_SEMANA);
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }


        return contagem;
    }

    public static String getTempoCompletoSemSegundos() {
        return getData() + " " + getHoraMinuto();
    }


    public static ArrayList<Data> listar_datas_entre_anos(int ano_inicio, int ano_fim) {
        return listar_datas_entre("01/01/" + ano_inicio, "31/12/" + ano_fim);
    }
}


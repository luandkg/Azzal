package libs.tempo;

import libs.luan.fmt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Tempo {

    public static String getData() {
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        return date;
    }


    public static String getHoraCompleta() {
        String date = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());
        return date;
    }

    public static String getTempoCompleto() {
        return getData() + " " + getHoraCompleta();
    }

    public static long getTempoNano() {
        return System.nanoTime();
    }

    public static String getIntervalo(long d1, long d2) {
        return fmt.getTempoFormatado(d2 - d1);
    }

    public static long getTempoMillis() {
        return System.currentTimeMillis();
    }


}

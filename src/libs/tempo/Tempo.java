package libs.tempo;

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

    public static long getTempoNano(){
        return  System.nanoTime();
    }

}

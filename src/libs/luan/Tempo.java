package libs.luan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tempo {

    public static int getSegundos() {
        Calendar c = Calendar.getInstance();

        int eSegundo = c.getTime().getSeconds();
        int eMinuto = c.getTime().getMinutes();
        int eHora = c.getTime().getHours();

        return (eHora * 60 * 60) + (eMinuto * 60) + eSegundo;
    }


    public static int getDias(String DATA_INICIO, String DATA_FIM) {

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
}

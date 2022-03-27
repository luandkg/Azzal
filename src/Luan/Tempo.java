package Luan;

import java.util.Calendar;

public class Tempo {

    public static int getSegundos() {
        Calendar c = Calendar.getInstance();

        int eSegundo = c.getTime().getSeconds();
        int eMinuto = c.getTime().getMinutes();
        int eHora = c.getTime().getHours();

        return (eHora * 60 * 60) + (eMinuto * 60) + eSegundo;
    }

}

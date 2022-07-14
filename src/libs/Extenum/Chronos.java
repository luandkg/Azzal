package libs.Extenum;

import java.time.LocalDate;
import java.time.LocalTime;

public class Chronos {

    public String getData() {
        return getNum(LocalDate.now().getDayOfMonth() )+ "/" + getNum(LocalDate.now().getMonthValue()) + "/" + LocalDate.now().getYear();
    }

    public String getTempo() {
        return getNum(LocalTime.now().getHour() )+ ":" + getNum(LocalTime.now().getMinute()) + ":" + getNum(LocalTime.now().getSecond());
    }

    public String getNum(int n) {
        String v = String.valueOf(n);

        if (v.length() == 1) {
            v = "0" + v;
        }

        return v;
    }

}

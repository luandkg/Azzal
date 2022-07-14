package apps.app_attuz.Legendas;

public class Legendas {

    public static Legenda getTemperatura() {

        Legenda eLegenda = new Legenda(11);

        eLegenda.set(1, "-20ºC");
        eLegenda.set(2, "-10ºC");
        eLegenda.set(3, "0ºC");
        eLegenda.set(4, "10ºC");
        eLegenda.set(5, "25ºC");
        eLegenda.set(6, "30ºC");
        eLegenda.set(7, "35ºC");
        eLegenda.set(8, "40ºC");
        eLegenda.set(9, "45ºC");
        eLegenda.set(10, "55ºC");
        eLegenda.set(11, "60ºC");

        return eLegenda;
    }

    public static Legenda getPreciptacao() {

        Legenda eLegenda = new Legenda(7);

        eLegenda.set(1, "0");
        eLegenda.set(2, "20");
        eLegenda.set(3, "40");
        eLegenda.set(4, "60");
        eLegenda.set(5, "80");
        eLegenda.set(6, "100");


        return eLegenda;
    }


}

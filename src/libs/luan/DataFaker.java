package libs.luan;

public class DataFaker {


    public static String GET_HORARIO_ENTRE(int hora_min,int hora_maximo){


        int a_hora = Aleatorio.aleatorio_entre(hora_min,hora_maximo);
        int a_min = Aleatorio.aleatorio_entre(0,59);
        int a_seg = Aleatorio.aleatorio_entre(0,59);


        return fmt.numero_zerado_c2(a_hora)+":"+fmt.numero_zerado_c2(a_min)+":"+fmt.numero_zerado_c2(a_seg);
    }


}

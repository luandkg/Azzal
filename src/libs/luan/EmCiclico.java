package libs.luan;


public class EmCiclico {


    public static<T1> ItemCiclicoIterador<T1> GET(Lista<T1> eLista){
        return new ItemCiclicoIterador<T1>(eLista);
    }



}

package Servittor;

public class Servittor {

    public static void onServico(String eNome,Servico eServico){

      //  System.out.println("############### SERVITTOR ##############");
      //  System.out.println("onServico : " + eNome);

        System.out.println("############### " + eNome + " ##############");

        eServico.onInit();

    }


}

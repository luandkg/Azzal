package libs.Servittor;

public class Servittor {

    public static void onServico(String servico_nome,Servico eServico){

      //  System.out.println("############### SERVITTOR ##############");
      //  System.out.println("onServico : " + eNome);

        System.out.println("############### " + servico_nome + " ##############");
        eServico.onNomear(servico_nome);
        eServico.onInit();

    }


}

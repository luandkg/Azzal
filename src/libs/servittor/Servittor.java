package libs.servittor;

public class Servittor {

    public static void onServico(String servico_nome, Servico eServico) {


        System.out.println("############### " + servico_nome + " ##############");
        eServico.onNomear(servico_nome);
        eServico.onInit();

    }


}

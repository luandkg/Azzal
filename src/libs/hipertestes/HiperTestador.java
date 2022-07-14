package libs.hipertestes;

import java.util.ArrayList;

public class HiperTestador {

    private ArrayList<Teste> testes;

    public HiperTestador() {
        testes = new ArrayList<Teste>();
    }

    public void adicionar(Teste eTeste) {
        testes.add(eTeste);
    }

    public void realizar_testes() {

        System.out.println("");
        System.out.println("----------------------- TESTES -----------------------------");
        System.out.println("");

        int CONTADOR_TESTES = 0;
        int CONTADOR_SUCESSO = 0;
        int CONTADOR_FALHOU = 0;

        long INICIO = System.nanoTime();

        for (Teste teste : testes) {

            System.out.println("Teste :: " + teste.getNome());

            teste.executar();

            CONTADOR_TESTES += 1;

            if (teste.getStatus()) {
                CONTADOR_SUCESSO += 1;
            } else {
                CONTADOR_FALHOU += 1;
            }

            System.out.println("\t - ASSERTIVAS :: " + teste.getRealizadosTexto());
            System.out.println("\t - STATUS     :: " + teste.getStatusTexto());

            for (String s : teste.getFalhas()) {
                System.out.println(s);
            }

            System.out.println("");


        }

        long FIM = System.nanoTime();

        String testes_status = "";

        if (CONTADOR_TESTES == CONTADOR_SUCESSO) {
            testes_status = "TUDO OK";
        } else {
            if (CONTADOR_FALHOU == 1) {
                testes_status = CONTADOR_FALHOU + " falhou...";
            } else {
                testes_status = CONTADOR_FALHOU + " falharam...";
            }

        }


    long tempo = (FIM - INICIO) / 1000000;

        System.out.println("----------------------------------------------------");
        System.out.println("TESTES   :: "+CONTADOR_TESTES);
        System.out.println("DURACAO  :: "+tempo +" ms");
        System.out.println("STATUS   :: "+testes_status);

}
}

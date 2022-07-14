package libs.Servittor;

import libs.Luan.STTY;

public class Servico {

    private String mNome = "";

    private long inicio = 0;
    private long fim = 0;

    public void onNomear(String eNome) {
        mNome = eNome;
    }

    public void onInit() {

    }

    public void println(String a) {
        System.out.println(mNome + " :: " + a);
    }

    public void marcarInicio() {
        inicio = System.nanoTime();
    }

    public void marcarFim() {
        fim = System.nanoTime();
    }

    public void mostrarTempo() {

        long tempo = fim - inicio;

        double segundos = (double) tempo / 1_000_000_000.0;

        System.out.println("TEMPO :: " + STTY.doubleNumC2(segundos) + " s");

    }
}

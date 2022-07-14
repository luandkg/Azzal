package apps.AppAttuz.Assessorios;

import libs.Luan.STTY;
import libs.Luan.fmt;

public class Progressante {

    private int mMaximo;
    private int passado_i;

    public Progressante(int maximo) {
        mMaximo = maximo;
        passado_i = -1;
    }


    public void emitir(int eValor, String eFrase) {

        double agora = ((double) eValor / (double) mMaximo) * 100.0;
        int agora_i = (int) agora;

        if (agora_i > passado_i) {

            String v = fmt.format("PROGRESSO :: {f2} {}", STTY.doubleNumC2(agora), eFrase);

            System.out.println("\t - " + v);

            passado_i = agora_i;
        }


    }

    public void vazio(int eValor ) {

        double agora = ((double) eValor / (double) mMaximo) * 100.0;
        int agora_i = (int) agora;

        if (agora_i > passado_i) {
            passado_i = agora_i;
        }


    }
}

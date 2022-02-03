package Arquivos.Audio;

import java.util.ArrayList;

public class RepetidorBinario {


    public ArrayList<RepeticaoBinaria> getRepeticoes(ArrayList<Integer> bloco) {

        ArrayList<RepeticaoBinaria> ret = new ArrayList<RepeticaoBinaria>();

        int i = 0;
        int o = bloco.size();
        while (i < o) {

            int valor = bloco.get(i);
            if (i + 1 < o) {
                int prox = bloco.get(i + 1);

                String sValor = String.valueOf(valor) + "_" + String.valueOf(prox);

                boolean existe = false;

                for (RepeticaoBinaria pc : ret) {
                    if (pc.getValor().contentEquals(sValor)) {

                        pc.aumentar();

                        pc.guardar(i);

                        existe = true;
                        break;
                    }
                }

                if (!existe) {

                    RepeticaoBinaria novo = new RepeticaoBinaria((byte) valor, (byte) prox, sValor);
                    novo.guardar(i);

                    ret.add(novo);

                }

                i += 1;

            }

            i += 1;

        }


        return ret;
    }
}

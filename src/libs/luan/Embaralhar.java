package libs.luan;

import apps.app_attuz.SistemaEleitoral.Candidato;
import libs.luan.Aleatorio;
import libs.luan.Lista;

public class Embaralhar {


    public static <T> void emabaralhe(Lista<T> lista) {


        for (int i = 0; i < lista.getQuantidade(); i++) {

            int p1 = Aleatorio.aleatorio(lista.getQuantidade());
            int p2 = Aleatorio.aleatorio(lista.getQuantidade());

            T c1 = lista.getValor(p1);
            T c2 = lista.getValor(p2);

            lista.setValor(p1, c2);
            lista.setValor(p2, c1);

        }


    }

    public static <T> Lista<Indexado<T>> indexe(Vetor<T> vetor) {


        for (int i = 0; i < vetor.getCapacidade(); i++) {

            int p1 = Aleatorio.aleatorio(vetor.getCapacidade());
            int p2 = Aleatorio.aleatorio(vetor.getCapacidade());

            T c1 = vetor.get(p1);
            T c2 = vetor.get(p2);

            vetor.set(p1, c2);
            vetor.set(p2, c1);

        }

        Lista<Indexado<T>> ret = new Lista<Indexado<T>>();

        for (int i = 0; i < vetor.getCapacidade(); i++) {
            ret.adicionar(new Indexado<>(i, vetor.get(i)));
        }

        return ret;

    }

}

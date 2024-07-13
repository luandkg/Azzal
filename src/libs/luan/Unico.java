package libs.luan;

import java.util.Iterator;

public class Unico<T> implements Iterable<T> {

    private Igualavel<T> mIgualavel;
    private Lista<T> mLista;

    public Unico(Igualavel<T> eIgualavel) {
        mIgualavel = eIgualavel;
        mLista = new Lista<T>();
    }


    public boolean item(T valor) {

        if (mLista.existe(mIgualavel, valor)) {
            return false;
        } else {
            mLista.adicionar(valor);
            return true;
        }

    }

    public T item_get(T valor) {

        T item_ret =null;

        if (mLista.existe(mIgualavel, valor)) {
            for (T proc : this) {
                if (mIgualavel.is(proc, valor)) {
                    item_ret= proc;
                    break;
                }
            }
        } else {
            mLista.adicionar(valor);
            item_ret= valor;
        }

        return item_ret;
    }


    public void remover(T item){
        mLista.remover(item);
    }

    public void remover_varios(Lista<T> remocao){
        mLista.remover_varios(remocao);
    }



    public int getQuantidade() {
        return mLista.getQuantidade();
    }


    public Lista<T> toLista(){
        Lista<T> lista = new Lista<T>();
        for(T item : this){
            lista.adicionar(item);
        }
        return lista;
    }



    public Iterator<T> iterator() {
        return new Lista.IteradorDaLista<T>(mLista);
    }


    public static <T1> Unico<T1> TIRAR_COPIA(Unico<T1> original) {
        Unico<T1> copia = new Unico<T1>(original.mIgualavel);

        for (T1 valor : original) {
            copia.item(valor);
        }

        return copia;
    }

    public static <T> void ORDENAR_CRESCENTE(Unico<T> unico, Ordenavel<T> algoritmo_de_ordenacao) {

        int n = unico.getQuantidade();
        T temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                int ordem = algoritmo_de_ordenacao.emOrdem(unico.mLista.get(j - 1), unico.mLista.get(j));

                if (ordem == Ordenavel.MAIOR) {
                    temp = unico.mLista.get(j - 1);
                    unico.mLista.set(j - 1, unico.mLista.get(j));
                    unico.mLista.set(j, temp);
                }

            }
        }

    }

    public static <T> void ORDENAR_DECRESCENTE(Unico<T> unico, Ordenavel<T> algoritmo_de_ordenacao) {

        int n = unico.getQuantidade();
        T temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                int ordem = algoritmo_de_ordenacao.emOrdem(unico.mLista.get(j - 1), unico.mLista.get(j));

                if (ordem == Ordenavel.MENOR) {
                    temp = unico.mLista.get(j - 1);
                    unico.mLista.set(j - 1, unico.mLista.get(j));
                    unico.mLista.set(j, temp);
                }

            }
        }

    }


    public static <T> Unico<T> EMBARALHAR(Unico<T> lista) {


        for (int i = 0; i < lista.getQuantidade(); i++) {

            int p1 = Aleatorio.aleatorio(lista.getQuantidade());
            int p2 = Aleatorio.aleatorio(lista.getQuantidade());

            T c1 = lista.mLista.getValor(p1);
            T c2 = lista.mLista.getValor(p2);

            lista.mLista.setValor(p1, c2);
            lista.mLista.setValor(p2, c1);

        }


        return lista;
    }


}

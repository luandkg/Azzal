package libs.luan;

import java.util.Iterator;

public class ItemCiclicoIterador<T> implements Iterable<ItemCiclico<T>> {

    private Lista<T> mLista;
    private int mIndex;

    public ItemCiclicoIterador(Lista<T> eLista) {
        mLista = eLista;
        mIndex = 0;
    }


    @Override
    public Iterator<ItemCiclico<T>> iterator() {
        return new Iterator<ItemCiclico<T>>() {

            @Override
            public boolean hasNext() {
                return mIndex < mLista.getQuantidade();
            }

            @Override
            public ItemCiclico<T> next() {

                int pos_corrente = mIndex;

                int ant_indice = mIndex - 1;
                if (ant_indice < 0) {
                    ant_indice = mLista.getQuantidade() - 1;
                }

                mIndex += 1;

                int prox_indice = mIndex;
                if (prox_indice >= mLista.getQuantidade()) {
                    prox_indice = 0;
                }

                return new ItemCiclico<>(ant_indice, pos_corrente, prox_indice, mLista.getValor(ant_indice), mLista.getValor(pos_corrente), mLista.getValor(prox_indice));
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("no changes allowed");
            }
        };
    }
}

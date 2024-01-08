package libs.luan;

import java.util.Iterator;

public class Seq implements Iterable<Integer> {

    private int mMinimo;
    private int mMaximo;

    public Seq(int minimo, int maximo) {
        mMinimo = minimo;
        mMaximo = maximo;
    }


    @Override
    public Iterator<Integer> iterator() {

        RefInt indice = new RefInt(mMinimo);

        return new Iterator<Integer>() {

            @Override
            public boolean hasNext() {
                return indice.get() < mMaximo;
            }

            @Override
            public Integer next() {
                Integer objeto = indice.get();
                indice.somar(1);
                return objeto;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("no changes allowed");
            }
        };
    }


    public static Seq SEQUENCIE(int minimo, int maximo) {
        return new Seq(minimo, maximo);

    }
}

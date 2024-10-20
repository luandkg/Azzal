package libs.luan;

public class IndexadoComAnterior<T> {

    private int mIndexAnteriormente;
    private T mValorAnteriormente;
    private int mIndexCorrente;
    private T mValorCorrente;

    public IndexadoComAnterior(int indexAnteriormente, T valorAnteriormente,int indexCorrente, T valorCorrente) {
        mIndexAnteriormente = indexAnteriormente;
        mValorAnteriormente = valorAnteriormente;

        mIndexCorrente = indexCorrente;
        mValorCorrente = valorCorrente;
    }

    public T getCorrente() {
        return mValorCorrente;
    }

    public int indexCorrente() {
        return mIndexCorrente;
    }

    public void setIndexCorrente(int eIndex) {
        mIndexCorrente = eIndex;
    }



    public T getAnteriormente() {
        return mValorAnteriormente;
    }

    public int indexAnteriormente() {
        return mIndexAnteriormente;
    }
}

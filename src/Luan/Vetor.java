package Luan;

public class Vetor<T> {

    private T mDados[];
    private int mCapacidade;

    public Vetor(int eCapacidade) {

        mDados = (T[]) new Object[eCapacidade];
        mCapacidade = eCapacidade;

        if (mCapacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que 0 ");
        }

    }

    public int getCapacidade() {
        return mCapacidade;
    }


    public T get(int eIndice) {
        if (eIndice < 0) {
            throw new IllegalArgumentException("Indice nao existente : " + eIndice);
        } else if (eIndice > mCapacidade - 1) {
            throw new IllegalArgumentException("Indice nao existente : " + eIndice);
        }
        return mDados[eIndice];
    }

    public void set(int eIndice, T eValor) {
        if (eIndice < 0) {
            throw new IllegalArgumentException("Indice nao existente : " + eIndice);
        } else if (eIndice > mCapacidade - 1) {
            throw new IllegalArgumentException("Indice nao existente : " + eIndice);
        }
        mDados[eIndice] = eValor;
    }

    public void aumentar(int eMais){

        if (eMais <= 0) {
            throw new IllegalArgumentException("O aumento precisa ser um numero maior que 0 : " + eMais);
        }

        int eNovaCapacidade = mCapacidade+eMais;
        T mDadosMais[] = (T[]) new Object[eNovaCapacidade];

        for(int i=0;i<mCapacidade;i++){
            mDadosMais[i] = mDados[i];
        }

        mDados=mDadosMais;
        mCapacidade=eNovaCapacidade;

    }

    public void reduzir(int eMenos){

        if (eMenos <= 0) {
            throw new IllegalArgumentException("A reducao precisa ser um numero maior que 0 : " + eMenos);
        }

        int eNovaCapacidade = mCapacidade-eMenos;
        if (eNovaCapacidade <= 0) {
            throw new IllegalArgumentException("Essa reducao nao pode acontecer que o tamanho ficara menor ou igual a 0");
        }

        T mDadosMenos[] = (T[]) new Object[eNovaCapacidade];

        for(int i=0;i<eNovaCapacidade;i++){
            mDadosMenos[i] = mDados[i];
        }

        mDados=mDadosMenos;
        mCapacidade=eNovaCapacidade;

    }

}

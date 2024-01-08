package libs.ranking;

public class RankeadoDouble<T> {

    private String mNome;
    private T mObjeto;
    private double mRanking;

    public RankeadoDouble(String nome, double eRanking, T eObjeto) {
        mNome = nome;
        mRanking = eRanking;
        mObjeto = eObjeto;
    }

    public String getNome() {
        return mNome;
    }

    public double getRanking() {
        return mRanking;
    }

    public T getObjeto() {
        return mObjeto;
    }

}

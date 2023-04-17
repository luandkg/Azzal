package libs.ranking;

public class RankeadoInteiro<T> {

    private String mNome;
    private T mObjeto;
    private int mRanking;

    public RankeadoInteiro(String nome, int eRanking, T eObjeto){
        mNome=nome;
        mRanking=eRanking;
        mObjeto=eObjeto;
    }

    public String getNome(){return mNome;}
    public int getRanking(){return mRanking;}

    public T getObjeto(){return mObjeto;}

}

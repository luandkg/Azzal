package libs.acumulattor;

public class Acumulado {

    private String mNome;
    private int mQuantidade;

    public Acumulado (String eNome){
        mNome=eNome;
        mQuantidade=0;
    }

    public void mais(int v){
        mQuantidade+=v;
    }

    public void menos(int v){
        mQuantidade-=v;
    }
    public String getNome(){return mNome;}
    public int getQuantidade(){return mQuantidade;}

}

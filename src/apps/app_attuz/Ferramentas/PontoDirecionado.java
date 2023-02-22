package apps.app_attuz.Ferramentas;

public class PontoDirecionado {

    private int mDistancia;
    private int mValor;
    private String mDirecao;

    public PontoDirecionado(int eDistancia,int eValor,String eDirecao){
        mDistancia=eDistancia;
        mValor=eValor;
        mDirecao=eDirecao;
    }

    public int getDistancia(){return mDistancia;}
    public int getValor(){return mValor;}
    public String getDirecao(){return mDirecao;}

    public void mudar(int eDistancia,int eValor,String eDirecao){
        mDistancia=eDistancia;
        mValor=eValor;
        mDirecao=eDirecao;
    }

}

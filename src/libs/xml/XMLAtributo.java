package libs.xml;

public class XMLAtributo {

    private String mNome;
    private String mValor;

    public XMLAtributo(String eNome,String eValor){
        mNome=eNome;
        mValor=eValor;
    }

    public String getNome(){return mNome;}
    public String getValor(){return mValor;}

    public void setValor(String eValor){
        mValor=eValor;
    }
}

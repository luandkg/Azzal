package libs.xlsx;

public class Documento {

    private String mNome;
    private String mConteudo;


    public Documento(String eNome, String eConteudo) {
        mNome = eNome;
        mConteudo = eConteudo;
    }

    public String getNome() {
        return mNome;
    }

    public String getConteudo() {
        return mConteudo;
    }
}

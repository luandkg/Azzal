package libs.arquivos;

public class DocumentoTexto {

    private String mNome;
    private String mConteudo;


    public DocumentoTexto(String eNome, String eConteudo) {
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

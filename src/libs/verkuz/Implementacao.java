package libs.verkuz;

public class Implementacao {

    private String mArquivo;
    private String mGrupo;
    private String mNome;
    private String mData;

    public Implementacao(String eArquivo,String eGrupo, String eNome, String eData) {
        mArquivo = eArquivo;
        mGrupo=eGrupo;
        mNome = eNome;
        mData = eData;
    }

    public String getArquivo() {
        return mArquivo;
    }

    public String getGrupo() {
        return mGrupo;
    }

    public String getNome() {
        return mNome;
    }

    public String getData() {
        return mData;
    }

    public void setArquivo(String arquivo) {
        mArquivo = arquivo;
    }
}

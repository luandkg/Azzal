package libs.verkuz;

public class Implementacao {

    private String mArquivo;
    private String mFuncao;
    private String mData;

    public Implementacao(String eArquivo, String eFuncao, String eData) {
        mArquivo = eArquivo;
        mFuncao = eFuncao;
        mData = eData;
    }

    public String getArquivo() {
        return mArquivo;
    }

    public String getFuncao() {
        return mFuncao;
    }

    public String getData() {
        return mData;
    }

    public void setArquivo(String arquivo) {
        mArquivo = arquivo;
    }
}

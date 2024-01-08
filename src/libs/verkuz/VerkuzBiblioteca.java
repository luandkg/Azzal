package libs.verkuz;

public class VerkuzBiblioteca {

    private String mArquivo;
    private long mTamanho = 0;

    public VerkuzBiblioteca(String eArquivo, long eTamanho) {
        mArquivo = eArquivo;
        mTamanho = eTamanho;
    }

    public String getArquivo() {
        return mArquivo;
    }

    public long getTamanho() {
        return mTamanho;
    }


    public void setArquivo(String arquivo) {
        mArquivo = arquivo;
    }

    public void setTamanho(long t) {
        mTamanho = t;
    }
}

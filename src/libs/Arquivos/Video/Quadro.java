package libs.Arquivos.Video;


import libs.Arquivos.Binario.Arquivador;

public class Quadro {

    private Arquivador mArquivo;
    private int mIndice;
    private long mInicio;

    public Quadro(Arquivador eArquivo, int eIndice, long eInicio) {
        mArquivo = eArquivo;
        mIndice = eIndice;
        mInicio = eInicio;

    }

    public int getIndex() {
        return mIndice;
    }

    public long getInicio() {
        return mInicio;
    }

    public long getFim() {
        return mInicio + 8;
    }

    public long getConteudo() {

        mArquivo.setPonteiro(mInicio);

        return mArquivo.readLong();
    }
}

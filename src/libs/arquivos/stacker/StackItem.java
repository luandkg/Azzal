package libs.arquivos.stacker;

import java.nio.charset.StandardCharsets;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;

public class StackItem {

    private int mIndex;

    private String mArquivo;
    private int mStatus;
    private String mNome;
    private long mTamanho;
    private long mInicio;

    public StackItem(String eArquivo, int eIndex, int eStatus, String eNome, long eTamanho, long eInicio) {

        mArquivo = eArquivo;
        mIndex = eIndex;
        mStatus = eStatus;
        mNome = eNome;
        mTamanho = eTamanho;
        mInicio = eInicio;

    }


    public String getArquivo() {
        return mArquivo;
    }

    public int getIndex() {
        return mIndex;
    }

    public int getStatus() {
        return mStatus;
    }

    public String getNome() {
        return mNome;
    }

    public long getTamanho() {
        return mTamanho;
    }

    public long getInicio() {
        return mInicio;
    }


    public byte[] getBytes() {

        Arquivador arquivar = new Arquivador(mArquivo);
        arquivar.setPonteiro(mInicio);

        byte[] dados = new byte[(int) mTamanho];

        for (int b = 0; b < mTamanho; b++) {
            dados[b] = arquivar.get();
        }

        arquivar.encerrar();

        return dados;
    }

    public int[] getInts() {

        Arquivador arquivar = new Arquivador(mArquivo);
        arquivar.setPonteiro(mInicio);

        int[] dados = new int[(int) mTamanho];

        for (int b = 0; b < mTamanho; b++) {
            dados[b] = Inteiro.byteToInt(arquivar.get());
        }

        arquivar.encerrar();

        return dados;
    }

    public String getTexto() {
        return new String(getBytes(), StandardCharsets.UTF_8);
    }
}

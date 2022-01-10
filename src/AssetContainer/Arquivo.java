package AssetContainer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.GZIPInputStream;

public class Arquivo {

    private Ponto mPonto;
    private AssetContainer mAssetContainer;
    private String mNomeCompleto;

    private Referencia mReferencia;

    private long mTamanho;
    private long mTamanhoDescompactado;

    private boolean mLido;

    public Arquivo(AssetContainer eAssetContainer, Referencia eReferencia, Ponto ePonto) {

        mAssetContainer = eAssetContainer;
        mReferencia = eReferencia;
        mPonto = ePonto;
        mNomeCompleto = "";

        mTamanho = 0;
        mTamanhoDescompactado = 0;
        mLido = false;

    }

    public Referencia getReferencia() {
        return mReferencia;
    }


    public String getNome() {
        return mPonto.getNome();
    }

    public long getTipo() {
        return mPonto.getTipo();
    }

    public long getInicio() {
        return mPonto.getInicio();
    }

    public long getFim() {
        return mPonto.getFim();
    }

    public long getTamanho() {
        if (!mLido) {
            getBytes();
        }
        return mTamanho;
    }

    public long getTamanhoDescompactado() {
        if (!mLido) {
            getBytes();
        }
        return mTamanhoDescompactado;
    }


    public String getNomeCompleto() {
        return mNomeCompleto;
    }

    public void setNomeCompleto(String eNomeCompleto) {
        mNomeCompleto = eNomeCompleto;
    }


    public void exportar(String eLocal) {
        try {


            RandomAccessFile mLendo = new RandomAccessFile(new File(mAssetContainer.getArquivo()), "r");
            mLendo.seek(getInicio());


            RandomAccessFile mExportando = new RandomAccessFile(new File(eLocal), "rw");

            long mAquivandoIndex = 0;
            long mAquivandoTamanho = this.getTamanho();

            mExportando.seek(0);

            while (mAquivandoIndex < mAquivandoTamanho) {

                mExportando.writeByte(mLendo.readByte());

                mAquivandoIndex += 1;
            }

            mExportando.close();
            mLendo.close();

        } catch (IOException e) {

        }


    }

    public byte[] getBytes() {

        long mTam = this.getFim() - this.getInicio();

        mTamanho = 0;
        mTamanhoDescompactado = 0;

        byte[] mBytes = new byte[(int) mTam];

        if (mTam > 0) {

            try {


                RandomAccessFile mLendo = new RandomAccessFile(new File(mAssetContainer.getArquivo()), "r");
                mLendo.seek(getInicio());


                long mAquivandoIndex = 0;
                long mAquivandoTamanho = mTam;


                while (mAquivandoIndex < mAquivandoTamanho) {

                    mBytes[(int) mAquivandoIndex] = mLendo.readByte();

                    mAquivandoIndex += 1;
                }

                mLendo.close();

                mTamanho = mBytes.length;
                mTamanhoDescompactado = mBytes.length;
                mLido = true;

            } catch (IOException e) {

            }

            if (mAssetContainer.getCabecalho().contentEquals(AssetCreator.ASSET_CONTAINER_COMPRESSED)) {

                try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mBytes)) {

                    GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);

                    mBytes = gzipInputStream.readAllBytes();

                } catch (IOException e) {
                    throw new RuntimeException("Failed to unzip content", e);
                }

                mTamanhoDescompactado = mBytes.length;

            }

        }


        mLido = true;

        return mBytes;
    }

}

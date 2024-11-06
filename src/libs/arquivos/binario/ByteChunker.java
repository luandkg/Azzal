package libs.arquivos.binario;

import libs.luan.Lista;
import libs.luan.Matematica;

public class ByteChunker {

    private Lista<ByteChunk> mChunks;

    private long mCapacidade;
    private long mPonteiro;

    public ByteChunker() {
        mChunks = new Lista<ByteChunk>();
        mCapacidade = 0;
        mPonteiro = 0;
    }

    public long getTamanho(){return mPonteiro;}

    public long getCapacidade(){return mCapacidade;}

    public Lista<ByteChunk> getChunks(){return mChunks;}


    public void set_u8(byte b) {

        if (mChunks.estaVazia()) {
            mChunks.adicionar(new ByteChunk(Matematica.KB(4)));
            mCapacidade = calcularCapacidade();
        }

        if (mPonteiro == mCapacidade) {
            mChunks.adicionar(new ByteChunk(Matematica.KB(4)));
            mCapacidade = calcularCapacidade();
        }


        long indo = 0;
        for (ByteChunk chunk : mChunks) {
            if (mPonteiro  < indo + chunk.getLength()) {

                long pos = mPonteiro - indo;
                chunk.set((int) pos, b);

                break;
            }
            indo += chunk.getLength();
        }

        mPonteiro += 1;

    }



    public void set_u32(int valor){

        set_u8((byte) (valor >>> 24));
        set_u8((byte) (valor >>> 16));
        set_u8((byte) (valor >>> 8));
        set_u8((byte) (valor ));


    }

    public long calcularCapacidade() {
        long capacidade = 0;

        for (ByteChunk chunk : mChunks) {
            capacidade += (long) chunk.getLength();
        }

        return capacidade;
    }

}

package libs.arquivos.binario;


public class ByteChunk {

    private byte[] mBytes;
    private int mLength;

    private int mPonteiro;

    public ByteChunk(int eTamanho) {
        mBytes = new byte[eTamanho];
        mLength = eTamanho;

        mPonteiro=0;
    }

    public ByteChunk(byte[] eBytes, int eTamanho) {
        mBytes = eBytes;
        mLength = eTamanho;
        mPonteiro=0;
    }

    public byte[] getChunk() {
        return mBytes;
    }

    public void setLength(int v) {
        mLength = v;
    }

    public int getLength() {
        return mLength;
    }

    public ByteChunk getCopy(int len) {
       ByteChunk copia = new ByteChunk(mLength);
        for (int v = 0; v < mLength; v++) {
            copia.getChunk()[v] = mBytes[v];
        }
        copia.setLength(len);
        return copia;
    }


    public byte get(){
        byte b = mBytes[mPonteiro];
        mPonteiro+=1;
        return b;
    }

    public int get_u32() {
        int i  = ((get() & 0xFF) << 24) |
                    ((get() & 0xFF) << 16) |
                    ((get() & 0xFF) << 8) |
                    ((get() & 0xFF) << 0);

        return i;
    }


}

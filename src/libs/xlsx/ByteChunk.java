package libs.xlsx;


public class ByteChunk {
    private byte[] mBytes;
    private int mLength;

    public ByteChunk(int eTamanho) {
        mBytes = new byte[eTamanho];
        mLength = eTamanho;
    }

    public ByteChunk(byte[] eBytes, int eTamanho) {
        mBytes = eBytes;
        mLength = eTamanho;
    }

    public byte[] get() {
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
            copia.get()[v] = mBytes[v];
        }
        copia.setLength(len);
        return copia;
    }


}

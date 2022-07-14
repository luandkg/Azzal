package apps.appArch;

public class Memoria {

    private byte mDados[];
    private int mCapacidade;

    public Memoria() {
        mCapacidade = 14 * 1024;
        mDados = new byte[mCapacidade];
        for (int i = 0; i < mCapacidade; i++) {
            mDados[i] = (byte) 0;
        }
    }

    public int getCapacidade() {
        return mCapacidade;
    }

    public void set(int index, byte valor) {
        mDados[index] = valor;
    }

    public void set(I16 index, byte valor) {

        int iByte1 = (index.getByte1() & 0xFF);
        int iByte2 = (index.getByte2() & 0xFF);

        int eTotal = (iByte1 *256) + iByte2;

        mDados[eTotal] = valor;
    }

    public byte get(int index) {
        return mDados[index];
    }

    public byte get(I16 index) {

        int iByte1 = (index.getByte1() & 0xFF);
        int iByte2 = (index.getByte2() & 0xFF);

        int eTotal = (iByte1 *256) + iByte2;

        return mDados[eTotal];
    }

    public I8 getI8(I16 index) {
        int iByte1 = (index.getByte1() & 0xFF);
        int iByte2 = (index.getByte2() & 0xFF);

        int eTotal = (iByte1 *256) + iByte2;
        return new I8(mDados[eTotal]);
    }

}

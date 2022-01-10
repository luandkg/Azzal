package Extenum.Arquivador;

import Extenum.Stringer;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Bloco {

    private RandomAccessFile mFile;
    private Arquivador mArquivador;

    private Utils mUtils;

    private long mID;

    private long mInicio;
    private long mFim;

    public Bloco(long eID, RandomAccessFile eFile, Arquivador eArquivador, Utils eUtils, long eInicio, long eFim) {

        mID = eID;

        mFile = eFile;
        mArquivador = eArquivador;
        mUtils = eUtils;

        mInicio = eInicio;
        mFim = eFim;

    }

    public long getID() {
        return mID;
    }

    public Arquivador getArquivador() {
        return mArquivador;
    }

    public RandomAccessFile getFile() {
        return mFile;
    }

    public long getInicio() {
        return mInicio;
    }

    public long getFim() {
        return mFim;
    }

    public long getTamanho() {
        return mFim - mInicio;
    }

    public Utils getUtils() {
        return mUtils;
    }

    public void writeBoolean(long p, boolean e) {

        mUtils.setPonteiro(mInicio + p);
        mUtils.writeBoolean(e);

    }

    public void writeLong(long p, long e) {

        mUtils.setPonteiro(mInicio + p);
        mUtils.writeLong(e);

    }

    public long readLong(long p) {

        mUtils.setPonteiro(mInicio + p);
        return mUtils.readLong();

    }

    public byte readByte(long p) {

        mUtils.setPonteiro(mInicio + p);
        return mUtils.readByte();

    }

    public void writeByte(long p, byte b1) {

        mUtils.setPonteiro(mInicio + p);
        mUtils.writeByte(b1);

    }

    public boolean readBoolean(long p) {

        mUtils.setPonteiro(mInicio + p);
        return mUtils.readBoolean();

    }

    public void guardarObjeto(long p, String s) {

        Stringer ms = new Stringer();

        byte b[] = ms.stringToBytes(s);

        int i = 0;
        int o = s.length();

        mUtils.setPonteiro(mInicio + p);

        while (i < o) {

            mUtils.writeByte(b[i]);

            i += 1;
        }

        mUtils.writeByte((byte) 0);

    }

    public String lerObjeto(long p) {

        mUtils.setPonteiro(mInicio + p);

        int tamanho = (4 * 1024);
        int index = 0;

        byte result[] = new byte[tamanho];

        while (index < tamanho) {

            try {
                result[index] = mFile.readByte();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            index += 1;

        }

        Stringer ms = new Stringer();

        return ms.readString(result);

    }

}

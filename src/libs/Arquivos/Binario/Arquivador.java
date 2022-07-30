package libs.Arquivos.Binario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class Arquivador {

    private RandomAccessFile mFile;
    private String mArquivo;

    public Arquivador(String eArquivo) {
        mArquivo = eArquivo;

        try {
            mFile = new RandomAccessFile(new File(eArquivo), "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Arquivador(String eArquivo, String modo) {
        mArquivo = eArquivo;

        try {
            mFile = new RandomAccessFile(new File(eArquivo), modo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void remover(String eArq) {

        File Arq = new File(eArq);
        if (Arq.exists()) {
            Arq.delete();
        }

    }




    public byte[] longToBytes(long l) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte) (l & 0xFF);
            l >>= 8;
        }
        return result;
    }

    public byte[] intToByte(int value) {
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value};
    }

    int bytestoInt(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8) |
                ((bytes[3] & 0xFF) << 0);
    }


    public long readLong() {
        long result = 0;
        try {

            byte[] lendo = new byte[8];
            for (int i = 0; i < 8; i++) {
                lendo[i] = mFile.readByte();
            }
            for (int i = 0; i < 8; i++) {
                result <<= Long.BYTES;
                result |= (lendo[i] & 0xFF);
            }

        } catch (IOException e) {

        }
        return result;
    }

    public int readInt() {
        int i = 0;


        try {

            i = ((mFile.readByte() & 0xFF) << 24) |
                    ((mFile.readByte() & 0xFF) << 16) |
                    ((mFile.readByte() & 0xFF) << 8) |
                    ((mFile.readByte() & 0xFF) << 0);


        } catch (IOException e) {

        }


        return i;
    }


    public String organizar(byte b) {
        String si = String.valueOf(b);

        if (si.length() == 1) {
            si = "00" + si;
        } else if (si.length() == 2) {
            si = "0" + si;
        }

        return si;
    }

    public int organizar_to_int(byte b) {
        String si = String.valueOf(b);

        return Integer.parseInt(si);
    }

    public void limpar() {

        try {

            long tamanho = mFile.length();
            long index = 0;

            mFile.seek(0);

            while (index < tamanho) {
                mFile.write(0);
                index += 1;

            }
        } catch (IOException e) {

        }

    }

    public void writeInt(int l) {

        try {
            mFile.write(intToByte(l));
        } catch (IOException e) {

        }

    }

    public void writeInt16(int l) {

        int v1 = l / 255;
        int v2 = l - (v1 * 255);


        try {
            mFile.write((byte) v1);
            mFile.write((byte) v2);
        } catch (IOException e) {

        }
    }


    public void writeLong(long l) {

        try {
            mFile.write(longToBytes(l));
        } catch (IOException e) {

        }

    }


    public long getLength() {
        try {
            return mFile.length();
        } catch (IOException e) {
            return -1;
        }
    }


    public void inicio() {
        try {
            mFile.seek(0);
        } catch (IOException e) {

        }
    }

    public byte readByte() {
        try {
            return mFile.readByte();
        } catch (IOException e) {
            return -1;
        }
    }

    public void writeByte(Byte eByte) {
        try {
            mFile.writeByte(eByte);
        } catch (IOException e) {

        }
    }

    public void writeByteArray(ArrayList<Byte> dados) {
        try {
            for (Byte b : dados) {
                mFile.writeByte((byte) b);
            }
        } catch (IOException e) {

        }

    }


    public void writeByteRepetidos(int quantidade, Byte byte_v) {
        try {
            for (int i = 0; i < quantidade; i++) {
                mFile.writeByte((byte) byte_v);
            }
        } catch (IOException e) {

        }

    }

    public void writeByteRepetidos(long quantidade, Byte byte_v) {
        try {
            for (long i = 0; i < quantidade; i++) {
                mFile.writeByte((byte) byte_v);
            }
        } catch (IOException e) {

        }

    }

    public long getPonteiro() {
        try {
            return mFile.getFilePointer();
        } catch (IOException e) {
            return -1;
        }
    }


    public void setPonteiro(long p) {
        try {
            mFile.seek(p);
        } catch (IOException e) {
        }
    }

    public ArrayList<Byte> readBytes(int eQuantidade) {

        ArrayList<Byte> bytes = new ArrayList<Byte>();

        try {
            for (int i = 0; i < eQuantidade; i++) {
                bytes.add(mFile.readByte());
            }
        } catch (IOException e) {

        }

        return bytes;
    }

    public void readBufferBytes(byte[] buff,int eQuantidade) {

        try {
            mFile.read(buff,0,eQuantidade);
        } catch (IOException e) {

        }

    }

    public void fechar() throws IOException {
        mFile.close();
    }

    public void encerrar() {

        try {
            mFile.close();
        } catch (IOException e) {

        }

    }


}

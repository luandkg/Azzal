package libs.arquivos.binario;

import libs.luan.Lista;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


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


    public static long GET_TAMANHO(String arquivo) {
        Arquivador arq = new Arquivador(arquivo);
        long tamanho = arq.getLength();
        arq.encerrar();
        return tamanho;
    }

    public static void CONSTRUIR_ARQUIVO(String arquivo,byte[] bytes) {

        Arquivador.remover(arquivo);

        Arquivador arq = new Arquivador(arquivo);
arq.setPonteiro(0);
for(byte b : bytes){
    arq.set_u8(b);
}
        arq.encerrar();
    }

    public static byte[] GET_BYTES(String arquivo) {
        int i = 0;
int o =(int)GET_TAMANHO(arquivo);

        byte[] bytes = new byte[o];

        Arquivador arq = new Arquivador(arquivo);
        arq.setPonteiro(0);


        while(i<o){
            bytes[i] = arq.get();
            i+=1;
        }

        arq.encerrar();


        return bytes;
    }



    public byte get() {
        try {
            return mFile.readByte();
        } catch (IOException e) {
            return -1;
        }
    }


    public int get_u8() {
        try {
            byte b = mFile.readByte();

            if (b >= 0) {
                return (int) b;
            } else {
                return 256 + (int) b;
            }

        } catch (IOException e) {
            return 0;
        }
    }

    public long get_u64() {
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

    public int get_u32() {
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

    public void set_u32(int l) {

        try {
            mFile.write(Bytes.intToByte(l));
        } catch (IOException e) {

        }

    }

    public void set_u16(int l) {

        int v1 = l / 255;
        int v2 = l - (v1 * 255);

        try {
            mFile.write((byte) v1);
            mFile.write((byte) v2);
        } catch (IOException e) {

        }
    }


    public void set_u64(long l) {

        try {
            mFile.write(Bytes.longToBytes(l));
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


    public void set_u8(Byte eByte) {
        try {
            mFile.writeByte(eByte);
        } catch (IOException e) {

        }
    }

    public void set_u8_array(Lista<Byte> dados) {
        try {
            for (Byte b : dados) {
                mFile.writeByte((byte) b);
            }
        } catch (IOException e) {

        }

    }

    public void set_u8_lista(Lista<Byte> dados) {
        try {
            for (Byte b : dados) {
                mFile.writeByte((byte) b);
            }
        } catch (IOException e) {

        }

    }

    public void set_u8_array(byte[] dados) {
        try {
            mFile.write(dados);
        } catch (IOException e) {

        }

    }


    public void set_u8_em_bloco(int quantidade, Byte byte_v) {
        try {
            for (int i = 0; i < quantidade; i++) {
                mFile.writeByte((byte) byte_v);
            }
        } catch (IOException e) {

        }

    }

    public void set_u8_em_bloco(long quantidade, Byte byte_v) {
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

    public Lista<Byte> get_u8_bloco(int eQuantidade) {

        Lista<Byte> bytes = new Lista<Byte>();

        try {
            for (int i = 0; i < eQuantidade; i++) {
                bytes.adicionar(mFile.readByte());
            }
        } catch (IOException e) {

        }

        return bytes;
    }


    public byte[] get_u8_array(int eQuantidade) {

        byte[] bytes = new byte[eQuantidade];

        try {
            for (int i = 0; i < eQuantidade; i++) {
                bytes[i]=mFile.readByte();
            }
        } catch (IOException e) {

        }

        return bytes;
    }

    public void get_u8_em_bloco(byte[] buff, int eQuantidade) {

        try {
            mFile.read(buff, 0, eQuantidade);
        } catch (IOException e) {

        }

    }


    public void set_double(double valor) {

        try {
            mFile.writeDouble(valor);
        } catch (IOException e) {

        }

    }

    public double get_double( ) {

        try {
           return mFile.readDouble();
        } catch (IOException e) {

        }

        return 0;
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

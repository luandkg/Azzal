package libs.Extenum.Arquivador;


import libs.Luan.TTY;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Arquivador {

    private String mArquivo;
    private RandomAccessFile mFile;
    private Utils mUtils;

    private long mPonteiroInicializado;
    private long mPonteiroValorizador;
    private long mPonteiroDados;


    private final int BLOCO_TAMANHO = 4 * 1024;

    public Arquivador(String eArquivo) {

        mArquivo = eArquivo;
        mFile = null;
        mPonteiroInicializado = 0;
        mPonteiroValorizador = 0;
        mPonteiroDados = 0;


        try {

            mFile = new RandomAccessFile(new File(mArquivo), "rw");

            mUtils = new Utils(mFile);

            iniciar();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Utils getUtils() {
        return mUtils;
    }


    private void iniciar() {

        try {
            mFile.seek(0);
            //   System.out.println(" :: Tam = " + mFile.length());
            // System.out.println(" :: PTR = " + mUtils.getPonteiro());

            if (mFile.length() == 0) {

                mFile.writeByte((byte) 69);
                mFile.writeByte((byte) 88);
                mFile.writeByte((byte) 84);

                mUtils.writeByte((byte) 0);

                mUtils.writeLong(0);
                mUtils.writeLong(0);

            }

            mFile.seek(0);

            int i1 = getByteInt(mFile.readByte());
            int i2 = getByteInt(mFile.readByte());
            int i3 = getByteInt(mFile.readByte());

            // System.out.println(i1 + " " + i2 + " " + i3);

            if (i1 == 69 && i2 == 88 && i3 == 84) {

                mPonteiroInicializado = mUtils.getPonteiro();

                int pi = getByteInt(mFile.readByte());

                mPonteiroValorizador = mUtils.getPonteiro();

                long v1 = mUtils.readLong();

                mPonteiroDados = mUtils.getPonteiro();


                //System.out.println(" :: PI = " + mPonteiroInicializado);
                // System.out.println(" :: PV = " + mPonteiroValorizador);
                // System.out.println(" :: PD = " + mPonteiroDados);

            } else {
                throw new IllegalArgumentException("libs.Extenum corrompido !");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public long getConstrutor() {
        mUtils.setPonteiro(mPonteiroValorizador);
        return mUtils.readLong();
    }


    public void setValorizador(long v) {

        mUtils.setPonteiro(mPonteiroInicializado);
        mUtils.writeByte((byte) 1);

        mUtils.setPonteiro(mPonteiroValorizador);
        mUtils.writeLong(v);

    }

    public boolean temConstrutor() {

        mUtils.setPonteiro(mPonteiroInicializado);

        int pi = -1;
        try {
            pi = getByteInt(mFile.readByte());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pi == 1;
    }


    public void formatar() {

        try {

            System.out.println(" -->> Formatando...");

            mFile.seek(0);

            mFile.writeByte((byte) 69);
            mFile.writeByte((byte) 88);
            mFile.writeByte((byte) 84);

            mUtils.writeByte((byte) 0);
            mUtils.writeLong(0);


            System.out.println(" -->> Formatado !");

            iniciar();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void expanda() {


        try {

            mUtils.setPonteiro(mPonteiroInicializado);

            int pi = getByteInt(mFile.readByte());


            //  System.out.println(" -->> Comecar expansao...");

            mUtils.setPonteiro(mFile.length());

            //  System.out.println(" :: E1 = " + mUtils.getPonteiro());

            long g = mUtils.getPonteiro();

            for (int a = 0; a < BLOCO_TAMANHO; a++) {
                mFile.writeByte((byte) 0);
            }

            long f = mUtils.getPonteiro();

            //  System.out.println(" :: E2 = " + mUtils.getPonteiro());

            //  System.out.println(" :: E3 = " + (f - g));

            if (pi == 0) {

                //  System.out.println(" :: Primeiro Bloco");

                mUtils.setPonteiro(mPonteiroInicializado);
                mUtils.writeByte((byte) 1);
                mUtils.setPonteiro(mPonteiroValorizador);
                mUtils.writeLong(0);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void fechar() {
        try {
            mFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public long getBlocosContagem() {

        long eBlocos = 0;
        try {
            eBlocos = (mFile.length() - mPonteiroDados) / BLOCO_TAMANHO;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return eBlocos;
    }


    private int getByteInt(byte b) {

        if (b >= 0) {
            return (int) b;
        } else {
            return 256 + (int) b;
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


    public Bloco getBloco(long n) {
        if (n < getBlocosContagem()) {

            long p1 = mPonteiroDados + (n * BLOCO_TAMANHO);
            long p2 = p1 + BLOCO_TAMANHO;

            return new Bloco(n, mFile, this, mUtils, p1, p2);

        } else {
            return null;
        }
    }

    public void dump_blocos() {

        TTY eTTY = new TTY();

        long eContagem = this.getBlocosContagem();

        System.out.println("");
        System.out.println("-->> Blocos   =   " + eContagem);
        System.out.println("");

        for (long b = 0; b < eContagem; b++) {

            Bloco blocoC = this.getBloco(b);

            String s0 = " Bloco = " + eTTY.LongNum(blocoC.getID(), 5);

            String s1 = " Inicio = " + eTTY.LongNum(blocoC.getInicio(), 10);
            String s2 = " Fim = " + eTTY.LongNum(blocoC.getFim(), 10);
            String s3 = " Tamanho = " + eTTY.LongNum(blocoC.getTamanho(), 5);

            System.out.println("\t-->> "  + s0 + s1 + s2 + s3);

        }

    }


}

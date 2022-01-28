package Arquivos.Video;

import Arquivos.Binario.Arquivador;

import java.util.ArrayList;

public class Arena {

    private Arquivador mArquivo;
    private long mLocal;

    public Arena(Arquivador eArquivo, long eLocal) {

        mArquivo = eArquivo;
        mLocal = eLocal;

    }

    public long getPonteiro() {
        return mLocal;
    }

    public long getAnterior() {
        mArquivo.setPonteiro(mLocal + 1);
        return mArquivo.readLong();
    }

    public long getProximo() {
        mArquivo.setPonteiro(mLocal + 9);
        return mArquivo.readLong();
    }

    public ArrayList<Quadro> getQuadros() {

        ArrayList<Quadro> mQuadros = new ArrayList<Quadro>();

        int eContador = 0;

        mArquivo.setPonteiro(mLocal + 18);

        for (int i = 0; i < 100; i++) {

            long eAntes = mArquivo.getPonteiro();
            mQuadros.add(new Quadro(mArquivo, i, eAntes));
            mArquivo.setPonteiro(eAntes);

            long eFramePonteiro = mArquivo.readLong();
            eContador += 1;

        }

        return mQuadros;

    }

    public int getFramesContagem() {

        int eContador = 0;

        mArquivo.setPonteiro(mLocal + 18);

        for (int i = 0; i < 100; i++) {

            long eFramePonteiro = mArquivo.readLong();
            eContador += 1;


        }

        return eContador;


    }

    public int getFramesUsadosContagem() {

        int eContador = 0;

        mArquivo.setPonteiro(mLocal + 18);

        for (int i = 0; i < 100; i++) {

            long eFramePonteiro = mArquivo.readLong();
            if (eFramePonteiro != 0) {
                eContador += 1;
            }

        }

        return eContador;


    }

    public int getFramesLivreContagem() {

        int eContador = 0;

        mArquivo.setPonteiro(mLocal + 18);

        for (int i = 0; i < 100; i++) {

            long eFramePonteiro = mArquivo.readLong();
            if (eFramePonteiro == 0) {
                eContador += 1;
            }

        }

        return eContador;


    }

    public void guardar(long e) {

        mArquivo.setPonteiro(mLocal + 18);

        boolean guardou = false;

        for (int i = 0; i < 100; i++) {

            long ePonteiroAntes = mArquivo.getPonteiro();


            long eFramePonteiro = mArquivo.readLong();

            if (eFramePonteiro == 0) {


                mArquivo.setPonteiro(ePonteiroAntes);
                mArquivo.writeLong(e);
                guardou = true;



                break;
            } else {
              //  System.out.println(i + " de 100 ocupado com " + eFramePonteiro);
            }

            mArquivo.setPonteiro(ePonteiroAntes+8);


        }

        if (!guardou) {
            System.out.println("Falta de pagina no quadro detectada !");
        }


    }


}

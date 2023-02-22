package libs.gama_fs;

import libs.arquivos.binario.Arquivador;

public class Cluster {

    private Arquivador mArquivador;
    private GamaFS mGamaFS;
    private long mClusterID;

    public Cluster(GamaFS eGamaFS, Arquivador eArquivador, long eClusterID) {
        mGamaFS = eGamaFS;
        mArquivador = eArquivador;
        mClusterID = eClusterID;
    }

    public byte readByte(int pos) {

        if (pos >= 0 && pos < (4 * 1024)) {

            long ler =  (mClusterID * (4 * 1024) + pos);
           // System.out.println("Ler em :: " + ler);

            mArquivador.setPonteiro(ler);
            return mArquivador.get();
        }

        return (byte) 0;
    }
}

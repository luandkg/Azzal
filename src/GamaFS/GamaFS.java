package GamaFS;

import Arquivos.Binario.Arquivador;
import Arquivos.Binario.Inteiro;

public class GamaFS {

    public static void criar(String eArquivo, long eTamanho) {

        Arquivador.remover(eArquivo);

        Arquivador arquivador = new Arquivador(eArquivo);

        arquivador.writeByteRepetidos(eTamanho, (byte) 0);


        arquivador.encerrar();

    }

    public static void zerar(String eArquivo) {

        Arquivador arquivador = new Arquivador(eArquivo);

        long tamanho = arquivador.getLength();

        arquivador.setPonteiro(0);
        arquivador.writeByteRepetidos(tamanho, (byte) 0);

        arquivador.encerrar();

    }

    public static void formatar(String eArquivo) {

        Arquivador arquivador = new Arquivador(eArquivo);

        byte CLUSTER_LIVRE = (byte) 0;
        byte CLUSTER_OCUPADO = (byte) 255;

        long tamanho = arquivador.getLength();
        long cluster = 4 * 1024;

        arquivador.setPonteiro(0);

        arquivador.writeByte((byte) 120);
        arquivador.writeByte((byte) 15);
        arquivador.writeByte((byte) 60);

        long blocos = (tamanho / cluster);

        arquivador.writeByte((byte) 100);
        arquivador.writeLong(blocos);
        arquivador.writeByte((byte) 100);

        long cluter_inicio = arquivador.getPonteiro();

        for (int b = 0; b < blocos; b++) {
            arquivador.writeByte(CLUSTER_LIVRE);
        }

        long cluter_fim = arquivador.getPonteiro();

        arquivador.writeByte((byte) 100);

        long dados_inicio = arquivador.getPonteiro();

        long posicao = arquivador.getPonteiro();
        long ocupados = (posicao / cluster) + 1;

        long cluster_raiz = arquivador.getPonteiro();

        arquivador.writeLong(0);

        arquivador.writeByte((byte) 100);

        long pos_dados = arquivador.getPonteiro();

        System.out.println("Dados :: " + pos_dados);



        // MARCAR OCUPADOS
        arquivador.setPonteiro(cluter_inicio);
        for (int b = 0; b < ocupados; b++) {
            arquivador.writeByte(CLUSTER_OCUPADO);
        }


        // PEGAR PRIMEIRO CLUSTER LIVRE E DEFINIR COMO RAIZ
        arquivador.setPonteiro(cluter_inicio);
        long cluster_raiz_id = -1;
        for (long i = 0; i < blocos; i++) {

            int status = Inteiro.byteToInt(arquivador.readByte());
            if (status == 0) {
                cluster_raiz_id = i;
                break;
            }
        }

        if (cluster_raiz_id > 0) {

            arquivador.setPonteiro(cluter_inicio + cluster_raiz_id);
            arquivador.writeByte(CLUSTER_OCUPADO);

            arquivador.setPonteiro(cluster_raiz);
            arquivador.writeLong(cluster_raiz_id);

            long esc =  (cluster_raiz_id * (4 * 1024));

            System.out.println("Escrever em :: " +esc );
            arquivador.setPonteiro((cluster_raiz_id * (4 * 1024)));
            arquivador.writeByte((byte)150);
            arquivador.writeByte((byte)80);
            arquivador.writeByte((byte)30);

        }

        arquivador.encerrar();

    }


    private Arquivador mArquivador;

    private long MAPA_CLUSTERS_INICIO;
    private long MAPA_CLUSTERS_FIM;
    private long MAPA_CLUSTERS_TAMANHO;
    private long DADOS_INICO;

    public GamaFS(String eArquivo) {

        mArquivador = new Arquivador(eArquivo);

        byte CLUSTER_LIVRE = (byte) 0;
        byte CLUSTER_OCUPADO = (byte) 255;

        long tamanho = mArquivador.getLength();
        long cluster = 4 * 1024;

        mArquivador.setPonteiro(0);

        int I1 = Inteiro.byteToInt(mArquivador.readByte());
        int I2 = Inteiro.byteToInt(mArquivador.readByte());
        int I3 = Inteiro.byteToInt(mArquivador.readByte());

        System.out.println("GAMA :: " + (I1) + "::" + (I2) + "::" + (I3));

        int I4 = Inteiro.byteToInt(mArquivador.readByte());

        MAPA_CLUSTERS_TAMANHO = mArquivador.readLong();

        int I5 = Inteiro.byteToInt(mArquivador.readByte());

        System.out.println("\t - Clusters = " + MAPA_CLUSTERS_TAMANHO);

        MAPA_CLUSTERS_INICIO = mArquivador.getPonteiro();
        MAPA_CLUSTERS_FIM = MAPA_CLUSTERS_INICIO + MAPA_CLUSTERS_TAMANHO;

        System.out.println("\t - C. Inicio = " + MAPA_CLUSTERS_INICIO);
        System.out.println("\t - C. Fim    = " + MAPA_CLUSTERS_FIM);

        mArquivador.setPonteiro(MAPA_CLUSTERS_FIM);

        int I6 = Inteiro.byteToInt(mArquivador.readByte());

        //  System.out.println("\t - I6 :: " + I6);

        long raiz = mArquivador.readLong();

        int I7 = Inteiro.byteToInt(mArquivador.readByte());

        DADOS_INICO=mArquivador.getPonteiro();

        System.out.println("\t - Raiz     = " + raiz);
        System.out.println("\t - Dados    = " + DADOS_INICO);


        System.out.println("\t - Clusters Livres      = " + getLivres());
        System.out.println("\t - Clusters Ocupados    = " + getOcupados());

        Raiz eRaiz = new Raiz(this, mArquivador, raiz);

    }

    public long getLivres() {
        long s = 0;

        mArquivador.setPonteiro(MAPA_CLUSTERS_INICIO);

        for (long i = 0; i < MAPA_CLUSTERS_TAMANHO; i++) {

            int status = Inteiro.byteToInt(mArquivador.readByte());
            if (status == 0) {
                s += 1;
            }
        }


        return s;
    }

    public long getOcupados() {
        long s = 0;

        mArquivador.setPonteiro(MAPA_CLUSTERS_INICIO);

        for (long i = 0; i < MAPA_CLUSTERS_TAMANHO; i++) {

            int status = Inteiro.byteToInt(mArquivador.readByte());
            if (status != 0) {
                s += 1;
            }
        }


        return s;
    }


    public long getMAPA_CLUSTERS_INICIO() {
        return MAPA_CLUSTERS_INICIO;
    }

    public long getMAPA_CLUSTERS_FIM() {
        return MAPA_CLUSTERS_FIM;
    }

    public long getDADOS_INICIO() {
        return DADOS_INICO;
    }

    public void encerrar() {
        mArquivador.encerrar();
    }
}

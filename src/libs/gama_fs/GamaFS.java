package libs.gama_fs;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;

public class GamaFS {

    public static void criar(String eArquivo, long eTamanho) {

        Arquivador.remover(eArquivo);

        Arquivador arquivador = new Arquivador(eArquivo);

        arquivador.set_u8_em_bloco(eTamanho, (byte) 0);


        arquivador.encerrar();

    }

    public static void zerar(String eArquivo) {

        Arquivador arquivador = new Arquivador(eArquivo);

        long tamanho = arquivador.getLength();

        arquivador.setPonteiro(0);
        arquivador.set_u8_em_bloco(tamanho, (byte) 0);

        arquivador.encerrar();

    }

    public static void formatar(String eArquivo) {

        Arquivador arquivador = new Arquivador(eArquivo);

        byte CLUSTER_LIVRE = (byte) 0;
        byte CLUSTER_OCUPADO = (byte) 255;

        long tamanho = arquivador.getLength();
        long cluster = 4 * 1024;

        arquivador.setPonteiro(0);

        arquivador.set_u8((byte) 120);
        arquivador.set_u8((byte) 15);
        arquivador.set_u8((byte) 60);

        long blocos = (tamanho / cluster);

        arquivador.set_u8((byte) 100);
        arquivador.set_u64(blocos);
        arquivador.set_u8((byte) 100);

        long cluter_inicio = arquivador.getPonteiro();

        for (int b = 0; b < blocos; b++) {
            arquivador.set_u8(CLUSTER_LIVRE);
        }

        long cluter_fim = arquivador.getPonteiro();

        arquivador.set_u8((byte) 100);

        long dados_inicio = arquivador.getPonteiro();

        long posicao = arquivador.getPonteiro();
        long ocupados = (posicao / cluster) + 1;

        long cluster_raiz = arquivador.getPonteiro();

        arquivador.set_u64(0);

        arquivador.set_u8((byte) 100);

        long pos_dados = arquivador.getPonteiro();

        System.out.println("Dados :: " + pos_dados);


        // MARCAR OCUPADOS
        arquivador.setPonteiro(cluter_inicio);
        for (int b = 0; b < ocupados; b++) {
            arquivador.set_u8(CLUSTER_OCUPADO);
        }


        // PEGAR PRIMEIRO CLUSTER LIVRE E DEFINIR COMO RAIZ
        arquivador.setPonteiro(cluter_inicio);
        long cluster_raiz_id = -1;
        for (long i = 0; i < blocos; i++) {

            int status = Inteiro.byteToInt(arquivador.get());
            if (status == 0) {
                cluster_raiz_id = i;
                break;
            }
        }

        if (cluster_raiz_id > 0) {

            arquivador.setPonteiro(cluter_inicio + cluster_raiz_id);
            arquivador.set_u8(CLUSTER_OCUPADO);

            arquivador.setPonteiro(cluster_raiz);
            arquivador.set_u64(cluster_raiz_id);

            long esc = (cluster_raiz_id * (4 * 1024));

            System.out.println("Escrever em :: " + esc);
            arquivador.setPonteiro((cluster_raiz_id * (4 * 1024)));
            arquivador.set_u8((byte) 150);
            arquivador.set_u8((byte) 80);
            arquivador.set_u8((byte) 30);

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

        int I1 = Inteiro.byteToInt(mArquivador.get());
        int I2 = Inteiro.byteToInt(mArquivador.get());
        int I3 = Inteiro.byteToInt(mArquivador.get());

        System.out.println("GAMA :: " + (I1) + "::" + (I2) + "::" + (I3));

        int I4 = Inteiro.byteToInt(mArquivador.get());

        MAPA_CLUSTERS_TAMANHO = mArquivador.get_u64();

        int I5 = Inteiro.byteToInt(mArquivador.get());

        System.out.println("\t - Clusters = " + MAPA_CLUSTERS_TAMANHO);

        MAPA_CLUSTERS_INICIO = mArquivador.getPonteiro();
        MAPA_CLUSTERS_FIM = MAPA_CLUSTERS_INICIO + MAPA_CLUSTERS_TAMANHO;

        System.out.println("\t - C. Inicio = " + MAPA_CLUSTERS_INICIO);
        System.out.println("\t - C. Fim    = " + MAPA_CLUSTERS_FIM);

        mArquivador.setPonteiro(MAPA_CLUSTERS_FIM);

        int I6 = Inteiro.byteToInt(mArquivador.get());

        //  System.out.println("\t - I6 :: " + I6);

        long raiz = mArquivador.get_u64();

        int I7 = Inteiro.byteToInt(mArquivador.get());

        DADOS_INICO = mArquivador.getPonteiro();

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

            int status = Inteiro.byteToInt(mArquivador.get());
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

            int status = Inteiro.byteToInt(mArquivador.get());
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

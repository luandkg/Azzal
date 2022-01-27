package GamaFS;

import Arquivos.Binario.Arquivador;
import Arquivos.Binario.Inteiro;

public class Raiz {


    public Raiz(GamaFS eGamaFS, Arquivador eArquivador, long eClusterRaiz) {

        Cluster RaizCluster = new Cluster(eGamaFS, eArquivador, eClusterRaiz);

        System.out.println("P0 -->> " + Inteiro.byteToInt(RaizCluster.readByte(0)) );
        System.out.println("P1 -->> " + Inteiro.byteToInt(RaizCluster.readByte(1)) );
        System.out.println("P2 -->> " + Inteiro.byteToInt(RaizCluster.readByte(2)) );

    }

}

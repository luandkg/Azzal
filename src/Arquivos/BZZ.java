package Arquivos;

import Arquivos.Binario.Arquivador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BZZ {

    private static int BLOCO_TAMANHO = 10 * 1024;

    public static void alocar(String eArquivo, int quantos_blocos) {

        File Arq = new File(eArquivo);
        if (Arq.exists()) {
            Arq.delete();
        }

        try {

            Arquivador arquivador = new Arquivador(eArquivo);

            for (int v = 0; v < quantos_blocos; v++) {
                arquivador.writeByteRepetidos(BLOCO_TAMANHO, (byte) 0);
            }

            arquivador.fechar();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void atribuir(String eArquivo, int posicao, String dados) {

        int posicionador = posicao * (BLOCO_TAMANHO);


        ArrayList<Byte> bytes = TX.toListBytes(dados.toString());


        try {

            Arquivador arquivador = new Arquivador(eArquivo);

            if (posicionador < arquivador.getLength()) {
                arquivador.setPonteiro(posicionador);
                arquivador.writeByteArray(bytes);
            } else {
                System.out.println("NAO TEM ESPACO :: " + posicao);
            }

            arquivador.fechar();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String procurar(String eArquivo, int indice) {

        int posicionador = indice * (BLOCO_TAMANHO);

        // System.out.println("PROC -->> " + indice + " :: " + posicionador);

        ArrayList<Byte> bytes = new ArrayList<Byte>();

        try {

            Arquivador arquivador = new Arquivador(eArquivo, "r");

            if (posicionador < arquivador.getLength()) {

                arquivador.setPonteiro(posicionador);
                bytes = arquivador.readBytes(BLOCO_TAMANHO);

            }

            arquivador.fechar();

        } catch (IOException e) {
            e.printStackTrace();
        }


        // System.out.println("Bytes -->> " + bytes.size());

        return TX.lerDeBytes(bytes);

    }

    public static int comValores(String eArquivo) {

        int indiceBZZ = 0;

        int blocos = getQuantidadeMaxima(eArquivo);

        int comValores = 0;
        int semValores = 0;

        while (indiceBZZ<blocos) {

         //   System.out.println("BLOCO BZZ -->> " + indiceBZZ);

            String conteudo_bloco = BZZ.procurar(eArquivo, indiceBZZ);

            if (conteudo_bloco.length() > 0) {
                comValores+=1;
            }else{
                semValores+=1;
            }

            indiceBZZ += 1;
        }

        return comValores;
    }

    public static int semValores(String eArquivo) {

        int indiceBZZ = 0;

        int blocos = getQuantidadeMaxima(eArquivo);

        int comValores = 0;
        int semValores = 0;

        while (indiceBZZ<blocos) {

            // System.out.println("BLOCO BZZ -->> " + indiceBZZ);

            String conteudo_bloco = BZZ.procurar(eArquivo, indiceBZZ);

            if (conteudo_bloco.length() > 0) {
                comValores+=1;
            }else{
                semValores+=1;
            }

            indiceBZZ += 1;
        }

        return semValores;
    }

    public static int getQuantidadeMaxima(String eArquivo) {

        Arquivador arquivador = new Arquivador(eArquivo, "r");

        int blocos = (int) (arquivador.getLength() / BLOCO_TAMANHO);

        arquivador.encerrar();

        return blocos;
    }
}

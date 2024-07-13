package libs.arquivos;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BZ3 {

    private static int BLOCO_TAMANHO = 100 * 1024;

    public static void alocar(String eArquivo, int quantos_blocos) {

        IO.remova_se_existir(eArquivo);

        try {

            Arquivador arquivador = new Arquivador(eArquivo);

            for (int v = 0; v < quantos_blocos; v++) {
                arquivador.set_u8_em_bloco(BLOCO_TAMANHO, (byte) 0);
            }

            arquivador.fechar();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void atribuir(String eArquivo, int posicao, String dados) {

        int posicionador = posicao * (BLOCO_TAMANHO);


        Lista<Byte> bytes = TX.toListBytes(dados.toString());


        try {

            Arquivador arquivador = new Arquivador(eArquivo);

            if (posicionador < arquivador.getLength()) {
                arquivador.setPonteiro(posicionador);
                arquivador.set_u8_array(bytes);
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

        Lista<Byte> bytes = new Lista<Byte>();

        try {

            Arquivador arquivador = new Arquivador(eArquivo, "r");

            if (posicionador < arquivador.getLength()) {

                arquivador.setPonteiro(posicionador);
                bytes = arquivador.get_u8_bloco(BLOCO_TAMANHO);

            }

            arquivador.fechar();

        } catch (IOException e) {
            e.printStackTrace();
        }


        //   System.out.println("Bytes -->> " + bytes.size());

        return TX.lerDeBytes(bytes);

    }

    public static int comValores(String eArquivo) {

        int indiceBZZ = 0;

        int blocos = getQuantidadeMaxima(eArquivo);

        int comValores = 0;
        int semValores = 0;

        while (indiceBZZ < blocos) {

            //   System.out.println("BLOCO BZZ -->> " + indiceBZZ);

            String conteudo_bloco = procurar(eArquivo, indiceBZZ);

            if (conteudo_bloco.length() > 0) {
                comValores += 1;
            } else {
                semValores += 1;
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

        while (indiceBZZ < blocos) {

            // System.out.println("BLOCO BZZ -->> " + indiceBZZ);

            String conteudo_bloco = procurar(eArquivo, indiceBZZ);

            if (conteudo_bloco.length() > 0) {
                comValores += 1;
            } else {
                semValores += 1;
            }

            indiceBZZ += 1;
        }

        return semValores;
    }

    public static int getQuantidadeMaxima(String eArquivo) {

        int blocos = 0;

        if (IO.existe(eArquivo)) {

            Arquivador arquivador = new Arquivador(eArquivo, "r");
            blocos = (int) (arquivador.getLength() / BLOCO_TAMANHO);
            arquivador.encerrar();

        }


        return blocos;
    }
}

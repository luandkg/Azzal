package libs.arquivos;

import libs.arquivos.binario.Arquivador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BZ {

    public static void alocar(String eArquivo, int quantos_blocos) {

        File Arq = new File(eArquivo);
        if (Arq.exists()) {
            Arq.delete();
        }

        try {

            Arquivador arquivador = new Arquivador(eArquivo);

            for (int v = 0; v < quantos_blocos; v++) {
                arquivador.set_u8_em_bloco(5 * 1024, (byte) 0);
            }

            arquivador.fechar();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void atribuir(String eArquivo, int posicao, String dados) {

        int posicionador = posicao * (5 * 1024);

        TX eTX = new TX();

        ArrayList<Byte> bytes = eTX.toListBytes(dados.toString());


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

        int posicionador = indice * (5 * 1024);

        // System.out.println("PROC -->> " + indice + " :: " + posicionador);

        ArrayList<Byte> bytes = new ArrayList<Byte>();

        try {

            Arquivador arquivador = new Arquivador(eArquivo, "r");

            if (posicionador < arquivador.getLength()) {

                arquivador.setPonteiro(posicionador);
                bytes = arquivador.get_u8_bloco(5 * 1024);

            }

            arquivador.fechar();

        } catch (IOException e) {
            e.printStackTrace();
        }

        TX eTX = new TX();

        // System.out.println("Bytes -->> " + bytes.size());

        return eTX.lerDeBytes(bytes);

    }

}

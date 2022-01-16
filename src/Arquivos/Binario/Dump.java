package Arquivos.Binario;

import java.io.IOException;

public class Dump {

    public static void onDump(String eArquivo, int tamanho) {

        Arquivador arquivador = new Arquivador(eArquivo);
        int eLinha = 10;

        int e = 0;

        for (int i = 0; i < tamanho; i++) {

            int iByte = Inteiro.byteToInt(arquivador.readByte());

            String s1 = String.valueOf(iByte);


            if (s1.length() == 1) {
                s1 = "00" + s1;
            } else if (s1.length() == 2) {
                s1 = "0" + s1;
            }

            System.out.print(s1 + " ");

            e += 1;
            if (e == eLinha) {
                e = 0;
                System.out.print("\n");
            }
        }

        try {
            arquivador.fechar();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

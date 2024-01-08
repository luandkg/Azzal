package libs.arquivos;

import libs.arquivos.binario.Arquivador;

public class IOBytes {

    public static void guardar(byte[] dados_bytes, String eArquivo) {

        IO.remova_se_existir(eArquivo);
        Arquivador arq = new Arquivador(eArquivo);


        int dados_tamanho = dados_bytes.length;
        int dados_ponteiro = 0;

        while (dados_ponteiro < dados_tamanho) {
            arq.set_u8(dados_bytes[dados_ponteiro]);
            dados_ponteiro += 1;
        }

        arq.encerrar();


    }


    public static byte[] obter(String eArquivo) {

        Arquivador ler = new Arquivador(eArquivo);

        byte dados_ler[] = new byte[(int) ler.getLength()];

        int ler_tamanho = dados_ler.length;
        int ler_ponteiro = 0;

        while (ler_ponteiro < ler_tamanho) {
            dados_ler[ler_ponteiro] = ler.get();
            ler_ponteiro += 1;
        }

        ler.encerrar();

        return dados_ler;
    }
}



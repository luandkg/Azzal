package libs.armazenador;


import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;

import java.nio.charset.StandardCharsets;

public class ItemGuardar {

    public static void guardar_em_item_nao_alocado(Arquivador mArquivador, long ponteiro_item, long ponteiro_guardar, String conteudo) {

        mArquivador.setPonteiro(mArquivador.getLength());

        long ponteiro_item_dados = mArquivador.getPonteiro();
        mArquivador.set_u8_em_bloco(10 * 1024, (byte) 0);

        mArquivador.setPonteiro(ponteiro_item_dados);
        mArquivador.set_u8_array(TX.toListBytes(conteudo));

        mArquivador.setPonteiro(ponteiro_item);
        mArquivador.set_u8((byte) Armazenador.ITEM_ALOCADO_OCUPADO);

        mArquivador.setPonteiro(ponteiro_guardar);
        mArquivador.set_u64(ponteiro_item_dados);


    }

    public static void guardar_em_item_ja_alocado(Arquivador mArquivador, long ponteiro_item, long ponteiro_guardar, String conteudo) {

        mArquivador.setPonteiro(ponteiro_guardar);
        mArquivador.set_u8_array(TX.toListBytes(conteudo));

        mArquivador.setPonteiro(ponteiro_item);
        mArquivador.set_u8((byte) Armazenador.ITEM_ALOCADO_OCUPADO);
        if (Armazenador.IS_DEBUG) {
            System.out.println("!INFO -- REAPROVEITAR ITEM ALOCADO :: " + ponteiro_item);
        }

    }

    // FUNCOES UTF-8

    public static void guardar_em_item_nao_alocado_utf8(Arquivador mArquivador, long ponteiro_item, long ponteiro_guardar, String conteudo) {

        mArquivador.setPonteiro(mArquivador.getLength());

        long ponteiro_item_dados = mArquivador.getPonteiro();
        mArquivador.set_u8_em_bloco(Armazenador.TAMANHO_ITEM, (byte) 0);

        mArquivador.setPonteiro(ponteiro_item_dados);

        byte[] bytes = conteudo.getBytes(StandardCharsets.UTF_8);

        mArquivador.set_u32(bytes.length);
        mArquivador.set_u8_vector(bytes);

        mArquivador.setPonteiro(ponteiro_item);
        mArquivador.set_u8((byte) Armazenador.ITEM_ALOCADO_OCUPADO);

        mArquivador.setPonteiro(ponteiro_guardar);
        mArquivador.set_u64(ponteiro_item_dados);


    }

    public static void guardar_em_item_ja_alocado_utf8(Arquivador mArquivador, long ponteiro_item, long ponteiro_guardar, String conteudo) {

        mArquivador.setPonteiro(ponteiro_guardar);


        byte[] bytes = conteudo.getBytes(StandardCharsets.UTF_8);

        mArquivador.set_u32(bytes.length);
        mArquivador.set_u8_vector(bytes);

        mArquivador.setPonteiro(ponteiro_item);
        mArquivador.set_u8((byte) Armazenador.ITEM_ALOCADO_OCUPADO);
        if (Armazenador.IS_DEBUG) {
            System.out.println("!INFO -- REAPROVEITAR ITEM ALOCADO :: " + ponteiro_item);
        }

    }


}

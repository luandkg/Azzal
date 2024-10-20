package libs.arquivos;

import libs.arquivos.binario.Arquivador;
import libs.luan.fmt;

import java.nio.charset.StandardCharsets;

public class Quadrum {

    public static final int TAMANHO_COLUNA = 255;

    private String mArquivo;
    private Arquivador mArquivar;
    private int mQuantidadeDeColunas = 0;

    public Quadrum(String eArquivo) {
        mArquivo = eArquivo;
    }

    public void abrir() {
        mArquivar = new Arquivador(mArquivo);
        mArquivar.setPonteiro(0);

        byte b1 = mArquivar.get();
        byte b2 = mArquivar.get();
        byte b3 = mArquivar.get();

        mQuantidadeDeColunas = mArquivar.get_u32();

    }

    public void fechar() {
        mArquivar.encerrar();
    }


    public void set(int item_id, int coluna_id, String dados) {

        byte[] bytes = dados.getBytes(StandardCharsets.UTF_8);

        if (coluna_id < mQuantidadeDeColunas && bytes.length < TAMANHO_COLUNA) {

            long ponteiro = 3L + 4L + ((long) item_id * ((long) mQuantidadeDeColunas * (4L + TAMANHO_COLUNA))) + ((long) coluna_id * (4L + TAMANHO_COLUNA));

            // fmt.print("Escrevendo em {} - {} :: {}",item_id,coluna_id,ponteiro);

            mArquivar.setPonteiro(ponteiro);
            mArquivar.set_u32(bytes.length);
            mArquivar.set_u8_array(bytes);

        }
    }

    public String get(int item_id, int coluna_id) {

        if (coluna_id < mQuantidadeDeColunas) {

            long ponteiro = 3L + 4L + ((long) item_id * ((long) mQuantidadeDeColunas * (4L + TAMANHO_COLUNA))) + ((long) coluna_id * (4L + TAMANHO_COLUNA));

            //   fmt.print("Lendo em {} - {} :: {}",item_id,coluna_id,ponteiro);

            mArquivar.setPonteiro(ponteiro);
            int tam = mArquivar.get_u32();
            byte[] bytes = mArquivar.get_u8_array(tam);

            return new String(bytes, StandardCharsets.UTF_8);
        }


        return "";
    }

    public int getTamanho(int item_id, int coluna_id) {

        int tam = 0;

        if (coluna_id < mQuantidadeDeColunas) {
            long ponteiro = 3L + 4L + ((long) item_id * ((long) mQuantidadeDeColunas * (4L + TAMANHO_COLUNA))) + ((long) coluna_id * (4L + TAMANHO_COLUNA));

            mArquivar.setPonteiro(ponteiro);
            tam = mArquivar.get_u32();
        }


        return tam;
    }

    public void limpar_coluna(int item_id, int coluna_id) {


        if (coluna_id < mQuantidadeDeColunas) {

            long ponteiro = 3L + 4L + ((long) item_id * ((long) mQuantidadeDeColunas * (4L + TAMANHO_COLUNA))) + ((long) coluna_id * (4L + TAMANHO_COLUNA));

            // fmt.print("Escrevendo em {} - {} :: {}",item_id,coluna_id,ponteiro);

            mArquivar.setPonteiro(ponteiro);
            mArquivar.set_u32(0);

        }
    }

    public void limpar(int item_id) {

        for (int coluna_id = 0; coluna_id < mQuantidadeDeColunas; coluna_id++) {

            long ponteiro = 3L + 4L + ((long) item_id * ((long) mQuantidadeDeColunas * (4L + TAMANHO_COLUNA))) + ((long) coluna_id * (4L + TAMANHO_COLUNA));

            mArquivar.setPonteiro(ponteiro);
            mArquivar.set_u32(0);
        }

    }


    public int getQuantidadeDeColunas() {
        return mQuantidadeDeColunas;
    }

    public long getQuantidadeDeItens() {
        long tamanho = mArquivar.getLength() - (3L + 4L);
        long quantidade = tamanho / ((long) mQuantidadeDeColunas * (TAMANHO_COLUNA + 4L));
        return quantidade;
    }

    public void novo_item() {

        long quantidade = getQuantidadeDeItens();

        mArquivar.setPonteiro(3L + 4L + ((long) quantidade * ((long) mQuantidadeDeColunas * (4L + TAMANHO_COLUNA))));

        mArquivar.set_u32(0);
        mArquivar.set_u8_em_bloco(TAMANHO_COLUNA, (byte) 0);

    }


    public int getTamanhoMenor() {

        int tamanho = 0;
        boolean primeiro = true;

        for (int i = 0; i < getQuantidadeDeItens(); i++) {
            for (int c = 0; c < getQuantidadeDeColunas(); c++) {

                int tam = getTamanho(i, c);

                if (primeiro) {
                    tamanho = tam;
                    primeiro = false;
                } else {
                    if (tam < tamanho) {
                        tamanho = tam;
                    }
                }

            }
        }

        return tamanho;
    }

    public int getTamanhoMaior() {

        int tamanho = 0;
        boolean primeiro = true;

        for (int i = 0; i < getQuantidadeDeItens(); i++) {
            for (int c = 0; c < getQuantidadeDeColunas(); c++) {

                int tam = getTamanho(i, c);

                if (primeiro) {
                    tamanho = tam;
                    primeiro = false;
                } else {
                    if (tam > tamanho) {
                        tamanho = tam;
                    }
                }

            }
        }

        return tamanho;
    }

    public void exibirItensComTamanho(int tamanho) {


        for (int i = 0; i < getQuantidadeDeItens(); i++) {
            for (int c = 0; c < getQuantidadeDeColunas(); c++) {

                int tam = getTamanho(i, c);

                if (tam == tamanho) {
                    fmt.print("{}", get(i, c));
                }

            }
        }

    }


    public static void init(String eArquivo, int eQuantidadeDeItens, int eQuantidadeDeColunas) {


        Arquivador.remover(eArquivo);

        Arquivador arquivar = new Arquivador(eArquivo);
        arquivar.setPonteiro(0);

        arquivar.set_u8((byte) 122);
        arquivar.set_u8((byte) 155);
        arquivar.set_u8((byte) 1);

        arquivar.set_u32(eQuantidadeDeColunas);

        for (int item = 0; item < eQuantidadeDeItens; item++) {
            for (int coluna = 0; coluna < eQuantidadeDeColunas; coluna++) {
                arquivar.set_u32(0);
                arquivar.set_u8_em_bloco(TAMANHO_COLUNA, (byte) 0);
            }

            fmt.print("Alocando :: {}", item);
        }


        arquivar.encerrar();

    }


}

package libs.arquivos.ds;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;

import java.nio.charset.StandardCharsets;

public class DSItem {

    private int mIndex;

    private String mArquivo;
    private int mStatus;
    private String mNome;
    private long mTamanho;
    private long mInicio;

    public DSItem(String eArquivo, int eIndex, int eStatus, String eNome, long eTamanho, long eInicio) {

        mArquivo = eArquivo;
        mIndex = eIndex;
        mStatus = eStatus;
        mNome = eNome;
        mTamanho = eTamanho;
        mInicio = eInicio;

    }


    public String getArquivo() {
        return mArquivo;
    }

    public int getIndex() {
        return mIndex;
    }

    public int getStatus() {
        return mStatus;
    }

    public String getNome() {
        return mNome;
    }

    public long getTamanho() {
        return mTamanho;
    }

    public long getInicio() {
        return mInicio;
    }

    public long getFim(){
        return mInicio+mTamanho;
    }


    public byte[] getBytes() {

        Arquivador arquivar = new Arquivador(mArquivo);
        arquivar.setPonteiro(mInicio);

        byte[] dados = new byte[(int) mTamanho];

        for (int b = 0; b < mTamanho; b++) {
            dados[b] = arquivar.get();
        }

        arquivar.encerrar();

        return dados;
    }

    public byte[] getBytes(int quantidade_de_retorno) {

        if (quantidade_de_retorno <= mTamanho) {
            Arquivador arquivar = new Arquivador(mArquivo);
            arquivar.setPonteiro(mInicio);

            byte[] dados = new byte[(int) quantidade_de_retorno];

            for (int b = 0; b < quantidade_de_retorno; b++) {
                dados[b] = arquivar.get();
            }

            arquivar.encerrar();

            return dados;
        } else {
            throw new RuntimeException("Tamanho superior ao tamanho do item !");
        }

    }

    public int[] getInts() {

        Arquivador arquivar = new Arquivador(mArquivo);
        arquivar.setPonteiro(mInicio);

        int[] dados = new int[(int) mTamanho];

        for (int b = 0; b < mTamanho; b++) {
            dados[b] = Inteiro.byteToInt(arquivar.get());
        }

        arquivar.encerrar();

        return dados;
    }

    public String getTexto() {
        return new String(getBytes(), StandardCharsets.UTF_8);
    }


    public String getTextoPreAlocado() {

        String s = "";

        Arquivador arquivar = new Arquivador(mArquivo);
        arquivar.setPonteiro(mInicio);

        //int valor_pre_alocado = arquivar.get_u8();
        long tamanho_pre_alocado = arquivar.get_u64();

        // fmt.print("Status Pre alloc : {}",valor_pre_alocado);
        //  fmt.print("Tam    Pre alloc : {}",tamanho_pre_alocado);


        if (tamanho_pre_alocado > 0) {

            arquivar.get();
            arquivar.get();

            byte[] dados = new byte[(int) tamanho_pre_alocado];

            for (int b = 0; b < tamanho_pre_alocado; b++) {
                dados[b] = arquivar.get();
            }


            s = new String(dados, StandardCharsets.UTF_8);
        } else {
            s = "";
        }

        arquivar.encerrar();

        return s;
    }

    public long getTamanhoUtilizadoPreAlocado() {

        long tamanho_pre_alocado_utlizado =0;

        Arquivador arquivar = new Arquivador(mArquivo);
        arquivar.setPonteiro(mInicio);

      //  int valor_pre_alocado = arquivar.get_u8();
        tamanho_pre_alocado_utlizado= arquivar.get_u64();

        // fmt.print("Status Pre alloc : {}",valor_pre_alocado);
        //  fmt.print("Tam    Pre alloc : {}",tamanho_pre_alocado);

        arquivar.encerrar();

        return tamanho_pre_alocado_utlizado;
    }



    public boolean isNome(String eNome) {
        return mNome.contentEquals(eNome);
    }

}

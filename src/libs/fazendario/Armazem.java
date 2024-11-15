package libs.fazendario;

import apps.app_campeonatum.VERIFICADOR;
import libs.arquivos.binario.Arquivador;
import libs.luan.Strings;

public class Armazem {

    private Fazendario mFazendario;
    private Arquivador mArquivador;

    private int mIndice;
    private long mPonteiro;

    public Armazem(Fazendario eFazendario, Arquivador eArquivador, int eIndice) {
        mFazendario = eFazendario;
        mArquivador = eArquivador;

        mIndice = eIndice;
        mPonteiro = 4L + ((long) mIndice * (1024 + 4 + 8 + 2));

    }


    public int getIndice() {
        return mIndice;
    }

    public boolean isDisponivel() {
        mArquivador.setPonteiro(mPonteiro + 1L);
        return mArquivador.get_u8() == Fazendario.ARMAZEM_JA_INICIADO_E_DISPONIVEL;
    }

    public boolean isNaoIniciado() {
        mArquivador.setPonteiro(mPonteiro + 1L);
        return mArquivador.get_u8() == Fazendario.ARMAZEM_NAO_INICIADO;
    }

    public boolean isOcupado() {
        mArquivador.setPonteiro(mPonteiro + 1L);
        return mArquivador.get_u8() == Fazendario.ARMAZEM_JA_INICIADO_E_OCUPADO;
    }

    public String getNome() {

        mArquivador.setPonteiro(mPonteiro + 2L + 8L);

        int bloco_nome_tamanho = mArquivador.get_u32();
        if (bloco_nome_tamanho > 1024) {
            bloco_nome_tamanho = 1024;
        }
        byte[] nome_bytes = mArquivador.get_u8_array(bloco_nome_tamanho);

        return Strings.GET_STRING_VIEW(nome_bytes);
    }

    public boolean isNome(String eNome) {
        return Strings.isIgual(getNome(), eNome);
    }

    public void setStatus(int s) {
        mArquivador.setPonteiro(mPonteiro + 1L);
        mArquivador.set_u8((byte) s);
    }

    public void setNome(String eNome) {
        mArquivador.setPonteiro(mPonteiro + 2L + 8L);

        byte[] bytes = Strings.GET_STRING_VIEW_BYTES(eNome);

        VERIFICADOR.MENOR_OU_IGUAL(bytes.length, 1024);

        mArquivador.set_u32(bytes.length);
        mArquivador.set_u8_vector(bytes);

    }

    public void setPonteiro(long ptr) {
        mArquivador.setPonteiro(mPonteiro + 2L);
        mArquivador.set_u64(ptr);
    }

    public long getPonteiro() {
        mArquivador.setPonteiro(mPonteiro + 2L);
        return mArquivador.get_u64();
    }
}

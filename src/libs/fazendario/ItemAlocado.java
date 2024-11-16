package libs.fazendario;

import apps.app_campeonatum.VERIFICADOR;
import libs.arquivos.binario.Arquivador;
import libs.luan.Strings;

public class ItemAlocado {

    private Arquivador mArquivador;
    private ArmazemAndar mArmazemAndar;

    private int mIndiceSequencial;
    private long mPonteiroStatus;
    private long mPonteiroDados;

    private boolean mRemovido = false;

    public ItemAlocado(Arquivador eArquivador, ArmazemAndar eArmazemAndar, int eIndiceSequencial, long ePonteiroStatus, long ePonteiroDados) {
        mArquivador = eArquivador;
        mArmazemAndar = eArmazemAndar;
        mIndiceSequencial = eIndiceSequencial;
        mPonteiroStatus = ePonteiroStatus;
        mPonteiroDados = ePonteiroDados;
    }

    public long getPonteiroStatus() {
        return mPonteiroStatus;
    }

    public int getStatus() {
        mArquivador.setPonteiro(mPonteiroStatus);
        return mArquivador.get_u8();
    }


    public long getPonteiroDados() {
        return mPonteiroDados;
    }

    public int getIndiceSequencial() {
        return mIndiceSequencial;
    }


    public void remover() {
        if (!mRemovido) {
            mArmazemAndar.remover(this);
            mRemovido = true;
        } else {
            throw new RuntimeException("Esse item foi removido !");
        }

    }

    public boolean isRemovido() {
        return mRemovido;
    }

    public void marcarRemovido() {
        mRemovido = true;
    }

    public String lerTextoUTF8() {
        if (mRemovido) {
            throw new RuntimeException("Esse item foi removido !");
        }
        mArquivador.setPonteiro(mPonteiroDados);
        int texto_tamanhho = mArquivador.get_u32();

        byte[] bytes = mArquivador.get_u8_array(texto_tamanhho);
        String ss = Strings.GET_STRING_VIEW(bytes);
        // String.valueOf("sv {"+bytes.length+"}");
        return ss;
    }


    public void atualizarUTF8(String texto) {

        byte[] bytes = Strings.GET_STRING_VIEW_BYTES(texto);

        VERIFICADOR.MENOR_OU_IGUAL(bytes.length + 10, Fazendario.TAMANHO_SETOR_ITEM);

        mArquivador.setPonteiro(mPonteiroDados);

        mArquivador.set_u32(bytes.length);
        mArquivador.set_u8_vector(bytes);
    }
}

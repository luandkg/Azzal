package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Strings;

public class ItemAlocado {

    private Arquivador mArquivador;
    private long mPonteiroStatus;
    private long mPonteiroDados;

    public ItemAlocado(Arquivador eArquivador, long ePonteiroStatus, long ePonteiroDados) {
        mArquivador = eArquivador;
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


    public String getTextoUTF8() {
        mArquivador.setPonteiro(mPonteiroDados);
        int texto_tamanhho = mArquivador.get_u32();

        byte[] bytes=mArquivador.get_u8_array(texto_tamanhho);
        String ss = Strings.GET_STRING_VIEW(bytes);
       // String.valueOf("sv {"+bytes.length+"}");
        return ss;
    }
}

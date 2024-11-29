package libs.zetta.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.fmt;

public class ItemAlocado {

    private Arquivador mArquivador;
    private Fazendario mFazendario;
    private PortaoDeslizante mPortaoPrimario;

    private ArmazemAndar mArmazemAndar;

    private int mIndiceSequencial;
    private long mPonteiroStatus;
    private long mPonteiroDados;

    private boolean mRemovido = false;

    public ItemAlocado(Arquivador eArquivador, Fazendario eFazendario, PortaoDeslizante rPortaoPrimario, ArmazemAndar eArmazemAndar, int eIndiceSequencial, long ePonteiroStatus, long ePonteiroDados) {
        mArquivador = eArquivador;
        mFazendario = eFazendario;
        mPortaoPrimario = rPortaoPrimario;

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

        return ItemAcessarDireto.LER(mArquivador, mPonteiroDados);
    }


    public void atualizarUTF8(String texto) {

        //  byte[] bytes = Strings.GET_STRING_VIEW_BYTES(texto);

        // VERIFICADOR.MENOR_OU_IGUAL(bytes.length + 10, Fazendario.TAMANHO_SETOR_ITEM);

        mArquivador.setPonteiro(mPonteiroDados);

        int item_tipo = mArquivador.get_u8();

        if (item_tipo == Fazendario.OBJETO_GRANDE) {

            fmt.print("Remover bloco grande -- Antes de atualizar !");
            PlantacaoAcessoDireto.REMOVER(mArquivador);

        }

        ArmazemAndar.item_adicionar_em_espaco_alocado(mArquivador, mFazendario, mPortaoPrimario, mPonteiroDados, texto);


    }
}

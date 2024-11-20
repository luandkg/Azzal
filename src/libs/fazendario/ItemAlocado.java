package libs.fazendario;

import apps.app_campeonatum.VERIFICADOR;
import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.fmt;

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

        int item_tipo = mArquivador.get_u8();

        String ss = "";

        if (item_tipo == Fazendario.OBJETO_PEQUENO) {

            int texto_tamanhho = mArquivador.get_u32();

            byte[] bytes = mArquivador.get_u8_array(texto_tamanhho);
            ss = Strings.GET_STRING_VIEW(bytes);

        } else if (item_tipo == Fazendario.OBJETO_GRANDE) {

            fmt.print("Ler Grande ---->> ");
            int bytes_quantidade = mArquivador.get_u32();
            int blocos = mArquivador.get_u32();

            fmt.print("\t ++ Tamanho Bytes     :: {}", bytes_quantidade);
            fmt.print("\t ++ Quantidade Blocos :: {}", blocos);


            Lista<Long> blocos_alocados = new Lista<Long>();
            for (int b = 0; b < blocos; b++) {
                long bloco_ref = mArquivador.get_u64();

                fmt.print("\t -- BlocoRef :: {}", bloco_ref);
                blocos_alocados.adicionar(bloco_ref);

            }

            byte[] bytes_completo = new byte[bytes_quantidade];

            int bytes_i = 0;
            int bytes_ate = (int) Fazendario.TAMANHO_AREA_ITEM;

            int bytes_o = bytes_quantidade;

            for (Long bloco : blocos_alocados) {

                mArquivador.setPonteiro(bloco);

                int area_status = mArquivador.get_u8();
                long area_ponteiro = mArquivador.get_u64();
                long area_ponteiro_dados = mArquivador.get_u64();

                mArquivador.setPonteiro(area_ponteiro_dados);

                while (bytes_i < bytes_ate) {
                    bytes_completo[bytes_i] = mArquivador.get();
                    bytes_i += 1;
                }

                bytes_ate += (int) Fazendario.TAMANHO_AREA_ITEM;
                if (bytes_ate > bytes_quantidade) {
                    bytes_ate = bytes_quantidade;
                }
            }

            ss = Strings.GET_STRING_VIEW(bytes_completo);


        }


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

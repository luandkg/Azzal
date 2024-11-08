package libs.aqz.volume;


public class AQZVolumeBloco {

    private AQZVolume mAQZVolume;
    private int mBlocoID;

    public AQZVolumeBloco(AQZVolume eAQZVolume, int eBlocoID) {
        mAQZVolume = eAQZVolume;
        mBlocoID = eBlocoID;
    }

    public AQZVolume getVolume() {
        return mAQZVolume;
    }

    public int getBlocoID() {
        return mBlocoID;
    }


    public void marcarComoRaiz() {
        mAQZVolume.getArquivador().setPonteiro(mAQZVolume.getMapaInicio() + (long) mBlocoID);
        mAQZVolume.getArquivador().set_u8((byte) 2);
    }

    public void marcarComoOcupado() {
        mAQZVolume.getArquivador().setPonteiro(mAQZVolume.getMapaInicio() + (long) mBlocoID);
        mAQZVolume.getArquivador().set_u8((byte) 1);
    }


    public long getPonteiroDados() {
        return mAQZVolume.getDadosInicio() + ((long) mBlocoID * AZVolumeInternamente.VOLUME_INODE_TAMANHO);
    }

    public void setNome(byte[] bytes) {

        mAQZVolume.getArquivador().setPonteiro(mAQZVolume.getDadosInicio() + ((long) mBlocoID * AZVolumeInternamente.VOLUME_INODE_TAMANHO + ((long) 1 + 8)));
        mAQZVolume.getArquivador().set_u32(bytes.length);
        mAQZVolume.getArquivador().set_u8_vector(bytes);

    }

    public void setDados(byte[] dados) {

        if (dados.length > AZVolumeInternamente.VOLUME_INODE_DADOS_TAMANHO) {
            throw new RuntimeException("Quantidade de dados alem da capacidade do bloco !");
        }

        mAQZVolume.getArquivador().setPonteiro(mAQZVolume.getDadosInicio() + (long) ((long) mBlocoID * AZVolumeInternamente.VOLUME_INODE_TAMANHO) + 2000);
        mAQZVolume.getArquivador().set_u32(dados.length);

        mAQZVolume.getArquivador().setPonteiro(mAQZVolume.getDadosInicio() + (long) ((long) mBlocoID * AZVolumeInternamente.VOLUME_INODE_TAMANHO) + 2020);
        mAQZVolume.getArquivador().set_u8_vector(dados);

    }

    public void marcar_ultimo() {
        mAQZVolume.getArquivador().setPonteiro(mAQZVolume.getDadosInicio() + (long) ((long) mBlocoID *AZVolumeInternamente.VOLUME_INODE_TAMANHO));
        mAQZVolume.getArquivador().set_u8((byte) 1);
        mAQZVolume.getArquivador().set_u64(0);
    }

    public void marcar_proximo(long eProximo) {
        mAQZVolume.getArquivador().setPonteiro(mAQZVolume.getDadosInicio() + (long) ((long) mBlocoID * AZVolumeInternamente.VOLUME_INODE_TAMANHO));
        mAQZVolume.getArquivador().set_u8((byte) 1);
        mAQZVolume.getArquivador().set_u64(eProximo);
    }

}

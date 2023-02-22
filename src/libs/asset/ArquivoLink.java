package libs.asset;


public class ArquivoLink {

    private String mNome;
    private String mONome;

    private AssetContainer mAssetContainer;
    private Arquivo mArquivo;
    private boolean mIndexado;

    private long mInicio;
    private long mFim;

    public ArquivoLink(AssetContainer eAssetContainer, String eNome,String eoNome, long eInicio, long eFim) {

        mNome = eNome;
        mONome=eoNome;
        mInicio = eInicio;
        mFim = eFim;

        mAssetContainer = eAssetContainer;
        mArquivo = null;
        mIndexado = false;
    }


    public String getNome() {
        return mNome;
    }

    public Arquivo getArquivo() {

        if (!mIndexado) {
            mIndexado = true;
            abrir();
        }

        return mArquivo;
    }

    private void abrir() {

        mArquivo = new Arquivo(mAssetContainer,mArquivo.getReferencia(),new AssetRef(mONome,12,mInicio,mFim));



    }


}

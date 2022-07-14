package libs.AssetContainer;


import java.util.ArrayList;

public class PastaLink {

    private String mNome;
    private String mONome;

    private AssetContainer mAssetContainer;
    private Pasta mPasta;
    private boolean mIndexado;

    private long mInicio;
    private long mFim;

    public PastaLink(AssetContainer eAssetContainer, String eNome,String eONome, long eInicio, long eFim) {

        mNome = eNome;
        mONome=eONome;

        mInicio = eInicio;
        mFim = eFim;

        mAssetContainer = eAssetContainer;
        mPasta = null;
        mIndexado = false;
    }


    public String getNome() {
        return mNome;
    }

    public Pasta getPasta() {

        if (!mIndexado) {
            mIndexado = true;
            mPasta = new Pasta(mAssetContainer, mPasta.getReferencia(), new Ponto(mONome,12, mInicio, mFim));
        }

        return mPasta;
    }


    public ArrayList<Arquivo> getArquivos() {

        return getPasta().getArquivos();

    }

    public ArrayList<Pasta> getPastas() {

        return getPasta().getPastas();

    }


    public ArrayList<Arquivo> getArquivosTodos() {

        return getPasta().getArquivosTodos();

    }

    public ArrayList<Pasta> getPastasTodos() {

        return getPasta().getPastasTodos();

    }

}

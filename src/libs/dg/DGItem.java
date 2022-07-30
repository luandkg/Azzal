package libs.dg;

import libs.Arquivos.TX;

public class DGItem {

    private DG mDG;
    private long mPonteiroItem;

    private boolean mIsCache;
    private DGObjeto mCached;

    public DGItem(DG eDG, long ePonteiroItem) {
        mDG = eDG;
        mPonteiroItem = ePonteiroItem;
        mIsCache = false;
        mCached = null;
    }

    public long getRefPtr() {
        return mPonteiroItem;
    }

    public void excluir() {

        mDG.getArquivador().setPonteiro(mPonteiroItem);

        int valido = mDG.getArquivador().organizar_to_int(mDG.getArquivador().readByte());

        if (valido != 0) {
            mDG.getArquivador().setPonteiro(mPonteiroItem);
            mDG.getArquivador().writeByte((byte) 0);
            mIsCache = false;
        }

    }

    public String getValor() {
        String s = "";

        mDG.getArquivador().setPonteiro(mPonteiroItem);

        int valido = mDG.getArquivador().organizar_to_int(mDG.getArquivador().readByte());

        if (valido != 0) {
            TX eTX = new TX();
            s = eTX.lerFluxoLimitado(mDG.getArquivador(), 4 * 1024);
        }

        return s;
    }

    public String getValorEmLinha() {
        String s = "";

        mDG.getArquivador().setPonteiro(mPonteiroItem);

        int valido = mDG.getArquivador().organizar_to_int(mDG.getArquivador().readByte());

        if (valido != 0) {
            TX eTX = new TX();
            s = eTX.lerFluxoLimitado(mDG.getArquivador(), 4 * 1024);
        }

        return s.replace("\n", "");
    }

    public boolean isValido() {

        mDG.getArquivador().setPonteiro(mPonteiroItem);

        int valido = mDG.getArquivador().organizar_to_int(mDG.getArquivador().readByte());

        return valido != 0;
    }

    public DGObjeto to() {

        if (!mIsCache) {
            mIsCache = true;
            mCached = new DGObjeto();
            mCached.parser(getValor());
        }

        return mCached;
    }

    public void commit() {

        if (mIsCache) {

            if (mCached.getTamanho() < DG.BLOCO - 5) {

                TX eTX = new TX();

                mDG.getArquivador().setPonteiro(mPonteiroItem);
                mDG.getArquivador().writeByte((byte) 1);
                eTX.escreverFluxo(mCached.toString(), mDG.getArquivador());

            }

        }

    }
}

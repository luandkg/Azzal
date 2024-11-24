package libs.arquivos.dsvideo;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.ds.DSItem;

public class DSQuadro {

    private Arquivador mArquivo;
    private int mIndice;
    private long mInicio;
    private DSItem mItem;

    public DSQuadro(Arquivador eArquivo,DSItem eItem, int eIndice, long eInicio) {
        mArquivo = eArquivo;
        mIndice = eIndice;
        mInicio = eInicio;
        mItem=eItem;
    }

    public int getIndex() {
        return mIndice;
    }


    public long getConteudo() {

        mArquivo.setPonteiro(mItem.getInicio()+mInicio);

        return mArquivo.get_u64();
    }

}

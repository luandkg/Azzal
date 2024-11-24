package libs.arquivos.dsvideo;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.ds.DSItem;
import libs.luan.Lista;

public class DSArena {

    private Arquivador mArquivo;
    private long mLocal;

    private DSItem mItem;

    public DSArena(Arquivador eArquivo, DSItem eItem, long eLocal) {

        mArquivo = eArquivo;
        mLocal = eLocal;
        mItem = eItem;

        //fmt.print("PTR Arena :: {}", eLocal);

    }


    public long getAnterior() {
        mArquivo.setPonteiro(mItem.getInicio()+mLocal + 1);
        return mArquivo.get_u64();
    }

    public long getProximo() {
        mArquivo.setPonteiro(mItem.getInicio()+mLocal + 9);
        return mArquivo.get_u64();
    }

    public Lista<DSQuadro> getQuadros() {

        Lista<DSQuadro> mQuadros = new Lista<DSQuadro>();

        int eContador = 0;

        mArquivo.setPonteiro(mItem.getInicio()+mLocal + 18L);

        for (int i = 0; i < 100; i++) {

           // fmt.print("Q :: {}", i);

            long eAntes = mArquivo.getPonteiro();
            mQuadros.adicionar(new DSQuadro(mArquivo, mItem,i, eAntes-mItem.getInicio()));
            mArquivo.setPonteiro(eAntes);

            long eFramePonteiro = mArquivo.get_u64();
            eContador += 1;

        }

       // fmt.print("Quadros :: {}", mQuadros.getQuantidade());

        return mQuadros;

    }


    public int getFramesUsadosContagem() {

        int eContador = 0;

        mArquivo.setPonteiro(mItem.getInicio()+mLocal + 18);

        for (int i = 0; i < 100; i++) {

            long eFramePonteiro = mArquivo.get_u64();
            if (eFramePonteiro != 0) {
                eContador += 1;
            }

        }

        return eContador;


    }

    public int getFramesLivreContagem() {

        int eContador = 0;

        mArquivo.setPonteiro(mItem.getInicio()+mLocal + 18);

        for (int i = 0; i < 100; i++) {

            long eFramePonteiro = mArquivo.get_u64();
            if (eFramePonteiro == 0) {
                eContador += 1;
            }

        }

        return eContador;


    }


}

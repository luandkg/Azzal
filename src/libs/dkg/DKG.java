package libs.dkg;

import libs.dkg.IO.EscritorDKG;
import libs.dkg.IO.ParserDKG;
import libs.OLLT.Texto;

import java.util.ArrayList;

public class DKG {

    private ArrayList<DKGObjeto> mDKGObjetos;

    public DKG() {

        mDKGObjetos = new ArrayList<DKGObjeto>();

    }

    // IO

    public void abrir(String eArquivo){
        ParserDKG parserDKGC = new ParserDKG();
        parserDKGC.parse(Texto.Ler(eArquivo), this);
    }


    public void parser(String eTexto){
        ParserDKG parserDKGC = new ParserDKG();
        parserDKGC.parse(eTexto, this);
    }

    public void salvar(String eArquivo){

        EscritorDKG escritor = new EscritorDKG();
        escritor.montar( "", mDKGObjetos);

        Texto.Escrever(eArquivo, escritor.getTexto());
    }



    public String toString() {

        EscritorDKG escritor = new EscritorDKG();

        escritor.montar( "", mDKGObjetos);

        return escritor.getTexto();
    }


    // OBJETO

    public ArrayList<DKGObjeto> getObjetos(){return mDKGObjetos;}

    public DKGObjeto criarObjeto(String eNome) {

        DKGObjeto ret = new DKGObjeto(eNome);
        mDKGObjetos.add(ret);

        return ret;
    }

    public DKGObjeto unicoObjeto(String eNome) {

        boolean enc = false;
        DKGObjeto ret = null;

        for (DKGObjeto mDKGObjeto : mDKGObjetos) {

            if (mDKGObjeto.getNome().contentEquals(eNome)) {
                enc = true;
                ret = mDKGObjeto;
                break;
            }

        }

        if (enc == false) {
            ret = new DKGObjeto(eNome);
            mDKGObjetos.add(ret);
        }

        return ret;
    }

    public void removerObjeto(DKGObjeto eDKGObjeto) {

        for (DKGObjeto mDKGObjeto : mDKGObjetos) {

            if (mDKGObjeto == eDKGObjeto) {
                mDKGObjetos.remove(eDKGObjeto);
                break;
            }

        }

    }

    public void removerObjetoPorNome(String eNome) {

        for (DKGObjeto mDKGObjeto : mDKGObjetos) {

            if (mDKGObjeto.getNome().contentEquals(eNome)) {
                mDKGObjetos.remove(mDKGObjeto);
                break;
            }

        }

    }
}

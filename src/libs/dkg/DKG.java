package libs.dkg;

import libs.dkg.IO.EscritorDKG;
import libs.dkg.IO.ParserDKG;

import java.io.*;
import java.util.ArrayList;

public class DKG {

    private ArrayList<DKGObjeto> mDKGObjetos;

    public DKG() {

        mDKGObjetos = new ArrayList<DKGObjeto>();

    }


    // ESTATICOS

    public static DKG ABRIR_DO_ARQUIVO(String eArquivo) {
        DKG eDKG = new DKG();
        File arq = new File(eArquivo);

        if (arq.exists()) {
            eDKG.abrir(eArquivo);
        }

        return eDKG;
    }

    public static DKG PARSER(String dados) {
        DKG eDKG = new DKG();
        eDKG.parser(dados);
        return eDKG;
    }

    public static DKGObjeto PARSER_TO_OBJETO(String dados, String eObjeto) {
        DKG eDKG = new DKG();
        eDKG.parser(dados);
        return eDKG.unicoObjeto(eObjeto);
    }


    // IO

    public void abrir(String eArquivo) {
        ParserDKG parserDKGC = new ParserDKG();
        parserDKGC.parse(arquivo_ler(eArquivo), this);
    }


    public void parser(String eTexto) {
        ParserDKG parserDKGC = new ParserDKG();
        parserDKGC.parse(eTexto, this);
    }

    public void salvar(String eArquivo) {

        EscritorDKG escritor = new EscritorDKG();
        escritor.montar("", mDKGObjetos);

        arquivo_escrever(eArquivo, escritor.getTexto());
    }


    public String toString() {

        EscritorDKG escritor = new EscritorDKG();

        escritor.montar("", mDKGObjetos);

        return escritor.getTexto();
    }

    public String toDocumento() {

        EscritorDKG escritor = new EscritorDKG();

        escritor.montar("", mDKGObjetos);

        return escritor.getTexto();
    }

    // OBJETO

    public ArrayList<DKGObjeto> getObjetos() {
        return mDKGObjetos;
    }

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

        if (!enc) {
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


    private static void arquivo_escrever(String eArquivo, String eConteudo) {

        BufferedWriter writer = null;
        try {
            File logFile = new File(eArquivo);

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(eConteudo);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }

    }

    private static String arquivo_ler(String eArquivo) {

        String ret = "";

        try {
            FileReader arq = new FileReader(eArquivo);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            ret += linha;

            while (linha != null) {

                linha = lerArq.readLine();
                if (linha != null) {
                    ret += "\n" + linha;
                }

            }

            arq.close();
        } catch (IOException e) {

        }

        return ret;
    }

    // FEATURE 22.09

    public ArrayList<String> toListaDeString(String atributo){

        ArrayList<String> ls = new ArrayList<String>();

        for (DKGObjeto objeto : getObjetos()) {
            ls.add(objeto.identifique(atributo).getValor());
        }

        return ls;
    }

    // FEATURE 22.09
    public DKGObjeto unicamente(String ePrimeiro, String eSegundo) {
        return unicoObjeto(ePrimeiro).unicoObjeto(eSegundo);
    }

    // FEATURE 22.09
    public DKGObjeto unicamente(String ePrimeiro, String eSegundo,String eTerceiro) {
        return unicoObjeto(ePrimeiro).unicoObjeto(eSegundo).unicoObjeto(eTerceiro);
    }

    // FEATURE 22.10
    public ArrayList<DKGObjetoOuAtributo> getTodos(){

        ArrayList<DKGObjetoOuAtributo> lista = new ArrayList<DKGObjetoOuAtributo>();

        for (DKGObjeto obj : getObjetos()) {
            lista.add(new DKGObjetoOuAtributo(obj));
        }
        return lista;
    }

}

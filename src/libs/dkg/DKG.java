package libs.dkg;

import libs.dkg.IO.EscritorDKG;
import libs.dkg.IO.ParserDKG;
import libs.luan.Lista;

import java.io.*;

public class DKG {

    private Lista<DKGObjeto> mDKGObjetos;

    public DKG() {
        mDKGObjetos = new Lista<DKGObjeto>();
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

    public static DKGObjeto PARSER_TO_OBJETO(String dados ) {
        DKG eDKG = new DKG();
        eDKG.parser(dados);

        if (eDKG.getObjetos().getQuantidade()>0){
            return eDKG.getObjetos().get(0);
        }else{
            return new DKGObjeto("");
        }
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

    public Lista<DKGObjeto> getObjetos() {
        return mDKGObjetos;
    }

    public DKGObjeto criarObjeto(String eNome) {

        DKGObjeto ret = new DKGObjeto(eNome);
        mDKGObjetos.adicionar(ret);

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
            mDKGObjetos.adicionar(ret);
        }

        return ret;
    }

    public void removerObjeto(DKGObjeto eDKGObjeto) {

        for (DKGObjeto mDKGObjeto : mDKGObjetos) {

            if (mDKGObjeto == eDKGObjeto) {
                mDKGObjetos.remover(eDKGObjeto);
                break;
            }

        }

    }

    public void removerObjetoPorNome(String eNome) {

        for (DKGObjeto mDKGObjeto : mDKGObjetos) {

            if (mDKGObjeto.getNome().contentEquals(eNome)) {
                mDKGObjetos.remover(mDKGObjeto);
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

    public Lista<String> toListaDeString(String atributo){

        Lista<String> ls = new Lista<String>();

        for (DKGObjeto objeto : getObjetos()) {
            ls.adicionar(objeto.identifique(atributo).getValor());
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
    public Lista<DKGObjetoOuAtributo> getTodos(){

        Lista<DKGObjetoOuAtributo> lista = new Lista<DKGObjetoOuAtributo>();

        for (DKGObjeto obj : getObjetos()) {
            lista.adicionar(new DKGObjetoOuAtributo(obj));
        }
        return lista;
    }

}

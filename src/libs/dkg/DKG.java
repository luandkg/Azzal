package libs.dkg;

import libs.dkg.IO.EscritorDKG;
import libs.dkg.IO.ParserDKG;
import libs.luan.Lista;

import java.io.*;

public class DKG {

    // 2023 12 09 - MIGRAR ARRAYLIST -> LISTA
    // 2023 12 09 - UNIR PARSER E SERIALIZADOR


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
        parse(arquivo_ler(eArquivo), this);
    }


    public void parser(String eTexto) {
        parse(eTexto, this);
    }

    public void salvar(String eArquivo) {
        arquivo_escrever(eArquivo, serializar(mDKGObjetos));
    }


    public String toString() {
        return serializar(mDKGObjetos);
    }

    public String toDocumento() {
        return serializar(mDKGObjetos);
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



    // PARSER TEXTUM
    private final String ALFABETO_INICIAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_@.123456789";
    private final String ALFABETO_FINAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_@.0123456789";

    private String mTextoParser;
    private int mIndex;
    private int mMaximo;

    private Lista<String> mParserErros;



    public Lista<String> getErros() {
        return mParserErros;
    }



    // PARSER

    private void parse(String eDocumento, DKG eEmpacotador) {

        mTextoParser = "";
        mIndex = 0;
        mMaximo = 0;
        mParserErros = new Lista<String>();

        eEmpacotador.getObjetos().limpar();

        // mPacotes = new ArrayList<Pacote>();

        mTextoParser = eDocumento;
        mIndex = 0;
        mMaximo = eDocumento.length();

        //   String eAnterior = "";

        while (mIndex < mMaximo) {
            String l = String.valueOf(eDocumento.charAt(mIndex));

            if (l.contentEquals(" ")) {
            } else if (l.contentEquals("\n")) {
            } else if (l.contentEquals("\t")) {
            } else if (l.contentEquals("\r")) {

            } else if (l.contentEquals("!")) {

                mIndex += 1;
                parserObjeto(eEmpacotador.getObjetos());

            } else {

            }

            mIndex += 1;
        }

    }

    private String obterPalavra() {

        String p = String.valueOf(mTextoParser.charAt(mIndex));
        String ret = p;

        if (ALFABETO_INICIAL.contains(p)) {

            mIndex += 1;

            while (mIndex < mMaximo) {

                String d = String.valueOf(mTextoParser.charAt(mIndex));

                if (ALFABETO_FINAL.contains(d)) {
                    ret += d;
                } else {
                    mIndex -= 1;
                    break;
                }

                mIndex += 1;

            }

        }

        return ret;
    }


    private String esperarPalavra() {

        // System.out.println("Aqui " + String.valueOf(mDocumento.charAt(mIndex)));

        String ret = "";

        while (mIndex < mMaximo) {

            String l = String.valueOf(mTextoParser.charAt(mIndex));

            if (ALFABETO_INICIAL.contains(l)) {
                ret = obterPalavra();
                break;

            } else if (l.contentEquals(" ")) {
            } else if (l.contentEquals("\n")) {
            } else if (l.contentEquals("\t")) {

            } else {

            }

            mIndex += 1;

        }

        return ret;
    }

    private boolean EsperarPor(String Esperando) {

        // System.out.println("Aqui " + String.valueOf(mDocumento.charAt(mIndex)));

        boolean ret = false;

        while (mIndex < mMaximo) {

            String l = String.valueOf(mTextoParser.charAt(mIndex));

            if (Esperando.contentEquals(l)) {
                ret = true;
                mIndex += 1;
                break;

            } else if (l.contentEquals(" ")) {
            } else if (l.contentEquals("\n")) {
            } else if (l.contentEquals("\t")) {

            } else {

            }

            mIndex += 1;

        }

        return ret;
    }

    private void parserObjeto(Lista<DKGObjeto> lsPacotes) {

        String Palavra = decodifica(esperarPalavra());

        DKGObjeto NovoPacote = new DKGObjeto(Palavra);
        lsPacotes.adicionar(NovoPacote);

        boolean esperar_dp1 = EsperarPor(":");
        boolean esperar_dp2 = EsperarPor(":");
        boolean esperar_abrir = EsperarPor("{");

        if (esperar_dp1 && esperar_dp2 && esperar_abrir) {

            dentroObjeto(NovoPacote);

        } else {
            mParserErros.adicionar("ERRO : Era esperado :: { ");

        }

    }

    private void dentroObjeto(DKGObjeto eDKGObjetoCorrente) {

        while (mIndex < mMaximo) {
            String l = String.valueOf(mTextoParser.charAt(mIndex));

            if (l.contentEquals(" ")) {
            } else if (l.contentEquals("\n")) {
            } else if (l.contentEquals("\t")) {

            } else if (l.contentEquals("!")) {

                mIndex += 1;
                parserObjeto(eDKGObjetoCorrente.getObjetos());

            } else if (l.contentEquals("@")) {

                mIndex += 1;
                parserAtributo(eDKGObjetoCorrente.getAtributos());

            } else if (l.contentEquals("}")) {

                break;

            } else {
                mParserErros.adicionar(mIndex + " : " + l);

            }

            mIndex += 1;
        }

    }


    private String esperarTexto() {

        // System.out.println("Aqui " + String.valueOf(mDocumento.charAt(mIndex)));

        String ret = "";

        while (mIndex < mMaximo) {

            String l = String.valueOf(mTextoParser.charAt(mIndex));

            if (l.contentEquals("'")) {
                mIndex += 1;

                ret = buscandoTexto("'");
                break;
            } else if (l.contentEquals("\"")) {
                mIndex += 1;

                ret = buscandoTexto("\"");
                break;
            } else if (l.contentEquals(" ")) {
            } else if (l.contentEquals("\n")) {
            } else if (l.contentEquals("\t")) {

            } else {

            }

            mIndex += 1;

        }

        return ret;
    }

    private String buscandoTexto(String Finalizador) {

        // System.out.println("Aqui " + String.valueOf(mDocumento.charAt(mIndex)));

        String ret = "";

        while (mIndex < mMaximo) {

            String l = String.valueOf(mTextoParser.charAt(mIndex));

            if (l.contentEquals(Finalizador)) {
                mIndex += 1;
                break;

            } else {
                ret += l;
            }

            mIndex += 1;

        }

        return ret;
    }

    private void parserAtributo(Lista<DKGAtributo> ls_Identificadores) {

        String NomeIdentificador = decodifica(esperarPalavra());

        DKGAtributo IDC = new DKGAtributo(NomeIdentificador);
        ls_Identificadores.adicionar(IDC);

        if (EsperarPor("=")) {
            // System.out.println("Achou =");

            String eValor = esperarTexto();
            IDC.setValor(decodifica(eValor));
            // Parser_DentroPacote(NovoPacote);

        } else {
            System.out.println("ERRO : Era esperado =");

        }

    }

    public static String decodifica(String e) {
        e = e.replace("_H", "-");
        e = e.replace("_D", "\"");
        e = e.replace("_S", "'");
        e = e.replace("_A", "@");
        e = e.replace("_U", "_");

        return e;
    }





    // SERIALIZAR


    private String mSerializando = "";


    public String serializar(Lista<DKGObjeto> ls_objetos){
        mSerializando = "";
        serializar_objetos("", ls_objetos);
        return mSerializando;
    }

    public void serializando_adicionarLinha(String eLinha) {
        mSerializando += eLinha + "\n";
    }



    private void serializar_objetos(String ePrefixo, Lista<DKGObjeto> ls_objetos) {

        for (DKGObjeto PacoteC : ls_objetos) {

            if (PacoteC.getObjetos().getQuantidade() == 0 && PacoteC.getAtributos().getQuantidade() == 0) {

                serializando_adicionarLinha(ePrefixo + "!" + codifica(PacoteC.getNome()) + " :: { } ");

            } else if (PacoteC.getObjetos().getQuantidade() == 0 && PacoteC.getAtributos().getQuantidade() > 0) {

                String eIdentificadores = "";

                for (DKGAtributo IdentificadorC : PacoteC.getAtributos()) {
                    eIdentificadores += " @" + codifica(IdentificadorC.getNome()) + " = " + "\"" + codifica(IdentificadorC.getValor()) + "\"";
                }

                serializando_adicionarLinha(ePrefixo + "!" + codifica(PacoteC.getNome()) + " :: { " + eIdentificadores + " } ");

            } else if (PacoteC.getObjetos().getQuantidade() > 0) {

                serializando_adicionarLinha(ePrefixo + "!" + codifica(PacoteC.getNome()) + " :: { ");

                for (DKGAtributo IdentificadorC : PacoteC.getAtributos()) {
                    serializando_adicionarLinha(ePrefixo+"  @" + codifica(IdentificadorC.getNome()) + " = " + "\"" + codifica(IdentificadorC.getValor()) + "\"");
                }

                serializar_objetos(ePrefixo + "  ", PacoteC.getObjetos());

                serializando_adicionarLinha(ePrefixo+ "}");

            }


        }

    }


    public static String codifica(String e) {

        e = e.replace("_", "_U");
        e = e.replace("@", "_A");
        e = e.replace("'", "_S");
        e = e.replace("\"", "_D");
        e = e.replace("-", "_H");

        return e;
    }
}

package libs.dg;


import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.dkg.DKGAtributo;

import java.util.ArrayList;

public class IOParser {

    private final String ALFABETO_INICIAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_@.123456789";
    private final String ALFABETO_FINAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_@.0123456789";

    private String mDocumento;
    private int mIndex;
    private int mMaximo;

    public static String codifica(String e) {

        e = e.replace("@", "_A");
        e = e.replace("'", "_S");
        e = e.replace("\"", "_D");
        e = e.replace("-", "_H");

        return e;
    }

    public static String decodifica(String e) {
        e = e.replace("_H", "-");
        e = e.replace("_D", "\"");
        e = e.replace("_S", "'");
        e = e.replace("_A", "@");

        return e;
    }

    private ArrayList<String> mErros;

    private String mTexto;


    public IOParser() {

        mDocumento = "";

        mTexto = "";
        mIndex = 0;
        mMaximo = 0;
        mErros = new ArrayList<String>();

    }

    public ArrayList<String> getErros() {
        return mErros;
    }


    // PARSER

    public void parse(String eDocumento, DGObjeto eEmpacotador) {

        mErros.clear();

        eEmpacotador.getAtributos().clear();

        // mPacotes = new ArrayList<Pacote>();

        mDocumento = eDocumento;
        mIndex = 0;
        mMaximo = eDocumento.length();

        //   String eAnterior = "";

        while (mIndex < mMaximo) {
            String l = String.valueOf(eDocumento.charAt(mIndex));

            if (l.contentEquals(" ")) {
            } else if (l.contentEquals("\n")) {
            } else if (l.contentEquals("\t")) {
            } else if (l.contentEquals("\r")) {

            } else if (l.contentEquals("{")) {

                mIndex += 1;
                dentroObjeto(eEmpacotador);


            } else {

            }

            mIndex += 1;
        }

    }

    private String obterPalavra() {

        String p = String.valueOf(mDocumento.charAt(mIndex));
        String ret = p;

        if (ALFABETO_INICIAL.contains(p)) {

            mIndex += 1;

            while (mIndex < mMaximo) {

                String d = String.valueOf(mDocumento.charAt(mIndex));

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

            String l = String.valueOf(mDocumento.charAt(mIndex));

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

            String l = String.valueOf(mDocumento.charAt(mIndex));

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


    private void dentroObjeto(DGObjeto eDKGObjetoCorrente) {

        while (mIndex < mMaximo) {
            String l = String.valueOf(mDocumento.charAt(mIndex));

            if (l.contentEquals(" ")) {
            } else if (l.contentEquals("\n")) {
            } else if (l.contentEquals("\t")) {


            } else if (l.contentEquals("@")) {

                mIndex += 1;
                parserAtributo(eDKGObjetoCorrente.getAtributos());

            } else if (l.contentEquals("}")) {

                break;

            } else {
                mErros.add(mIndex + " : " + l);

            }

            mIndex += 1;
        }

    }


    private String esperarTexto() {

        // System.out.println("Aqui " + String.valueOf(mDocumento.charAt(mIndex)));

        String ret = "";

        while (mIndex < mMaximo) {

            String l = String.valueOf(mDocumento.charAt(mIndex));

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

            String l = String.valueOf(mDocumento.charAt(mIndex));

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

    private void parserAtributo(ArrayList<DGAtributo> ls_Identificadores) {

        String NomeIdentificador = decodifica(esperarPalavra());

        DGAtributo IDC = new DGAtributo(NomeIdentificador);
        ls_Identificadores.add(IDC);

        if (EsperarPor("=")) {
            // System.out.println("Achou =");

            String eValor = esperarTexto();
            IDC.setValor(decodifica(eValor));
            // Parser_DentroPacote(NovoPacote);

        } else {
            System.out.println("ERRO : Era esperado =");

        }

    }


// ESCREVER


    public void adicionarLinha(String eLinha) {
        mTexto += eLinha + "\n";
    }

    public void adicionar(String eMais) {
        mTexto += eMais;
    }

    public String toString() {
        return mTexto;
    }

    public String getTexto() {
        return mTexto;
    }

    public void montar(String ePrefixo, DGObjeto a) {


        adicionarLinha(ePrefixo + "{ ");

        for (DGAtributo IdentificadorC : a.getAtributos()) {
            adicionarLinha("  @" + codifica(IdentificadorC.getNome()) + " = " + "\"" + codifica(IdentificadorC.getValor()) + "\"");
        }

        adicionarLinha(ePrefixo + "}");

    }
}

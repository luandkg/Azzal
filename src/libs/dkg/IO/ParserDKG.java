package libs.dkg.IO;


import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.dkg.DKGAtributo;
import libs.luan.Lista;

public class ParserDKG {

    private final String ALFABETO_INICIAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_@.123456789";
    private final String ALFABETO_FINAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_@.0123456789";

    private String mDocumento;
    private int mIndex;
    private int mMaximo;

    private Lista<String> mErros;

    public ParserDKG() {

        mDocumento = "";
        mIndex = 0;
        mMaximo = 0;
        mErros = new Lista<String>();

    }

    public Lista<String> getErros() {
        return mErros;
    }



    // PARSER

    public void parse(String eDocumento, DKG eEmpacotador) {

        mErros.limpar();

        eEmpacotador.getObjetos().limpar();

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

            } else if (l.contentEquals("!")) {

                mIndex += 1;
                parserObjeto(eEmpacotador.getObjetos());

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

    private void parserObjeto(Lista<DKGObjeto> lsPacotes) {

        String Palavra = Textum.decodifica(esperarPalavra());

        DKGObjeto NovoPacote = new DKGObjeto(Palavra);
        lsPacotes.adicionar(NovoPacote);

        boolean esperar_dp1 = EsperarPor(":");
        boolean esperar_dp2 = EsperarPor(":");
        boolean esperar_abrir = EsperarPor("{");

        if (esperar_dp1 && esperar_dp2 && esperar_abrir) {

            dentroObjeto(NovoPacote);

        } else {
            mErros.adicionar("ERRO : Era esperado :: { ");

        }

    }

    private void dentroObjeto(DKGObjeto eDKGObjetoCorrente) {

        while (mIndex < mMaximo) {
            String l = String.valueOf(mDocumento.charAt(mIndex));

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
                mErros.adicionar(mIndex + " : " + l);

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

    private void parserAtributo(Lista<DKGAtributo> ls_Identificadores) {

        String NomeIdentificador = Textum.decodifica(esperarPalavra());

        DKGAtributo IDC = new DKGAtributo(NomeIdentificador);
        ls_Identificadores.adicionar(IDC);

        if (EsperarPor("=")) {
            // System.out.println("Achou =");

            String eValor = esperarTexto();
            IDC.setValor(Textum.decodifica(eValor));
            // Parser_DentroPacote(NovoPacote);

        } else {
            System.out.println("ERRO : Era esperado =");

        }

    }


}

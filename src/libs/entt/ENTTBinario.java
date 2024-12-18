package libs.entt;

import apps.app_atzum.AtzumCreator;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.luan.Lista;

public class ENTTBinario {


    public static Entidade PARSER_TO_OBJETO(String texto) {
        ENTTBinario parser = new ENTTBinario();

        Entidade e = new Entidade();
        parser.parse(texto, e);

        if (e.getEntidades().getQuantidade() == 1) {
            return e.getEntidades().get(0);
        }
        return e;
    }

    public static String TO_DOCUMENTO(Entidade e) {
        ENTTBinario parser = new ENTTBinario();

        Lista<Entidade> entidades = new Lista<Entidade>();
        entidades.adicionar(e);

        return parser.serializar(entidades);
    }


    public static void CORRECAO_VISUALIZAR(String arquivo){

        Lista<Entidade> lista = new Lista<Entidade>();

        for (DSItem item : DS.ler_todos(arquivo)) {
            Entidade e = ENTTBinario.PARSER_TO_OBJETO(item.getTexto());
            if(e.getEntidades().getQuantidade()>0){
                if(e.getEntidades().get(0).getEntidades().getQuantidade()>0) {
                    lista.adicionar(e.getEntidades().get(0).getEntidades().get(0));
                }

            }
        }

        ENTT.EXIBIR_TABELA_COM_TITULO(lista,"Logs - Tabela Mestre");

    }


    // DESSERIALIZAR

    private final String ALFABETO_INICIAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_@.123456789";
    private final String ALFABETO_FINAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_@.0123456789";

    private String mTextoParser;
    private int mIndex;
    private int mMaximo;
    private Lista<String> mParserErros;


    private void parse(String eDocumento, Entidade eEmpacotador) {

        mTextoParser = "";
        mIndex = 0;
        mMaximo = 0;
        mParserErros = new Lista<String>();

        eEmpacotador.getEntidades().limpar();

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
                parserObjeto(eEmpacotador.getEntidades());

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

    private void parserObjeto(Lista<Entidade> lsPacotes) {

        String Palavra = decodifica(esperarPalavra());

        Entidade NovoPacote = new Entidade();
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

    private void dentroObjeto(Entidade eDKGObjetoCorrente) {

        while (mIndex < mMaximo) {
            String l = String.valueOf(mTextoParser.charAt(mIndex));

            if (l.contentEquals(" ")) {
            } else if (l.contentEquals("\n")) {
            } else if (l.contentEquals("\t")) {

            } else if (l.contentEquals("!")) {

                mIndex += 1;
                parserObjeto(eDKGObjetoCorrente.getEntidades());

            } else if (l.contentEquals("@")) {

                mIndex += 1;
                parserAtributo(eDKGObjetoCorrente.tags());

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

    private void parserAtributo(Lista<Tag> ls_Identificadores) {

        String NomeIdentificador = decodifica(esperarPalavra());

        Tag IDC = new Tag(NomeIdentificador);
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

    private final String ENTIDADE_ITEM = codifica("Item");
    private String mSerializando = "";


    public String serializar(Lista<Entidade> ls_objetos) {
        mSerializando = "";
        serializar_objetos("", ls_objetos);
        return mSerializando;
    }

    public void serializando_adicionarLinha(String eLinha) {
        mSerializando += eLinha + "\n";
    }


    private void serializar_objetos(String ePrefixo, Lista<Entidade> ls_objetos) {

        for (Entidade PacoteC : ls_objetos) {

            if (PacoteC.getEntidades().getQuantidade() == 0 && PacoteC.tags().getQuantidade() == 0) {

                serializando_adicionarLinha(ePrefixo + "!" + ENTIDADE_ITEM + " :: { } ");

            } else if (PacoteC.getEntidades().getQuantidade() == 0 && PacoteC.tags().getQuantidade() > 0) {

                String eIdentificadores = "";

                for (Tag tag_corrente : PacoteC.tags()) {
                    eIdentificadores += " @" + codifica(tag_corrente.getNome()) + " = " + "\"" + codifica(tag_corrente.getValor()) + "\"";
                }

                serializando_adicionarLinha(ePrefixo + "!" + ENTIDADE_ITEM + " :: { " + eIdentificadores + " } ");

            } else if (PacoteC.getEntidades().getQuantidade() > 0) {

                serializando_adicionarLinha(ePrefixo + "!" + ENTIDADE_ITEM + " :: { ");

                for (Tag tag_corrente : PacoteC.tags()) {
                    serializando_adicionarLinha(ePrefixo + "  @" + codifica(tag_corrente.getNome()) + " = " + "\"" + codifica(tag_corrente.getValor()) + "\"");
                }

                serializar_objetos(ePrefixo + "  ", PacoteC.getEntidades());

                serializando_adicionarLinha(ePrefixo + "}");

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

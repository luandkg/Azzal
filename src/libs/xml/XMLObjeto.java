package libs.xml;


import libs.luan.Lista;

public class XMLObjeto {

    private String mNome;
    private int mTipo;
    private String mConteudo;

    private Lista<XMLAtributo> mAtributos;
    private Lista<XMLObjeto> mObjetos;

    public XMLObjeto(String eNome) {
        mNome = eNome;
        mConteudo = "";
        mTipo = XML.XML_OBJETO;
        mAtributos = new Lista<XMLAtributo>();
        mObjetos = new Lista<XMLObjeto>();

    }

    public XMLObjeto(String eNome, int eTipo) {
        mNome = eNome;
        mConteudo = "";
        mTipo = eTipo;
        mAtributos = new Lista<XMLAtributo>();
        mObjetos = new Lista<XMLObjeto>();
    }

    public int getTipo() {
        return mTipo;
    }

    public boolean isProcessador() {
        return mTipo == XML.XML_PROCESSADOR;
    }

    public boolean isObjeto() {
        return mTipo == XML.XML_OBJETO;
    }

    public boolean isComentario() {
        return mTipo == XML.XML_COMENTARIO;
    }


    public String getNome() {
        return mNome;
    }

    public void setNome(String eNome) {
        mNome = eNome;
    }

    public String getConteudo() {
        return mConteudo;
    }

    public void setConteudo(String eConteudo) {
        mConteudo = eConteudo;
    }


    public Lista<XMLObjeto> getObjetos() {
        return mObjetos;
    }


    public XMLObjeto getObjeto(String eNome) {
        XMLObjeto ret = null;

        for (XMLObjeto at : mObjetos) {
            if (at.getNome().contentEquals(eNome)) {
                ret = at;
                break;
            }
        }

        if (ret == null) {
            ret = new XMLObjeto(eNome, XML.XML_OBJETO);
            mObjetos.adicionar(ret);
        }

        return ret;
    }


    public void adicionar(XMLObjeto eObjeto) {
        mObjetos.adicionar(eObjeto);
    }


    public XMLAtributo atributo(String eNome) {
        XMLAtributo ret = null;

        for (XMLAtributo at : mAtributos) {
            if (at.getNome().contentEquals(eNome)) {
                ret = at;
                break;
            }
        }

        if (ret == null) {
            ret = new XMLAtributo(eNome, "");
            mAtributos.adicionar(ret);
        }
        return ret;
    }

    public XMLAtributo atributo(String eNome, String eValor) {
        XMLAtributo at = atributo(eNome);
        at.setValor(eValor);
        return at;
    }


    public Lista<XMLAtributo> getAtributos() {
        return mAtributos;
    }


    public void exibir_objetos() {

        for (XMLObjeto obj : mObjetos) {
            System.out.println("-->> " + obj.getNome());

            for (XMLAtributo at : obj.getAtributos()) {
                System.out.println("\t - " + at.getNome() + " = " + at.getValor());
            }

            for (XMLObjeto obj_filho : obj.getObjetos()) {
                System.out.println("\t - XML_OBJETO(" + obj_filho.getNome() + ")");
            }

        }
    }

    public void exibir_quadro() {

        for (XMLObjeto obj : mObjetos) {
            System.out.println("-->> " + obj.getNome());

            for (XMLAtributo at : obj.getAtributos()) {
                System.out.println("\t - " + at.getNome() + " = " + at.getValor());
            }

            for (XMLObjeto obj_filho : obj.getObjetos()) {
                System.out.println("\t - XML_OBJETO(" + obj_filho.getNome() + ") :: A = " + obj_filho.getAtributos().getQuantidade() + " O = " + obj_filho.getObjetos().getQuantidade() + " V = " + obj_filho.getConteudo().length());
            }

        }
    }

}

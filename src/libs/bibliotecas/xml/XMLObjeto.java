package libs.bibliotecas.xml;


import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.RefInt;

public class XMLObjeto {

    private String mNome;
    private int mTipo;
    private String mConteudo;

    private Lista<XMLAtributo> mAtributos;
    private Lista<XMLObjeto> mObjetos;

    private Opcional<XMLObjeto> mPai;

    public XMLObjeto(String eNome) {
        mNome = eNome;
        mConteudo = "";
        mTipo = XML.XML_OBJETO;
        mAtributos = new Lista<XMLAtributo>();
        mObjetos = new Lista<XMLObjeto>();
        mPai = Opcional.CANCEL();
    }

    public XMLObjeto(String eNome, int eTipo) {
        mNome = eNome;
        mConteudo = "";
        mTipo = eTipo;
        mAtributos = new Lista<XMLAtributo>();
        mObjetos = new Lista<XMLObjeto>();
        mPai = Opcional.CANCEL();

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


    public boolean temPai() {
        return mPai.isOK();
    }

    public XMLObjeto getPai() {
        return mPai.get();
    }

    public void setPai(XMLObjeto ePai) {
        mPai = Opcional.OK(ePai);
    }

    public String getNome() {
        return mNome;
    }

    public void setNome(String eNome) {
        mNome = eNome;
    }

    public boolean isNome(String eNome) {
        return mNome.contentEquals(eNome);
    }

    public String getConteudo() {
        return mConteudo;
    }

    public void setConteudo(String eConteudo) {
        mConteudo = eConteudo;
    }

    public boolean isConteudo(String eConteudo) {
        return mConteudo.contentEquals(eConteudo);
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


    public Lista<XMLObjeto> getObjetosComNome(String eNome) {
        Lista<XMLObjeto> ls = new Lista<XMLObjeto>();
        for (XMLObjeto obj : mObjetos) {
            if (obj.isNome(eNome)) {
                ls.adicionar(obj);
            }
        }
        return ls;
    }

    public boolean temObjetoComNome(String eNome) {
        boolean ret = false;
        for (XMLObjeto obj : mObjetos) {
            if (obj.isNome(eNome)) {
                ret = true;
                break;
            }
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

    public void exibir_se() {

        System.out.println("-->> " + getNome());

        for (XMLAtributo at : getAtributos()) {
            System.out.println("\t - " + at.getNome() + " = " + at.getValor());
        }

        for (XMLObjeto obj_filho : getObjetos()) {
            System.out.println("\t - XML_OBJETO(" + obj_filho.getNome() + ")");
        }

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
                System.out.println("\t - XML_OBJETO(" + obj_filho.getNome() + ") :: A = " + obj_filho.getAtributos().getQuantidade() + " O = " + obj_filho.getObjetos().getQuantidade() + " C = " + obj_filho.getConteudo().length());
            }

        }
    }

    public void exibir_quadro_detalhado() {

        for (XMLObjeto obj : mObjetos) {
            System.out.println("-->> " + obj.getNome());

            for (XMLAtributo at : obj.getAtributos()) {
                System.out.println("\t - " + at.getNome() + " = " + at.getValor());
            }

            for (XMLObjeto obj_filho : obj.getObjetos()) {
                System.out.println("\t - XML_OBJETO(" + obj_filho.getNome() + ") :: A = " + obj_filho.getAtributos().getQuantidade() + " O = " + obj_filho.getObjetos().getQuantidade() + " C = " + obj_filho.getConteudo().length());

                for (XMLAtributo at : obj_filho.getAtributos()) {
                    System.out.println("\t\t - " + at.getNome() + " = " + at.getValor());
                }
                System.out.println("\t\t - CONTEUDO :: " + obj_filho.getConteudo());
            }

        }
    }

    public XMLObjeto getObjetoSequencial(String eNome, int proc) {
        XMLObjeto ret = null;

        int i = 0;

        for (XMLObjeto at : mObjetos) {
            if (at.getNome().contentEquals(eNome)) {
                if (i == proc) {
                    ret = at;
                    break;
                }
                i += 1;
            }

        }

        if (ret == null) {
            ret = new XMLObjeto(eNome, XML.XML_OBJETO);
            ret.setPai(this);
        }

        return ret;
    }

    public void exibir() {
        for (XMLObjeto objeto : mObjetos) {
            interno("", objeto);
        }
    }

    private void interno(String prefixo, XMLObjeto pai) {

        if (pai.isComentario()) {

            System.out.println(prefixo + "XMLComentario :: " + pai.getConteudo());

        } else {

            if (pai.getConteudo().length() == 0) {
                System.out.println(prefixo + "XMLObjeto(" + pai.getNome() + ")");
            } else {
                System.out.println(prefixo + "XMLObjeto(" + pai.getNome() + ") -->> " + pai.getConteudo());
            }

            for (XMLAtributo at : pai.getAtributos()) {
                System.out.println(prefixo + " - " + at.getNome() + " = " + at.getValor());
            }

            for (XMLObjeto obj : pai.getObjetos()) {
                interno(prefixo + "     ", obj);
            }

        }

    }


    public int contagemCoisas() {

        RefInt contagem = new RefInt(0);

        for (XMLObjeto obj : mObjetos) {
            contagem.set(contagem.get() + 1);

            for (XMLAtributo at : obj.getAtributos()) {
                contagem.set(contagem.get() + 1);
            }

            for (XMLObjeto obj_filho : obj.getObjetos()) {
                contagem.set(contagem.get() + 1);
                contagem_interna(contagem, obj_filho);
            }

        }

        return contagem.get();
    }

    private int contagem_interna(RefInt contagem, XMLObjeto pai) {

        for (XMLAtributo at : pai.getAtributos()) {
            contagem.set(contagem.get() + 1);
        }

        for (XMLObjeto obj : pai.getObjetos()) {
            contagem.set(contagem.get() + 1);

            for (XMLAtributo at : obj.getAtributos()) {
                contagem.set(contagem.get() + 1);
            }

            for (XMLObjeto obj_filho : obj.getObjetos()) {
                contagem.set(contagem.get() + 1);
                contagem_interna(contagem, obj_filho);
            }

        }

        return contagem.get();
    }


}

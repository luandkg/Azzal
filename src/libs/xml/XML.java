package libs.xml;

import libs.luan.Lista;
import libs.luan.Texto;


public class XML {

    // DESENVOLVEDOR : LUAN ALVES FREITAS
    // DATA : 2023 05 21

    private Lista<XMLObjeto> mObjetos;


    public static final int XML_PROCESSADOR = 0;
    public static final int XML_OBJETO = 1;

    public static final int XML_COMENTARIO = 2;


    public XML() {
        mObjetos = new Lista<XMLObjeto>();
    }


    public void abrir(String arquivo) {

        XMLParser parser = new XMLParser();
        parser.parser(mObjetos, Texto.arquivo_ler(arquivo));

    }

    public void parser(String texto) {

        XMLParser parser = new XMLParser();
        parser.parser(mObjetos, texto);

    }

    public void adicionar(XMLObjeto eObjeto) {
        mObjetos.adicionar(eObjeto);
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

    public Lista<XMLObjeto> getObjetos() {
        return mObjetos;
    }

    public static XML PARSER_XML(String conteudo) {
        XML documento = new XML();
        documento.parser(conteudo);
        return documento;
    }


}

package libs.bibliotecas.xml;

import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.Texto;
import libs.luan.Unico;


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


    public static Lista<String> COLETAR_ATRIBUTOS(XML xml) {

        Unico<String> atributos = new Unico<String>(Strings.IGUALAVEL());

        for (XMLObjeto pai : xml.getObjetos()) {

            for (XMLAtributo att : pai.getAtributos()) {
                atributos.item(att.getNome());
            }

            for (XMLObjeto obj : pai.getObjetos()) {
                COLETAR_ATRIBUTOS_INTERNO(atributos, obj);
            }

        }

        return atributos.toLista();
    }

    public static Lista<String> COLETAR_ATRIBUTOS(XMLObjeto xml) {

        Unico<String> atributos = new Unico<String>(Strings.IGUALAVEL());

        for (XMLObjeto pai : xml.getObjetos()) {

            for (XMLAtributo att : pai.getAtributos()) {
                atributos.item(att.getNome());
            }

            for (XMLObjeto obj : pai.getObjetos()) {
                COLETAR_ATRIBUTOS_INTERNO(atributos, obj);
            }

        }

        return atributos.toLista();
    }

    private static void COLETAR_ATRIBUTOS_INTERNO(Unico<String> atributos, XMLObjeto pai) {

        for (XMLAtributo att : pai.getAtributos()) {
            atributos.item(att.getNome());
        }

        for (XMLObjeto obj : pai.getObjetos()) {
            COLETAR_ATRIBUTOS_INTERNO(atributos, obj);
        }

    }


    public static Lista<String> COLETAR_OBJETOS(XML xml) {

        Unico<String> objetos = new Unico<String>(Strings.IGUALAVEL());

        for (XMLObjeto pai : xml.getObjetos()) {

            objetos.item(pai.getNome());

            for (XMLObjeto obj : pai.getObjetos()) {
                COLETAR_OBJETOS_INTERNO(objetos, obj);
            }

        }

        return objetos.toLista();
    }

    public static Lista<String> COLETAR_OBJETOS(XMLObjeto xml) {

        Unico<String> objetos = new Unico<String>(Strings.IGUALAVEL());

        for (XMLObjeto pai : xml.getObjetos()) {

            objetos.item(pai.getNome());

            for (XMLObjeto obj : pai.getObjetos()) {
                COLETAR_OBJETOS_INTERNO(objetos, obj);
            }

        }

        return objetos.toLista();
    }

    private static void COLETAR_OBJETOS_INTERNO(Unico<String> objetos, XMLObjeto pai) {

        objetos.item(pai.getNome());

        for (XMLObjeto obj : pai.getObjetos()) {
            COLETAR_OBJETOS_INTERNO(objetos, obj);
        }

    }

    public static Lista<String> COLETAR_CONTEUDOS(XML xml) {

        Unico<String> conteudos = new Unico<String>(Strings.IGUALAVEL());

        for (XMLObjeto pai : xml.getObjetos()) {

            conteudos.item(pai.getConteudo());

            for (XMLObjeto obj : pai.getObjetos()) {
                COLETAR_CONTEUDOS_INTERNO(conteudos, obj);
            }

        }

        return conteudos.toLista();
    }

    public static Lista<String> COLETAR_CONTEUDOS(XMLObjeto xml) {

        Unico<String> conteudos = new Unico<String>(Strings.IGUALAVEL());

        for (XMLObjeto pai : xml.getObjetos()) {

            conteudos.item(pai.getConteudo());

            for (XMLObjeto obj : pai.getObjetos()) {
                COLETAR_CONTEUDOS_INTERNO(conteudos, obj);
            }

        }

        return conteudos.toLista();
    }

    private static void COLETAR_CONTEUDOS_INTERNO(Unico<String> conteudos, XMLObjeto pai) {

        conteudos.item(pai.getConteudo());

        for (XMLObjeto obj : pai.getObjetos()) {
            COLETAR_CONTEUDOS_INTERNO(conteudos, obj);
        }

    }


    public static Lista<XMLObjeto> COLETAR_OBJETOS_COM_CONTEUDO(XML xml, String conteudo) {

        Lista<XMLObjeto> conteudos = new Lista<XMLObjeto>();

        for (XMLObjeto pai : xml.getObjetos()) {

            if (pai.isConteudo(conteudo)) {
                conteudos.adicionar(pai);
            }

            for (XMLObjeto obj : pai.getObjetos()) {
                COLETAR_OBJETOS_COM_CONTEUDO_INTERNO(conteudos, obj, conteudo);
            }

        }

        return conteudos;
    }

    private static void COLETAR_OBJETOS_COM_CONTEUDO_INTERNO(Lista<XMLObjeto> conteudos, XMLObjeto pai, String conteudo) {

        if (pai.isConteudo(conteudo)) {
            conteudos.adicionar(pai);
        }
        for (XMLObjeto obj : pai.getObjetos()) {
            COLETAR_OBJETOS_COM_CONTEUDO_INTERNO(conteudos, obj, conteudo);
        }

    }


    public static Lista<XMLObjeto> COLETAR_OBJETOS_COM_ATRIBUTO(XML xml, String att_nome) {

        Lista<XMLObjeto> conteudos = new Lista<XMLObjeto>();


        for (XMLObjeto pai : xml.getObjetos()) {

            boolean tem_att = false;

            for (XMLAtributo att : pai.getAtributos()) {
                if (att.isIgual(att_nome)) {
                    tem_att = true;
                    break;
                }
            }

            if (tem_att) {
                conteudos.adicionar(pai);
            }

            for (XMLObjeto obj : pai.getObjetos()) {
                COLETAR_OBJETOS_COM_ATRIBUTO_INTERNO(conteudos, obj, att_nome);
            }

        }

        return conteudos;
    }

    public static Lista<XMLObjeto> COLETAR_OBJETOS_COM_ATRIBUTO(XMLObjeto xml, String att_nome) {

        Lista<XMLObjeto> conteudos = new Lista<XMLObjeto>();


        for (XMLObjeto pai : xml.getObjetos()) {

            boolean tem_att = false;

            for (XMLAtributo att : pai.getAtributos()) {
                if (att.isIgual(att_nome)) {
                    tem_att = true;
                    break;
                }
            }

            if (tem_att) {
                conteudos.adicionar(pai);
            }

            for (XMLObjeto obj : pai.getObjetos()) {
                COLETAR_OBJETOS_COM_ATRIBUTO_INTERNO(conteudos, obj, att_nome);
            }

        }

        return conteudos;
    }

    private static void COLETAR_OBJETOS_COM_ATRIBUTO_INTERNO(Lista<XMLObjeto> conteudos, XMLObjeto pai, String att_nome) {

        boolean tem_att = false;

        for (XMLAtributo att : pai.getAtributos()) {
            if (att.isIgual(att_nome)) {
                tem_att = true;
                break;
            }
        }

        if (tem_att) {
            conteudos.adicionar(pai);
        }

        for (XMLObjeto obj : pai.getObjetos()) {
            COLETAR_OBJETOS_COM_ATRIBUTO_INTERNO(conteudos, obj, att_nome);
        }

    }

}

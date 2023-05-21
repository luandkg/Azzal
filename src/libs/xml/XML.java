package libs.xml;

import libs.luan.Texto;

import java.util.ArrayList;

public class XML {

    // DESENVOLVEDOR : LUAN ALVES FREITAS
    // DATA : 2023 05 21

    private ArrayList<XMLObjeto> mObjetos;



    public static final int XML_PROCESSADOR = 0;
    public static final int XML_OBJETO = 1;

    public static final int XML_COMENTARIO = 2;


    public XML() {
        mObjetos = new ArrayList<XMLObjeto>();
    }


    public void abrir(String arquivo) {

        XMLParser parser = new XMLParser();
        parser.parser(mObjetos,Texto.arquivo_ler(arquivo));

    }

    public void adicionar(XMLObjeto eObjeto) {
        mObjetos.add(eObjeto);
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

}

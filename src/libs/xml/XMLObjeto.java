package libs.xml;

import java.util.ArrayList;

public class XMLObjeto {

    private String mNome;
    private int mTipo;
    private String mConteudo;

    private ArrayList<XMLAtributo> mAtributos;
    private ArrayList<XMLObjeto> mObjetos;

    public XMLObjeto(String eNome){
        mNome=eNome;
        mConteudo="";
mTipo=XML.XML_OBJETO;
        mAtributos=new ArrayList<XMLAtributo>();
        mObjetos=new ArrayList<XMLObjeto>();

    }

    public XMLObjeto(String eNome,int eTipo){
        mNome=eNome;
        mConteudo="";
        mTipo=eTipo;
        mAtributos=new ArrayList<XMLAtributo>();
        mObjetos=new ArrayList<XMLObjeto>();
    }

    public int getTipo(){return mTipo;}

    public boolean isProcessador(){return mTipo==XML.XML_PROCESSADOR;}
    public boolean isObjeto(){return mTipo==XML.XML_OBJETO;}
    public boolean isComentario(){return mTipo==XML.XML_COMENTARIO;}



    public String getNome(){return mNome;}
    public void setNome(String eNome){mNome=eNome;}

    public String getConteudo(){return mConteudo;}
    public void setConteudo(String eConteudo){mConteudo=eConteudo;}


    public ArrayList<XMLObjeto>  getObjetos(){return mObjetos;}

    public void adicionar(XMLObjeto eObjeto){mObjetos.add(eObjeto);}


    public XMLAtributo atributo(String eNome){
        XMLAtributo ret = null;

        for(XMLAtributo at : mAtributos){
            if(at.getNome().contentEquals(eNome)){
                ret=at;
                break;
            }
        }

        if(ret==null){
            ret=new XMLAtributo(eNome,"");
            mAtributos.add(ret);
        }
        return ret;
    }

    public XMLAtributo atributo(String eNome,String eValor) {
        XMLAtributo at = atributo(eNome);
        at.setValor(eValor);
        return at;
    }


    public ArrayList<XMLAtributo> getAtributos(){return mAtributos;}

    }

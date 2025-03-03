package libs.mitestum;

import libs.luan.Igualdade;
import libs.luan.Strings;

public class MitestumTexto extends MitestumTeste {

    private String mDireita;
    private String mEsquerda;

    public MitestumTexto( String eDireita, String eEsquerda){
        super(MitestumTipo.TESTE_IGUALDADE);
        mDireita=eDireita;
        mEsquerda=eEsquerda;
    }

    public String getDireita(){
        return mDireita;
    }


    public String getEsquerda(){
        return mEsquerda;
    }

    public boolean isOK(){
        return Strings.isIgual(mDireita,mEsquerda);
    }

    public String getDireitaTexto(){
        return String.valueOf(mDireita);
    }

    public String getEsquerdaTexto(){
        return String.valueOf(mEsquerda);
    }
}

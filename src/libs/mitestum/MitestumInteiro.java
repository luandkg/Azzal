package libs.mitestum;


public class MitestumInteiro extends MitestumTeste{

    private int mDireita;
    private int mEsquerda;

    public MitestumInteiro(MitestumTipo eTipo, int eDireita, int eEsquerda){
        super(eTipo);
        mDireita=eDireita;
        mEsquerda=eEsquerda;
    }

    public int getDireita(){
        return mDireita;
    }


    public int getEsquerda(){
        return mEsquerda;
    }

    public boolean isOK(){
        if(getTipo()==MitestumTipo.TESTE_IGUALDADE) {
            return mDireita == mEsquerda;
        }else    if(getTipo()==MitestumTipo.TESTE_DIFERENTE){
            return mDireita!=mEsquerda;
        }else    if(getTipo()==MitestumTipo.TESTE_MAIOR){
            return mDireita>mEsquerda;
        }else    if(getTipo()==MitestumTipo.TESTE_MENOR){
            return mDireita<mEsquerda;
        }else    if(getTipo()==MitestumTipo.TESTE_MAIOR_IGUAL){
            return mDireita>=mEsquerda;
        }else    if(getTipo()==MitestumTipo.TESTE_MENOR_IGUAL){
            return mDireita<=mEsquerda;
        }
        return false;
    }

    public String getDireitaTexto(){
        return String.valueOf(mDireita);
    }

    public String getEsquerdaTexto(){
        return String.valueOf(mEsquerda);
    }
}

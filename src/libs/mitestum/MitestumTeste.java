package libs.mitestum;

public abstract class MitestumTeste {

    private MitestumTipo mTipo;

    public MitestumTeste(MitestumTipo eTipo){
        mTipo=eTipo;
    }

    public MitestumTipo getTipo(){
        return mTipo;
    }

    public abstract boolean isOK();

    public abstract String getEsquerdaTexto();
    public abstract String getDireitaTexto();

}

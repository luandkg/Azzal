package algoritmos;

public class FitMensagem {

    public static final int TIPO_NORMAL = 0;
    public static final int TIPO_COMPACTADO = 1;


   private int mTipoLocal;
    private int mCabecalhoTipo;
    private boolean mTemDesenvolvedor;

    public FitMensagem(int eTipoLocal,int eCabecalhoTipo,boolean eTemDesenvolvedor){
        mTipoLocal=eTipoLocal;
        mCabecalhoTipo=eCabecalhoTipo;
        mTemDesenvolvedor=eTemDesenvolvedor;
    }


    public String getTipo(){
        if(mCabecalhoTipo==TIPO_NORMAL){
            return "Normal";
        }else{
            return "Compactado";
        }
    }

    public int getTipoLocal(){return mTipoLocal;}
}

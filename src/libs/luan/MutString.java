package libs.luan;

public class MutString {

    private String mTexto;
    private boolean mMudou;

    public MutString(){
        mTexto="";
        mMudou=false;
    }

    public MutString(String eInicio){
        mTexto=eInicio;
        mMudou=false;
    }

    public void set(String eTexto){
        mMudou=false;
        if(!mTexto.contentEquals(eTexto)){
            mMudou=true;
            mTexto=eTexto;
        }
    }

    public String get(){return mTexto;}
    public boolean mudou(){return mMudou;}

}

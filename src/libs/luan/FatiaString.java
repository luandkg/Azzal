package libs.luan;

public class FatiaString {

    private String mDados;
    private String mTipo;

    public FatiaString(String eDados) {
        mDados = eDados;
        mTipo = "STRING";
    }

    public FatiaString() {
        mDados = "";
        mTipo = "OTHER";
    }


    public String get() {
        return mDados;
    }

    public String getTipo() {
        return mTipo;
    }

    public void setTipo(String eTipo) {
        mTipo = eTipo;
    }

    public boolean mesmoTipo(String eTipo) {
        return mTipo.contentEquals(eTipo);
    }
}


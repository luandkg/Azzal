package libs.tronarko.Harrempluz;

public class HarremItem {

    private String mNome;
    private String mValor;

    public HarremItem(String eNome) {
        mNome = eNome;
        mValor = "";
    }

    public HarremItem(String eNome,String eValor) {
        mNome = eNome;
        mValor = eValor;
    }

    public String getNome() {
        return mNome;
    }

    public String getValor() {
        return mValor;
    }

    public void setValor(String eValor) {
        mValor = eValor;
    }

}

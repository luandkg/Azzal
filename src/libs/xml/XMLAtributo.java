package libs.xml;

public class XMLAtributo {

    private String mNome;
    private String mValor;

    public XMLAtributo(String eNome, String eValor) {
        mNome = eNome;
        mValor = eValor;
    }

    public XMLAtributo(String eNome) {
        mNome = eNome;
        mValor = "";
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

    public void setValor(int eValor) {
        mValor = String.valueOf(eValor);
    }

    public void setValor(boolean eValor) {
        mValor = String.valueOf(eValor);
    }

    public void setValor(long eValor) {
        mValor = String.valueOf(Long.toUnsignedString(eValor));
    }

    public void setValor(double eValor) {
        mValor = String.valueOf(eValor);
    }

    public void setLong(long eValor) {
        mValor = String.valueOf(Long.toUnsignedString(eValor));
    }

    public boolean isIgual(String eNome) {
        return mNome.contentEquals(eNome);
    }

    public double asInt() {
        return Integer.parseInt(mValor);
    }

    public double asDouble() {
        return Double.parseDouble(mValor);
    }

    public long asLong() {
        return Long.parseUnsignedLong(mValor);
    }

    public boolean asBoolean() {
        return Boolean.parseBoolean(mValor);
    }
}

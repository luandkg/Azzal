package libs.entt;

public class Tag {

    private String mNome;
    private String mValor;

    public Tag(String eNome) {
        mNome = eNome;
        mValor = "";
    }

    public Tag(String eNome, String eValor) {
        mNome = eNome;
        mValor = eValor;
    }

    public boolean is_nome(String eNome) {
        return mNome.contentEquals(eNome);
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


    // INTEIRO
    public void setInteiro(int eValor) {
        mValor = String.valueOf(eValor);
    }

    public int asInt() {
        return Integer.parseInt(mValor);
    }

    public int asInt(int ePadrao) {
        try {
            return Integer.parseInt(mValor);
        } catch (Exception e) {
            return ePadrao;
        }
    }


    // LONG
    public void setLong(long eValor) {
        mValor = String.valueOf(eValor);
    }

    public long asLong() {
        return Long.parseLong(mValor);
    }

    public long asLong(long ePadrao) {
        try {
            return Long.parseLong(mValor);
        } catch (Exception e) {
            return ePadrao;
        }
    }

    // DOUBLE
    public void setDouble(double eValor) {
        mValor = String.valueOf(eValor);
    }

    public double asDouble() {
        return Double.parseDouble(mValor);
    }

    public double asDouble(double ePadrao) {
        try {
            return Double.parseDouble(mValor);
        } catch (Exception e) {
            return ePadrao;
        }
    }
}

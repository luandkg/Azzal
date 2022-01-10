package Arch.Assembler;

public class RefString {

    private String mString;
    private int mIndex;
    private int mTamanho;

    public RefString(String eString) {
        mString = eString;
        mIndex = 0;
        mTamanho = mString.length();
    }

    public void setIndex(int eIndex) {
        mIndex = eIndex;
    }

    public int getIndex() {
        return mIndex;
    }

    public void aumentar(int e) {
        mIndex += e;
    }
    public void voltar(int e) {
        mIndex -= e;
    }
    public int getTamanho() {
        return mTamanho;
    }

    public String getString() {
        return mString;
    }

    public String getCorrente() {
        return String.valueOf(mString.charAt(mIndex));
    }
}

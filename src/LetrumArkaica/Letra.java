package LetrumArkaica;

public class Letra {

    private int mX1;
    private int mX2;
    private int mTamanho;


    private String mLetra;


    public Letra(String eLetra, int eX1, int eX2) {

        mLetra = eLetra;

        mX1 = eX1;
        mX2 = eX2;

        mTamanho = mX2 - mX1;


    }


    public int getX1() {
        return mX1;
    }

    public int getX2() {
        return mX2;
    }

    public int getTamanho() {
        return mTamanho;
    }

    public String getLetra() {
        return mLetra;
    }

    public boolean isLetra(String eLetra) {
        return mLetra.contentEquals(eLetra);
    }
}

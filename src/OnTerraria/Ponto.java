package OnTerraria;

public class Ponto {

    private boolean mSinal;
    private int mIndice;
    private int mResto;
    private boolean mValido;
    private final String NUMEROS = "0123456789";

    public Ponto(String ePonto) {

        int i = 0;
        int o = ePonto.length();

        mValido = true;
        mSinal = true;
        mIndice = 0;
        mResto = 0;

        String pSinal = "";
        String pIndice = "";
        String pResto = "";

        // OBTER SINAL
        while (i < o) {
            String c = String.valueOf(ePonto.charAt(i));

            if (isNumero(c)) {
                pSinal = "+";
                break;
            } else if (c.contentEquals("+")) {
                pSinal = "+";
                i += 1;
                break;
            } else if (c.contentEquals("-")) {
                pSinal = "-";
                i += 1;
                break;
            } else {
                mValido = false;
                break;
            }

        }

        boolean mTemResto = false;

        while (i < o && mValido == true) {
            String c = String.valueOf(ePonto.charAt(i));

            if (isNumero(c)) {
                pIndice += c;
            } else if (c.contentEquals(".")) {
                i += 1;
                mTemResto = true;
                break;
            } else {
                mValido = false;
                break;
            }

            i += 1;
        }

        while (i < o && mValido == true && mTemResto == true) {
            String c = String.valueOf(ePonto.charAt(i));

            if (isNumero(c)) {
                pResto += c;
            } else {
                mValido = false;
                break;
            }
            i += 1;

        }

        if (pSinal.contentEquals("+")) {
            mSinal = true;
        } else {
            mSinal = false;
        }

        if (pIndice.length() > 0) {
            mIndice = Integer.parseInt(pIndice);
        }

        if (pResto.length() > 0) {
            while (pResto.length() < 8) {
                pResto += "0";
            }
            mResto = Integer.parseInt(pResto);
        }

    }


    public boolean isNumero(String p) {

        boolean retorno = false;

        int i = 0;
        int o = NUMEROS.length();

        while (i < o) {
            String c = String.valueOf(NUMEROS.charAt(i));

            if (c.contentEquals(p)) {
                retorno = true;
                break;
            }

            i += 1;
        }

        return retorno;
    }

    public boolean isValido() {
        return mValido;
    }

    public boolean getSinal() {
        return mSinal;
    }

    public boolean isPositivo() {
        return mSinal == true;
    }

    public boolean isNegativo() {
        return mSinal == false;
    }

    public int getIndice() {
        return mIndice;
    }

    public int getResto() {
        return mResto;
    }


    public String toString() {

        String eMontando = "";
        if (isPositivo()) {
            eMontando = "+";
        } else {
            eMontando = "-";
        }

        eMontando += getIndice() + "." + getResto();

        return eMontando;
    }
}

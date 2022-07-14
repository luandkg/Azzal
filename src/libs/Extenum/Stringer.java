package libs.Extenum;


public class Stringer {

    private String ALFABETO;
    private int ALFABETO_MAX;


    public Stringer() {

        ALFABETO = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789 ()[]{}?!-.,<>//\\:;";
        ALFABETO_MAX = ALFABETO.length();

    }

    public byte getString(String l) {

        int procurando = 0;
        boolean enc = false;

        while (procurando < ALFABETO_MAX) {

            String pl = String.valueOf(ALFABETO.charAt(procurando));
            if (pl.contentEquals(l)) {
                enc = true;
                break;

            }
            procurando += 1;
        }

        if (!enc) {
            throw new IllegalArgumentException("Char nao encontrado : " + l);
        }

        return (byte) (procurando + 1);
    }

    public String stringFromByte(Byte b) {

        int procurando = 0;
        int pb = ((int) b) - 1;
        String ret = "";

        boolean enc = false;

        while (procurando < ALFABETO_MAX) {

            if (procurando == pb) {
                ret = String.valueOf(ALFABETO.charAt(procurando));
                enc = true;
                break;
            }

            procurando += 1;
        }

        if (!enc) {
            throw new IllegalArgumentException("Byte Char nao encontrado : " + pb);
        }

        return ret;
    }

    public byte[] stringToBytes(String s) {
        byte[] result = new byte[s.length()];

        int tamanho = s.length();

        int procurando = 0;

        while (procurando < tamanho) {

            byte v = getString(String.valueOf(s.charAt(procurando)));
            result[procurando] = v;

            procurando += 1;
        }


        return result;
    }

    public String readString(byte[] result) {

        String ret = "";

        int eIndex = 0;
        while (eIndex < result.length) {
            byte i = result[eIndex];
            // System.out.println("Passando : " + i);
            if (i == 0) {
                break;
            } else {
                ret += stringFromByte(i);
            }
            eIndex += 1;
        }

        return ret;
    }

}

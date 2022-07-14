package apps.appArch;

public class Utils {

    public static String getKb(int eValor) {

        int e = eValor / 1024;

        return e + " KB";
    }

    public static int byteToInt(byte eValor) {
        int iByte = (eValor & 0xFF);
        return iByte;
    }

    public static String getI8(I8 eValor) {

        String s1 = String.valueOf((int) (eValor.getByte() & 0xFF));


        if (s1.length() == 1) {
            s1 = "00" + s1;
        } else if (s1.length() == 2) {
            s1 = "0" + s1;
        }


        return s1 ;
    }

    public static String getI16(I16 eValor) {

        String s1 = String.valueOf((int) (eValor.getByte1() & 0xFF));
        String s2 = String.valueOf((int) (eValor.getByte2() & 0xFF));


        if (s1.length() == 1) {
            s1 = "00" + s1;
        } else if (s1.length() == 2) {
            s1 = "0" + s1;
        }

        if (s2.length() == 1) {
            s2 = "00" + s2;
        } else if (s2.length() == 2) {
            s2 = "0" + s2;
        }

        return s1 + ":" + s2;
    }

}

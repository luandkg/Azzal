package libs.arquivos.binario;

public class Inteiro {

    public static int byteToInt(byte b) {

        if (b >= 0) {
            return (int) b;
        } else {
            return 256 + (int) b;
        }

    }

}

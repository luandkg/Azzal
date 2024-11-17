package libs.matematica;

public class Tipo {

    public static final long u8 = 1;
    public static final long u64 = 8;


    public static long SOMAR(long... tipos) {
        long total = 0;
        for (long tipo : tipos) {
            total += tipo;
        }
        return total;
    }
}

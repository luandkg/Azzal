package libs.arquivos;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Bytes;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class StringView {

    private long mTamanho;
    private String mTexto;

    public StringView(String texto) {
        mTamanho = texto.length();
        mTexto = texto;
    }


    public static ArrayList<Byte> toBytes(String essa) {

        ArrayList<Byte> ret = new ArrayList<Byte>();

        byte[] b = Bytes.longToBytes((long) essa.length());
        int i = 0;
        int o = b.length;

        while (i < o) {
            ret.add(b[i]);
            i += 1;
        }

        byte[] b2 = essa.getBytes(StandardCharsets.UTF_8);
        int i2 = 0;
        int o2 = b2.length;

        while (i2 < o2) {
            ret.add(b2[i2]);
            i2 += 1;
        }


        return ret;
    }

    public static ArrayList<Byte> toBytes(String essa, int tamanho) {

        ArrayList<Byte> ret = new ArrayList<Byte>();

        byte[] b = Bytes.longToBytes((long) essa.length());
        int i = 0;
        int o = b.length;

        while (i < o) {
            ret.add(b[i]);
            i += 1;
        }

        byte[] b2 = essa.getBytes(StandardCharsets.UTF_8);
        int i2 = 0;
        int o2 = b2.length;

        while (i2 < o2) {
            ret.add(b2[i2]);
            i2 += 1;
        }

        while (i2 < tamanho) {
            ret.add((byte) 0);
            i2 += 1;
        }

        return ret;
    }


    public static String deArquivador(Arquivador arquivador) {

        long t = arquivador.get_u64();

        //System.out.println(" T : " + t);
        byte[] v = new byte[(int) t];
        for (int i = 0; i < t; i++) {
            v[i] = arquivador.get();

            //  System.out.println("v(" + i + ") : " + v[i]);

        }


        return new String(v, StandardCharsets.UTF_8);
    }

    public static String deArquivador(Arquivador arquivador, int tamanho) {

        long t = arquivador.get_u64();

        //System.out.println(" T : " + t);
        byte[] v = new byte[(int) t];

        int lendo = 0;

        for (int i = 0; i < t; i++) {
            v[i] = arquivador.get();
            //  System.out.println("v(" + i + ") : " + v[i]);
            lendo += 1;
        }

        int falta = tamanho - lendo;
        if (falta > 0) {
            arquivador.setPonteiro(arquivador.getPonteiro() + falta);
        }

        return new String(v, StandardCharsets.UTF_8);
    }

}

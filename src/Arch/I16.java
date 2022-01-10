package Arch;


public class I16 {

    private byte m1;
    private byte m2;

    public I16() {
        m1 = (byte) 0;
        m2 = (byte) 0;
    }

    public I16(byte v1, byte v2) {
        m1 = v1;
        m2 = v2;
    }

    public I16(I16 v) {
        m1 = v.m1;
        m2 = v.m2;
    }


    public void set(byte v1, byte v2) {
        m1 = v1;
        m2 = v2;
    }

    public void set(I16 v) {
        m1 = v.m1;
        m2 = v.m2;
    }

    public byte getByte1() {
        return m1;
    }

    public byte getByte2() {
        return m2;
    }

    public void aumentar(byte i) {

        int a = (int) (i & 0xFF);


        if (a < 0) {
            throw new IllegalArgumentException("Byte invalido = " + i);
        }

        if (a > 256) {
            throw new IllegalArgumentException("Byte invalido = " + i);
        }

        int i1 = (int) (m1 & 0xFF);
        int i2 = (int) (m2 & 0xFF);

        int dif = i2 + a;

        if (dif >= 256) {
            i1 += 1;
            dif -= 256;
        }

        i2 = dif;

        if (i1 >= 256) {
            i1 -= 256;
            i2 = i1;
            i1 = 0;
        }

        m1 = (byte) i1;
        m2 = (byte) i2;

    }


    public static I16 getFromInt(int valor) {

        int m1 = 0;
        int m2 = 0;
        while (valor >= 256) {
            valor -= 256;
            m1 += 1;
        }
        m2 = valor;

        return new I16((byte) m1, (byte) m2);
    }

    public String getDebug() {

        int i1 = (int) (m1 & 0xFF);
        int i2 = (int) (m2 & 0xFF);


        return i1 + "_" + i2;
    }

    public static I16 sum(I16 a, I16 b) {

        int i1 = (int) (a.getByte1() & 0xFF);
        int i2 = (int) (a.getByte2() & 0xFF);

        int i3 = (int) (b.getByte1() & 0xFF);
        int i4 = (int) (b.getByte2() & 0xFF);

        int i24 = i2 + i4;
        if (i24 > 255) {
            i24 -= 255;
            i3 += 1;
        }

        int i13 = i1 + i3;
        if (i13 > 255) {
            i13 -= 255;
        }

        return new I16((byte) i13, (byte) i24);
    }
}

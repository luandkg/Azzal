package apps.app_arch.Matematica;

import java.util.ArrayList;

public class u8 {

    private boolean bit_0;
    private boolean bit_1;
    private boolean bit_2;
    private boolean bit_3;
    private boolean bit_4;
    private boolean bit_5;
    private boolean bit_6;
    private boolean bit_7;


    public u8(byte b) {

        bit_0 = getSimOuNao(b, 7);
        bit_1 = getSimOuNao(b, 6);
        bit_2 = getSimOuNao(b, 5);
        bit_3 = getSimOuNao(b, 4);
        bit_4 = getSimOuNao(b, 3);
        bit_5 = getSimOuNao(b, 2);
        bit_6 = getSimOuNao(b, 1);
        bit_7 = getSimOuNao(b, 0);

    }

    public boolean getSimOuNao(byte b, int p) {
        int v = (int) (b >> (8 - (p + 1)) & 0x0001);
        if (v == 0) {
            return false;
        } else {
            return true;
        }
    }

    public u8(boolean ebit_7, boolean ebit_6, boolean ebit_5, boolean ebit_4, boolean ebit_3, boolean ebit_2, boolean ebit_1, boolean ebit_0) {

        bit_0 = ebit_0;
        bit_1 = ebit_1;
        bit_2 = ebit_2;
        bit_3 = ebit_3;
        bit_4 = ebit_4;
        bit_5 = ebit_5;
        bit_6 = ebit_6;
        bit_7 = ebit_7;

    }

    public boolean getBit_0() {
        return bit_0;
    }

    public boolean getBit_1() {
        return bit_1;
    }

    public boolean getBit_2() {
        return bit_2;
    }

    public boolean getBit_3() {
        return bit_3;
    }

    public boolean getBit_4() {
        return bit_4;
    }

    public boolean getBit_5() {
        return bit_5;
    }

    public boolean getBit_6() {
        return bit_6;
    }

    public boolean getBit_7() {
        return bit_7;
    }


    public String getString() {
        return getStringBit(bit_7) + getStringBit(bit_6) + getStringBit(bit_5) + getStringBit(bit_4) + getStringBit(bit_3) + getStringBit(bit_2) + getStringBit(bit_1) + getStringBit(bit_0);
    }

    public String getStringBit(boolean b) {
        if (b == true) {
            return "1";
        } else {
            return "0";
        }
    }


    public boolean getParte(int p) {
        boolean ret = false;

        if (p == 0) {
            ret = bit_0;
        } else if (p == 1) {
            ret = bit_1;
        } else if (p == 2) {
            ret = bit_2;
        } else if (p == 3) {
            ret = bit_3;
        } else if (p == 4) {
            ret = bit_4;
        } else if (p == 5) {
            ret = bit_5;
        } else if (p == 6) {
            ret = bit_6;
        } else if (p == 7) {
            ret = bit_7;
        }

        return ret;
    }

    public void setParte(int p, boolean e) {

        if (p == 0) {
            bit_0 = e;
        } else if (p == 1) {
            bit_1 = e;
        } else if (p == 2) {
            bit_2 = e;
        } else if (p == 3) {
            bit_3 = e;
        } else if (p == 4) {
            bit_4 = e;
        } else if (p == 5) {
            bit_5 = e;
        } else if (p == 6) {
            bit_6 = e;
        } else if (p == 7) {
            bit_7 = e;
        }

    }

    public int toint(boolean e) {
        if (e) {
            return 1;
        } else {
            return 0;
        }
    }

    public byte getByte() {

        int[] bits = new int[]{toint(bit_7), toint(bit_6), toint(bit_5), toint(bit_4), toint(bit_3), toint(bit_2), toint(bit_1), toint(bit_0)};

        byte b = encodeToByteArray(bits)[0];

        return b;
    }

    public int getByteNormalizado() {

        int i1 = (int) (getByte() & 0xFF);

        return i1;
    }

    private byte[] encodeToByteArray(int[] bits) {
        byte[] results = new byte[(bits.length + 7) / 8];
        int byteValue = 0;
        int index;
        for (index = 0; index < bits.length; index++) {

            byteValue = (byteValue << 1) | bits[index];

            if (index % 8 == 7) {
                results[index / 8] = (byte) byteValue;
            }
        }

        if (index % 8 != 0) {
            results[index / 8] = (byte) ((byte) byteValue << (8 - (index % 8)));
        }

        return results;
    }

    public u8 getCopia() {
        return new u8(bit_7, bit_6, bit_5, bit_4, bit_3, bit_2, bit_1, bit_0);
    }

    public static u8 get(byte b) {
        return new u8(b);
    }

    public static u8 sum(u8 b1, u8 b2) {

        u8 c = new u8((byte) 0);

        boolean carry = false;

        for (int p = 0; p < 8; p++) {

            boolean m1 = b1.getParte(p);
            boolean m2 = b2.getParte(p);

            if (m1 == false && m2 == false) {
                if (carry) {
                    carry = false;
                    c.setParte(p, true);
                } else {
                    c.setParte(p, false);
                }
            } else if (m1 == false && m2 == true) {

                if (carry) {
                    carry = true;
                    c.setParte(p, false);
                } else {
                    c.setParte(p, true);
                }

            } else if (m1 == true && m2 == false) {

                if (carry) {
                    carry = true;
                    c.setParte(p, false);
                } else {
                    c.setParte(p, true);
                }

            } else {

                if (carry) {
                    carry = true;
                    c.setParte(p, true);
                } else {
                    c.setParte(p, false);
                    carry = true;
                }


            }

        }

        return c;

    }

    public static u8 sub(u8 v1, u8 v2) {

        u8 b1 = v1.getCopia();
        u8 b2 = v2.getCopia();

        u8 c = new u8((byte) 0);


        for (int p = 0; p < 8; p++) {

            boolean m1 = b1.getParte(p);
            boolean m2 = b2.getParte(p);

            if (m1 == false && m2 == false) {

                c.setParte(p, false);

            } else if (m1 == false && m2 == true) {

                int f = p + 1;
                boolean mConseguiu = false;
                ArrayList<Integer> carry = new ArrayList<Integer>();
                while (f < 8 && mConseguiu == false) {
                    boolean mFuturo = b1.getParte(f);
                    if (mFuturo) {
                        mConseguiu = true;
                      //  b1.setParte(f, false);
                        break;
                    } else {
                        carry.add(f);
                    }
                    f += 1;
                }

                if (mConseguiu) {
                    c.setParte(p, true);
                } else {
                    c.setParte(p, false);
                }


            } else if (m1 == true && m2 == false) {

                c.setParte(p, true);


            } else {
                c.setParte(p, false);
            }

        }

        return c;

    }

}

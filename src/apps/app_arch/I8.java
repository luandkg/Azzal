package apps.app_arch;

public class I8 {

    private byte m;

    public I8() {
        m = (byte) 0;
    }

    public I8(byte v1) {
        m = v1;
    }

    public void set(byte v1) {
        m = v1;
    }

    public void set(I8 v1) {
        m = v1.getByte();
    }
    public byte getByte() {
        return m;
    }


}

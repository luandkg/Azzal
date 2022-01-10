package Arch;

public class CPU {

    private I8 cur8;
    private I16 cur16;

    private I8 regA;
    private I8 regB;
    private I8 regC;
    private I8 regD;

    private I16 regE;
    private I16 regF;
    private I16 regG;
    private I16 regH;

    private I16 mPC;

    private I8 mExecutando;

    private boolean mO8;
    private boolean mO16;

    private I16 VIDEO;

    public CPU() {

        cur8 = new I8();
        cur16 = new I16();

        regA = new I8();
        regB = new I8();
        regC = new I8();
        regD = new I8();
        regE = new I16();
        regF = new I16();
        regG = new I16();
        regH = new I16();

        mPC = new I16();
        mExecutando = new I8();

        mO8 = false;
        mO16 = false;

        VIDEO = new I16();
        VIDEO.set((byte) 0, (byte) 50);

    }

    public I16 getPC() {
        return mPC;
    }

    public I8 getCurI8() {
        return cur8;
    }

    public I16 getCurI16() {
        return cur16;
    }

    public I16 getVideo() {
        return VIDEO;
    }

    public I8 getExecutando() {
        return mExecutando;
    }

    public I8 getRegA() {
        return regA;
    }

    public I8 getRegB() {
        return regB;
    }

    public I8 getRegC() {
        return regC;
    }

    public I8 getRegD() {
        return regD;
    }

    public I16 getRegE() {
        return regE;
    }

    public I16 getRegF() {
        return regF;
    }

    public I16 getRegG() {
        return regG;
    }

    public I16 getRegH() {
        return regH;
    }

    public boolean getO8() {
        return mO8;
    }

    public boolean getO16() {
        return mO16;
    }

    public void setO8(boolean e) {
        mO8 = e;
    }

    public void setO16(boolean e) {
        mO16 = e;
    }
}

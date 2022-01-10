package Arch;

public class Montador {

    private int mInicio;
    private int mCorrente;
    private Memoria mMemoria;

    public Montador(Memoria eMemoria, int eInicio) {
        mMemoria = eMemoria;
        mInicio = eInicio;
        mCorrente = eInicio;
    }

    public void set(byte v) {
        mMemoria.set(mCorrente, (byte) v);
        mCorrente += 1;
    }

    public void setI8(I8 v) {
        set(Opcode.readi8);
        set(v.getByte());
    }

    public void setI16(I16 v) {
        set(Opcode.readi16);
        set(v.getByte1());
        set(v.getByte2());
    }

    public void setGoto(I16 v) {
        set(Opcode.setGoto);
        set(v.getByte1());
        set(v.getByte2());
    }

    public void setRegA() {
        set(Opcode.setRegA);
    }

    public void setRegB() {
        set(Opcode.setRegB);
    }

    public void setRegC() {
        set(Opcode.setRegC);
    }

    public void setRegD() {
        set(Opcode.setRegD);
    }

    public void addI8(byte b1 ,byte b2) {
        set(Opcode.addI8);
        set(b1);
        set(b2);
    }

    public void movI8(byte b1 ,byte b2) {
        set(Opcode.movI8);
        set(b1);
        set(b2);
    }

    public void encerrar() {
        set(Opcode.encerrar);
    }
}

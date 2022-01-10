package Arch;

public class Programas {

    public void progInfinitum(Memoria mMemoria) {

        Montador mMontador = new Montador(mMemoria, 0);
        mMontador.setI8(new I8((byte) 15));
        mMontador.setI16(new I16((byte) 150, (byte) 20));
        mMontador.setRegA();

        mMontador.setI16(new I16((byte) 150, (byte) 20));
        mMontador.setI16(new I16((byte) 200, (byte) 40));
        mMontador.setI16(new I16((byte) 200, (byte) 40));
        mMontador.setI16(new I16((byte) 200, (byte) 40));
        mMontador.setI16(new I16((byte) 200, (byte) 40));
        mMontador.setI16(new I16((byte) 200, (byte) 40));
        mMontador.setI16(new I16((byte) 200, (byte) 40));
        mMontador.setGoto(new I16((byte) 0, (byte) 0));


        mMontador.encerrar();
    }

    public void progSomador(Memoria mMemoria) {

        Montador mMontador = new Montador(mMemoria, 0);
        mMontador.setI8(new I8((byte) 0));
        mMontador.setRegA();
        mMontador.setI8(new I8((byte) 1));
        mMontador.setRegB();
        mMontador.setI8(new I8((byte) 10));
        mMontador.setRegC();
        mMontador.addI8(Opcode.REG_A, Opcode.REG_B);
        mMontador.movI8(Opcode.REG_A, Opcode.REG_D);

        // mMontador.jeg(Opcode.REG_A,Opcode.REG_C);
        mMontador.setGoto(new I16((byte) 0, (byte) 7));
        //mMontador.setGoto(new I16((byte) 0, (byte) 7));
        // mMontador.setI8(new I8((byte) 0));
        // mMontador.setRegA();

        mMontador.encerrar();
    }



}

package Arch;

public class Processador {

    private Memoria mMemoria;
    private CPU mCPU;


    public Processador(Memoria eMemoria, CPU eCPU) {
        mMemoria = eMemoria;
        mCPU = eCPU;
    }

    public void processe() {

        I8 mCorrente = mMemoria.getI8(mCPU.getPC());

        System.out.println("----------------------------------------------------------------");
        System.out.println("PC " + Utils.getI16(mCPU.getPC()) + " = " + Utils.byteToInt(mCorrente.getByte()));
        System.out.println("----------------------------------------------------------------");

        if (Opcode.isReadI8(mCorrente)) {

            mCPU.getPC().aumentar((byte) 1);
            mCPU.getCurI8().set(mMemoria.get(mCPU.getPC()));

        } else if (Opcode.isReadI16(mCorrente)) {

            mCPU.getPC().aumentar((byte) 1);
            byte b1 = mMemoria.get(mCPU.getPC());
            mCPU.getPC().aumentar((byte) 1);
            byte b2 = mMemoria.get(mCPU.getPC());
            mCPU.getCurI16().set(b1, b2);

        } else if (Opcode.isGet8(mCorrente)) {

            mCPU.getPC().aumentar((byte) 1);
            byte b1 = mMemoria.get(mCPU.getPC());
            mCPU.getPC().aumentar((byte) 1);
            byte b2 = mMemoria.get(mCPU.getPC());

            System.out.println("Get I8 :: " + b1 + "_" + b2 + " :: " + mMemoria.get(new I16(b1, b2)));

            // mCPU.getCurI8().set(mMemoria.get(new I16(b1, b2)));
            mCPU.getRegD().set(mMemoria.get(new I16(b1, b2)));


        } else if (Opcode.isRegA(mCorrente)) {
            mCPU.getRegA().set(mCPU.getCurI8());
        } else if (Opcode.isRegB(mCorrente)) {
            mCPU.getRegB().set(mCPU.getCurI8());
        } else if (Opcode.isRegC(mCorrente)) {
            mCPU.getRegC().set(mCPU.getCurI8());
        } else if (Opcode.isRegD(mCorrente)) {
            mCPU.getRegD().set(mCPU.getCurI8());

        } else if (Opcode.isTerminar(mCorrente)) {
            mCPU.getExecutando().set((byte) 255);
        } else if (Opcode.isGoto(mCorrente)) {

            mCPU.getPC().aumentar((byte) 1);
            byte b1 = mMemoria.get(mCPU.getPC());
            mCPU.getPC().aumentar((byte) 1);
            byte b2 = mMemoria.get(mCPU.getPC());
            mCPU.getPC().set(b1, b2);

            System.out.println("Goto :: " + b1 + "_" + b2);
            return;

        } else if (Opcode.isAddI8(mCorrente)) {

            mCPU.getPC().aumentar((byte) 1);
            byte b1 = mMemoria.get(mCPU.getPC());
            mCPU.getPC().aumentar((byte) 1);
            byte b2 = mMemoria.get(mCPU.getPC());

            mCPU.setO8(false);

            if (b1 == Opcode.setRegA || b1 == Opcode.setRegB || b1 == Opcode.setRegC || b1 == Opcode.setRegD) {


                if (b2 == Opcode.setRegA || b2 == Opcode.setRegB || b2 == Opcode.setRegC || b2 == Opcode.setRegD) {

                    I8 mValor1 = new I8();
                    I8 mValor2 = new I8();

                    if (b1 == Opcode.setRegA) {
                        mValor1.set(mCPU.getRegA());
                    } else if (b1 == Opcode.setRegB) {
                        mValor1.set(mCPU.getRegB());
                    } else if (b1 == Opcode.setRegC) {
                        mValor1.set(mCPU.getRegC());
                    } else if (b1 == Opcode.setRegD) {
                        mValor1.set(mCPU.getRegD());
                    }

                    if (b2 == Opcode.setRegA) {
                        mValor2.set(mCPU.getRegA());
                    } else if (b2 == Opcode.setRegB) {
                        mValor2.set(mCPU.getRegB());
                    } else if (b2 == Opcode.setRegC) {
                        mValor2.set(mCPU.getRegC());
                    } else if (b2 == Opcode.setRegD) {
                        mValor2.set(mCPU.getRegD());
                    }

                    int p1 = (int) (mValor1.getByte() & 0xFF);
                    int p2 = (int) (mValor2.getByte() & 0xFF);

                    int p3 = p1 + p2;

                    if (p3 >= 256) {
                        p3 -= 256;
                        mCPU.setO8(true);
                    }


                    System.out.println("ADD I8");
                    System.out.println("\t - P1 = " + p1);
                    System.out.println("\t - P2 = " + p2);
                    System.out.println("\t - R  = " + p3);
                    System.out.println("\t - O  = " + getObs(mCPU.getO8()));


                    mCPU.getRegD().set((byte) p3);

                } else {
                    mCPU.getExecutando().set(Opcode.FALHOU);
                    System.out.println("Falhou :: From");
                }

            } else {
                mCPU.getExecutando().set(Opcode.FALHOU);
                System.out.println("Falhou :: To");

            }

        } else if (Opcode.isMovI8(mCorrente)) {

            mCPU.getPC().aumentar((byte) 1);
            byte b1 = mMemoria.get(mCPU.getPC());
            mCPU.getPC().aumentar((byte) 1);
            byte b2 = mMemoria.get(mCPU.getPC());

            mCPU.setO8(false);

            if (b1 == Opcode.setRegA || b1 == Opcode.setRegB || b1 == Opcode.setRegC || b1 == Opcode.setRegD) {


                if (b2 == Opcode.setRegA || b2 == Opcode.setRegB || b2 == Opcode.setRegC || b2 == Opcode.setRegD) {

                    I8 mValor1 = getRegistrador(b1);
                    I8 mValor2 = getRegistrador(b2);

                    int p1 = (int) (mValor1.getByte() & 0xFF);
                    int p2 = (int) (mValor2.getByte() & 0xFF);


                    System.out.println("MOV I8");
                    System.out.println("\t - P1 = " + p1);
                    System.out.println("\t - P2 = " + p2);


                    mValor1.set(mValor2);


                } else {
                    mCPU.getExecutando().set(Opcode.FALHOU);
                    System.out.println("Falhou :: From");
                }


            } else {
                mCPU.getExecutando().set(Opcode.FALHOU);
                System.out.println("Falhou :: To");
            }

        } else if (Opcode.isSendi8(mCorrente)) {

            mCPU.getPC().aumentar((byte) 1);
            byte b1 = mMemoria.get(mCPU.getPC());
            mCPU.getPC().aumentar((byte) 1);
            byte b2 = mMemoria.get(mCPU.getPC());
            mCPU.getPC().aumentar((byte) 1);
            byte r = mMemoria.get(mCPU.getPC());

            mCPU.setO8(false);

            I16 mLocal = new I16(b1, b2);


            if (r == Opcode.setRegA || r == Opcode.setRegB || r == Opcode.setRegC || r == Opcode.setRegD) {

                I8 mValor1 = getRegistrador(r);

                int p1 = (int) (mValor1.getByte() & 0xFF);


                System.out.println("SEND I8");
                System.out.println("\t - MEM = " + mLocal.getDebug());
                System.out.println("\t - VALOR = " + p1);


                mMemoria.set(mLocal, mValor1.getByte());


            } else {
                mCPU.getExecutando().set(Opcode.FALHOU);
                System.out.println("Falhou :: From");
            }


        } else {
            System.out.println("Opcode desconhecido = " + Utils.byteToInt(mCorrente.getByte()));
        }


        mCPU.getPC().aumentar((byte) 1);


    }

    public I8 getRegistrador(byte b1) {

        I8 mValor1 = new I8();

        if (b1 == Opcode.setRegA) {
            mValor1 = mCPU.getRegA();
        } else if (b1 == Opcode.setRegB) {
            mValor1 = mCPU.getRegB();
        } else if (b1 == Opcode.setRegC) {
            mValor1 = mCPU.getRegC();
        } else if (b1 == Opcode.setRegD) {
            mValor1 = mCPU.getRegD();
        }


        return mValor1;
    }

    public int getObs(boolean e) {
        if (e) {
            return 1;
        } else {
            return 0;
        }
    }


}

package apps.app_arch;

public class Opcode {

    public final static byte REG_A = (byte) 200;
    public final static byte REG_B = (byte) 201;
    public final static byte REG_C = (byte) 202;
    public final static byte REG_D = (byte) 203;

    public final static byte readi8 = (byte) 10;
    public final static byte readi16 = (byte) 11;

    public final static byte get8 = (byte) 8;
    public final static byte get16 = (byte) 9;

    public final static byte setRegA = (byte) 12;
    public final static byte setRegB = (byte) 13;
    public final static byte setRegC = (byte) 14;
    public final static byte setRegD = (byte) 15;

    public final static byte setRegE = (byte) 16;
    public final static byte setRegF = (byte) 17;
    public final static byte setRegG = (byte) 18;
    public final static byte setRegH = (byte) 19;

    public final static byte addI8 = (byte) 20;
    public final static byte subI8 = (byte) 21;
    public final static byte muxI8 = (byte) 22;
    public final static byte divI8 = (byte) 23;
    public final static byte movI8 = (byte) 24;

    public final static byte addI16 = (byte) 30;
    public final static byte subI16 = (byte) 31;
    public final static byte muxI16 = (byte) 32;
    public final static byte divI16 = (byte) 33;
    public final static byte movI16 = (byte) 34;


    public final static byte setGoto = (byte) 100;

    public final static byte sendi8 = (byte) 90;


    public final static byte encerrar = (byte) 255;


    public final static byte EXECUTANDO = (byte) 0;
    public final static byte FALHOU = (byte) 1;

    public static boolean isReadI8(I8 eValor) {
        return (eValor.getByte() == Opcode.readi8);
    }

    public static boolean isGoto(I8 eValor) {
        return (eValor.getByte() == Opcode.setGoto);
    }

    public static boolean isGet8(I8 eValor) {
        return (eValor.getByte() == Opcode.get8);
    }


    public static boolean isReadI16(I8 eValor) {
        return (eValor.getByte() == Opcode.readi16);
    }

    public static boolean isRegA(I8 eValor) {
        return (eValor.getByte() == Opcode.setRegA);
    }

    public static boolean isRegB(I8 eValor) {
        return (eValor.getByte() == Opcode.setRegB);
    }

    public static boolean isRegC(I8 eValor) {
        return (eValor.getByte() == Opcode.setRegC);
    }

    public static boolean isRegD(I8 eValor) {
        return (eValor.getByte() == Opcode.setRegD);
    }

    public static boolean isRegE(I8 eValor) {
        return (eValor.getByte() == Opcode.setRegE);
    }

    public static boolean isRegF(I8 eValor) {
        return (eValor.getByte() == Opcode.setRegF);
    }

    public static boolean isRegG(I8 eValor) {
        return (eValor.getByte() == Opcode.setRegG);
    }

    public static boolean isRegH(I8 eValor) {
        return (eValor.getByte() == Opcode.setRegH);
    }


    public static boolean isTerminar(I8 eValor) {
        return (eValor.getByte() == Opcode.encerrar);
    }


    public static boolean isAddI8(I8 eValor) {
        return (eValor.getByte() == Opcode.addI8);
    }

    public static boolean isMovI8(I8 eValor) {
        return (eValor.getByte() == Opcode.movI8);
    }

    public static boolean isMovI16(I8 eValor) {
        return (eValor.getByte() == Opcode.movI16);
    }

    public static boolean isSendi8(I8 eValor) {
        return (eValor.getByte() == Opcode.sendi8);
    }


}

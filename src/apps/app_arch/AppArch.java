package apps.app_arch;

import apps.app_arch.Assembler.Assembler;
import apps.app_arch.Matematica.u8;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.geometria.Retangulo;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.Cronometro;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class AppArch extends Cena {

    private Memoria mMemoria;
    private CPU mCPU;
    private Processador mProcessador;
    private I16 VIDEO_POINT;

    private final int TEMPO_CLOCK = 5;

    private Fonte mLetramentoPreto;
    private Cronometro mCron;
    private int mInstrucoes = 0;
    private int mTempo = 0;

    private int TEMPO_FREQUENCIA = 100;
    private int mTempoFrequenciando = 0;
    private int mInstrucoesFrequenciando = 0;
    private double mVelocidade = 0;

    @Override
    public void iniciar(Windows eWindows) {

        for (int a = 0; a <= 10; a++) {

            u8 valor_a = u8.get((byte) a);
            u8 valor_b = u8.get((byte) (1));
            u8 valor_c = u8.sub(valor_a, valor_b);

            System.out.println(valor_a.getString() + "-" + valor_b.getString() + " = " + valor_c.getString());
            System.out.println(valor_a.getByteNormalizado() + "-" + valor_b.getByteNormalizado() + " = " + valor_c.getByteNormalizado());

        }


        eWindows.setTitle("Arch - libs.Luan Freitas");

        //  mLetramentoPreto = new Letramento(new FontePadrao());
        mLetramentoPreto = new FonteRunTime(new Cor(0, 0, 0), 15);

        mMemoria = new Memoria();
        mCPU = new CPU();
        mProcessador = new Processador(mMemoria, mCPU);

        System.out.println("Memoria : " + Utils.getKb(mMemoria.getCapacidade()));

        // progSomador();


        I16 ENTRY_POINT = new I16((byte) 0, (byte) 0);

        String eObjetoMontado = "res/montagem.o";

        Assembler mAssembler = new Assembler();
        mAssembler.compilar("res/montagem.l1", "res/montagem.l0", "res/montagem.o");

        try {
            RandomAccessFile mFile = new RandomAccessFile(new File(eObjetoMontado), "rw");

            long eTamanho = mFile.length();
            long eInicio = 0;
            while (eInicio < eTamanho) {
                byte b = mFile.readByte();
                mMemoria.set((int) eInicio, b);
                eInicio += 1;
            }


            mFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        mCPU.getPC().set(ENTRY_POINT);
        mCPU.getExecutando().set(Opcode.EXECUTANDO);

        mCron = new Cronometro(TEMPO_CLOCK);

        VIDEO_POINT = new I16(mCPU.getVideo());
        // mMemoria.set(VIDEO_POINT, (byte) 200);

        mAntes = new I16();
        mAntes.set(VIDEO_POINT);

    }


    public String toBits(final byte val) {
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            result.append((int) (val >> (8 - (i + 1)) & 0x0001));
        }
        return result.toString();
    }

    private I16 mAntes;

    @Override
    public void update(double dt) {

        mCron.esperar();

        if (mCPU.getExecutando().getByte() == Opcode.EXECUTANDO && mCron.foiEsperado()) {

            mProcessador.processe();

            mInstrucoes += 1;
            mTempo += TEMPO_CLOCK;

            mTempoFrequenciando += TEMPO_CLOCK;
            mInstrucoesFrequenciando += 1;

            if (mTempoFrequenciando >= TEMPO_FREQUENCIA) {
                mVelocidade = ((double) mInstrucoesFrequenciando / (double) mTempoFrequenciando);
                mInstrucoesFrequenciando = 0;
                mTempoFrequenciando -= TEMPO_FREQUENCIA;
            }

            dump();


            mMemoria.set(mAntes, (byte) 0);

            mAntes = I16.sum(VIDEO_POINT, mCPU.getPC());

            mMemoria.set(mAntes, (byte) 200);

        }

    }


    public void dump() {

        System.out.println("----------------------------------------------------------------");
        System.out.println("EX      : " + Utils.getI8(mCPU.getExecutando()));
        System.out.println("PC      : " + Utils.getI16(mCPU.getPC()));
        System.out.println("CUR_I8  : " + Utils.getI8(mCPU.getCurI8()));
        System.out.println("CUR_I16 : " + Utils.getI16(mCPU.getCurI16()));
        System.out.println("");
        System.out.println("REG_A   : " + Utils.getI8(mCPU.getRegA()) + "       " + "REG_E   : " + Utils.getI16(mCPU.getRegE()));
        System.out.println("REG_B   : " + Utils.getI8(mCPU.getRegB()) + "       " + "REG_F   : " + Utils.getI16(mCPU.getRegF()));
        System.out.println("REG_C   : " + Utils.getI8(mCPU.getRegC()) + "       " + "REG_G   : " + Utils.getI16(mCPU.getRegG()));
        System.out.println("REG_D   : " + Utils.getI8(mCPU.getRegD()) + "       " + "REG_H   : " + Utils.getI16(mCPU.getRegH()));
        System.out.println("");

        //  memoriaDump(0, 100, 10);

    }


    public void memoriaDump(int eInicio, int eTamanho, int eLinha) {

        int e = 0;

        for (int i = eInicio; i < (eInicio + eTamanho); i++) {

            int iByte = (mMemoria.get(i) & 0xFF);

            String s1 = String.valueOf(iByte);


            if (s1.length() == 1) {
                s1 = "00" + s1;
            } else if (s1.length() == 2) {
                s1 = "0" + s1;
            }

            System.out.print(s1 + " ");

            e += 1;
            if (e == eLinha) {
                e = 0;
                System.out.print("\n");
            }
        }

    }

    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);


        mLetramentoPreto.setRenderizador(mRenderizador);

        mLetramentoPreto.escreveLinha(100, 1000, 1170, "INSTRUÇÕES       ", ": " + mInstrucoes);
        mLetramentoPreto.escreveLinha(125, 1000, 1170, "TEMPO       ", ": " + mTempo);
        mLetramentoPreto.escreveLinha(150, 1000, 1170, "VELOCIDADE       ", ": " + mVelocidade);


        renderMemoriaDump(mRenderizador, 0, 300, 10);


        int menos = 100;

        mLetramentoPreto.escreveLinha(100, 700 - menos, 820 - menos, "EX       ", ": " + Utils.getI8(mCPU.getExecutando()));
        mLetramentoPreto.escreveLinha(125, 700 - menos, 820 - menos, "PC       ", ": " + Utils.getI16(mCPU.getPC()));
        mLetramentoPreto.escreveLinha(150, 700 - menos, 820 - menos, "CUR::I8  ", ": " + Utils.getI8(mCPU.getCurI8()));
        mLetramentoPreto.escreveLinha(175, 700 - menos, 820 - menos, "CUR::I16 ", ": " + Utils.getI16(mCPU.getCurI16()));

        mLetramentoPreto.escreveLinha(225, 700 - menos, 820 - menos, "REG::A   ", ": " + Utils.getI8(mCPU.getRegA()));
        mLetramentoPreto.escreveLinha(225, 1000 - menos, 1120 - menos, "REG::E   ", ": " + Utils.getI16(mCPU.getRegE()));

        mLetramentoPreto.escreveLinha(250, 700 - menos, 820 - menos, "REG::B   ", ": " + Utils.getI8(mCPU.getRegB()));
        mLetramentoPreto.escreveLinha(250, 1000 - menos, 1120 - menos, "REG::F   ", ": " + Utils.getI16(mCPU.getRegF()));

        mLetramentoPreto.escreveLinha(275, 700 - menos, 820 - menos, "REG::C   ", ": " + Utils.getI8(mCPU.getRegC()));
        mLetramentoPreto.escreveLinha(275, 1000 - menos, 1120 - menos, "REG::G   ", ": " + Utils.getI16(mCPU.getRegG()));

        mLetramentoPreto.escreveLinha(300, 700 - menos, 820 - menos, "REG::D   ", ": " + Utils.getI8(mCPU.getRegD()));
        mLetramentoPreto.escreveLinha(300, 1000 - menos, 1120 - menos, "REG::H   ", ": " + Utils.getI16(mCPU.getRegH()));

        mLetramentoPreto.escreveLinha(350, 700 - menos, 820 - menos, "O8       ", ": " + mProcessador.getObs(mCPU.getO8()));
        mLetramentoPreto.escreveLinha(375, 700 - menos, 820 - menos, "O16      ", ": " + mProcessador.getObs(mCPU.getO16()));

        I16 videoCursor = new I16(VIDEO_POINT);

        for (int y = 0; y < 40; y++) {
            for (int x = 0; x < 40; x++) {

                byte b = mMemoria.get(videoCursor);

                if (b != (byte) 0) {
                    mRenderizador.drawRect_Pintado(new Retangulo(650 + (x * 10), 500 + (y * 10), 10, 10), Cor.getRGB(Color.BLACK));
                }
                mRenderizador.drawRect(new Retangulo(650 + (x * 10), 500 + (y * 10), 10, 10), Cor.getRGB(Color.RED));
                videoCursor.aumentar((byte) 1);
            }
        }


    }

    public void renderMemoriaDump(Renderizador mRenderizador, int eInicio, int eTamanho, int eLinha) {

        int e = 0;
        int maisx = 0;
        int maisy = 0;

        int selx = 0;
        int sely = 0;
        boolean enc = false;

        for (int i = eInicio; i < (eInicio + eTamanho); i++) {

            int iByte = (mMemoria.get(i) & 0xFF);

            String s1 = String.valueOf(iByte);


            if (s1.length() == 1) {
                s1 = "00" + s1;
            } else if (s1.length() == 2) {
                s1 = "0" + s1;
            }

            int v1 = (mCPU.getPC().getByte1() & 0xFF);
            int v2 = (mCPU.getPC().getByte2() & 0xFF);
            int v = (v1 * 256) + v2;

            if (i == v) {
                selx = maisx;
                sely = maisy;
                enc = true;
            }

            mLetramentoPreto.escreva(50 + maisx, 100 + maisy, s1 + " ");
            maisx += 50;

            e += 1;
            if (e == eLinha) {
                e = 0;
                maisy += 25;
                maisx = 0;
            }
        }

        if (enc) {
            mRenderizador.drawRect(new Retangulo(50 + selx - 5, 100 + sely - 5, 50, 25), Cor.getRGB(Color.RED));
        }

    }

}

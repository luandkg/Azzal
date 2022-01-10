package Arch.Assembler.MontadorL0;

import Arch.Assembler.AST;
import Arch.Assembler.MontadorL1.Regiao;
import Arch.I16;
import Arch.Opcode;
import LuanDKG.ITexto;
import LuanDKG.Texto;

import java.io.*;
import java.util.ArrayList;

public class MontadorL0 {

    private ArrayList<String> mErros;

    public MontadorL0() {
        mErros = new ArrayList<String>();
    }

    public ArrayList<String> getErros() {
        return mErros;
    }

    public void montarL0(String eObjeto, String eObjetoMontado, int eIndexador, String eStart, ArrayList<Regiao> mText, ArrayList<AST> mData, ArrayList<AST> mBSS) {

        mErros.clear();


        ArrayList<Integer> mExecutavel = new ArrayList<Integer>();

        RefInt eRefInt = new RefInt(-1);

        ArrayList<Regiao> mTextNovos = new ArrayList<Regiao>();

        String eMontando = organizar(eIndexador, eStart,eRefInt, mText, mData, mBSS, mTextNovos);

        compilar(eRefInt,mData, mBSS,mTextNovos,mExecutavel);

        String eCompilado = arquivar(eObjetoMontado, mExecutavel);

        String eObjetoConstruindo = eMontando + "\n\n###################################################################\n\n" + eCompilado;


        System.out.println("");
        Texto.Escrever(eObjeto, eObjetoConstruindo);
        System.out.println("-->> Montado :: " + eObjeto);


    }

    public void compilar(RefInt ePosStart, ArrayList<AST> mData, ArrayList<AST> mBSS,ArrayList<Regiao> mTextNovos,ArrayList<Integer> mExecutavel){

        int p = 0;
        adicionar(mExecutavel, (Opcode.setGoto));
        adicionari16(mExecutavel, I16.getFromInt(ePosStart.get()));

        for (AST eAST : mData) {

            if (eAST.mesmoTipo("I8")) {
                int b = Integer.parseInt(eAST.getSubValor());
                adicionar(mExecutavel, (byte) b);
            } else if (eAST.mesmoTipo("I16")) {
                int b = Integer.parseInt(eAST.getSubValor());
                adicionari16(mExecutavel, I16.getFromInt(b));
            }

        }

        for (AST eAST : mBSS) {

            if (eAST.mesmoTipo("I8")) {
                adicionar(mExecutavel, (byte) 0);
            } else if (eAST.mesmoTipo("I16")) {
                adicionari16(mExecutavel, I16.getFromInt(0));
            }

        }

        for (Regiao eRegiao : mTextNovos) {

            for (AST eInstrucao : eRegiao.getASTS()) {


                if (eInstrucao.mesmoTipo("MOVI8")) {

                    adicionar(mExecutavel, (Opcode.movI8));
                    adicionar(mExecutavel, (getRegistrador(eInstrucao.getBranch("TO").getValor())));
                    adicionar(mExecutavel, (getRegistrador(eInstrucao.getBranch("FROM").getValor())));


                } else if (eInstrucao.mesmoTipo("ADDI8")) {

                    adicionar(mExecutavel, (Opcode.addI8));
                    adicionar(mExecutavel, (getRegistrador(eInstrucao.getBranch("TO").getValor())));
                    adicionar(mExecutavel, (getRegistrador(eInstrucao.getBranch("FROM").getValor())));

                } else if (eInstrucao.mesmoTipo("JMP")) {

                    adicionar(mExecutavel, (Opcode.setGoto));
                    adicionari16(mExecutavel, I16.getFromInt(Integer.parseInt(eInstrucao.getSubValor())));

                } else if (eInstrucao.mesmoTipo("LDI8")) {

                    adicionar(mExecutavel, (Opcode.get8));
                    adicionari16(mExecutavel, I16.getFromInt(Integer.parseInt(eInstrucao.getValor())));

                } else if (eInstrucao.mesmoTipo("SENDI8")) {

                    adicionar(mExecutavel, (Opcode.sendi8));
                    adicionari16(mExecutavel, I16.getFromInt(Integer.parseInt(eInstrucao.getValor())));
                    adicionar(mExecutavel, (getRegistrador(eInstrucao.getSubValor())));


                }

            }
        }



    }

    public String arquivar(String eObjetoMontado, ArrayList<Integer> mExecutavel) {

        System.out.println(" -->> Montagem Final : " + eObjetoMontado);

        ITexto eCompilado = new ITexto();

        try {
            RandomAccessFile mFile = new RandomAccessFile(new File(eObjetoMontado), "rw");

            for (Integer e : mExecutavel) {
                int v = (int) e;
                mFile.write((byte) v);
            }

            mFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        int p = -1;
        for (Integer e : mExecutavel) {
            int i = e;
            p = adicionarTexto(p, eCompilado, byteToIntString3((byte) i));
        }

        return eCompilado.toString();
    }

    public String organizar(int eIndexador, String eStart,RefInt ePosStart, ArrayList<Regiao> mText, ArrayList<AST> mData, ArrayList<AST> mBSS, ArrayList<Regiao> mTextNovos) {

        ITexto eMontando = new ITexto();
        ITexto eGuardando = new ITexto();


        ArrayList<AST> mTodos = new ArrayList<AST>();


        // eMontando.adicionarLinha("section .data");
        for (AST eAST : mData) {
            int eTamanho = 0;

            if (eAST.mesmoTipo("I8")) {
                eTamanho = 1;
            } else if (eAST.mesmoTipo("I16")) {
                eTamanho = 2;
            }

            eGuardando.adicionarLinha(trilhar(eAST.getAST("POS").getValor()) + " DATA " + eAST.getAST("POS").getValor() + " :: " + eAST.getTipo() + " = " + eAST.getSubValor() + " ");
            mTodos.add(eAST);
        }

        //eMontando.adicionarLinha("section .bss");
        for (AST eAST : mBSS) {

            int eTamanho = 0;

            if (eAST.mesmoTipo("I8")) {
                eTamanho = 1;
            } else if (eAST.mesmoTipo("I16")) {
                eTamanho = 2;
            }

            eGuardando.adicionarLinha(trilhar(eAST.getAST("POS").getValor()) + " BSS " + eAST.getAST("POS").getValor() + " :: " + eAST.getTipo() + " ");
            mTodos.add(eAST);
        }

        //eMontando.adicionarLinha("section .text");

        InstrucionadorL0 eInstrucionadorL0 = new InstrucionadorL0();


        for (Regiao eRegiao : mText) {


            Regiao eNovaRegiao = new Regiao(eRegiao.getNome());
            eNovaRegiao.setPos(eIndexador);
            mTextNovos.add(eNovaRegiao);


            if (eRegiao.getNome().contentEquals(eStart)) {
                ePosStart.set(eNovaRegiao.getPos());
            }

            for (AST eInstrucao : eRegiao.getASTS()) {

                if (eInstrucao.mesmoTipo("MOVI8")) {
                    eIndexador = eInstrucionadorL0.instrucao_MOV(eIndexador, eInstrucao, eNovaRegiao.getASTS(), mTodos, mErros);
                } else if (eInstrucao.mesmoTipo("ADDI8")) {
                    eIndexador = eInstrucionadorL0.instrucao_ADD(eIndexador, eInstrucao, eNovaRegiao.getASTS(), mTodos, mErros);
                } else if (eInstrucao.mesmoTipo("JMP")) {
                    eIndexador = eInstrucionadorL0.instrucao_JMP(eIndexador, eInstrucao, eNovaRegiao.getASTS(), mTodos, mErros);
                } else {
                    mErros.add("Instrucao desconhecida : " + eInstrucao.getTipo());
                }


            }


        }

        eMontando.adicionarLinha(trilhar("0") + " JMP " + ePosStart.get());

        eMontando.adicionar(eGuardando.toString());
        InstrucionadorEmTexto eInstrucionadorEmTexto = new InstrucionadorEmTexto();

        for (Regiao eRegiao : mTextNovos) {
            //  eMontando.adicionarLinha("\t@" + eRegiao.getNome() + " [ " + eRegiao.getPos() + " ] ) :");


            for (AST eInstrucao : eRegiao.getASTS()) {

                String eLinha = trilhar(eInstrucao.unico("POS").getValor()) + " ";

                if (eInstrucao.mesmoTipo("MOVI8")) {
                    eLinha += eInstrucionadorEmTexto.instrucao_MOV(eMontando, eInstrucao, mTodos, mErros);
                } else if (eInstrucao.mesmoTipo("ADDI8")) {
                    eLinha += eInstrucionadorEmTexto.instrucao_ADD(eMontando, eInstrucao, mTodos, mErros);
                } else if (eInstrucao.mesmoTipo("JMP")) {
                    eLinha += eInstrucionadorEmTexto.instrucao_JMP(eMontando, eInstrucao, mTodos, mTextNovos, mErros);
                } else if (eInstrucao.mesmoTipo("LDI8")) {
                    eLinha += eInstrucionadorEmTexto.instrucao_LDI8(eMontando, eInstrucao, mTodos, mErros);
                } else if (eInstrucao.mesmoTipo("SENDI8")) {
                    eLinha += eInstrucionadorEmTexto.instrucao_SENDI8(eMontando, eInstrucao, mTodos, mErros);
                } else {
                    mErros.add("Instrucao desconhecida : " + eInstrucao.getTipo());
                }

                eMontando.adicionarLinha(eLinha);

            }
        }



        return eMontando.toString();
    }


    public String trilhar(String e) {
        while (e.length() < 5) {
            e = "0" + e;
        }
        return e;
    }


    public byte getRegistrador(String reg) {
        byte b = (byte) 0;
        if (reg.contentEquals("A")) {
            b = Opcode.setRegA;
        } else if (reg.contentEquals("B")) {
            b = Opcode.setRegB;
        } else if (reg.contentEquals("C")) {
            b = Opcode.setRegC;
        } else if (reg.contentEquals("D")) {
            b = Opcode.setRegD;
        }
        return b;
    }

    public int adicionarTexto(int p, ITexto eCompilado, String v) {
        if (p >= 9) {
            p = 0;
            eCompilado.adicionar("\n");
        } else {
            p += 1;
        }
        eCompilado.adicionar(v + " ");
        return p;
    }


    public void adicionar(ArrayList<Integer> valores, byte v) {
        valores.add((int) v);
    }


    public void adicionari16(ArrayList<Integer> valores, I16 v) {
        adicionar(valores, v.getByte1());
        adicionar(valores, v.getByte2());
    }


    public int byteToInt(byte eValor) {
        int iByte = (eValor & 0xFF);
        return iByte;
    }

    public String byteToIntString(byte eValor) {
        int iByte = (eValor & 0xFF);
        return String.valueOf(iByte);
    }

    public String byteToIntString3(byte eValor) {
        int iByte = (eValor & 0xFF);
        String s = String.valueOf(iByte);
        if (s.length() == 1) {
            s = "00" + s;
        } else if (s.length() == 2) {
            s = "0" + s;
        }
        return s;
    }

}

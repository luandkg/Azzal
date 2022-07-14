package apps.appArch.Assembler.MontadorL0;

import apps.appArch.Assembler.AST;

import java.util.ArrayList;

public class InstrucionadorL0 {

    public int instrucao_MOV(int eAntes,AST eInstrucao, ArrayList<AST> mMontandoInstrucoes, ArrayList<AST> mTodos, ArrayList<String> mErros) {


        if (eInstrucao.getBranch("TO").getSubValor().contentEquals("REG") && eInstrucao.getBranch("FROM").getSubValor().contentEquals("VAR")) {

            AST eFrom = getVar(mTodos, eInstrucao.getBranch("FROM").getValor());


            AST eNova = new AST("");
            eNova.setValor(eFrom.getBranch("POS").getValor());
            eNova.unico("POS").setValor(String.valueOf(eAntes));
            eAntes += 3;

            if (eFrom.mesmoTipo("I8")) {
                eNova.setTipo("LDI8");
            } else if (eFrom.mesmoTipo("I16")) {
                eNova.setTipo("LDI16");
            }

            mMontandoInstrucoes.add(eNova);

            AST eCorrente = new AST("MOVI8");
            eCorrente.criarAST("TO").setValor(eInstrucao.getBranch("TO").getValor());
            eCorrente.getBranch("TO").setSubTipo("REG");
            eCorrente.unico("POS").setValor(String.valueOf(eAntes));

            if (eFrom.mesmoTipo("I8")) {
                eCorrente.criarAST("FROM").setValor("D");
                eCorrente.getBranch("FROM").setSubTipo("REG");
            } else if (eFrom.mesmoTipo("I16")) {
                eCorrente.criarAST("FROM").setValor("H");
                eCorrente.getBranch("FROM").setSubTipo("REG");
            }

            eCorrente.imprimir("");

            mMontandoInstrucoes.add(eCorrente);

            eAntes += 3;

        } else if (eInstrucao.getBranch("TO").getSubValor().contentEquals("VAR") && eInstrucao.getBranch("FROM").getSubValor().contentEquals("REG")) {

            AST eTo = getVar(mTodos, eInstrucao.getBranch("TO").getValor());


            System.out.println(" -->> Movendo " +eTo.getBranch("POS").getValor() );


            if (eTo.mesmoTipo("I8")) {

                AST eEnviarPar = new AST("SENDI8");
                eEnviarPar.setValor(eTo.getBranch("POS").getValor());
                eEnviarPar.setSubTipo(eInstrucao.getBranch("FROM").getValor());
                mMontandoInstrucoes.add(eEnviarPar);

                eAntes += 4;

            } else if (eTo.mesmoTipo("I16")) {

                AST eEnviarPar = new AST("SENDI16");
                eEnviarPar.setValor(eTo.getBranch("POS").getValor());
                eEnviarPar.setSubTipo(eInstrucao.getBranch("FROM").getValor());
                mMontandoInstrucoes.add(eEnviarPar);

                eAntes += 4;

            }


        } else if (eInstrucao.getBranch("TO").getSubValor().contentEquals("REG") && eInstrucao.getBranch("FROM").getSubValor().contentEquals("REG")) {

            AST eCorrente = new AST("MOVI8");
            eCorrente.criarAST("TO").setValor(eInstrucao.getBranch("TO").getValor());
            eCorrente.criarAST("FROM").setValor(eInstrucao.getBranch("FROM").getValor());

            eCorrente.getBranch("TO").setSubTipo("REG");
            eCorrente.getBranch("FROM").setSubTipo("REG");
            eCorrente.unico("POS").setValor(String.valueOf(eAntes));

            mMontandoInstrucoes.add(eCorrente);

            eAntes += 3;

        } else {
            mErros.add("Mov estranho --  " + eInstrucao.getBranch("TO").getSubValor() + " -> " + eInstrucao.getBranch("FROM").getSubValor());
        }

        return eAntes;
    }

    public int instrucao_ADD(int eAntes,AST eInstrucao, ArrayList<AST> mMontandoInstrucoes, ArrayList<AST> mTodos, ArrayList<String> mErros) {


        if (eInstrucao.getBranch("TO").getSubValor().contentEquals("REG") && eInstrucao.getBranch("FROM").getSubValor().contentEquals("VAR")) {

            AST eFrom = getVar(mTodos, eInstrucao.getBranch("FROM").getValor());

            AST eNova = new AST("");
            eNova.setValor(eFrom.getBranch("POS").getValor());
            eNova.unico("POS").setValor(String.valueOf(eAntes));
            eAntes += 3;

            if (eFrom.mesmoTipo("I8")) {
                eNova.setTipo("LDI8");
            } else if (eFrom.mesmoTipo("I16")) {
                eNova.setTipo("LDI16");
            }

            mMontandoInstrucoes.add(eNova);


            AST eCorrente = new AST("ADDI8");
            eCorrente.criarAST("TO").setValor(eInstrucao.getBranch("TO").getValor());
            eCorrente.getBranch("TO").setSubTipo("REG");
            eCorrente.unico("POS").setValor(String.valueOf(eAntes));
            eAntes += 3;

            if (eFrom.mesmoTipo("I8")) {
                eCorrente.criarAST("FROM").setValor("D");
                eCorrente.getBranch("FROM").setSubTipo("REG");
            } else if (eFrom.mesmoTipo("I16")) {
                eCorrente.criarAST("FROM").setValor("H");
                eCorrente.getBranch("FROM").setSubTipo("REG");
            }

            mMontandoInstrucoes.add(eCorrente);

        } else if (eInstrucao.getBranch("TO").getSubValor().contentEquals("REG") && eInstrucao.getBranch("FROM").getSubValor().contentEquals("REG")) {

            AST eCorrente = new AST("ADDI8");
            eCorrente.criarAST("TO").setValor(eInstrucao.getBranch("TO").getValor());
            eCorrente.criarAST("FROM").setValor(eInstrucao.getBranch("FROM").getValor());

            eCorrente.getBranch("TO").setSubTipo("REG");
            eCorrente.getBranch("FROM").setSubTipo("REG");
            eCorrente.unico("POS").setValor(String.valueOf(eAntes));

            mMontandoInstrucoes.add(eCorrente);

            eAntes += 3;

        } else {
            mErros.add("Add estranho -- " + eInstrucao.getBranch("TO").getSubValor() + " -> " + eInstrucao.getBranch("FROM").getSubValor());
        }

        return eAntes;
    }

    public int instrucao_JMP(int eAntes,AST eInstrucao, ArrayList<AST> mMontandoInstrucoes, ArrayList<AST> mTodos, ArrayList<String> mErros) {

        AST eCorrente = new AST("JMP");
        eCorrente.setValor(eInstrucao.getValor());
        eCorrente.unico("POS").setValor(String.valueOf(eAntes));
        mMontandoInstrucoes.add(eCorrente);

        eAntes+=3;

        return eAntes;
    }

    public AST getVar(ArrayList<AST> mTodos, String eNome) {
        AST ret = null;

        for (AST eInstrucao : mTodos) {
            if (eInstrucao.getValor().contentEquals(eNome)) {
                ret = eInstrucao;
                break;
            }
        }
        return ret;
    }

}

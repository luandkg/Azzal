package Arch.Assembler;

import Arch.Assembler.MontadorL1.Regiao;

import java.util.ArrayList;

public class Instrucoes {

    private RefString mCodigo;
    private Regiao mRegiao;
    private ArrayList<String> mErros;
    private Utils mUtils;


    public Instrucoes(RefString eCodigo, Regiao eRegiao, ArrayList<String> eErros) {
        mCodigo = eCodigo;
        mRegiao = eRegiao;
        mErros = eErros;
        mUtils = new Utils(eCodigo, eErros);
    }


    public void definicao(AST eAST) {

        while (mCodigo.getIndex() < mCodigo.getTamanho()) {

            String eCorrente = mCodigo.getCorrente();

            if (mUtils.isRegistrador(eCorrente)) {

                mCodigo.aumentar(1);
                String eNome = mUtils.getIdentificador();
                eAST.setValor(eNome);
                eAST.setSubTipo("REG");

                break;
            } else if (mUtils.isExclamacao(eCorrente)) {

                mCodigo.aumentar(1);
                String eNome = mUtils.getIdentificador();
                eAST.setValor(eNome);
                eAST.setSubTipo("VAR");

                break;
            } else if (mUtils.isEspaco(eCorrente)) {
            } else {
                break;
            }

            mCodigo.aumentar(1);
        }

    }

    public AST mov() {

        AST eInstrucao = new AST("MOVI8");
        AST eTo = eInstrucao.criarAST("TO");
        AST eFrom = eInstrucao.criarAST("FROM");

        definicao(eTo);

        if (!mUtils.esperarVigula()) {
            mErros.add("Era esperado uma vigula");
        }

        definicao(eFrom);

        return eInstrucao;

    }

    public AST add() {

        AST eInstrucao = new AST("ADDI8");
        AST eTo = eInstrucao.criarAST("TO");
        AST eFrom = eInstrucao.criarAST("FROM");

        definicao(eTo);

        if (!mUtils.esperarVigula()) {
            mErros.add("Era esperado uma vigula");
        }

        definicao(eFrom);

        return eInstrucao;

    }

    public AST jmp() {

        AST eInstrucao = new AST("JMP");

        while (mCodigo.getIndex() < mCodigo.getTamanho()) {

            String eCorrente = mCodigo.getCorrente();

            if (mUtils.isArroba(eCorrente)) {

                mCodigo.aumentar(1);
                String eNome = mUtils.getIdentificador();
                eInstrucao.setValor(eNome);

                break;
            } else if (mUtils.isEspaco(eCorrente)) {
            } else {
                break;
            }

            mCodigo.aumentar(1);
        }

        return eInstrucao;

    }

    public AST ldi8() {

        AST eInstrucao = new AST("LDI8");

        while (mCodigo.getIndex() < mCodigo.getTamanho()) {

            String eCorrente = mCodigo.getCorrente();

            if (mUtils.isNumero(eCorrente)) {

                String eNome = mUtils.getNumero();
                eInstrucao.setValor(eNome);

                break;
            } else if (mUtils.isEspaco(eCorrente)) {
            } else {
                break;
            }

            mCodigo.aumentar(1);
        }

        return eInstrucao;

    }

}

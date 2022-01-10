package Arch.Assembler;

import java.util.ArrayList;

public class AST {

    private String mTipo;
    private String mValor;
    private String mSubValor;

    private ArrayList<AST> mASTs;

    public AST(String eTipo) {
        mTipo = eTipo;
        mValor = "";
        mSubValor = "";
        mASTs = new ArrayList<AST>();
    }


    public String getTipo() {
        return mTipo;
    }

    public String getValor() {
        return mValor;
    }

    public String getSubValor() {
        return mSubValor;
    }

    public void setTipo(String eTipo) {
        mTipo = eTipo;
    }

    public void setValor(String eValor) {
        mValor = eValor;
    }

    public void setSubTipo(String eSubTipo) {
        mSubValor = eSubTipo;
    }

    public ArrayList<AST> getASTS() {
        return mASTs;
    }

    public AST criarAST(String eTipo) {
        AST eAST = new AST(eTipo);
        mASTs.add(eAST);
        return eAST;
    }


    public AST unico(String eNome) {
        AST eRet = null;

        for (AST eAST : mASTs) {
            if (eAST.getTipo().contentEquals(eNome)) {
                eRet = eAST;
                break;
            }
        }

        if (eRet == null) {
            eRet = new AST(eNome);
            mASTs.add(eRet);
        }
        return eRet;
    }

    public void imprimir(String ePrefixo) {

        System.out.println(ePrefixo + "-->> " + mTipo);
        if (mValor.length() > 0) {
            System.out.println(ePrefixo + "   - Valor : " + mValor);
        }
        if (mSubValor.length() > 0) {
            System.out.println(ePrefixo + "   - SubValor : " + mSubValor);
        }


        for (AST eAST : mASTs) {
            eAST.imprimir(ePrefixo + "\t");
        }

    }

    public String toString() {

        String eTexto = "";

        eTexto = (mTipo + " " + mValor + " " + mSubValor);


        return eTexto;

    }


    public AST getBranch(String eNome) {
        AST eRet = null;

        for (AST eAST : mASTs) {
            if (eAST.getTipo().contentEquals(eNome)) {
                eRet = eAST;
                break;
            }
        }

        return eRet;
    }

    public AST getAST(String eNome) {
        AST eRet = null;

        for (AST eAST : mASTs) {
            if (eAST.getTipo().contentEquals(eNome)) {
                eRet = eAST;
                break;
            }
        }

        return eRet;
    }

    public boolean mesmoTipo(String eTipo) {
        return mTipo.contentEquals(eTipo);
    }

    public boolean mesmoValor(String eValor) {
        return mValor.contentEquals(eValor);
    }

    public boolean mesmoSubValor(String eSubValor) {
        return mSubValor.contentEquals(eSubValor);
    }
}

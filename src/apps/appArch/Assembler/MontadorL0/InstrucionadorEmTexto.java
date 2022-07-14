package apps.appArch.Assembler.MontadorL0;

import apps.appArch.Assembler.AST;
import apps.appArch.Assembler.MontadorL1.Regiao;
import libs.OLLT.ITexto;

import java.util.ArrayList;

public class InstrucionadorEmTexto {

    public String instrucao_MOV(ITexto eMontando, AST eInstrucao, ArrayList<AST> mTodos, ArrayList<String> mErros) {

        String eLinha = "";


        String mDestino = "";
        String mOrigem = "";

        if (eInstrucao.getBranch("TO").getSubValor().contentEquals("REG") && eInstrucao.getBranch("FROM").getSubValor().contentEquals("REG")) {
            mDestino = "@" + eInstrucao.getBranch("TO").getValor();
            mOrigem = "@" + eInstrucao.getBranch("FROM").getValor();
        } else {
            mErros.add("Mov estranho -- a " + eInstrucao.getBranch("TO").getSubValor() + " -> " + eInstrucao.getBranch("FROM").getSubValor());
        }

        eLinha = "MOVI8 " + mDestino + " , " + mOrigem;

        return eLinha;
    }

    public String instrucao_ADD(ITexto eMontando, AST eInstrucao, ArrayList<AST> mTodos, ArrayList<String> mErros) {


        String mDestino = "";
        String mOrigem = "";

        if (eInstrucao.getBranch("TO").getSubValor().contentEquals("REG") && eInstrucao.getBranch("FROM").getSubValor().contentEquals("REG")) {
            mDestino = "@" + eInstrucao.getBranch("TO").getValor();
            mOrigem = "@" + eInstrucao.getBranch("FROM").getValor();
        } else {
            mErros.add("Add estranho -- " + eInstrucao.getBranch("TO").getSubValor() + " -> " + eInstrucao.getBranch("FROM").getSubValor());
        }

        String eLinha = "ADDI8 " + mDestino + " , " + mOrigem;


        return eLinha;
    }

    public String instrucao_SENDI8(ITexto eMontando, AST eInstrucao, ArrayList<AST> mTodos, ArrayList<String> mErros) {


        String mDestino = "";
        String mOrigem = "";

      //  if (eInstrucao.getBranch("TO").getSubValor().contentEquals("REG") && eInstrucao.getBranch("FROM").getSubValor().contentEquals("REG")) {
            mDestino = "#" + eInstrucao.getValor();
            mOrigem = "@" + eInstrucao.getSubValor();
        //} else {
        //    mErros.add("Sendi8 estranho -- " + eInstrucao.getBranch("TO").getSubValor() + " -> " + eInstrucao.getBranch("FROM").getSubValor());
       // }

        String eLinha = "SENDI8 " + mDestino + " , " + mOrigem;


        return eLinha;
    }

    public String instrucao_JMP(ITexto eMontando, AST eInstrucao, ArrayList<AST> mTodos, ArrayList<Regiao> mTextNovos, ArrayList<String> mErros) {

        int endereco = -1;

        for (Regiao eRegiao : mTextNovos) {
            if (eRegiao.getNome().contentEquals(eInstrucao.getValor())) {
                endereco = eRegiao.getPos();
                eInstrucao.setSubTipo(String.valueOf(endereco));
                break;
            }
        }

        // return  "JMP @" + eInstrucao.getValor() + " [ " + endereco + " ] ";
        return "JMP " + endereco + " ";

    }


    public String instrucao_LDI8(ITexto eMontando, AST eInstrucao, ArrayList<AST> mTodos, ArrayList<String> mErros) {


        return "LDI8 " + eInstrucao.getValor();
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

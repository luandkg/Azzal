package Arch.Assembler.MontadorL1;

import Arch.Assembler.AST;

import java.util.ArrayList;

public class Secao {

    private String mNome;
    private ArrayList<Regiao> mRegioes;
    private ArrayList<AST> mDatas;

    public Secao(String eNome) {
        mNome = eNome;
        mRegioes = new ArrayList<Regiao>();
        mDatas = new ArrayList<AST>();
    }

    public Regiao criarRegiao(String aNome) {
        Regiao eRegiao = new Regiao(aNome);
        mRegioes.add(eRegiao);
        return eRegiao;
    }

    public String getNome() {
        return mNome;
    }

    public ArrayList<Regiao> getRegioes() {
        return mRegioes;
    }

    public ArrayList<AST> getDatas() {
        return mDatas;
    }

    public void criarData(String aTipado,String eNome, String eValor) {

        AST eAST = new AST(aTipado);
        eAST.setValor(eNome);
        eAST.setSubTipo(eValor);
        mDatas.add(eAST);

    }


}

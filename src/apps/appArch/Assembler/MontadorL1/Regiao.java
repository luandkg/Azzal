package apps.appArch.Assembler.MontadorL1;

import apps.appArch.Assembler.AST;

import java.util.ArrayList;

public class Regiao {

    private String mNome;
    private ArrayList<AST> mASTs;
    private int mPos;

    public Regiao(String eNome) {
        mNome = eNome;
        mASTs = new ArrayList<AST>();
        mPos = -1;
    }

    public void setPos(int ePos) {
        mPos = ePos;
    }

    public int getPos() {
        return mPos;
    }

    public String getNome() {
        return mNome;
    }

    public ArrayList<AST> getASTS() {
        return mASTs;
    }

}

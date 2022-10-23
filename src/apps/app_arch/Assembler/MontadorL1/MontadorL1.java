package apps.app_arch.Assembler.MontadorL1;

import apps.app_arch.Assembler.*;
import libs.Luan.ArquivoTexto;

import java.util.ArrayList;

public class MontadorL1 {

    private ArrayList<String> mErros;
    private RefString mCodigo;
    private String mGlobal;
    private boolean mtemGlobal;
    private ArrayList<Secao> mSecoes;
    private Utils mUtils;
    private EscopoGlobal mEscopoGlobal;
    private EscopoSection mEscopoSection;

    private ArrayList<Regiao> mText;
    private ArrayList<AST> mData;
    private ArrayList<AST> mBSS;
    private ArrayList<AST> mTodos;


    public MontadorL1() {

        mCodigo = new RefString("");
        mErros = new ArrayList<String>();
        mGlobal = "";
        mtemGlobal = false;
        mSecoes = new ArrayList<Secao>();
        mUtils = new Utils(mCodigo, mErros);
        mEscopoGlobal = new EscopoGlobal(mCodigo, mErros);
        mEscopoSection = new EscopoSection(mCodigo, mSecoes, mErros);

        mText = new ArrayList<Regiao>();
        mData = new ArrayList<AST>();
        mBSS = new ArrayList<AST>();
        mTodos = new ArrayList<AST>();

    }

    public ArrayList<String> getErros() {
        return mErros;
    }


    public String getGlobal() {
        return mGlobal;
    }

    public boolean temGlobal() {
        return mtemGlobal;
    }

    public ArrayList<Secao> getSecoes() {
        return mSecoes;
    }

    public ArrayList<Regiao> getText() {
        return mText;
    }

    public ArrayList<AST> getData() {
        return mData;
    }

    public ArrayList<AST> getBSS() {
        return mBSS;
    }

    public int montar(String eArquivo, String eObjeto) {


        mCodigo = new RefString(ArquivoTexto.arquivo_ler(eArquivo));
        mErros.clear();
        mGlobal = "";
        mtemGlobal = false;
        mSecoes.clear();
        mUtils = new Utils(mCodigo, mErros);

        mEscopoGlobal = new EscopoGlobal(mCodigo, mErros);
        mEscopoSection = new EscopoSection(mCodigo, mSecoes, mErros);


        //System.out.println(mCodigo.getString());
        mText.clear();
        mData.clear();
        mBSS.clear();
        mTodos.clear();


        while (mCodigo.getIndex() < mCodigo.getTamanho()) {

            String eCorrente = mCodigo.getCorrente();


            if (mUtils.isEspaco(eCorrente)) {

            } else if (mUtils.isAlfa(eCorrente)) {


                String eNome = mUtils.getIdentificador();


                if (eNome.contentEquals("global")) {


                    if (!mtemGlobal) {
                        mtemGlobal = true;
                        mGlobal = mEscopoGlobal.init();
                    } else {
                        mErros.add("Ja existe um Global definido !");
                    }

                } else if (eNome.contentEquals("section")) {
                    mEscopoSection.init();
                } else {
                    mErros.add("Regiao desconhecida : " + eNome);
                    break;
                }

            } else {
                mErros.add("Valor desconhecido x: " + eCorrente);
                break;
            }

            mCodigo.aumentar(1);
        }


        System.out.println("------------------ COMPILED L1 -----------------------");


        System.out.println("-->> GLOBAL :: " + mGlobal);

        // eMontando.adicionarLinha("global " + mGlobal);

        boolean existeGlobal = false;


        for (Secao eSecao : mSecoes) {
            System.out.println("Secao : " + eSecao.getNome());

            if (eSecao.getNome().contentEquals(".text")) {

                for (Regiao eRegiao : eSecao.getRegioes()) {
                    if ((eRegiao.getNome()).contentEquals(mGlobal)) {
                        existeGlobal = true;
                    }
                }

                mText.addAll(eSecao.getRegioes());

            } else if (eSecao.getNome().contentEquals(".data")) {
                mData.addAll(eSecao.getDatas());
                mTodos.addAll(eSecao.getDatas());
            } else if (eSecao.getNome().contentEquals(".bss")) {
                mBSS.addAll(eSecao.getDatas());
                mTodos.addAll(eSecao.getDatas());
            }


            for (Regiao eRegiao : eSecao.getRegioes()) {
                System.out.println("\t - Regiao : " + eRegiao.getNome());

                for (AST eAST : eRegiao.getASTS()) {
                    eAST.imprimir("\t\t");
                }
            }

            for (AST eAST : eSecao.getDatas()) {
                eAST.imprimir("\t\t");
            }

        }

        if (mtemGlobal) {
            if (!existeGlobal) {
                mErros.add("Regiao em .text Global nao encontrada : " + mGlobal);
            }
        } else {
            mErros.add("Nao existe Global !");
        }

        int eInicio = 3;

        for (AST eAST : mData) {

            eAST.criarAST("POS").setValor(String.valueOf(eInicio));

            if (eAST.getTipo().contentEquals("I8")) {
                eInicio += 1;
            } else if (eAST.getTipo().contentEquals("I16")) {
                eInicio += 2;
            }

        }

        for (AST eAST : mBSS) {

            eAST.criarAST("POS").setValor(String.valueOf(eInicio));

            if (eAST.getTipo().contentEquals("I8")) {
                eInicio += 1;
            } else if (eAST.getTipo().contentEquals("I16")) {
                eInicio += 2;
            }
        }


        return eInicio;

    }

}

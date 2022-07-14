package apps.appArch.Assembler.MontadorL1;

import apps.appArch.Assembler.AST;
import apps.appArch.Assembler.Instrucoes;
import apps.appArch.Assembler.RefString;
import apps.appArch.Assembler.Utils;

import java.util.ArrayList;

public class EscopoText {


    private RefString mCodigo;
    private Secao mSecao;
    private ArrayList<String> mErros;

    private Utils mUtils;

    public EscopoText(RefString eCodigo, Secao eSecao, ArrayList<String> eErros) {
        mCodigo = eCodigo;
        mSecao = eSecao;
        mErros = eErros;
        mUtils = new Utils(eCodigo, eErros);

    }


    public void init(){

        while (mCodigo.getIndex() < mCodigo.getTamanho()) {

            String eCorrente = mCodigo.getCorrente();

            if (mUtils.isEspaco(eCorrente)) {

            } else if (mUtils.isArroba(eCorrente)) {

                mCodigo.aumentar(1);
                String eNome = mUtils.getIdentificador();

                if (!mUtils.esperaDoisPontos()) {
                    mErros.add("Era esperado dois pontos apos a definicao da regiao : " + eNome);
                }

                Regiao eRegiao = mSecao.criarRegiao(eNome);

                Instrucoes eInstrucoes = new Instrucoes(mCodigo, eRegiao, mErros);

                while (mCodigo.getIndex() < mCodigo.getTamanho()) {
                    int aIndex = mCodigo.getIndex();

                    String pCorrente = mCodigo.getCorrente();
                    if (mUtils.isEspaco(pCorrente)) {
                    } else if (mUtils.isAlfa(pCorrente)) {

                        String inst = mUtils.getIdentificador();


                        if (inst.toLowerCase().contentEquals("addi8")) {
                            AST eAST = eInstrucoes.add();
                            eRegiao.getASTS().add(eAST);
                            mCodigo.aumentar(1);
                        } else if (inst.toLowerCase().contentEquals("movi8")) {
                            AST eAST = eInstrucoes.mov();
                            eRegiao.getASTS().add(eAST);
                            mCodigo.aumentar(1);
                        } else if (inst.toLowerCase().contentEquals("jmp")) {
                            AST eAST = eInstrucoes.jmp();
                            eRegiao.getASTS().add(eAST);
                        } else if (inst.toLowerCase().contentEquals("ldi8")) {
                            AST eAST = eInstrucoes.ldi8();
                            eRegiao.getASTS().add(eAST);
                        } else if (inst.toLowerCase().contentEquals("section")) {
                            mCodigo.setIndex(aIndex - 1);
                            return;
                        } else {
                            mErros.add("Instrucao Deconhecida : " + inst);
                        }

                    } else {
                        mCodigo.voltar(1);
                        break;
                    }
                    mCodigo.aumentar(1);
                }


            } else {
                mErros.add("Valor desconhecido : " + eCorrente);
                break;
            }

            mCodigo.aumentar(1);
        }

    }
}

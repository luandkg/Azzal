package apps.appArch.Assembler.MontadorL1;

import apps.appArch.Assembler.RefString;
import apps.appArch.Assembler.Utils;

import java.util.ArrayList;

public class EscopoSection {

    private RefString mCodigo;
    private ArrayList<Secao> mSecoes;
    private ArrayList<String> mErros;
    private Utils mUtils;

    public EscopoSection(RefString eCodigo, ArrayList<Secao> eSecoes, ArrayList<String>eErros) {
        mCodigo = eCodigo;
        mSecoes = eSecoes;
        mErros = eErros;
        mUtils = new Utils(eCodigo, eErros);
    }

    public void init() {

        Secao eSecao = null;

        while (mCodigo.getIndex() < mCodigo.getTamanho()) {

            String eCorrente = mCodigo.getCorrente();

            if (mUtils.isEspaco(eCorrente)) {

            } else if (mUtils.isPonto(eCorrente)) {

                mCodigo.aumentar(1);
                String eNome = mUtils.getIdentificador();

                eSecao = new Secao("." + eNome);
                mSecoes.add(eSecao);
                break;

            } else {
                mErros.add("Valor desconhecido : " + eCorrente);
                break;
            }

            mCodigo.aumentar(1);
        }

        if (eSecao != null) {

            if (eSecao.getNome().contentEquals(".text")) {

                EscopoText eEscopoText = new EscopoText(mCodigo, eSecao, mErros);
                eEscopoText.init();
            } else if (eSecao.getNome().contentEquals(".data")) {

                EscopoData eData = new EscopoData(mCodigo, eSecao, mErros);
                eData.init();

            } else if (eSecao.getNome().contentEquals(".bss")) {

                EscopoBSS eBSS = new EscopoBSS(mCodigo, eSecao, mErros);
                eBSS.init();

            } else {
                mErros.add("Secao desconhecida : " + eSecao.getNome());
            }


        }

    }

}

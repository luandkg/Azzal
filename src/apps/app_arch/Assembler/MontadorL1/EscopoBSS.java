package apps.app_arch.Assembler.MontadorL1;

import apps.app_arch.Assembler.RefString;
import apps.app_arch.Assembler.Utils;

import java.util.ArrayList;

public class EscopoBSS {


    private RefString mCodigo;
    private Secao mSecao;
    private ArrayList<String> mErros;

    private Utils mUtils;

    public EscopoBSS(RefString eCodigo, Secao eSecao, ArrayList<String> eErros) {
        mCodigo = eCodigo;
        mSecao = eSecao;
        mErros = eErros;
        mUtils = new Utils(eCodigo, eErros);

    }


    public void init() {

        while (mCodigo.getIndex() < mCodigo.getTamanho()) {
            int aIndex = mCodigo.getIndex();
            String eCorrente = mCodigo.getCorrente();

            if (mUtils.isEspaco(eCorrente)) {

            } else if (mUtils.isAlfa(eCorrente)) {
                String eNome = mUtils.getIdentificador();

                if (eNome.contentEquals("i8")) {
                    criarData("I8");
                } else if (eNome.contentEquals("section")) {
                    mCodigo.setIndex(aIndex - 1);
                    break;
                } else {
                    mErros.add("Data tipo desconhecido : " + eNome);
                }

            } else {
                mErros.add("Valor desconhecido : " + eCorrente);
                break;
            }

            mCodigo.aumentar(1);
        }

    }

    public void criarData(String aTipado) {

        String eNome = "";

        while (mCodigo.getIndex() < mCodigo.getTamanho()) {

            String eCorrente = mCodigo.getCorrente();

            if (mUtils.isEspaco(eCorrente)) {

            } else if (mUtils.isAlfa(eCorrente)) {
                eNome = mUtils.getIdentificador();
                break;
            } else {
                mErros.add("Valor desconhecido : " + eCorrente);
                break;
            }

            mCodigo.aumentar(1);
        }


        mSecao.criarData(aTipado, eNome, "");

    }

}

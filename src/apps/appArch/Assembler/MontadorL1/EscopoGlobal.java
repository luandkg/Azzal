package apps.appArch.Assembler.MontadorL1;

import apps.appArch.Assembler.RefString;
import apps.appArch.Assembler.Utils;

import java.util.ArrayList;

public class EscopoGlobal {

    private RefString mCodigo;
    private ArrayList<String> mErros;
    private Utils mUtils;

    public EscopoGlobal(RefString eCodigo, ArrayList<String> eErros) {
        mCodigo = eCodigo;
        mErros = eErros;
        mUtils = new Utils(eCodigo, eErros);
    }

    public String init() {

        String mNomeGlobal = "";


        while (mCodigo.getIndex() < mCodigo.getTamanho()) {

            String eCorrente = mCodigo.getCorrente();



            if (mUtils.isEspaco(eCorrente)) {

            } else if (mUtils.isArroba(eCorrente)) {

                mCodigo.aumentar(1);
                String eNome = mUtils.getIdentificador();
                mNomeGlobal = eNome;

                break;

            } else {
                mErros.add("Valor desconhecido d: " + eCorrente);
                break;
            }

            mCodigo.aumentar(1);
        }

        return mNomeGlobal;

    }


}

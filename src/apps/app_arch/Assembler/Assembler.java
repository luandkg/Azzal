package apps.app_arch.Assembler;

import apps.app_arch.Assembler.MontadorL0.MontadorL0;
import apps.app_arch.Assembler.MontadorL1.MontadorL1;

import java.util.ArrayList;

public class Assembler {


    public Assembler() {

    }

    public void compilar(String eArquivo, String eObjeto,String eObjetoMontado) {

        MontadorL1 eMontadorL1 = new MontadorL1();
        int eIndexador = eMontadorL1.montar(eArquivo, eObjeto);

        ArrayList<String> mErros = new ArrayList<String>();

        if (eMontadorL1.getErros().size() == 0) {

            MontadorL0 eMontadorL0 = new MontadorL0();

            eMontadorL0.montarL0(eObjeto,eObjetoMontado, eIndexador,eMontadorL1.getGlobal(), eMontadorL1.getText(), eMontadorL1.getData(), eMontadorL1.getBSS());

            if (eMontadorL0.getErros().size() > 0) {
                mErros.addAll(eMontadorL0.getErros());
            }
        } else {
            mErros.addAll(eMontadorL1.getErros());
        }

        if (mErros.size() > 0) {
            System.out.println("------------------ ERROS -----------------------");
            for (String eErro : mErros) {
                System.out.println("Erro : " + eErro);
            }
        }


    }


}

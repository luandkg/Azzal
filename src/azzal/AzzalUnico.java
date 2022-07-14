package azzal;

import azzal.Cenarios.Cena;

public class AzzalUnico {

    public static void unico(String eNome, int eLargura, int eAltura, Cena eCena){

        Windows mWindows = new Windows(eNome, eLargura, eAltura);

        mWindows.CriarCenarioAplicavel(eCena);

        Thread mThread = new Thread(mWindows);
        mThread.start();

    }
}

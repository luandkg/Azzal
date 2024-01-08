package libs.azzal;

import libs.azzal.cenarios.Cena;

public class AzzalUnico {

    public static void unico(String eNome, int eLargura, int eAltura, Cena eCena) {

        Windows mWindows = new Windows(eNome, eLargura, eAltura);

        mWindows.CriarCenarioAplicavel(eCena);

        Thread mThread = new Thread(mWindows);
        mThread.start();

    }
}

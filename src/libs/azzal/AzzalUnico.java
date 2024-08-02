package libs.azzal;

import libs.azzal.cenarios.Cena;

public class AzzalUnico {

    public static void unico(String eNome, int eLargura, int eAltura, Cena eCena) {

        Windows mWindows = new Windows(eNome, eLargura, eAltura);

        mWindows.CriarCenarioAplicavel(eCena);

        Thread mThread = new Thread(mWindows);
        mThread.start();

    }

    public static void unico_posicionado(String eNome, int eLargura, int eAltura, Cena eCena,int px,int py) {

        Windows mWindows = new Windows(eNome, eLargura, eAltura,px,py);

        mWindows.CriarCenarioAplicavel(eCena);

        Thread mThread = new Thread(mWindows);
        mThread.start();

    }
}

package Wav;

import java.io.File;
import java.io.IOException;

public class VerFrameWav {

    public void ver() {

        ArquivoWav eArquivoWav = new ArquivoWav(new File("/home/luan/Música/musicas_hz/blurred.wav"));
        eArquivoWav.open();

        System.out.println(" :: " + eArquivoWav.getFileSize());

        double[] frame = new double[256];

        try {
            eArquivoWav.readFrames(frame);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int v = 0; v < 256; v++) {
            System.out.println("Frame ( " + v + " ) -->> " + frame[v]);
        }


        eArquivoWav.close();

    }
}

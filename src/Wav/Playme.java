package Wav;

import javax.sound.sampled.*;
import java.io.*;

public class Playme {

    public Playme(String filename) {

        int total, totalToRead, numBytesRead, numBytesToRead;
        byte[] buffer;
        boolean stopped;
        AudioFormat wav;
        TargetDataLine line;
        SourceDataLine lineIn;
        DataLine.Info info;
        File file;

        //AudioFormat(float sampleRate, int sampleSizeInBits,
        //int channels, boolean signed, boolean bigEndian)
        wav = new AudioFormat(44100, 16, 2, true, false);
        info = new DataLine.Info(SourceDataLine.class, wav);


        buffer = new byte[1024 * 4];
        numBytesToRead = 1024 * 4;
        total = 0;
        stopped = false;

        if (!AudioSystem.isLineSupported(info)) {
            System.out.print("no support for " + wav.toString());
        }

        try {
            // Obtain and open the line.
            lineIn = (SourceDataLine) AudioSystem.getLine(info);
            lineIn.open(wav);
            lineIn.start();

            FileInputStream fis = new FileInputStream(filename);
            totalToRead = fis.available();

            while (total < totalToRead && !stopped) {
                numBytesRead = fis.read(buffer, 0, numBytesToRead);
                if (numBytesRead == -1) break;
                total += numBytesRead;

                System.out.println("Linha -->> " + numBytesRead + " :: " + total);

                lineIn.write(buffer, 0, numBytesRead);

            }

            fis.close();


        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException nofile) {
            nofile.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }


}

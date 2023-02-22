package libs.Wav;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class CriarWav {

    public void criarArquivo(String eArquivo) {


        double sampleRate = 44100.0;
        double amplitude = 1.0;
        double seconds = 4.0;

        int samples_qtd = 2;

        float[] buffer = new float[(int) (seconds * sampleRate * samples_qtd)];

        int i = 0 * ((int) (seconds * sampleRate));
        int f = 1 * ((int) (seconds * sampleRate));


        for (int sample = i; sample < f; sample++) {
            double time = sample / sampleRate;
            buffer[sample] = getArcoSonoro(0.5, 440, 90, time);
        }

        i = 1 * ((int) (seconds * sampleRate));
        f = 2 * ((int) (seconds * sampleRate));

        for (int sample = i; sample < f; sample++) {
            double time = sample / sampleRate;
            buffer[sample] = getArcoSonoro(amplitude, 120, 200, time);
        }

        byte[] byteBuffer = toAudioBytes(buffer);

        System.out.println("Tam ::: " + byteBuffer.length);

        // Reduttor.compactar(byteBuffer, "/home/luan/Downloads/Music/out10.au");

        // salvar_arquivo(eArquivo, byteBuffer);
        salvarWav(eArquivo, byteBuffer);

    }

    public boolean salvarWav(String eArquivo, byte[] dados) {
        try {
            DataOutputStream outFile = new DataOutputStream(new FileOutputStream(eArquivo));

            // write the wav file per the wav file format
            outFile.writeBytes("RIFF");                 // 00 - RIFF
            outFile.writeInt(dados.length);     // 04 - how big is the rest of this file?
            outFile.writeBytes("WAVE");                 // 08 - WAVE
            outFile.writeBytes("fmt ");                 // 12 - fmt
            outFile.writeInt(16); // 16 - size of this chunk
            outFile.write(1);        // 20 - what is the audio format? 1 for PCM = Pulse Code Modulation
            outFile.write(2);  // 22 - mono or stereo? 1 or 2?  (or 5 or ???)
            outFile.write(16);        // 24 - samples per second (numbers per second)
            outFile.writeInt(0);      // 28 - bytes per second
            outFile.writeShort(16);    // 32 - # of bytes in one sample, for all channels
            outFile.writeShort(0); // 34 - how many bits in a sample(number)?  usually 16 or 24
            outFile.writeBytes("data");                 // 36 - data
            outFile.writeInt(dados.length);      // 40 - how big is this data chunk
            outFile.write(dados);                      // 44 - the actual data itself - just a long string of numbers
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    private float getArcoSonoro(double amplitude, double frequency, double frequency2, double time) {
        double twoPiF = 2 * Math.PI * frequency;
        double piF = Math.PI * frequency2;

        return (float) (amplitude * Math.cos(piF * time) * Math.sin(twoPiF * time));

    }

    private byte[] toAudioBytes(float[] buffer) {

        byte[] byteBuffer = new byte[buffer.length * 2];

        int bufferIndex = 0;
        for (int i = 0; i < byteBuffer.length; i++) {
            final int x = (int) (buffer[bufferIndex++] * 32767.0);
            byteBuffer[i++] = (byte) x;
            byteBuffer[i] = (byte) (x >>> 8);
        }

        return byteBuffer;

    }


    public void salvar_arquivo(String eArquivo, byte[] byteBuffer) {

        try {
            DataOutputStream outFile = new DataOutputStream(new FileOutputStream(eArquivo));
            outFile.write(byteBuffer);
            outFile.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void salvar_arquivo_ate(String eArquivo, byte[] byteBuffer, int ate) {

        try {
            DataOutputStream outFile = new DataOutputStream(new FileOutputStream(eArquivo));
            outFile.write(byteBuffer, 0, ate);
            outFile.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}

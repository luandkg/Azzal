package apps.app_azzal;


import javax.sound.sampled.*;
import java.io.*;


public class Audio {

    public void toque() {


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

        //   AudioInputStream inputStream = toPulse(byteBuffer);

        InputStream inputStream = getWAVSound("/home/luan/Downloads/Music/out10.wav");

        System.out.println("--> COMECAR");

        try {
            Clip clip = AudioSystem.getClip();
            //  clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("--> TERMINAR");

        // Reduttor.compactar(byteBuffer, "/home/luan/Downloads/Music/out10.au");

        toWAV("/home/luan/Downloads/Music/out10.wav", byteBuffer, buffer, sampleRate);

    }

    public InputStream getWAVSound(String eArquivo) {

        AudioInputStream ais = null;
        try {
            ais = AudioSystem.getAudioInputStream(new File(eArquivo));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = new byte[0];
        try {
            data = new byte[ais.available()];
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ais.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(ais.getFrameLength());

        InputStream is = new ByteArrayInputStream(data);

        return is;
    }

    public AudioInputStream toPulse(byte[] buffer) {

        System.out.println("A :: " + (Math.sin(4 * Math.PI * 523.25)));

        byte[] b = new byte[64000];

        //lets make a 440hz tone for 1s at 32kbps, and 523.25hz.
        for (int i = 0; i < b.length / 2; i++) {
            b[i * 2 + 1] = (byte) (127 * Math.sin(4 * Math.PI * 440.0 / b.length * i));
            b[i * 2] = (byte) (127 * Math.sin(4 * Math.PI * 523.25 / b.length * i));
        }

        AudioInputStream stream = new AudioInputStream(new ByteArrayInputStream(b), new AudioFormat(44100, 16, 1, true, false), b.length);

        return stream;
    }

    public AudioInputStream toPulseBuffer(byte[] buffer) {

        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        AudioInputStream stream = null;
        try {
            stream = AudioSystem.getAudioInputStream(bais);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }

    public float getArcoSonoro(double amplitude, double frequency, double frequency2, double time) {
        double twoPiF = 2 * Math.PI * frequency;
        double piF = Math.PI * frequency2;

        return (float) (amplitude * Math.cos(piF * time) * Math.sin(twoPiF * time));

    }

    public byte[] toAudioBytes(float[] buffer) {

        byte[] byteBuffer = new byte[buffer.length * 2];

        int bufferIndex = 0;
        for (int i = 0; i < byteBuffer.length; i++) {
            final int x = (int) (buffer[bufferIndex++] * 32767.0);
            byteBuffer[i++] = (byte) x;
            byteBuffer[i] = (byte) (x >>> 8);
        }

        return byteBuffer;

    }

    public void toWAV(String eArquivo, byte[] byteBuffer, float[] buffer, double sampleRate) {

        File out = new File(eArquivo);

        final boolean bigEndian = false;
        final boolean signed = true;

        final int bits = 16;
        final int channels = 1;

        AudioFormat format = new AudioFormat((float) sampleRate, bits, channels, signed, bigEndian);
        ByteArrayInputStream bais = new ByteArrayInputStream(byteBuffer);
        AudioInputStream audioInputStream = new AudioInputStream(bais, format, buffer.length);
        try {
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            audioInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void criar_au(String eArquivo) {


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

        au(eArquivo, byteBuffer);

    }

    public void au(String eArquivo, byte[] byteBuffer) {

        try {
            DataOutputStream outFile = new DataOutputStream(new FileOutputStream(eArquivo));

            outFile.write(byteBuffer);
            outFile.close();// 44 - the actual data itself - just a long string of numbers
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
package libs.Wav;

import libs.Arquivos.Audio.RefInt;
import libs.Arquivos.Binario.Arquivador;

import javax.sound.sampled.*;

public class AU {

    protected long au_total = 0;
    protected long au_lendo = 0;

    protected int mais;
    protected byte[] buffer;
    protected boolean terminou;

    protected RefInt ptr;
    protected AudioFormat wav;
    protected DataLine.Info info;
    protected SourceDataLine lineIn;

    protected byte[] au_buffer;
    protected byte[] originais;
    protected byte[] copiar;

    protected boolean continuar;

    public AU(String eArquivo) {

        au_total = 0;
        au_lendo = 0;

        mais = 0;
        buffer = new byte[256];
        terminou = false;

        wav = new AudioFormat(44100, 16, 2, true, false);
        info = new DataLine.Info(SourceDataLine.class, wav);


        if (!AudioSystem.isLineSupported(info)) {
            System.out.print("no support for " + wav.toString());
        }

        // AreaBinaria ab = new AreaBinaria();
        continuar = true;

        lineIn = null;
        try {
            lineIn = (SourceDataLine) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            lineIn.open(wav);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        lineIn.start();

        Arquivador arquivo_au = new Arquivador(eArquivo, "r");
        arquivo_au.setPonteiro(0);
        au_total = arquivo_au.getLength();

        au_buffer = new byte[(int) au_total];

        arquivo_au.readBufferBytes(au_buffer, (int) au_total);

        arquivo_au.encerrar();

        System.out.println("lido");

        buffer = new byte[256];
        originais = new byte[256];
        copiar = new byte[256];


        for (int v = 0; v < 256; v++) {
            buffer[v] = 0;
            originais[v] = 0;
            copiar[v] = 0;
        }

        ptr = new RefInt();

    }

    public boolean temMais() {
        return continuar && (au_lendo < au_total && !terminou);

    }

    public double getProgresso() {
        double p = (double) au_lendo / (double) au_total;
        p = p * (100.0);
        return p;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public boolean isPausado() {
        return !continuar;
    }

    public void pausar() {
        continuar = false;
    }

    public void reproduzir() {
        continuar = true;
    }
}

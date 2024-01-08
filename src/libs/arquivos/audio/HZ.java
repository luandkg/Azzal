package libs.arquivos.audio;

import libs.arquivos.binario.Arquivador;

import javax.sound.sampled.*;

public class HZ {

    protected long au_total = 0;
    protected long au_lendo = 0;

    protected int mais;
    protected byte[] buffer;
    protected boolean terminou;

    protected RefInt ptr;
    protected AudioFormat mAudio;
    protected DataLine.Info info;
    protected SourceDataLine mLinhaDeAudio;

    protected byte[] au_buffer;
    protected byte[] originais;
    protected byte[] copiar;

    protected boolean continuar;

    public HZ(String eArquivo) {

        au_total = 0;
        au_lendo = 0;

        mais = 0;
        buffer = new byte[256];
        terminou = false;

        mAudio = new AudioFormat(44100, 16, 2, true, false);
        info = new DataLine.Info(SourceDataLine.class, mAudio);


        if (!AudioSystem.isLineSupported(info)) {
            System.out.print("no support for " + mAudio.toString());
        }

        // AreaBinaria ab = new AreaBinaria();
        continuar = true;

        mLinhaDeAudio = null;
        try {
            mLinhaDeAudio = (SourceDataLine) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            mLinhaDeAudio.open(mAudio);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        mLinhaDeAudio.start();

        Arquivador arquivo_au = new Arquivador(eArquivo, "r");
        arquivo_au.setPonteiro(0);
        au_total = arquivo_au.getLength();

        au_buffer = new byte[(int) au_total];

        arquivo_au.get_u8_em_bloco(au_buffer, (int) au_total);

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

    public void onToque() {
        mLinhaDeAudio.write(buffer, 0, mais);
    }

    public void avancar(int eMais) {
        au_lendo += eMais;
    }


    public boolean temMais() {
        return continuar && (au_lendo < au_total && !terminou);

    }

    public long getLendo() {
        return au_lendo;
    }

    public int getMais() {
        return mais;
    }

    public byte[] getBuffer() {
        return buffer;
    }


    public double getProgresso() {
        double p = (double) au_lendo / (double) au_total;
        p = p * (100.0);
        return p;
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

    public void re_iniciar() {
        au_lendo = 0;
        ptr = new RefInt();
        continuar = true;
        terminou = false;

    }
}

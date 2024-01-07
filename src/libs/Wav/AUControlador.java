package libs.Wav;

import libs.arquivos.audio.AreaBinaria;
import libs.arquivos.audio.RefInt;
import libs.arquivos.audio.RepeticaoBinaria;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;

import javax.sound.sampled.*;
import java.util.ArrayList;

public class AUControlador {

    // IMPLEMENTACAO : 2022 01 30


    public int lerAmostragem(Arquivador arquivo_au, byte[] buffer, byte[] originais, byte[] copiar) {

        byte primeiro = arquivo_au.get();

        int numBytesRead = -1;

        if (primeiro == (byte) 0) {
            int pt = 0;
            for (int indice = 0; indice < (256 / 2); indice++) {
                buffer[pt] = arquivo_au.get();
                buffer[pt + 1] = arquivo_au.get();
                pt += 2;
            }
            numBytesRead = 256;
            //   System.out.println("BLOCO COMPLETO");
        } else {
            int repetidos = Inteiro.byteToInt(primeiro);
            int total_repetidos = 0;

            //  System.out.println("BLOCO COMPACTADO -->> " + repetidos);

            ArrayList<RepeticaoBinaria> repeticoes = new ArrayList<RepeticaoBinaria>();

            for (int r = 0; r < repetidos; r++) {

                byte b1 = arquivo_au.get();
                byte b2 = arquivo_au.get();

                int qt = Inteiro.byteToInt(arquivo_au.get());

                total_repetidos += qt;

                //   System.out.println("\t - Repetir :: " + Inteiro.byteToInt(b1) + " e " + Inteiro.byteToInt(b2) + " -->> " + qt);

                RepeticaoBinaria rb = new RepeticaoBinaria(b1, b2, "");
                repeticoes.add(rb);

                for (int q = 0; q < qt; q++) {

                    int pos = Inteiro.byteToInt(arquivo_au.get());
                    rb.guardar(pos);
                    //  System.out.println("\t\t :: " + pos);

                }

            }

            int qt_originais = 256 - (total_repetidos * 2);
            // System.out.println("\t - Originais : " + qt_originais);
            // System.out.println("\t - Repetidos : " + total_repetidos);

            int pt = 0;
            for (int indice = 0; indice < (qt_originais / 2); indice++) {
                originais[pt] = arquivo_au.get();
                originais[pt + 1] = arquivo_au.get();
                pt += 2;
            }

            // ab.mostrarBuffer(originais);

            rearranjar(buffer, originais, qt_originais, repeticoes, copiar);


            numBytesRead = 256;

        }

        return numBytesRead;
    }

    public static int lerAmostragemBuffer(RefInt ptr, byte[] arquivo_au, byte[] buffer, byte[] originais, byte[] copiar) {

        if (ptr.get() >= arquivo_au.length) {
            return -1;
        }

        byte primeiro = arquivo_au[ptr.get()];
        ptr.mais(1);

        int numBytesRead = -1;

        if (primeiro == (byte) 0) {
            int pt = 0;
            for (int indice = 0; indice < (256 / 2); indice++) {
                buffer[pt] = arquivo_au[ptr.get()];
                ptr.mais(1);
                buffer[pt + 1] = arquivo_au[ptr.get()];
                ptr.mais(1);

                pt += 2;
            }
            numBytesRead = 256;
            //   System.out.println("BLOCO COMPLETO");
        } else {
            int repetidos = Inteiro.byteToInt(primeiro);
            int total_repetidos = 0;

            //  System.out.println("BLOCO COMPACTADO -->> " + repetidos);

            ArrayList<RepeticaoBinaria> repeticoes = new ArrayList<RepeticaoBinaria>();

            for (int r = 0; r < repetidos; r++) {

                byte b1 = arquivo_au[ptr.get()];
                ptr.mais(1);

                byte b2 = arquivo_au[ptr.get()];
                ptr.mais(1);

                int qt = Inteiro.byteToInt(arquivo_au[ptr.get()]);
                ptr.mais(1);

                total_repetidos += qt;

                //   System.out.println("\t - Repetir :: " + Inteiro.byteToInt(b1) + " e " + Inteiro.byteToInt(b2) + " -->> " + qt);

                RepeticaoBinaria rb = new RepeticaoBinaria(b1, b2, "");
                repeticoes.add(rb);

                for (int q = 0; q < qt; q++) {

                    int pos = Inteiro.byteToInt(arquivo_au[ptr.get()]);
                    ptr.mais(1);

                    rb.guardar(pos);
                    //  System.out.println("\t\t :: " + pos);

                }

            }

            int qt_originais = 256 - (total_repetidos * 2);
            // System.out.println("\t - Originais : " + qt_originais);
            // System.out.println("\t - Repetidos : " + total_repetidos);

            int pt = 0;
            for (int indice = 0; indice < (qt_originais / 2); indice++) {
                originais[pt] = arquivo_au[ptr.get()];
                ptr.mais(1);
                originais[pt + 1] = arquivo_au[ptr.get()];
                ptr.mais(1);
                pt += 2;
            }

            // ab.mostrarBuffer(originais);

            rearranjar(buffer, originais, qt_originais, repeticoes, copiar);


            numBytesRead = 256;

        }

        return numBytesRead;
    }

    public static void rearranjar(byte[] buffer, byte[] originais, int qt_originais, ArrayList<RepeticaoBinaria> repeticoes, byte[] copiar) {

        // AreaBinaria ab = new AreaBinaria();
        //ab.mostrarBuffer(buffer);

        for (int o = 0; o < qt_originais; o++) {
            buffer[o] = originais[o];
        }

        for (RepeticaoBinaria rep : repeticoes) {

            //  System.out.println("Aplicar :: " + Inteiro.byteToInt(rep.getB1()) + "::" + Inteiro.byteToInt(rep.getB2()));

            int mais = 0;

            for (int pos : rep.getPosicoes()) {
                //System.out.println("\n\t - Pos :: " + pos);

                int deslocar = qt_originais - pos + mais;
                mais += 2;

                //System.out.println("\t - Deslocar :: " + deslocar);


                int c = 0;
                for (int des = pos; des < (pos + deslocar); des++) {
                    copiar[c] = buffer[des];
                    c += 1;
                }
                c = 0;
                for (int des = pos + 2; des < (pos + deslocar) + 4; des++) {
                    if (des < 256) {
                        buffer[des] = copiar[c];
                        c += 1;
                    }
                }


                buffer[pos] = rep.getB1();
                buffer[pos + 1] = rep.getB2();

                // System.out.println("\nMATRIZ BUFFER");
                //  ab.mostrarBuffer(buffer);

            }


        }


    }


    public void playA4(String filename) {

        long au_total = 0;
        long au_lendo = 0;

        int mais;
        byte[] buffer;
        boolean terminou;

        AudioFormat wav = new AudioFormat(44100, 16, 2, true, false);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, wav);

        terminou = false;

        if (!AudioSystem.isLineSupported(info)) {
            System.out.print("no support for " + wav.toString());
        }

        AreaBinaria ab = new AreaBinaria();

        try {

            SourceDataLine lineIn = (SourceDataLine) AudioSystem.getLine(info);
            lineIn.open(wav);
            lineIn.start();

            Arquivador arquivo_au = new Arquivador(filename, "r");
            arquivo_au.setPonteiro(0);
            au_total = arquivo_au.getLength();

            byte[] au_buffer = new byte[(int) au_total];

            arquivo_au.get_u8_em_bloco(au_buffer, (int) au_total);

            arquivo_au.encerrar();

            System.out.println("lido");

            buffer = new byte[256];
            byte[] originais = new byte[256];
            byte[] copiar = new byte[256];


            for (int v = 0; v < 256; v++) {
                buffer[v] = 0;
                originais[v] = 0;
                copiar[v] = 0;
            }

            RefInt ptr = new RefInt();

            while (au_lendo < au_total && !terminou) {

                mais = lerAmostragemBuffer(ptr, au_buffer, buffer, originais, copiar);

                if (mais == -1) break;
                au_lendo += mais;

                System.out.println("Amostra -->> " + mais + " :: " + au_lendo);

                lineIn.write(buffer, 0, mais);

            }

            // arquivo_au.encerrar();


        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public static AU init(String eArquivo) {

        AU eAU = new AU(eArquivo);

        return eAU;
    }

    public static boolean continuar_slice(AU eAU) {
        return eAU.continuar && (eAU.au_lendo < eAU.au_total && !eAU.terminou);
    }

    public static void toque(AU eAU) {


        eAU.mais = lerAmostragemBuffer(eAU.ptr, eAU.au_buffer, eAU.buffer, eAU.originais, eAU.copiar);

        if (eAU.mais > 0) {

            eAU.au_lendo += eAU.mais;

            // System.out.println("Amostra -->> " + eAU.mais + " :: " + eAU.au_lendo);

            eAU.lineIn.write(eAU.buffer, 0, eAU.mais);

        } else {
            eAU.continuar = false;
        }


    }


}

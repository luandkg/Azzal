package libs.arquivos.audio;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

public class HZControlador {

    public static class Transferencia {

        public byte[] wavDados;
        public long tamanho;

        public Transferencia(byte[] dados, long tam) {
            wavDados = dados;
            tamanho = tam;
        }

    }

    public static void converterToHZ(String eArquivoWAV, String eArquivoHZ) {
        Transferencia eTransferencia = obterDadosDoWAV(eArquivoWAV);

        Arquivador.remover(eArquivoHZ);

        toHZ(eTransferencia, eArquivoHZ);
    }

    public static void converterToHZ(String eArquivoWAV) {
        Transferencia eTransferencia = obterDadosDoWAV(eArquivoWAV);

        Arquivador.remover(eArquivoWAV.replace(".wav", ".hz"));

        toHZ(eTransferencia, eArquivoWAV.replace(".wav", ".hz"));
    }

    public static Transferencia obterDadosDoWAV(String eArquivoWAV) {


        ByteArrayOutputStream byteArrayOutputStream;
        AudioFormat audioFormat;
        TargetDataLine targetDataLine;
        AudioInputStream audioInputStream;
        SourceDataLine sourceDataLine;
        float frequency = 8000.0F;  //8000,11025,16000,22050,44100
        int samplesize = 16;
        String myPath;
        long myChunkSize;
        long mySubChunk1Size;
        int myFormat;
        long myChannels;
        long mySampleRate;
        long myByteRate;
        int myBlockAlign;
        int myBitsPerSample;
        long myDataSize;
        // I made this public so that you can toss whatever you want in here
        // maybe a recorded buffer, maybe just whatever you want
        byte[] myData;

        DataInputStream inFile = null;
        myData = null;
        byte[] tmpLong = new byte[4];
        byte[] tmpInt = new byte[2];

        try {
            inFile = new DataInputStream(new FileInputStream(eArquivoWAV));

            //System.out.println("Reading wav file...\n"); // for debugging only

            String chunkID = "" + (char) inFile.readByte() + (char) inFile.readByte() + (char) inFile.readByte() + (char) inFile.readByte();

            myChunkSize = inFile.readLong(); // read the ChunkSize

            String format = "" + (char) inFile.readByte() + (char) inFile.readByte() + (char) inFile.readByte() + (char) inFile.readByte();

            // print what we've read so far
            //System.out.println("chunkID:" + chunkID + " chunk1Size:" + myChunkSize + " format:" + format); // for debugging only


            String subChunk1ID = "" + (char) inFile.readByte() + (char) inFile.readByte() + (char) inFile.readByte() + (char) inFile.readByte();

            mySubChunk1Size = inFile.readLong(); // read the SubChunk1Size

            myFormat = inFile.readInt(); // read the audio format.  This should be 1 for PCM

            myChannels = inFile.readInt(); // read the # of channels (1 or 2)

            mySampleRate = inFile.readLong(); // read the samplerate

            myByteRate = inFile.readLong(); // read the byterate

            myBlockAlign = inFile.readInt(); // read the blockalign

            myBitsPerSample = inFile.readInt(); // read the bitspersample

            // print what we've read so far
            //System.out.println("SubChunk1ID:" + subChunk1ID + " SubChunk1Size:" + mySubChunk1Size + " AudioFormat:" + myFormat + " Channels:" + myChannels + " SampleRate:" + mySampleRate);


            // read the data chunk header - reading this IS necessary, because not all wav files will have the data chunk here - for now, we're just assuming that the data chunk is here
            String dataChunkID = "" + (char) inFile.readByte() + (char) inFile.readByte() + (char) inFile.readByte() + (char) inFile.readByte();

            myDataSize = inFile.readInt(); // read the size of the data


            // read the data chunk
            myData = new byte[(int) myDataSize];
            int leu = inFile.read(myData);

            System.out.println("TODO :: " + myData.length);
            System.out.println("LEU  :: " + leu);


            inFile.close();

            return new Transferencia(myData, leu);


        } catch (Exception e) {
            return new Transferencia(new byte[10], 9);
        }

    }

    private static void toHZ(Transferencia eTransferencia, String eArquivoAU) {

        AreaBinaria ab = new AreaBinaria();


        RepetidorBinario rb = new RepetidorBinario();

        // Arquivador arquivador = new Arquivador(eArquivoData, "r");
        // long eTamanho = arquivador.getLength();
        //arquivador.encerrar();


        System.out.println("WAV - Tamanho  :: " + eTransferencia.tamanho);

        long comecar = 0;
        long terminar = eTransferencia.tamanho;
        int i = 0;

        long s_total = 0;
        long s_repeticoes = 0;
        int rep_contagem = 0;

        int mostrar = 0;

        Arquivador au_arquivador = new Arquivador(eArquivoAU, "rw");

        int TAMANHO_BUFFER = 256;

        int adequados = 0;

        while (comecar < terminar) {

            ArrayList<Integer> z_Bloco = ab.getAreaGrandeBuffer(eTransferencia.wavDados, comecar, TAMANHO_BUFFER);

            ArrayList<RepeticaoBinaria> z_repeticoes = rb.getRepeticoes(z_Bloco);


            rep_contagem = 0;
            int chaves = 0;
            int bytes = 0;
            boolean adequado = true;

            for (RepeticaoBinaria rep : z_repeticoes) {
                if (rep.getQuantidade() > 1) {
                    // System.out.println("REP :: " + rep.getValor() + " -->> " + rep.getQuantidade());
                    rep_contagem += rep.getQuantidade();
                    chaves += 1;
                }
            }

            if (rep_contagem < 30) {
                // rep_contagem = 0;
                adequado = false;
            } else {
                adequados += 1;
            }

            if (rep_contagem == 0) {

                au_arquivador.writeByte((byte) 0);

                for (RepeticaoBinaria rep : z_repeticoes) {
                    au_arquivador.writeByte((byte) rep.getB1());
                    au_arquivador.writeByte((byte) rep.getB2());
                }

                bytes = 256 + 1;

            } else {


                au_arquivador.writeByte((byte) chaves);

                bytes = 1;

                for (RepeticaoBinaria rep : z_repeticoes) {
                    if (rep.getQuantidade() > 1) {
                        au_arquivador.writeByte((byte) rep.getB1());
                        au_arquivador.writeByte((byte) rep.getB2());

                        au_arquivador.writeByte((byte) rep.getPosicoes().size());

                        bytes += 3;

                        for (Integer ePosicao : rep.getPosicoes()) {
                            au_arquivador.writeByte((byte) ((int) ePosicao));
                            bytes += 1;
                        }

                    }
                }

                int originais = 0;

                for (RepeticaoBinaria rep : z_repeticoes) {
                    if (rep.getQuantidade() == 1) {
                        au_arquivador.writeByte((byte) rep.getB1());
                        au_arquivador.writeByte((byte) rep.getB2());
                        originais += 1;

                        bytes += 2;

                    }
                }


                // debug(z_repeticoes, rep_contagem, originais);
                //ab.mostrar(z_Bloco);

                // System.out.println("-------");
                //break;
            }


            long eFalta = eTransferencia.tamanho - ((comecar + z_Bloco.size()));

            if (mostrar >= 100 || ((comecar + (TAMANHO_BUFFER)) >= terminar)) {

                int dobro = rep_contagem + rep_contagem;

                System.out.println("Bloco de Audio - HZ : " + i);
                System.out.println("\t- Tamanho    :: " + z_Bloco.size() + " -->> ( " + comecar + " ate " + (comecar + z_Bloco.size()) + " ) :: " + eFalta);
                System.out.println("\t- Repeticoes :: " + dobro);
                // System.out.println("\t- Final      :: " + (z_Bloco.size() - dobro));
                String s_adq = adequado == true ? "Sim" : "Nao";

                System.out.println("\t- Adequado   :: " + s_adq);
                System.out.println("\t- Final      :: " + bytes);

                mostrar = 0;
            }
            mostrar += 1;


            s_total += z_Bloco.size();
            s_repeticoes += rep_contagem;

            comecar += (TAMANHO_BUFFER);
            i += 1;
        }

        au_arquivador.encerrar();


        System.out.println("");
        System.out.println("FINALMENTE");
        System.out.println("");
        System.out.println("\t- Total      :: " + s_total);
        System.out.println("\t- Repeticoes :: " + s_repeticoes);
        System.out.println("\t- Adequados  :: " + adequados);
        System.out.println("\t- Final      :: " + (s_total - s_repeticoes));

    }


    public static HZ init(String eArquivo) {
        HZ eHZ = new HZ(eArquivo);
        return eHZ;
    }


    public static void proxima(HZ eHZ) {

        eHZ.mais = lerAmostragemBuffer(eHZ.ptr, eHZ.au_buffer, eHZ.buffer, eHZ.originais, eHZ.copiar);
        if (eHZ.mais > 0) {
            eHZ.au_lendo += eHZ.mais;
        } else {
            eHZ.continuar = false;
            eHZ.terminou = true;
        }

    }

    public static void toque_direto(HZ eHZ, byte[] buff, int etam) {
        eHZ.mLinhaDeAudio.write(buff, 0, etam);
    }


    public static void toque(HZ eHZ) {


        eHZ.mais = lerAmostragemBuffer(eHZ.ptr, eHZ.au_buffer, eHZ.buffer, eHZ.originais, eHZ.copiar);


        if (eHZ.getMais() > 0) {

            eHZ.avancar(eHZ.getMais());

            // System.out.println("Amostra -->> " + eHZ.mais + " :: " + eHZ.au_lendo);

            eHZ.onToque();

        } else {
            eHZ.continuar = false;
            eHZ.terminou = true;
        }


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


}

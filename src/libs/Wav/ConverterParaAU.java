package libs.Wav;

import libs.arquivos.audio.AreaBinaria;
import libs.arquivos.audio.RepeticaoBinaria;
import libs.arquivos.audio.RepetidorBinario;
import libs.arquivos.binario.Arquivador;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

public class ConverterParaAU {

    public static boolean converter(String eArquivoWAV, String eArquivoAU) {


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

            myDataSize = inFile.readLong(); // read the size of the data


            // read the data chunk
            myData = new byte[(int) myDataSize];
            int leu = inFile.read(myData);

            System.out.println("TODO :: " + myData.length);
            System.out.println("LEU  :: " + leu);

            //AUControlador eAUControlador = new AUControlador();
            // eAUControlador.salvar_arquivo_ate(eArquivoAU,myData,leu);

            toAU(myData, leu, eArquivoAU);

            inFile.close();
        } catch (Exception e) {
            return false;
        }

        return true; // this should probably be something more descriptive
    }

    public static void toAU(byte[] eDados, long eTamanho, String eArquivoAU) {

        AreaBinaria ab = new AreaBinaria();


        RepetidorBinario rb = new RepetidorBinario();

        // Arquivador arquivador = new Arquivador(eArquivoData, "r");
        // long eTamanho = arquivador.getLength();
        //arquivador.encerrar();


        System.out.println("Tamanho Total      :: " + eTamanho);

        long comecar = 0;
        long terminar = eTamanho;
        int i = 0;

        long s_total = 0;
        long s_repeticoes = 0;
        int rep_contagem = 0;

        int mostrar = 0;

        Arquivador au_arquivador = new Arquivador(eArquivoAU, "rw");

        int TAMANHO_BUFFER = 256;

        while (comecar < terminar) {

            ArrayList<Integer> z_Bloco = ab.getAreaGrandeBuffer(eDados, comecar, TAMANHO_BUFFER);

            ArrayList<RepeticaoBinaria> z_repeticoes = rb.getRepeticoes(z_Bloco);


            rep_contagem = 0;
            int chaves = 0;

            for (RepeticaoBinaria rep : z_repeticoes) {
                if (rep.getQuantidade() > 1) {
                    // System.out.println("REP :: " + rep.getValor() + " -->> " + rep.getQuantidade());
                    rep_contagem += rep.getQuantidade();
                    chaves += 1;
                }
            }

            if (rep_contagem == 0) {

                // au_arquivador.writeByte((byte) 100);
                au_arquivador.set_u8((byte) 0);

                for (RepeticaoBinaria rep : z_repeticoes) {
                    au_arquivador.set_u8((byte) rep.getB1());
                    au_arquivador.set_u8((byte) rep.getB2());
                }

            } else {

                // au_arquivador.writeByte((byte) 200);

                au_arquivador.set_u8((byte) chaves);

                for (RepeticaoBinaria rep : z_repeticoes) {
                    if (rep.getQuantidade() > 1) {
                        au_arquivador.set_u8((byte) rep.getB1());
                        au_arquivador.set_u8((byte) rep.getB2());

                        au_arquivador.set_u8((byte) rep.getPosicoes().size());

                        for (Integer ePosicao : rep.getPosicoes()) {
                            au_arquivador.set_u8((byte) ((int) ePosicao));
                        }

                    }
                }

                int originais = 0;

                for (RepeticaoBinaria rep : z_repeticoes) {
                    if (rep.getQuantidade() == 1) {
                        au_arquivador.set_u8((byte) rep.getB1());
                        au_arquivador.set_u8((byte) rep.getB2());
                        originais += 1;
                    }
                }


                // debug(z_repeticoes, rep_contagem, originais);
                //ab.mostrar(z_Bloco);

                // System.out.println("-------");
                //break;
            }


            long eFalta = eTamanho - ((comecar + z_Bloco.size()));

            if (mostrar >= 100 || ((comecar + (TAMANHO_BUFFER)) >= terminar)) {

                int dobro = rep_contagem + rep_contagem;

                System.out.println("Bloco  " + i);
                System.out.println("\t- Tamanho    :: " + z_Bloco.size() + " -->> " + comecar + " ate " + (comecar + z_Bloco.size()) + " :: " + eFalta);
                System.out.println("\t- Repeticoes :: " + dobro);
                System.out.println("\t- Final      :: " + (z_Bloco.size() - dobro));

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
        System.out.println("\t- Final      :: " + (s_total - s_repeticoes));

    }


}

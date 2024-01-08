package libs.Wav;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ArquivoWav {
    //wave file constants
    //"RIFF" in ascii
    private static final int RIFF_CHUNK_ID = 0x52494646;
    //"WAVE" in ascii
    private static final int WAVE_FORMAT = 0x57415645;
    //"fmt" in ascii
    private static final int SUBCHUNK1ID = 0x666d7420;
    //PCM uses 16. That's the only wave we will support. Read as little endian
    private static final int SUBCHUNK1SIZE = 16;
    //Format = 1 for pcm. Others mean compression
    private static final int AUDIO_FORMAT = 1;
    //"data" in ascii
    private static final int SUBCHUNK2ID = 0x64617461;
    //Data should start at byte 44
    private static final int DATA_START_OFFSET = 44;

    //Wave file header
    //RIFF section
    private int chunkID;
    private LittleEndianInt chunkSize;
    private int format;
    //fmt section
    private int subchunk1ID;
    private LittleEndianInt subchunk1Size;
    private LittleEndianShort audioFormat;
    private LittleEndianShort numChannels;
    private LittleEndianInt samplerate;
    private LittleEndianInt byteRate;
    private LittleEndianShort blockAlign;
    private LittleEndianShort bitsPerSample;
    //Data header
    private int subchunk2ID;
    private LittleEndianInt subchunk2Size;
    //Input stream
    private InputStream inputStream;
    //Sample position in bytes
    private long dataOffset = 0;
    //ByteBuffer for conversion from byte[] to integers
    private ByteBuffer bb;

    public ArquivoWav(File inputFile) {
        try {
            inputStream = new FileInputStream(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Failed to get input stream for file");
        }
    }

    public ArquivoWav(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Opens wav file stream.
     * Must require RIFF PCM format. Others not supported yet
     *
     * @return Returns false if failed read or incorrect wave data
     * @throws Exception
     */
    public boolean open() {
        try {
            return readHeader();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean readHeader() throws Exception {
        //Declare buffers for use in storing bytes read in form inputStream
        byte intByteBuffer[] = new byte[4];
        byte shortByteBuffer[] = new byte[2];
        //ChunkID
        inputStream.read(intByteBuffer);
        chunkID = bytesToInt(intByteBuffer);
        //Check to see if valid RIFF file
        if (chunkID != RIFF_CHUNK_ID)
            throw new Exception("NOT A VALID RIFF FILE: chunkid [" + chunkID + "]");

        //ChunkSize
        inputStream.read(intByteBuffer);
        chunkSize = new LittleEndianInt(bytesToInt(intByteBuffer));

        //Format
        inputStream.read(intByteBuffer);
        format = bytesToInt(intByteBuffer);
        if (format != WAVE_FORMAT)
            throw new Exception("INVALID WAV FORMAT");


        //Subchunk1ID
        inputStream.read(intByteBuffer);
        subchunk1ID = bytesToInt(intByteBuffer);
        if (subchunk1ID != SUBCHUNK1ID)
            throw new Exception("INVALID SUBCHUNK 1 ID");


        //SubChunk1Size
        inputStream.read(intByteBuffer);
        subchunk1Size = new LittleEndianInt(bytesToInt(intByteBuffer));
        if (subchunk1Size.convert() != SUBCHUNK1SIZE)
            throw new Exception("NON PCM FILES ARE NOT SUPPORTED: chunk size[" + subchunk1Size.convert() + "]");


        //Audio Format
        inputStream.read(shortByteBuffer);
        audioFormat = new LittleEndianShort(bytesToShort(shortByteBuffer));
        if (audioFormat.convert() != AUDIO_FORMAT)
            throw new Exception("COMPRESSED WAVE FILE NOT SUPPORTED: format[" + audioFormat.convert() + "]");


        //NumChannels
        inputStream.read(shortByteBuffer);
        numChannels = new LittleEndianShort(bytesToShort(shortByteBuffer));
        if (numChannels.convert() > 2 || numChannels.convert() < 0)
            throw new Exception("INVALID NUMBER OF CHANNELS: numChannels[" + numChannels.convert() + "]");

        //SampleRate
        inputStream.read(intByteBuffer);
        samplerate = new LittleEndianInt(bytesToInt(intByteBuffer));

        //ByteRate
        inputStream.read(intByteBuffer);
        byteRate = new LittleEndianInt(bytesToInt(intByteBuffer));

        //BlockAlign
        inputStream.read(shortByteBuffer);
        blockAlign = new LittleEndianShort(bytesToShort(shortByteBuffer));

        //BitsPerSample
        //We only support 16
        inputStream.read(shortByteBuffer);
        bitsPerSample = new LittleEndianShort(bytesToShort(shortByteBuffer));

        //SubChunk2ID
        inputStream.read(intByteBuffer);
        subchunk2ID = bytesToInt(intByteBuffer);
        if (subchunk2ID != SUBCHUNK2ID)
            //  throw new Exception("INVALID DATA HEADER");

            //Subchunk2Size
            inputStream.read(shortByteBuffer);
        subchunk2Size = new LittleEndianInt(bytesToShort(shortByteBuffer));

        //Everything loaded fine
        return true;
    }

    /**
     * Returns a lone integer sample
     *
     * @return Sample in long
     */
    private long readSample(long offset) throws IOException {
        long sample = 0;
        byte buffer[] = new byte[bitsPerSample.convert() / 8];
        inputStream.skip(offset);
        int delta = inputStream.read(buffer);
        if (delta != -1) {
            dataOffset += delta;
        }

        switch (bitsPerSample.convert()) {
            case 16: {
                sample = bytesToShort(buffer);
                break;
            }
            default:
                break;
        }

        return sample;
    }

    public boolean isStereo() {
        return (numChannels.convert() == 2);
    }

    //META DATA
    public int getNumChannels() {
        return numChannels.convert();
    }

    public int getSampleRate() {
        return samplerate.convert();
    }

    public int getBitRate() {
        return bitsPerSample.convert();
    }

    public int getFileSize() {
        return subchunk1Size.convert() + 8;
    }

    public int getNumFrames() {
        return (chunkSize.convert() / blockAlign.convert());
    }

    //FRAME GRABBING - double
    public int readFrames(double[] frameBuffer) throws IOException {
        return readFrames(frameBuffer, 0);
    }

    public int readFrames(double[] frameBuffer, int offset) throws IOException {
        for (int f = 0; f < frameBuffer.length; f++) {
            frameBuffer[f] = (double) readSample(0) / (double) (Long.MAX_VALUE >> (64 - bitsPerSample.convert()));
        }
        return frameBuffer.length;
    }

    //UTIL
    public long bytesToLong(byte bytes[]) {
        bb = ByteBuffer.wrap(bytes);
        bb.order(ByteOrder.BIG_ENDIAN);
        return bb.getLong();
    }

    public int bytesToInt(byte bytes[]) {
        bb = ByteBuffer.wrap(bytes);
        bb.order(ByteOrder.BIG_ENDIAN);
        return bb.getInt();
    }

    public short bytesToShort(byte bytes[]) {
        bb = ByteBuffer.wrap(bytes);
        bb.order(ByteOrder.BIG_ENDIAN);
        return bb.getShort();
    }
}

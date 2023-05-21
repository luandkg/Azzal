package libs.xlsx;

import java.util.ArrayList;

public class ArenaChunk {
    private ArrayList<ByteChunk> mChunks;

    public ArenaChunk() {
        mChunks = new ArrayList<ByteChunk>();
    }

    public void adicionar(byte[] bytes, int tam) {

        ByteChunk copia = new ByteChunk(tam);
        for (int v = 0; v < tam; v++) {
            copia.get()[v] = bytes[v];
        }
        copia.setLength(tam);

        mChunks.add(copia);
    }

    public  int getLength(){
        int tamanho = 0;
        for (ByteChunk chunk : mChunks) {
            tamanho += chunk.getLength();
        }
        return tamanho;
    }

    public  ByteChunk unir(){

        int tamanho = getLength();


        byte[] bytes = new byte[tamanho];

        int escrevendo = 0;

        for (ByteChunk chunk_corrente : mChunks) {
            int i = 0;

            while (i < chunk_corrente.getLength()) {
                bytes[escrevendo + i] = chunk_corrente.get()[i];
                i += 1;
            }
            escrevendo += chunk_corrente.getLength();

        }

        return new ByteChunk(bytes,tamanho);
    }


    public ArrayList<ByteChunk> getChunks() {
        return mChunks;
    }

    public ByteChunk toChunk() {
        ByteChunk bytes = unir();
        return bytes;
    }

    public byte[] toBytes() {
        ByteChunk bytes = unir();
        return bytes.get();
    }
}

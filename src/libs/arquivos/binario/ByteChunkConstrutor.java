package libs.arquivos.binario;

import libs.luan.Lista;

public class ByteChunkConstrutor {

    private Lista<Byte> mBytes;

    public ByteChunkConstrutor(){
        mBytes=new Lista<Byte>();
    }


    public void set_u8(byte b){
        mBytes.adicionar(b);
    }

    public void set_u32(int valor){

        set_u8((byte) (valor >>> 24));
        set_u8((byte) (valor >>> 16));
        set_u8((byte) (valor >>> 8));
        set_u8((byte) (valor ));


    }

    public Lista<Byte> getBytes(){return mBytes;}
}

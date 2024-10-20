package libs.arquivos.indexados;

import libs.arquivos.binario.Arquivador;
import libs.luan.FS;
import libs.luan.fmt;

import java.nio.charset.StandardCharsets;


public class CacheIndexado {

    public static final int DECIMUM = 10;
    public static final int CENTUM = 100;
    public static final int DUPLUM = 200;
    public static final int TRIPLUM = 300;
    public static final int QUADRUM = 400;
    public static final int QUINTUM = 500;
    public static final int MEGA = 1024;

    private String mArquivo;
    private long mTamanhoSlot ;

    private Arquivador mArquivador;

    public CacheIndexado(int eTamanhoSlot,String eArquivo){
        mArquivo=eArquivo;
        mTamanhoSlot=eTamanhoSlot*1024L;
    }



    public void abrir(){
        mArquivador = new Arquivador(mArquivo);

        long t = mArquivador.getLength();

        if (t == 0) {
            mArquivador.setPonteiro(0);
            mArquivador.set_u8((byte) 100);
            mArquivador.set_u8((byte) 150);
        }

    }

    public void fechar(){
        mArquivador.encerrar();
    }

    public  void limpar( ) {
        Arquivador.remover(mArquivo);
    }


    public  boolean existe( ) {
        return FS.arquivo_existe(mArquivo);
    }

    public  int getQuantidade( ) {

        int v = 0;

        long t = mArquivador.getLength();

        if (t > 2) {
            t = t - 2;
            v = (int) (t / mTamanhoSlot);
        }

        return v;

    }


    public  void criar_slot( ) {

        mArquivador.setPonteiro(mArquivador.getLength());

        int v = 0;
        while (v < mTamanhoSlot) {
            mArquivador.set_u8((byte) 0);
            v += 1;
        }


    }


    public  int getTamanho(  int indice) {

        mArquivador.setPonteiro(2L + ((long) indice * (long) mTamanhoSlot));

        int tamanho = mArquivador.get_u32();
        return tamanho;
    }

    public  void setTamanho(  int indice, int tamanho) {


        mArquivador.setPonteiro(2L + ((long) indice * (long) mTamanhoSlot));
        mArquivador.set_u32(tamanho);

    }

    public  void set(  int indice, String dados) {


        mArquivador.setPonteiro(2L + ((long) indice * (long) mTamanhoSlot));

        byte[] bytes = dados.getBytes(StandardCharsets.UTF_8);

        mArquivador.set_u32(bytes.length);

        int i = 0;
        int o = bytes.length;

        while (i < o) {
            mArquivador.set_u8(bytes[i]);
            i += 1;
        }


    }

    public  long getPosicao(  int indice) {
        return 2L + ((long) indice * (long) mTamanhoSlot);
    }

        public  String get(  int indice) {


        mArquivador.setPonteiro(2L + ((long) indice * (long) mTamanhoSlot));


        int tamanho = mArquivador.get_u32();

        byte[] bytes = new byte[tamanho];
        int i = 0;

        while (i < tamanho) {
            bytes[i] = mArquivador.get();
            i += 1;
        }


        return new String(bytes, StandardCharsets.UTF_8);
    }


    public void limpar_slots(){

        int slot_id = 0;
        int quantidade = getQuantidade();

        while (slot_id < quantidade) {
            set( slot_id, "");
            slot_id += 1;
        }

    }



    public long getTamanhoMaiorBloco(){

        int quantidade = getQuantidade();

        long maior_tamanho = 0;

        for (int i = 0; i < quantidade; i++) {

            long tam = getTamanho(i);
            if (tam > maior_tamanho) {
                maior_tamanho = tam;
            }

        }

        return maior_tamanho;
    }

    public long getTamanhoMenorBloco(){

        int quantidade = getQuantidade();

        long menor_tamanho = 0;

        for (int i = 0; i < quantidade; i++) {

            long tam = getTamanho(i);

            if(i==0){
                menor_tamanho=tam;
            }

            if (tam < menor_tamanho) {
                menor_tamanho = tam;
            }

        }

        return menor_tamanho;
    }



}

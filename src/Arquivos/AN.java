package Arquivos;

import Arquivos.Binario.Arquivador;
import Arquivos.Binario.Inteiro;
import Azzal.Utils.Cor;
import Luan.Iterador;
import Luan.Lista;

import java.awt.image.BufferedImage;

public class AN {

    public static void criar(Lista<BufferedImage> eImagens, int eChrono, String eArquivo) {

        if (eImagens.getQuantidade() > 0) {
            Iterador<BufferedImage> mIterador = new Iterador<BufferedImage>(eImagens);

            int w = eImagens.getValor(0).getWidth();
            int h = eImagens.getValor(0).getHeight();


            for (mIterador.iniciar(); mIterador.continuar(); mIterador.proximo()) {

                BufferedImage mCorrente = mIterador.getValor();

                if (mCorrente.getWidth() != w) {
                    throw new IllegalArgumentException("Todos os quadros devem possuir a mesma largura !");
                }
                if (mCorrente.getHeight() != h) {
                    throw new IllegalArgumentException("Todos os quadros devem possuir a mesma altura !");
                }

            }

            Arquivador.remover(eArquivo);

            Arquivador arquivador = new Arquivador(eArquivo);

            System.out.println("Animacao AN - Cabecalho");

            arquivador.writeByte((byte) 65);
            arquivador.writeByte((byte) 78);

            arquivador.writeByte((byte) 1);

            arquivador.writeInt(eImagens.getQuantidade());

            arquivador.writeLong((long) w);
            arquivador.writeLong((long) h);

            arquivador.writeInt(eChrono);

            arquivador.writeByte((byte) 100);

            for (mIterador.iniciar(); mIterador.continuar(); mIterador.proximo()) {

                System.out.println("QUADRO : " + mIterador.getIndice());

                arquivador.writeByte((byte) 200);

                IM.salvar_bytes(mIterador.getValor(), arquivador);


                arquivador.writeByte((byte) 201);


            }


            arquivador.writeByte((byte) 255);


            arquivador.encerrar();

            System.out.println("Animacao IM - Terminada");


        }
    }

    public static AN abrirAnimacao(String eArquivo) {
        AN eAN = new AN();
        eAN.abrir(eArquivo);
        return eAN;
    }

    private int mImagemLargura;
    private int mImagemAltura;
    private int mChrono;
    private int mQuantidade;

    private Lista<BufferedImage> mImagens;

    public AN() {

        mImagens = new Lista<BufferedImage>();

        mImagemAltura = 0;
        mImagemLargura = 0;
        mChrono = 0;
        mQuantidade=0;

    }

    public void abrir(String eArquivo) {

        mImagens.limpar();
        mImagemLargura = 0;
        mImagemAltura = 0;
        mChrono = 0;


        Arquivador arquivador = new Arquivador(eArquivo);

        arquivador.setPonteiro(0);

        System.out.println("Imagem IM - ABRIR");

        byte b1 = arquivador.readByte();
        byte b2 = arquivador.readByte();
        byte b3 = arquivador.readByte();

        System.out.println("Cabecalho : " + b1 + "." + b2 + "." + b3);

        int mQuadros = arquivador.readInt();
        System.out.println("Quadros : " + mQuadros);

        long w = arquivador.readLong();
        long h = arquivador.readLong();

        System.out.println("Tamanho : " + w + " :: " + h);

        mChrono = arquivador.readInt();
        System.out.println("Chrono : " + mChrono);


        mImagemLargura = (int) w;
        mImagemAltura = (int) h;

        int fixo = Inteiro.byteToInt(arquivador.readByte());

        int mQuadroID = 0;

        byte bc = arquivador.readByte();
        while (bc != -1) {

            int mTipoBloco = Inteiro.byteToInt(bc);

            if (mTipoBloco == 200) {

                mImagens.adicionar(IM.lerDoFluxo(arquivador));

                int fixo2 = Inteiro.byteToInt(arquivador.readByte());

                mQuadroID += 1;
            }


            bc = arquivador.readByte();
        }

        System.out.println("Imagens  : " + mImagens.getQuantidade());

        System.out.println("Imagem IM - TERMINADO");

        arquivador.encerrar();

        mQuantidade=mImagens.getQuantidade();

    }

    public int getLargura() {
        return mImagemLargura;
    }

    public int getAltura() {
        return mImagemAltura;
    }

    public int getChrono() {
        return mChrono;
    }


    public Lista<BufferedImage> getImagens() {
        return mImagens;
    }

    public int getQuantidade() {
        return mQuantidade;
    }
}

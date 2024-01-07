package libs.arquivos;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;
import libs.luan.Iterador;
import libs.luan.Lista;

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

            arquivador.set_u8((byte) 65);
            arquivador.set_u8((byte) 78);

            arquivador.set_u8((byte) 1);

            arquivador.set_u32(eImagens.getQuantidade());

            arquivador.set_u64((long) w);
            arquivador.set_u64((long) h);

            arquivador.set_u32(eChrono);

            arquivador.set_u8((byte) 100);

            for (mIterador.iniciar(); mIterador.continuar(); mIterador.proximo()) {

                System.out.println("QUADRO : " + mIterador.getIndice());

                arquivador.set_u8((byte) 200);

                IM.salvar_bytes(mIterador.getValor(), arquivador);


                arquivador.set_u8((byte) 201);


            }


            arquivador.set_u8((byte) 255);


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
        mQuantidade = 0;

    }

    public void abrir(String eArquivo) {

        mImagens.limpar();
        mImagemLargura = 0;
        mImagemAltura = 0;
        mChrono = 0;


        Arquivador arquivador = new Arquivador(eArquivo);

        arquivador.setPonteiro(0);

        System.out.println("Imagem IM - ABRIR");

        byte b1 = arquivador.get();
        byte b2 = arquivador.get();
        byte b3 = arquivador.get();

        System.out.println("Cabecalho : " + b1 + "." + b2 + "." + b3);

        int mQuadros = arquivador.get_u32();
        System.out.println("Quadros : " + mQuadros);

        long w = arquivador.get_u64();
        long h = arquivador.get_u64();

        System.out.println("Tamanho : " + w + " :: " + h);

        mChrono = arquivador.get_u32();
        System.out.println("Chrono : " + mChrono);


        mImagemLargura = (int) w;
        mImagemAltura = (int) h;

        int fixo = Inteiro.byteToInt(arquivador.get());

        int mQuadroID = 0;

        byte bc = arquivador.get();
        while (bc != -1) {

            int mTipoBloco = Inteiro.byteToInt(bc);

            if (mTipoBloco == 200) {

                mImagens.adicionar(IM.lerDoFluxo(arquivador));

                int fixo2 = Inteiro.byteToInt(arquivador.get());

                mQuadroID += 1;
            }


            bc = arquivador.get();
        }

        System.out.println("Imagens  : " + mImagens.getQuantidade());

        System.out.println("Imagem IM - TERMINADO");

        arquivador.encerrar();

        mQuantidade = mImagens.getQuantidade();

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

package libs.arquivos.video;


import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;
import libs.arquivos.IM;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Video {

    // IMPLEMENTACAO : 2020 12 10 -->> MOVIE
    // IMPLEMENTACAO : 2020 12 12 -->> COMPRESSOR POR DIFERENCA ENTRE 2 QUADROS CONSECUTIVOS
    // IMPLEMENTACAO : 2022 01 28 -->> SIMPLIFICACAO DAS FUNCOES

    private final int FRAME_KEYFRAME = 50;
    private final int FRAME_REPETIR = 51;
    private final int FRAME_DIFERENCIAL = 52;


    private int mLargura;
    private int mAltura;
    private int mTaxa;
    private Arquivador mArquivo;

    private Arena mArenaInicial;
    private Arena mArenaCorrente;
    private BufferedImage mImagemCorrente;

    private int mArenaIndex;
    private int mFrameIndex;
    private boolean mAcabou;


    public Video() {
        mLargura = 0;
        mAltura = 0;
        mTaxa = 0;
        mArenaIndex = 0;
        mFrameIndex = 0;
        mAcabou = false;
    }

    public boolean getAcabou() {
        return mAcabou;
    }

    public void abrir(String eArquivo) {

        mArquivo = new Arquivador(eArquivo, "r");

        mArquivo.setPonteiro(0);


        byte b1 = mArquivo.get();
        byte b2 = mArquivo.get();
        byte b3 = mArquivo.get();

        System.out.println("Cabecalho : " + b1 + "." + b2 + "." + b3);

        byte p1 = mArquivo.get();

        long w = mArquivo.get_u64();
        long h = mArquivo.get_u64();

        byte p2 = mArquivo.get();

        long eTaxa = mArquivo.get_u64();

        byte p3 = mArquivo.get();


        mLargura = (int) w;
        mAltura = (int) h;
        mTaxa = (int) eTaxa;

        long ePosicao = mArquivo.getPonteiro();

        mArenaInicial = new Arena(mArquivo, ePosicao);
        mArenaCorrente = mArenaInicial;

        mImagemCorrente = new BufferedImage(mLargura, mAltura, BufferedImage.TYPE_INT_ARGB);

    }


    public Arena getQuadroCorrente() {
        return mArenaCorrente;
    }

    public BufferedImage getImagemCorrente() {
        return mImagemCorrente;
    }

    public void procurarQuadro() {
        for (Quadro eQuadro : mArenaCorrente.getQuadros()) {

            // System.out.println("Frame :: " + eFrame.getIndex());

            if (eQuadro.getIndex() == mFrameIndex) {
                if (eQuadro.getConteudo() == 0) {
                    mAcabou = true;
                } else {

                    mArquivo.setPonteiro(eQuadro.getConteudo());
                    lerQuadro();

                }
                break;
            }
        }

    }

    public void reIniciar() {

        mArenaIndex = 0;
        mFrameIndex = 0;
        mArenaCorrente = mArenaInicial;

        procurarQuadro();

    }

    public void proximo() {

        // System.out.println("Ler Quadro -- " + mQuadroIndex + "::" + mFrameIndex);

        procurarQuadro();

        avancar();

    }

    public void avancar() {

        mFrameIndex += 1;

        if (mFrameIndex >= 100) {
            mFrameIndex = 0;
            mArenaIndex += 1;
            if (mArenaCorrente.getProximo() > 0) {
                mArenaCorrente = new Arena(mArquivo, mArenaCorrente.getProximo());
            } else {
                mAcabou = true;
            }
        }
    }


    public int getFrameCorrente() {
        return (mArenaIndex * 100) + mFrameIndex;
    }


    public BufferedImage getFramePorIndex(int iFrame) {

        if (iFrame < 0) {
            return null;
        }


        Arena cArena = mArenaInicial;

        int pFrame = 0;
        int gFrame = 0;

        while (pFrame <= iFrame) {

            for (Quadro eQuadro : cArena.getQuadros()) {
                if (eQuadro.getIndex() == gFrame) {

                    if (eQuadro.getConteudo() == 0) {
                        break;
                    } else {

                        //System.out.println("Passando por frame - " + pFrame);

                        mArquivo.setPonteiro(eQuadro.getConteudo());
                        lerQuadro();

                    }

                }
            }

            pFrame += 1;
            gFrame += 1;

            if (gFrame >= 100) {
                gFrame = 0;
                if (cArena.getProximo() > 0) {
                    cArena = new Arena(mArquivo, cArena.getProximo());
                    // System.out.println("Trocar de Quadro");
                }
            }
        }


        return mImagemCorrente;

    }


    public void irParaPorcentagem(int mIrAte) {

        int mTotal = getQuadrosTotal();
        mArenaIndex = 0;
        mFrameIndex = 0;
        mAcabou = false;

        if (mIrAte >= 100) {
            mIrAte = 99;
        }

        if (mTotal > 0) {
            if (mIrAte > 0) {

                double mTaxinha = (double) mTotal / 100.0;
                double mPorcentagem = (double) getFrameCorrente() / mTaxinha;
                int mEstou = (int) mPorcentagem;

                while (mEstou < mIrAte) {
                    mPorcentagem = (double) getFrameCorrente() / mTaxinha;
                    mEstou = (int) mPorcentagem;
                    avancar();
                }


            }

            procurarQuadro();

        }

    }


    public void lerQuadro() {


        byte bc = mArquivo.get();

        int eModalidade = Inteiro.byteToInt(bc);

        // System.out.println("---------- LER QUADRO --------------");
        //System.out.println("Modalidade :: " + eModalidade);

        int mInicioX1 = 0;
        int mInicioY1 = 0;

        int mInicioX2 = mLargura;
        int mInicioY2 = mAltura;

        if (eModalidade == FRAME_DIFERENCIAL) {

            mInicioX1 = mArquivo.get_u32();
            mInicioY1 = mArquivo.get_u32();

            mInicioX2 = mArquivo.get_u32();
            mInicioY2 = mArquivo.get_u32();

        }

        BufferedImage quadro = null;


        if (eModalidade == FRAME_KEYFRAME || eModalidade == FRAME_DIFERENCIAL) {

            //  System.out.println("BC - " + mArquivo.organizarByteInt(bc));

            int eLargura = mInicioX2 - mInicioX1;
            int eAltura = mInicioY2 - mInicioY1;

            //  System.out.println("Achei Quadro em X1 = " + mInicioX1 + " Y1 = " + mInicioY1 + " X2 = " + mInicioX2 + " Y2 = " + mInicioY2);

            if (mInicioX1 <= 0 && mInicioX2 <= 0 && mInicioY1 <= 0 && mInicioY2 <= 0) {
                //      System.out.println("Repetir anterior...");
                return;
            } else {
                //   System.out.println("Acrescentar zona diferencial...");
            }

            BufferedImage mFrameCorrente = new BufferedImage(eLargura, eAltura, BufferedImage.TYPE_INT_ARGB);

            if (mInicioY2 != 0) {


                // System.out.println("BC - " + mArquivo.organizarByteInt(bc));
                boolean mAtualizarFrame = false;


                // System.out.println("Ler bloco = " + mTipoBloco);

                quadro = IM.lerDoFluxo(mArquivo);

                //  System.out.println("ql :: " + quadro.getWidth());
                //  System.out.println("qa :: " + quadro.getHeight());

                mAtualizarFrame = true;


                if (mAtualizarFrame) {


                    if (eLargura == mLargura && eAltura == mAltura) {
                        //     System.out.println("Quadro KeyFrame");
                    } else {
                        // System.out.println("Quadro Diferencial em X1 = " + mInicioX1 + " Y1 = " + mInicioY1 + " X2 = " + mInicioX2 + " Y2 = " + mInicioY2);
                    }

                    int ry = 0;

                    for (int aqy = mInicioY1; aqy < mInicioY2; aqy++) {
                        int rx = 0;

                        for (int aqx = mInicioX1; aqx < mInicioX2; aqx++) {

                            int pixel = quadro.getRGB(rx, ry);
                            // System.out.println(" colocar x = " + aqx + " y = " + mInicioY1);

                            mImagemCorrente.setRGB(aqx, aqy, pixel);
                            rx += 1;
                        }

                        //  mInicioY1 += 1;
                        ry += 1;
                    }


                } else {
                    //   System.out.println("Quadro Manutencao");
                }

            } else {

                if (mFrameIndex == 0) {
                    mImagemCorrente = mFrameCorrente;
                    //   System.out.println("Quadro Inicial !!! ");
                } else {
                    //    System.out.println("Quadro Repeticao !!! ");
                }

            }


        } else if (eModalidade == FRAME_KEYFRAME) {

            // System.out.println("Repitir Quadro !!! ");

        }


    }


    public int getLargura() {
        return mLargura;
    }

    public int getAltura() {
        return mAltura;
    }

    public int getTaxa() {
        return mTaxa;
    }


    public int getArenasContagem() {
        int eContando = 1;

        Arena eQuadroPercursor = mArenaInicial;


        while (eQuadroPercursor.getProximo() != 0) {
            eContando += 1;
            eQuadroPercursor = new Arena(mArquivo, eQuadroPercursor.getProximo());
        }

        return eContando;
    }


    public ArrayList<Arena> getArenas() {

        ArrayList<Arena> mArenas = new ArrayList<Arena>();

        mArenas.add(mArenaInicial);

        Arena eQuadroPercursor = mArenaInicial;


        while (eQuadroPercursor.getProximo() != 0) {
            eQuadroPercursor = new Arena(mArquivo, eQuadroPercursor.getProximo());
            mArenas.add(eQuadroPercursor);
        }

        return mArenas;

    }

    public int getQuadrosTotal() {

        int eContagem = 0;


        Arena eQuadroPercursor = mArenaInicial;

        eContagem += eQuadroPercursor.getFramesUsadosContagem();

        while (eQuadroPercursor.getProximo() != 0) {
            eQuadroPercursor = new Arena(mArquivo, eQuadroPercursor.getProximo());
            eContagem += eQuadroPercursor.getFramesUsadosContagem();
        }

        return eContagem;

    }


    public long getSegundosTotal() {

        long eContando = 0;
        long eFramesTotal = getDuracao();

        while (eFramesTotal >= 1000) {
            eFramesTotal -= 1000;
            eContando += 1;
        }

        return eContando;

    }

    public String getTempoTotalFormatado() {

        int eHoras = 0;
        int eMinutos = 0;
        long eSegundos = getSegundosTotal();


        while (eSegundos >= 60) {
            eSegundos -= 60;
            eMinutos += 1;
        }

        while (eMinutos >= 60) {
            eMinutos -= 60;
            eHoras += 1;
        }


        return eHoras + ":" + eMinutos + ":" + eSegundos;
    }


    public int getDuracao() {
        return getQuadrosTotal() * mTaxa;
    }

    public void fechar() {
        try {
            mArquivo.fechar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

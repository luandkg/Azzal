package libs.Arquivos.Video;


import libs.Arquivos.Binario.Arquivador;
import libs.Arquivos.IM;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Empilhador {

    private Arquivador mArquivo;
    private boolean mExisteQuadroAnterior;

    private int mLargura;
    private int mAltura;

    private int eGuardandoFrame;
    private Arena mArenaInicial;
    private Arena mArenaCorrente;
    private BufferedImage mUltimoFrame;

    private  long mTotal;
    private long mUsou;

    private final int FRAME_KEYFRAME = 50;
    private final int FRAME_REPETIR = 51;
    private final int FRAME_DIFERENCIAL = 52;

    private final int FRAME_FINALIZAR = 53;

    private final int QUADRUM_INICIAR = 30;
    private final int QUADRUM_INTERMEDIUM = 31;
    private final int QUADRUM_FINALIZAR = 32;

    private  int tipo_anterior ;
    private  int dif_contador ;

    public Empilhador(Arquivador eArquivo, int eLargura, int eAltura) {

        mArquivo = eArquivo;
        mLargura = eLargura;
        mAltura = eAltura;

        mExisteQuadroAnterior = false;

        mArenaCorrente = criarQuadroVazio();

        //  mBlocosN = mUtils.getBlocosNum(mLargura, mAltura);
        eGuardandoFrame = 0;
        mUltimoFrame = null;

        mTotal = 0;
        mUsou = 0;

        tipo_anterior-=1;
        dif_contador=0;

    }

    public Empilhador(Arquivador eArquivo, int eLargura, int eAltura, Arena eArena) {

        mArquivo = eArquivo;
        mLargura = eLargura;
        mAltura = eAltura;

        mExisteQuadroAnterior = true;

        eGuardandoFrame = 0;

        mArenaCorrente = eArena;
        eGuardandoFrame += mArenaCorrente.getFramesUsadosContagem();

        //  System.out.println("Iniciar quadro -> " + mQuadrumCorrente.getPonteiro());
        mArenaInicial = mArenaCorrente;

        while (mArenaCorrente.getProximo() > 0) {

            mArenaCorrente = new Arena(mArquivo, mArenaCorrente.getProximo());
            eGuardandoFrame += mArenaCorrente.getFramesUsadosContagem();

            //System.out.println("Trocar quadro -> " + mQuadrumCorrente.getPonteiro());

        }


        // mBlocosN = mUtils.getBlocosNum(mLargura, mAltura);

        mUltimoFrame = null;
        mTotal = 0;
        mUsou = 0;

        tipo_anterior-=1;
        dif_contador=0;
    }


    public int getLargura() {
        return mLargura;
    }

    public int getAltura() {
        return mAltura;
    }

    private Arena criarQuadroVazio() {


        long ePonteiro = 0;

        //  System.out.println(" -->> NOVO QUADRUM ..... ");

        if (mExisteQuadroAnterior) {

            mArquivo.setPonteiro(mArquivo.getLength());
            ePonteiro = mArquivo.getPonteiro();

            mArquivo.writeByte((byte) QUADRUM_INICIAR);

            mArquivo.writeLong(mArenaCorrente.getPonteiro());
        } else {

            ePonteiro = mArquivo.getPonteiro();

            mArquivo.writeByte((byte) QUADRUM_INICIAR);

            mArquivo.writeLong(0);
        }

        mArquivo.writeLong((byte) 0);
        mArquivo.writeByte((byte) QUADRUM_INTERMEDIUM);


        for (int i = 0; i < 100; i++) {
            mArquivo.writeLong((byte) 0);
        }

        mArquivo.writeByte((byte) QUADRUM_FINALIZAR);

        Arena mArenaNovo = new Arena(mArquivo, ePonteiro);

        if (mExisteQuadroAnterior) {

            mArquivo.setPonteiro(mArenaCorrente.getPonteiro());
            mArquivo.readByte();
            mArquivo.readLong();
            mArquivo.writeLong(ePonteiro);

        } else {
            mExisteQuadroAnterior = true;
            mArenaInicial = mArenaNovo;
        }

        return mArenaNovo;
    }

    public int getFrameCorrente() {
        return eGuardandoFrame;
    }

    public Arenar empurrarQuadro(BufferedImage eImagemQuadro) {


        Arenar eArenar = null;
        boolean foiObrigado = false;

        if (dif_contador >= 10) {
            //  System.out.println("\t - OBRIGATORIZAR COMPLETO");
            eArenar = guardarFrameCompleto(tipo_anterior, eImagemQuadro);
            dif_contador = 0;
            foiObrigado = true;
        } else {
            eArenar = guardarFrame(tipo_anterior, eImagemQuadro);
        }


        String eResto = "Local = " + eArenar.getLocal();
        eResto += " Tamanho = " + eArenar.getTamanho();
        eResto += " Tipo = " + eArenar.getTipoFrame().toString();

        if (foiObrigado) {
            eResto += " :: FOI OBRIGATORIO";
        }

        eArenar.setStatus(getFrameCorrente() + " $ARQUIVO " + eResto);

        tipo_anterior = eArenar.getTipoCorrente();

        if (tipo_anterior == 1) {
            dif_contador += 1;
            //System.out.println("\t - Contagem -->> " + dif_contador);
        } else if (tipo_anterior == 2) {
            dif_contador += 1;
        } else {
            dif_contador = 0;
        }

        return eArenar;
    }

    public Arenar guardarFrame(int eTipoAnterior, BufferedImage eFrame) {


        if (eFrame.getWidth() != mLargura) {
            throw new IllegalArgumentException("Largura incompativel");
        }

        if (eFrame.getHeight() != mAltura) {
            throw new IllegalArgumentException("Altura incompativel");
        }

        eGuardandoFrame += 1;

        if (eGuardandoFrame % 5 == 0) {
            mUltimoFrame = null;
        }

        Arenar eArenar = new Arenar();
        eArenar.setFrameIndex(eGuardandoFrame);

        if (mArenaCorrente.getFramesLivreContagem() == 0) {
            mArenaCorrente = criarQuadroVazio();
            //     System.out.println("Alocar novo Quadro");
            eArenar.setAlocouNovoQuadro(true);
        }


        mArquivo.setPonteiro(mArquivo.getLength());

        long ePonteiro = mArquivo.getPonteiro();

        eArenar.setLocal(ePonteiro);

        // System.out.println("Guardando Frame em " + ePonteiro);

        escreverQuadro(eTipoAnterior, eArenar, ePonteiro, eFrame);

        //      System.out.println("Guardando Frame " + eGuardandoFrame + " no Quadro " + mQuadrumCorrente.getPonteiro());

        mArenaCorrente.guardar(ePonteiro);


        return eArenar;
    }

    public Arenar guardarFrameCompleto(int eTipoAnterior, BufferedImage eFrame) {


        if (eFrame.getWidth() != mLargura) {
            throw new IllegalArgumentException("Largura incompativel");
        }

        if (eFrame.getHeight() != mAltura) {
            throw new IllegalArgumentException("Altura incompativel");
        }

        eGuardandoFrame += 1;

        if (eGuardandoFrame % 5 == 0) {
            mUltimoFrame = null;
        }

        Arenar eArenar = new Arenar();
        eArenar.setFrameIndex(eGuardandoFrame);

        if (mArenaCorrente.getFramesLivreContagem() == 0) {
            mArenaCorrente = criarQuadroVazio();
            //     System.out.println("Alocar novo Quadro");
            eArenar.setAlocouNovoQuadro(true);
        }


        mArquivo.setPonteiro(mArquivo.getLength());

        long ePonteiro = mArquivo.getPonteiro();

        eArenar.setLocal(ePonteiro);

        // System.out.println("Guardando Frame em " + ePonteiro);

        escreverQuadroCompleto(eTipoAnterior, eArenar, ePonteiro, eFrame);

        //      System.out.println("Guardando Frame " + eGuardandoFrame + " no Quadro " + mQuadrumCorrente.getPonteiro());

        mArenaCorrente.guardar(ePonteiro);


        return eArenar;
    }

    public long getUsou() {
        return mUsou;
    }

    public long getTotal() {
        return mTotal;
    }

    private void escreverQuadro(int eTipoAnterior, Arenar eArenar, long eLugar, BufferedImage imagem) {

        //  System.out.println("Escrever quadro em " + mArquivo.getPonteiro());


        BufferedImage mCorrenteFrame = imagem;

        int eX1 = 0;
        int eX2 = 0;

        int eY1 = 0;
        int eY2 = 0;

        int eReal = mCorrenteFrame.getWidth() * mCorrenteFrame.getHeight();


        mTotal += (mCorrenteFrame.getWidth() * mCorrenteFrame.getHeight());

        int mModalidade = 0;

        if (eTipoAnterior == -1 || mUltimoFrame == null) {

            // ESCREVER QUADRO COMPLETO

            eX1 = 0;
            eX2 = mCorrenteFrame.getWidth();


            eY1 = 0;
            eY2 = mCorrenteFrame.getHeight();

            //  System.out.println("Frame :: Novo KeyFrame");

            mUsou += (mCorrenteFrame.getWidth() * mCorrenteFrame.getHeight());
            eArenar.setTipoFrame(QuadroTipo.Completo);

            mModalidade = 0;

            eArenar.setTipoCorrente(0);

        }

        // TEM QUADRO ANTERIOR
        if (mUltimoFrame != null) {

            Q4 mDiferenca = getDiferenca(mCorrenteFrame, mUltimoFrame);


            if (mDiferenca.getX2() > 0 && mDiferenca.getY2() > 0) {

                int dx = mDiferenca.getX2() - mDiferenca.getX();
                int dy = mDiferenca.getY2() - mDiferenca.getY();

                int eRedux = dx * dy;

                double mReducao = (1 - ((double) (eRedux) / (double) eReal)) * 100.0F;

                // System.out.println("t - Diff x :: " + mDiferenca.getX() + "::" + mDiferenca.getX2() + " -->> " + (mDiferenca.getX2() - mDiferenca.getX()));
                // System.out.println("t - Diff y :: " + mDiferenca.getY() + "::" + mDiferenca.getY2() + " -->> " + (mDiferenca.getY2() - mDiferenca.getY()));
                //System.out.println("t - Diff  " + eReal + " -->> " + eRedux + " :: " + getCasas(mReducao, 2) + " %");

                mUsou += (mCorrenteFrame.getWidth() * (mDiferenca.getY() - mDiferenca.getX()));

                eArenar.setTipoFrame(QuadroTipo.Diferencial);

                imagem = getImagemAdequada(imagem, mDiferenca);
                mModalidade = 2;

                eX1 = mDiferenca.getX();
                eX2 = mDiferenca.getX2();

                eY1 = mDiferenca.getY();
                eY2 = mDiferenca.getY2();

                eArenar.setTipoCorrente(1);

            } else {
                // System.out.println("t - Rept x :: " + mDiferenca.getX() + "::" + mDiferenca.getX2());
                // System.out.println("t - Rept y :: " + mDiferenca.getY() + "::" + mDiferenca.getY2());

                //System.out.println("t - Rept :: " + mDiferenca.getX() + "::" + mDiferenca.getY());
                //  mUsou += (mCorrenteFrame.getWidth() * mCorrenteFrame.getHeight());

                boolean igual = isIgual(mCorrenteFrame, mUltimoFrame);

                if (igual) {
                    mModalidade = 1;
                    eArenar.setTipoFrame(QuadroTipo.Repetir);
                    eArenar.setTipoCorrente(2);
                } else {

                    eArenar.setTipoFrame(QuadroTipo.Completo);
                    mModalidade = 0;

                    eX1 = 0;
                    eX2 = mCorrenteFrame.getWidth();


                    eY1 = 0;
                    eY2 = mCorrenteFrame.getHeight();

                    //  System.out.println("Frame :: Novo KeyFrame");

                    mUsou += (mCorrenteFrame.getWidth() * mCorrenteFrame.getHeight());
                    eArenar.setTipoFrame(QuadroTipo.Completo);

                    eArenar.setTipoCorrente(0);

                }


            }


        }

        arquivar_quadro(eArenar, eX1, eY1, eX2, eY2, mCorrenteFrame, imagem, mModalidade, eLugar);


    }


    private void escreverQuadroCompleto(int eTipoAnterior, Arenar eArenar, long eLugar, BufferedImage imagem) {

        //  System.out.println("Escrever quadro em " + mArquivo.getPonteiro());

        // ESCREVER QUADRO COMPLETO

        BufferedImage mCorrenteFrame = imagem;

        int eX1 = 0;
        int eX2 = mCorrenteFrame.getWidth();

        int eY1 = 0;
        int eY2 = mCorrenteFrame.getHeight();

        mTotal += (mCorrenteFrame.getWidth() * mCorrenteFrame.getHeight());

        int mModalidade = 0;

        //  System.out.println("Frame :: Novo KeyFrame");

        mUsou += (mCorrenteFrame.getWidth() * mCorrenteFrame.getHeight());
        eArenar.setTipoFrame(QuadroTipo.Completo);

        eArenar.setTipoCorrente(0);


        arquivar_quadro(eArenar, eX1, eY1, eX2, eY2, mCorrenteFrame, imagem, mModalidade, eLugar);

    }

    private void arquivar_quadro(Arenar eArenar, int eX1, int eY1, int eX2, int eY2, BufferedImage mCorrenteFrame, BufferedImage imagem, int mModalidade, long eLugar) {


        mUltimoFrame = mCorrenteFrame;

        mArquivo.setPonteiro(eLugar);


        if (mModalidade == 0) {

            mArquivo.writeByte((byte) FRAME_KEYFRAME);

        } else if (mModalidade == 1) {

            mArquivo.writeByte((byte) FRAME_REPETIR);

        } else if (mModalidade == 2) {

            mArquivo.writeByte((byte) FRAME_DIFERENCIAL);

            mArquivo.writeInt(eX1);
            mArquivo.writeInt(eY1);

            mArquivo.writeInt(eX2);
            mArquivo.writeInt(eY2);

        }

        if (mModalidade == 0 || mModalidade == 2) {

            if (imagem != null) {
                long t = IM.salvar_bytes(imagem, mArquivo);
                eArenar.setTamanho(t);
            }

        }

        mArquivo.writeByte((byte) FRAME_FINALIZAR);
    }


    public BufferedImage getImagemAdequada(BufferedImage mIMG_01, Q4 eRegiao) {

        int largura = (eRegiao.getX2() - eRegiao.getX());
        int altura = (eRegiao.getY2() - eRegiao.getY());

        // System.out.println("Gerar quadro adequado : Largura = " + largura + " Altura = " + altura);

        if (altura > 0) {


            BufferedImage mRet = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);

            int x1 = eRegiao.getX();
            int x2 = eRegiao.getX2();

            int y1 = eRegiao.getY();
            int y2 = eRegiao.getY2();

            //   int m1l = mIMG_01.getWidth();


            int ny = 0;

            for (int aqy = y1; aqy <= y2; aqy++) {

                if (aqy < mAltura && ny < altura) {
                    int nx = 0;

                    for (int aqx = x1; aqx <= x2; aqx++) {
                        if (aqx < mLargura && nx < largura) {
                            //   System.out.println(" x = " + aqx + " y = " + aqy);

                            int pixel = mIMG_01.getRGB(aqx, aqy);

                            mRet.setRGB(nx, ny, pixel);
                            nx += 1;
                        }

                    }
                    ny += 1;

                }


            }


            return mRet;

        } else {
            return null;
        }

    }


    public Q4 getDiferenca(BufferedImage mIMG_01, BufferedImage mIMG_02) {

        int m1l = mIMG_01.getWidth();
        int m1a = mIMG_01.getHeight();

        int m2l = mIMG_02.getWidth();
        int m2a = mIMG_02.getHeight();


        int mLinha1 = -1;
        int mLinha2 = -1;

        int mColuna1 = -1;
        int mColuna2 = -1;


        boolean mPegouLinha = false;
        boolean mPegouColuna = false;

        if (m1l == m2l && m1a == m2a) {
            //System.out.println("Comparavel : Sim");


            // Pegar Linhas
            for (int aqy = 0; aqy < m1a; aqy++) {

                boolean isIgual = true;

                for (int aqx = 0; aqx < m1l; aqx++) {

                    int pixel1 = mIMG_01.getRGB(aqx, aqy);
                    int pixel2 = mIMG_02.getRGB(aqx, aqy);

                    if (pixel1 != pixel2) {
                        isIgual = false;
                        break;
                    }

                }

                if (!isIgual) {
                    if (!mPegouLinha) {
                        mLinha1 = aqy;
                        mLinha2 = aqy;
                        mPegouLinha = true;
                    } else {
                        mLinha2 = aqy;
                    }
                }

            }

            // Pegar Colunas
            for (int aqx = 0; aqx < m1l; aqx++) {

                boolean isIgual = true;

                for (int aqy = 0; aqy < m1a; aqy++) {

                    int pixel1 = mIMG_01.getRGB(aqx, aqy);
                    int pixel2 = mIMG_02.getRGB(aqx, aqy);

                    if (pixel1 != pixel2) {
                        isIgual = false;
                        break;
                    }

                }

                if (!isIgual) {
                    if (!mPegouColuna) {
                        mColuna1 = aqx;
                        mColuna2 = aqx;
                        mPegouColuna = true;
                    } else {
                        mColuna2 = aqx;
                    }
                }

            }

            if (mPegouLinha && mPegouColuna) {

                mColuna2 += 1;
                mLinha2 += 1;

            }

        }


        return new Q4(mColuna1, mLinha1, mColuna2, mLinha2);
    }

    public boolean isIgual(BufferedImage mIMG_01, BufferedImage mIMG_02) {

        int m1l = mIMG_01.getWidth();
        int m1a = mIMG_01.getHeight();

        int m2l = mIMG_02.getWidth();
        int m2a = mIMG_02.getHeight();


        boolean ret = true;

        if (m1l == m2l && m1a == m2a) {
            //System.out.println("Comparavel : Sim");

            // Pegar Linhas
            for (int aqy = 0; aqy < m1a; aqy++) {

                for (int aqx = 0; aqx < m1l; aqx++) {

                    int pixel1 = mIMG_01.getRGB(aqx, aqy);
                    int pixel2 = mIMG_02.getRGB(aqx, aqy);

                    if (pixel1 != pixel2) {
                        ret = false;
                        break;
                    }

                }

                if (!ret) {
                    break;
                }

            }

        } else {
            ret = false;
        }


        return ret;
    }

    public int getQuadrosContagem() {
        int eContando = 1;

        Arena eQuadroPercursor = mArenaInicial;


        while (eQuadroPercursor.getProximo() != 0) {
            eContando += 1;
            eQuadroPercursor = new Arena(mArquivo, eQuadroPercursor.getProximo());
        }

        return eContando;
    }

    public int getFramesContagem() {

        int eContagem = 0;

        ArrayList<Arena> mQuadros = new ArrayList<Arena>();

        mQuadros.add(mArenaInicial);

        Arena eQuadroPercursor = mArenaInicial;

        eContagem += eQuadroPercursor.getFramesUsadosContagem();

        while (eQuadroPercursor.getProximo() != 0) {
            eQuadroPercursor = new Arena(mArquivo, eQuadroPercursor.getProximo());
            eContagem += eQuadroPercursor.getFramesUsadosContagem();
        }

        return eContagem;

    }


    public void fechar() {

        try {
            mArquivo.fechar();
        } catch (IOException e) {
        }


    }
}

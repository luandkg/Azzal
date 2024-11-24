package libs.arquivos.ds_armazenados;

import libs.arquivos.IM;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;
import libs.arquivos.ds.DSItem;
import libs.arquivos.video.Quadro;
import libs.luan.Lista;
import libs.luan.fmt;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class DSVideo {

    private final int FRAME_KEYFRAME = 50;
    private final int FRAME_REPETIR = 51;
    private final int FRAME_DIFERENCIAL = 52;


    private int mLargura;
    private int mAltura;
    private int mTaxa;
    private Arquivador mArquivo;

    private DSArena mArenaInicial;
    private DSArena mArenaCorrente;
    private BufferedImage mImagemCorrente;

    private int mArenaIndex;
    private int mFrameIndex;
    private boolean mAcabou;

    private DSItem mItem;

    public DSVideo(DSItem eItem) {
        mLargura = 0;
        mAltura = 0;
        mTaxa = 0;
        mArenaIndex = 0;
        mFrameIndex = 0;
        mAcabou = false;
        mItem = eItem;
    }

    public boolean getAcabou() {
        return mAcabou;
    }

    public void abrir() {

        mArquivo=new Arquivador(mItem.getArquivo());

        mArquivo.setPonteiro(mItem.getInicio());


        byte b1 = mArquivo.get();
        byte b2 = mArquivo.get();
        byte b3 = mArquivo.get();

       // System.out.println("Cabecalho : " + b1 + "." + b2 + "." + b3);

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

        mArenaInicial = new DSArena(mArquivo,  mItem,ePosicao-mItem.getInicio());
        mArenaCorrente = mArenaInicial;

        mImagemCorrente = new BufferedImage(mLargura, mAltura, BufferedImage.TYPE_INT_ARGB);

    }


    public BufferedImage getImagemCorrente() {
        return mImagemCorrente;
    }

    public void procurarQuadro() {

        Lista<DSQuadro> quadros_obtidos = mArenaCorrente.getQuadros();
      //  fmt.print("Quadros = {}",quadros_obtidos.getQuantidade());


        for (DSQuadro eQuadro : quadros_obtidos) {

            // System.out.println("Frame :: " + eQuadro.getIndex());

            if (eQuadro.getIndex() == mFrameIndex) {

                long ip_para = mItem.getInicio()+eQuadro.getConteudo();

                if (ip_para == 0) {
                    mAcabou = true;
                } else {

                    //fmt.print("Conteudo : {}",eQuadro.getConteudo());
                    //fmt.print("Ir Para  : {}",ip_para);
                    //fmt.print("Inicio   : {}",mItem.getInicio());
                  //  fmt.print("Fim      : {}",mItem.getFim());

                    mArquivo.setPonteiro(ip_para);
                    lerQuadro();

                }
                break;
            }
        }

    }



    public void proximo() {

       //  System.out.println("Ler Quadro -- " + mArenaIndex + "::" + mFrameIndex);

        procurarQuadro();

       // fmt.print("Avancando....");

        avancar();

    }

    public void avancar() {

        mFrameIndex += 1;

        if (mFrameIndex >= 100) {
            mFrameIndex = 0;
            mArenaIndex += 1;

           // fmt.print("Avance :: {}",mArenaCorrente.getProximo());

            if (mArenaCorrente.getProximo() > 0) {
                mArenaCorrente = new DSArena(mArquivo, mItem,mArenaCorrente.getProximo());
            } else {
                mAcabou = true;
            }
        }
    }


    public int getFrameCorrente() {
        return (mArenaIndex * 100) + mFrameIndex;
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




    public void fechar() {
        try {
            mArquivo.fechar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

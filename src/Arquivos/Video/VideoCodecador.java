package Arquivos.Video;


import Arquivos.Binario.Arquivador;
import AssetContainer.Chronos_Intervalo;
import Imaginador.ImageUtils;
import Luan.OrdenadorAlfaNum;
import Luan.fmt;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class VideoCodecador {


    public static void init(String eArquivo) {

        boolean mCriar = true;
        boolean mAnexar = false;

        if (mCriar) {
            criar(eArquivo);
        }

        if (mAnexar) {
            anexar();
        }


    }


    public static void compare() {

        System.out.println("");
        System.out.println("------------------------- VI - COMPARATOR --------------------------");
        System.out.println("");

        String eLocal = "/home/luan/IdeaProjects/Azzal/res/ecossistema";

        File folder = new File(eLocal);
        File[] listOfFiles = folder.listFiles();

        ArrayList<String> mArquivos = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                mArquivos.add(listOfFiles[i].getAbsolutePath());
            }
        }

        if (mArquivos.size() > 2) {

            BufferedImage mIMG_01 = null;
            BufferedImage mIMG_02 = null;

            try {
                mIMG_01 = ImageIO.read(new File(mArquivos.get(12)));
            } catch (IOException e) {
            }

            try {
                mIMG_02 = ImageIO.read(new File(mArquivos.get(13)));
            } catch (IOException e) {
            }

            int m1l = mIMG_01.getWidth();
            int m1a = mIMG_01.getHeight();

            int m2l = mIMG_02.getWidth();
            int m2a = mIMG_02.getHeight();


            System.out.println("Imagem 1 - Largura : " + m1l);
            System.out.println("Imagem 1 - Altura : " + m1a);
            System.out.println("Imagem 2 - Largura : " + m2l);
            System.out.println("Imagem 2 - Altura : " + m2a);
            System.out.println("");

            int mLinha1 = 0;
            int mLinha2 = 0;

            int mColuna1 = 0;
            int mColuna2 = 0;


            boolean mPegouLinha = false;
            boolean mPegouColuna = false;

            if (m1l == m2l && m1a == m2a) {
                System.out.println("Comparavel : Sim");


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

                System.out.println("Diferencial");

                System.out.println("\t - X1 : " + mColuna1);
                System.out.println("\t - x2 : " + mColuna2);

                System.out.println("\t - Y1 : " + mLinha1);
                System.out.println("\t - Y2 : " + mLinha2);


            } else {
                System.out.println("Comparavel : Nao");

            }


        }


        System.out.println("");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("");


    }


    public static void criar(String eArquivo) {

        System.out.println("");
        System.out.println("------------------------- VIDEO_CODECADOR --------------------------");
        System.out.println("");


        String eLocal = "/home/luan/IdeaProjects/Azzal/res/ecossistema";

        File folder = new File(eLocal);
        File[] listOfFiles = folder.listFiles();

        ArrayList<String> mArquivos = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                mArquivos.add(listOfFiles[i].getAbsolutePath());
            }
        }

        int mLargura = 800;
        int mAltura = 801;
        int mTaxa = 200;

        Chronos_Intervalo mCrono = new Chronos_Intervalo();
        mCrono.marqueInicio();

        Collections.sort(mArquivos, new OrdenadorAlfaNum());

        System.out.println("\t - ARQUIVO = " + eArquivo);
        System.out.println("");
        System.out.println("\t - LARGURA = " + mLargura);
        System.out.println("\t - ALTURA = " + mAltura);
        System.out.println("\t - TAXA = " + mTaxa);
        System.out.println("");

        VideoCodecador eVideoCodecador = new VideoCodecador();

        Empilhador eEmpilhador = eVideoCodecador.criar(eArquivo, mLargura, mAltura);

        boolean mCarregado = true;


        for (String eArquivoParaFrame : mArquivos) {

            mCarregado = true;

            String eArquivoFrameFormatado = eArquivoParaFrame;
            eArquivoFrameFormatado = eArquivoFrameFormatado.replace(eLocal, "");
            eArquivoFrameFormatado = eArquivoFrameFormatado.replace("/", "");

            try {

                Arenar eArenar = eEmpilhador.empurrarQuadro(ImageIO.read(new File(eArquivoParaFrame)));

                System.out.println("\t - FRAME SUCESSO :: " + eArenar.getStatus().replace("$ARQUIVO", eArquivoParaFrame));


            } catch (IOException e) {
                mCarregado = false;
            }


            if (!mCarregado) {
                System.out.println("\t - FRAME FALHOU  :: " + eEmpilhador.getFrameCorrente() + " " + eArquivoFrameFormatado);
                break;
            }

        }

        mCrono.marqueFim();

        System.out.println("");


        double mReducao = (1 - ((double) (eEmpilhador.getUsou()) / (double) eEmpilhador.getTotal())) * 100.0F;

        System.out.println("\t - REDUCAO = " + fmt.getCasas(mReducao, 2) + " %");

        System.out.println("\t - TEMPO = " + mCrono.getIntervalo() + " s");

        if (mCarregado) {
            System.out.println("\t - STATUS = SUCESSO");
        } else {
            System.out.println("\t - STATUS = FALHOU");
        }

        eEmpilhador.fechar();

        System.out.println("");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("");

    }


    public static void anexar() {


        System.out.println("");
        System.out.println("------------------------- VIDEO_CODECADOR ANEXADOR --------------------------");
        System.out.println("");


        String eLocal = "/home/luan/IdeaProjects/Azzal/res/ecossistema";

        File folder = new File(eLocal);
        File[] listOfFiles = folder.listFiles();

        ArrayList<String> mArquivos = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                mArquivos.add(listOfFiles[i].getAbsolutePath());
            }
        }

        int mLargura = 800;
        int mAltura = 800;
        int mTaxa = 200;

        Chronos_Intervalo mCrono = new Chronos_Intervalo();
        mCrono.marqueInicio();

        Collections.sort(mArquivos, new OrdenadorAlfaNum());

        System.out.println("\t - ARQUIVO = video.vi");
        System.out.println("");
        System.out.println("\t - LARGURA = " + mLargura);
        System.out.println("\t - ALTURA = " + mAltura);
        System.out.println("\t - TAXA = " + mTaxa);
        System.out.println("");

        VideoCodecador eVideoCodecador = new VideoCodecador();

        Empilhador eEmpilhador = eVideoCodecador.anexar("video.vi");

        System.out.println("\t - QUADROS = " + eEmpilhador.getQuadrosContagem());
        System.out.println("\t - FRAMES = " + eEmpilhador.getFramesContagem());

        boolean mCarregado = true;


        for (String eArquivoParaFrame : mArquivos) {

            mCarregado = true;

            try {

                Arenar eArenar = eEmpilhador.empurrarQuadro(ImageIO.read(new File(eArquivoParaFrame)));

                System.out.println("\t - FRAME SUCESSO :: " + eArenar.getStatus().replace("$ARQUIVO", eArquivoParaFrame));


            } catch (IOException e) {
                mCarregado = false;
            }

            eArquivoParaFrame = eArquivoParaFrame.replace(eLocal, "");
            eArquivoParaFrame = eArquivoParaFrame.replace("/", "");

            if (mCarregado) {
                System.out.println("\t - FRAME SUCESSO :: " + eEmpilhador.getFrameCorrente() + " " + eArquivoParaFrame);
            } else {
                System.out.println("\t - FRAME FALHOU  :: " + eEmpilhador.getFrameCorrente() + " " + eArquivoParaFrame);
                break;
            }

        }

        mCrono.marqueFim();

        System.out.println("");

        System.out.println("\t - TEMPO = " + mCrono.getIntervalo() + " s");

        if (mCarregado) {
            System.out.println("\t - STATUS = SUCESSO");
        } else {
            System.out.println("\t - STATUS = FALHOU");
        }

        eEmpilhador.fechar();

        System.out.println("");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("");

    }

    public static void abrir(String eArquivo) {

        String eAbrir = eArquivo;

        System.out.println("");
        System.out.println("------------------------------------ VIDEO_CODECADOR - ABRIR -----------------------------------------------------");
        System.out.println("");


        System.out.println("\t - Arquivo : " + eAbrir);
        System.out.println("");

        Video eVideo = new Video();
        eVideo.abrir(eAbrir);


        System.out.println("\t - Largura : " + eVideo.getLargura());
        System.out.println("\t - Altura : " + eVideo.getAltura());
        System.out.println("\t - Taxa : " + eVideo.getTaxa());
        System.out.println("");

        System.out.println("\t - Arenas : " + eVideo.getArenasContagem());
        System.out.println("");

        for (Arena eArena : eVideo.getArenas()) {

            System.out.println("\t\t ARENA  " + fmt.getN8(eArena.getPonteiro()) + " - " + fmt.getN8(eArena.getAnterior()) + " - " + fmt.getN8(eArena.getProximo()) + "     :::     Quadros = " + fmt.getN3(eArena.getFramesContagem()) + " Usados = " + fmt.getN3(eArena.getFramesUsadosContagem()) + " Livres = " + fmt.getN3(eArena.getFramesLivreContagem()));

            for (Quadro eQuadro : eArena.getQuadros()) {
                System.out.println("\t\t\t    Quadro " + fmt.getN2(eQuadro.getIndex()) + " " + fmt.getN8(eQuadro.getInicio()) + " - " + fmt.getN8(eQuadro.getFim()) + " -->> " + eQuadro.getConteudo());
            }


        }

        eVideo.fechar();

        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("");

    }


    public Empilhador criar(String eArquivo, int eLargura, int eAltura) {

        Arquivador arquivador = new Arquivador(eArquivo);


        arquivador.writeByte((byte) 77); // M
        arquivador.writeByte((byte) 86); // V
        arquivador.writeByte((byte) 49); // 1

        arquivador.writeByte((byte) 1);

        arquivador.writeLong((long) eLargura);
        arquivador.writeLong((long) eAltura);

        arquivador.writeByte((byte) 1);

        arquivador.writeLong((long) 200);

        arquivador.writeByte((byte) 1);


        return new Empilhador(arquivador, eLargura, eAltura);


    }

    public Empilhador anexar(String eArquivo) {


        Arquivador mArquivo = new Arquivador(eArquivo);

        mArquivo.setPonteiro(0);


        byte b1 = mArquivo.readByte();
        byte b2 = mArquivo.readByte();
        byte b3 = mArquivo.readByte();

        //  System.out.println("Cabecalho : " + b1 + "." + b2 + "." + b3);

        byte p1 = mArquivo.readByte();

        long w = mArquivo.readLong();
        long h = mArquivo.readLong();

        byte p2 = mArquivo.readByte();

        long eTaxa = mArquivo.readLong();

        byte p3 = mArquivo.readByte();


        int mLargura = (int) w;
        int mAltura = (int) h;
        int mTaxa = (int) eTaxa;

        long ePosicao = mArquivo.getPonteiro();

        Arena mArenaInicial = new Arena(mArquivo, ePosicao);

        return new Empilhador(mArquivo, mLargura, mAltura, mArenaInicial);

    }

    public static void criar(String eArquivo, ArrayList<String> eArquivos) {

        System.out.println("");
        System.out.println("------------------------- VIDEO_CODECADOR --------------------------");
        System.out.println("");


        BufferedImage primeira = ImageUtils.getImagem(eArquivos.get(0));

        int mLargura = primeira.getWidth();
        int mAltura = primeira.getHeight();

        int mTaxa = 200;

        Chronos_Intervalo mCrono = new Chronos_Intervalo();
        mCrono.marqueInicio();


        System.out.println("\t - ARQUIVO = " + eArquivo);
        System.out.println("");
        System.out.println("\t - LARGURA = " + mLargura);
        System.out.println("\t - ALTURA = " + mAltura);
        System.out.println("\t - TAXA = " + mTaxa);
        System.out.println("");

        VideoCodecador eVideoCodecador = new VideoCodecador();

        Empilhador eEmpilhador = eVideoCodecador.criar(eArquivo, mLargura, mAltura);

        boolean mCarregado = true;

        Collections.sort(eArquivos, new OrdenadorAlfaNum());

        for (String eArquivoParaFrame : eArquivos) {

            mCarregado = true;

            String eArquivoFrameFormatado = eArquivoParaFrame;
            // eArquivoFrameFormatado = eArquivoFrameFormatado.replace(eLocal, "");
            // eArquivoFrameFormatado = eArquivoFrameFormatado.replace("/", "");

            try {

                Arenar eArenar = eEmpilhador.empurrarQuadro(ImageIO.read(new File(eArquivoParaFrame)));

                System.out.println("\t - FRAME SUCESSO :: " + eArenar.getStatus().replace("$ARQUIVO", eArquivoParaFrame));


            } catch (IOException e) {
                mCarregado = false;
            }


            if (!mCarregado) {
                System.out.println("\t - FRAME FALHOU  :: " + eEmpilhador.getFrameCorrente() + " " + eArquivoFrameFormatado);
                break;
            }

        }

        mCrono.marqueFim();

        System.out.println("");


        double mReducao = (1 - ((double) (eEmpilhador.getUsou()) / (double) eEmpilhador.getTotal())) * 100.0F;

        System.out.println("\t - REDUCAO = " + fmt.getCasas(mReducao, 2) + " %");

        System.out.println("\t - TEMPO = " + mCrono.getIntervalo() + " s");

        if (mCarregado) {
            System.out.println("\t - STATUS = SUCESSO");
        } else {
            System.out.println("\t - STATUS = FALHOU");
        }

        eEmpilhador.fechar();

        System.out.println("");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("");

    }

}

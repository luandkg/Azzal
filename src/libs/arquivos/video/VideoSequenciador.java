package libs.arquivos.video;

import libs.arquivos.binario.Arquivador;
import libs.luan.fmt;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VideoSequenciador {

    public static void criar(String eArquivo, int mLargura, int mAltura, String eRecurso, int inicio, int fim, String eSufixo) {


        System.out.println("");
        System.out.println("------------------------- VI - VIDEO SEQUENCIADOR --------------------------");
        System.out.println("");

        Arquivador.remover(eArquivo);


        ArrayList<String> mArquivos = new ArrayList<String>();

        for (int c = inicio; c < fim; c++) {
            mArquivos.add(eRecurso + String.valueOf(c) + eSufixo);
        }


        int mTaxa = 1;

        Temporizador mCrono = new Temporizador();
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


        for (String eArquivoParaFrame : mArquivos) {

            mCarregado = true;

            String eArquivoFrameFormatado = eArquivoParaFrame;

            try {

                Arenar eArenar = eEmpilhador.empurrarQuadro(ImageIO.read(new File(eArquivoParaFrame)));

                System.out.println("\t - FRAME SUCESSO :: " + eArenar.getStatus().replace("$ARQUIVO",eArquivoParaFrame));

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

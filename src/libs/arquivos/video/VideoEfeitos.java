package libs.arquivos.video;


import libs.imagem.Cinza;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VideoEfeitos {

    public Arenar guardarFrame(Empilhador eCriador, BufferedImage eFrame) {

        Arenar f4 = eCriador.empurrarQuadro(eFrame);

        String s4 = "Local = " + f4.getLocal() + " Tipo = " + f4.getTipoFrame().toString();
        System.out.println("adicionando " + eCriador.getFrameCorrente() + " - " + s4);

        return f4;

    }

    public Arenar efeitoRepetirQuadro(Empilhador eCriador, BufferedImage eFrame, int eQuantidade) {

        Arenar f4 = null;

        for (int p = 0; p < eQuantidade; p++) {

            f4 = eCriador.empurrarQuadro(eFrame);

            String sProlongamento = "Local = " + f4.getLocal() + " Tipo = " + f4.getTipoFrame().toString();
            System.out.println("Repetindo " + eCriador.getFrameCorrente() + " - " + sProlongamento);


        }
        return f4;

    }

    public void efeitoPretoEBrancoPiscar(Empilhador eCriador, BufferedImage eFrame, int eQuantidade) {

        Cinza eCinza = new Cinza();

        BufferedImage eColorida = eFrame;
        BufferedImage ePretoEBranco = eCinza.toCinza(eFrame);

        for (int p = 0; p < eQuantidade; p++) {

            Arenar fpb = eCriador.empurrarQuadro(ePretoEBranco);
            String sProlongamento = "Local = " + fpb.getLocal() + " Tipo = " + fpb.getTipoFrame().toString();
            System.out.println("Preto " + eCriador.getFrameCorrente() + " - " + sProlongamento);

            Arenar fcol = eCriador.empurrarQuadro(eColorida);
            String sCol = "Local = " + fcol.getLocal() + " Tipo = " + fcol.getTipoFrame().toString();
            System.out.println("Colorido " + eCriador.getFrameCorrente() + " - " + sCol);

        }

    }

    public void efeitoMudarCenaHorizontalDuasPartes(Empilhador eCriador, BufferedImage eAntes, BufferedImage eDepois, int eQuantidade) {


        int eLargura = eCriador.getLargura();
        int eAltura = eCriador.getAltura();


        int eLarguraTaxa = eLargura / (eQuantidade * 2);

        System.out.println("Taxa de Transicao por Largura = " + eLarguraTaxa);

        int eMetade = eLargura / 2;

        for (int q = 0; q < eQuantidade; q++) {

            int eDireita = eMetade + (q * eLarguraTaxa);
            int eEsquerda = eMetade - (q * eLarguraTaxa);

            System.out.println("Quadro Transicional : Esquerda = " + eEsquerda + " Direita = " + eDireita);

            BufferedImage mFrameCorrente = new BufferedImage(eCriador.getLargura(), eCriador.getAltura(), BufferedImage.TYPE_INT_ARGB);

            for (int y = 0; y < eAltura; y++) {
                for (int x = 0; x < eLargura; x++) {
                    mFrameCorrente.setRGB(x, y, eDepois.getRGB(x, y));
                }
            }

            if (eDireita < eLargura) {


                for (int y = 0; y < eAltura; y++) {
                    int indo = 0;
                    for (int x = eDireita; x < eLargura; x++) {
                        mFrameCorrente.setRGB(x, y, eAntes.getRGB(eMetade + indo, y));
                        indo += 1;
                    }
                }

            }

            if (eEsquerda >= 0) {


                for (int y = 0; y < eAltura; y++) {
                    int voltando = 0;
                    for (int x = eEsquerda; x >= 0; x--) {
                        mFrameCorrente.setRGB(x, y, eAntes.getRGB(eMetade - voltando, y));
                        voltando += 1;
                    }
                }

            }

            if (eDireita < eLargura) {

                for (int y = 0; y < eAltura; y++) {
                    mFrameCorrente.setRGB(eDireita, y, Color.BLACK.getRGB());
                }

            }

            if (eEsquerda >= 0) {

                for (int y = 0; y < eAltura; y++) {
                    mFrameCorrente.setRGB(eEsquerda, y, Color.BLACK.getRGB());
                }

            }


            eCriador.empurrarQuadro(mFrameCorrente);

        }


    }

    public void efeitoMudarCenaVerticalDuasPartes(Empilhador eCriador, int tipo_anterior, BufferedImage eAntes, BufferedImage eDepois, int eQuantidade) {


        int eLargura = eCriador.getLargura();
        int eAltura = eCriador.getAltura();


        int eAlturaTaxa = eAltura / (eQuantidade * 2);

        System.out.println("Taxa de Transicao por Altura = " + eAlturaTaxa);

        int eMetade = eAltura / 2;

        for (int q = 0; q < eQuantidade; q++) {

            int eSuperior = eMetade - (q * eAlturaTaxa);
            int eInferior = eMetade + (q * eAlturaTaxa);

            System.out.println("Quadro Transicional : Superior = " + eSuperior + " Inferior = " + eInferior);

            BufferedImage mFrameCorrente = new BufferedImage(eCriador.getLargura(), eCriador.getAltura(), BufferedImage.TYPE_INT_ARGB);

            for (int y = 0; y < eAltura; y++) {
                for (int x = 0; x < eLargura; x++) {
                    mFrameCorrente.setRGB(x, y, eDepois.getRGB(x, y));
                }
            }

            if (eSuperior < eAltura) {

                int indo = 0;

                for (int y = eSuperior; y >= 0; y--) {
                    if ((eMetade + indo) < eAltura) {
                        for (int x = 0; x < eLargura; x++) {
                            mFrameCorrente.setRGB(x, y, eAntes.getRGB(x, eMetade + indo));
                        }
                    }

                    indo += 1;

                }

            }

            if (eInferior >= 0) {

                int voltando = 0;

                for (int y = eInferior; y < eAltura; y++) {
                    if ((eMetade - voltando) >= 0) {
                        for (int x = 0; x < eLargura; x++) {
                            mFrameCorrente.setRGB(x, y, eAntes.getRGB(x, eMetade - voltando));
                        }
                    }

                    voltando += 1;

                }

            }

            if (eInferior < eLargura) {

                for (int x = 0; x < eLargura; x++) {
                    mFrameCorrente.setRGB(x, eInferior, Color.BLACK.getRGB());
                }

            }

            if (eSuperior >= 0) {

                for (int x = 0; x < eLargura; x++) {
                    mFrameCorrente.setRGB(x, eSuperior, Color.BLACK.getRGB());
                }

            }


            eCriador.guardarFrame(tipo_anterior, mFrameCorrente);

        }


    }

    public void exportar(BufferedImage eImagem, String eLocal) {


        try {
            ImageIO.write(eImagem, "png", new File(eLocal));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

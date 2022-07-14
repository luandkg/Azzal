package apps.AppArquivos;

import libs.Arquivos.AN;
import libs.Luan.Lista;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimadorCriador {


    public void criarAnimacao_01(String eArquivo) {

        Lista<BufferedImage> mLista = new Lista<BufferedImage>();

        BufferedImage Q1 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        pintarTudo(Q1, Color.WHITE);

        // mLista.adicionar(Q1);

        BufferedImage Q2 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        pintarTudo(Q2, Color.WHITE);
        pintarQuadranteGeral(Q2, Color.BLACK);

        mLista.adicionar(Q2);

        BufferedImage Q3 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        pintarTudo(Q3, Color.WHITE);
        pintarQuadrante(Q3, 1, Color.BLACK);
        pintarQuadranteGeral(Q3, Color.BLACK);

        mLista.adicionar(Q3);

        BufferedImage Q4 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        pintarTudo(Q4, Color.WHITE);
        pintarQuadrante(Q4, 1, Color.BLACK);
        pintarQuadrante(Q4, 2, Color.BLACK);
        pintarQuadranteGeral(Q4, Color.BLACK);

        mLista.adicionar(Q4);

        BufferedImage Q5 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        pintarTudo(Q5, Color.WHITE);
        pintarQuadrante(Q5, 1, Color.BLACK);
        pintarQuadrante(Q5, 2, Color.BLACK);
        pintarQuadrante(Q5, 3, Color.BLACK);

        pintarQuadranteGeral(Q5, Color.BLACK);

        mLista.adicionar(Q5);

        BufferedImage Q6 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        pintarTudo(Q6, Color.WHITE);
        pintarQuadrante(Q6, 1, Color.BLACK);
        pintarQuadrante(Q6, 2, Color.BLACK);
        pintarQuadrante(Q6, 3, Color.BLACK);
        pintarQuadrante(Q6, 4, Color.BLACK);

        pintarQuadranteGeral(Q6, Color.BLACK);

        mLista.adicionar(Q6);

        BufferedImage Q7 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        pintarTudo(Q7, Color.WHITE);
        pintarQuadrante(Q7, 2, Color.BLACK);
        pintarQuadrante(Q7, 3, Color.BLACK);
        pintarQuadrante(Q7, 4, Color.BLACK);

        pintarQuadranteGeral(Q7, Color.BLACK);

        mLista.adicionar(Q7);

        BufferedImage Q8 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        pintarTudo(Q8, Color.WHITE);
        pintarQuadrante(Q8, 3, Color.BLACK);
        pintarQuadrante(Q8, 4, Color.BLACK);

        pintarQuadranteGeral(Q8, Color.BLACK);

        mLista.adicionar(Q8);

        BufferedImage Q9 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        pintarTudo(Q9, Color.WHITE);
        pintarQuadrante(Q9, 4, Color.BLACK);

        pintarQuadranteGeral(Q9, Color.BLACK);

        mLista.adicionar(Q9);

        BufferedImage Q10 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        pintarTudo(Q10, Color.WHITE);

        pintarQuadranteGeral(Q10, Color.BLACK);

        mLista.adicionar(Q10);

        AN.criar(mLista, 200, eArquivo);

    }


    public void criarAnimacao_02(String eArquivo) {

        Lista<BufferedImage> mLista = new Lista<BufferedImage>();

        BufferedImage Q1 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        pintarTudo(Q1, Color.WHITE);

       // mLista.adicionar(Q1);

        BufferedImage Q2 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        pintarTudo(Q2, Color.WHITE);
        pintarQuadranteGeral(Q2, Color.BLACK);

        mLista.adicionar(Q2);

        for (int i = 1; i <= 4; i++) {

            BufferedImage eFluxo = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
            pintarTudo(eFluxo, Color.WHITE);

            if (i == 1) {

                pintarQuadrante(eFluxo, 1, Color.BLACK);

            } else  if (i == 2) {

                pintarQuadrante(eFluxo, 1, Color.BLACK);
                pintarQuadrante(eFluxo, 2, Color.BLACK);

            } else  if (i == 3) {

                pintarQuadrante(eFluxo, 1, Color.BLACK);
                pintarQuadrante(eFluxo, 2, Color.BLACK);
                pintarQuadrante(eFluxo, 3, Color.BLACK);

            } else  if (i == 4) {
                pintarQuadrante(eFluxo, 1, Color.BLACK);
                pintarQuadrante(eFluxo, 2, Color.BLACK);
                pintarQuadrante(eFluxo, 3, Color.BLACK);
                pintarQuadrante(eFluxo, 4, Color.BLACK);


            }

            pintarQuadranteGeral(eFluxo, Color.BLACK);
            mLista.adicionar(eFluxo);
        }

        for (int i = 1; i <= 4; i++) {

            BufferedImage eFluxo = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
            pintarTudo(eFluxo, Color.WHITE);

            if (i == 1) {

                pintarQuadrante(eFluxo, 1, Color.BLACK);
                pintarQuadrante(eFluxo, 2, Color.BLACK);
                pintarQuadrante(eFluxo, 3, Color.BLACK);
                pintarQuadrante(eFluxo, 4, Color.BLACK);
                pintarQuadranteCentro(eFluxo, 1, Color.RED);

            } else  if (i == 2) {

                pintarQuadrante(eFluxo, 1, Color.BLACK);
                pintarQuadrante(eFluxo, 2, Color.BLACK);
                pintarQuadrante(eFluxo, 3, Color.BLACK);
                pintarQuadrante(eFluxo, 4, Color.BLACK);
                pintarQuadranteCentro(eFluxo, 1, Color.RED);
                pintarQuadranteCentro(eFluxo, 2, Color.RED);

            } else  if (i == 3) {

                pintarQuadrante(eFluxo, 1, Color.BLACK);
                pintarQuadrante(eFluxo, 2, Color.BLACK);
                pintarQuadrante(eFluxo, 3, Color.BLACK);
                pintarQuadrante(eFluxo, 4, Color.BLACK);
                pintarQuadranteCentro(eFluxo, 1, Color.RED);
                pintarQuadranteCentro(eFluxo, 2, Color.RED);
                pintarQuadranteCentro(eFluxo, 3, Color.RED);


            } else  if (i == 4) {
                pintarQuadrante(eFluxo, 1, Color.BLACK);
                pintarQuadrante(eFluxo, 2, Color.BLACK);
                pintarQuadrante(eFluxo, 3, Color.BLACK);
                pintarQuadrante(eFluxo, 4, Color.BLACK);
                pintarQuadranteCentro(eFluxo, 1, Color.RED);
                pintarQuadranteCentro(eFluxo, 2, Color.RED);
                pintarQuadranteCentro(eFluxo, 3, Color.RED);
                pintarQuadranteCentro(eFluxo, 4, Color.RED);


            }

            pintarQuadranteGeral(eFluxo, Color.BLACK);
            mLista.adicionar(eFluxo);
        }

        for (int i = 1; i <= 4; i++) {

            BufferedImage eFluxo = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
            pintarTudo(eFluxo, Color.WHITE);

            if (i == 1) {

                pintarQuadrante(eFluxo, 1, Color.BLACK);
                pintarQuadrante(eFluxo, 2, Color.BLACK);
                pintarQuadrante(eFluxo, 3, Color.BLACK);
                pintarQuadrante(eFluxo, 4, Color.BLACK);
                pintarQuadranteCentro(eFluxo, 1, Color.WHITE);
                pintarQuadranteCentro(eFluxo, 2, Color.RED);
                pintarQuadranteCentro(eFluxo, 3, Color.RED);
                pintarQuadranteCentro(eFluxo, 4, Color.RED);
            } else  if (i == 2) {

                pintarQuadrante(eFluxo, 1, Color.BLACK);
                pintarQuadrante(eFluxo, 2, Color.BLACK);
                pintarQuadrante(eFluxo, 3, Color.BLACK);
                pintarQuadrante(eFluxo, 4, Color.BLACK);
                pintarQuadranteCentro(eFluxo, 1, Color.WHITE);
                pintarQuadranteCentro(eFluxo, 2, Color.WHITE);
                pintarQuadranteCentro(eFluxo, 3, Color.RED);
                pintarQuadranteCentro(eFluxo, 4, Color.RED);
            } else  if (i == 3) {

                pintarQuadrante(eFluxo, 1, Color.BLACK);
                pintarQuadrante(eFluxo, 2, Color.BLACK);
                pintarQuadrante(eFluxo, 3, Color.BLACK);
                pintarQuadrante(eFluxo, 4, Color.BLACK);
                pintarQuadranteCentro(eFluxo, 1, Color.WHITE);
                pintarQuadranteCentro(eFluxo, 2, Color.WHITE);
                pintarQuadranteCentro(eFluxo, 3, Color.WHITE);
                pintarQuadranteCentro(eFluxo, 4, Color.RED);

            } else  if (i == 4) {
                pintarQuadrante(eFluxo, 1, Color.BLACK);
                pintarQuadrante(eFluxo, 2, Color.BLACK);
                pintarQuadrante(eFluxo, 3, Color.BLACK);
                pintarQuadrante(eFluxo, 4, Color.BLACK);
                pintarQuadranteCentro(eFluxo, 1, Color.WHITE);
                pintarQuadranteCentro(eFluxo, 2, Color.WHITE);
                pintarQuadranteCentro(eFluxo, 3, Color.WHITE);
                pintarQuadranteCentro(eFluxo, 4, Color.WHITE);


            }

            pintarQuadranteGeral(eFluxo, Color.BLACK);
            mLista.adicionar(eFluxo);
        }

        for (int i = 1; i <= 4; i++) {

            BufferedImage eFluxo = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
            pintarTudo(eFluxo, Color.WHITE);

            if (i == 1) {

                pintarQuadrante(eFluxo, 2, Color.BLACK);
                pintarQuadrante(eFluxo, 3, Color.BLACK);
                pintarQuadrante(eFluxo, 4, Color.BLACK);
                pintarQuadranteCentro(eFluxo, 2, Color.WHITE);
                pintarQuadranteCentro(eFluxo, 3, Color.WHITE);
                pintarQuadranteCentro(eFluxo, 4, Color.WHITE);
            } else  if (i == 2) {

                pintarQuadrante(eFluxo, 3, Color.BLACK);
                pintarQuadrante(eFluxo, 4, Color.BLACK);
                pintarQuadranteCentro(eFluxo, 3, Color.WHITE);
                pintarQuadranteCentro(eFluxo, 4, Color.WHITE);
            } else  if (i == 3) {

                pintarQuadrante(eFluxo, 4, Color.BLACK);
                pintarQuadranteCentro(eFluxo, 4, Color.WHITE);

            } else  if (i == 4) {



            }

            pintarQuadranteGeral(eFluxo, Color.BLACK);
            mLista.adicionar(eFluxo);
        }





        BufferedImage Q10 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        pintarTudo(Q10, Color.WHITE);

        pintarQuadranteGeral(Q10, Color.BLACK);

        mLista.adicionar(Q10);


        AN.criar(mLista, 200, eArquivo);

    }


    public void pintarTudo(BufferedImage IMG, Color eCor) {

        for (int aqy = 0; aqy < (IMG.getHeight()); aqy++) {

            for (int aqx = 0; aqx < (IMG.getWidth()); aqx++) {

                int pixel = eCor.getRGB();

                IMG.setRGB(aqx, aqy, pixel);
            }

        }

    }


    public void pintarQuadrante(BufferedImage IMG, int eQuadro, Color eCor) {

        int mais = 10;
        int menos = 10;

        if (eQuadro == 1) {
            for (int aqy = 0 + mais; aqy < (IMG.getHeight() / 2) - menos; aqy++) {

                for (int aqx = 0 + mais; aqx < (IMG.getWidth() / 2) - menos; aqx++) {

                    IMG.setRGB(aqx, aqy, eCor.getRGB());
                }

            }
        } else if (eQuadro == 2) {

            mais = 10;

            for (int aqy = 0 + mais; aqy < (IMG.getHeight() / 2) - menos; aqy++) {

                for (int aqx = (IMG.getWidth() / 2) + mais; aqx < (IMG.getWidth() - menos); aqx++) {

                    IMG.setRGB(aqx, aqy, eCor.getRGB());
                }

            }
        } else if (eQuadro == 3) {

            mais = 10;

            for (int aqy = (IMG.getHeight() / 2) + mais; aqy < (IMG.getHeight() - menos); aqy++) {

                for (int aqx = (IMG.getWidth() / 2) + mais; aqx < (IMG.getWidth() - menos); aqx++) {


                    IMG.setRGB(aqx, aqy, eCor.getRGB());
                }

            }
        } else if (eQuadro == 4) {

            mais = 10;

            for (int aqy = (IMG.getHeight() / 2) + mais; aqy < (IMG.getHeight() - menos); aqy++) {

                for (int aqx = 0 + mais; aqx < (IMG.getWidth() / 2) - menos; aqx++) {

                    IMG.setRGB(aqx, aqy, eCor.getRGB());
                }

            }
        }


    }

    public void pintarQuadranteCentro(BufferedImage IMG, int eQuadro, Color eCor) {

        int mais = 20;
        int menos = 20;

        if (eQuadro == 1) {
            for (int aqy = 0 + mais; aqy < (IMG.getHeight() / 2) - menos; aqy++) {

                for (int aqx = 0 + mais; aqx < (IMG.getWidth() / 2) - menos; aqx++) {

                    IMG.setRGB(aqx, aqy, eCor.getRGB());
                }

            }
        } else if (eQuadro == 2) {


            for (int aqy = 0 + mais; aqy < (IMG.getHeight() / 2) - menos; aqy++) {

                for (int aqx = (IMG.getWidth() / 2) + mais; aqx < (IMG.getWidth() - menos); aqx++) {

                    IMG.setRGB(aqx, aqy, eCor.getRGB());
                }

            }
        } else if (eQuadro == 3) {


            for (int aqy = (IMG.getHeight() / 2) + mais; aqy < (IMG.getHeight() - menos); aqy++) {

                for (int aqx = (IMG.getWidth() / 2) + mais; aqx < (IMG.getWidth() - menos); aqx++) {


                    IMG.setRGB(aqx, aqy, eCor.getRGB());
                }

            }
        } else if (eQuadro == 4) {


            for (int aqy = (IMG.getHeight() / 2) + mais; aqy < (IMG.getHeight() - menos); aqy++) {

                for (int aqx = 0 + mais; aqx < (IMG.getWidth() / 2) - menos; aqx++) {

                    IMG.setRGB(aqx, aqy, eCor.getRGB());
                }

            }
        }


    }


    public void pintarQuadranteGeral(BufferedImage IMG, Color eCor) {


        for (int aqy = 0; aqy < IMG.getHeight(); aqy++) {

            for (int aqx = (IMG.getWidth() / 2) - 5; aqx < (IMG.getWidth() / 2) + 5; aqx++) {

                int pixel = eCor.getRGB();

                IMG.setRGB(aqx, aqy, pixel);
            }

        }

        for (int aqy = (IMG.getHeight() / 2) - 5; aqy < (IMG.getHeight() / 2) + 5; aqy++) {

            for (int aqx = 0; aqx < (IMG.getWidth()); aqx++) {

                int pixel = eCor.getRGB();

                IMG.setRGB(aqx, aqy, pixel);
            }

        }


    }


}

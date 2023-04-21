package apps.app_letrum.Maker;

import libs.luan.ArquivoTexto;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FonteGerador {

    // private final String COR_FUNDO = "COR_FUNDO";
    private final String COR_FUNDO = "0";

    private int mUNO = 0;

    private int mPRIM3 = 0;
    private int mPRIM4 = 0;

    private int mTRINCA = 0;
    private int mQUARTER = 0;
    private int mQUINTOX = 0;
    private int mSEXTAND = 0;

    private int mOMEGA3 = 0;
    private int mOMEGA4 = 0;
    private int mSEQUENCIAL = 0;
    private int mALL = 0;

    public void gerarFonteImagemEJava(String eArquivo, String eArquivoJAVA, int eTamanho) {

        ArrayList<String> mLetras = new ArrayList<String>();

        BufferedImage mImagem = gerarFonteJavaImagem(mLetras, Color.BLACK, eTamanho);

        try {
            ImageIO.write(mImagem, "png", new File(eArquivo));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        gerarJava(eTamanho, mImagem, mLetras, eArquivoJAVA);

    }

    public BufferedImage gerarFonteJavaImagem(ArrayList<String> mLetras, Color eCor, int eTamanho) {

        String sequencia = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789-<>.,:;/\\+-*=()[]{}!@#$%çÇ";
        sequencia += "áàâãäéèêẽëíìîĩïóòôõöúùûũü";
        sequencia += "ÁÀÂÃÄÉÈÊẼËÍÌÎĨÏÓÒÔÕÖÚÙÛŨÜ";

        int eLinhas = 1;

        int tam = sequencia.length();
        while (tam > 10) {
            eLinhas += 1;
            tam -= 10;
        }

        int mLargura = (12 * (eTamanho + (eTamanho / 2)));
        int mAltura = (eTamanho + 20) * eLinhas;

        BufferedImage mImagem = new BufferedImage(mLargura, mAltura, BufferedImage.TYPE_INT_ARGB);

        Graphics g = mImagem.getGraphics();

        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, mLargura, mAltura);

        int i = 0;
        int e = 0;
        int o = sequencia.length();

        int x = eTamanho;
        int y = eTamanho + 5;

        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, eTamanho));


        while (i < o) {
            String l = String.valueOf(sequencia.charAt(i));

            String iString = String.valueOf(i);
            while (iString.length() < 2) {
                iString = "0" + iString;
            }

            g.setColor(eCor);
            g.drawString(l, x, y);

            int w = g.getFontMetrics().stringWidth(l);

            g.setColor(Color.RED);

            int x1 = x - 2;
            int x2 = (x) + w;
            int y1 = y - eTamanho - 2;
            int y2 = y + (eTamanho / 2) - (eTamanho / 4);


            // g.fillRect(x1, y1, 2, 2);
            //  g.fillRect(x2, y1, 2, 2);
            //  g.fillRect(x1, y2, 2, 2);
            // g.fillRect(x2, y2, 2, 2);

            if (l.contentEquals("\\")) {
                //   mLetras.add("mLetras.add( new Letra (\"\\" + l + "\"," + x1 + "," + y1 + "," + x2 + "," + y2 + "));");

                mLetras.add("private final Letra LETRA_" + iString + " = new Letra (\"\\" + l + "\"," + x1 + "," + y1 + "," + x2 + "," + y2 + ");");

            } else {
                //    mLetras.add("mLetras.add( new Letra (\"" + l + "\"," + x1 + "," + y1 + "," + x2 + "," + y2 + "));");

                //  new Letra("a", 28, 3, 49, 43);
                mLetras.add("private final Letra LETRA_" + iString + " = new Letra (\"" + l + "\"," + x1 + "," + y1 + "," + x2 + "," + y2 + ");");

            }

            x += eTamanho + (eTamanho / 2);

            i += 1;
            e += 1;
            if (e > 10) {
                e = 0;
                y += (eTamanho + 20);
                x = eTamanho;
            }


        }
        return mImagem;
    }

    public void gerarFonteJava(String eArquivoJAVA, int eTamanho) {

        ArrayList<String> mLetras = new ArrayList<String>();

        BufferedImage mImagem = gerarFonteJavaImagem(mLetras, Color.BLACK, eTamanho);

        gerarJava(eTamanho, mImagem, mLetras, eArquivoJAVA);

    }

    public void gerarFonteJavaColorida(String eArquivoJAVA, Color eCor, int eTamanho) {

        ArrayList<String> mLetras = new ArrayList<String>();

        BufferedImage mImagem = gerarFonteJavaImagem(mLetras, eCor, eTamanho);

        gerarJava(eTamanho, mImagem, mLetras, eArquivoJAVA);

    }


    private void gerarJava(int eTamanho, BufferedImage mImagem, ArrayList<String> mLetras, String eArquivoJAVA) {

        String eDocumentoJAVA = "";


        FonteJavaGerador eFonteJavaGerador = new FonteJavaGerador();

        eDocumentoJAVA += eFonteJavaGerador.getImports();

        eDocumentoJAVA += "\npublic class G" + eTamanho + " implements  Fonte {";
        eDocumentoJAVA += "\n";
        eDocumentoJAVA += "\n";

        eDocumentoJAVA += eFonteJavaGerador.getMembros(mImagem, eTamanho);

        for (String eLetra : mLetras) {
            eDocumentoJAVA += "\n\t" + eLetra;
        }
        eDocumentoJAVA += "\n";
        eDocumentoJAVA += "\n";


        eDocumentoJAVA += eFonteJavaGerador.getConstrutor(eTamanho);

        eDocumentoJAVA += eFonteJavaGerador.getFuncoes();

        eDocumentoJAVA += eFonteJavaGerador.getEscreva(mLetras.size());

        eDocumentoJAVA += "\nprivate void colocar(int x, int y, int q) {";
        eDocumentoJAVA += "\n for (int xi = x; xi < (x + q); xi++) {";
        eDocumentoJAVA += "\n    mImagem.setRGB(xi, y, COR_FONTE);";
        eDocumentoJAVA += "\n  }";
        eDocumentoJAVA += "\n  }";

       // eDocumentoJAVA += "\nprivate void init_letras() {";

       // for (String eLetra : mLetras) {
        //    eDocumentoJAVA += "\n\t" + eLetra;
      //  }
    //    eDocumentoJAVA += "\n}";

        ArrayList<String> m10 = new ArrayList<String>();

        String sPixel = "COR_FONTE";

        for (int yi = 0; yi < mImagem.getHeight(); yi++) {

            boolean mAntesEra = false;
            int mContando = 0;
            int mInicioX = 0;

            ArrayList<String> mC2 = new ArrayList<String>();
            ArrayList<String> mC3 = new ArrayList<String>();
            ArrayList<String> mC4 = new ArrayList<String>();
            ArrayList<String> mC5 = new ArrayList<String>();
            ArrayList<String> mC6 = new ArrayList<String>();
            ArrayList<String> mC7 = new ArrayList<String>();
            ArrayList<String> mC8 = new ArrayList<String>();
            ArrayList<String> mC9 = new ArrayList<String>();

            for (int xi = 0; xi < mImagem.getWidth(); xi++) {
                int pixel = mImagem.getRGB(xi, yi);

                if (pixel == -16777216) {

                    if (!mAntesEra) {
                        mAntesEra = true;
                        mInicioX = xi;
                        mContando = 1;
                    } else {
                        mContando += 1;
                    }
                } else {
                    if (mAntesEra) {
                        mAntesEra = false;
                     //   if (mContando == 2) {
                     //       mC2.add(String.valueOf(mInicioX));
                      //  } else if (mContando == 3) {
                     //       mC3.add(String.valueOf(mInicioX));
                     //   } else if (mContando == 4) {
                      //      mC4.add(String.valueOf(mInicioX));
                     //   } else if (mContando == 5) {
                     //       mC5.add(String.valueOf(mInicioX));
                     //   } else if (mContando == 6) {
                     //       mC6.add(String.valueOf(mInicioX));
                     //   } else if (mContando == 7) {
                     //       mC7.add(String.valueOf(mInicioX));
                     //   } else if (mContando == 8) {
                     //       mC8.add(String.valueOf(mInicioX));
                    //    } else if (mContando == 9) {
                     //       mC9.add(String.valueOf(mInicioX));
                     //   } else {
                            m10.add("mMontagem.mapear(" + mInicioX + "," + yi + "," + mContando + ");");
                       // }
                        mInicioX = 0;
                        mContando = 0;
                    }
                }

            }


          //  organizar("2", yi, mC2, m10);
          //  organizar("3", yi, mC3, m10);
          //  organizar("4", yi, mC4, m10);
          //  organizar("5", yi, mC5, m10);
         //   organizar("6", yi, mC6, m10);
          //  organizar("7", yi, mC7, m10);
        //    organizar("8", yi, mC8, m10);
          //  organizar("9", yi, mC9, m10);


        }


        eDocumentoJAVA += "\n\tprivate void init_data () {";

        ArrayList<String> mAreas = dividir(m10);

        int nAreaID = 0;

        for (String eChamando : mAreas) {
            eDocumentoJAVA += "\n\t area_" + nAreaID + "();";
            nAreaID += 1;
        }

        eDocumentoJAVA += "\n\t}";
        nAreaID = 0;

        for (String eChamando : mAreas) {
            eDocumentoJAVA += "\n\t private void area_" + nAreaID + "(){";
            eDocumentoJAVA += eChamando;
            eDocumentoJAVA += "\n\t}";
            nAreaID += 1;
        }


        eDocumentoJAVA += "\n}";


        ArquivoTexto.arquivo_escrever(eArquivoJAVA, eDocumentoJAVA);

    }

    public ArrayList<String> dividir(ArrayList<String> linhas) {
        ArrayList<String> mAreas = new ArrayList<String>();

        String eLinhaCorrente = "";

        int i = 0;
        int maximo = 50;
        for (String elinha : linhas) {

            if (i == maximo) {
                mAreas.add(eLinhaCorrente);
                eLinhaCorrente = "";
                i = 0;
            }
            eLinhaCorrente += "\n\t\t" + elinha;
            i += 1;
        }

        mAreas.add(eLinhaCorrente);

        return mAreas;
    }


    public void organizar(String GRUPO, int yi, ArrayList<String> mSubGrupo, ArrayList<String> eGrande) {

        ArrayList<String> subC2 = new ArrayList<String>();

        for (String ePosX : mSubGrupo) {
            if (subC2.size() == 10) {

                String eArgumentos = "";
                for (String a : subC2) {
                    eArgumentos += "," + a;
                }

                eGrande.add("mMontagem.T10_" + GRUPO + "(" + yi + eArgumentos + ");");

                subC2.clear();
            } else {
                subC2.add(ePosX);
            }
        }

        organizar5(GRUPO, yi, subC2, eGrande);

        organizar4(GRUPO, yi, subC2, eGrande);

        organizar3(GRUPO, yi, subC2, eGrande);

        organizar2(GRUPO, yi, subC2, eGrande);

        for (String a : subC2) {
            eGrande.add("mMontagem.mapear(" + a + "," + yi + "," + GRUPO + ");");
        }

    }

    public void organizar5(String GRUPO, int yi, ArrayList<String> mSubGrupo, ArrayList<String> eGrande) {

        if (mSubGrupo.size() >= 5) {

            String eArgumentos = "";

            int i5 = 0;
            ArrayList<String> s5 = new ArrayList<String>();

            for (String a : mSubGrupo) {
                if (i5 < 5) {
                    eArgumentos += "," + a;
                } else {
                    s5.add(a);
                }
                i5 += 1;
            }

            eGrande.add("mMontagem.T05_" + GRUPO + "(" + yi + eArgumentos + ");");
            mSubGrupo.clear();
            for (String as : s5) {
                mSubGrupo.add(as);
            }
        }

    }

    public void organizar4(String GRUPO, int yi, ArrayList<String> mSubGrupo, ArrayList<String> eGrande) {

        if (mSubGrupo.size() >= 4) {

            String eArgumentos = "";

            int i5 = 0;
            ArrayList<String> s5 = new ArrayList<String>();

            for (String a : mSubGrupo) {
                if (i5 < 4) {
                    eArgumentos += "," + a;
                } else {
                    s5.add(a);
                }
                i5 += 1;
            }

            eGrande.add("mMontagem.T04_" + GRUPO + "(" + yi + eArgumentos + ");");
            mSubGrupo.clear();
            for (String as : s5) {
                mSubGrupo.add(as);
            }
        }

    }

    public void organizar3(String GRUPO, int yi, ArrayList<String> mSubGrupo, ArrayList<String> eGrande) {

        if (mSubGrupo.size() >= 3) {

            String eArgumentos = "";

            int i5 = 0;
            ArrayList<String> s5 = new ArrayList<String>();

            for (String a : mSubGrupo) {
                if (i5 < 3) {
                    eArgumentos += "," + a;
                } else {
                    s5.add(a);
                }
                i5 += 1;
            }

            eGrande.add("mMontagem.T03_" + GRUPO + "(" + yi + eArgumentos + ");");
            mSubGrupo.clear();
            for (String as : s5) {
                mSubGrupo.add(as);
            }
        }

    }

    public void organizar2(String GRUPO, int yi, ArrayList<String> mSubGrupo, ArrayList<String> eGrande) {

        if (mSubGrupo.size() >= 2) {

            String eArgumentos = "";

            int i5 = 0;
            ArrayList<String> s5 = new ArrayList<String>();

            for (String a : mSubGrupo) {
                if (i5 < 2) {
                    eArgumentos += "," + a;
                } else {
                    s5.add(a);
                }
                i5 += 1;
            }

            eGrande.add("mMontagem.T02_" + GRUPO + "(" + yi + eArgumentos + ");");
            mSubGrupo.clear();
            for (String as : s5) {
                mSubGrupo.add(as);
            }
        }

    }

}

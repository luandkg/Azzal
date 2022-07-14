package apps.appLetrum.Maker;

import java.awt.image.BufferedImage;

public class FonteJavaGerador {

    public String getImports() {
        String eDocumentoJAVA = "";

        eDocumentoJAVA += "\npackage Letrum.AutoGerador;";

        eDocumentoJAVA += "\nimport Azzal.Renderizador;";
        eDocumentoJAVA += "\nimport java.awt.*;";
        eDocumentoJAVA += "\nimport java.awt.image.BufferedImage;";
        eDocumentoJAVA += "\nimport java.util.ArrayList;";
        eDocumentoJAVA += "\nimport Azzal.Utils.Cor;";

        eDocumentoJAVA += "\n";
        eDocumentoJAVA += "\n";
        return eDocumentoJAVA;

    }

    public String getMembros(BufferedImage mImagem, int eTamanho) {
        String eDocumentoJAVA = "";

        eDocumentoJAVA += "\n";
        eDocumentoJAVA += "\n\t public final int LARGURA = " + mImagem.getWidth() + ";";
        eDocumentoJAVA += "\n\t public final int ALTURA = " + mImagem.getHeight() + ";";
        eDocumentoJAVA += "\n\t public final int FONTE = " + eTamanho + ";";
        eDocumentoJAVA += "\n\t private int COR_FONTE = new Color(0, 0, 0).getRGB();";
        eDocumentoJAVA += "\n\t private int COR_FUNDO = 0;";


        eDocumentoJAVA += "\n";
        eDocumentoJAVA += "\n";

        eDocumentoJAVA += "\n\t private BufferedImage mImagem;";
        eDocumentoJAVA += "\n\t private Renderizador mRenderizador;";
        eDocumentoJAVA += "\n\t private ArrayList<Letra> mLetras;";
        eDocumentoJAVA += "\n\t private IMontadora mMontagem;";

        return eDocumentoJAVA;
    }


    public String getConstrutor(int eTamanho) {
        String eDocumentoJAVA = "";

        eDocumentoJAVA += "\n\t public G" + eTamanho + "() {";
        eDocumentoJAVA += "\n\t\t mImagem = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_ARGB);";
        eDocumentoJAVA += "\n\t\t mLetras = new ArrayList<Letra>();";
        eDocumentoJAVA += "\n\t\t mMontagem = new IMontadora(mImagem,COR_FONTE);";
        //  eDocumentoJAVA += "\n\t\t init_letras();";
        eDocumentoJAVA += "\n\t\t init_data();";
        eDocumentoJAVA += "\n\t }";

        eDocumentoJAVA += "\n\t   public G" + eTamanho + "(Cor eCor) {";
        eDocumentoJAVA += "\n\t     mImagem = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_ARGB);";
        eDocumentoJAVA += "\n\t    mLetras = new ArrayList<Letra>();";
        eDocumentoJAVA += "\n\t    COR_FONTE = new Color(eCor.getRed(), eCor.getGreen(), eCor.getBlue()).getRGB();";
        eDocumentoJAVA += "\n\t      mMontagem = new IMontadora(mImagem, COR_FONTE);";
        //   eDocumentoJAVA += "\n\t      init_letras();";
        eDocumentoJAVA += "\n\t      init_data();";
        eDocumentoJAVA += "\n\t   }";

        return eDocumentoJAVA;
    }

    public String getFuncoes() {

        String documento = "";
        documento += "\n\n";


        documento += "\n\n";
        documento += "\n\tpublic void setRenderizador(Renderizador eRenderizador) {";
        documento += "\n\t\tmRenderizador = eRenderizador;";
        documento += "\n\t}";


        return documento;
    }

    public String getEscreva1() {

        String documento = "";
        documento += "\n\n";

        documento += "\n\tpublic void escreva(int x, int y, String frase) {";
        documento += "\n\t\tint i = 0;";
        documento += "\n\t\tint o = frase.length();";

        documento += "\n\t\twhile (i < o) {";
        documento += "\n\t\tString l = String.valueOf(frase.charAt(i));";

        documento += "\n\t\tboolean enc = false;";

        documento += "\n\t\tfor (Letra eLetra : mLetras) {";
        documento += "\n\t\tif (l.contentEquals(\" \")) {";
        documento += "\n\t\tx += 20;";
        documento += "\n\t\tenc = true;";
        documento += "\n\t\tbreak;";
        documento += "\n\t\t} else if (l.contentEquals(\"\t\")) {";
        documento += "\n\t\tx += 30;";
        documento += "\n\t\tenc = true;";
        documento += "\n\t\t break;";
        documento += "\n\t\t  } else if (eLetra.getL().contentEquals(l)) {";

        documento += "\n\t\tint ox = x;";
        documento += "\n\t\tint yc = y;";

        documento += "\n\t\tfor (int y1 = eLetra.getY1(); y1 < eLetra.getY2(); y1++) {";
        documento += "\n \t\tint xc = ox;";

        documento += "\n\t\tfor (int xi = eLetra.getX1(); xi < eLetra.getX2(); xi++) {";

        documento += "\n\t\tint v = mImagem.getRGB(xi, y1);";
        documento += "\n\t\tif (v!=0){";
        documento += "\n\t\tmRenderizador.drawPixelBruto(xc, yc, v);";
        documento += "\n\t\t}";

        documento += "\n \t\t    xc += 1;";
        documento += "\n \t\t }";
        documento += "\n \t\t yc += 1;";
        documento += "\n\t\t }";

        documento += "\n \t\t x += (eLetra.getX2() - eLetra.getX1());";

        documento += "\n\t\t  enc = true;";
        documento += "\n\t\t break;";
        documento += "\n\t\t }";
        documento += "\n\t\t  }";

        documento += "\n\t\t  i += 1;";
        documento += "\n \t\t  }";
        documento += "\n\t }";



        documento += "\n \tpublic int getLarguraDe(String frase) {";
        documento += "\n \t\t     int i = 0;";
        documento += "\n \t\t    int o = frase.length();";
        documento += "\n \t\t     int x = 0;";

        documento += "\n\t\t   while (i < o) {";
        documento += "\n      String l = String.valueOf(frase.charAt(i));";
        documento += "\n       boolean enc = false;";
        documento += "\n       for (Letra eLetra : mLetras) {";
        documento += "\n      if (l.contentEquals(\" \")) {";
        documento += "\n       x += 10;";
        documento += "\n       enc = true;";
        documento += "\n    break;";
        documento += "\n } else if (l.contentEquals(\" \")) {";
        documento += "\n      x += 20;";
        documento += "\n      enc = true;";
        documento += "\n      break;";
        documento += "\n  } else if (eLetra.getL().contentEquals(l)) {";
        documento += "\n      x += (eLetra.getX2() - eLetra.getX1());";
        documento += "\n      enc = true;";
        documento += "\n      break;";
        documento += "\n  }";
        documento += "\n }";
        documento += "\n  i += 1;";
        documento += "\n }";
        documento += "\n   return x;";
        documento += "\n }";

        documento += "\n \t  public int getAltura() {";
        documento += "\n \t\t     return FONTE;";
        documento += "\n \t}";

        documento += "\n public void escreveLinha(int y, int x1, int x2, String eTexto1, String eTexto2) {";
        documento += "\n     escreva(x1, y, eTexto1);";
        documento += "\n    escreva(x2, y, eTexto2);";
        documento += "\n  }";


        return documento;
    }

    public String getEscreva(int eQuantos) {

        String documento = "";
        documento += "\n\n";

        documento += "\n \t  public int getAltura() {";
        documento += "\n \t\t     return FONTE;";
        documento += "\n \t}";

        documento += "\n public void escreveLinha(int y, int x1, int x2, String eTexto1, String eTexto2) {";
        documento += "\n     escreva(x1, y, eTexto1);";
        documento += "\n    escreva(x2, y, eTexto2);";
        documento += "\n  }";

        documento += "\n\n";
        documento += "\n\t public void escreva(int x, int y, String frase) {";
        documento += "\n\t  int i = 0;";
        documento += "\n\t  int o = frase.length();";
        documento += "\n\t   while (i < o) {";
        documento += "\n\t     String l = String.valueOf(frase.charAt(i));";
        documento += "\n\t    if (l.contentEquals(\" \")) {";
        documento += "\n\t        x += 20;";
        documento += "\n\t   } else if (l.contentEquals(\"\\t\")) {";
        documento += "\n\t        x += 30;";
        documento += "\n\t    } else {";
        documento += "\n\t       boolean enc = false;";
        documento += "\n\t       Letra letraSelecionada = null;";


        if (eQuantos > 0) {

            documento += "\n\t     if (LETRA_00.igual(l)) {";
            documento += "\n\t         enc = true;";
            documento += "\n\t            letraSelecionada = LETRA_00;";

            for (int i = 1; i < eQuantos; i++) {
                if (i < 10) {
                    documento += "\n\t    } else if (LETRA_0" + i + ".igual(l)) {";
                    documento += "\n\t         enc = true;";
                    documento += "\n\t            letraSelecionada = LETRA_0" + i + ";";
                } else {
                    documento += "\n\t    } else if (LETRA_" + i + ".igual(l)) {";
                    documento += "\n\t         enc = true;";
                    documento += "\n\t            letraSelecionada = LETRA_" + i + ";";
                }
            }

            documento += "\n\t         }";

        }

        documento += "\n\t       if (enc) {";
        documento += "\n\t            int ox = x;";
        documento += "\n\t             int yc = y;";
        documento += "\n\t             for (int y1 = letraSelecionada.getY1(); y1 < letraSelecionada.getY2(); y1++) {";
        documento += "\n\t                 int xc = ox;";
        documento += "\n\t                for (int xi = letraSelecionada.getX1(); xi < letraSelecionada.getX2(); xi++) {";
        documento += "\n\t                    int v = mImagem.getRGB(xi, y1);";
        documento += "\n\t                    if (v != 0) {";
        documento += "\n\t                        mRenderizador.drawPixelBruto(xc, yc, v);";
        documento += "\n\t                      }";
        documento += "\n\t                      xc += 1;";
        documento += "\n\t                  }";
        documento += "\n\t                  yc += 1;";
        documento += "\n\t              }";
        documento += "\n\t               x += (letraSelecionada.getX2() - letraSelecionada.getX1());";
        documento += "\n\t          }";
        documento += "\n\t      }";
        documento += "\n\t      i += 1;";
        documento += "\n\t    }";
        documento += "\n\t   }";

        documento += "\n\n";
        documento += "\n\t public int getLarguraDe( String frase) {";
        documento += "\n\t  int x = 0;";
        documento += "\n\t  int i = 0;";
        documento += "\n\t  int o = frase.length();";
        documento += "\n\t   while (i < o) {";
        documento += "\n\t     String l = String.valueOf(frase.charAt(i));";
        documento += "\n\t    if (l.contentEquals(\" \")) {";
        documento += "\n\t        x += 20;";
        documento += "\n\t   } else if (l.contentEquals(\"\\t\")) {";
        documento += "\n\t        x += 30;";
        documento += "\n\t    } else {";
        documento += "\n\t       boolean enc = false;";
        documento += "\n\t       Letra letraSelecionada = null;";


        if (eQuantos > 0) {

            documento += "\n\t     if (LETRA_00.igual(l)) {";
            documento += "\n\t         enc = true;";
            documento += "\n\t            letraSelecionada = LETRA_00;";

            for (int i = 1; i < eQuantos; i++) {
                if (i < 10) {
                    documento += "\n\t    } else if (LETRA_0" + i + ".igual(l)) {";
                    documento += "\n\t         enc = true;";
                    documento += "\n\t            letraSelecionada = LETRA_0" + i + ";";
                } else {
                    documento += "\n\t    } else if (LETRA_" + i + ".igual(l)) {";
                    documento += "\n\t         enc = true;";
                    documento += "\n\t            letraSelecionada = LETRA_" + i + ";";
                }
            }

            documento += "\n\t         }";

        }

        documento += "\n\t       if (enc) {";
        documento += "\n\t               x += (letraSelecionada.getX2() - letraSelecionada.getX1());";
        documento += "\n\t          }";
        documento += "\n\t      }";
        documento += "\n\t      i += 1;";
        documento += "\n\t    }";
        documento += "\n\t    return x;";
        documento += "\n\t   }";

        return documento;
    }

    public String getColocadores() {
        String eDocumentoJAVA = "";

        for (int i = 1; i <= 10; i++) {
            eDocumentoJAVA += "\n\t  private void T10_" + i + "(int y,int a1,int a2,int a3,int a4,int a5,int a6,int a7,int a8,int a9,int a10){";
            eDocumentoJAVA += "\n\t    colocar(a1, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a2, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a3, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a4, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a5, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a6, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a7, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a8, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a9, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a10, y, " + i + ");";
            eDocumentoJAVA += "\n\t  }";
        }

        for (int i = 1; i <= 10; i++) {
            eDocumentoJAVA += "\n\t  private void T05_" + i + "(int y,int a1,int a2,int a3,int a4,int a5){";
            eDocumentoJAVA += "\n\t    colocar(a1, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a2, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a3, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a4, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a5, y, " + i + ");";
            eDocumentoJAVA += "\n\t  }";
        }

        for (int i = 1; i <= 10; i++) {
            eDocumentoJAVA += "\n\t  private void T04_" + i + "(int y,int a1,int a2,int a3,int a4){";
            eDocumentoJAVA += "\n\t    colocar(a1, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a2, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a3, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a4, y, " + i + ");";
            eDocumentoJAVA += "\n\t  }";
        }

        for (int i = 1; i <= 10; i++) {
            eDocumentoJAVA += "\n\t  private void T03_" + i + "(int y,int a1,int a2,int a3){";
            eDocumentoJAVA += "\n\t    colocar(a1, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a2, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a3, y, " + i + ");";
            eDocumentoJAVA += "\n\t  }";
        }

        for (int i = 1; i <= 10; i++) {
            eDocumentoJAVA += "\n\t  private void T02_" + i + "(int y,int a1,int a2){";
            eDocumentoJAVA += "\n\t    colocar(a1, y, " + i + ");";
            eDocumentoJAVA += "\n\t    colocar(a2, y, " + i + ");";
            eDocumentoJAVA += "\n\t  }";
        }

        return eDocumentoJAVA;
    }
}

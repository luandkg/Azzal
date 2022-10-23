package libs.arquivos;

import azzal.utilitarios.Cor;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Int6;
import libs.arquivos.binario.Int8;
import libs.arquivos.binario.Inteiro;
import libs.Imaginador.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IM {

    private static int IMAGEM_IM1 = 38;
    private static int IMAGEM_IM2 = 42;
    private static int IMAGEM_VERSAO_1 = 100;

    private static int IMAGEM_ALFA_COM = 31;
    private static int IMAGEM_ALFA_SEM = 48;

    private static boolean DEBUG = false;

    // 11 - COR INDEXADA
    // 10 - REPETIR
    // 01 - NOVA COR
    // 00 - FECHAR

    public static void salvar(BufferedImage eImagem, String eArquivo) {


        TX eTX = new TX();
        System.out.println("I :: " + eTX.getIndice("I"));
        System.out.println("M :: " + eTX.getIndice("M"));
        System.out.println("C :: " + eTX.getIndice("C"));
        System.out.println("S :: " + eTX.getIndice("S"));


        System.out.println("Imagem IM - Criando");

        File Arq = new File(eArquivo);
        if (Arq.exists()) {
            Arq.delete();
        }


        int largura = eImagem.getWidth();
        int altura = eImagem.getHeight();

        int todos = 0;
        int fixados = 0;
        int repetir = 0;

        Int8 int8 = new Int8(0);
        Int6 int6 = new Int6(0);

        int primeiro = 10;


        try {

            Arquivador arquivador = new Arquivador(eArquivo);


            arquivador.writeByte((byte) IMAGEM_IM1);
            arquivador.writeByte((byte) IMAGEM_IM2);

            arquivador.writeByte((byte) IMAGEM_VERSAO_1);

            arquivador.writeInt(largura);
            arquivador.writeInt(altura);

            int alfa_primeiro = new Color(eImagem.getRGB(0, 0)).getAlpha();
            boolean tem_alfa = false;

            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {

                    int alfa_corrente = new Color(eImagem.getRGB(x, y)).getAlpha();

                    if (alfa_primeiro != alfa_corrente) {
                        tem_alfa = true;
                        break;
                    }

                }
            }

            if (tem_alfa) {
                arquivador.writeByte((byte) IMAGEM_ALFA_SEM);
                arquivador.writeByte((byte) alfa_primeiro);
            } else {
                arquivador.writeByte((byte) IMAGEM_ALFA_COM);
                arquivador.writeByte((byte) 0);
            }

            System.out.println("Tem Alfa :: " + tem_alfa);

            int matriz[] = new int[64];

            for (int i = 0; i < 64; i++) {
                matriz[i] = 0;
            }

            int pixel_corrente = 0;
            int repetindo = 0;


            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {

                    int pixel = eImagem.getRGB(x, y);

                    todos += 1;

                    if (pixel_corrente == pixel) {

                        repetir += 1;

                        repetindo += 1;

                        if (repetindo == 63) {

                            int8.zerar();
                            int8.set2Bits(0, 1, 0);

                            int6.set(repetindo);
                            int8.copiarComecando(2, int6.getValores(), 6);

                            arquivador.writeByte((byte) int8.getInt());

                            if (primeiro > 0) {
                                primeiro -= 1;
                                //   System.out.println("Repetindo (" + repetindo + ") = " + pixel_corrente + " em " + INT_8BITS.getInt());
                            }

                            repetindo = 0;
                        }

                    } else {

                        if (repetindo > 0) {
                            int8.zerar();
                            int8.set2Bits(0, 1, 0);

                            int6.set(repetindo);
                            int8.copiarComecando(2, int6.getValores(), 6);

                            arquivador.writeByte((byte) int8.getInt());

                            if (primeiro > 0) {
                                primeiro -= 1;
                                //    System.out.println("Repetindo (" + repetindo + ") = " + pixel_corrente + " em " + INT_8BITS.getInt());
                            }

                            repetindo = 0;
                        }


                        Color cPixel = new Color(pixel);

                        int indice = existe(matriz, pixel);
                        pixel_corrente = cPixel.getRGB();


                        int pos = ((cPixel.getAlpha()) + (cPixel.getRed()) + (cPixel.getGreen()) + (cPixel.getBlue())) % 64;

                        if (indice >= 0) {

                            int6.zerar();
                            int6.set(pos);

                            int8.zerar();
                            int8.set2Bits(0, 1, 1);

                            int8.copiarComecando(2, int6.getValores(), 6);

                            arquivador.writeByte((byte) int8.getInt());

                            if (primeiro > 0) {
                                primeiro -= 1;
                                //    System.out.println("Pixel Index (" + pos + ") = " + INT_8BITS.getInt());
                            }
                        } else {

                            matriz[pos] = pixel;

                            int6.zerar();
                            int6.set(pos);


                            int8.zerar();
                            int8.set2Bits(0, 0, 1);


                            int8.copiarComecando(2, int6.getValores(), 6);

                            arquivador.writeByte((byte) int8.getInt());

                            if (!tem_alfa) {
                                arquivador.writeByte((byte) cPixel.getAlpha());
                            }

                            arquivador.writeByte((byte) cPixel.getRed());
                            arquivador.writeByte((byte) cPixel.getGreen());
                            arquivador.writeByte((byte) cPixel.getBlue());


                            if (primeiro > 0) {
                                primeiro -= 1;
                                //   System.out.println("Novo Pixel (" + pos + ") = " + pixel + " em " + INT_8BITS.getInt() + " -->> " + INT_8BITS.get());
                            }
                        }


                        fixados += 1;

                    }


                }
            }

            int8.zerar();
            arquivador.writeByte((byte) int8.getInt());


            arquivador.fechar();

            System.out.println("Imagem IM - Terminada");

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("TODOS     :: " + todos);
        System.out.println("FIXADOS   :: " + fixados);
        System.out.println("REPITIDOS :: " + repetir);

    }

    public static void salvar_imagem32(ImagemI32 imagem, String eArquivo) {
        TX eTX = new TX();
        System.out.println("I :: " + eTX.getIndice("I"));
        System.out.println("M :: " + eTX.getIndice("M"));
        System.out.println("C :: " + eTX.getIndice("C"));
        System.out.println("S :: " + eTX.getIndice("S"));
        System.out.println("Imagem IM - Criando");
        File Arq = new File(eArquivo);
        if (Arq.exists()) {
            Arq.delete();
        }

        int largura = imagem.getLargura();
        int altura = imagem.getAltura();
        int todos = 0;
        int fixados = 0;
        int repetir = 0;
        Int8 int8 = new Int8(0);
        Int6 int6 = new Int6(0);
        int primeiro = 10;

        try {
            Arquivador arquivador = new Arquivador(eArquivo);
            arquivador.writeByte((byte) IMAGEM_IM1);
            arquivador.writeByte((byte) IMAGEM_IM2);
            arquivador.writeByte((byte) IMAGEM_VERSAO_1);
            arquivador.writeInt(largura);
            arquivador.writeInt(altura);
            int alfa_primeiro = imagem.getRGB(0, 0).getAlpha();
            boolean tem_alfa = false;

            int pixel_corrente;
            int repetindo;
            for (int y = 0; y < altura; ++y) {
                for (pixel_corrente = 0; pixel_corrente < largura; ++pixel_corrente) {
                    repetindo = imagem.getRGB(pixel_corrente, y).getAlpha();
                    if (alfa_primeiro != repetindo) {
                        tem_alfa = true;
                        break;
                    }
                }
            }

            if (tem_alfa) {
                arquivador.writeByte((byte) IMAGEM_ALFA_COM);
                arquivador.writeByte((byte) alfa_primeiro);
            } else {
                arquivador.writeByte((byte) IMAGEM_ALFA_SEM);
                arquivador.writeByte((byte) 0);
            }

            System.out.println("Tem Alfa :: " + tem_alfa);
            int[] matriz = new int[64];

            for (pixel_corrente = 0; pixel_corrente < 64; ++pixel_corrente) {
                matriz[pixel_corrente] = 0;
            }

            pixel_corrente = 0;
            repetindo = 0;

            for (int y = 0; y < altura; ++y) {
                for (int x = 0; x < largura; ++x) {
                    int pixel = imagem.getIntPixel(x, y);
                    ++todos;
                    if (pixel_corrente == pixel) {
                        ++repetir;
                        ++repetindo;
                        if (repetindo == 63) {
                            int8.zerar();
                            int8.set2Bits(0, 1, 0);
                            int6.set(repetindo);
                            int8.copiarComecando(2, int6.getValores(), 6);
                            arquivador.writeByte((byte) int8.getInt());
                            if (primeiro > 0) {
                                --primeiro;
                            }

                            repetindo = 0;
                        }
                    } else {
                        if (repetindo > 0) {
                            int8.zerar();
                            int8.set2Bits(0, 1, 0);
                            int6.set(repetindo);
                            int8.copiarComecando(2, int6.getValores(), 6);
                            arquivador.writeByte((byte) int8.getInt());
                            if (primeiro > 0) {
                                --primeiro;
                            }

                            repetindo = 0;
                        }

                        Cor cPixel = Cor.int32_to_cor(pixel);
                        int indice = existe(matriz, pixel);
                        pixel_corrente = pixel;
                        int pos = (cPixel.getAlpha() + cPixel.getRed() + cPixel.getGreen() + cPixel.getBlue()) % 64;
                        if (indice >= 0) {
                            int6.zerar();
                            int6.set(pos);
                            int8.zerar();
                            int8.set2Bits(0, 1, 1);
                            int8.copiarComecando(2, int6.getValores(), 6);
                            arquivador.writeByte((byte) int8.getInt());
                            if (primeiro > 0) {
                                --primeiro;
                            }
                        } else {
                            matriz[pos] = pixel;
                            int6.zerar();
                            int6.set(pos);
                            int8.zerar();
                            int8.set2Bits(0, 0, 1);
                            int8.copiarComecando(2, int6.getValores(), 6);
                            arquivador.writeByte((byte) int8.getInt());
                            if (!tem_alfa) {
                                arquivador.writeByte((byte) cPixel.getAlpha());
                            }

                            arquivador.writeByte((byte) cPixel.getRed());
                            arquivador.writeByte((byte) cPixel.getGreen());
                            arquivador.writeByte((byte) cPixel.getBlue());
                            if (primeiro > 0) {
                                --primeiro;
                            }
                        }

                        ++fixados;
                    }
                }
            }

            int8.zerar();
            arquivador.writeByte((byte) int8.getInt());
            arquivador.fechar();
            System.out.println("Imagem IM - Terminada");
        } catch (IOException var24) {
            var24.printStackTrace();
        }

        System.out.println("TODOS     :: " + todos);
        System.out.println("FIXADOS   :: " + fixados);
        System.out.println("REPITIDOS :: " + repetir);
    }


    public static int existe(int matriz[], int procurado) {
        int resposta = -1;

        for (int i = 0; i < 64; i++) {
            if (matriz[i] == procurado) {
                resposta = i;
                break;
            }
        }

        return resposta;
    }

    public static void toPNG(String eArquivo, String eArquivoPNG) {


        System.out.println("Imagem IM - Abrindo");


        int todos = 0;
        int fixados = 0;
        int repetir = 0;

        Int8 INT_8BITS = new Int8(0);
        Int6 INT_6BITS = new Int6(0);


        try {

            Arquivador arquivador = new Arquivador(eArquivo);

            byte b1 = arquivador.readByte();
            byte b2 = arquivador.readByte();

            byte versao = arquivador.readByte();

            int largura = arquivador.readInt();
            int altura = arquivador.readInt();

            System.out.println("Largura :: " + largura);
            System.out.println("Altura  :: " + altura);

            byte a1 = arquivador.readByte();
            byte a2 = arquivador.readByte();

            boolean alfa_com = false;
            int alfa_canal = 0;

            if (Inteiro.byteToInt(a1) == IMAGEM_ALFA_COM) {
                alfa_com = true;
                alfa_canal = Inteiro.byteToInt(a2);
                System.out.println("Tem Alfa :: " + alfa_canal);
            }


            BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);


            int matriz[] = new int[64];

            for (int i = 0; i < 64; i++) {
                matriz[i] = 0;
            }

            int pixel_corrente = 0;
            int repetindo = 0;

            boolean lendo = true;

            int x = 0;
            int y = 0;

            while (lendo) {

                byte valor = arquivador.readByte();

                if (valor == (byte) 0) {
                    lendo = false;
                } else {

                    int v = Inteiro.byteToInt(valor);

                    // System.out.println("Chave :: " + v);

                    INT_8BITS.set(v);

                    if (INT_8BITS.getBitsInt(0, 2) == 01) {

                        if (alfa_com) {
                            int v1 = Inteiro.byteToInt(arquivador.readByte());
                            alfa_canal = v1;
                        }

                        int v2 = Inteiro.byteToInt(arquivador.readByte());
                        int v3 = Inteiro.byteToInt(arquivador.readByte());
                        int v4 = Inteiro.byteToInt(arquivador.readByte());

                        Color cPixel = new Color(v2, v3, v4, alfa_canal);

                        //   int pos = ((cPixel.getAlpha()) + (cPixel.getRed()) + (cPixel.getGreen()) + (cPixel.getBlue())) % 64;


                        INT_6BITS.zerar();
                        INT_6BITS.setValor(0, INT_8BITS.getValor(2));
                        INT_6BITS.setValor(1, INT_8BITS.getValor(3));
                        INT_6BITS.setValor(2, INT_8BITS.getValor(4));
                        INT_6BITS.setValor(3, INT_8BITS.getValor(5));
                        INT_6BITS.setValor(4, INT_8BITS.getValor(6));
                        INT_6BITS.setValor(5, INT_8BITS.getValor(7));


                        pixel_corrente = cPixel.getRGB();
                        matriz[INT_6BITS.getInt()] = pixel_corrente;

                        imagem.setRGB(x, y, pixel_corrente);

                        fixados += 1;
                        todos += 1;

                        x += 1;
                        if (x >= largura) {
                            x = 0;
                            y += 1;
                        }

                        // int a = cPixel.getAlpha();
                        // int r = cPixel.getRed();
                        // int g = cPixel.getGreen();
                        ///  int b = cPixel.getBlue();

                        //System.out.println(v + " -->> NOVA COR :: " + pixel_corrente + " :: " + pos + " _ " + INT_6BITS.getInt() + " { " + a + "," + r + "," + g + "," + b + " } ");

                    } else if (INT_8BITS.getBitsInt(0, 2) == 11) {


                        INT_6BITS.zerar();
                        INT_6BITS.setValor(0, INT_8BITS.getValor(2));
                        INT_6BITS.setValor(1, INT_8BITS.getValor(3));
                        INT_6BITS.setValor(2, INT_8BITS.getValor(4));
                        INT_6BITS.setValor(3, INT_8BITS.getValor(5));
                        INT_6BITS.setValor(4, INT_8BITS.getValor(6));
                        INT_6BITS.setValor(5, INT_8BITS.getValor(7));


                        pixel_corrente = matriz[INT_6BITS.getInt()];

                        //  System.out.println("{" +INT_8BITS.getValor(0) + "" + INT_8BITS.getValor(1) + "} :: " + INT_8BITS.get() + " :::: " +  v + " -->> INDEXADA :: " + INT_8BITS.getInt() + " -->> " + INT_6BITS.getInt() + " :: " + pixel_corrente);

                        imagem.setRGB(x, y, pixel_corrente);

                        todos += 1;
                        fixados += 1;

                        x += 1;
                        if (x >= largura) {
                            x = 0;
                            y += 1;
                        }
                    } else if (INT_8BITS.getBitsInt(0, 2) == 10) {

                        INT_6BITS.zerar();
                        INT_6BITS.setValor(0, INT_8BITS.getValor(2));
                        INT_6BITS.setValor(1, INT_8BITS.getValor(3));
                        INT_6BITS.setValor(2, INT_8BITS.getValor(4));
                        INT_6BITS.setValor(3, INT_8BITS.getValor(5));
                        INT_6BITS.setValor(4, INT_8BITS.getValor(6));
                        INT_6BITS.setValor(5, INT_8BITS.getValor(7));

                        //    System.out.println(v + " -->> Repetir :: " + INT_8BITS.getInt() + " -->> " + INT_6BITS.getInt());

                        for (int rep = 0; rep < INT_6BITS.getInt(); rep++) {

                            imagem.setRGB(x, y, pixel_corrente);

                            x += 1;
                            if (x >= largura) {
                                x = 0;
                                y += 1;
                            }

                            repetir += 1;
                            todos += 1;

                        }


                    }

                }
            }


            arquivador.fechar();

            ImageUtils.exportar(imagem, eArquivoPNG);
            if (DEBUG) {
                System.out.println("Imagem IM - Terminada");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (DEBUG) {
            System.out.println("TODOS     :: " + todos);
            System.out.println("FIXADOS   :: " + fixados);
            System.out.println("REPITIDOS :: " + repetir);
        }

    }

    public static long salvar_bytes(BufferedImage eImagem, Arquivador arquivador) {


        int largura = eImagem.getWidth();
        int altura = eImagem.getHeight();

        int todos = 0;
        int fixados = 0;
        int repetir = 0;

        Int8 int8 = new Int8(0);
        Int6 int6 = new Int6(0);

        int primeiro = 10;

        long pos_inicio = arquivador.getPonteiro();

        arquivador.writeByte((byte) IMAGEM_IM1);
        arquivador.writeByte((byte) IMAGEM_IM2);

        arquivador.writeByte((byte) IMAGEM_VERSAO_1);

        arquivador.writeInt(largura);
        arquivador.writeInt(altura);

        int alfa_primeiro = new Color(eImagem.getRGB(0, 0)).getAlpha();
        boolean tem_alfa = false;

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {

                int alfa_corrente = new Color(eImagem.getRGB(x, y)).getAlpha();

                if (alfa_primeiro != alfa_corrente) {
                    tem_alfa = true;
                    break;
                }

            }
        }

        if (tem_alfa) {
            arquivador.writeByte((byte) IMAGEM_ALFA_SEM);
            arquivador.writeByte((byte) alfa_primeiro);
        } else {
            arquivador.writeByte((byte) IMAGEM_ALFA_COM);
            arquivador.writeByte((byte) 0);
        }
        if (DEBUG) {
            System.out.println("Tem Alfa :: " + tem_alfa);
            System.out.println("Largura  :: " + eImagem.getWidth());
            System.out.println("Altura   :: " + eImagem.getHeight());
        }

        int matriz[] = new int[64];

        for (int i = 0; i < 64; i++) {
            matriz[i] = 0;
        }

        int pixel_corrente = 0;
        int repetindo = 0;


        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {

                int pixel = eImagem.getRGB(x, y);

                todos += 1;

                if (pixel_corrente == pixel) {

                    repetir += 1;

                    repetindo += 1;

                    if (repetindo == 63) {

                        int8.zerar();
                        int8.set2Bits(0, 1, 0);

                        int6.set(repetindo);
                        int8.copiarComecando(2, int6.getValores(), 6);

                        arquivador.writeByte((byte) int8.getInt());

                        if (primeiro > 0) {
                            primeiro -= 1;
                            //   System.out.println("Repetindo (" + repetindo + ") = " + pixel_corrente + " em " + INT_8BITS.getInt());
                        }

                        repetindo = 0;
                    }

                } else {

                    if (repetindo > 0) {
                        int8.zerar();
                        int8.set2Bits(0, 1, 0);

                        int6.set(repetindo);
                        int8.copiarComecando(2, int6.getValores(), 6);

                        arquivador.writeByte((byte) int8.getInt());

                        if (primeiro > 0) {
                            primeiro -= 1;
                            //    System.out.println("Repetindo (" + repetindo + ") = " + pixel_corrente + " em " + INT_8BITS.getInt());
                        }

                        repetindo = 0;
                    }


                    Color cPixel = new Color(pixel);

                    int indice = existe(matriz, pixel);
                    pixel_corrente = cPixel.getRGB();


                    int pos = ((cPixel.getAlpha()) + (cPixel.getRed()) + (cPixel.getGreen()) + (cPixel.getBlue())) % 64;

                    if (indice >= 0) {

                        int6.zerar();
                        int6.set(pos);

                        int8.zerar();
                        int8.set2Bits(0, 1, 1);

                        int8.copiarComecando(2, int6.getValores(), 6);

                        arquivador.writeByte((byte) int8.getInt());

                        if (primeiro > 0) {
                            primeiro -= 1;
                            //    System.out.println("Pixel Index (" + pos + ") = " + INT_8BITS.getInt());
                        }
                    } else {

                        matriz[pos] = pixel;

                        int6.zerar();
                        int6.set(pos);


                        int8.zerar();
                        int8.set2Bits(0, 0, 1);


                        int8.copiarComecando(2, int6.getValores(), 6);

                        arquivador.writeByte((byte) int8.getInt());

                        if (!tem_alfa) {
                            arquivador.writeByte((byte) cPixel.getAlpha());
                        }

                        arquivador.writeByte((byte) cPixel.getRed());
                        arquivador.writeByte((byte) cPixel.getGreen());
                        arquivador.writeByte((byte) cPixel.getBlue());


                        if (primeiro > 0) {
                            primeiro -= 1;
                            //   System.out.println("Novo Pixel (" + pos + ") = " + pixel + " em " + INT_8BITS.getInt() + " -->> " + INT_8BITS.get());
                        }
                    }


                    fixados += 1;

                }


            }
        }

        int8.zerar();
        arquivador.writeByte((byte) int8.getInt());

        if (DEBUG) {
            System.out.println("TODOS     :: " + todos);
            System.out.println("FIXADOS   :: " + fixados);
            System.out.println("REPITIDOS :: " + repetir);
        }

        long pos_fim = arquivador.getPonteiro();

        return pos_fim - pos_inicio;
    }

    public static BufferedImage lerDoFluxo(Arquivador arquivador) {

        if (DEBUG) {
            System.out.println("Imagem IM - Abrindo");
        }


        int todos = 0;
        int fixados = 0;
        int repetir = 0;

        Int8 INT_8BITS = new Int8(0);
        Int6 INT_6BITS = new Int6(0);


        byte b1 = arquivador.readByte();
        byte b2 = arquivador.readByte();

        byte versao = arquivador.readByte();

        int largura = arquivador.readInt();
        int altura = arquivador.readInt();

        if (DEBUG) {
            System.out.println("Largura :: " + largura);
            System.out.println("Altura  :: " + altura);
        }

        byte a1 = arquivador.readByte();
        byte a2 = arquivador.readByte();

        boolean alfa_com = false;
        int alfa_canal = 0;

        if (Inteiro.byteToInt(a1) == IMAGEM_ALFA_COM) {
            alfa_com = true;
            alfa_canal = Inteiro.byteToInt(a2);
            if (DEBUG) {
                System.out.println("Tem Alfa :: " + alfa_canal);
            }
        }


        BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);


        int matriz[] = new int[64];

        for (int i = 0; i < 64; i++) {
            matriz[i] = 0;
        }

        int pixel_corrente = 0;
        int repetindo = 0;

        boolean lendo = true;

        int x = 0;
        int y = 0;

        while (lendo) {

            byte valor = arquivador.readByte();

            if (valor == (byte) 0) {
                lendo = false;
            } else {

                int v = Inteiro.byteToInt(valor);

                // System.out.println("Chave :: " + v);

                INT_8BITS.set(v);

                if (INT_8BITS.getBitsInt(0, 2) == 01) {

                    if (alfa_com) {
                        int v1 = Inteiro.byteToInt(arquivador.readByte());
                        alfa_canal = v1;
                    }

                    int v2 = Inteiro.byteToInt(arquivador.readByte());
                    int v3 = Inteiro.byteToInt(arquivador.readByte());
                    int v4 = Inteiro.byteToInt(arquivador.readByte());

                    Color cPixel = new Color(v2, v3, v4, alfa_canal);

                    //   int pos = ((cPixel.getAlpha()) + (cPixel.getRed()) + (cPixel.getGreen()) + (cPixel.getBlue())) % 64;


                    INT_6BITS.zerar();
                    INT_6BITS.setValor(0, INT_8BITS.getValor(2));
                    INT_6BITS.setValor(1, INT_8BITS.getValor(3));
                    INT_6BITS.setValor(2, INT_8BITS.getValor(4));
                    INT_6BITS.setValor(3, INT_8BITS.getValor(5));
                    INT_6BITS.setValor(4, INT_8BITS.getValor(6));
                    INT_6BITS.setValor(5, INT_8BITS.getValor(7));


                    pixel_corrente = cPixel.getRGB();
                    matriz[INT_6BITS.getInt()] = pixel_corrente;

                    imagem.setRGB(x, y, pixel_corrente);

                    fixados += 1;
                    todos += 1;

                    x += 1;
                    if (x >= largura) {
                        x = 0;
                        y += 1;
                    }

                    // int a = cPixel.getAlpha();
                    // int r = cPixel.getRed();
                    // int g = cPixel.getGreen();
                    ///  int b = cPixel.getBlue();

                    //System.out.println(v + " -->> NOVA COR :: " + pixel_corrente + " :: " + pos + " _ " + INT_6BITS.getInt() + " { " + a + "," + r + "," + g + "," + b + " } ");

                } else if (INT_8BITS.getBitsInt(0, 2) == 11) {


                    INT_6BITS.zerar();
                    INT_6BITS.setValor(0, INT_8BITS.getValor(2));
                    INT_6BITS.setValor(1, INT_8BITS.getValor(3));
                    INT_6BITS.setValor(2, INT_8BITS.getValor(4));
                    INT_6BITS.setValor(3, INT_8BITS.getValor(5));
                    INT_6BITS.setValor(4, INT_8BITS.getValor(6));
                    INT_6BITS.setValor(5, INT_8BITS.getValor(7));


                    pixel_corrente = matriz[INT_6BITS.getInt()];

                    //  System.out.println("{" +INT_8BITS.getValor(0) + "" + INT_8BITS.getValor(1) + "} :: " + INT_8BITS.get() + " :::: " +  v + " -->> INDEXADA :: " + INT_8BITS.getInt() + " -->> " + INT_6BITS.getInt() + " :: " + pixel_corrente);

                    imagem.setRGB(x, y, pixel_corrente);

                    todos += 1;
                    fixados += 1;

                    x += 1;
                    if (x >= largura) {
                        x = 0;
                        y += 1;
                    }
                } else if (INT_8BITS.getBitsInt(0, 2) == 10) {

                    INT_6BITS.zerar();
                    INT_6BITS.setValor(0, INT_8BITS.getValor(2));
                    INT_6BITS.setValor(1, INT_8BITS.getValor(3));
                    INT_6BITS.setValor(2, INT_8BITS.getValor(4));
                    INT_6BITS.setValor(3, INT_8BITS.getValor(5));
                    INT_6BITS.setValor(4, INT_8BITS.getValor(6));
                    INT_6BITS.setValor(5, INT_8BITS.getValor(7));

                    //    System.out.println(v + " -->> Repetir :: " + INT_8BITS.getInt() + " -->> " + INT_6BITS.getInt());

                    for (int rep = 0; rep < INT_6BITS.getInt(); rep++) {

                        imagem.setRGB(x, y, pixel_corrente);

                        x += 1;
                        if (x >= largura) {
                            x = 0;
                            y += 1;
                        }

                        repetir += 1;
                        todos += 1;

                    }


                }

            }
        }

        if (DEBUG) {

            System.out.println("Imagem IM - Terminada");

            System.out.println("TODOS     :: " + todos);
            System.out.println("FIXADOS   :: " + fixados);
            System.out.println("REPITIDOS :: " + repetir);
        }

        return imagem;
    }

    public static ImagemI32 lerDoFluxo_imagem_i32(Arquivador arquivador) {

        boolean DEBUG = true;


        int todos = 0;
        int fixados = 0;
        int repetir = 0;
        Int8 INT_8BITS = new Int8(0);
        Int6 INT_6BITS = new Int6(0);
        byte b1 = arquivador.readByte();
        byte b2 = arquivador.readByte();
        byte versao = arquivador.readByte();
        int largura = arquivador.readInt();
        int altura = arquivador.readInt();
        if (DEBUG) {
            System.out.println("Largura :: " + largura);
            System.out.println("Altura  :: " + altura);
        }

        byte a1 = arquivador.readByte();
        byte a2 = arquivador.readByte();
        boolean alfa_com = false;
        int alfa_canal = 0;
        if (Inteiro.byteToInt(a1) == IMAGEM_ALFA_COM) {
            alfa_com = true;
            alfa_canal = Inteiro.byteToInt(a2);
            if (DEBUG) {
                System.out.println("Tem Alfa :: " + alfa_canal);
            }
        }

        ImagemI32 imagem = new ImagemI32(largura, altura);
        int[] matriz = new int[64];

        int pixel_corrente;
        for (pixel_corrente = 0; pixel_corrente < 64; ++pixel_corrente) {
            matriz[pixel_corrente] = 0;
        }

        pixel_corrente = 0;
        boolean repetindo = false;
        boolean lendo = true;
        int x = 0;
        int y = 0;

        while (true) {
            while (lendo) {
                byte valor = arquivador.readByte();
                if (valor == 0) {
                    lendo = false;
                } else {
                    int v = Inteiro.byteToInt(valor);
                    INT_8BITS.set(v);
                    int rep;
                    if (INT_8BITS.getBitsInt(0, 2) == 1) {
                        if (alfa_com) {
                            rep = Inteiro.byteToInt(arquivador.readByte());
                            alfa_canal = rep;
                        }

                        rep = Inteiro.byteToInt(arquivador.readByte());
                        int v3 = Inteiro.byteToInt(arquivador.readByte());
                        int v4 = Inteiro.byteToInt(arquivador.readByte());

                        Cor cPixel = new Cor(rep, v3, v4);
                        if (alfa_com) {
                            cPixel.setAlpha(alfa_canal);
                        }
                        INT_6BITS.zerar();
                        INT_6BITS.setValor(0, INT_8BITS.getValor(2));
                        INT_6BITS.setValor(1, INT_8BITS.getValor(3));
                        INT_6BITS.setValor(2, INT_8BITS.getValor(4));
                        INT_6BITS.setValor(3, INT_8BITS.getValor(5));
                        INT_6BITS.setValor(4, INT_8BITS.getValor(6));
                        INT_6BITS.setValor(5, INT_8BITS.getValor(7));
                        pixel_corrente = Cor.corToInt(cPixel);
                        matriz[INT_6BITS.getInt()] = pixel_corrente;
                        imagem.set(x, y, pixel_corrente);
                        ++fixados;
                        ++todos;
                        ++x;
                        if (x >= largura) {
                            x = 0;
                            ++y;
                        }
                    } else if (INT_8BITS.getBitsInt(0, 2) == 11) {
                        INT_6BITS.zerar();
                        INT_6BITS.setValor(0, INT_8BITS.getValor(2));
                        INT_6BITS.setValor(1, INT_8BITS.getValor(3));
                        INT_6BITS.setValor(2, INT_8BITS.getValor(4));
                        INT_6BITS.setValor(3, INT_8BITS.getValor(5));
                        INT_6BITS.setValor(4, INT_8BITS.getValor(6));
                        INT_6BITS.setValor(5, INT_8BITS.getValor(7));
                        pixel_corrente = matriz[INT_6BITS.getInt()];

                        if (x < largura && y < altura) {
                            imagem.set(x, y, pixel_corrente);
                        } else {
                            break;
                        }

                        ++todos;
                        ++fixados;
                        ++x;
                        if (x >= largura) {
                            x = 0;
                            ++y;
                        }
                    } else if (INT_8BITS.getBitsInt(0, 2) == 10) {
                        INT_6BITS.zerar();
                        INT_6BITS.setValor(0, INT_8BITS.getValor(2));
                        INT_6BITS.setValor(1, INT_8BITS.getValor(3));
                        INT_6BITS.setValor(2, INT_8BITS.getValor(4));
                        INT_6BITS.setValor(3, INT_8BITS.getValor(5));
                        INT_6BITS.setValor(4, INT_8BITS.getValor(6));
                        INT_6BITS.setValor(5, INT_8BITS.getValor(7));

                        for (rep = 0; rep < INT_6BITS.getInt(); ++rep) {
                            imagem.set(x, y, pixel_corrente);
                            ++x;
                            if (x >= largura) {
                                x = 0;
                                ++y;
                            }

                            ++repetir;
                            ++todos;
                        }
                    }
                }
            }

            if (DEBUG) {
                System.out.println("Imagem IM - Terminada");
                System.out.println("TODOS     :: " + todos);
                System.out.println("FIXADOS   :: " + fixados);
                System.out.println("REPITIDOS :: " + repetir);
            }

            return imagem;
        }
    }

    public static BufferedImage abrir(String eArquivo) {

        Arquivador arquivador = new Arquivador(eArquivo);

        BufferedImage imagem = lerDoFluxo(arquivador);

        arquivador.encerrar();


        return imagem;
    }

    public static ImagemI32 abrir_imagem32(String eArquivo) {

        Arquivador arquivador = new Arquivador(eArquivo);

        ImagemI32 imagem = lerDoFluxo_imagem_i32(arquivador);

        arquivador.encerrar();


        return imagem;
    }
}

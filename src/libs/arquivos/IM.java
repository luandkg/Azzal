package libs.arquivos;

import libs.azzal.utilitarios.Cor;
import libs.luan.RefInt;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Int6;
import libs.arquivos.binario.Int8;
import libs.arquivos.binario.Inteiro;
import libs.imagem.Imagem;
import libs.meta_functional.FuncaoBeta;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IM {

    private static int IMAGEM_IM1 = 38;
    private static int IMAGEM_IM2 = 42;
    private static int IMAGEM_VERSAO_1 = 100;

    private static int IMAGEM_ALFA_SEM = 11;
    private static int IMAGEM_ALFA_UNICO = 12;
    private static int IMAGEM_ALFA_COM = 13;

    private static final int PIXEL_NOVO = 1;
    private static final int PIXEL_REPETIR = 10;
    private static final int PIXEL_PALETADO = 11;

    private static final int TERMINAR_IMAGEM = 0;

    private static final int MATRIZ_TAMANHO = 64;

    private static boolean DEBUG = false;

    // 11 - COR INDEXADA
    // 10 - REPETIR
    // 01 - NOVA COR
    // 00 - FECHAR

    public static void indices() {
        TX eTX = new TX();
        System.out.println("I :: " + eTX.getIndice("I"));
        System.out.println("M :: " + eTX.getIndice("M"));
        System.out.println("C :: " + eTX.getIndice("C"));
        System.out.println("S :: " + eTX.getIndice("S"));
    }

    public static void salvar(BufferedImage eImagem, String eArquivo) {

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


        FuncaoBeta<RefInt, int[], RefInt> procurar = new FuncaoBeta<RefInt, int[], RefInt>() {
            @Override
            public RefInt fazer(int[] matriz, RefInt procurado) {

                RefInt resposta = new RefInt(-1);

                for (int i = 0; i < MATRIZ_TAMANHO; i++) {
                    if (matriz[i] == procurado.get()) {
                        resposta.set(i);
                        break;
                    }
                }

                return resposta;

            }
        };


        Arquivador arquivador = new Arquivador(eArquivo);


        arquivador.set_u8((byte) IMAGEM_IM1);
        arquivador.set_u8((byte) IMAGEM_IM2);

        arquivador.set_u8((byte) IMAGEM_VERSAO_1);

        arquivador.set_u32(largura);
        arquivador.set_u32(altura);

        int alfa_primeiro = new Color(eImagem.getRGB(0, 0)).getAlpha();
        boolean tem_alfa = false;

        int contando_unico = 0;
        int pixels_todos = altura * largura;

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {

                int alfa_corrente = Cor.int32_to_alfa(eImagem.getRGB(x, y));

                if (alfa_primeiro == alfa_corrente) {
                    contando_unico += 1;
                } else {
                    tem_alfa = true;
                    break;
                }

            }
            if (tem_alfa) {
                break;
            }
        }

        String sAlfa = "";
        int alfa_modo = 0;

        if (tem_alfa) {
            alfa_modo = IMAGEM_ALFA_COM;
            arquivador.set_u8((byte) IMAGEM_ALFA_COM);
            arquivador.set_u8((byte) alfa_primeiro);
            sAlfa = "COM";
        } else {

            if (contando_unico == pixels_todos) {
                sAlfa = "UNICO";
                alfa_modo = IMAGEM_ALFA_UNICO;

                arquivador.set_u8((byte) IMAGEM_ALFA_UNICO);
                arquivador.set_u8((byte) alfa_primeiro);

            } else {
                sAlfa = "SEM";
                alfa_modo = IMAGEM_ALFA_SEM;

                arquivador.set_u8((byte) IMAGEM_ALFA_SEM);
                arquivador.set_u8((byte) alfa_primeiro);

            }
        }

        System.out.println("\tTem Alfa   :: " + sAlfa);
        System.out.println("\tAlfa Valor :: " + alfa_primeiro);

        int matriz[] = new int[64];

        for (int i = 0; i < 64; i++) {
            matriz[i] = 0;
        }

        int pixel_corrente = 0;
        int repetindo = 0;

        //  RhoLog log = new RhoLog();
        //    log.titulo("SALVAR IM");

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

                        arquivador.set_u8((byte) int8.getInt());

                        // log.adicionar("Repetir -- " + pixel_corrente + " -->> " + repetindo);

                        repetindo = 0;
                    }

                } else {

                    if (repetindo > 0) {
                        int8.zerar();
                        int8.set2Bits(0, 1, 0);

                        int6.set(repetindo);
                        int8.copiarComecando(2, int6.getValores(), 6);

                        arquivador.set_u8((byte) int8.getInt());

                        //  log.adicionar("Repetir -- " + pixel_corrente + " -->> " + repetindo);

                        repetindo = 0;
                    }


                    Cor cPixel = Cor.int32_to_cor(pixel);

                    int pos = 0;

                    int indice = procurar.fazer(matriz, new RefInt(pixel)).get();

                    pixel_corrente = pixel;

                    pos = ((cPixel.getAlpha()) + (cPixel.getRed()) + (cPixel.getGreen()) + (cPixel.getBlue())) % 64;


                    if (indice >= 0) {

                        int6.zerar();
                        int6.set(pos);

                        int8.zerar();
                        int8.set2Bits(0, 1, 1);

                        int8.copiarComecando(2, int6.getValores(), 6);

                        arquivador.set_u8((byte) int8.getInt());

                        //      log.adicionar("Paletado -- " + int6.getInt());

                    } else {

                        matriz[pos] = pixel;

                        int6.zerar();
                        int6.set(pos);


                        int8.zerar();
                        int8.set2Bits(0, 0, 1);


                        int8.copiarComecando(2, int6.getValores(), 6);

                        arquivador.set_u8((byte) int8.getInt());

                        if (alfa_modo == IMAGEM_ALFA_COM) {
                            arquivador.set_u8((byte) cPixel.getAlpha());
                        }

                        arquivador.set_u8((byte) cPixel.getRed());
                        arquivador.set_u8((byte) cPixel.getGreen());
                        arquivador.set_u8((byte) cPixel.getBlue());

                        //     log.adicionar("Pixel -- " + pixel + " :: " + pos);

                    }


                    fixados += 1;

                }


            }
        }

        int8.zerar();
        arquivador.set_u8((byte) int8.getInt());


        arquivador.encerrar();

        System.out.println("\tTODOS      :: " + todos);
        System.out.println("\tFIXADOS    :: " + fixados);
        System.out.println("\tREPITIDOS  :: " + repetir);

        //    log.salvar("/home/luan/Containers/im_salvar.txt");

        System.out.println("Imagem IM - Terminada");


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


        FuncaoBeta<RefInt, int[], RefInt> procurar = new FuncaoBeta<RefInt, int[], RefInt>() {
            @Override
            public RefInt fazer(int[] matriz, RefInt procurado) {

                int resposta = -1;

                for (int i = 0; i < 64; i++) {
                    if (matriz[i] == procurado.get()) {
                        resposta = i;
                        break;
                    }
                }

                return new RefInt(resposta);

            }
        };


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
            arquivador.set_u8((byte) IMAGEM_IM1);
            arquivador.set_u8((byte) IMAGEM_IM2);
            arquivador.set_u8((byte) IMAGEM_VERSAO_1);
            arquivador.set_u32(largura);
            arquivador.set_u32(altura);
            int alfa_primeiro = imagem.getRGB(0, 0).getAlpha();
            boolean tem_alfa = false;

            int pixel_corrente = 0;
            int repetindo = 0;

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
                arquivador.set_u8((byte) IMAGEM_ALFA_COM);
                arquivador.set_u8((byte) alfa_primeiro);
            } else {
                arquivador.set_u8((byte) IMAGEM_ALFA_SEM);
                arquivador.set_u8((byte) 0);
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
                            arquivador.set_u8((byte) int8.getInt());
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
                            arquivador.set_u8((byte) int8.getInt());
                            if (primeiro > 0) {
                                --primeiro;
                            }

                            repetindo = 0;
                        }

                        Cor cPixel = Cor.int32_to_cor(pixel);
                        //    int indice = existe(matriz, pixel);

                        int indice = procurar.fazer(matriz, new RefInt(pixel)).get();


                        pixel_corrente = pixel;
                        int pos = (cPixel.getAlpha() + cPixel.getRed() + cPixel.getGreen() + cPixel.getBlue()) % 64;
                        if (indice >= 0) {
                            int6.zerar();
                            int6.set(pos);
                            int8.zerar();
                            int8.set2Bits(0, 1, 1);
                            int8.copiarComecando(2, int6.getValores(), 6);
                            arquivador.set_u8((byte) int8.getInt());
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
                            arquivador.set_u8((byte) int8.getInt());
                            if (!tem_alfa) {
                                arquivador.set_u8((byte) cPixel.getAlpha());
                            }

                            arquivador.set_u8((byte) cPixel.getRed());
                            arquivador.set_u8((byte) cPixel.getGreen());
                            arquivador.set_u8((byte) cPixel.getBlue());
                            if (primeiro > 0) {
                                --primeiro;
                            }
                        }

                        ++fixados;
                    }
                }
            }

            int8.zerar();
            arquivador.set_u8((byte) int8.getInt());
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

        Arquivador arquivador = new Arquivador(eArquivo);

        BufferedImage imagem = lerDoFluxo(arquivador);

        arquivador.encerrar();

        Imagem.exportar(imagem, eArquivoPNG);

        if (DEBUG) {
            System.out.println("Imagem IM - Terminada");
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

        arquivador.set_u8((byte) IMAGEM_IM1);
        arquivador.set_u8((byte) IMAGEM_IM2);

        arquivador.set_u8((byte) IMAGEM_VERSAO_1);

        arquivador.set_u32(largura);
        arquivador.set_u32(altura);

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
            arquivador.set_u8((byte) IMAGEM_ALFA_SEM);
            arquivador.set_u8((byte) alfa_primeiro);
        } else {
            arquivador.set_u8((byte) IMAGEM_ALFA_COM);
            arquivador.set_u8((byte) 0);
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

        FuncaoBeta<RefInt, int[], RefInt> procurar = new FuncaoBeta<RefInt, int[], RefInt>() {
            @Override
            public RefInt fazer(int[] matriz, RefInt procurado) {

                int resposta = -1;

                for (int i = 0; i < 64; i++) {
                    if (matriz[i] == procurado.get()) {
                        resposta = i;
                        break;
                    }
                }

                return new RefInt(resposta);

            }
        };

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

                        arquivador.set_u8((byte) int8.getInt());

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

                        arquivador.set_u8((byte) int8.getInt());

                        if (primeiro > 0) {
                            primeiro -= 1;
                            //    System.out.println("Repetindo (" + repetindo + ") = " + pixel_corrente + " em " + INT_8BITS.getInt());
                        }

                        repetindo = 0;
                    }


                    Color cPixel = new Color(pixel);

                    // int indice = existe(matriz, pixel);

                    int indice = procurar.fazer(matriz, new RefInt(pixel)).get();

                    pixel_corrente = cPixel.getRGB();


                    int pos = ((cPixel.getAlpha()) + (cPixel.getRed()) + (cPixel.getGreen()) + (cPixel.getBlue())) % 64;

                    if (indice >= 0) {

                        int6.zerar();
                        int6.set(pos);

                        int8.zerar();
                        int8.set2Bits(0, 1, 1);

                        int8.copiarComecando(2, int6.getValores(), 6);

                        arquivador.set_u8((byte) int8.getInt());

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

                        arquivador.set_u8((byte) int8.getInt());

                        if (!tem_alfa) {
                            arquivador.set_u8((byte) cPixel.getAlpha());
                        }

                        arquivador.set_u8((byte) cPixel.getRed());
                        arquivador.set_u8((byte) cPixel.getGreen());
                        arquivador.set_u8((byte) cPixel.getBlue());


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
        arquivador.set_u8((byte) int8.getInt());

        if (DEBUG) {
            System.out.println("TODOS     :: " + todos);
            System.out.println("FIXADOS   :: " + fixados);
            System.out.println("REPITIDOS :: " + repetir);
        }

        long pos_fim = arquivador.getPonteiro();

        return pos_fim - pos_inicio;
    }

    public static BufferedImage lerDoFluxo(Arquivador arquivador) {

        DEBUG = true;

        if (DEBUG) {
            System.out.println("Imagem IM - Abrindo");
        }

        //  RhoLog log = new RhoLog();
        //   log.titulo("ABRIR IM");


        int todos = 0;
        int fixados = 0;
        int repetir = 0;

        Int8 mapa_de_8_bits = new Int8(0);
        //  Int6 mapa_de_6_bits = new Int6(0);


        byte b1 = arquivador.get();
        byte b2 = arquivador.get();

        byte versao = arquivador.get();

        int largura = arquivador.get_u32();
        int altura = arquivador.get_u32();

        if (DEBUG) {
            System.out.println("\tLargura    :: " + largura);
            System.out.println("\tAltura     :: " + altura);
        }

        int alfa_modo = Inteiro.byteToInt(arquivador.get());
        int alfa_canal = Inteiro.byteToInt(arquivador.get());

        boolean alfa_com = false;

        if (alfa_modo == IMAGEM_ALFA_COM) {
            alfa_com = true;
            if (DEBUG) {
                System.out.println("\tTem Alfa   :: COM");
                System.out.println("\tTem Alfa   :: " + alfa_canal);
            }
        } else if (alfa_modo == IMAGEM_ALFA_UNICO) {
            alfa_com = true;
            System.out.println("\tTem Alfa   :: UNICO");
            System.out.println("\tTem Alfa   :: " + alfa_canal);
        } else if (alfa_modo == IMAGEM_ALFA_SEM) {
            System.out.println("\tTem Alfa   :: SEM");
        }


        BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);


        int matriz[] = new int[64];

        for (int i = 0; i < 64; i++) {
            matriz[i] = 0;
        }

        int pixel_corrente = 0;
        int paletado_corrente = 0;

        boolean lendo = true;

        int x = 0;
        int y = 0;

        while (lendo) {

            int valor = Inteiro.byteToInt(arquivador.get());

            if (valor == TERMINAR_IMAGEM) {
                lendo = false;
            } else {

                // System.out.println("Chave :: " + v);

                mapa_de_8_bits.set(valor);

                if (mapa_de_8_bits.getBitsInt(0, 2) == PIXEL_NOVO) {

                    if (alfa_modo == IMAGEM_ALFA_COM) {
                        alfa_canal = Inteiro.byteToInt(arquivador.get());
                    }

                    int v_r = Inteiro.byteToInt(arquivador.get());
                    int v_g = Inteiro.byteToInt(arquivador.get());
                    int v_b = Inteiro.byteToInt(arquivador.get());

                    int cPixel = Cor.rgba_to_int(v_r, v_g, v_b, alfa_canal);


                    //  mapa_de_6_bits.zerar();
                    // mapa_de_6_bits.setArray(0, mapa_de_8_bits.getArray(2, 6));

                    int posicao_indexada = mapa_de_8_bits.getParteToInt(2, 6);

                    pixel_corrente = cPixel;
                    matriz[posicao_indexada] = pixel_corrente;

                    if (x < largura && y < altura) {
                        imagem.setRGB(x, y, pixel_corrente);
                    }

                    //    log.adicionar("Pixel -- " + cPixel + " :: " + posicao_indexada);


                    fixados += 1;
                    todos += 1;

                    x += 1;
                    if (x >= largura) {
                        x = 0;
                        y += 1;
                    }


                } else if (mapa_de_8_bits.getBitsInt(0, 2) == PIXEL_PALETADO) {


                    // mapa_de_6_bits.zerar();
                    // mapa_de_6_bits.setArray(0, mapa_de_8_bits.getArray(2, 6));

                    int posicao_indexada = mapa_de_8_bits.getParteToInt(2, 6);

                    paletado_corrente = posicao_indexada;
                    //   paletado_corrente = mapa_de_6_bits.getInt();
                    pixel_corrente = matriz[posicao_indexada];

                    //   log.adicionar("Paletado -- " + posicao_indexada);

                    if (x < largura && y < altura) {
                        imagem.setRGB(x, y, pixel_corrente);
                    }

                    todos += 1;
                    fixados += 1;

                    x += 1;
                    if (x >= largura) {
                        x = 0;
                        y += 1;
                    }
                } else if (mapa_de_8_bits.getBitsInt(0, 2) == PIXEL_REPETIR) {

                    //  mapa_de_6_bits.zerar();
                    //  mapa_de_6_bits.setArray(0, mapa_de_8_bits.getArray(2, 6));

                    // int repetir_vezes = mapa_de_6_bits.getInt();

                    int repetir_vezes = mapa_de_8_bits.getParteToInt(2, 6);

                    //     log.adicionar("Repetir -- " + pixel_corrente + " -->> " + repetir_vezes);


                    for (int rep = 0; rep < repetir_vezes; rep++) {

                        if (x < largura && y < altura) {
                            imagem.setRGB(x, y, pixel_corrente);
                        }

                        x += 1;
                        if (x >= largura) {
                            x = 0;
                            y += 1;
                        }

                        //   repetir += 1;
                        //   todos += 1;

                    }

                    repetir += repetir_vezes;
                    todos += repetir_vezes;


                }

            }
        }

        //  log.salvar("/home/luan/Containers/im_abrir.txt");

        if (DEBUG) {


            System.out.println("\tTODOS      :: " + todos);
            System.out.println("\tFIXADOS    :: " + fixados);
            System.out.println("\tREPITIDOS  :: " + repetir);

            System.out.println("Imagem IM - Terminada");

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
        byte b1 = arquivador.get();
        byte b2 = arquivador.get();
        byte versao = arquivador.get();
        int largura = arquivador.get_u32();
        int altura = arquivador.get_u32();
        if (DEBUG) {
            System.out.println("Largura :: " + largura);
            System.out.println("Altura  :: " + altura);
        }

        byte a1 = arquivador.get();
        byte a2 = arquivador.get();
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
                byte valor = arquivador.get();
                if (valor == 0) {
                    lendo = false;
                } else {
                    int v = Inteiro.byteToInt(valor);
                    INT_8BITS.set(v);
                    int rep;
                    if (INT_8BITS.getBitsInt(0, 2) == 1) {
                        if (alfa_com) {
                            rep = Inteiro.byteToInt(arquivador.get());
                            alfa_canal = rep;
                        }

                        rep = Inteiro.byteToInt(arquivador.get());
                        int v3 = Inteiro.byteToInt(arquivador.get());
                        int v4 = Inteiro.byteToInt(arquivador.get());

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

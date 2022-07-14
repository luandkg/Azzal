package apps.AppAzzal;

import libs.Arquivos.Binario.Arquivador;
import libs.Arquivos.Binario.Int6;
import libs.Arquivos.Binario.Int8;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Reduttor {

    public static void compactar(byte[] byteBuffer, String eArquivo) {


        System.out.println("Imagem IM - Criando");

        File Arq = new File(eArquivo);
        if (Arq.exists()) {
            Arq.delete();
        }


        int todos = 0;
        int fixados = 0;
        int repetir = 0;

        Int8 int8 = new Int8(0);
        Int6 int6 = new Int6(0);

        int primeiro = 10;


        try {

            Arquivador arquivador = new Arquivador(eArquivo);


            int matriz[] = new int[64];

            for (int i = 0; i < 64; i++) {
                matriz[i] = 0;
            }

            int pixel_corrente = 0;
            int repetindo = 0;

            int t4 = byteBuffer.length / 4;
            Tom tc = new Tom();

            for (int i = 0; i < t4; i++) {


                tc.a = byteBuffer[i*4];
                tc.b = byteBuffer[(i*4)+1];
                tc.c = byteBuffer[(i*4)+2];
                tc.d = byteBuffer[(i*4)+3];

                int pixel = tc.getInt();

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


                        arquivador.writeByte((byte) cPixel.getAlpha());
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
}

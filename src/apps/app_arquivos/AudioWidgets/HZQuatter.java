package apps.app_arquivos.AudioWidgets;

import apps.app_letrum.Fonte;
import libs.arquivos.binario.Inteiro;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.utilitarios.Cor;

public class HZQuatter {

    public static void completo(Windows eWindows) {

        int linha = 0;
        int coluna = 0;

        int c16 = 12 * 16;

        int c = 0;

        for (int vi = 0; vi < 256; vi += 2) {

            int va = Inteiro.byteToInt(eWindows.getAudio().getBuffer()[vi]);
            int vb = Inteiro.byteToInt(eWindows.getAudio().getBuffer()[vi + 1]);

            // mRenderizador.drawRect_Pintado(250 + coluna, 300 + linha, 10, 10, new Cor(va, 0, 0));

            //int vs = (va*256) + vb;
            int vs = (va << 8) | (vb & 0xff);

            if (c < 16) {
                System.out.println("V1 :: " + vs);
            } else {
                System.out.println("V2 :: " + vs);
            }

            c += 2;

            if (c >= 32) {
                c = 0;
            }

            coluna += 12;

            if (coluna >= c16) {
                linha += 12;
                coluna = 0;
            }
        }

    }

    public static void esquerda(Windows eWindows, Renderizador mRenderizador) {


        int coluna = 0;
        int linha = 0;

        int c8 = 12 * 8;

        for (int vi = 0; vi < 256; vi += 4) {

            int va = Inteiro.byteToInt(eWindows.getAudio().getBuffer()[vi]);
            int vb = Inteiro.byteToInt(eWindows.getAudio().getBuffer()[vi + 1]);

            int vg = (va + vb) / 2;


            mRenderizador.drawRect_Pintado(250 + coluna, 300 + linha, 10, 10, new Cor(vg, 0, 0));
            coluna += 12;

            if (coluna >= c8) {
                linha += 12;
                coluna = 0;
            }
        }

    }

    public static void direita(Windows eWindows, Renderizador mRenderizador) {

        int coluna = 0;
        int linha = 0;

        int c8 = 12 * 8;

        for (int vi = 2; vi < 256; vi += 4) {

            int va = Inteiro.byteToInt(eWindows.getAudio().getBuffer()[vi]);
            int vb = Inteiro.byteToInt(eWindows.getAudio().getBuffer()[vi + 1]);

            int vg = (va + vb) / 2;

            mRenderizador.drawRect_Pintado(400 + coluna, 300 + linha, 10, 10, new Cor(vg, 0, 0));
            coluna += 12;

            if (coluna >= c8) {
                linha += 12;
                coluna = 0;
            }
        }
    }

    public static void valores(Windows eWindows, Renderizador mRenderizador, Fonte texto) {

        int coluna = 0;
        int linha = 0;

        int c8 = 12 * 8;

        int al = 0;

        int c = 0;

        for (int vi = 0; vi < 256; vi += 1) {

            int va = Inteiro.byteToInt(eWindows.getAudio().getBuffer()[vi]);

            String sva = String.valueOf(va);

            if (sva.length() == 1) {
                sva = "00" + sva;
            } else if (sva.length() == 2) {
                sva = "0" + sva;
            }

            texto.escreva(160 + coluna, 300 + linha, sva);

            c += 1;
            coluna += 40;

            if (c >= 8) {
                c = 0;
                coluna = 0;
                linha += 20;
            }

        }
    }

    public static int[] normalizado(Windows eWindows, Renderizador mRenderizador, Fonte texto) {

        int coluna = 0;
        int linha = 0;

        int c8 = 12 * 8;

        int al = 0;

        int c = 0;

        int[] valorado = new int[(256 / 2)];

        int chave = 0;

        for (int vi = 0; vi < 256; vi += 2) {

            int va = Inteiro.byteToInt(eWindows.getAudio().getBuffer()[vi]);
            int vb = Inteiro.byteToInt(eWindows.getAudio().getBuffer()[vi + 1]);

            // int val = (va & 0xff) << 8 + (vb & 0xff);
            // int value = 0;
            // value |= (va & 0x000000FF) << (0 * 8);
            //  value |= (vb & 0x000000FF) << (1 * 8);

            short value = (short) (((va & 0xFF) << 8) | (vb & 0xFF));

            //    int valor = ((int) value)/200;

            int valor = (int) value;

            valorado[chave] = valor;
            chave += 1;

            // texto.escreva(600 + coluna, 300 + linha, String.valueOf(valor));

            c += 1;
            coluna += 100;

            if (c >= 4) {
                c = 0;
                coluna = 0;
                linha += 20;
            }

        }

        return valorado;
    }

}

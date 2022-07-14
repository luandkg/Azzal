package apps.app_arquivos.AudioWidgets;

import libs.Arquivos.Binario.Inteiro;

public class Espectrum {

    public static int[] normalizado(byte[] audio_buffer) {

        int[] valorado = new int[(256/2)];

        int chave = 0;

        for (int vi = 0; vi < 256; vi += 2) {

            int va = Inteiro.byteToInt(audio_buffer[vi]);
            int vb = Inteiro.byteToInt(audio_buffer[vi + 1]);

            short value = (short) (((va & 0xFF) << 8) | (vb & 0xFF));

            int valor = (int)value;

            valorado[chave] = valor;
            chave+=1;

        }

        return valorado;
    }

}

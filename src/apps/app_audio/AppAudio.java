package apps.app_audio;

import apps.app_arquivos.AudioWidgets.AudioRender;
import apps.app_arquivos.AudioWidgets.Espectrum;
import apps.app_arquivos.AudioWidgets.HZQuatter;
import libs.arquivos.audio.HZ;
import libs.arquivos.audio.HZControlador;
import azzal.cenarios.Cena;
import azzal.utilitarios.*;
import azzal.Renderizador;
import azzal.Windows;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;

import java.awt.*;
import java.awt.event.KeyEvent;


public class AppAudio extends Cena {

    private Cor mCor;
    private HZ audio;

    private Fonte texto;

    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Audio Player");

        texto = new FonteRunTime(new Cor(0, 0, 0), 10);
        mCor = new Cor(76, 175, 80);

        audio = HZControlador.init("/home/luan/Música/musicas_hz/top.hz");

        eWindows.audio_emitir(audio);


    }


    @Override
    public void update(double dt) {


        if (getWindows().getTeclado().foiPressionado(KeyEvent.VK_P)) {
            if (getWindows().temAudio()) {
                if (getWindows().getAudio().isPausado()) {
                    getWindows().getAudio().reproduzir();
                } else {
                    getWindows().getAudio().pausar();
                }
            }
        }


        getWindows().getMouse().liberar();

        if (!getWindows().getAudio().temMais()) {
            getWindows().getAudio().re_iniciar();
        }

    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);

        if (getWindows().temAudio()) {

            AudioRender.onPlayer(mRenderizador, 100, 200, getWindows().getAudio());


            if (getWindows().getAudio().getBuffer().length == 256) {

                //    HZQuatter.direita(getWindows(), mRenderizador);

                texto.setRenderizador(mRenderizador);
                HZQuatter.valores(getWindows(), mRenderizador, texto);

                // int[] valorado = HZQuatter.normalizado(getWindows(), mRenderizador, texto);

                int[] fluxo_audio = Espectrum.normalizado(getWindows().getAudio().getBuffer());

                AudioRender.onFluxoAmostragem2(mRenderizador, fluxo_audio, 140, 200, mCor);

            }


        }

    }


}

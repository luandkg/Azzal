package AppAttuz.Servicos;

import AppAttuz.Camadas.Massas;
import AppAttuz.Camadas.OnData;
import AppAttuz.EscalasPadroes;
import AppAttuz.Ferramentas.Escala;
import AppAttuz.Ferramentas.MapaRender;
import AppAttuz.Ferramentas.Pintor;
import AppAttuz.Ferramentas.LatitudeCalor;
import AppAttuz.Ferramentas.Normalizador;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Imaginador.Efeitos;
import Imaginador.ImageUtils;
import Letrum.Fonte;
import Letrum.Maker.FonteRunTime;
import Servittor.Servico;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Temperatura extends Servico {

    private String LOCAL;
    private LatitudeCalor[] mLatitudes;

    private int INVERNO = 1;
    private int VERAO = 2;

    public Temperatura(String eLOCAL) {
        LOCAL = eLOCAL;
        mLatitudes = new LatitudeCalor[10];
    }

    @Override
    public void onInit( ) {

        mLatitudes[0] = new LatitudeCalor(1200, -400);
        mLatitudes[1] = new LatitudeCalor(1000, -200);
        mLatitudes[2] = new LatitudeCalor(800, -100);
        mLatitudes[3] = new LatitudeCalor(600, 100);
        mLatitudes[4] = new LatitudeCalor(400, 200);
        mLatitudes[5] = new LatitudeCalor(200, 400);
        mLatitudes[6] = new LatitudeCalor(100, 800);
        mLatitudes[7] = new LatitudeCalor(0, 1000);
        mLatitudes[8] = new LatitudeCalor(-100, 1500);
        mLatitudes[9] = new LatitudeCalor(-200, 2000);

        render(VERAO, 10, 500, 5, LOCAL + "build/temperatura_verao.png");
        render(INVERNO, 10, 500, 5, LOCAL + "build/temperatura_inverno.png");

        unir();

    }


    public void render(int estacao, int TAXA_DISTANCIA, int TAXA_ALTURA, int TAXA_UMIDADE, String eArquivo) {

        Escala mEscala = EscalasPadroes.getEscalaTemperatura();

        OnData onData = new OnData(LOCAL);
        Cartografia onCartografia = new Cartografia(LOCAL);

        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");

        Massas massa = new Massas(LOCAL);
        Massas tectonica = new Massas(LOCAL);

        Normalizador normalizador = new Normalizador(mEscala.getMaximo());

        for (int y = 0; y < mapa.getHeight(); y++) {
            for (int x = 0; x < mapa.getWidth(); x++) {


                if (tectonica.isTerra(x, y)) {


                    int altitude = onData.getAltura(x, y);
                    int distancia_mar = onData.getDistanciaDoMar(x, y);
                    int lat = onCartografia.getLatitudeModular(y);
                    int umidade = onData.getUmidade(x, y);

                    int variavel = 0;

                    if (estacao == INVERNO) {
                        variavel = mLatitudes[lat / 2].getInverno();
                    } else if (estacao == VERAO) {
                        variavel = mLatitudes[lat / 2].getVerao();
                    }

                    int valor = (distancia_mar * TAXA_DISTANCIA) - (altitude / TAXA_ALTURA) - (umidade / TAXA_UMIDADE) + variavel;

                    normalizador.adicionar(valor);

                    massa.setValor(x, y, valor);

                }

            }
        }

        normalizador.equilibrar();
        //normalizador.stock();

        MapaRender.equilibrador(tectonica, tectonica.getTerra(), massa, mEscala, normalizador);

        BufferedImage mTemperatura = Pintor.colorir(mapa, massa, mEscala);

        Renderizador render = new Renderizador(mTemperatura);

        Fonte escrever = new FonteRunTime(Cor.getRGB(Color.BLACK), 20);
        escrever.setRenderizador(render);


        int temp_val = -12;

        int n1 = 0;
        int n2 = 5;

        for (int t = 1; t < 11; t++) {
            render.drawRect_Pintado(100, (t * 50) + 100, 30, 30, Cor.getInt((mEscala.get(t))));
            escrever.escreva(140, (t * 50) + 100, temp_val + "ºC");
            temp_val += n1 + n2;
            n1 += 5;
            n2 += 1;
            if (n1 > 12) {
                n1 = 0;
                n2 += 2;
            }
            if (n2 > 8) {
                n2 = 0;
            }
            if (temp_val >= 44) {
                n2 = 0;
            }
        }


        ImageUtils.exportar(mTemperatura, eArquivo);

    }

    public void unir() {
        Massas tectonica = new Massas(LOCAL);

        BufferedImage img_verao = Efeitos.reduzir(ImageUtils.getImagem(LOCAL + "build/temperatura_verao.png"), tectonica.getLargura() / 3, tectonica.getAltura() / 3);
        BufferedImage img_inverno = Efeitos.reduzir(ImageUtils.getImagem(LOCAL + "build/temperatura_inverno.png"), tectonica.getLargura() / 3, tectonica.getAltura() / 3);

        BufferedImage mapa = new BufferedImage(tectonica.getLargura() / 3, ((tectonica.getAltura() / 3) * 2 + 50), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < img_verao.getHeight(); y++) {
            for (int x = 0; x < img_verao.getWidth(); x++) {
                mapa.setRGB(x, y, img_verao.getRGB(x, y));
            }
        }

        for (int y = 0; y < img_inverno.getHeight(); y++) {
            for (int x = 0; x < img_inverno.getWidth(); x++) {
                mapa.setRGB(x, y + 600, img_inverno.getRGB(x, y));
            }
        }


        ImageUtils.exportar(mapa, LOCAL + "build/temperatura.png");

    }

}

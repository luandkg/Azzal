package AppAttuz.Servicos;

import AppAttuz.Camadas.Massas;
import AppAttuz.Camadas.OnData;
import AppAttuz.EscalasPadroes;
import AppAttuz.Ferramentas.*;
import Arquivos.Video.VideoCodecador;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Imaginador.Efeitos;
import Imaginador.ImageUtils;
import Letrum.Fonte;
import Letrum.Maker.FonteRunTime;
import Servittor.Servico;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Temperatura extends Servico {

    private String LOCAL;
    private LatitudeCalor[] mLatitudes;

    private int INVERNO = 1;
    private int VERAO = 2;

    public Temperatura(String eLOCAL) {
        LOCAL = eLOCAL;
        mLatitudes = new LatitudeCalor[18];
    }

    @Override
    public void onInit() {

        // mLatitudes[0] = new LatitudeCalor(-100, -550);
        //   mLatitudes[1] = new LatitudeCalor(-50, -500);
        //   mLatitudes[2] = new LatitudeCalor(0, -450);
        //   mLatitudes[3] = new LatitudeCalor(50, -400);
        //  mLatitudes[4] = new LatitudeCalor(100, -350);
        //   mLatitudes[5] = new LatitudeCalor(200, -300);
        //  mLatitudes[6] = new LatitudeCalor(300, -250);
        //  mLatitudes[7] = new LatitudeCalor(400, -200);
        // mLatitudes[8] = new LatitudeCalor(500, -150);

        // mLatitudes[9] = new LatitudeCalor(500, -100);
        //  mLatitudes[10] = new LatitudeCalor(400, -50);
        //  mLatitudes[11] = new LatitudeCalor(300, 0);
        //  mLatitudes[12] = new LatitudeCalor(200, 500);
        //  mLatitudes[13] = new LatitudeCalor(100, 400);
        //  mLatitudes[14] = new LatitudeCalor(50, 300);
        //  mLatitudes[15] = new LatitudeCalor(0, 200);
        // mLatitudes[16] = new LatitudeCalor(-50, 100);
        // mLatitudes[17] = new LatitudeCalor(-100, 50);

        mLatitudes[0] = new LatitudeCalor(-100, -450);
        mLatitudes[1] = new LatitudeCalor(-50, -350);
        mLatitudes[2] = new LatitudeCalor(0, -300);
        mLatitudes[3] = new LatitudeCalor(50, -250);
        mLatitudes[4] = new LatitudeCalor(100, -200);
        mLatitudes[5] = new LatitudeCalor(200, -150);
        mLatitudes[6] = new LatitudeCalor(300, -100);
        mLatitudes[7] = new LatitudeCalor(400, 50);
        mLatitudes[8] = new LatitudeCalor(500, 100);

        mLatitudes[9] = new LatitudeCalor(500, 200);
        mLatitudes[10] = new LatitudeCalor(400, 300);
        mLatitudes[11] = new LatitudeCalor(300, 400);
        mLatitudes[12] = new LatitudeCalor(200, 700);
        mLatitudes[13] = new LatitudeCalor(100, 700);
        mLatitudes[14] = new LatitudeCalor(50, 700);
        mLatitudes[15] = new LatitudeCalor(0, 700);
        mLatitudes[16] = new LatitudeCalor(-50, 700);
        mLatitudes[17] = new LatitudeCalor(-100, 700);

        render(VERAO, 10, 500, 5, LOCAL + "build/temperatura_verao.png");
        render(INVERNO, 10, 500, 5, LOCAL + "build/temperatura_inverno.png");

        unir();

        variacao();

    }

    public int getFaixaDeRecebimentoSolar(Massas tectonica, int p) {
        int CARTOGRAFIA_ALTURA = tectonica.getAltura() / 18;
        return (p / CARTOGRAFIA_ALTURA);
    }

    public int getFaixaRecebimentoSolarTamanho(Massas tectonica) {
        int tamanhoFaixa = tectonica.getAltura() / 18;
        return tamanhoFaixa;
    }

    public int getFaixaRecebimentoSolarInicio(Massas tectonica, int eFaixa) {
        int tamanhoFaixa = tectonica.getAltura() / 18;
        return tamanhoFaixa * eFaixa;
    }

    public MassaComNormal gerarDados(Massas tectonica, OnData onData, int estacao, int TAXA_DISTANCIA, int TAXA_ALTURA, int TAXA_UMIDADE, Escala mEscala) {

        Normalizador normalizador = new Normalizador(mEscala.getMaximo());
        Massas dados = new Massas(LOCAL);

        Random eSorte = new Random();

        for (int y = 0; y < tectonica.getAltura(); y++) {

            int em_linha = eSorte.nextInt(10);

            for (int x = 0; x < tectonica.getLargura(); x++) {


                if (tectonica.isTerra(x, y)) {

                    int faixa_solar = getFaixaDeRecebimentoSolar(tectonica, y);

                    int calor_sol = 0;

                    if (estacao == INVERNO) {
                        calor_sol = mLatitudes[faixa_solar].getInverno();
                    } else if (estacao == VERAO) {
                        calor_sol = mLatitudes[faixa_solar].getVerao();
                    }

                    if (eSorte.nextInt(100) >= 50) {
                        calor_sol += eSorte.nextInt(100);
                    } else {
                        calor_sol -= eSorte.nextInt(100);
                    }

                    em_linha += eSorte.nextInt(5);

                    if (em_linha >= 50) {
                        em_linha -= eSorte.nextInt(30) + 10;
                    }

                    if (em_linha < -30) {
                        em_linha += eSorte.nextInt(10) + 5;
                    }

                    if (eSorte.nextInt(100) >= 50) {
                        calor_sol += em_linha;
                    } else {
                        calor_sol -= em_linha;
                    }

                    int altitude = onData.getAltura(x, y);
                    int distancia_mar = onData.getDistanciaDoMar(x, y);
                    int umidade = onData.getUmidade(x, y);


                    int valor = (distancia_mar * TAXA_DISTANCIA) - (altitude / TAXA_ALTURA) - (umidade / TAXA_UMIDADE) + calor_sol;

                    normalizador.adicionar(valor);

                    dados.setValor(x, y, valor);

                }

            }
        }


        //emQuatrum(tectonica, massa);

        return new MassaComNormal(dados, normalizador);
    }

    public void render(int estacao, int TAXA_DISTANCIA, int TAXA_ALTURA, int TAXA_UMIDADE, String eArquivo) {

        Escala mEscala = EscalasPadroes.getEscalaTemperatura();


        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");

        Massas tectonica = new Massas(LOCAL);
        OnData onData = new OnData(LOCAL);


        MassaComNormal dados = gerarDados(tectonica, onData, estacao, TAXA_DISTANCIA, TAXA_ALTURA, TAXA_UMIDADE, mEscala);

        MapaRender.equilibrador(tectonica, tectonica.getTerra(), dados.getDados(), mEscala, dados.getNormalizado());



        BufferedImage mTemperatura = Pintor.colorir(mapa, dados.getDados(), mEscala);

        Renderizador render = new Renderizador(mTemperatura);

        Fonte escrever = new FonteRunTime(Cor.getRGB(Color.BLACK), 20);
        escrever.setRenderizador(render);


        int temp_val = -12;


        for (int t = 1; t < 12; t++) {
            render.drawRect_Pintado(100, (t * 50) + 100, 30, 30, Cor.getInt((mEscala.get(t))));
            escrever.escreva(140, (t * 50) + 100, temp_val + "ºC");
            temp_val +=6;

        }


        ImageUtils.exportar(mTemperatura, eArquivo);

    }

    public void unir() {

        Massas tectonica = new Massas(LOCAL);

        BufferedImage img_verao = Efeitos.reduzir(ImageUtils.getImagem(LOCAL + "build/temperatura_verao.png"), tectonica.getLargura() / 3, tectonica.getAltura() / 3);
        BufferedImage img_inverno = Efeitos.reduzir(ImageUtils.getImagem(LOCAL + "build/temperatura_inverno.png"), tectonica.getLargura() / 3, tectonica.getAltura() / 3);

        BufferedImage mapa = new BufferedImage(((tectonica.getLargura() / 3) * 2) + 20, (tectonica.getAltura() / 3) +30, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < img_verao.getHeight(); y++) {
            for (int x = 0; x < img_verao.getWidth(); x++) {
                mapa.setRGB(x, y, img_verao.getRGB(x, y));
            }
        }

        for (int y = 0; y < img_inverno.getHeight(); y++) {
            for (int x = 0; x < img_inverno.getWidth(); x++) {
                mapa.setRGB(x + img_verao.getWidth() + 10, y, img_inverno.getRGB(x, y));
            }
        }


        ImageUtils.exportar(mapa, LOCAL + "build/temperatura.png");

    }


    public void emQuatrum(Massas tectonica, Massas massa) {


        for (int y = 0; y < tectonica.getAltura(); y++) {
            for (int x = 0; x < tectonica.getLargura(); x++) {

                if (tectonica.isTerra(x, y)) {

                    int valor = massa.getValor(x, y);

                    if (tectonica.isTerra(x, y - 1) && tectonica.isTerra(x, y + 1) && tectonica.isTerra(x - 1, y) && tectonica.isTerra(x + 1, y)) {

                        valor += massa.getValor(x, y - 1);
                        valor += massa.getValor(x, y + 1);
                        valor += massa.getValor(x - 1, y);
                        valor += massa.getValor(x + 1, y);

                        valor = valor / 5;


                    }


                    massa.setValor(x, y, valor);

                }


            }
        }


    }

    public void variacao() {

        int quantidade = 10;

        int futuro_real = quantidade;

        Escala mEscala = EscalasPadroes.getEscalaTemperatura();

        OnData onData = new OnData(LOCAL);

        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");

        Massas tectonica = new Massas(LOCAL);


        MassaComNormal verao = gerarDados(tectonica, onData, VERAO, 10, 500, 5, mEscala);
        MassaComNormal inverno =   gerarDados(tectonica, onData, INVERNO, 10, 500, 5, mEscala);

        Variattor eVariattor = new Variattor();
        eVariattor.init(tectonica, quantidade, verao.getDados(), inverno.getDados());



        Massas verao_normalizado = renderJa(tectonica, verao.getDados(), verao.getNormalizado(), mEscala);

        //  ImageUtils.exportar(Pintor.colorir(mapa, verao_normalizado, mEscala), LOCAL + "build/var_temperatura/0v.png");



        Massas inverno_normalizado = renderJa(tectonica, inverno.getDados(), inverno.getNormalizado(), mEscala);

        // ImageUtils.exportar(Pintor.colorir(mapa, inverno_normalizado, mEscala), LOCAL + "build/var_temperatura/oi.png");

        ArrayList<String> eArquivos = new ArrayList<String>();

        for (int i = 0; i < (quantidade); i++) {

            Normalizador cGeral = new Normalizador(mEscala.getMaximo());

            for (int y = 0; y < tectonica.getAltura(); y++) {
                for (int x = 0; x < tectonica.getLargura(); x++) {

                    if (tectonica.isTerra(x, y)) {


                        int inicio = inverno.getDados().getValor(x, y);

                        if (eVariattor.temVariacao(x, y)) {

                            double variacao = eVariattor.getVariacao(x, y);

                            if (i > 0) {
                                inicio += (int) variacao;
                            }


                            inverno.getDados().setValor(x, y, inicio);


                        }

                        cGeral.adicionar(inicio);

                    }


                }
            }

            cGeral.equilibrar();

            Massas corrente_normalizado = renderJa(tectonica, inverno.getDados(), cGeral, mEscala);


            println("Variacao :: " + i);

            int passado = (quantidade - i - 1);
            int futuro = (quantidade + i);

            ImageUtils.exportar(Pintor.colorir(mapa, corrente_normalizado, mEscala), LOCAL + "build/var_temperatura/temperatura_" + passado + ".png");

            eArquivos.add(LOCAL + "build/var_temperatura/temperatura_" + passado + ".png");

            if (futuro != quantidade && futuro != ((2 * quantidade) - 1)) {

                ImageUtils.exportar(Pintor.colorir(mapa, corrente_normalizado, mEscala), LOCAL + "build/var_temperatura/temperatura_" + futuro_real + ".png");

                eArquivos.add(LOCAL + "build/var_temperatura/temperatura_" + futuro_real + ".png");


                futuro_real += 1;
            }

        }

        //VideoCodecador.criar(LOCAL + "build/temperatura.vi", eArquivos);
        for (String eArquivo : eArquivos) {
            //new File(eArquivo).delete();
        }
    }

    public Massas renderJa(Massas tectonica, Massas dados, Normalizador vGeral, Escala mEscala) {

        Massas renderAqui = new Massas(LOCAL);

        for (int y = 0; y < tectonica.getAltura(); y++) {
            for (int x = 0; x < tectonica.getLargura(); x++) {
                if (tectonica.getValor(x, y) == tectonica.getTerra()) {
                    int real = vGeral.intervalo(mEscala.getMinimo(), mEscala.getMaximo(), vGeral.get(dados.getValor(x, y)));
                    renderAqui.setValor(x, y, real);
                }
            }
        }

        return renderAqui;
    }
}

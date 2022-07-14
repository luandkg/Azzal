package apps.AppAttuz.Servicos;

import apps.AppAttuz.Assessorios.Escala;
import apps.AppAttuz.Camadas.Massas;
import apps.AppAttuz.Camadas.MassasDados;
import apps.AppAttuz.Camadas.DadosQTT;
import apps.AppAttuz.Camadas.EscalasPadroes;
import apps.AppAttuz.Ferramentas.*;
import apps.AppAttuz.Legendas.Legendar;
import apps.AppAttuz.Legendas.Legendas;
import libs.Imaginador.ImageUtils;
import libs.Servittor.Servico;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Temperatura extends Servico {

    private String LOCAL;

    private int INVERNO = 1;
    private int VERAO = 2;


    public Temperatura(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {


        int TAXA_DISTANCIA_MAR = 10;
        int TAXA_ALTITUDE = 500;
        int TAXA_UMIDADE = 5;

        marcarInicio();

        println("-->> VERAO :: INVERNO");
        criarTemperatura(VERAO, TAXA_DISTANCIA_MAR, TAXA_ALTITUDE, TAXA_UMIDADE, LOCAL + "build/temperatura_vi.png", LOCAL + "dados/temperatura_vi.qtt");


        marcarFim();
        mostrarTempo();


        marcarInicio();

        println("-->> INVERNO :: VERAO");
        criarTemperatura(INVERNO, TAXA_DISTANCIA_MAR, TAXA_ALTITUDE, TAXA_UMIDADE, LOCAL + "build/temperatura_iv.png", LOCAL + "dados/temperatura_iv.qtt");

        marcarFim();
        mostrarTempo();


        UnirMapas.lado_a_lado(LOCAL, "build/temperatura_vi.png", "build/temperatura_iv.png", "build/temperatura.png");


        boolean variar = true;

        if (variar) {

            marcarInicio();

            int QUANTIDADE_VARIACAO = 10;

            println("-->> VARIACAO :: " + QUANTIDADE_VARIACAO);

            VariattorSequencia vs = new VariattorSequencia();


            MassaComNormal verao = gerarDados(VERAO, TAXA_DISTANCIA_MAR, TAXA_ALTITUDE, TAXA_UMIDADE, EscalasPadroes.getEscalaTemperatura());
            MassaComNormal inverno = gerarDados(INVERNO, TAXA_DISTANCIA_MAR, TAXA_ALTITUDE, TAXA_UMIDADE, EscalasPadroes.getEscalaTemperatura());

            vs.variacao(LOCAL, 10, verao, inverno, EscalasPadroes.getEscalaTemperatura(), "build/var_temperatura/temperatura_");

            marcarFim();
            mostrarTempo();

        }


    }


    public void criarTemperatura(int estacao, int TAXA_DISTANCIA, int TAXA_ALTURA, int TAXA_UMIDADE, String eArquivo, String eArquivoQTT) {

        Escala mEscala = EscalasPadroes.getEscalaTemperatura();


        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");

        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        MassaComNormal dados = gerarDados(estacao, TAXA_DISTANCIA, TAXA_ALTURA, TAXA_UMIDADE, mEscala);

        MapaRender.equilibrador(tectonica, tectonica.getTerra(), dados.getDados(), mEscala, dados.getNormalizado());


        System.out.println("Guardar Temperatura - QTT");

        MassaToQTT.salvarTerra(tectonica, dados.getDados(), eArquivoQTT);


        BufferedImage mTemperatura = Pintor.colorir(mapa, dados.getDados(), mEscala);

        mTemperatura = Legendar.legendar(mTemperatura, Legendas.getTemperatura(), EscalasPadroes.getEscalaTemperatura(), 100, 100);


        ImageUtils.exportar(mTemperatura, eArquivo);

    }

    public MassaComNormal gerarDados(int estacao, int TAXA_DISTANCIA, int TAXA_ALTURA, int TAXA_UMIDADE, Escala mEscala) {

        Normalizador normalizador = new Normalizador(mEscala.getMaximo());

        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        Massas dados = MassasDados.getTerraAgua(LOCAL);
        DadosQTT dadosQTT = new DadosQTT(LOCAL);

        Random eSorte = new Random();

        RecebimentoSolar mRecebimentoSolar = new RecebimentoSolar();

        for (int y = 0; y < tectonica.getAltura(); y++) {

            int em_linha = eSorte.nextInt(10);

            for (int x = 0; x < tectonica.getLargura(); x++) {


                if (tectonica.isTerra(x, y)) {

                    int faixa_solar = mRecebimentoSolar.getFaixaDeRecebimentoSolar(tectonica, y);

                    int calor_sol = 0;

                    if (estacao == INVERNO) {
                        calor_sol = mRecebimentoSolar.getInverno(faixa_solar);
                    } else if (estacao == VERAO) {
                        calor_sol = mRecebimentoSolar.getVerao(faixa_solar);
                    }

                    // if (eSorte.nextInt(100) >= 50) {
                    //     calor_sol += eSorte.nextInt(100);
                    //  } else {
                    //      calor_sol -= eSorte.nextInt(100);
                    // }

                    if (eSorte.nextInt(100) >= 50) {

                        if (eSorte.nextInt(100) >= 50) {
                            calor_sol += eSorte.nextInt(100);
                        } else {
                            calor_sol -= eSorte.nextInt(100);
                        }

                    }

                    if (eSorte.nextInt(100) >= 50) {

                        if (eSorte.nextInt(100) >= 50) {
                            em_linha += eSorte.nextInt(5);
                        } else {
                            em_linha -= eSorte.nextInt(5);
                        }

                    }


                    if (em_linha >= 50) {
                        em_linha = eSorte.nextInt(30);
                    } else if (em_linha <= -30) {
                        em_linha = eSorte.nextInt(10);
                    }

                    if (eSorte.nextInt(100) >= 50) {
                        calor_sol += em_linha;
                    } else {
                        calor_sol -= em_linha;
                    }

                    int altitude = dadosQTT.getAltura(x, y);
                    int distancia_mar = dadosQTT.getDistanciaDoMar(x, y);
                    int umidade = dadosQTT.getUmidade(x, y);


                    //   int valor = (distancia_mar * TAXA_DISTANCIA) - (altitude / TAXA_ALTURA) - (umidade / TAXA_UMIDADE) + calor_sol;

                    int valor = (distancia_mar * TAXA_DISTANCIA) - (altitude / TAXA_ALTURA) - (umidade * TAXA_UMIDADE) + calor_sol;

                    normalizador.adicionar(valor);

                    dados.setValor(x, y, valor);

                }

            }
        }


        return new MassaComNormal(dados, normalizador);
    }

}
